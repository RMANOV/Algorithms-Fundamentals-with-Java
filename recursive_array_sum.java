// Recursive Array Sum
// Write a program that finds the sum of all elements in an integer array. Use recursion.
// Note: In practice, this recursion should not be used here (instead use an iterative solution), this is just an exercise.
// Examples
// Input Output
// 1 2 3 4 10
// -1 0 1 0
// Hints
// Write a recursive method. It will take as arguments the input array and the current index.
// • The method should return the current element + the sum of all next elements (obtained by recursively
// calling it).
// • The recursion should stop when there are no more elements in the array.

import java.util.Scanner;

public class RecursiveArraySum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
//        int[] numbers = new int[input.length];
//        for (int i = 0; i < input.length; i++) {
//            numbers[i] = Integer.parseInt(input[i]);

        int[] numbers = parseArray(input, 0, new int[input.length]);
        int sum = sum(numbers, 0);
        System.out.println(sum);
    }

    private static int[] parseArray(String[] input, int index, int[] numbers) {
        if (index == input.length) {
            return numbers;
        }
        numbers[index] = Integer.parseInt(input[index]);
        return parseArray(input, index + 1, numbers);
    }

    private static int sum(int[] numbers, int index) {
        if (index == numbers.length) {
            return 0;
        }
        return numbers[index] + sum(numbers, index + 1);
    }
}
