package NQP;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomGenerationTest {
    @Test
    void setRandomGenerationTest() {
        // Setting initial parameters
        int DIMENSION = 5;
        Random R = new Random();

        // Generating
        Set<Integer> generated;
        Integer[] result;
        for (int i = 0; i < 100; i++) {
            generated = new LinkedHashSet<>();
            while (generated.size() < DIMENSION)
            {
                Integer next = R.nextInt(DIMENSION);
                generated.add(next);
            }

            // Converting set to array
            result = new Integer[DIMENSION];
            generated.toArray(result);

            // Printing
            for (int j = 0; j < DIMENSION; j++) {
                System.out.print(result[j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
    }

    @Test
    void shuffleRandomTestGeneration() {
        int DIMENSION = 5;
        List<Integer> source = IntStream.range(0, DIMENSION)
                .boxed()
                .collect(Collectors.toList());
        for (int i = 0; i < 100; i++) {
            Collections.shuffle(source, ThreadLocalRandom.current());
            for (int j = 0; j < DIMENSION; j++) {
                System.out.print(source.get(j));
                System.out.print(' ');
            }
            System.out.print('\n');
        }
    }
}
