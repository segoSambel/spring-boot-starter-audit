package io.github.segosambel.audit.model;

import java.time.Instant;
import java.util.Map;

public class AuditEvent {
    private final String action;
    private final String category;
    private final AuditResult result;
    private final Instant timestamp;
    private final String actor;
    private final String source;
    private final Map<String, Object> metadata;

    public AuditEvent(Builder builder) {
        this.action = builder.action;
        this.category = builder.category;
        this.result = builder.result;
        this.timestamp = builder.timestamp;
        this.actor = builder.actor;
        this.source = builder.source;
        this.metadata = builder.metadata;
    }

    public String getAction() {
        return action;
    }

    public String getCategory() {
        return category;
    }

    public AuditResult getResult() {
        return result;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getActor() {
        return actor;
    }

    public String getSource() {
        return source;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    // Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String action;
        private String category;
        private AuditResult result;
        private Instant timestamp;
        private String actor;
        private String source;
        private Map<String, Object> metadata;

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder result(AuditResult result) {
            this.result = result;
            return this;
        }

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder actor(String actor) {
            this.actor = actor;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public AuditEvent build() {
            if (this.timestamp == null) {
                this.timestamp = Instant.now();
            }
            return new AuditEvent(this);
        }
    }
}
