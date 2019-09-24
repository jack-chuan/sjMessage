package cn.com.sparknet.sjMessage.utils;

import java.util.HashMap;

/**
 * @program: JdbcTypeConvertUtils
 * @description: convert dataType to jdbcType（常用）
 * @author: Leo Lee
 * @create: 2019-03-27 14:49
 **/
public class JdbcTypeConvertUtils {

    private static HashMap<String, String> jdbcTypeMap;

    /**
    * @Description:  jdbcTypeMap{"dataType": "jdbcType"}
    */
    static {
        jdbcTypeMap = new HashMap<String, String>();
        jdbcTypeMap.put("INT", "INTEGER");
        jdbcTypeMap.put("INTEGER", "INTEGER");
        jdbcTypeMap.put("NUMBER", "DECIMAL");
        jdbcTypeMap.put("REAL", "REAL");
        jdbcTypeMap.put("DOUBLE", "DOUBLE");
        jdbcTypeMap.put("REAL", "REAL");
        jdbcTypeMap.put("CHAR", "CHAR");
        jdbcTypeMap.put("VARCHAR", "VARCHAR");
        jdbcTypeMap.put("VARCHAR2", "VARCHAR");
        jdbcTypeMap.put("BLOB", "BINARY");
        jdbcTypeMap.put("DATE", "DATE");
        jdbcTypeMap.put("DATETIME", "TIMESTAMP");
        jdbcTypeMap.put("TIMESTAMP", "TIMESTAMP");
    }

    public static HashMap<String, String> getJdbcTypeMap() {
        return jdbcTypeMap;
    }
}
