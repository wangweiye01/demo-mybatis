package com.example.demo.hello.web;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.common.JsonResult;
import com.example.demo.hello.entity.Hello;
import com.example.demo.hello.service.HelloService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hellos")
public class HelloController {
    @Autowired
    private HelloService helloService;


    @ApiOperation(value = "获得所有", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
            @ApiImplicitParam(name = "page", value = "第几页", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页几条", dataType = "int", required = false, paramType = "query"),
    })
    @RequestMapping(method = RequestMethod.GET)
    public JSONObject index(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "5") Integer size) {
        Page pageinfo = new Page(page, size);

        List<Hello> hellos = helloService.findPage(pageinfo);

        return JsonResult.success(hellos, pageinfo);
    }

    @ApiOperation(value = "获得一个", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "path", name = "id", value = "HelloId", required = true, dataType = "long")
    })

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JSONObject show(@PathVariable Long id) {
        Hello hello = helloService.findOne(id);

        return JsonResult.success(hello);
    }
}
