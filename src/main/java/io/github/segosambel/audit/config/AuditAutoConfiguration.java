package io.github.segosambel.audit.config;

import io.github.segosambel.audit.aspect.AuditAspect;
import io.github.segosambel.audit.store.AuditStore;
import io.github.segosambel.audit.store.InMemoryAuditStore;
import io.github.segosambel.audit.support.AuditContextResolver;
import io.github.segosambel.audit.support.DefaultAuditContextResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AuditAutoConfiguration {

    /**
     * Default AuditStore.
     * User can override by providing their own AuditStore bean.
     */
    @Bean
    @ConditionalOnMissingBean
    public AuditStore auditStore() {
        return new InMemoryAuditStore();
    }

    /**
     * Default AuditContextResolver.
     */
    @Bean
    @ConditionalOnMissingBean
    public AuditContextResolver auditContextResolver() {
        return new DefaultAuditContextResolver();
    }

    /**
     * Main audit aspect.
     */
    @Bean
    public AuditAspect auditAspect(
            AuditStore auditStore,
            AuditContextResolver auditContextResolver
    ) {
        return new AuditAspect(auditStore, auditContextResolver);
    }
}
