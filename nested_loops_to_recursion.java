// Nested Loops To Recursion
// Write a program that simulates the execution of n nested loops from 1 to n which prints the values of all its iteration
// variables at any given time on a single line. 
// Use recursion.

// Input Output
// Solution with nested loops
// (assuming n is positive)
// 2
// excpected output:
// 1 1
// 1 2
// 2 1
// 2 2

// 1.get n from the console
//2.printNestedLoops(elements, elements.length - 1, n);
//

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        printNestedLoops(new int[n], 0, n);

    }

    private static void printNestedLoops(int[] elements, int index, int n) {

        if (index == elements.length) {

            printArray(elements);

            return;

        }

        for (int i = 1; i <= n; i++) {

            elements[index] = i;

            printNestedLoops(elements, index + 1, n);

        }

    }

    private static void printArray(int[] elements) {

        for (int element : elements) {

            System.out.print(element + " ");

        }

        System.out.println();

    }

}
