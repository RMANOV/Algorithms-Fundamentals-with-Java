// Longest Zigzag Subsequence
// A zigzag sequence is one that alternately increases and decreases. More formally, such a sequence has to comply with
// one of the two rules below:
// 1) Every even element is smaller than its neighbors and every odd element is larger than its neighbors, or
// 2) Every odd element is smaller than its neighbors and every even element is larger than its neighbors
// 1 3 2 is a zigzag sequence, but 1 2 3 is not. Any sequence of one or two elements is zig zag.
// Find the longest zigzag subsequence in a given sequence.
// Use dynamic programming to solve this problem.
// Input: 1 7 4 9 2 5
// Output: 1 7 4 9 2 5
// Explanation: The longest zigzag subsequence is 1 7 4 9 2 5. It is strictly increasing, then strictly decreasing. 
// There are other solutions, like 1 7 9 2 5 or 1 4 9 2 5, but the one we presented is the longest.
// Input1: 8 3 5 7 0 8 9 10 20 20 20 12 19 11
// Output1: 8 3 5 0 20 12 19 11
// Get the input from the user and store it - can be with unknown length, so use ArrayList, then convert to array
// Use a BufferedReader to get the input from the user

import java.util.*;

public class LongestZigzagSubsequence {
    public static void main(String[] args) {
        // Get the input from the user
        Scanner input = new Scanner(System.in);
        // Create an ArrayList to store the input
        ArrayList<Integer> numbers = new ArrayList<>();
        while (input.hasNextInt()) {
            numbers.add(input.nextInt());
        }
        // Convert the ArrayList to an array
        int[] array = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            array[i] = numbers.get(i);
        }
        // Call the method to find the longest zigzag subsequence
        int[] result = longestZigzagSubsequence(array);
        // Print the results
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    // Method to find the longest zigzag subsequence
    public static int[] longestZigzagSubsequence(int[] array) {
        // Create a 2D array to store the results
        int[][] result = new int[array.length][2];
        // Initialize the result array
        for (int i = 0; i < array.length; i++) {
            result[i][0] = 1;
            result[i][1] = 1;
        }
        // Create a 2D array to store the previous index
        int[][] previousIndex = new int[array.length][2];
        // Initialize the previous index array
        for (int i = 0; i < array.length; i++) {
            previousIndex[i][0] = -1;
            previousIndex[i][1] = -1;
        }
        // Create a variable to store the maximum length
        int maxLength = 1;
        // Create a variable to store the index of the maximum length
        int index = 0;
        // Loop through the array
        for (int i = 1; i < array.length; i++) {
            // Loop through the previous elements
            for (int j = 0; j < i; j++) {
                // Check if the current element is greater than the previous element
                if (array[i] > array[j]) {
                    // Check if the current element is greater than the previous element and the
                    // previous element is
                    // less than the current element
                    if (result[i][0] < result[j][1] + 1) {
                        // Update the result array
                        result[i][0] = result[j][1] + 1;
                        // Update the previous index array
                        previousIndex[i][0] = j;
                    }
                }
                // Check if the current element is less than the previous element
                if (array[i] < array[j]) {
                    // Check if the current element is less than the previous element and the
                    // previous element is
                    // greater than the current element
                    if (result[i][1] < result[j][0] + 1) {
                        // Update the result array
                        result[i][1] = result[j][0] + 1;
                        // Update the previous index array
                        previousIndex[i][1] = j;
                    }
                }
            }
            // Check if the maximum length is less than the current length
            if (maxLength < Math.max(result[i][0], result[i][1])) {
                // Update the maximum length
                maxLength = Math.max(result[i][0], result[i][1]);
                // Update the index
                index = i;
            }
        }
        // Create an array to store the result
        int[] zigzagSubsequence = new int[maxLength];
        // Create a variable to store the index of the result array
        int k = maxLength - 1;
        // Loop through the result array
        while (index != -1) {
            // Check if the current element is greater than the previous element
            if (result[index][0] > result[index][1]) {
                // Update the result array
                zigzagSubsequence[k] = array[index];
                // Update the index
                index = previousIndex[index][0];
            } else {
                // Update the result array
                zigzagSubsequence[k] = array[index];
                // Update the index
                index = previousIndex[index][1];
            }
            // Update the index of the result array
            k--;
        }
        // Return the result array
        return zigzagSubsequence;
    }
}
