package NQP;

public class MultiThreadNQP implements Runnable{
    // NQP to call solve from
    private final NQP nqp;

    /*
     * Default constructor
     */
    public MultiThreadNQP(int dimension, ATemperatureController temperatureController) {
        this.nqp = new NQP(dimension, temperatureController);
    }
    public void run() {

    }
}
