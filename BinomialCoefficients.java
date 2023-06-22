// Binomial Coefficients
// Write a program that finds the binomial coefficient (
// n
// 𝑘
// ) for given non-negative integers n and k. The coefficient can
// be found recursively by adding the two numbers above using the formula:
// However, this leads to calculating the same coefficient multiple times (a problem that also occurs when solving the
// Fibonacci problem recursively). Use memoization to improve performance.
// You can check your answers using the picture below (row and column indices start from 0):
// Input
// 3
// 2
// Output
// 3

import java.util.Scanner;
import java.math.BigInteger;

public class BinomialCoefficients {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        BigInteger[][] binomialCoefficients = new BigInteger[n + 1][k + 1];
        
        // Initialize the 2D array with BigInteger values
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                binomialCoefficients[i][j] = BigInteger.ZERO;
            }
        }
        
        // Any number choose 0 is 1
        for (int i = 0; i <= n; i++) {
            binomialCoefficients[i][0] = BigInteger.ONE;
        }
        
        // Any number choose itself is 1
        for (int i = 0; i <= k && i <= n; i++) {
            binomialCoefficients[i][i] = BigInteger.ONE;
        }
        
        // Calculate the binomial coefficients for all other n and k values
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i && j <= k; j++) {
                binomialCoefficients[i][j] = binomialCoefficients[i - 1][j - 1].add(binomialCoefficients[i - 1][j]);
            }
        }
        
        // Print the binomial coefficient for n and k
        System.out.println(binomialCoefficients[n][k]);
    }
}
