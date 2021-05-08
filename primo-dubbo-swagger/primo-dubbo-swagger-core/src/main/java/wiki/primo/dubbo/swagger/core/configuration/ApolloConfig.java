/*
 * primo.wiki
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.core.configuration;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.build.ApolloInjector;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.config.NamespaceHandler;
import com.ctrip.framework.apollo.util.ConfigUtil;
import com.ctrip.framework.foundation.internals.io.BOMInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 依赖
 *
 * @author chenhx
 * @version ApolloConfig.java, v 0.1 2019-08-19 17:21 chenhx
 */
public class ApolloConfig {

    private static final Logger logger = LoggerFactory.getLogger(ApolloConfig.class);

    private static final List<Config> privateConfigs = new ArrayList<Config>();

    /**
     * 本地开发模式 properties
     */
    private static Properties props = new Properties();

    static {
        try {
            init();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private ApolloConfig() {
    }

    public static String getConfig(String key) {
        if (isLocalEnv()) {
            return getLocalConfig(key);
        }
        String result = null;
        for (Config config : privateConfigs) {
            result = config.getProperty(key, null);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    private static ConfigChangeListener changeListener() {
        ConfigChangeListener changeListener = new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                logger.info("Changes for namespace {}", changeEvent.getNamespace());
                for (String key : changeEvent.changedKeys()) {
                    ConfigChange change = changeEvent.getChange(key);
                    logger.info("Change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
                            change.getPropertyName(), change.getOldValue(), change.getNewValue(),
                            change.getChangeType());
                }
            }
        };
        return changeListener;
    }

    public static void getAllConfigs() {
//        ConfigChangeListener changeListener = changeListener();
        List<String> namespaces = NamespaceHandler.getNamespaces();
        logger.info("namespacesis => {}", namespaces);
        for (String nameSpace : namespaces) {
            Config config = ConfigService.getConfig(nameSpace);
            // TODO 暂时去掉动态监听
//            config.addChangeListener(changeListener);
            privateConfigs.add(config);
        }
    }

    private static void init() {
        if (isLocalEnv()) {
            initLocalProps(ApolloInjector.getInstance(ConfigUtil.class).getAppId());
            return;
        }
        getAllConfigs();
    }

    private static String getLocalConfig(String key) {
        return props.getProperty(key);
    }

    private static boolean isLocalEnv() {
        return "LOCAL".equals(ApolloInjector.getInstance(ConfigUtil.class).getApolloEnv().name());
    }

    private static void initLocalProps(String appName) {
        try {
            StringBuffer sb = new StringBuffer("/opt/returnData/");
            String path = sb.append(appName).append("/config-cache").toString();
            File file = new File(path);
            if (file.exists() && file.isDirectory()) {
                File[] files = file.listFiles();
                if (files == null) {
                    return;
                }
                for (int i = 0; i < files.length; i++) {
                    readLocalProps(files[i]);
                }
            }
        } catch (Throwable ex) {
            logger.error("Initialize DefaultEnv failed.", ex);
        }
    }

    private static void readLocalProps(File file) throws FileNotFoundException {
        if (file.exists() && file.canRead()) {
            logger.info("Local loading {}", file.getAbsolutePath());
            FileInputStream fis = new FileInputStream(file);
            initialize(fis);
            return;
        }
    }

    private static void initialize(InputStream in) {
        try {
            if (in != null) {
                try {
                    props.load(new InputStreamReader(new BOMInputStream(in), StandardCharsets.UTF_8));
                } finally {
                    in.close();
                }
            }
        } catch (Throwable ex) {
            logger.error("Initialize DefaultEnv failed.", ex);
        }
    }

}
