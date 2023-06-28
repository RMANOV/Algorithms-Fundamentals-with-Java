// 2.	Stairs
// Stairs, why it does have to be stairs? 
// Well, this one is simple… 
// You need to climb N steps on the stairs, the catch is on each step you can either climb one stair or two (by jumping over one), so the question is knowing on each step you can make that choice in how many ways can you climb the stairs.
// … or is it?
// 1.	Input
// •	The input will come from the console on a single line a single number N the number of steps on the stairs. 
// 2.	Output
// •	The output is a single integer that represents how many ways you can climb the stairs.
// 3.	Constraints
// •	The input will be an integer in the range [0…10 000].
// Hint: Note that the first step (the one from the ground to the stairs) is also a step made and a choice as well.
// 4.	Examples
// Input	Output	Explanation
// 1	1	There is only one way to climb 1 step.
// 2	2	There are two ways to climb 2 steps. You can either climb 1 step twice or jump over one step.
// 3	3	There are three ways to climb 3 steps. You can either climb 1 step three times, climb 2 steps and then 1 step or climb 1 step and then 2 steps.
// 8    34 There are 34 ways to climb 8 steps.

// Solution: Stairs.java
// The code reads the number of steps from the console and calculates the number of ways to climb the stairs
// The solution uses an array to store the number of ways to climb each step
// The array is initialized with 0s and the first two elements are set to 1
// The number of ways to climb each step is calculated by summing the number of ways to climb the previous two steps
// The final result is printed to the console

import java.math.BigInteger;
import java.util.Scanner;

public class Stairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // read the number of steps from the console
        int steps = Integer.parseInt(scanner.nextLine());
        // initialize an array to store the number of ways to climb the stairs
        // the array will have a length of steps + 1
        // the array will be initialized with 0s
        BigInteger[] ways = new BigInteger[steps + 1];
        // set the first two elements of the array to 1
        // the first element represents the number of ways to climb 0 steps
        // the second element represents the number of ways to climb 1 step
        ways[0] = BigInteger.ONE;
        ways[1] = BigInteger.ONE;
        // loop through the array starting at index 2
        for (int i = 2; i < ways.length; i++) {
            // the number of ways to climb the current step is equal to the sum of the
            // number of ways to climb the previous two steps
            ways[i] = ways[i - 1].add(ways[i - 2]);
        }
        // print the number of ways to climb the stairs
        System.out.println(ways[steps]);
    }
}
