package com.example.demo.poi.entity;

import lombok.Data;

@Data
public class Poi {
    private Integer id;
    private String file;
    private String registerCode;
    // 安装地址
    private String installAddress;
    // 维保单位
    private String serviceUnit;
    // 维保负责人
    private String principal;
    // 维保负责人手机
    private String serviceMobile;
    // 使用单位
    private String useUnit;
}
