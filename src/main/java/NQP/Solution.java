package NQP;

import java.util.Arrays;
import java.util.Random;

final class Solution {
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
        this.coordinates = new Random().ints(dimension, 0, dimension - 1).toArray();

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
     * Dimension getter
     */
    public int getDimension() {
        return this.coordinates.length;
    }

    /*
     * Overriding inherited toString()
     * from object
     */
    @Override
    public String toString() {
        // Filling chess field with zeroes
        char[][] field = new char[this.coordinates.length][this.coordinates.length];
        for (int i = 0; i < this.coordinates.length; i++) {
            for (int j = 0; j < this.coordinates.length; j++) {
                field[i][j] = '0';
            }
        }

        // Placing 1 on queens positions and building final string
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.coordinates.length; i++) {
            field[i][this.coordinates[i]] = '1';
            stringBuilder.append(field[i]);
            stringBuilder.append('\n');
        }

        // returning result
        return stringBuilder.toString();
    }

    /*
     * Mutator
     */
    private void mutate() {
        // Generating indexes to swap
        int p1, p2;
        Random R = new Random();
        p1 = R.nextInt(this.coordinates.length);
        do {
            p2 = R.nextInt(this.coordinates.length);
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
        for (int i = 0; i < coordinates.length; i++) {
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
