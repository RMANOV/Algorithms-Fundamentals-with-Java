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
// Use BufferedReader and BufferedWriter instead of Scanner and System.out

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Climbing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // read the number of rows and columns
        int rows = Integer.parseInt(br.readLine().trim());
        int cols = Integer.parseInt(br.readLine().trim());

        // initialize the matrix, dp and path arrays
        BigInteger[][] matrix = new BigInteger[rows][cols];
        BigInteger[][] dp = new BigInteger[rows][cols];
        StringBuilder[][] path = new StringBuilder[rows][cols];

        // fill the matrix, dp and path arrays
        for (int row = rows - 1; row >= 0; row--) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = cols - 1; col >= 0; col--) {
                matrix[row][col] = new BigInteger(st.nextToken());
                if (row == rows - 1 && col == cols - 1) {
                    // if we are at the bottom right corner, set the dp value to the matrix value
                    dp[row][col] = matrix[row][col];
                    // set the path value to the matrix value
                    path[row][col] = new StringBuilder(matrix[row][col].toString());
                } else if (row == rows - 1) {
                    // if we are at the bottom row, set the dp value to the sum of the current matrix value and the dp value of the cell to the right
                    dp[row][col] = matrix[row][col].add(dp[row][col + 1]);
                    // set the path value to the matrix value followed by the path value of the cell to the right
                    path[row][col] = new StringBuilder(matrix[row][col] + " ").append(path[row][col + 1]);
                } else if (col == cols - 1) {
                    // if we are at the rightmost column, set the dp value to the sum of the current matrix value and the dp value of the cell below
                    dp[row][col] = matrix[row][col].add(dp[row + 1][col]);
                    // set the path value to the matrix value followed by the path value of the cell below
                    path[row][col] = new StringBuilder(matrix[row][col] + " ").append(path[row + 1][col]);
                } else {
                    // if we are not at the bottom row or rightmost column, choose the maximum dp value between the cell below and the cell to the right
                    if (dp[row + 1][col].compareTo(dp[row][col + 1]) > 0) {
                        dp[row][col] = matrix[row][col].add(dp[row + 1][col]);
                        path[row][col] = new StringBuilder(matrix[row][col] + " ").append(path[row + 1][col]);
                    } else {
                        dp[row][col] = matrix[row][col].add(dp[row][col + 1]);
                        path[row][col] = new StringBuilder(matrix[row][col] + " ").append(path[row][col + 1]);
                    }
                }
            }
        }

        // write the highest sum and the path to the output
        bw.write(dp[0][0] + "\n");
        bw.write(path[0][0].toString() + "\n");
        bw.flush();
    }
}

// Commit message: Read and write in console with BufferedReader and BufferedWriter, use BigInteger instead of int, use dynamic programming