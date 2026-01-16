package io.github.segosambel.audit;

import io.github.segosambel.audit.model.AuditEvent;
import io.github.segosambel.audit.model.AuditResult;
import io.github.segosambel.audit.store.InMemoryAuditStore;
import io.github.segosambel.audit.test.TestAuditConfig;
import io.github.segosambel.audit.test.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AuditEnabledTest {

    @Test
    void audit_should_be_recorded_on_success() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(TestAuditConfig.class)
        ) {
            TestService service = context.getBean(TestService.class);
            InMemoryAuditStore store = context.getBean(InMemoryAuditStore.class);

            service.success();

            List<AuditEvent> events = store.getEvents();
            assertThat(events).hasSize(1);

            AuditEvent event = events.get(0);
            assertThat(event.getAction()).isEqualTo("SUCCESS_ACTION");
            assertThat(event.getResult()).isEqualTo(AuditResult.SUCCESS);
        }
    }

    @Test
    void audit_should_be_recorded_on_failure() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(TestAuditConfig.class)
        ) {
            TestService service = context.getBean(TestService.class);
            InMemoryAuditStore store = context.getBean(InMemoryAuditStore.class);

            assertThatThrownBy(service::failure).isInstanceOf(RuntimeException.class);

            List<AuditEvent> events = store.getEvents();
            assertThat(events).hasSize(1);

            AuditEvent event = events.get(0);
            assertThat(event.getAction()).isEqualTo("FAILED_ACTION");
            assertThat(event.getResult()).isEqualTo(AuditResult.FAILED);
        }
    }
}
