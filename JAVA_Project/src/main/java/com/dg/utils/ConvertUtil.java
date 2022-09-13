package com.dg.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ty
 * @apiNote
 */
public class ConvertUtil {
    private ConvertUtil() {
    }

    public static boolean isEmpty(String str) {
        return StringUtils.isBlank(str);
    }

    public static String camelToUnderline(String fieldName) {
        char[] chars = fieldName.toCharArray();
        StringBuilder builder = new StringBuilder();
        char[] var3 = chars;
        int var4 = chars.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            char aChar = var3[var5];
            if (Character.isUpperCase(aChar)) {
                builder.append('_').append(Character.toLowerCase(aChar));
            } else {
                builder.append(aChar);
            }
        }

        return builder.toString();
    }

    public static String underlineToCamel(String column) {
        column = column.toLowerCase();
        char[] chars = column.toCharArray();
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < chars.length; ++i) {
            if ('_' == chars[i]) {
                ++i;
                builder.append(Character.toUpperCase(chars[i]));
            } else {
                builder.append(chars[i]);
            }
        }

        return builder.toString();
    }

    public static String underline2Camel(String line, boolean... firstIsUpperCase) {
        String str = "";
        if (StringUtils.isBlank(line)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder();
            if (!line.contains("_") && firstIsUpperCase.length == 0) {
                sb.append(line.substring(0, 1).toLowerCase()).append(line.substring(1));
                str = sb.toString();
            } else if (!line.contains("_") && firstIsUpperCase.length != 0) {
                if (!firstIsUpperCase[0]) {
                    sb.append(line.substring(0, 1).toLowerCase()).append(line.substring(1));
                    str = sb.toString();
                } else {
                    sb.append(line.substring(0, 1).toUpperCase()).append(line.substring(1));
                    str = sb.toString();
                }
            } else {
                String[] strArr;
                String[] var5;
                int var6;
                int var7;
                String s;
                if (line.contains("_") && firstIsUpperCase.length == 0) {
                    strArr = line.split("_");
                    var5 = strArr;
                    var6 = strArr.length;

                    for(var7 = 0; var7 < var6; ++var7) {
                        s = var5[var7];
                        sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
                    }

                    str = sb.toString();
                    str = str.substring(0, 1).toLowerCase() + str.substring(1);
                } else if (line.contains("_") && firstIsUpperCase.length != 0) {
                    strArr = line.split("_");
                    var5 = strArr;
                    var6 = strArr.length;

                    for(var7 = 0; var7 < var6; ++var7) {
                        s = var5[var7];
                        sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
                    }

                    if (!firstIsUpperCase[0]) {
                        str = sb.toString();
                        str = str.substring(0, 1).toLowerCase() + str.substring(1);
                    } else {
                        str = sb.toString();
                    }
                }
            }

            return str;
        }
    }
}
