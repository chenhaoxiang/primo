/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.csdn;

import cn.hutool.http.HttpUtil;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenhx
 * @version 0.0.1
 * @className CsdnBlog.java
 * @date 2021-05-25 5:11 下午
 * @description
 */
public class CsdnBlog {

    /**
     * 标题
     */
    public static final String TITLE = "//div[@class='article-header']/div[@class='article-title-box']/h1/text()";

    /**
     * 文章内容
     */
    public static final String CONTENT ="//div[@id='content_views']";

    /**
     * 标签
     */
    public static final String TAG = "//div[@class='article-header']/div[@class='article-info-box']/div[@class='blog-tags-box']/div[contains(@class,'tags-box')]/a[@class='tag-link']/text()";

    /**
     * 作者
     */
    public static final String AUTHOR="//div[@class='article-header']/div[@class='article-info-box']/div[@class='article-bar-top']/div[@class='bar-content']/a[contains(@class,'follow-nickName')]/text()";

    /**
     * 获取CSDN文章数据
     * @param args
     */
    public static void main(String[] args) {
        String url = "https://blog.csdn.net/qq_26525215/article/details/111500185";
        //超时时间要记得设置
        String htmlStr = HttpUtil.get(url,30*1000);
        System.out.println(htmlStr);
        JXDocument jxDocument = JXDocument.createByUrl(url);
        String title = jxDocument.selNOne(TITLE).asString();
        String author = jxDocument.selNOne(AUTHOR).asString();
        String content = jxDocument.selNOne(CONTENT).asString();
        List<String> tags = jxDocument.selN(TAG).stream().map(JXNode::asString).collect(Collectors.toList());
        System.out.println("=====");
    }

}
