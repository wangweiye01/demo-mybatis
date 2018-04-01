package com.example.demo.hello.service;


import com.example.demo.hello.entity.Hello;
import com.example.demo.hello.repository.HelloDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloService {
    @Value("${page.begin}")
    private Integer defaultPage;

    @Value("${page.size}")
    private Integer defaultSize;

    @Autowired
    private HelloDao helloDao;

    public PageInfo<Hello> test(Integer page, Integer size) {
        page = page == null ? defaultPage : page;
        size = size == null ? defaultSize : size;
        PageHelper.startPage(page, size);

        List<Hello> hellos = helloDao.findList(new Hello());
        return new PageInfo<Hello>(hellos);
    }
}
