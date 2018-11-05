package com.example.demo.security.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "RESOURCE")
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
    private List<Api> apis;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "RESOURCE_AUTHORITY",
            joinColumns = {@JoinColumn(name = "RESOURCE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private Set<Authority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Api> getApis() {
        return apis;
    }

    public void setApis(List<Api> apis) {
        this.apis = apis;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
