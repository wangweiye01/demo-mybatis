package com.example.demo;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class AntPathMatcherTest {
    @Test
    public void antPathMatcherTest() {
        PathMatcher matcher = new AntPathMatcher();

        String requestPath1 = "/hellos/1/read";
        String patternPath1 = "/hellos/{id}/read";

        String requestPath2 = "/hellos/a/b/c/d";
        String patternPath2 = "/hellos/**";

        String requestPath3 = "/hellos/a/b/c";
        String patternPath3 = "/hellos/*";

        String requestPath4 = "/a/b/hellos/c";
        String patternPath4 = "/**/hellos/*";

        boolean result = matcher.match(patternPath1, requestPath1);
        assertTrue(result);

        result = matcher.match(patternPath2, requestPath2);
        assertTrue(result);

        result = matcher.match(patternPath3, requestPath3);
        assertFalse(result);

        result = matcher.match(patternPath4, requestPath4);
        assertTrue(result);
    }
}
