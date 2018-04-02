package com.example.demo.common;


import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

public class JsonResult {
    // 成功状态
    public static final Integer SUCCESS_CODE = 200;
    // 参数错误
    public static final Integer PARAMETER_ERROR = 501;
    // 错误
    public static final Integer ERROR_CODE = 500;

    public static final <T> JSONObject success(Object data, PageInfo<T> pageInfo) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("data", data);
        jsonObject.put("code", SUCCESS_CODE);

        if (pageInfo != null) {
            Map<String, Object> page = new HashMap<>();
            page.put("pageNum", pageInfo.getPageNum());
            page.put("pageSize", pageInfo.getPageSize());
            page.put("total", pageInfo.getTotal());
            page.put("isFirstPage", pageInfo.isIsFirstPage());
            page.put("isLastPage", pageInfo.isIsLastPage());
            jsonObject.put("page", page);
        }

        return jsonObject;
    }

    public static final JSONObject fail(String message, Integer code) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        jsonObject.put("code", code);

        return jsonObject;
    }
}

