/**
 * copyfuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.reptile.util;

import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Collections;

/**
 * @author chenhx
 * @version ChromeOptionsUtil.java, v 0.1 2019-03-12 00:27 chenhx
 */
public class ChromeOptionsUtils {

    /**
     * 使用volatile解决重排问题
     */
    private static volatile ChromeOptions chrome;

    /**
     * 双重检验锁 单例 懒汉式
     * @return
     */
    public static ChromeOptions getChrome() {
        if (chrome == null) {
            synchronized (ChromeOptionsUtils.class) {
                if(chrome==null) {
                    //简单的使用方式
                    chrome = new ChromeOptions();
                    // 禁用沙箱
                    chrome.addArguments("--no-sandbox");
                    chrome.addArguments("--disable-dev-shm-usage");
                    // 设置为 headless 模式 （无头浏览器）
                    chrome.addArguments("--headless");
                    //开启开发者模式
                    chrome.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                }
            }
        }
        return chrome;
    }

}
