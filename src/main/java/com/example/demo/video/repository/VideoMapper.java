package com.example.demo.video.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.video.entity.Video;

public interface VideoMapper extends BaseMapper<Video> {
    Video findOne(Integer id);
}
