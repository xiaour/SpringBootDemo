package com.xiaour.spring.boot.controller;

import com.xiaour.spring.boot.utils.JsonUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * @Author: Xiaour
 * @Description:
 * @Date: 2018/3/26 15:53
 */
@RestController
@RequestMapping(value="/test")
public class OrderDispose {



    /**
     * 获取redis中的值
     * @param key
     * @return
     */
    @RequestMapping("/get")
    public String get(String key){
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) {

        try {



            String [] 小区={"小区","家园","嘉园","花园","公寓","胡同","社区","单元"};
            String [] 公司={"大厦","写字楼","服务楼","基地","公司","SOHO","中心","商厦"};
            String [] 批发和零售业={"批发","零售","超市","便利店","小卖部","卖场","手机","电脑","鲜花"};
            String [] 交通运输仓储和邮政业={"物流","快递","运输","储备","储运","邮政"};
            String [] 住宿和餐饮业={"客栈","宾馆","酒店","饭店","餐厅","HOTEL","招待所","山庄","洗浴中心","会所"};
            String [] 信息传输软件和信息技术服务业={"软件","通信","科技园","中国移动","中国联通"};
            String [] 金融业={"金融","基金","投资","股票","证券","银行"};
            String [] 房地产业={"房产","地产","建筑"};
            String [] 租赁和商务服务业={"出租","租赁","租售"};
            String [] 科学研究和技术服务业={"研究所","产业园","研究院"};
            String [] 水利环境和公共设施管理业={"水利","环境","设施"};
            String [] 居民服务修理和其他服务业={"维修","修理","地铁","五金","造型","商务","服务","网吧","蛋糕","网咖","健身","家政","宠物","干洗","美容","美发","理发","文印","文具","洗车"};
            String [] 教育={"大学","学校","中学","高中","学院","幼儿园","门诊","教育","培训","教学","亲子"};
            String [] 卫生和社会工作={"医院","诊所","门诊","住院","急诊","卫生所","防疫"};
            String [] 文化体育和娱乐业={"健身","运动","体育","图书馆"};
            String [] 商场={"商场","百货","大悦城","购物"};
            String [] 公共管理社会保障和社会组织={"大使馆"};
            int xq=0,gs=0,pfhlsy=0,jtyscchyz=0,zshcy=0,xxcsrj=0,
                    jry=0,fdcy=0,zlhswfwy=0,kxyjhjsfwy=0,slhjhggssgl=0,
                    jmfwxl=0,jy=0,wshshgz=0,whty=0,sc=0,gggl=0;

            Map<String,Integer> dataList= new HashMap<>();



            int other=0;
            List<List<List<String>>> list= readExcelWithoutTitle("/3.工作文件/美团地址.xls");

            for(List<List<String>> lt:list) {

                System.out.println("共有记录：" + lt.size());
                for (List<String> ls : lt) {

                    for (String address : ls) {
                        boolean flag = true;
                        for (String tag : 公司) {
                            if (address.contains(tag)) {
                                gs++;
                                flag = false;
                                break;
                            }
                        }
                        for (String tag : 卫生和社会工作) {
                            if (address.contains(tag)) {
                                wshshgz++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 教育) {
                            if (address.contains(tag)) {
                                jy++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 小区) {
                            if (address.contains(tag)) {
                                xq++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 住宿和餐饮业) {
                            if (address.contains(tag)) {
                                zshcy++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 批发和零售业) {
                            if (address.contains(tag)) {
                                pfhlsy++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 交通运输仓储和邮政业) {
                            if (address.contains(tag)) {
                                jtyscchyz++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 金融业) {
                            if (address.contains(tag)) {
                                jry++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 房地产业) {
                            if (address.contains(tag)) {
                                fdcy++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 租赁和商务服务业) {
                            if (address.contains(tag)) {
                                zlhswfwy++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 信息传输软件和信息技术服务业) {
                            if (address.contains(tag)) {
                                xxcsrj++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 科学研究和技术服务业) {
                            if (address.contains(tag)) {
                                kxyjhjsfwy++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 水利环境和公共设施管理业) {
                            if (address.contains(tag)) {
                                slhjhggssgl++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 居民服务修理和其他服务业) {
                            if (address.contains(tag)) {
                                jmfwxl++;
                                flag = false;
                                break;
                            }
                        }


                        for (String tag : 文化体育和娱乐业) {
                            if (address.contains(tag)) {
                                whty++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 商场) {
                            if (address.contains(tag)) {
                                sc++;
                                flag = false;
                                break;
                            }
                        }

                        for (String tag : 公共管理社会保障和社会组织) {
                            if (address.contains(tag)) {
                                gggl++;
                                flag = false;
                                break;
                            }
                        }

                        if (flag) {
                            System.out.println(address);
                            other++;
                            flag = false;
                        }
                    }

                }

                dataList.put("小区",xq);
                dataList.put("公司",gs);
                dataList.put("批发和零售业",pfhlsy);
                dataList.put("交通运输、仓储和邮政业",jtyscchyz);
                dataList.put("住宿和餐饮业",zshcy);
                dataList.put("信息传输、软件和信息技术服务业",xxcsrj);
                dataList.put("金融业",jry);
                dataList.put("房地产业",fdcy);
                dataList.put("租赁和商务服务业",zlhswfwy);
                dataList.put("科学研究和技术服务业",kxyjhjsfwy);
                dataList.put("水利、环境和公共设施管理业",slhjhggssgl);
                dataList.put("居民服务、修理和其他服务业",jmfwxl);
                dataList.put("教育",jy);
                dataList.put("卫生和社会工作",wshshgz);
                dataList.put("文化、体育和娱乐业",whty);
                dataList.put("商场",sc);
                dataList.put("公共管理、社会保障和社会组织",gggl);
                dataList.put("未知",other);


                System.out.println(JsonUtil.getJsonString(dataList));

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 适用于没有标题行的excel，例如
     * 张三	25岁		男	175cm
     * 李四	22岁		女	160cm
     * 每一行构成一个map，key值是列标题，value是列值。没有值的单元格其value值为null
     * 返回结果最外层的list对应一个excel文件，第二层的list对应一个sheet页，第三层的map对应sheet页中的一行
     * @throws Exception
     */
    public static List<List<List<String>>> readExcelWithoutTitle(String filepath) throws Exception{
        String fileType = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
        InputStream is = null;
        Workbook wb = null;
        try {
            is = new FileInputStream(filepath);

            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                throw new Exception("读取的不是excel文件");
            }

            List<List<List<String>>> result = new ArrayList<List<List<String>>>();//对应excel文件

            int sheetSize = wb.getNumberOfSheets();
            for (int i = 0; i < sheetSize; i++) {//遍历sheet页
                Sheet sheet = wb.getSheetAt(i);
                List<List<String>> sheetList = new ArrayList<List<String>>();//对应sheet页

                int rowSize = sheet.getLastRowNum() + 1;
                for (int j = 0; j < rowSize; j++) {//遍历行
                    Row row = sheet.getRow(j);
                    if (row == null) {//略过空行
                        continue;
                    }
                    int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
                    List<String> rowList = new ArrayList<String>();//对应一个数据行
                    for (int k = 0; k < cellSize; k++) {
                        Cell cell = row.getCell(k);
                        String value = null;
                        if (cell != null) {
                            value = cell.toString();
                        }
                        rowList.add(value);
                    }
                    sheetList.add(rowList);
                }
                result.add(sheetList);
            }

            return result;
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (wb != null) {
                wb.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }
}
