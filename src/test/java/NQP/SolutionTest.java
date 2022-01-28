package NQP;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SolutionTest {
    @Test
    void energyCalculationTest4() {
        // Creating solutions
        Solution[] solutions = new Solution[8];
        solutions[0] = new Solution(new int[] {1, 3, 0, 2});
        solutions[1] = new Solution(new int[] {1, 1, 1, 1});
        solutions[2] = new Solution(new int[] {0, 1, 2, 3});
        solutions[3] = new Solution(new int[] {3, 2, 1, 0});
        solutions[4] = new Solution(new int[] {0, 1, 1, 0});
        solutions[5] = new Solution(new int[] {3, 2, 2, 3});
        solutions[6] = new Solution(new int[] {0, 1, 0, 3});
        solutions[7] = new Solution(new int[] {0, 3, 0, 2});

        // Storing true energy values
        int[] energy = new int[] {0, 6, 6, 6, 4, 4, 5, 1};

        // Checking
        for (int i = 0; i < solutions.length; i++) {
            assertEquals(energy[i], solutions[i].getEnergy());
        }
    }

    @Test
    void energyCalculationTest5() {
        // Creating solutions
        Solution[] solutions = new Solution[2];
        solutions[0] = new Solution(new int[]{4, 1, 2, 0, 1});
        solutions[1] = new Solution(new int[]{1, 0, 0, 1, 3});

        // Storing true energy
        int[] energy = new int[] {4, 5};

        // Checking
        for (int i = 0; i < solutions.length; i++) {
            assertEquals(energy[i], solutions[i].getEnergy());
        }
    }

    @Test
    void randomSolutionGenerationTest() {
        System.out.println("Random solution generation test result:");
        for (int i = 0; i < 5; i++) {
            System.out.println(new Solution(8));
        }
    }

    @Test
    void mutationTest() {
        // Creating random solution
        Solution s1 = new Solution(5);

        // Creating mutated copy from s1
        Solution s2 = new Solution(s1);

        // Checking
        int checkSum = 0;
        for (int i = 0; i < 5; i++) {
            if (s1.getCoordinate(i) == s2.getCoordinate(i)) {
                checkSum++;
            }
        }
        assertNotEquals(5, checkSum);
    }
}
