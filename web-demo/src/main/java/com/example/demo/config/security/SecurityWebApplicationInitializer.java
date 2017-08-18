package com.example.demo.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * servlet管理的session
 */
@Configuration
public class SecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {

    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
}
