package com.example.demo.common;


import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;

public class JsonResult {
    // 成功状态
    public static final Integer SUCCESS_CODE = 200;
    // 参数错误
    public static final Integer PARAMETER_ERROR = 501;
    // 其他错误
    public static final Integer ERROR_CODE = 500;

    public static final <T> JSONObject success(Object data, Page page) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("data", data);
        jsonObject.put("code", SUCCESS_CODE);

        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("current", page.getCurrent());
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("size", page.getSize());
        pageInfo.put("hasNext", page.hasNext());
        pageInfo.put("hasPrevious", page.hasPrevious());

        jsonObject.put("page", pageInfo);

        return jsonObject;
    }

    public static final <T> JSONObject success(Object data) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("data", data);
        jsonObject.put("code", SUCCESS_CODE);

        return jsonObject;
    }

    public static final JSONObject fail(String message, Integer code) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        jsonObject.put("code", code);

        return jsonObject;
    }
}

