// Climbing
// Roi has to climb a building (N x M matrix with positive integers) from bottom right to top left. 
// However, his path must be the path with the highest possible sum. 
// Also, he can move only up or left.
// Roi needs your help to find the path with the highest sum by following rules described above.

// Roi’s starting position.

// Roi’s target position.

// Best path.

// Input
// •	You will receive an integer – n – number of rows.
// •	You will receive an integer – m – number of cols.
// •	On the next n lines, you will receive col elements in the following format: "{colEl0} {colEl1} … {colElN}". 
// Output
// •	Print the sum of the highest path.
// •	Print all cells value that are part of the highest sum path on a single line joined by a space.
// Constraints
// •	n and m will be positive integers in the range [1,	 15].
// •	Column elements will be positive integers in the range [1, 999 999].
// Input
// 4
// 4
// 1 3 2 1
// 5 3 2 1
// 1 7 3 1
// 1 3 1 1
// Output
// 21
// 1 1 3 7 3 5 1
// Input
// 3
// 3
// 1 1 1
// 1 1 1
// 1 1 1
// Output

// 5
// 1 1 1 1 1

// Use BigInteger instead of int
// Use dynamic programming


import java.util.Scanner;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Climbing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        BigInteger[][] matrix = new BigInteger[rows][cols];
        BigInteger[][] dp = new BigInteger[rows][cols];
        String[][] path = new String[rows][cols];

        for (int row = rows - 1; row >= 0; row--) {
            for (int col = cols - 1; col >= 0; col--) {
                matrix[row][col] = scanner.nextBigInteger();
                if (row == rows - 1 && col == cols - 1) {
                    dp[row][col] = matrix[row][col];
                    path[row][col] = matrix[row][col].toString();
                } else if (row == rows - 1) {
                    dp[row][col] = matrix[row][col].add(dp[row][col + 1]);
                    path[row][col] = matrix[row][col] + " " + path[row][col + 1];
                } else if (col == cols - 1) {
                    dp[row][col] = matrix[row][col].add(dp[row + 1][col]);
                    path[row][col] = matrix[row][col] + " " + path[row + 1][col];
                } else {
                    if (dp[row + 1][col].compareTo(dp[row][col + 1]) > 0) {
                        dp[row][col] = matrix[row][col].add(dp[row + 1][col]);
                        path[row][col] = matrix[row][col] + " " + path[row + 1][col];
                    } else {
                        dp[row][col] = matrix[row][col].add(dp[row][col + 1]);
                        path[row][col] = matrix[row][col] + " " + path[row][col + 1];
                    }
                }
            }
        }

        System.out.println(dp[0][0]);
        System.out.println(path[0][0]);
    }
}
