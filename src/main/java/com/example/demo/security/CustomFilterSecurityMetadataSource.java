package com.example.demo.security;

import com.example.demo.security.model.Api;
import com.example.demo.security.model.Authority;
import com.example.demo.security.model.Resource;
import com.example.demo.security.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 路径拦截处理类
 * <p>
 * 如果路径属于允许访问列表，则不做拦截，放开访问；
 * <p>
 * 否则，获得路径访问所需角色，并返回；如果没有找到该路径所需角色，则拒绝访问。
 */
@Component
public class CustomFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private ApiRepository apiRepository;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object; //当前请求对象

        if (isMatcherAllowedRequest(fi)) return null; //return null 表示允许访问，不做拦截

        List<ConfigAttribute> configAttributes = getMatcherConfigAttribute(fi.getRequestUrl(), fi.getRequest().getMethod()); // 获得访问当前路径所需要的角色

        return configAttributes.size() > 0 ? configAttributes : deniedRequest(); //返回当前路径所需角色，如果路径没有对应角色，则拒绝访问
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    /**
     * 获取当前路径以及请求方式获得所需要的角色
     *
     * @param url 当前路径
     * @return 所需角色集合
     */
    private List<ConfigAttribute> getMatcherConfigAttribute(String url, String method) {
        /*return roleResourceRepository.findByResource_ResUrl(url).stream()
                .map(roles -> new SecurityConfig(roles.getRole().getRoleCode()))
                .collect(Collectors.toList());*/

        Set<Authority> authorities = new HashSet<>();

        Api api = apiRepository.findByUriAndMethod(url, method);

        if (api == null) {
            return new ArrayList<>();
        }

        List<Resource> resources = api.getResources();

        resources.forEach(x -> {
            authorities.addAll(x.getAuthorities());
        });

        return authorities.stream().map(authority -> new SecurityConfig(authority.getName().toString())).collect(Collectors.toList());
    }

    /**
     * 判断当前请求是否在允许请求的范围内
     *
     * @param fi 当前请求
     * @return 是否在范围中
     */
    private boolean isMatcherAllowedRequest(FilterInvocation fi) {
        return allowedRequest().stream().map(AntPathRequestMatcher::new)
                .filter(requestMatcher -> requestMatcher.matches(fi.getHttpRequest()))
                .toArray().length > 0;
    }

    /**
     * @return 定义允许请求的列表
     */
    private List<String> allowedRequest() {
        return Arrays.asList("/auth/**", "/css/**", "/fonts/**", "/js/**", "/scss/**", "/img/**", "/html/**", "/", "/favicon.ico", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs");
    }

    /**
     * @return 默认拒绝访问配置
     */
    private List<ConfigAttribute> deniedRequest() {
        return Collections.singletonList(new SecurityConfig("ROLE_DENIED"));
    }
}
