package io.github.segosambel.audit;

import io.github.segosambel.audit.store.InMemoryAuditStore;
import io.github.segosambel.audit.test.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class AuditDisabledTest {

    @Test
    void audit_should_not_run_without_enable_annotation() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(DisabledConfig.class)
        ) {
            TestService service = context.getBean(TestService.class);
            InMemoryAuditStore store = context.getBean(InMemoryAuditStore.class);

            service.success();

            assertThat(store.getEvents()).isEmpty();
        }
    }

    @Configuration
    static class DisabledConfig {

        @Bean
        public TestService testService() {
            return new TestService();
        }

        @Bean
        public InMemoryAuditStore auditStore() {
            return new InMemoryAuditStore();
        }
    }
}
