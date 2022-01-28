import NQP.*;

public class App {
    public static void main(String[] args) {
        ATemperatureController tc = new LinearTemperatureController(
                0.5, 50, 0.98, 100);
        NQP problem = new NQP(19, tc);
        Solution result = problem.solve();
        System.out.println(result.toString());
    }
}
