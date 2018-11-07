package com.example.demo.security.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "RESOURCE")
@Data
public class Resource {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "TYPE", length = 50)
    @NotNull
    private String type;

    @Column(name = "NAME", length = 255)
    @NotNull
    private String name;

    @Column(name = "URL", length = 500)
    @NotNull
    private String url;

    @Column(name = "PARENT_ID")
    @NotNull
    private Long parentId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "RESOURCE_API",
            joinColumns = {@JoinColumn(name = "RESOURCE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "API_ID", referencedColumnName = "ID")})
    @JSONField(serialize = false)
    private List<Api> apis;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "RESOURCE_AUTHORITY",
            joinColumns = {@JoinColumn(name = "RESOURCE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    @JSONField(serialize = false)
    private Set<Authority> authorities;

    @Override
    public String toString() {
        return name;
    }
}
