package com.example.demo.common.utils;


import com.alibaba.fastjson.JSONObject;

public class JsonResult {
    public static final JSONObject success(Object data) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("data", data);
        jsonObject.put("code", 200);

        return jsonObject;
    }

    public static final JSONObject fail(String message, Integer code) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        jsonObject.put("code", code);

        return jsonObject;
    }
}
