package io.github.segosambel.examples.audit;

import io.github.segosambel.audit.store.InMemoryAuditStore;
import org.springframework.stereotype.Component;

@Component
public class DemoAuditStore extends InMemoryAuditStore {
}
