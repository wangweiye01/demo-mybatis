package com.example.demo.hello.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.hello.entity.Hello;
import com.example.demo.hello.repository.HelloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloService {
    @Autowired
    private HelloMapper helloMapper;

    public List<Hello> findPage(Page page) {
        Hello param = new Hello();

        List<Hello> hellos = helloMapper.selectPage(page, new EntityWrapper<Hello>(param));

        return hellos;
    }

    public Hello findOne(Long id) {
        return helloMapper.selectById(id);
    }
}
