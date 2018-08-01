package com.example.demo.poi.web;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.common.JsonResult;
import com.example.demo.poi.entity.OriginPoi;
import com.example.demo.poi.repository.PoiMapper;
import com.example.demo.video.entity.Video;
import com.example.demo.video.repository.VideoMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/poi")
public class PoiController {
    @Autowired
    private PoiMapper poiMapper;


    @ApiOperation(value = "将所有的原始数据excel导入到数据库", notes = "")

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public JSONObject importToDB() {

        ExcelReader reader = ExcelUtil.getReader(new File("/Users/wangweiye/Desktop/电梯资料_20180801.xls"));
        List<Map<String, Object>> readAll = reader.readAll();

        readAll.forEach(map -> {
            String registerCode = map.get("设备注册代码").toString();

            OriginPoi originPoi = new OriginPoi();
            originPoi.setRegisterCode(registerCode);
            poiMapper.insert(originPoi);

            System.out.println(registerCode);

            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return JsonResult.success("所有的原始数据导入到数据库成功");
    }
}