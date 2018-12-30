package com.xiaour.spring.boot.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;


public class ExcelHander {
    /**
     * 取得指定单元格行和列
     * @param keyMap 所有单元格行、列集合
     * @param key 单元格标识
     * @return 0：列 1：行（列表型数据不记行，即1无值）
     */
    public static int[] getPos(HashMap<String, Object> keyMap, String key) {
        int[] ret = new int[0];

        String val = (String) keyMap.get(key);

        if (val == null || val.length() == 0)
            return ret;

        String pos[] = val.split(",");

        if (pos.length == 1 || pos.length == 2) {
            ret = new int[pos.length];
            for (int i0 = 0; i0 < pos.length; i0++) {
                if (pos[i0] != null && pos[i0].trim().length() > 0) {
                    ret[i0] = Integer.parseInt(pos[i0].trim());
                } else {
                    ret[i0] = 0;
                }
            }
        }
        return ret;
    }

    /**
     * 取对应格子的值
     * @param sheet
     * @param rowNo 行
     * @param cellNo 列
     * @return
     * @throws IOException
     */
    public static String getCellValue(Sheet sheet, int rowNo, int cellNo) {
        String cellValue = null;
        Row row = sheet.getRow(rowNo);
        Cell cell = row.getCell(cellNo);
        if (cell != null) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                DecimalFormat df = new DecimalFormat("0");
                cellValue = getCutDotStr(df.format(cell.getNumericCellValue()));
            } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                cellValue = cell.getStringCellValue();
            }
            if (cellValue != null) {
                cellValue = cellValue.trim();
            }
        } else {
            cellValue = null;
        }
        return cellValue;
    }

    /**
     * 取整数
     * @param srcString
     * @return
     */
    private static String getCutDotStr(String srcString) {
        String newString = "";
        if (srcString != null && srcString.endsWith(".0")) {
            newString = srcString.substring(0, srcString.length() - 2);
        } else {
            newString = srcString;
        }
        return newString;
    }

    /**
     * 读数据模板
     * @throws IOException
     */
    public static HashMap<String, Object>[] getTemplateFile(String templateFileName) throws IOException {
        FileInputStream fis = new FileInputStream(templateFileName);

        Workbook wbPartModule = null;
        if (templateFileName.endsWith(".xlsx")) {
            wbPartModule = new XSSFWorkbook(fis);
        } else if (templateFileName.endsWith(".xls")) {
            wbPartModule = new HSSFWorkbook(fis);
        }

        int numOfSheet = wbPartModule.getNumberOfSheets();
        HashMap<String, Object>[] templateMap = new HashMap[numOfSheet];
        for (int i = 0; i < numOfSheet; i++) {
            Sheet sheet = wbPartModule.getSheetAt(i);
            templateMap[i] = new HashMap<String, Object>();
            readSheet(templateMap[i], sheet);
        }
        fis.close();
        return templateMap;
    }

    /**
     * @throws Exception
     *
     * @Title: getTemplateFile
     * @Description: 获取对应的模板sheet
     * @param @param templateFileName
     * @param @param sheetName
     * @param @return
     * @param @throws IOException    设定文件
     * @return HashMap<String               ,               Object>[]    返回类型
     * @throws
     */
    public static void getTemplateSheet(SXSSFSheet targetSheet, SXSSFWorkbook targetWork, InputStream fis, String sheetName) throws Exception {

        XSSFWorkbook xs = new XSSFWorkbook(fis);
        SXSSFWorkbook wbPartModule = new SXSSFWorkbook(xs);
        XSSFSheet sheetTemp1 = null;
        int numOfSheet1 = xs.getNumberOfSheets();
        //获取
        for (int i = 0; i < numOfSheet1; i++) {
            XSSFSheet sheet = xs.getSheetAt(i);
            if (sheet != null) {
                String name = sheet.getSheetName();
                if (sheetName.equals(name)) {
                    sheetTemp1 = sheet;
                }
            }
        }

        POIUtils.copySheet(targetSheet, sheetTemp1, targetWork, wbPartModule);
        fis.close();
    }

    /**
     * 读模板数据的样式值置等信息
     * @param keyMap
     * @param sheet
     */
    private static void readSheet(HashMap<String, Object> keyMap, Sheet sheet) {
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("当前sheet名称 -------------" + sheet.getSheetName());
        for (int j = firstRowNum; j <= lastRowNum; j++) {
            Row rowIn = sheet.getRow(j);
            if (rowIn == null) {
                continue;
            }
            int firstCellNum = rowIn.getFirstCellNum();
            int lastCellNum = rowIn.getLastCellNum();
            for (int k = firstCellNum; k <= lastCellNum; k++) {
//              Cell cellIn = rowIn.getCell((short) k);
                Cell cellIn = rowIn.getCell(k);
                if (cellIn == null) {
                    continue;
                }

                int cellType = cellIn.getCellType();
                if (Cell.CELL_TYPE_STRING != cellType) {
                    continue;
                }
                String cellValue = cellIn.getStringCellValue();
                if (cellValue == null) {
                    continue;
                }
                cellValue = cellValue.trim();
                if (cellValue.length() > 2 && cellValue.substring(0, 2).equals("<%")) {
                    String key = cellValue.substring(2, cellValue.length());
                    String keyPos = Integer.toString(k) + "," + Integer.toString(j);
                    keyMap.put(key, keyPos);
                    keyMap.put(key + "CellStyle", cellIn.getCellStyle());
                } else if (cellValue.length() > 3 && cellValue.substring(0, 3).equals("<!%")) {
                    String key = cellValue.substring(3, cellValue.length());
                    keyMap.put("STARTCELL", Integer.toString(j));
                    keyMap.put(key, Integer.toString(k));
                    keyMap.put(key + "CellStyle", cellIn.getCellStyle());
                }
            }
        }
    }

    /**
     * 获取格式，不适于循环方法中使用，wb.createCellStyle()次数超过4000将抛异常
     * @param keyMap
     * @param key
     * @return
     */
    public static CellStyle getStyle(HashMap<String, Object> keyMap, String key, Workbook wb) {
        CellStyle cellStyle = null;

        cellStyle = (CellStyle) keyMap.get(key + "CellStyle");
        //当字符超出时换行
        cellStyle.setWrapText(true);
        CellStyle newStyle = wb.createCellStyle();
        newStyle.cloneStyleFrom(cellStyle);
        return newStyle;
    }

    /**
     * Excel单元格输出
     * @param sheet
     * @param row 行
     * @param cell 列
     * @param value 值
     * @param cellStyle 样式
     */
    public static void setValue(Sheet sheet, int row, int cell, Object value, CellStyle cellStyle) {
        Row rowIn = sheet.getRow(row);
        if (rowIn == null) {
            rowIn = sheet.createRow(row);
        }
        Cell cellIn = rowIn.getCell(cell);
        if (cellIn == null) {
            cellIn = rowIn.createCell(cell);
        }
        if (cellStyle != null) {
            //修复产生多超过4000 cellStyle 异常
            //CellStyle newStyle = wb.createCellStyle();
            //newStyle.cloneStyleFrom(cellStyle);
            cellIn.setCellStyle(cellStyle);
        }
        //对时间格式进行单独处理
        if (value == null) {
            cellIn.setCellValue("");
        } else {
            if (isCellDateFormatted(cellStyle)) {
                cellIn.setCellValue((Date) value);
            } else {
                cellIn.setCellValue(new XSSFRichTextString(value.toString()));
            }
        }
    }

    /**
     * 根据表格样式判断是否为日期格式
     * @param cellStyle
     * @return
     */
    public static boolean isCellDateFormatted(CellStyle cellStyle) {
        if (cellStyle == null) {
            return false;
        }
        int i = cellStyle.getDataFormat();
        String f = cellStyle.getDataFormatString();

        return org.apache.poi.ss.usermodel.DateUtil.isADateFormat(i, f);
    }

    /**
     * 适用于导出的数据Excel格式样式重复性较少
     * 不适用于循环方法中使用
     * @param wbModule
     * @param sheet
     * @param pos 模板文件信息
     * @param startCell 开始的行
     * @param value 要填充的数据
     * @param cellStyle 表格样式
     */
    public static void createCell(Workbook wbModule, Sheet sheet, HashMap<String, Object> pos, int startCell, Object value, String cellStyle) {
        int[] excelPos = getPos(pos, cellStyle);
        setValue(sheet, startCell, excelPos[0], value, getStyle(pos, cellStyle, wbModule));
    }

}
