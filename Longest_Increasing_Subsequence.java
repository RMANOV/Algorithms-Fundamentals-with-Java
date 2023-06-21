// Longest Increasing Subsequence
// Let’s have a sequence of numbers S = {a1, a2, … an}. An increasing subsequence is a sequence of numbers within S
// where each number is larger than the previous. We do not change the relative positions of the numbers, e.g. we do
// not move smaller elements to the left to obtain longer sequences. If several sequences with equal length exist, find
// the left-most of them.
// Examples
// Input Output
// 1 2 5 3 4 1 2 3 4
// 4 3 2 1 4
// 4 2 -1 3 5 5 2 3 5
// Solution
// Dynamic Programming Approach
// The LIS problem can be solved by dividing it into sub-problems – for each element at index i of the sequence S, find
// the LIS in the range [S0 … Si].
// Example for S = { 3, 14, 5, 12, 15, 7, 8, 9, 11, 10, 1 }:
// • LIS { 3 } => { 3 }
// • LIS { 3, 14 } => { 3, 14 }
// • LIS { 3, 14, 5 } => { 3, 5 }
// • LIS { 3, 14, 5, 12 } => { 3, 5, 12 }
// • etc.
// For each index, we’ll keep track of the length of the LIS up to that index and the previous index of the LIS. E.g., the
// length of the LIS at index 5 is 3, the longest sequence ending in seq[5] is {3, 5, 7} and the index of the previous
// element of the subsequence (the number 5) is 2. The table below illustrates these computations:
// index 0 1 2 3 4 5 6 7 8 9 10
// S[] 3 14 5 12 15 7 8 9 11 10 1
// len[] 1 2 2 3 4 3 4 5 6 6 1
// prev[] -1 0 0 2 3 2 5 6 7 7 -1
// LIS {3} {3,14} {3,5} {3,5,12} {3,5,12,15} {3,5,7} {3,5,7,8} {3,5,7,8,9} {3,5,7,8,9,11} {3,5,7,8,9,10} {1}
// We need to calculate the info in the above table for every element of the original sequence S, so we’ll need two
// additional arrays with lengths equal to the length of S. Translating this into code within our method, we get:
// We also need to keep track of the length of the longest subsequence found so far and the index at which it ends (we’ll
// use -1 to mark that there is no such index found currently):
// Calculate LIS at Each Index
// To obtain the longest increasing sequence up to a given index, we just have to find the LIS up to that point to which
// the current element can be appended as the largest. That is why, when considering the sequence {3, 14, 5} we
// obtained {3, 5}; we want to know the longest sequence in which the current number (5) participates.
// We’ll do the following:
// • loop through each number in the sequence;
// • find the longest sequence up to that point which ends with a number that is smaller than the current.
// Remember that we keep track of the length of each LIS in the len[] array. We’ll use this to find the longest sequence
// Don’t forget to keep track of the length of the longest increasing subsequence and the index of its last element.
// Recover the LIS by Walking through prev[]
// Knowing the index of the last element of the LIS, we can get the whole sequence by continuously taking each previous
// element using the info we keep in the prev[] array. Store the elements in a list, reverse it and return it.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        // Create a scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);
        // Read the input sequence from the console and convert it to an integer array
        int[] sequence = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        // Create two arrays to store the length of the longest increasing subsequence
        // ending at each index and the index of the previous element in the subsequence
        int[] len = new int[sequence.length];
        int[] prev = new int[sequence.length];
        // Initialize the maximum length of the longest increasing subsequence found so
        // far and the index of its last element
        int maxLength = 0;
        int lastIndex = -1;
        // Loop through each element in the sequence
        for (int i = 0; i < sequence.length; i++) {
            // Initialize the length of the longest increasing subsequence ending at the
            // current index to 1 and the index of the previous element to -1
            len[i] = 1;
            prev[i] = -1;
            // Loop through each element in the sequence before the current index
            for (int j = 0; j < i; j++) {
                // If the element at the current index is greater than the element at the
                // previous index and the length of the longest increasing subsequence ending at
                // the previous index plus 1 is greater than the length of the longest
                // increasing subsequence ending at the current index
                if (sequence[j] < sequence[i] && len[j] + 1 > len[i]) {
                    // Update the length of the longest increasing subsequence ending at the current
                    // index and the index of the previous element
                    len[i] = len[j] + 1;
                    prev[i] = j;
                }
            }
            // If the length of the longest increasing subsequence ending at the current
            // index is greater than the maximum length found so far
            if (len[i] > maxLength) {
                // Update the maximum length and the index of the last element of the longest
                // increasing subsequence
                maxLength = len[i];
                lastIndex = i;
            }
        }
        // Create a list to store the elements of the longest increasing subsequence
        List<Integer> longestSeq = new ArrayList<>();
        // Traverse the array of previous indices starting from the index of the last
        // element of the longest increasing subsequence and add each element to the
        // list
        while (lastIndex != -1) {
            longestSeq.add(sequence[lastIndex]);
            lastIndex = prev[lastIndex];
        }
        // Print the elements of the longest increasing subsequence in reverse order
        for (int i = longestSeq.size() - 1; i >= 0; i--) {
            System.out.print(longestSeq.get(i) + " ");
        }
    }
}
