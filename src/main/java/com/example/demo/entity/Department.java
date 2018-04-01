package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "T_DEPARTMENT")
@Entity
@Getter
@Setter
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Department implements Serializable{
    @Column(name = "ID")
    @GeneratedValue
    @Id
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @JoinColumn(name = "MANAGER_ID")
    @OneToOne(cascade = CascadeType.ALL)
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

}
