package dev.pekelund.pekcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PekCodeApplicationTests {

    @Test
    void applicationExists() {
        // Test that application class exists
        PekCodeApplication app = new PekCodeApplication();
        assertNotNull(app);
    }
}
