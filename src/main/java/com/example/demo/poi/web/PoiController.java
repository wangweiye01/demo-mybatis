package com.example.demo.poi.web;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.JsonResult;
import com.example.demo.poi.entity.Poi;
import com.example.demo.poi.repository.PoiMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/poi")
@Log
public class PoiController {
    @Autowired
    private PoiMapper poiMapper;


    @ApiOperation(value = "将所有的原始数据excel导入到数据库", notes = "")

    @RequestMapping(value = "/origin/import", method = RequestMethod.GET)
    public JSONObject originImport() {

        ExcelReader reader = ExcelUtil.getReader(new File("/Users/wangweiye/Desktop/电梯资料_20180801.xls"));
        List<Map<String, Object>> readAll = reader.readAll();

        readAll.forEach(map -> {
            String registerCode = map.get("设备注册代码").toString();

            Poi poi = new Poi();
            poi.setRegisterCode(registerCode);
            poiMapper.insert(poi);

            System.out.println(registerCode);

            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return JsonResult.success("所有的原始数据导入到数据库成功");
    }

    @ApiOperation(value = "将所有的原始数据excel导入到数据库", notes = "")

    @RequestMapping(value = "/new/import", method = RequestMethod.GET)
    public JSONObject newImport() {
        File folder = new File("/Users/wangweiye");
        // 所有的excel列表
        ArrayList<File> fileList = new ArrayList<File>();

        if (folder.isDirectory()) {
            // 文件夹
            File[] files = folder.listFiles();

            for (File file : files) {
                String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);

                if (suffix.equalsIgnoreCase("xlsx")) {
                    fileList.add(file);
                }
            }
        }

        System.out.println("共有excel文件:" + fileList.size() + "个");

        fileList.forEach(x -> {
            ExcelReader reader = ExcelUtil.getReader(x);
            List<Map<String, Object>> readAll = reader.readAll();

            readAll.forEach(map -> {
                Poi poi = new Poi();
                poi.setRegisterCode(map.get("注册代码（不超过20位）").toString());

                System.out.println(poi.toString());

                try {
                    poiMapper.insertNewPoi(poi);
                } catch (Exception e) {
                    log.info("文件:" + x.getName() + "中" + poi.getRegisterCode() + "重复");
                    e.printStackTrace();
                }
            });
        });

        return JsonResult.success("新数据导入成功");
    }
}