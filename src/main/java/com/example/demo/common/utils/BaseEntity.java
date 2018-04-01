package com.example.demo.common.utils;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BaseEntity {
    protected Integer id;
    protected String createdAt;
    @JsonIgnore
    protected String deletedAt;
    @JsonIgnore
    protected Integer isDelete;
    public static final Integer DELETED = 1;
    public static final Integer NO_DELETED = 0;

    public void preInsert() {
        this.createdAt = "";
    }

    public void preDelete() {
        this.deletedAt = "";
    }
}
