package com.example.demo.hello.web;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.common.JsonResult;
import com.example.demo.hello.entity.Video;
import com.example.demo.hello.repository.VideoMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private VideoMapper videoMapper;


    @ApiOperation(value = "测试swagger2", notes = "接口具体描述")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
    })

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public JSONObject index(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        List<Video> videos = videoMapper.selectList(new EntityWrapper().eq("title", "标题"));

        return JsonResult.success(videos, null);
    }
}