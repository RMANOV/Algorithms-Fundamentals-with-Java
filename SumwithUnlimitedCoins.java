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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] coins = br.readLine().split(" ");
        int[] coinsInt = new int[coins.length];
        for (int i = 0; i < coins.length; i++) {
            coinsInt[i] = Integer.parseInt(coins[i]);
        }
        int sum = Integer.parseInt(br.readLine());
        System.out.println(count(coinsInt, sum));
    }

    public static int count(int[] coins, int sum) {
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, 0);
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= sum; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[sum];
    }
}

