// Generating 0/1 Vectors
// Generate all n-bit vectors of zeroes and ones in lexicographic order.
// Examples
// Input Output
// 3 000
// 001
// 010
// 011
// 100
// 101
// 110
// 111
// 5 00000
// 00001
// 00010
// …
// 11110
// 11111
// Hints
// The method should receive as parameters the array which will be our vector and a current index.
// Bottom of recursion should be when the index is outside of the vector.
// To generate all combinations, create a for loop with a recursive call.

import java.util.Scanner;

public class Generating0_1Vectors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] vector = new int[n];
        generate(vector, 0);
    }

    private static void generate(int[] vector, int index) {
        if (index == vector.length) {
            print(vector);
            return;
        }
        for (int i = 0; i <= 1; i++) {
            vector[index] = i;
            generate(vector, index + 1);
        }
    }

    private static void print(int[] vector) {
        for (int i : vector) {
            System.out.print(i);
        }
        System.out.println();
    }
}

