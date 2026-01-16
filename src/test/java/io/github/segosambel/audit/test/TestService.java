package io.github.segosambel.audit.test;

import io.github.segosambel.audit.annotation.Auditable;

public class TestService {

    @Auditable(action = "SUCCESS_ACTION", category = "TEST")
    public String success() {
        return "OK";
    }

    @Auditable(action = "FAILED_ACTION", category = "TEST")
    public void failure() {
        throw new RuntimeException("Failed");
    }
}
