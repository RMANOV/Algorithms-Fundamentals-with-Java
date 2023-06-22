// Move Down/Right
// Given a matrix of N by M cells filled with positive integers, find the path from top left to bottom right with the
// highest sum of cells by moving only down or right.
// Use dynamic programming to solve this problem.
// Use buffered reader to read the input.
// On the first line you will receive the numbers N and M separated by a single space
// On the next N lines there will be M numbers separated with spaces - the cells of the matrix
// Print the pairs of row and col separated by a single space in the path with the highest sum of cells
// The first element should always be 0, 0
// The last element should always be N - 1, M - 1
// Constraints
// 1 <= N <= 1000
// 1 <= M <= 1000
// Numbers in the matrix will be in the interval [0, 1000].
// Example input:
// 4
// 4
// 1 3 2 1
// 5 3 2 1
// 1 7 3 1
// 1 3 1 1
//Example output:
// [0, 0] [1, 0] [2, 0] [3, 0] [3, 1] [3, 2] [3, 3]

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MoveDownRight {
    public static void main(String[] args) throws IOException {
        // Create a BufferedReader object to read the input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Read the number of rows and columns of the matrix
        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());

        // Create a matrix with the given number of rows and columns
        int[][] matrix = new int[rows][cols];

        // Fill the matrix with the input values
        for (int i = 0; i < rows; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(input[j]);
            }
        }

        // Create a matrix to store the sums of the cells
        int[][] sums = new int[rows][cols];

        // Calculate the sum of the first row
        sums[0][0] = matrix[0][0];
        for (int i = 1; i < cols; i++) {
            sums[0][i] = sums[0][i - 1] + matrix[0][i];
        }

        // Calculate the sum of the first column
        for (int i = 1; i < rows; i++) {
            sums[i][0] = sums[i - 1][0] + matrix[i][0];
        }

        // Calculate the sum of the remaining cells
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                sums[i][j] = Math.max(sums[i - 1][j], sums[i][j - 1]) + matrix[i][j];
            }
        }

        // Find the path with the highest sum of cells
        int row = rows - 1;
        int col = cols - 1;

        StringBuilder result = new StringBuilder();
        result.append(String.format("[%d, %d] ", row, col));

        while (row > 0 && col > 0) {
            if (sums[row - 1][col] > sums[row][col - 1]) {
                row--;
            } else {
                col--;
            }

            result.insert(0, String.format("[%d, %d] ", row, col));
        }

        while (row > 0) {
            row--;
            result.insert(0, String.format("[%d, %d] ", row, col));
        }

        while (col > 0) {
            col--;
            result.insert(0, String.format("[%d, %d] ", row, col));
        }

        // Print the path with the highest sum of cells
        System.out.println(result.toString());
    }
}
