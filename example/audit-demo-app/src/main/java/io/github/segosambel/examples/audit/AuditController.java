package io.github.segosambel.examples.audit;

import io.github.segosambel.audit.model.AuditEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/audits")
public class AuditController {

    public final DemoAuditStore auditStore;

    public AuditController(DemoAuditStore auditStore) {
        this.auditStore = auditStore;
    }

    @GetMapping
    public List<AuditEvent> getAuditEvents() {
        return auditStore.getEvents();
    }

}
