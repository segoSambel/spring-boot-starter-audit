package io.github.segosambel.audit.store;

import io.github.segosambel.audit.model.AuditEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryAuditStore implements AuditStore {

    private final List<AuditEvent> events = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(AuditEvent event) {
        try {
            events.add(event);
        } catch (Exception e) {
            // ignore
        }
    }

    /**
     * For testing purpose only.
     */
    public List<AuditEvent> getEvents() {
        return List.copyOf(events);
    }
}
