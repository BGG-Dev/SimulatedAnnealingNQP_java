package NQP;

public final class LinearTemperatureController extends ATemperatureController {
    // Freezing coefficient
    private final double FREEZE_COEFFICIENT;

    // Freeze trigger level
    private final int TRIGGER_LEVEL;

    // Call counter
    private int callCounter;

    /*
     * Default constructor
     */
    public LinearTemperatureController(double minimum, double start, double freezeCoefficient, int trigger_level) {
        super(minimum, start);
        this.FREEZE_COEFFICIENT = freezeCoefficient;
        this.TRIGGER_LEVEL = trigger_level;
        this.callCounter = 0;
    }

    /*
     * Update implementation
     */
    @Override
    boolean update() {
        if (this.callCounter < this.TRIGGER_LEVEL) {
            this.callCounter++;
            return false;
        }
        else {
            this.callCounter = 0;
            this.current *= this.FREEZE_COEFFICIENT;
            return true;
        }
    }
}
