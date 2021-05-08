/*
 * primo.wiki
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.core.extension;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import wiki.primo.dubbo.swagger.api.ApiModelProperty;
import wiki.primo.dubbo.swagger.core.util.EnumUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.AllowableRangeValues;
import springfox.documentation.service.AllowableValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spring.web.DescriptionResolver;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.util.StringUtils.hasText;
import static springfox.documentation.schema.Annotations.findPropertyAnnotation;

/**
 * @author chenhx
 * @version ApiModelPropertyPropertyBuilder.java, v 0.1 2019-11-12 17:54 chenhx
 */
@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
@Slf4j
public class ApiModelPropertyPropertyBuilderExt implements ModelPropertyBuilderPlugin {
    private static final Pattern RANGE_PATTERN = Pattern.compile("range([\\[(])(.*),(.*)([])])$");
    private final DescriptionResolver descriptions;

    @Autowired
    public ApiModelPropertyPropertyBuilderExt(DescriptionResolver descriptions) {
        this.descriptions = descriptions;
    }

    private static Function<ApiModelProperty, AllowableValues> toAllowableValues() {
        return new Function<ApiModelProperty, AllowableValues>() {
            @Override
            public AllowableValues apply(ApiModelProperty annotation) {
                //生成枚举拼装
                String desc = EnumUtils.getAllowableValue(annotation.enumClass(), annotation.enumAttributeName());
                if (StringUtils.isEmpty(desc)) {
                    return allowableValueFromString(annotation.allowableValues());
                }
                return allowableValueFromString(desc);
            }
        };
    }

    static Function<ApiModelProperty, String> toDescription(
            final DescriptionResolver descriptions) {

        return new Function<ApiModelProperty, String>() {
            @Override
            public String apply(ApiModelProperty annotation) {
                String desc = EnumUtils.getEnumClassDesc(annotation.enumClass());
                String description = "";
                if (!Strings.isNullOrEmpty(annotation.value())) {
                    description = annotation.value() + desc;
                } else if (!Strings.isNullOrEmpty(annotation.notes())) {
                    description = annotation.notes() + desc;
                }
                return descriptions.resolve(description);
            }
        };
    }


    public static AllowableValues allowableValueFromString(String allowableValueString) {
        AllowableValues allowableValues = new AllowableListValues(Lists.<String>newArrayList(), "LIST");
        String trimmed = allowableValueString.trim();
        Matcher matcher = RANGE_PATTERN.matcher(trimmed.replaceAll(" ", ""));
        if (matcher.matches()) {
            if (matcher.groupCount() != 4) {
                log.warn("Unable to parse range specified {} correctly", trimmed);
            } else {
                allowableValues = new AllowableRangeValues(
                        matcher.group(2).contains("infinity") ? null : matcher.group(2),
                        matcher.group(1).equals("("),
                        matcher.group(3).contains("infinity") ? null : matcher.group(3),
                        matcher.group(4).equals(")"));
            }
        } else if (trimmed.contains(",")) {
            Iterable<String> split = Splitter.on(',').trimResults().omitEmptyStrings().split(trimmed);
            allowableValues = new AllowableListValues(newArrayList(split), "LIST");
        } else if (hasText(trimmed)) {
            List<String> singleVal = Collections.singletonList(trimmed);
            allowableValues = new AllowableListValues(singleVal, "LIST");
        }
        return allowableValues;
    }

    static Function<ApiModelProperty, Boolean> toIsRequired() {
        return new Function<ApiModelProperty, Boolean>() {
            @Override
            public Boolean apply(ApiModelProperty annotation) {
                return annotation.required();
            }
        };
    }

    static Function<ApiModelProperty, Integer> toPosition() {
        return new Function<ApiModelProperty, Integer>() {
            @Override
            public Integer apply(ApiModelProperty annotation) {
                return annotation.position();
            }
        };
    }

    static Function<ApiModelProperty, Boolean> toIsReadOnly() {
        return new Function<ApiModelProperty, Boolean>() {
            @Override
            public Boolean apply(ApiModelProperty annotation) {
                return annotation.readOnly();
            }
        };
    }

    @Override
    public void apply(ModelPropertyContext context) {
        Optional<ApiModelProperty> annotation = Optional.absent();

        if (context.getAnnotatedElement().isPresent()) {
            annotation = annotation.or(findApiModePropertyAnnotation(context.getAnnotatedElement().get()));
        }
        if (context.getBeanPropertyDefinition().isPresent()) {
            annotation = annotation.or(findPropertyAnnotation(
                    context.getBeanPropertyDefinition().get(),
                    ApiModelProperty.class));
        }
        if (annotation.isPresent()) {
            context.getBuilder()
                    .allowableValues(annotation.transform(toAllowableValues()).orNull())
                    .required(annotation.transform(toIsRequired()).or(false))
                    .readOnly(annotation.transform(toIsReadOnly()).or(false))
                    .description(annotation.transform(toDescription(descriptions)).orNull())
                    .isHidden(annotation.transform(toHidden()).or(false))
                    .type(annotation.transform(toType(context.getResolver())).orNull())
                    .position(annotation.transform(toPosition()).or(0))
                    .example(annotation.transform(toExample()).orNull());
        }
    }

    static Function<ApiModelProperty, ResolvedType> toType(final TypeResolver resolver) {
        return new Function<ApiModelProperty, ResolvedType>() {
            @Override
            public ResolvedType apply(ApiModelProperty annotation) {
                try {
                    return resolver.resolve(Class.forName(annotation.dataType()));
                } catch (ClassNotFoundException e) {
                    return resolver.resolve(Object.class);
                }
            }
        };
    }

    public static Optional<ApiModelProperty> findApiModePropertyAnnotation(AnnotatedElement annotated) {
        Optional<ApiModelProperty> annotation = Optional.absent();

        if (annotated instanceof Method) {
            // If the annotated element is a method we can use this information to check superclasses as well
            annotation = Optional.fromNullable(AnnotationUtils.findAnnotation(((Method) annotated), ApiModelProperty.class));
        }

        return annotation.or(Optional.fromNullable(AnnotationUtils.getAnnotation(annotated, ApiModelProperty.class)));
    }

    static Function<ApiModelProperty, Boolean> toHidden() {
        return new Function<ApiModelProperty, Boolean>() {
            @Override
            public Boolean apply(ApiModelProperty annotation) {
                return annotation.hidden();
            }
        };
    }

    static Function<ApiModelProperty, String> toExample() {
        return new Function<ApiModelProperty, String>() {
            @Override
            public String apply(ApiModelProperty annotation) {
                String example = "";
                if (!Strings.isNullOrEmpty(annotation.example())) {
                    example = annotation.example();
                }
                return example;
            }
        };
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

}
