package com.example.demo.security;

import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

public class CustomPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
    @Override
    public <T extends FilterSecurityInterceptor> T postProcess(T fsi) {
        fsi.setSecurityMetadataSource(new CustomFilterSecurityMetadataSource()); //1.路径（资源）拦截处理

        fsi.setAccessDecisionManager(new CustomAccessDecisionManager()); //2.权限决策处理类

        return fsi;
    }
}
