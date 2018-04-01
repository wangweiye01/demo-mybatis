package com.example.demo.hello.web;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.utils.JsonResult;
import com.example.demo.hello.entity.Hello;
import com.example.demo.hello.service.HelloService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @ApiOperation(value = "测试swagger2", notes = "接口具体描述")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
            @ApiImplicitParam(name = "page", value = "第几页", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页多少个", dataType = "int", required = false, paramType = "query")
    })

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public JSONObject index(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        PageInfo<Hello> pageInfo = helloService.test(page, size);
        return JsonResult.success(pageInfo.getList(), pageInfo);
    }
}