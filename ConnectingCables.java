// Connecting Cables
// We are in a rectangular room. On opposite sides of the room, there are sets of n cables (n < 1000). The cables are
// indexed from 1 to n.
// On each side of the room, there is a permutation of the cables, e.g. on one side we always have ordered {1, 2, 3, 4, 5}
// and on the other side, we have some permutation {5, 1, 3, 4, 2}. We are trying to connect each cable from one side
// with the corresponding cable on the other side – connect 1 with 1, 2 with 2, etc. The cables are straight and should
// not overlap!
// The task is to find the maximum number of pairs we can connect given the restrictions above.
// Input
// • The input data should be read from the console.
// 2 5 3 8 7 4 6 9 1 
// Output
// • The output data should be printed on the console.
// "Maximum pairs connected: 5"
// Input1
// 1 2 3 
// Output1
// "Maximum pairs connected: 3"

import java.util.Arrays;
import java.util.Scanner;

public class ConnectingCables {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the input sequence of integers
        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        // Create a matrix to store the maximum number of connected cables
        // for each possible length of the first set of cables and the second set of
        // cables
        int[][] matrix = new int[numbers.length + 1][numbers.length + 1];

        // Fill in the matrix using dynamic programming
        for (int row = 1; row < matrix.length; row++) { // first set of cables
            int firstSetCable = numbers[row - 1]; // first set cable
            for (int col = 1; col < matrix[0].length; col++) { // second set of cables
                int secondSetCable = numbers[col - 1]; // second set cable
                if (firstSetCable == secondSetCable) { // if cables are equal
                    matrix[row][col] = matrix[row - 1][col - 1] + 1; // copy the value from the upper left diagonal and
                                                                     // add 1
                } else { // if cables are not equal
                    matrix[row][col] = Math.max(matrix[row - 1][col], matrix[row][col - 1]); // get the maximum value
                                                                                             // from the upper and left
                                                                                             // cells
                }
            }
        }

        // Print the maximum number of connected cables
        System.out.println("Maximum pairs connected: " + matrix[numbers.length][numbers.length]);
    }
}