package priv.jesse.mall.utils;

import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author chen
 * @version 1.0
 * @date 2020/3/7 15:25
 */
public class DownloadUtils {
    public static void download(ByteArrayOutputStream byteArrayOutputStream,
                                HttpServletResponse response,  String returnName) throws Exception {
        response.setContentType("application/octet-stream;charset=utf-8");

        returnName =response.encodeURL(new String(returnName.getBytes(),"ISO8859-1"));
        response.setHeader("content-disposition","attachment;filename="+returnName);
        response.setContentLength(byteArrayOutputStream.size());
        //取得输出流
        ServletOutputStream outputStream =response.getOutputStream();
        //写到输出流
        byteArrayOutputStream.writeTo(outputStream);
        //关闭
        byteArrayOutputStream.close();

        //刷数据
        outputStream.flush();
        System.out.println("完成下载");
    }
}
