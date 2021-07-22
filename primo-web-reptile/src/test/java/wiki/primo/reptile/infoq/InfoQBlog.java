/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.infoq;

/**
 * @author chenhx
 * @version 0.0.1
 * @className InfoQBlog.java
 * @date 2021-07-22 8:11 下午
 * @description
 */
public class InfoQBlog {

    /**
     * 标题
     */
    public static final String TITLE = "//div[contains(@class,'inner-content')]/div[contains(@class,'article-content')]/div[contains(@class,'main')]/h1[contains(@class,'article-title')]/text()";

    /**
     * 文章内容
     */
    public static final String CONTENT ="//div[contains(@class,'inner-content')]/div[contains(@class,'article-content')]/div[contains(@class,'main')]/div[contains(@class,'article-detaile')]/div[contains(@class,'article-preview-content')]/div[contains(@class,'article-preview')]/div[contains(@class,'ProseMirror')]";

    /**
     * 标签
     */
    public static final String TAG = "//div[contains(@class,'inner-content')]/div[contains(@class,'article-content')]/div[contains(@class,'main')]/div[contains(@class,'tag-list')]/a[contains(@class,'com-label-title')]/text()";

    /**
     * 作者
     */
    public static final String AUTHOR="//div[contains(@class,'inner-content')]/div[contains(@class,'article-content')]/div[contains(@class,'main')]/div[contains(@class,'author-information')]/div[contains(@class,'row')]/a[contains(@class,'com-author-name')]/text()";


    /**
     * 标题
     */
    public static final String TITLES = "//div[contains(@class,'component')]/div[contains(@class,'home-feed-recommend')]/div[contains(@class,'article-list')]/div[contains(@class,'list')]/div[contains(@class,'article-item')]/div[contains(@class,'item-main')]/div[contains(@class,'info')]/h4[contains(@class,'title')]/a[contains(@class,'com-article-title')]/text()";
    /**
     * 链接
     */
    public static final String URLS = "//div[contains(@class,'component')]/div[contains(@class,'home-feed-recommend')]/div[contains(@class,'article-list')]/div[contains(@class,'list')]/div[contains(@class,'article-item')]/div[contains(@class,'item-main')]/div[contains(@class,'info')]/h4[contains(@class,'title')]/a[contains(@class,'com-article-title')]/@href";

}
