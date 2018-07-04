package com.example.demo.video.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.demo.video.entity.Video;

import java.util.List;

public interface VideoMapper extends BaseMapper<Video> {
    Video findOne(Integer id);

    List<Video> findPage(Pagination pagination);
}
