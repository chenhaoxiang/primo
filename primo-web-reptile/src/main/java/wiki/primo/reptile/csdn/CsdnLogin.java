/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.csdn;

import com.alibaba.fastjson.JSON;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import wiki.primo.reptile.util.ChromeOptionsUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author chenhx
 * @version 0.0.1
 * @className CsdnBlog.java
 * @date 2021-05-25 5:11 下午
 * @description 登录
 */
public class CsdnLogin {

    /**
     * 标题
     */
    public static final String TITLE = "//div[@class='navList-box']/div/div/article[@class='blog-list-box']/a/div[@class='blog-list-box-top']/h4/text()";
    /**
     * 链接
     */
    public static final String URL = "//div[@class='navList-box']/div/div/article[@class='blog-list-box']/a/@href";

    /**
     * 登录
     * @param args
     */
    public static void main(String[] args) {
        String url = "https://chenhx.blog.csdn.net/?type=blog";

        System.getProperties().setProperty("webdriver.chrome.driver", "/Users/chenhx/Desktop/config/chromedriver");
        System.getProperties().setProperty("selenuim_config", "/Users/chenhx/Desktop/config/config.ini");

        ChromeOptions chromeOptions = ChromeOptionsUtils.getChrome();
        ChromeDriver webDriver = new ChromeDriver();


        webDriver.get(url);
        //  设置隐性等待时间 - 需要配合使用的
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {
            //等待一定时间再进行加载
            WebDriverWait wait = new WebDriverWait(webDriver, 20);
            //等待元素的加载。最大的时间30秒
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(URL.substring(0, URL.lastIndexOf("/")))));
        } catch (org.openqa.selenium.TimeoutException e) {
            //实际项目中，请打印日志。务必不要使用：e.printStackTrace()
            e.printStackTrace();
        }
        String pageSource = webDriver.getPageSource();
        JXDocument jxDocument = JXDocument.create(pageSource);
        List<String> titles = jxDocument.selN(TITLE).stream().map(JXNode::asString).collect(Collectors.toList());
        List<String> blogUrls = jxDocument.selN(URL).stream().map(JXNode::asString).collect(Collectors.toList());
        webDriver.close();
        webDriver.quit();
        System.out.println("titles:"+JSON.toJSONString(titles));
        System.out.println("blogUrls:"+JSON.toJSONString(blogUrls));
    }

}
