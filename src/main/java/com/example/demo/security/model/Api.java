package com.example.demo.security.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "api")
@Data
public class Api {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "METHOD", length = 10)
    @NotNull
    private String method;

    @Column(name = "URI", length = 255)
    @NotNull
    private String uri;

    @ManyToMany(mappedBy = "apis", fetch = FetchType.EAGER)
    private List<Resource> resources;

    @Override
    public String toString() {
        return "uri";
    }
}
