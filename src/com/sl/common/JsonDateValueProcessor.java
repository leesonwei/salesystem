package com.sl.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * JsonDateValueProcessor
 * JSON 日期格式处理（java转化为JSON）
 **/
public class JsonDateValueProcessor implements JsonValueProcessor {

    /**
     * 成员变量：初始化日期格式
     */
    private String datePattern = "yyyy-MM-dd";

    /**
     * JsonDateValueProcessor的无参构造方法
     */
    public JsonDateValueProcessor() {
        super();
    }

    /**
     * 有参构造方法
     * @param format
     */
    public JsonDateValueProcessor(String format) {
        super();
        this.datePattern = format;
    }

    /**
     * 处理数组值
     * @param value
     * @param jsonConfig
     * @return Object
     */
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    /**
     * 处理对象值
     * @param key
     * @param value
     * @param jsonConfig
     * @return Object
     */
    public Object processObjectValue(String key, Object value,
            JsonConfig jsonConfig) {
        return process(value);
    }

    /**
     * 处理对象
     * process
     * @param value
     * @return
     */
    private Object process(Object value) {
        try {
        	//判断参数是否为日期格式
            if (value instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern,
                        Locale.UK);
                return sdf.format((Date) value);
            }
            //三元运算，返回日期值
            return value == null ? "" : value.toString();
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 获取日期格式
     * @return the datePattern
     */
    public String getDatePattern() {
        return datePattern;
    }

    /**
     * 设置日期格式
     * @param pDatePattern the datePattern to set
     */
    public void setDatePattern(String pDatePattern) {
        datePattern = pDatePattern;
    }

}
