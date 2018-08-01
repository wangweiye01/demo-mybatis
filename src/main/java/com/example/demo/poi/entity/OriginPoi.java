package com.example.demo.poi.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
public class OriginPoi {
    private Integer id;
    private String registerCode;
}
