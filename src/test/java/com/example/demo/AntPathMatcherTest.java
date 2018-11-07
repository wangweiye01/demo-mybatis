package com.example.demo;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import static junit.framework.TestCase.assertTrue;

public class AntPathMatcherTest {
    @Test
    public void antPathMatcherTest() {
        PathMatcher matcher = new AntPathMatcher();

        String requestPath = "/hellos/1/read";//请求路径
        String patternPath = "/hellos/{id}/read";//路径匹配模式

        // 不完整路径uri方式路径匹配
        // String requestPath="/app/pub/login.do";//请求路径
        // String patternPath="/**/login.do";//路径匹配模式
        // 模糊路径方式匹配
        // String requestPath="/app/pub/login.do";//请求路径
        // String patternPath="/**/*.do";//路径匹配模式
        // 包含模糊单字符路径匹配
        //String requestPath = "/app/pub/login.do";// 请求路径
        //String patternPath = "/**/lo?in.do";// 路径匹配模式
        boolean result = matcher.match(patternPath, requestPath);
        assertTrue(result);
    }
}
