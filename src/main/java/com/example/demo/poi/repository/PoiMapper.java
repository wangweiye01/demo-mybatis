package com.example.demo.poi.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.poi.entity.Poi;

public interface PoiMapper extends BaseMapper<Poi> {
    Poi findByRegisterCode(String registerCode);

    Integer insertNewPoi(Poi poi);
}
