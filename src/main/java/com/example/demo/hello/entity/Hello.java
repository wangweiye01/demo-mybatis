package com.example.demo.hello.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("hello")
public class Hello {
    @TableId
    private Long id;

    private String name;

    private String name1;
}
