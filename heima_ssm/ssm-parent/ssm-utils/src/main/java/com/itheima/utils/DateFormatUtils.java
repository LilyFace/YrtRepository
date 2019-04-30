package com.itheima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: YangRunTao
 * @Description: 日期格式转换工具类
 * @Date: 2019/04/25 11:30
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public class DateFormatUtils {

    /**
     * @param dateStr
     * @description: 将字符串转为日期类型
     * @return: java.util.Date
     * @author: YangRunTao
     * @date: 2019/04/25 15:14
     * @throws:
     **/
    public static Date string2Date(String dateStr, String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        return sdf.parse(dateStr);
    }

    /**
     * @param date
     * @param patt
     * @description: 将日期转为字符串
     * @return: java.lang.String
     * @author: YangRunTao
     * @date: 2019/04/25 15:48
     * @throws:
     **/
    public static String date2String(Date date, String patt) {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        return sdf.format(date);
    }
}
