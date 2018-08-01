package com.example.demo.poi.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.poi.entity.OriginPoi;

public interface PoiMapper extends BaseMapper<OriginPoi> {
    OriginPoi findByRegisterCode(String registerCode);
}
