package com.example.demo.video.web;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.common.JsonResult;
import com.example.demo.video.entity.Video;
import com.example.demo.video.repository.VideoMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private VideoMapper videoMapper;


    @ApiOperation(value = "获得所有视频", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
    })

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject index() {
        List<Video> videos = videoMapper.selectList(new EntityWrapper().eq("title", "标题"));

        return JsonResult.success(videos, null);
    }

    @ApiOperation(value = "获得一个视频", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "path", name = "id", value = "视频Id", required = true, dataType = "int")
    })

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JSONObject show(@PathVariable Integer id) {
        Video video = videoMapper.findOne(id);

        return JsonResult.success(video, null);
    }
}