package NQP;

public abstract class ATemperatureController {
    // Minimum temperature
    final double minimum;

    // Current temperature
    double current;

    /*
     * Default protected constructor
     * to initialize temperatures
     */
    ATemperatureController(double minimum, double start) {
        this.minimum = minimum;
        this.current = start;
    }

    /*
     * Method to update current temperature
     * Returns true, if current temperature has been updated
     * else returns false
     */
    abstract boolean update();
}
