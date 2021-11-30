package wiki.primo.generator.mybatis.plus.config.rules;

import wiki.primo.generator.mybatis.plus.config.constant.ConstVal;
import org.apache.commons.lang.StringUtils;

/**
 * 从数据库表到文件的命名策略
 *
 * @author chenhx
 * @since 2020/8/30
 */
public enum NamingStrategy {
    /**
     * 不做任何改变，原样输出
     */
    nochange,
    /**
     * 下划线转驼峰命名
     */
    underline_to_camel,
    /**
     * 仅去掉前缀
     */
    remove_prefix,
    /**
     * 去掉前缀并且转驼峰
     */
    remove_prefix_and_camel;

    public static String underlineToCamel(String name) {
        // 快速检查
        if (StringUtils.isEmpty(name)) {
            // 没必要转换
            return "";
        }
        StringBuilder result = new StringBuilder();
        // 用下划线将原始字符串分割
        String[] camels = name.toLowerCase().split(ConstVal.UNDERLINE);
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (StringUtils.isEmpty(camel)) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel);
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(NamingStrategy.capitalFirst(camel));
            }
        }
        return result.toString();
    }

    /**
     * 去掉下划线前缀
     *
     * @param name 名称
     * @return 去除前缀的名称
     */
    public static String removePrefix(String name) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        int idx = name.indexOf(ConstVal.UNDERLINE);
        if (idx == -1) {
            return name;
        }
        return name.substring(idx + 1);
    }

    /**
     * 去掉指定的前缀
     *
     * @param name 名字
     * @param prefix 前缀
     * @return 字符串
     */
    public static String removePrefix(String name, String prefix) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        if (StringUtils.isEmpty(prefix)) {
            return name;
        }
        int idx = name.indexOf(ConstVal.UNDERLINE);
        // 判断是否有匹配的前缀，然后截取前缀
        if (name.toLowerCase().matches("^" + prefix.toLowerCase() + ".*")) {
            idx = prefix.length() - 1;
        }
        if (idx == -1) {
            return name;
        }
        return name.substring(idx + 1);
    }

    /**
     * 去掉下划线前缀且将后半部分转成驼峰格式
     *
     * @param name 名称
     * @param tablePrefix 前缀
     * @return 字符串
     */
    public static String removePrefixAndCamel(String name, String tablePrefix) {
        return underlineToCamel(removePrefix(name, tablePrefix));
    }

    /**
     * 实体首字母大写
     *
     * @param name 待转换的字符串
     * @return 转换后的字符串
     */
    public static String capitalFirst(String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        //return name.substring(0, 1).toUpperCase() + name.substring(1);
        char[] array = name.toCharArray();
        //只有小写字母才进行转换
        if(NamingStrategy.isLowerCase(array[0])) {
            array[0] -= 32;
        }
        return String.valueOf(array);
    }

    /**
     * 是否是大写
     * @param c
     * @return
     */
    public static boolean isUpperCase(char c) {
        return c >=65 && c <= 90;
    }

    /**
     * 是否是小写
     * @param c
     * @return
     */
    public static boolean isLowerCase(char c) {
        return c >=97 && c <= 122;
    }

}
