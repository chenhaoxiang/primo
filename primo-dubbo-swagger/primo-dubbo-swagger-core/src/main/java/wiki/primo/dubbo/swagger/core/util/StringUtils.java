/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串判断类
 *
 * @author chenhx
 * @version StringUtils.java, v 0.1 2019-08-19 10:13 chenhx
 */
public class StringUtils {
    /**
     * 空字符串。
     */
    public static final String EMPTY_STRING = "";
    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);
    private final static String AS = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static int getStringLen(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        return str.length();
    }

    public static String convertEncode(String strIn, String encoding, String targetEncoding) {
        String strOut = strIn;
        if (strIn == null)
            return strOut;

        try {
            if (encoding != null && targetEncoding != null) {
                strOut = new String(strIn.getBytes(encoding), targetEncoding);
            } else if (encoding != null) {
                strOut = new String(strIn.getBytes(encoding));
            } else if (targetEncoding != null) {
                strOut = new String(strIn.getBytes(), targetEncoding);
            } else {
                return strOut;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("Unsupported Encoding: " + encoding);
        }
        return strOut;
    }

    /**
     * 截取str中以startStr开头，endStr结束的字符串
     *
     * @param str
     * @param startStr
     * @param endStr
     * @return 返回以以startStr开头，以endStr结束的字符串，如果startStr不存在，则有str为起始；如果endStr不存在，则以字符串结束为终结
     */
    public static String extractString(String str, String startStr, String endStr) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }

        if (startStr == null) {
            startStr = "";
        }

        int startIdx = 0;

        startIdx = str.indexOf(startStr);

        if (startIdx == -1) {
            startIdx = 0;
        } else {
            startIdx += startStr.length();
        }

        int endIdx = str.length();
        if (endStr != null) {
            endIdx = str.indexOf(endStr, startIdx);
            if (endIdx == -1) {
                endIdx = str.length();
            }
        }

        return str.substring(startIdx, endIdx);
    }

    /**
     * 替换指定的子串，替换所有出现的子串。
     *
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
     * ，则返回原字符串。
     * </p>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    public static String replaceSpace(String text) {
        return replace(text, " ", "");
    }

    /**
     * 替换指定的子串，替换指定的次数。
     *
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
     * ，则返回原字符串。
     * </p>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     * @param max  maximum number of values to replace, or <code>-1</code> if no
     *             maximum
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with, int max) {
        if ((text == null) || (repl == null) || (with == null) || (repl.length() == 0) || (max == 0)) {
            return text;
        }

        StringBuffer buf = new StringBuffer(text.length());
        int start = 0;
        int end = 0;

        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            if (--max == 0) {
                break;
            }
        }

        if (start == 0) {
            return text;
        }

        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * 除去字符串头尾部的空白，如果字符串是<code>null</code>，依然返回<code>null</code>。
     *
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     * </p>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String trim(String str) {
        return trim(str, null, 0);
    }

    /**
     * 除去字符串头尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String trim(String str, String stripChars) {
        return trim(str, stripChars, 0);
    }

    /**
     * 除去字符串头部的空白，如果字符串是<code>null</code>，则返回<code>null</code>。
     *
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     * </p>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
     * <code>null</code>
     */
    public static String trimStart(String str) {
        return trim(str, null, -1);
    }

    /**
     * 除去字符串头部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String trimStart(String str, String stripChars) {
        return trim(str, stripChars, -1);
    }

    /**
     * 除去字符串尾部的空白，如果字符串是<code>null</code>，则返回<code>null</code>。
     * </p>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
     * <code>null</code>
     */
    public static String trimEnd(String str) {
        return trim(str, null, 1);
    }

    /**
     * 除去字符串尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String trimEnd(String str, String stripChars) {
        return trim(str, stripChars, 1);
    }

    /**
     * 除去字符串头尾部的空白，如果结果字符串是空字符串<code>""</code>，则返回<code>null</code>。
     *
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     * </p>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
     * <code>null</code>
     */
    public static String trimToNull(String str) {
        return trimToNull(str, null);
    }

    /**
     * 除去字符串头尾部的空白，如果结果字符串是空字符串<code>""</code>，则返回<code>null</code>。
     *
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
     * <code>null</code>
     */
    public static String trimToNull(String str, String stripChars) {
        String result = trim(str, stripChars);

        if ((result == null) || (result.length() == 0)) {
            return null;
        }

        return result;
    }

    /**
     * 除去字符串头尾部的空白，如果字符串是<code>null</code>，则返回空字符串<code>""</code>。
     *
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     * </p>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
     * <code>null</code>
     */
    public static String trimToEmpty(String str) {
        return trimToEmpty(str, null);
    }

    /**
     * 除去字符串头尾部的空白，如果字符串是<code>null</code>，则返回空字符串<code>""</code>。
     *
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     * </p>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
     * <code>null</code>
     */
    public static String trimToEmpty(String str, String stripChars) {
        String result = trim(str, stripChars);

        if (result == null) {
            return EMPTY_STRING;
        }

        return result;
    }

    /**
     * 除去字符串头尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     * @param mode       <code>-1</code>表示trimStart，<code>0</code>表示trim全部，
     *                   <code>1</code>表示trimEnd
     * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    private static String trim(String str, String stripChars, int mode) {
        if (str == null) {
            return null;
        }

        int length = str.length();
        int start = 0;
        int end = length;

        // 扫描字符串头部
        if (mode <= 0) {
            if (stripChars == null) {
                while ((start < end) && (Character.isWhitespace(str.charAt(start)))) {
                    start++;
                }
            } else if (stripChars.length() == 0) {
                return str;
            } else {
                while ((start < end) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                    start++;
                }
            }
        }

        // 扫描字符串尾部
        if (mode >= 0) {
            if (stripChars == null) {
                while ((start < end) && (Character.isWhitespace(str.charAt(end - 1)))) {
                    end--;
                }
            } else if (stripChars.length() == 0) {
                return str;
            } else {
                while ((start < end) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                    end--;
                }
            }
        }

        if ((start > 0) || (end < length)) {
            return str.substring(start, end);
        }

        return str;
    }

    /**
     * Check if a String has length.
     * <p>
     *
     * @param str the String to check, may be <code>null</code>
     * @return <code>true</code> if the String is not null and has length
     */
    public static boolean hasLength(String str) {
        return (str != null && str.length() > 0);
    }

    /**
     * Check if a String has text. More specifically, returns <code>true</code>
     * if the string not <code>null<code>, it's <code>length is > 0</code>, and it has at least one
     * non-whitespace character.
     * <p>
     *
     * @param str the String to check, may be <code>null</code>
     * @return <code>true</code> if the String is not null, length > 0, and not
     * whitespace only
     * @see java.lang.Character#isWhitespace
     */
    public static boolean hasText(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return false;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a String has text. More specifically, returns <code>true</code>
     * if the string not <code>null<code>, it's <code>length is > 0</code>, and it has at least one
     * non-whitespace character.
     * <p>
     *
     * @param str the String to check, may be <code>null</code>
     * @return <code>true</code> if the String is not null, length > 0, and not
     * whitespace only
     * @see java.lang.Character#isWhitespace
     */
    public static boolean hasText(StringBuffer str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return false;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Convert a CSV list into an array of Strings.
     *
     * @param str CSV list
     * @return an array of Strings, or the empty array if s is null
     */
    public static String[] commaDelimitedListToStringArray(String str) {
        return delimitedListToStringArray(str, ",");
    }

    /**
     * Take a String which is a delimited list and convert it to a String array.
     * <p>
     * A single delimiter can consists of more than one character: It will still
     * be considered as single delimiter string, rather than as bunch of
     * potential delimiter characters - in contrast to
     * <code>tokenizeToStringArray</code>.
     *
     * @param str       the input String
     * @param delimiter the delimiter between elements (this is a single
     *                  delimiter, rather than a bunch individual delimiter
     *                  characters)
     * @return an array of the tokens in the list
     * @see #tokenizeToStringArray
     */
    public static String[] delimitedListToStringArray(String str, String delimiter) {
        if (str == null) {
            return new String[0];
        }
        if (delimiter == null) {
            return new String[]{str};
        }

        List<String> result = new ArrayList<String>();
        if ("".equals(delimiter)) {
            for (int i = 0; i < str.length(); i++) {
                result.add(str.substring(i, i + 1));
            }
        } else {
            int pos = 0;
            int delPos = 0;
            while ((delPos = str.indexOf(delimiter, pos)) != -1) {
                result.add(str.substring(pos, delPos));
                pos = delPos + delimiter.length();
            }
            if (str.length() > 0 && pos <= str.length()) {
                // Add rest of String, but not in case of empty input.
                result.add(str.substring(pos));
            }
        }
        return toStringArray(result);
    }

    /**
     * Copy the given Collection into a String array. The Collection must
     * contain String elements only.
     *
     * @param collection the Collection to copy
     * @return the String array (<code>null</code> if the Collection was
     * <code>null</code> as well)
     */
    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null) {
            return null;
        }
        return (String[]) collection.toArray(new String[collection.size()]);
    }

    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return
     * @author Taylor
     */
    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    /**
     * 其中任意一个是否为空
     * 实现有问题,已经弃用,请使用isAnyEmptyNew
     *
     * @param s
     * @return
     */
    @Deprecated
    public static boolean isAnyEmpty(String... s) {
        for (String str : s) {
            if (isEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 其中任意一个是否为空
     *
     * @param s
     * @return
     */
    public static boolean isAnyEmptyNew(String... s) {
        if (s == null) {
            return true;
        }
        for (String str : s) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否全部为空
     *
     * @param s
     * @return
     */
    public static boolean isAllEmpty(String... s) {
        if (s == null) {
            return true;
        }
        for (String str : s) {
            if (isNotEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为不为空
     *
     * @param s
     * @return
     * @author Taylor
     */
    public static boolean isNotEmpty(String s) {
        return s != null && s.trim().length() != 0;
    }

    /**
     * 反转一个字符串
     *
     * @param s
     * @return
     */
    public static String reverse(String s) {
        if (StringUtils.isEmpty(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    /**
     * 转义所有"<"和">"符号
     *
     * @param str
     * @return
     */
    public static String escapeHtml(String str) {
        if (str == null) {
            return "";
        }
        str = str.replace(">", "&gt;");
        str = str.replace("<", "&lt;");
        return str;
    }

    /**
     * 将字符串截取一定的长度,末尾用...补全
     *
     * @param s
     * @param byteLength
     * @return
     */
    public static String limitString(String s, int byteLength) {
        return limitString(s, byteLength, "...");
    }

    /**
     * 将字符串截取一定的长度,末尾用omit补全
     *
     * @param s
     * @param byteLength
     * @param omit
     * @return
     * @author Taylor
     */
    public static String limitString(String s, int byteLength, String omit) {
        if (s == null) {
            return null;
        }
        if (byteLength <= 0) {
            return "";
        }
        if (s.getBytes().length <= byteLength) {
            return s;
        }
        String r = "";
        for (int i = 0; i < s.length(); i++) {
            String tmp = s.substring(i, i + 1);
            if (r.getBytes().length + tmp.getBytes().length > byteLength) {
                break;
            }
            r += tmp;
        }
        if (omit != null) {
            r += omit;
        }
        return r;
    }

    public static String getPatternMatchStr(String src, String pattern) {
        if (src == null) {
            return null;
        }
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher matcher = p.matcher(src);
            if (matcher.find()) {
                return matcher.group();
            }
        } catch (Exception e) {
            logger.warn("", e);
        }
        return null;

    }

    /**
     * 获取固定长度的随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char c = AS.charAt((int) (Math.random() * (AS.length())));
            sb.append(c);
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 将字符串补齐至一定长度,不足部分用omit补齐
     *
     * @param s
     * @param byteLength
     * @param omit
     * @return
     * @author Taylor
     */
    public static String toSize(String s, int byteLength, String omit) {
        if (byteLength <= 0) {
            return "";
        }
        if (s == null) {
            s = "";
        }
        if (omit == null) {
            omit = "...";
        }
        int omitSize = omit.getBytes().length;

        if (s.getBytes().length > byteLength) {
            if (byteLength < omitSize) {
                s = limitString(s, byteLength);
            } else {
                s = limitString(s, byteLength - omitSize, omit);
            }
        }
        while (s.getBytes().length + omitSize <= byteLength) {
            s += omit;
        }
        return s;
    }

    /**
     * 将字符串的首字母大写.
     *
     * @param str
     * @return
     */
    public static String capitalizeFirstLetter(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        String firstLetter = str.substring(0, 1);
        String result = firstLetter.toUpperCase() + str.substring(1);
        return result;
    }

    /**
     * 将字符串的首字母小写.
     *
     * @param str
     * @return
     */
    public static String lowerFirstLetter(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        String firstLetter = str.substring(0, 1);
        String result = firstLetter.toLowerCase() + str.substring(1);
        return result;
    }

    /**
     * 判断某字符串是否都在ascii的范围内
     *
     * @param str
     * @return
     */
    public static boolean isAsciiStr(String str) {
        if (str == null) {
            return false;
        }

        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (str.charAt(i) > 255) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断某字符串是否数值型
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }

        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 将字符串转换成小写。
     *
     * <p>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     * </p>
     *
     * @param str 要转换的字符串
     * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String toLowerCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toLowerCase();
    }

    /**
     * 取得指定子串在字符串中出现的次数。
     *
     * <p>
     * 如果字符串为<code>null</code>或空，则返回<code>0</code>。
     *
     * </p>
     *
     * @param str    要扫描的字符串
     * @param subStr 子字符串
     * @return 子串在字符串中出现的次数，如果字符串为<code>null</code>或空，则返回<code>0</code>
     */
    public static int countMatches(String str, String subStr) {
        if ((str == null) || (str.length() == 0) || (subStr == null) || (subStr.length() == 0)) {
            return 0;
        }

        int count = 0;
        int index = 0;

        while ((index = str.indexOf(subStr, index)) != -1) {
            count++;
            index += subStr.length();
        }

        return count;
    }

    /**
     * 从inputReader中读出一行
     *
     * @param inputReader
     * @return
     * @throws IOException
     */
    public static String readLine(InputStreamReader inputReader) throws IOException {
        StringBuffer sb = new StringBuffer();

        char c;
        int n;
        int num = 0;
        while ((n = inputReader.read()) > 0) {
            num++;
            c = (char) n;
            if (c == '\n' || c == '\r') {
                break;
            }
            sb.append(c);
        }
        if (num == 0) {
            return null;
        }

        return sb.toString();
    }

    /**
     * 获取资源
     *
     * @param uri
     * @return
     */
    public static String getResource(String uri) {
        if (isEmpty(uri)) {
            return "";
        }
        if (uri.contains(".")) {
            return uri.split("\\.")[0];
        } else {
            return uri;
        }
    }

    /**
     * 获取文件后缀名
     *
     * @param uri
     * @return
     */
    public static String getExtension(String uri) {
        if (isEmpty(uri)) {
            return "";
        }
        if (uri.contains(".")) {
            String[] strs = uri.split("\\.");
            if (strs.length > 1) {
                String extension = strs[1];
                if (isNotEmpty(extension)) {
                    return extension;
                }
            }
        }
        return "";
    }

    /**
     * 获取文件的后缀
     *
     * @param file
     * @return
     */
    public static String getFileExtName(String file) {
        if (isEmpty(file)) {
            return null;
        }

        int idx = file.lastIndexOf(".");
        if (idx < 0) {
            return file;
        }

        return file.substring(idx + 1);
    }

    /**
     * 格式化string
     *
     * @param str
     * @param obj
     * @return
     */
    public static String formatString(String str, Object... obj) {
        return String.format(str, obj);
    }

    /**
     * 取字符串后面几个len长度
     *
     * @param str
     * @param len
     * @return
     */
    public static String lastSubStr(String str, int len) {
        if (str == null) {
            return null;
        }

        if (str.length() <= len) {
            return str;
        }

        return str.substring(str.length() - len, str.length());

    }

    /**
     * 过滤掉ascii的字符串，主要用于取出中文文字
     */
    public static String filterAsciiStr(String str) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c > 255) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 取出ascii的文本
     */
    public static String getAsciiStr(String str) {
        if (str == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < 255) {
                sb.append(c);
            }
        }

        return trim(sb.toString());
    }


    /**
     * 去除无用的字符串，只保留字母和数字
     *
     * @return
     */
    public static String getLetterOrDigit(String str) {
        if (str == null) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static boolean isPhone(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        try {
            Pattern p = null;
            Matcher m = null;
            boolean b = false;
            p = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$"); // 验证手机号
            m = p.matcher(phone);
            b = m.matches();
            return b;
        } catch (Exception e) {
            return false;
        }
    }


    public static String escapeQueryChars(String s) {
        if (isEmpty(s)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // These characters are part of the query syntax and must be escaped
            if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':'
                    || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
                    || c == '*' || c == '?' || c == '|' || c == '&' || c == ';' || c == '/'
                    || Character.isWhitespace(c)) {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }


    public static String genArrayInSql(String... strings) {
        if (strings == null || strings.length == 0) return null;
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(",'").append(string).append("'");
        }
        return sb.substring(1);
    }


    /**
     * 过滤ascii码为32以下的控制码
     *
     * @param str
     * @return
     */
    public static String trimCtrlChars(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }

        boolean containCtrlChar = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < 32 && c != '\n' && c != '\r') {
                containCtrlChar = true;
            }
        }

        if (containCtrlChar) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c < 32 && c != 10 && c != 13) {
                    continue;
                }
                sb.append(c);
            }

            str = sb.toString();
        }

        return str;

    }

    /**
     * @return
     */
    public static String convertToUnderlineStr(String str) {
        if (str == null) {
            return null;
        }

        boolean lastUpper = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0 && Character.isUpperCase(c) && !lastUpper) {
                sb.append('_');
            }
            sb.append(Character.toLowerCase(c));

            lastUpper = Character.isUpperCase(c);
        }
        return sb.toString();
    }

    public static String convertToHumpString(String str) {
        if (str == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean nextUpper = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                if (nextUpper) {
                    c = Character.toUpperCase(c);
                    nextUpper = false;
                }
                sb.append(c);
            } else if (sb.length() > 0) {
                nextUpper = true;
            }
        }
        return sb.toString();
    }
}
