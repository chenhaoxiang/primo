/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.juejin;

/**
 * @author chenhx
 * @version 0.0.1
 * @className JueJinBlog.java
 * @date 2021-07-22 8:11 下午
 * @description
 */
public class JueJinBlog {

    /**
     * 标题
     */
    public static final String TITLE = "//article[@class='article']/h1[@class='article-title']/text()";

    /**
     * 文章内容
     */
    public static final String CONTENT ="//article[@class='article']/div[@class='article-content']/div[@class='markdown-body']";

    /**
     * 标签
     */
    public static final String TAG = "//div[@class='tag-list-box']/div[@class='tag-list']/div[@class='tag-list-container']/a[@class='item']/div[@class='tag-title']/text()";

    /**
     * 作者
     */
    public static final String AUTHOR="//div[@class='author-info-block']/div[@class='author-info-box']/div[@class='author-name']/a/span[@class='name']/text()";


    /**
     * 标题
     */
    public static final String TITLES = "//div[contains(@class,'title-row')]/a[contains(@class,'title')]/span/text()";

    /**
     * 链接
     */
    public static final String URLS = "//div[contains(@class,'title-row')]/a[contains(@class,'title')]/@href";

}
