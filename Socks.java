// Socks
// You will be given two sequences of integers representing socks – left and right socks. 
// We are trying to connect each sock from one side with the corresponding sock on the other side. Find the longest sequence that appears in the same relative order, but not necessarily contiguous.
// Input
// •	You will receive 2 arrays of integers – left and right socks – in the following format: "{sock1} … {sockN}".
// Output
// •	Print the length of the longest sequence that appears in the same relative order.
// Constraints
// •	Prices will be integers in the range [1… 2 147 483 647].
// •	The input arrays will always contain at least 1 element.
// Input
// 31 41 29 32 42 40 26
// 31 30 32 42 28 29 29
// Output - 3
// Explanation - 31 32 42
// Input1
32 32 29 32 29
29 32 32 29 32
// Output1 - 4
// Use the longest common subsequence algorithm
// Use bufferedReader to read the input from the console and print the output on the console
// Use BigInteger to store the input values


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Socks {
    public static void main(String[] args) throws IOException {
        // Create a BufferedReader object to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // Read the left and right socks from the console and store them in BigInteger arrays
        BigInteger[] leftSocks = Arrays.stream(reader.readLine().split("\\s+")).map(BigInteger::new).toArray(BigInteger[]::new);
        BigInteger[] rightSocks = Arrays.stream(reader.readLine().split("\\s+")).map(BigInteger::new).toArray(BigInteger[]::new);
        
        // Create a matrix to store the length of the longest common subsequence
        int[][] matrix = new int[leftSocks.length + 1][rightSocks.length + 1];
        
        // Iterate over the matrix and fill it with the length of the longest common subsequence
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix[row].length; col++) {
                // Get the left and right socks at the current position
                BigInteger leftSock = leftSocks[row - 1];
                BigInteger rightSock = rightSocks[col - 1];
                
                // If the left and right socks are equal, add 1 to the length of the longest common subsequence
                if (leftSock.equals(rightSock)) {
                    matrix[row][col] = matrix[row - 1][col - 1] + 1;
                } else {
                    // If the left and right socks are not equal, take the maximum length of the longest common subsequence
                    // from the previous row or the previous column
                    matrix[row][col] = Math.max(matrix[row - 1][col], matrix[row][col - 1]);
                }
            }
        }
        
        // Print the length of the longest common subsequence
        System.out.println(matrix[leftSocks.length][rightSocks.length]);
    }
}


// Commit message - Read the input from the console with BufferedReader and print the output on the console, use BigInteger to store the input values, implements the longest common subsequence algorithm