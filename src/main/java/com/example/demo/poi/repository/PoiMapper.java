package com.example.demo.poi.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.poi.entity.Poi;

import java.util.List;

public interface PoiMapper extends BaseMapper<Poi> {
    Poi findByRegisterCode(String registerCode);

    Poi findByRegisterCodeFromNew(String registerCode);

    List<Poi> findAllOrigin();

    Integer insertNewPoi(Poi poi);
}
