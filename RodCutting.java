//  Rod Cutting
// Find the best way to cut up a rod with a specified length. You are also given prices of all possible lengths starting
// from 0.
// Use dynamic programming to solve this problem.
// Input: length of rod, price of each length
// Output: maximum price
// Sample input:
// 0 1 5 8 9 10 17 17 20 24 30
// 4
// Sample output:
//10
//2 2
// Explanation: The best way to cut up a rod of length 4 is to cut it into two pieces of length 2, which gives a price of 5+5=10.


import java.util.*;

import java.util.*;

public class RodCutting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // read prices from input and store them in an ArrayList
        ArrayList<Integer> prices = new ArrayList<>();
        String[] inputs = sc.nextLine().split(" ");
        for (String input : inputs) {
            prices.add(Integer.parseInt(input));
        }

        // read length of rod from input
        int length = sc.nextInt();

        // create an ArrayList to store the maximum price for each length of rod
        ArrayList<Integer> dp = new ArrayList<>(Collections.nCopies(length + 1, 0));

        // loop through each length of rod from 1 to length
        for (int i = 1; i <= length; i++) {
            int max = Integer.MIN_VALUE;
            // loop through each possible cut of the rod from 1 to i
            for (int j = 1; j <= i; j++) {
                // check if the cut is within the range of available prices
                if (j < prices.size()) {
                    // calculate the maximum price for this cut and store it in max
                    max = Math.max(max, prices.get(j) + dp.get(i - j));
                }
            }
            // store the maximum price for this length of rod in dp
            dp.set(i, max);
        }

        // print the maximum price for the entire rod
        System.out.println(dp.get(length));

        // loop through each cut made to get the maximum price
        int i = length;
        while (i > 0) {
            int max = Integer.MIN_VALUE;
            int cut = 0;
            // loop through each possible cut of the rod from 1 to i
            for (int j = 1; j <= i; j++) {
                // check if the cut is within the range of available prices
                if (j < prices.size() && max < prices.get(j) + dp.get(i - j)) {
                    // update max and cut to the values for this cut
                    max = prices.get(j) + dp.get(i - j);
                    cut = j;
                }
            }
            // print the length of the cut
            System.out.print(cut + " ");
            // update i to the remaining length of the rod
            i -= cut;
        }
    }
}
