# Spring Boot Audit Starter

Lightweight, annotation-based audit logging for Spring Boot applications.  
Designed to be **explicit**, **safe**, and **non-intrusive**.

> No boilerplate. No polluted business logic. Just clean audit.

---

## ✨ Features
- Annotation-based audit (`@Auditable`)
- Explicit opt-in via `@EnableAudit`
- Context-aware (Spring Security supported, optional)
- Pluggable storage (`AuditStore`)
- Safe by default (audit failure never breaks business)
- Spring Boot 3.x compatible
---

## 📦 Installation

```xml
<dependency>
    <groupId>io.github.segosambel</groupId>
    <artifactId>spring-boot-starter-audit</artifactId>
    <version>0.1.0</version>
</dependency>
```

## 🚀 Enable Audit
Audit is disabled by default. To enable it:
```java
@EnableAudit
@SpringBootApplication
public class Application {
}
```

## 🧩 Usage
Annotate any method you want to audit:
```java
import io.github.segosambel.audit.annotation.Auditable;

@Auditable(
        action = "CREATE_USER",
        category = "USER",
        captureArgs = true
)
public User createUser(CreateUserCommand command) {
    ...
}
```
Your business logic stays clean.
Audit runs as a side effect.

## 🧠 Audit Data Model
Each audit produces an `AuditEvent` containing:
* `action` - logical action name 
* `category` - optional grouping 
* `result` - SUCCESS / FAILED 
* `timestamp` 
* `actor` - resolved from context (or fallback)
* `source` - HTTP / SYSTEM / UNKNOWN 
* `metadata` - optional arguments / result

## 🔌 Storage (AuditStore)
Audit storage is pluggable.  
Default:
- `InMemoryAuditStore` (safe fallback, dev-friendly)

Custom implementation example:
```java
@Bean
public AuditStore auditStore() {
    return event -> {
        // persist to DB, send to Kafka, etc
    };
}
```

## 📄 License
MIT License