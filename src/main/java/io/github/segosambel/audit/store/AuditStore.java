package io.github.segosambel.audit.store;

import io.github.segosambel.audit.model.AuditEvent;

public interface AuditStore {

    /**
     * Persist audit event.
     * Implementation MUST NOT throw exception to caller.
     */
    void save(AuditEvent event);
}
