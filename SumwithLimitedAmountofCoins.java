// Sum with Limited Amount of Coins
// In this problem, we’ll regard the coins as actual coins, e.g.
// 1, 2, and 5 are three coins and we can use
// each of them only once. We can, of course, have more coins of a given value,
// e.g. – 1, 1, 2, 2, 10.
// The task is - find the number of ways we can combine the coins to
// obtain a certain sum S.
// For example, if we have coins with values 2, 3 and 5, we can obtain the sum 10
// in the following 4 ways: 2+3+5, 2+8, 3+7, and 5+5.
// Use dynamic programming.
// Input
// 1 2 2 3 3 4 6
// 6
// Output - 4
// Input1
// 50 20 20 20 20 20 10
// 100
// Output1 - 2

import java.util.*;

public class SumWithLimitedAmountOfCoins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the input coins and store them in a map with their frequency
        String[] coins = scanner.nextLine().split(" ");
        Map<Integer, Integer> coinMap = new HashMap<>();
        for (String coin : coins) {
            int coinValue = Integer.parseInt(coin);
            coinMap.put(coinValue, coinMap.getOrDefault(coinValue, 0) + 1);
        }
        // Read the target sum
        int sum = Integer.parseInt(scanner.nextLine());
        // Create a matrix to store the number of ways to obtain each sum
        int[][] matrix = new int[coinMap.size() + 1][sum + 1];
        // Initialize the first row to 1, since there is only one way to obtain a sum of
        // 0
        matrix[0][0] = 1;
        int i = 0;
        // Iterate over each coin value in the map
        for (int coinValue : coinMap.keySet()) {
            i++;
            // Iterate over each possible sum
            for (int j = 0; j <= sum; j++) {
                // Copy the value from the previous row
                matrix[i][j] = matrix[i - 1][j];
                // Iterate over each possible frequency of the current coin
                for (int k = 1; k <= coinMap.get(coinValue); k++) {
                    // If the current sum is greater than or equal to the value of k coins of the
                    // current value
                    if (j >= k * coinValue) {
                        // Add the number of ways to obtain the remaining sum using the previous coins
                        matrix[i][j] += matrix[i - 1][j - k * coinValue];
                    }
                }
            }
        }
        // Print the number of ways to obtain the target sum
        System.out.println(matrix[coinMap.size()][sum]);
    }
}
