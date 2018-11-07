package com.example.demo;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import static junit.framework.TestCase.assertTrue;

public class AntPathMatcherTest {
    @Test
    public void antPathMatcherTest() {
        PathMatcher matcher = new AntPathMatcher();

        String requestPath = "/hellos/1/read";
        String patternPath = "/hellos/{id}/read";

        boolean result = matcher.match(patternPath, requestPath);
        assertTrue(result);
    }
}
