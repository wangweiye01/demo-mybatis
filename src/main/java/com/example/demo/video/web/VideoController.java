package com.example.demo.video.web;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.common.JsonResult;
import com.example.demo.video.entity.Video;
import com.example.demo.video.repository.VideoMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private VideoMapper videoMapper;


    @ApiOperation(value = "获得所有视频", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
            @ApiImplicitParam(name = "id", value = "视频Id", dataType = "int", required = false, paramType = "query"),
    })

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject index(@RequestParam(required = false) Integer id) {
        // List<Video> videos = videoMapper.selectList(new EntityWrapper().eq("title", "标题"));
        Video param = new Video();
        param.setId(id);

        List<Video> videos = videoMapper.selectPage(new Page<Video>(1, 2), new EntityWrapper<Video>(param));

        return JsonResult.success(videos);
    }

    @ApiOperation(value = "获得一个视频", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "path", name = "id", value = "视频Id", required = true, dataType = "int")
    })

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JSONObject show(@PathVariable Integer id) {
        Video video = videoMapper.findOne(id);

        return JsonResult.success(video);
    }
}