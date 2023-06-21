//  Fibonacci
// Write a dynamic programming solution for finding nth Fibonacci members.
// get input from user and print the nth Fibonacci number

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        // create a scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);
        // read the input integer from the user
        int n = scanner.nextInt();
        // print the nth Fibonacci number
        System.out.println(fib(n));
    }

    // method to calculate the nth Fibonacci number using dynamic programming
    public static int fib(int n) {
        // create an array to store the Fibonacci numbers
        int[] fib = new int[n + 1];
        // set the first Fibonacci number to 0
        fib[0] = 0;
        // if n is greater than 0, set the second Fibonacci number to 1
        if (n > 0) {
            fib[1] = 1;
            // calculate the rest of the Fibonacci numbers using dynamic programming
            for (int i = 2; i <= n; i++) {
                fib[i] = fib[i - 1] + fib[i - 2];
            }
        }
        // return the nth Fibonacci number
        return fib[n];
    }
}
