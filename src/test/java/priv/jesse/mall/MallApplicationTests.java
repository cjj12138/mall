package priv.jesse.mall;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import priv.jesse.mall.utils.FileUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests {

    @Test
    public void contextLoads() {

    }

//    @Test
//    public void test1() throws IOException {
//        String filePath = "C:\\Users\\Administrator\\Desktop\\test.xlsx";
//        //判断是否为excel类型文件
//        if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")) {
//            System.out.println("文件不是excel类型");
//        }
//
//        FileInputStream fis = null;
//        Workbook wookbook = null;
//        Sheet sheet = null;
//        try {
//            //获取一个绝对地址的流
//            fis = new FileInputStream(filePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            //2003版本的excel，用.xls结尾
//            wookbook = new HSSFWorkbook(fis);//得到工作簿
//
//        } catch (Exception ex) {
//            //ex.printStackTrace();
//            try {
//                //2007版本的excel，用.xlsx结尾
//                fis = new FileInputStream(filePath);
//                wookbook = new XSSFWorkbook(fis);//得到工作簿
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//
//
//        Map<String, PictureData> maplist = null;
//
//        sheet = wookbook.getSheetAt(0);
//        // 判断用07还是03的方法获取图片
//        if (filePath.endsWith(".xls")) {
//            maplist = FileUtil.getPictures1((HSSFSheet) sheet);
//        } else if (filePath.endsWith(".xlsx")) {
//            maplist = FileUtil.getPictures2((XSSFSheet) sheet);
//        }
//        try {
//            LinkedList<String> filenames=FileUtil.printImg(maplist);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        //得到一个工作表
//
//
//        //获得表头
//        Row rowHead = sheet.getRow(0);
//
//        //判断表头是否正确
////		System.out.println(rowHead.getPhysicalNumberOfCells());
////		if(rowHead.getPhysicalNumberOfCells() != 5)
////		{
////			System.out.println("表头的数量不对!");
////		}
//
//        //获得数据的总行数
//        int totalRowNum = sheet.getLastRowNum();
//
//        //要获得属性
//        int studentid = 0;
//        String studentname = "";
//        String grade = "";
//        String classes = "";
//        String pic = "";
//        //获得所有数据
//        for (int i = 1; i <= totalRowNum; i++) {
//            //获得第i行对象
//            Row row = sheet.getRow(i);
//
//            //获得获得第i行第0列的 String类型对象
//            Cell cell = row.getCell((short) 0);
//            studentid = (int) cell.getNumericCellValue();
//
//            //获得一个数字类型的数据
//            //studentname = (int) cell.getNumericCellValue();
//            cell = row.getCell((short) 1);
//            studentname = cell.getStringCellValue().toString();
//
//            cell = row.getCell((short) 2);
//            grade = cell.getStringCellValue();
//
//
//            cell = row.getCell((short) 3);
//            classes = String.valueOf(cell.getNumericCellValue());
//
//            System.out.println("学号：" + studentid + ",姓名：" + studentname + ",年级：" + grade + ",班级：" + classes + ",证件照：" + pic);
//        }
//        for (Map.Entry<String, PictureData> entry : maplist.entrySet()) {
//
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//
//        }
//    }
//

}
