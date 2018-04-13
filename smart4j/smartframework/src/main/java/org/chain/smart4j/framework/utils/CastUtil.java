package org.chain.smart4j.framework.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17032651
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public final class CastUtil {
    /*
    * 转为String型
    * */
    public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }

    /*
    * 转为String型（提供默认值）
    * */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /*
    * 转为double型
    * */
    public static double castDouble(Object obj) {
        return castDouble(obj, 0.0);
    }

    /*
    * 转为double型（提供默认值）
    * */
    public static double castDouble(Object obj, Double defaultValue) {
        double doubleValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    defaultValue = defaultValue;
                }

            }
        }
        return doubleValue;
    }

    /*
    * 转为long型
    * */
    public static long castLong(Object obj) {
        return castLong(obj, 0);
    }

    /*
     * 转为long型（提供默认值）
     * */
    public static long castLong(Object obj, long defaultValue) {
        long longValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }

            }
        }
        return longValue;
    }

    /*
    * 转为int型
    * */
    public static int castInt(Object obj) {
        return castInt(obj, 0);
    }

    /*
    * 转为int型(提供默值)
    * */
    public static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }

            }
        }
        return intValue;
    }

    /*
    * 转为boolean型
    * */
    public static boolean castBoolean(Object obj) {
        return castBoolean(obj, false);
    }

    /*
    * 转为boolean型(提供默认值)
    * */
    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (obj != null) {
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }

}
