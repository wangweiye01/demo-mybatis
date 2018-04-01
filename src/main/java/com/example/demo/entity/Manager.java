package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "T_MANAGER")
@Entity
@Getter
@Setter
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" }) //使用hibernate时，查询数据库产生的实体类是个代理类，使用此注解生成的JSON不会产生多余字段
public class Manager implements Serializable {
    @Column(name = "ID")
    @GeneratedValue
    @Id
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(mappedBy = "manager", optional = true)
    @JsonBackReference
    private Department department;
}
