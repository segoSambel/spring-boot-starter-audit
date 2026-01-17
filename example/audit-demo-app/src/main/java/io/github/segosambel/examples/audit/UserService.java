package io.github.segosambel.examples.audit;

import io.github.segosambel.audit.annotation.Auditable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Auditable(
            action = "CREATE_USER",
            category = "USER",
            captureArgs = true
    )
    public String createUser(String username) {
        return "user-" + username;
    }

    @Auditable(
            action = "DELETE_USER",
            category = "USER"
    )
    public void deleteUser(String username) {
        if ("admin".equalsIgnoreCase(username)) {
            throw new RuntimeException("Cannot delete admin user");
        }
    }
}
