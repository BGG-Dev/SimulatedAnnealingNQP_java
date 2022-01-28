package NQP;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemperatureControllerTest {
    @Test
    void linearTemperatureControllerUpdateTest() {
        // Creating news LinearTemperatureController
        LinearTemperatureController temperatureController =
                new LinearTemperatureController(0.5, 100, 0.95, 100);

        // Updating 100 times
        for (int i = 0; i < 100; i++) {
            temperatureController.update();
        }

        // Assert 1
        assertEquals(100, temperatureController.current);

        // 101st call
        temperatureController.update();

        // Asserting
        assertEquals(95, temperatureController.current);
    }
}
