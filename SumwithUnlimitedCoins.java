// Sum with Unlimited Amount of Coins
// We have a set of coins with predetermined values, e.g. 1, 2, 5, 10, 20, 50. Given a sum of S, the task is to find how
// many combinations of coins will sum up to S. For each value, we can use an unlimited number of coins, e.g. we can
// use S coins of value 1 or S/2 coins of value 2 (if S is even), etc.
// Use a dynamic programming approach to solve this problem.
// Use buffered reader to read input from console
// Example input:
// 1 2 3 4 6
// 6
// Example output: 10
// Example input1:
// 1 2 5 10 20 50 100
// 100
// Example output1: 4563

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SumwithUnlimitedCoins {

    public static void main(String[] args) throws Exception {
        // Create a BufferedReader object to read input from console
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Read the coins from the input and split them by space
        String[] coins = br.readLine().split(" ");
        // Create an integer array to store the coins
        int[] coinsInt = new int[coins.length];
        // Convert the coins from string to integer and store them in the coinsInt array
        for (int i = 0; i < coins.length; i++) {
            coinsInt[i] = Integer.parseInt(coins[i]);
        }
        // Read the sum from the input
        int sum = Integer.parseInt(br.readLine());
        // Call the count function and print the result
        System.out.println(count(coinsInt, sum));
    }

    // Function to count the number of combinations of coins that sum up to S
    public static int count(int[] coins, int sum) {
        // Create an integer array to store the number of combinations for each sum
        int[] dp = new int[sum + 1];
        // Initialize the array with 0
        Arrays.fill(dp, 0);
        // There is only one way to make a sum of 0
        dp[0] = 1;
        // Loop through all the coins
        for (int i = 0; i < coins.length; i++) {
            // Loop through all the sums from the current coin value to the target sum
            for (int j = coins[i]; j <= sum; j++) {
                // Add the number of combinations for the current sum minus the current coin value
                dp[j] += dp[j - coins[i]];
            }
        }
        // Return the number of combinations for the target sum
        return dp[sum];
    }
}
