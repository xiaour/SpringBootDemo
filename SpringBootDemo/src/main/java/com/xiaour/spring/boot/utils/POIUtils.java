package com.xiaour.spring.boot.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * 
* @ClassName: POIUtils 
* @Description: POIUtils工具类
*
 */
public class POIUtils {
	
	   /**
     * 功能：拷贝sheet
     * 实际调用     copySheet(targetSheet, sourceSheet, targetWork, sourceWork, true)
     * @param targetSheet
     * @param sourceSheet
     * @param targetWork
     * @param sourceWork                                                                   
     */
    public static void copySheet(SXSSFSheet targetSheet, XSSFSheet sourceSheet,
            SXSSFWorkbook targetWork, SXSSFWorkbook sourceWork) throws Exception{
        if(targetSheet == null || sourceSheet == null || targetWork == null || sourceWork == null){
            throw new IllegalArgumentException("调用PoiUtil.copySheet()方法时，targetSheet、sourceSheet、targetWork、sourceWork都不能为空，故抛出该异常！");
        }
        copySheet(targetSheet, sourceSheet, targetWork, sourceWork, true);
    }
 
    /**
     * 功能：拷贝sheet
     * @param targetSheet
     * @param sourceSheet
     * @param targetWork
     * @param sourceWork
     * @param copyStyle                 boolean 是否拷贝样式
     */
	public static void copySheet(SXSSFSheet targetSheet, XSSFSheet sourceSheet,
            SXSSFWorkbook targetWork, SXSSFWorkbook sourceWork, boolean copyStyle)throws Exception {
         
        if(targetSheet == null || sourceSheet == null || targetWork == null || sourceWork == null){
            throw new IllegalArgumentException("调用PoiUtil.copySheet()方法时，targetSheet、sourceSheet、targetWork、sourceWork都不能为空，故抛出该异常！");
        }
         
        //复制源表中的行
        int maxColumnNum = 0;
 
        Map styleMap = (copyStyle) ? new HashMap() : null;
         
        Drawing patriarch = targetSheet.createDrawingPatriarch(); //用于复制注释
        for (int i = sourceSheet.getFirstRowNum(); i <= sourceSheet.getLastRowNum(); i++) {
        	XSSFRow sourceRow = sourceSheet.getRow(i);
            SXSSFRow targetRow = (SXSSFRow) targetSheet.createRow(i);
             
            if (sourceRow != null) {
                copyRow(targetRow, sourceRow,
                        targetWork, sourceWork,patriarch, styleMap);
                if (sourceRow.getLastCellNum() > maxColumnNum) {
                	
                    maxColumnNum = sourceRow.getLastCellNum();
                }
            }
        }
         
        //复制源表中的合并单元格
        mergerRegion(targetSheet, sourceSheet);
         
        //设置目标sheet的列宽
        if (maxColumnNum > 100) {
        	maxColumnNum = 100;
        }
        for (int i = 0; i <= maxColumnNum; i++) {
            targetSheet.setColumnWidth(i, sourceSheet.getColumnWidth(i));
        }
    }
     
    /**
     * 功能：拷贝row
     * @param targetRow
     * @param sourceRow
     * @param styleMap
     * @param targetWork
     * @param sourceWork
     * @param targetPatriarch
     */
    public static void copyRow(SXSSFRow targetRow, XSSFRow sourceRow,
            SXSSFWorkbook targetWork, SXSSFWorkbook sourceWork,Drawing targetPatriarch, Map styleMap) throws Exception {
        if(targetRow == null || sourceRow == null || targetWork == null || sourceWork == null || targetPatriarch == null){
            throw new IllegalArgumentException("调用PoiUtil.copyRow()方法时，targetRow、sourceRow、targetWork、sourceWork、targetPatriarch都不能为空，故抛出该异常！");
        }
         
        //设置行高
        targetRow.setHeight(sourceRow.getHeight());
         
        for (int i = sourceRow.getFirstCellNum(); i <= sourceRow.getLastCellNum(); i++) {
            XSSFCell sourceCell = sourceRow.getCell(i);
            SXSSFCell targetCell = (SXSSFCell) targetRow.getCell(i);
             
            if (sourceCell != null) {
                if (targetCell == null) {
                    targetCell = (SXSSFCell) targetRow.createCell(i);
                }
                 
                //拷贝单元格，包括内容和样式
                copyCell(targetCell, sourceCell, targetWork, sourceWork, styleMap);
                 
                //拷贝单元格注释
                copyComment(targetCell,sourceCell,targetPatriarch);
            }
        }
    }
     
    /**
     * 功能：拷贝cell，依据styleMap是否为空判断是否拷贝单元格样式
     * @param targetCell            不能为空
     * @param sourceCell            不能为空
     * @param targetWork            不能为空
     * @param sourceWork            不能为空
     * @param styleMap              可以为空                
     */
    public static void copyCell(SXSSFCell targetCell, XSSFCell sourceCell, SXSSFWorkbook targetWork, SXSSFWorkbook sourceWork,Map styleMap) {
        if(targetCell == null || sourceCell == null || targetWork == null || sourceWork == null ){
            throw new IllegalArgumentException("调用PoiUtil.copyCell()方法时，targetCell、sourceCell、targetWork、sourceWork都不能为空，故抛出该异常！");
        }
         
        //处理单元格样式
        if(styleMap != null){
            if (targetWork == sourceWork) {
                targetCell.setCellStyle(sourceCell.getCellStyle());
            } else {
                String stHashCode = "" + sourceCell.getCellStyle().hashCode();
                CellStyle targetCellStyle = (XSSFCellStyle) styleMap
                        .get(stHashCode);
                if (targetCellStyle == null) {
                    targetCellStyle = targetWork.createCellStyle();
                    targetCellStyle.cloneStyleFrom(sourceCell.getCellStyle());
                    styleMap.put(stHashCode, targetCellStyle);
                }
                 
                targetCell.setCellStyle(targetCellStyle);
            }
        }
         
        //处理单元格内容
        switch (sourceCell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            targetCell.setCellValue(sourceCell.getRichStringCellValue());
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            targetCell.setCellValue(sourceCell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            targetCell.setCellType(HSSFCell.CELL_TYPE_BLANK);
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            targetCell.setCellValue(sourceCell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_ERROR:
            targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
            break;
        case HSSFCell.CELL_TYPE_FORMULA:
            targetCell.setCellFormula(sourceCell.getCellFormula());
            break;
        default:
            break;
        }
    }
     
    /**
     * 功能：拷贝comment
     * @param targetCell
     * @param sourceCell
     * @param targetPatriarch
     */
    public static void copyComment(SXSSFCell targetCell,XSSFCell sourceCell,Drawing targetPatriarch)throws Exception{
        if(targetCell == null || sourceCell == null || targetPatriarch == null){
            throw new IllegalArgumentException("调用PoiUtil.copyCommentr()方法时，targetCell、sourceCell、targetPatriarch都不能为空，故抛出该异常！");
        }
         
        //处理单元格注释
        Comment comment = sourceCell.getCellComment();
        if(comment != null){
            Comment newComment = targetPatriarch.createCellComment(new XSSFClientAnchor());
            newComment.setAuthor(comment.getAuthor());
            newComment.setColumn(comment.getColumn());
            newComment.setRow(comment.getRow());
            newComment.setString(comment.getString());
            newComment.setVisible(comment.isVisible());
            
            targetCell.setCellComment(newComment);
        }
    }
     
    /**
     * 功能：复制原有sheet的合并单元格到新创建的sheet
     * 
     * @param sourceSheet
     */
    public static void mergerRegion(SXSSFSheet targetSheet, XSSFSheet sourceSheet)throws Exception {
        if(targetSheet == null || sourceSheet == null){
            throw new IllegalArgumentException("调用PoiUtil.mergerRegion()方法时，targetSheet或者sourceSheet不能为空，故抛出该异常！");
        }
         
        for (int i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
            CellRangeAddress oldRange = sourceSheet.getMergedRegion(i);
            CellRangeAddress newRange = new CellRangeAddress(
                    oldRange.getFirstRow(), oldRange.getLastRow(),
                    oldRange.getFirstColumn(), oldRange.getLastColumn());
            targetSheet.addMergedRegion(newRange);
        }
    }

}
