/*
 * wiki.primo
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.core.util;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author chenhx
 * @version 0.0.1
 * @className FileUtils.java
 * @date 2020-05-08 16:21
 * @description 文件工具类
 */
public class FileUtils {

    private static Log log = new SystemStreamLog();

    /**
     * 下载jar包文件到项目中
     *
     * @param fileName 文件名称
     * @param savePath 保存路径
     * @throws IOException IO异常
     */
    public static void downLoadFile(String fileName, String savePath,String jarFileName) throws IOException {
        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            if (!saveDir.mkdirs()) {
                log.info(savePath + " 文件路径不存在，AGT进行创建失败，请检查是否有权限");
                return;
            }
        }
        File file = new File(saveDir + File.separator + fileName);
        if (file.exists()) {
            log.info("配置文件已经存在，路径：" + savePath + ",文件名：" + fileName);
            return;
        }

        //创建目录
        ClassPathResource classPathResource = new ClassPathResource(jarFileName);

        //得到输入流
        InputStream inputStream = classPathResource.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        fos.close();
        inputStream.close();
        log.info("下载配置文件成功，路径：" + savePath + ",文件名：" + fileName);
    }

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
