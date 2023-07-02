// 1.	Bitcoin Miners
// As a Bitcoin miner, your goal is to select a subset of transactions from a pool of unconfirmed transactions to include in the next block that you mine. However, the block has a size limit, so you cannot include all transactions. You must carefully choose x transactions from the pool of n transactions that will generate the highest transaction fees and provide the most value to the network.
// You are a Bitcoin miner and you have a pool of n transactions waiting to be added to a block. You want to select x transactions to include in the next block, where x <= n. 
// How many different ways can you select x transactions from the pool of n transactions?
// Input
// •	The input consists of two lines.
// •	The first line is an integer - n - the number of all transactions in the pool.
// •	The second line is an integer - x - the number of transactions you can pick as a miner.
// Output
// •	The output consists of one line - p - the number of ways can you select x transactions from the pool of n transactions.
// Constraints
// •	n will be an integer in the range [1… 20].
// •	x will be an integer in the range [1… 10].
// •	n >= x
//INPUT
// 10
// 3
//OUTPUT
// 120
//INPUT1
// 20
// 5
//OUTPUT1
// 15504

import java.util.Scanner;

public class BitcoinMiners {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the number of all transactions in the pool
        int n = Integer.parseInt(scanner.nextLine());
        // Read the number of transactions you can pick as a miner
        int x = Integer.parseInt(scanner.nextLine());
        // Calculate the number of ways can you select x transactions from the pool of n transactions
        System.out.println(binomialCoeff(n, x));
    }

    // Calculate the binomial coefficient of n and k
    static int binomialCoeff(int n, int k) {
        int res = 1;
        // If k is greater than n - k, swap them
        if (k > n - k)
            k = n - k;
        // Calculate the binomial coefficient using the formula
        for (int i = 0; i < k; ++i) {
            res *= (n - i);
            res /= (i + 1);
        }
        // Return the result
        return res;
    }
}
