//  N Choose K Count
// Given a n and k, calculate the number of possible n choose k combinations (without repetition).
// Examples
// Input Output
// 3
// 2
// 3
// 49
// 6
// 13983816

// 1. Get the input n and k
// 2. Create a method to calculate the factorial of a number
// 3. Calculate the result using the formula: n! / (k! * (n - k)!)
// 4. Print the result

import java.util.*; 
import java.math.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int k = Integer.parseInt(scanner.nextLine());

        BigInteger result = binomialCoefficient(n, k);

        System.out.println(result);

    }

    private static BigInteger factorial(int n) {

        if (n < 0) return BigInteger.valueOf(-1);   // handle negative input
        if (n == 0) return BigInteger.ONE;   // handle 0 input

        BigInteger result = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {

            result = result.multiply(BigInteger.valueOf(i));   

        }

        return result;

    }

    private static BigInteger binomialCoefficient(int n, int k) {
        
        if (n < k) return BigInteger.valueOf(-1);  // handle k > n

        // use the optimized formula: C(n,k) = C(n,n-k) * n! / ((n-k)! * n!)
        if (k > n - k) {
            k = n - k;
        }

        BigInteger result = BigInteger.ONE;

        for (int i = 0; i < k; i++) {
            // multiply by (n-i)
            result = result.multiply(BigInteger.valueOf(n - i));
            // divide by (i+1)
            result = result.divide(BigInteger.valueOf(i + 1));
        }
        
        return result;

        }
}


