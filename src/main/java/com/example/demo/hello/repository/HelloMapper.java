package com.example.demo.hello.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.demo.hello.entity.Hello;

import java.util.List;

public interface HelloMapper extends BaseMapper<Hello> {
    List<Hello> findPage(Pagination pagination);

    Hello findOne(Long id);
}
