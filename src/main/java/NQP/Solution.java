package NQP;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Solution {
    // Chess figures positions
    private final int[] coordinates;

    // Energy
    private final int energy;

    /*
     * Default constructor
     */
    Solution(int[] coordinates) {
        this.coordinates = coordinates;
        this.energy = this.calculateEnergy();
    }

    /*
     * Random solution constructor
     */
    Solution(int dimension) {
        // Generating random coordinates
        List<Integer> source = IntStream.range(0, dimension)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(source, ThreadLocalRandom.current());
        this.coordinates = source.stream().mapToInt(i -> i).toArray();

        // Calculating energy
        this.energy = this.calculateEnergy();
    }

    /*
     * Mutation constructor
     */
    Solution(Solution other) {
        // Copying coordinates from given solution
        this.coordinates = Arrays.copyOf(other.coordinates, other.coordinates.length);

        // Mutating
        this.mutate();

        // Calculating energy
        this.energy = this.calculateEnergy();
    }

    /*
     * Coordinate getter
     */
    public int getCoordinate(int i) {
        return coordinates[i];
    }

    /*
     * Energy getter
     */
    public int getEnergy() {
        return this.energy;
    }

    /*
     * Overriding inherited toString()
     * from object
     */
    @Override
    public String toString() {
        // Creating new string builder
        StringBuilder stringBuilder = new StringBuilder();

        // Appending coordinates
        /*for (int i = 0; i < this.coordinates.length; i++) {
            stringBuilder.append("Coord #")
                    .append(i)
                    .append(": ")
                    .append(this.coordinates[i])
                    .append('\n');
        }*/

        // Appending energy
        stringBuilder.append("Energy: ").append(this.energy).append('\n');

        // returning result
        return stringBuilder.toString();
    }

    /*
     * Mutator
     */
    private void mutate() {
        // Generating indexes to swap
        int p1, p2;
        p1 = ThreadLocalRandom.current().nextInt(this.coordinates.length);
        do {
            p2 = ThreadLocalRandom.current().nextInt(this.coordinates.length);
        } while (p1 == p2);

        // Swapping
        int swap = this.coordinates[p1];
        this.coordinates[p1] = this.coordinates[p2];
        this.coordinates[p2] = swap;
    }

    /*
     * Energy calculator
     */
    private int calculateEnergy() {
        // Initializing variable to store conflicts
        int conflictCount = 0;

        // Counting conflicts
        for (int i = 0; i < coordinates.length - 1; i++) {
            for (int j = i + 1; j < coordinates.length; j++) {
                // Horizontal
                if (coordinates[i] == coordinates[j]) {
                    conflictCount++;
                    continue;
                }

                // Diagonal
                int delta = j - i;
                if (coordinates[i] + delta == coordinates[j]) {
                    conflictCount++;
                    continue;
                }
                if (coordinates[i] - delta == coordinates[j]) {
                    conflictCount++;
                }
            }
        }

        // Returning result
        return conflictCount;
    }
}
