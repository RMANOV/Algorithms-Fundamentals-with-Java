// Reverse Array
// Write a program that reverses and prints an array. Use recursion

// 1. Create a method that takes an array and an index as parameters
// 2. Create a base case that checks if the index is less than 0
// 3. If the base case is true, return
// 4. Else, print the element at the index
// 5. Call the method with the array and index - 1
// 6. Print the element at the index

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] elements = scanner.nextLine().split(" ");

        printArrayReversed(elements, elements.length - 1);

    }

    private static void printArrayReversed(String[] elements, int index) {

        if (index < 0)
            return;

        System.out.print(elements[index] + " ");

        printArrayReversed(elements, index - 1);

    }

}