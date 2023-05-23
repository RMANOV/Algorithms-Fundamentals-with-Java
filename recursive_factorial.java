// Recursive Factorial
// Write a program that finds the factorial of a given number. Use recursion.
// Note: In practice, this recursion should not be used here (instead use an iterative solution), this is just an exercise.
// Examples
// Input Output
// 5 120
// 10 3628800
// Hints
// Write a recursive method. It will take as arguments an integer number.
// • The method should return the current element * the result of calculating the factorial of current element -
// 1 (obtained by recursively calling it).
// • The recursion should stop when the last element is reached.

import java.util.Scanner;

public class RecursiveFactorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        long factorial = factorial(n);
        System.out.println(factorial);
    }

    private static long factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1); // recursion
    }
}


