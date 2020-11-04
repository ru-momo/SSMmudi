package com.suha.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseCode {

    static Map<String, Object> map = new HashMap<>();
    /**
     * 返回状态码
     * @return
     */
    public static Map<String, Object> ok(){
        map.put("code", 200) ;
        map.put("data", "ok");
        map.put("time", System.currentTimeMillis());
        return map ;
    }

    /**
     * 返回状态码
     * @param data
     * @return
     */
    public static Map<String, Object> ok(Object data){
        map.put("code", 200) ;
        map.put("data", data);
        map.put("time", System.currentTimeMillis());
        return map ;
    }

    /**
     * 返回错误信息
     * @param data
     * @return
     */
    public static Map<String, Object> error(Object data){
        map.put("code", 500) ;
        map.put("data", data);
        map.put("time", System.currentTimeMillis());
        return map ;
    }

    public static Map<String, Object> paramIsNull(){
        map.put("code", 500) ;
        map.put("data", "参数不完整");
        map.put("time", System.currentTimeMillis());
        return map ;
    }


    public static Map<String, Object> idIsNotNull(){
        map.put("code", 500) ;
        map.put("data", "编号不能为空");
        map.put("time", System.currentTimeMillis());
        return map ;
    }

    public static Map<String, Object> idIsNotExists(Integer id){
        map.put("code", 500) ;
        map.put("data", id + "不存在");
        map.put("time", System.currentTimeMillis());
        return map ;
    }
}
