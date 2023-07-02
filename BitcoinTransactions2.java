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


import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class BitcoinTransactions {

    // dp array to store the longest common subsequence
    private static String[][] dp;
    // list to store all the sequences that match the longest common subsequence
    private static List<List<String>> allSequences = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // read the two arrays of Bitcoin transactions
        String[] first = reader.readLine().split("\\s+");
        String[] second = reader.readLine().split("\\s+");

        // convert the lengths of the arrays to BigIntegers
        BigInteger m = new BigInteger(String.valueOf(first.length));
        BigInteger n = new BigInteger(String.valueOf(second.length));

        // initialize the dp array
        dp = new String[m.intValue() + 1][n.intValue() + 1];

        // find the longest common subsequence
        List<String> lcs = LCS(first, m.intValue(), second, n.intValue());

        // write the longest common subsequence to the output
        writer.write(lcs.toString().replaceAll(",", ""));
        writer.flush();
        writer.close();
        reader.close();
    }

    // function to find the longest common subsequence
    private static List<String> LCS(String[] first, int m, String[] second, int n) {
        // base case: if either array is empty, return an empty list
        if (m == 0 || n == 0) {
            return new ArrayList<>();
        }

        // if the current subproblem has already been solved, return the solution
        if (dp[m][n] != null) {
            return new ArrayList<>(Arrays.asList(dp[m][n].split(",")));
        }

        // if the last elements of the two arrays match, add the element to the LCS
        if (first[m - 1].equals(second[n - 1])) {
            List<String> lcs = new ArrayList<>(LCS(first, m - 1, second, n - 1));
            lcs.add(first[m - 1]);
            dp[m][n] = String.join(",", lcs);
            allSequences.add(lcs);
            return lcs;
        }

        // if the last elements of the two arrays do not match, find the LCS of the two arrays without the last element
        List<String> lcs1 = LCS(first, m, second, n - 1);
        List<String> lcs2 = LCS(first, m - 1, second, n);

        // choose the longer LCS and store it in the dp array
        if (lcs1.size() > lcs2.size()) {
            dp[m][n] = String.join(",", lcs1);
            return lcs1;
        } else {
            dp[m][n] = String.join(",", lcs2);
            return lcs2;
        }
    }
}

// Commit message - Used Buffered Reader and Writer. Used dymamic programming to find all the longest common subsequence and then chose the one that starts first and is the longest.