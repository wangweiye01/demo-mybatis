package com.example.demo.hello.repository;

import com.example.demo.common.utils.CrudDao;
import com.example.demo.hello.entity.Hello;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloDao extends CrudDao<Hello> {

}
