package com.example.demo.security.repository;

import com.example.demo.security.model.Api;
import com.example.demo.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiRepository extends JpaRepository<Api, Long> {
    Api findByUriAndMethod(String uri, String method);

    List<Api> findByUriLikeAndMethod(String uri, String method);
}
