package com.example.demo.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限决策处理类
 *
 * 判断用户的角色，如果为空，则拒绝访问；
 *
 * 判断用户所有的角色中是否有一个包含在 访问路径允许的角色集合中；
 *
 * 如果有，则放开；否则拒绝访问；
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (authentication == null) {
            throw new AccessDeniedException("permission denied");
        }

        //当前用户拥有的角色集合
        List<String> roleCodes = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        //访问路径所需要的角色集合
        List<String> configRoleCodes = configAttributes.stream().map(ConfigAttribute::getAttribute).collect(Collectors.toList());
        for (String roleCode : roleCodes) {
            if (configRoleCodes.contains(roleCode)) {
                return;
            }
        }

        throw new AccessDeniedException("permission denied");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
