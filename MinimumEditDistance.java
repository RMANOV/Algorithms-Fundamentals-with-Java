// Minimum Edit Distance
// We have two strings, s1 and s2. The goal is to obtain s2 from s1 by applying the following operations:
// • replace(i, x) – in s1, replace the symbol at index i with the character x
// • insert(i, x) – in s1, inserts the character x at index i
// • delete(i) – from s1, remove the character at index i
// We are only allowed to modify s1, s2 stays unchanged at all times. Each of the three operations has a certain cost
// associated with it (positive integer number). Note: the cost of the replace(i, x) operation is 0 if it doesn’t actually
// change the character.
// The goal is to find the sequence of operations which will produce s2 from s1 with minimal cost.
// Input
// • The input consists of five lines, on the first line the cost for replace on the second the cost for
// insert, and then on the third the cost for the delete. After that on the next two lines are the two strings
// s1 and s2.
// Output - Minimum edit distance: minimum cost of transforming s1 into s2
// • The output consists of a single line, the minimum cost of transforming s1 into s2.
// Use dynamic programming to solve this problem.
// Sample Input
// 3
// 2
// 1
// abracadabra
// mabragabra
// Sample Output
// "Minimum edit distance: 7"

import java.util.Scanner;

public class MinimumEditDistance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the cost of replace, insert, and delete operations
        int replaceCost = Integer.parseInt(scanner.nextLine());
        int insertCost = Integer.parseInt(scanner.nextLine());
        int deleteCost = Integer.parseInt(scanner.nextLine());
        // Read the two strings
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        // Create a 2D array to store the minimum edit distance
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        // Initialize the first row and column of the array
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i * deleteCost;
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = i * insertCost; // insertCost
        }
        // Fill in the rest of the array using dynamic programming
        for (int i = 1; i < dp.length; i++) {
            char s1Char = s1.charAt(i - 1);
            for (int j = 1; j < dp[0].length; j++) {
                char s2Char = s2.charAt(j - 1);
                if (s1Char == s2Char) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int replace = dp[i - 1][j - 1] + replaceCost;
                    int insert = dp[i][j - 1] + insertCost;
                    int delete = dp[i - 1][j] + deleteCost;
                    dp[i][j] = Math.min(replace, Math.min(insert, delete));
                }
            }
        }
        // Print the minimum edit distance
        System.out.println("Minimum edit distance: " + dp[s1.length()][s2.length()]);
    }
}