// Bitcoin Transactions
// You are given two arrays of Bitcoin transactions, represented as arrays of transaction IDs.
// Your task is to find the longest transaction ID sequence that appears in both arrays, in the same order but not necessarily contiguous.
// For example, consider the following two arrays:
// Array 1: ["tx1", "tx2", "tx3", "tx4", "tx5", "tx6", "tx7"]
// Array 2: ["tx1", "tx3", "tx5", "tx7", "tx9"]
// The longest sequence of transaction IDs that appears in both arrays, in the same order, is ["tx1", "tx3", "tx5", "tx7"], which has a length of 4.
// Input
// •	The input consists of 2 lines - arrays of Bitcoin transactions.
// •	Both arrays will be in the following format: "{tx1} {tx2} … {txN}".
// Output
// •	Print the best sequence as described in the problem description in the following format: "[{tx1} {tx2} … {txN}]".
// Constraints
// •	The input will always be valid.
// •	The array lengths will be in the range [1… 1000].
// •	There might be more than one sequence matching condition described above.
// o	In such a case, you should pick the sequence that starts before others.
// o	If there are more than one sequence that starts at the same index, pick the one with the longest length.
// Input
// tx1 tx2 tx3 tx4 tx5 tx6 tx7
// tx1 tx3 tx5 tx7 tx9
// Output
// [tx1 tx3 tx5 tx7]
// Input1
// tx1 tx2 tx3 tx4 tx5
// tx1 tx2 tx3 tx4 tx5
// Output1
// [tx1 tx2 tx3 tx4 tx5]


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BitcoinTransactions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the two arrays of Bitcoin transactions
        String[] first = scanner.nextLine().split("\\s+");
        String[] second = scanner.nextLine().split("\\s+");

        int m = first.length;
        int n = second.length;

        // Create a 2D array to store the length of the longest common subsequence
        int[][] dp = new int[m + 1][n + 1];

        // Fill the 2D array using dynamic programming
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    // If one of the arrays is empty, the length of the longest common subsequence is 0
                    dp[i][j] = 0;
                } else if (first[i - 1].equals(second[j - 1])) {
                    // If the last elements of the two arrays are equal, the length of the longest common subsequence is the length of the longest common subsequence of the two arrays without their last elements plus 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // If the last elements of the two arrays are not equal, the length of the longest common subsequence is the maximum of the length of the longest common subsequence of the first array without its last element and the length of the longest common subsequence of the second array without its last element
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // The length of the longest common subsequence is stored in the bottom-right corner of the 2D array
        int index = dp[m][n];

        // Create a list to store the longest common subsequence
        List<String> result = new ArrayList<>();

        // Traverse the 2D array from the bottom-right corner to the top-left corner to find the longest common subsequence
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (first[i - 1].equals(second[j - 1])) {
                // If the last elements of the two arrays are equal, add the last element to the list and move to the next element in both arrays
                result.add(0, first[i - 1]);
                i--;
                j--;
                index--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                // If the length of the longest common subsequence of the first array without its last element is greater than the length of the longest common subsequence of the second array without its last element, move to the next element in the first array
                i--;
            } else {
                // If the length of the longest common subsequence of the second array without its last element is greater than the length of the longest common subsequence of the first array without its last element, move to the next element in the second array
                j--;
            }
        }

        // Print the longest common subsequence without commas
        System.out.println(result.toString().replaceAll(",", ""));
    }
}
