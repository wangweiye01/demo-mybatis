package com.example.demo.poi.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
public class Poi {
    private Integer id;
    private String registerCode;
}
