package com.example.demo.hello.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("video")
public class Video {
    @TableId
    private String id;

    private String title;
}
