import NQP.*;

public class App {
    public static void main(String[] args) {
        int threadCount = 6;
        int dimension = 300;
        NQP[] jobs = new NQP[threadCount];
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            jobs[i] = new NQP(dimension, new LinearTemperatureController(0.5, 30, 0.99, 5000));
            threads[i] = new Thread(jobs[i]);
            threads[i].start();
        }
        int count = 0;
        int smallest = 100;
        for (int i = 0; i < threadCount; i++) {
            System.out.println("Solution #" + Integer.toString(i));
            System.out.println(jobs[i].getBest().toString());
            System.out.println("\n-------------------------------------\n");
            if (jobs[i].getBest().getEnergy() < 50) {
                count++;
                if (smallest > jobs[i].getBest().getEnergy()) {
                    smallest = jobs[i].getBest().getEnergy();
                }
            }
        }
        System.out.println("Small count: " + Integer.toString(count));
        System.out.println("Smallest: " + Integer.toString(smallest));
    }
}
