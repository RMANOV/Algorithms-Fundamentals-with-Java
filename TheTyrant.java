// 2.	The Tyrant
// Lord Vetinari has tasked you with a strange request. He is playing a long-distance game with the lord of Überwald.
// The rules are simple by given a sequence of positive integers you need to find the minimum sum subsequence from the array such that 
// at least one value among all groups of four consecutive elements is picked. 
// And since Lord Vetinari is bored of that game you have been tasked to create a program that calculates the solution.
// Input
// •	The input will come from the console on a single line as a sequence of integers 
// Output
// •	The output is a single integer that represents the minimum sum.
// Constraints
// •	The input will contain only positive integers.
//
// Examples
// Input - 1 2 3 4 5 6 7 8
// Output - 6
// Input - 11 34 23 8 1 3 5 13 4 69
// Output - 13
// Use buffered reader to read the input from the console and print the output to the console.
// Use dynamic programming to solve the problem.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TheTyrant {
    public static void main(String[] args) throws IOException {
        // create a buffered reader to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // read the input sequence of integers and store them in an array
        int[] numbers = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        // create an array to store the minimum sum subsequence
        int[] dp = new int[numbers.length];
        // initialize the first four elements of the dp array with the first four elements of the input array
        dp[0] = numbers[0];
        dp[1] = numbers[1];
        dp[2] = numbers[2];
        dp[3] = numbers[3];
        // iterate over the rest of the input array
        for (int i = 4; i < numbers.length; i++) {
            // calculate the minimum sum subsequence up to the current index
            dp[i] = Math.min(dp[i - 4], Math.min(dp[i - 3], Math.min(dp[i - 2], dp[i - 1]))) + numbers[i];
        }
        // print the minimum sum subsequence that includes at least one value among all groups of four consecutive elements
        System.out.println(Math.min(dp[numbers.length - 1],
                Math.min(dp[numbers.length - 2], Math.min(dp[numbers.length - 3], dp[numbers.length - 4]))));
    }
}
