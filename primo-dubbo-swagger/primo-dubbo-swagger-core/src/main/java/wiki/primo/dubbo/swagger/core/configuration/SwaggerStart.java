/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author chenhx
 * @version SwaggerStart.java, v 0.1 2019-08-19 17:14 chenhx
 */
@Configuration
public class SwaggerStart {
    private final static Logger logger = LoggerFactory.getLogger(SwaggerStart.class);

    public static class SwaggerCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Boolean enable = false;
            try {
                enable = PropertiesConfig.getValue(MegatronSwaggerConfig.MEGATRON_SWAGGER_ENABLE, Boolean.class);
            } catch (Exception e) {
                logger.error("您可能未进行配置megatron.swagger.enable的值,如果您不需要开启megatron-swagger，可以进行忽略，但是建议将该值设置为false，避免报错", e);
            }
            return enable;
        }
    }
}
