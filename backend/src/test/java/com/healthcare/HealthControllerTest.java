package com.healthcare;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class HealthControllerTest {
    private final HealthController controller = new HealthController();

    @Test
    void statusReturnsRunningApplicationDetails() {
        Map<String, String> status = controller.status();

        assertThat(status).containsEntry("application", "Health Backend")
                .containsEntry("status", "running")
                .containsEntry("frontend", "Open health.html from the project folder")
                .containsEntry("authApi", "/api/auth");
    }
}
