package com.example.demo.video.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("video")
public class Video {
    @TableId
    private Integer id;

    private String title;

    private String url;
}
