package com.example.demo.common.utils;


import lombok.Data;

@Data
public class BaseEntity {
    protected Integer id;
    protected String createdAt;
    protected String updatedAt;
    protected Integer isDelete;
    public static final Integer DELETED = 1;
    public static final Integer NO_DELETED = 0;
}
