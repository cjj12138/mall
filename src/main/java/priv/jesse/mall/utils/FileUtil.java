package priv.jesse.mall.utils;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FileUtil {

    /**
     * 保存上传的文件
     *
     * @param file
     * @return 文件下载的url
     * @throws Exception
     */
    public static String saveFile(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return "";
        }
        File target = new File("file");
        if (!target.isDirectory()) {
            target.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(file.getBytes());
        String fileName = (Helper.bytesToHex(md.digest(), 0, md.digest().length - 1)) + "." + getPostfix(originalFilename);
        File file1 = new File(target.getPath() + "/" + fileName);
        Files.write(Paths.get(file1.toURI()), file.getBytes(), StandardOpenOption.CREATE_NEW);
        return "/mall/admin/product/img/" + fileName;
    }

    /**
     * 获得文件的后缀名
     *
     * @param fileName
     * @return
     */
    public static String getPostfix(String fileName) {
        if (fileName == null || "".equals(fileName.trim())) {
            return "";
        }
        if (fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        }
        return "";
    }

    /**
     * 获取图片和位置 (xls)
     *
     * @param sheet
     * @return
     * @throws IOException
     */
    public static Map<String, PictureData> getPictures1(HSSFSheet sheet) throws IOException {
        Map<String, PictureData> map = new HashMap<String, PictureData>();
        List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
        for (HSSFShape shape : list) {
            if (shape instanceof HSSFPicture) {
                HSSFPicture picture = (HSSFPicture) shape;
                HSSFClientAnchor cAnchor = (HSSFClientAnchor) picture.getAnchor();
                PictureData pdata = picture.getPictureData();
                // 行号-列号
                String key = cAnchor.getRow1() + "-" + cAnchor.getCol1();
                map.put(key, pdata);
            }
        }
        return map;
    }

    /**
     * 获取图片和位置 (xlsx)
     *
     * @param sheet
     * @return
     * @throws IOException
     */
    public static Map<String, PictureData> getPictures2(XSSFSheet sheet) throws IOException {
        Map<String, PictureData> map = new HashMap<String, PictureData>();
        List<POIXMLDocumentPart> list = sheet.getRelations();
        for (POIXMLDocumentPart part : list) {
            if (part instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) part;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture picture = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = picture.getPreferredSize();
                    CTMarker marker = anchor.getFrom();
                    String key = marker.getRow() + "-" + marker.getCol();
                    map.put(key, picture.getPictureData());
                }
            }
        }
        return map;
    }

    /**
     * 图片写出
     *
     * @param sheetList
     * @return 图片名字的链表
     * @throws IOException
     */
    public static LinkedList<String> printImg(Map<String, PictureData> sheetList) throws IOException {
        LinkedList<String> fileNames = new LinkedList<>();
        //for (Map<String, PictureData> map : sheetList) {
        Object key[] = sheetList.keySet().toArray();
        for (int i = 0; i < sheetList.size(); i++) {
            // 获取图片流
            PictureData pic = sheetList.get(key[i]);
            // 获取图片索引
            String picName = UUIDUtils.getUUID();

            //System.out.println(picName);
            // 获取图片格式
            String ext = pic.suggestFileExtension();
            fileNames.add(picName + "." + ext);
            byte[] data = pic.getData();
            //得到当前项目路径
            String url = System.getProperty("user.dir");
            System.out.println(url + "\\file");
            //图片保存路径
            FileOutputStream out = new FileOutputStream(url + "\\file\\" + picName + "." + ext);

            out.write(data);
            out.close();
        }
        // }
        System.out.println("保存完毕");
        return fileNames;
    }

    /**
     * 迭代删除文件夹
     *
     * @param dirPath 文件夹路径
     */

    public static void deleteDir(String dirPath) {
        // 读取
        File file = new File(dirPath);
        // 判断是否是文件夹
        if (file.isFile()) {
            // 删除
            file.delete();
        } else {
            // 获取文件
            File[] files = file.listFiles();
            if (files == null) {
                // 删除
                file.delete();
            } else {
                // 循环
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i].getAbsolutePath());
                }
                // 删除
                file.delete();
            }
        }
    }
}
