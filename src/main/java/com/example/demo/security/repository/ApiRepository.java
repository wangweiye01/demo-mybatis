package com.example.demo.security.repository;

import com.example.demo.security.model.Api;
import com.example.demo.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Api, Long> {
    Api findByUriAndMethod(String uri, String method);
}
