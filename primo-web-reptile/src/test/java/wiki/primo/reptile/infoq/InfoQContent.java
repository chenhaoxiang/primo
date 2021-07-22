/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.infoq;

import cn.hutool.http.HttpUtil;
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
 * @className InfoQContent.java
 * @date 2021-07-22 8:11 下午
 * @description
 */
public class InfoQContent {

    /**
     * 获取InfoQ文章数据
     * @param args
     */
    public static void main(String[] args) {
        String url = "https://xie.infoq.cn/article/2914e131d2b4bb34dee013455";

        System.getProperties().setProperty("webdriver.chrome.driver", "/Users/chenhx/Desktop/config/chromedriver");
        System.getProperties().setProperty("selenuim_config", "/Users/chenhx/Desktop/config/config.ini");

        ChromeOptions chromeOptions = ChromeOptionsUtils.getChrome();
        //InfoQ有检测 user-agent，必须有这句
        chromeOptions.addArguments("user-agent=\"Mozilla/5.0 (iPod; U; CPU iPhone OS 2_1 like Mac OS X; ja-jp) AppleWebKit/525.18.1 (KHTML, like Gecko) Version/3.1.1 Mobile/5F137 Safari/525.20\"");
        ChromeDriver webDriver = new ChromeDriver(chromeOptions);

        webDriver.get(url);
        //  设置隐性等待时间 - 需要配合使用的
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {
            //等待一定时间再进行加载
            WebDriverWait wait = new WebDriverWait(webDriver, 20);
            //等待元素的加载。最大的时间30秒
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(InfoQBlog.CONTENT)));
        } catch (org.openqa.selenium.TimeoutException e) {
            //实际项目中，请打印日志。务必不要使用：e.printStackTrace()
            e.printStackTrace();
        }
        String pageSource = webDriver.getPageSource();

        JXDocument jxDocument = JXDocument.create(pageSource);
        String title = jxDocument.selNOne(InfoQBlog.TITLE).asString();
        String author = jxDocument.selNOne(InfoQBlog.AUTHOR).asString();
        String content = jxDocument.selNOne(InfoQBlog.CONTENT).asString();
        List<String> tags = jxDocument.selN(InfoQBlog.TAG).stream().map(JXNode::asString).collect(Collectors.toList());
        System.out.println("==title===" + title);
        System.out.println("==author===" + author);
        System.out.println("==tags==="+ JSON.toJSONString(tags));
        System.out.println("==content==="+ content);
    }

}
