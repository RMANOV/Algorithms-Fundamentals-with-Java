// . Combinations without Repetition
// Write a recursive program for generating and printing all combinations without duplicates of k elements from a set of n
// elements (k <= n). In combinations, the order of elements doesn’t matter, therefore (1 2) and (2 1) are the same
// combination, meaning that once you print/obtain (1 2), (2 1) is no longer valid.

// Input Output
// 2 3
// 1 2 3
// 1 2 4
// 1 3 4
// 2 3 4

// 1. get n and k from the console
// 2. create an array with size k
// 3. create a method that start from 1 to n
// 4. create a base case that checks if the index is equal to the array length
// 5. if the base case is true, print the array
// 6. else, start from 1 to n
// 7. assign the element at the index to the current number
// 8. call the method with index + 1 and the current number + 1

import java.util.Scanner;

public class Main {

    static int n, k;
    static int[] array;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 1. Get n and k from the console

        n = scanner.nextInt();

        k = scanner.nextInt();

        // 2. Create an array with size k
        array = new int[k];

        // 3. Call the recursive method starting from 1
        generateCombinations(0, 1);
    }

    static void generateCombinations(int index, int start) {
        // 4. Check if the index is equal to the array length
        if (index == k) {
            // 5. If it is, print the array
            printArray();
        } else {
            // 6. Else, start from the current number to n
            for (int i = start; i <= n; i++) {
                // 7. Assign the element at the index to the current number
                array[index] = i;
                // 8. Call the method with index + 1 and the current number + 1
                generateCombinations(index + 1, i + 1);
            }
        }
    }

    static void printArray() {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}