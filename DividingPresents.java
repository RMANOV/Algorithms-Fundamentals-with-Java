// Dividing Presents
// Alan and Bob are twins. For their birthday they received some presents and now they need to split them amongst
// themselves. The goal is to minimize the difference between the values of the presents received by the two brothers,
// i.e. to divide the presents as equally as possible.
// Assume the presents have values represented by positive integer numbers and that presents cannot be split in half (a
// present can only go to one brother or the other).
// Find the minimal difference that can be obtained and print which presents each brother has received (you may only
// print the presents for one of them, it is obvious that the rest will go to the other brother). In the examples below Alan
// always takes a value less than or equal to Bob, but you may do it the other way around.
// Use a dynamic programming approach to solve this problem.
// Input - The input data should be read from the console - use buffered reader.
//3 2 3 2 2 77 89 23 90 11
// Output - The output data should be printed on the console.
// Difference: 30
// Alan:136 Bob:166
// Alan takes: 11 90 23 2 2 3 2 3
// Bob takes the rest.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DividingPresents {

    public static void main(String[] args) throws IOException {
        // Read input from console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] presents = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // Calculate total sum of presents
        int totalSum = Arrays.stream(presents).sum();

        // Create an array to store all possible sums of presents
        int[] sums = new int[totalSum + 1];

        // Iterate through each present
        for (int i = 0; i < presents.length; i++) {
            // Iterate through each possible sum of presents in reverse order
            for (int j = totalSum; j >= 0; j--) {
                // If the current sum is possible and the sum of the current present and the
                // current sum is not possible
                if (sums[j] > 0 && j + presents[i] < sums.length && sums[j + presents[i]] == 0) {
                    // Mark the sum of the current present and the current sum as possible
                    sums[j + presents[i]] = i + 1;
                }
            }
            // Mark the current present as possible
            sums[presents[i]] = i + 1;
        }

        // Calculate the half sum of presents
        int halfSum = totalSum / 2;
        int firstSum = 0;
        int secondSum = 0;
        int firstIndex = 0;

        // Iterate through each possible sum of presents less than or equal to the half
        // sum in reverse order
        for (int i = halfSum; i >= 0; i--) {
            // If the current sum is possible
            if (sums[i] > 0) {
                // Mark the current sum as the first sum
                firstSum = i;
                // Mark the index of the present that contributed to the current sum as the
                // index of the first present
                firstIndex = sums[i];
                break;
            }
        }

        // Calculate the second sum of presents
        secondSum = totalSum - firstSum;

        // Print the difference between the first and second sums of presents
        System.out.println("Difference: " + Math.abs(firstSum - secondSum));
        // Print the first and second sums of presents
        System.out.println("Alan:" + firstSum + " Bob:" + secondSum);

        // Create a StringBuilder to store the presents that Alan takes
        StringBuilder alanTakes = new StringBuilder();
        int tempSum = firstSum;

        // Iterate through each present that Alan takes
        while (tempSum > 0) {
            // Append the present to the StringBuilder
            alanTakes.append(presents[sums[tempSum] - 1]).append(" ");
            // Subtract the present from the current sum
            tempSum -= presents[sums[tempSum] - 1];
        }

        // Print the presents that Alan takes
        System.out.println("Alan takes: " + alanTakes.toString().trim());

        // Print that Bob takes the rest of the presents
        System.out.println("Bob takes the rest.");
    }
}
