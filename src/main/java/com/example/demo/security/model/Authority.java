package com.example.demo.security.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "AUTHORITY")
@Data
public class Authority {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "NAME", length = 50)
    @NotNull
    private String name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<Resource> resources;

    @Override
    public String toString() {
        return name;
    }
}