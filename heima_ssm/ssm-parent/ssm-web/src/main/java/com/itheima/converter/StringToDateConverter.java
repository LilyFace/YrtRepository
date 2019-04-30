package com.itheima.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: YangRunTao
 * @Description: 字符串转时间转换器
 * @Date: 2019/04/25 16:21
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public class StringToDateConverter implements Converter<String, Date> {
    /**
     * @param source
     * @description: 字符串转时间
     * @return: java.util.Date
     * @author: YangRunTao
     * @date: 2019/04/16 21:08
     * @throws:
     **/
    @Override
    public Date convert(String source) {
        DateFormat dateFormat;
        Date parse = null;
        try {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            parse = dateFormat.parse(source);
        } catch (ParseException e) {
            dateFormat = new SimpleDateFormat("yyyy_MM_dd");
            try {
                parse = dateFormat.parse(source);
            } catch (ParseException e1) {
                System.out.println("日期格式错误");
                e1.printStackTrace();
            }
        }
        return parse;
    }

}
