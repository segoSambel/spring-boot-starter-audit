package io.github.segosambel.audit.aspect;

import io.github.segosambel.audit.annotation.Auditable;
import io.github.segosambel.audit.model.AuditEvent;
import io.github.segosambel.audit.model.AuditResult;
import io.github.segosambel.audit.store.AuditStore;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class AuditAspect {

    private final AuditStore auditStore;

    public AuditAspect(AuditStore auditStore) {
        this.auditStore = auditStore;
    }

    @Around("@annotation(Auditable)")
    public Object audit(ProceedingJoinPoint joinPoint, Auditable auditable) throws Throwable {
        Object result = null;
        AuditResult auditResult = AuditResult.SUCCESS;

        try {
            result = joinPoint.proceed();
            return result;
        } catch (Throwable ex) {
            auditResult = AuditResult.FAILED;
            throw ex;
        } finally {
            try {
                AuditEvent auditEvent = buildEvent(joinPoint, auditable, auditResult, result);
                auditStore.save(auditEvent);
            } catch (Exception ignored) {
                // ignored
            }
        }
    }

    public AuditEvent buildEvent(
            ProceedingJoinPoint joinPoint,
            Auditable auditable,
            AuditResult result,
            Object returnValue
    ) {
        Map<String, Object> metadata = new HashMap<>();

        if (auditable.captureArgs()) {
            metadata.put("args", joinPoint.getArgs());
        }

        if (auditable.captureResult() && result == AuditResult.SUCCESS) {
            metadata.put("result", returnValue);
        }

        return AuditEvent.builder()
                .action(auditable.action())
                .category(auditable.category())
                .result(result)
                .metadata(metadata.isEmpty() ? null : metadata)
                .build();
    }
}
