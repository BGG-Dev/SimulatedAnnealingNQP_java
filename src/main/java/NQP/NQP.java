package NQP;

import java.util.concurrent.ThreadLocalRandom;

public class NQP implements Runnable {
    // Problem dimension
    private final int DIMENSION;

    // Solutions
    private Solution best = null;
    private Solution current = null;

    // Temperature controller
    private final ATemperatureController temperatureController;

    /*
     * Default constructor
     */
    public NQP(int dimension, ATemperatureController temperatureController) {
        this.DIMENSION = dimension;
        this.temperatureController = temperatureController;
    }

    /*
     * Best solution getter
     */
    public synchronized Solution getBest() {
        return this.best;
    }

    /*
     * Main algorithm implementation
     */
    public synchronized void run() {
        // Initializing best with random
        this.best = new Solution(this.DIMENSION);

        // Main cycle
        while (this.best.getEnergy() != 0 &&
                this.temperatureController.current > this.temperatureController.minimum) {
            // Generating new solution by mutating best
            this.current = new Solution(this.best);

            // Checking energy
            if (this.current.getEnergy() < this.best.getEnergy()) {
                // True -> current is the best
                this.best = this.current;
            }
            else {
                // Calculating chance
                double delta = this.current.getEnergy() - this.best.getEnergy();
                double barrier = Math.pow(Math.E, -1.0 * delta / this.temperatureController.current);

                // Generating chance of jump
                double chance = ThreadLocalRandom.current().nextDouble();

                // Making final decision
                if (chance < barrier) {
                    this.best = this.current;
                }
            }

            // Updating temperature
            this.temperatureController.update();
        }
    }
}
