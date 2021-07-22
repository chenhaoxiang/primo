/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.csdn;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
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
public class CsdnContent {

    /**
     * 获取CSDN文章数据
     * @param args
     */
    public static void main(String[] args) {
        String url = "https://blog.csdn.net/qq_26525215/article/details/111500185";
        //超时时间要记得设置
        String htmlStr = HttpUtil.get(url,30*1000);
        System.out.println(htmlStr.length());
        JXDocument jxDocument = JXDocument.create(htmlStr);
        String title = jxDocument.selNOne(CsdnBlog.TITLE).asString();
        String author = jxDocument.selNOne(CsdnBlog.AUTHOR).asString();
        String content = jxDocument.selNOne(CsdnBlog.CONTENT).asString();
        List<String> tags = jxDocument.selN(CsdnBlog.TAG).stream().map(JXNode::asString).collect(Collectors.toList());
        System.out.println("==title===" + title);
        System.out.println("==author===" + author);
        System.out.println("==tags==="+ JSON.toJSONString(tags));
        System.out.println("==content==="+ content);
    }

}
