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

        if (n < 0 || k < 0) {
            throw new IllegalArgumentException("Neither n nor k can be negative.");
        }

        if (n < k) {
            throw new IllegalArgumentException("n cannot be less than k.");
        }

        if (n > 20) {
            throw new IllegalArgumentException("The calculation might result in overflow, n should be less than or equal to 20.");
        }

        long result = choose(n, k);

        System.out.println(result);
    }

    private static long factorial(int n) {
        long result = 1;

        for (int i = 2; i <= n; i++) {
            result *= i;
        }

        return result;
    }

    private static long choose(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }

        return factorial(n) / (factorial(k) * factorial(n - k));
    }
}

