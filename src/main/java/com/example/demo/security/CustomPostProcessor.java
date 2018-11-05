package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

@Component
public class CustomPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
    @Autowired
    private CustomFilterSecurityMetadataSource customFilterSecurityMetadataSource;

    @Autowired
    private CustomAccessDecisionManager customAccessDecisionManager;

    @Override
    public <T extends FilterSecurityInterceptor> T postProcess(T fsi) {
        fsi.setSecurityMetadataSource(customFilterSecurityMetadataSource); //1.路径（资源）拦截处理

        fsi.setAccessDecisionManager(customAccessDecisionManager); //2.权限决策处理类

        return fsi;
    }
}
