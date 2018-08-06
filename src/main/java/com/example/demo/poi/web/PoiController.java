package com.example.demo.poi.web;


import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.JsonResult;
import com.example.demo.log.entity.ErrorLog;
import com.example.demo.log.repository.ErrorLogMapper;
import com.example.demo.poi.entity.Poi;
import com.example.demo.poi.repository.PoiMapper;
import io.swagger.annotations.ApiOperation;
import javafx.print.PageOrientation;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/poi")
public class PoiController {
    private static final Logger logger = LoggerFactory.getLogger(PoiController.class);

    @Autowired
    private PoiMapper poiMapper;

    @Autowired
    private ErrorLogMapper errorLogMapper;

    @Value("${poi.origin-data-path}")
    private String originDataPath;

    @Value("${poi.new-data-folder}")
    private String newDataFolder;

    @ApiOperation(value = "将所有的原始数据excel导入到数据库", notes = "")

    @RequestMapping(value = "/origin/import", method = RequestMethod.GET)
    public JSONObject originImport() {

        ExcelReader reader = ExcelUtil.getReader(new File(originDataPath));
        List<Map<String, Object>> readAll = reader.readAll();

        readAll.forEach(map -> {
            String registerCode = map.get("设备注册代码").toString();

            Poi poi = new Poi();
            poi.setRegisterCode(registerCode);
            poiMapper.insert(poi);

            System.out.println(registerCode);

            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return JsonResult.success("所有的原始数据导入到数据库成功");
    }

    @ApiOperation(value = "将新数据excel导入到数据库", notes = "")
    @RequestMapping(value = "/new/import", method = RequestMethod.GET)
    public JSONObject newImport() {
        File folder = new File(newDataFolder);
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
                ErrorLog errorLog = new ErrorLog();

                poi.setRegisterCode(map.get("注册代码（不超过20位）") == null ? null : map.get("注册代码（不超过20位）").toString());
                poi.setInstallAddress(map.get("电梯安装地址") == null ? null : map.get("电梯安装地址").toString());
                poi.setServiceUnit(map.get("电梯维保单位") == null ? null : map.get("电梯维保单位").toString());
                poi.setPrincipal(map.get("维保负责人") == null ? null : map.get("维保负责人").toString());
                poi.setServiceMobile(map.get("维保负责人手机") == null ? null : map.get("维保负责人手机").toString());
                poi.setFile(x.getName());

                if (StrUtil.isEmpty(poi.getRegisterCode())) {
                    // 注册代码为空
                    errorLog.setInfo("文件:" + x.getName() + "中维保负责人手机为:" + poi.getServiceMobile() + "的注册代码为空");
                    errorLogMapper.insert(errorLog);
                }

                /*if (!PoiController.isNumeric(poi.getRegisterCode().trim())) {
                    // 注册代码中不全是数字
                    errorLog.setInfo("文件:" + x.getName() + "中注册代码为:" + poi.getRegisterCode() + "的注册代码不全部是数字");
                    errorLogMapper.insert(errorLog);
                }*/

                if (StrUtil.isEmpty(poi.getInstallAddress())) {
                    // 安装地址为空
                    errorLog.setInfo("文件:" + x.getName() + "中注册代码为:" + poi.getRegisterCode() + "的数据安装地址为空");
                    errorLogMapper.insert(errorLog);
                }

                if (StrUtil.isEmpty(poi.getServiceUnit())) {
                    // 维保单位为空
                    errorLog.setInfo("文件:" + x.getName() + "中注册代码为:" + poi.getRegisterCode() + "的数据维保单位为空");
                }
                if (StrUtil.isEmpty(poi.getPrincipal())) {
                    // 维保负责人为空
                    errorLog.setInfo("文件:" + x.getName() + "中注册代码为:" + poi.getRegisterCode() + "的维保负责人为空");
                    errorLogMapper.insert(errorLog);
                }
                if (StrUtil.isEmpty(poi.getServiceMobile())) {
                    // 维保负责人手机为空
                    errorLog.setInfo("文件:" + x.getName() + "中注册代码为:" + poi.getRegisterCode() + "的维保负责人手机为空");
                    errorLogMapper.insert(errorLog);
                }

                try {
                    poiMapper.insertNewPoi(poi);
                } catch (Exception e) {
                    if (StrUtil.isNotEmpty(poi.getRegisterCode())) {
                        // 查找重复的库中数据文件
                        Poi poi1 = poiMapper.findByRegisterCodeFromNew(poi.getRegisterCode());

                        errorLog.setInfo("文件:" + x.getName() + "中注册代码为:" + poi.getRegisterCode() + "的数据与" + poi1.getFile() + "文件中数据重复");
                        errorLogMapper.insert(errorLog);
                    }
                    e.printStackTrace();
                }
            });
        });

        return JsonResult.success("新数据导入成功");
    }


    @ApiOperation(value = "对比新旧数据", notes = "对比新导入的数据是否和原来数据有重复")
    @RequestMapping(value = "/compare", method = RequestMethod.GET)
    public JSONObject compare() {
        // 将原来数据插入到新数据表中，如果出现Duplicate entry错误，证明有重复，记录

        List<Poi> pois = poiMapper.findAllOrigin();

        ErrorLog errorLog = new ErrorLog();

        pois.forEach(x -> {
            Poi poi = new Poi();
            poi.setFile("Origin表");
            poi.setRegisterCode(x.getRegisterCode());

            try {
                poiMapper.insertNewPoi(poi);
            } catch (Exception e) {
                // 查询
                Poi poi1 = poiMapper.findByRegisterCodeFromNew(x.getRegisterCode());

                errorLog.setInfo("原有数据中注册代码为:" + x.getRegisterCode() + "的数据与文件:" + poi1.getFile() + "中数据重复");
                errorLogMapper.insert(errorLog);
                e.printStackTrace();
            }
        });

        return JsonResult.success("对比结束");
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}