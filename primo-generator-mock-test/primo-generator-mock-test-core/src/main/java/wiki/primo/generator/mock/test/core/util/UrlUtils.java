/*
 * wiki.primo
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.core.util;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络url处理工具类
 *
 * @author chenhx
 * @version UrlUtil.java, v 0.1 2019-06-11 18:37 chenhx
 */
public class UrlUtils {
    private static Log log = new SystemStreamLog();

    /**
     * 从网络Url中下载文件
     *
     * @param urlStr   地址
     * @param fileName 文件名称
     * @param savePath 保存路径
     * @throws IOException IO异常
     */
    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
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
            log.info("配置文件已经存在不进行下载，URL:" + urlStr + ",路径：" + savePath + ",文件名：" + fileName);
            return;
        }

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为30秒
        conn.setConnectTimeout(30 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = FileUtils.readInputStream(inputStream);

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        fos.close();
        inputStream.close();
        log.info("下载配置文件成功，URL:" + url + ",路径：" + savePath + ",文件名：" + fileName);
    }

}
