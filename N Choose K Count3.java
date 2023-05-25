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

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int k = Integer.parseInt(scanner.nextLine());

        long result = binomialCoefficient(n, k);

        System.out.println(result);

    }

    private static long factorial(int n) {

        if (n < 0) return -1;   // handle negative input
        if (n == 0) return 1;   // handle 0 input

        long result = 1;

        for (int i = 2; i <= n; i++) {

            result *= i;   

        }

        return result;

    }

    private static long binomialCoefficient(int n, int k) {
        
        if (n < k) return -1;  // handle k > n

        long result = factorial(n) / (factorial(k) * factorial(n - k));
        
        return result;
    
    }

}
