/*
 * wiki.primo
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.util;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 文件工具类
 * @author chenhx
 * @version 0.0.1
 * @since  2020-05-08 16:21
 */
public class FileUtils {

    private static Log log = new SystemStreamLog();

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream 输入流
     * @return 字节数组
     * @throws IOException 流处理IO异常
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * 将文本文件中的内容读入到字符串
     * @param filePath 文件路径
     * @return 文件内容-字符串，utf-8编码
     * @throws IOException 异常
     */
    public static String readFileToString(String filePath) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStream is = new FileInputStream(filePath);
        //用来保存每行读取的内容
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        //读取第一行
        line = reader.readLine();
        //如果 line 为空说明读完了
        while (line != null) {
//            将读到的内容添加到 builder 中
            builder.append(line);
            builder.append("\n");
            //读取下一行
            line = reader.readLine();
        }
        reader.close();
        is.close();
        return builder.toString();
    }
    /**
     * 将 InputStream 中的内容读入到字符串
     * @param is InputStream
     * @throws IOException 异常
     */
    public static String readInputStreamToString(InputStream is) throws IOException {
        StringBuilder builder = new StringBuilder();
        //用来保存每行读取的内容
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        //读取第一行
        line = reader.readLine();
        //如果 line 为空说明读完了
        while (line != null) {
//            将读到的内容添加到 builder 中
            builder.append(line);
            builder.append("\n");
            //读取下一行
            line = reader.readLine();
        }
        reader.close();
        is.close();
        return builder.toString();
    }

}
