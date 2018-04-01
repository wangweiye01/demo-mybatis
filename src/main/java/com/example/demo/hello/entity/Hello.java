package com.example.demo.hello.entity;

import com.example.demo.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Hello extends BaseEntity {
    Integer age;
    String name;
}
