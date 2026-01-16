package io.github.segosambel.audit.support;

public interface AuditContextResolver {

    /**
     * @return actor identifier or null if unavailable.
     */
    String resolveActor();

    /**
     * @return source of action (HTTP, SCHEDULER, etc) or null if unavailable.
     */
    String resolveSource();
}
