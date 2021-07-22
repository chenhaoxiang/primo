/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.csdn;

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
     * 标题
     */
    public static final String TITLES = "//div[@class='navList-box']/div/div/article[@class='blog-list-box']/a/div[@class='blog-list-box-top']/h4/text()";
    /**
     * 链接
     */
    public static final String URLS = "//div[@class='navList-box']/div/div/article[@class='blog-list-box']/a/@href";

}
