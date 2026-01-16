package io.github.segosambel.audit.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auditable {

    /**
     * Logical action name.
     * Example: CREATE_USER, UPDATE_ORDER, DELETE_PAYMENT
     */
    String action();

    /**
     * Optional category for grouping audits.
     * Example: USER, ORDER, PAYMENT
     */
    String category() default "";

    /**
     * Whether audit should include method arguments.
     * Be careful with sensitive data.
     */
    boolean captureArgs() default false;

    /**
     * Whether audit should include return value.
     */
    boolean captureResult() default false;
}
