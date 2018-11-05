package com.example.demo.security.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "api")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
