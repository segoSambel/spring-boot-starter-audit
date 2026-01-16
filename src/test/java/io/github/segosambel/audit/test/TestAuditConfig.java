package io.github.segosambel.audit.test;

import io.github.segosambel.audit.annotation.EnableAudit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAudit
public class TestAuditConfig {

    @Bean
    public TestService testService() {
        return new TestService();
    }
}
