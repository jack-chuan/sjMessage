package cn.com.sparknet.sjMessage.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: sjMessage-spsvn
 * @description: 返回数据
 * @author: Leo Lee
 * @create: 2019-03-26 22:52
 **/
public class MapResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public MapResult(){
        put("code", 200);
        put("msg", "success");
    }

    public static MapResult error(){
        return error(500, "未知异常，请联系管理员");
    }

    public static MapResult error(String msg){
        return error(500, msg);
    }

    public static MapResult error(int code, String msg){
        MapResult mapResult = new MapResult();
        mapResult.put("code", code);
        mapResult.put("msg", msg);
        return mapResult;
    }

    public static MapResult success(String msg){
        MapResult mapResult = new MapResult();
        mapResult.put("msg", msg);
        return mapResult;
    }

    public static MapResult success(Map<String, Object> map){
        MapResult mapResult = new MapResult();
        mapResult.putAll(map);
        return mapResult;
    }

    public static MapResult success(){
        return new MapResult();
    }

    @Override
    public MapResult put(String key, Object value){
        super.put(key, value);
        return this;
    }
}
