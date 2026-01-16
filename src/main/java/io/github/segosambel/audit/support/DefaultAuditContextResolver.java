package io.github.segosambel.audit.support;

public class DefaultAuditContextResolver implements AuditContextResolver {

    @Override
    public String resolveActor() {
        return "SYSTEM";
    }

    @Override
    public String resolveSource() {
        return "UNKNOWN";
    }
}
