// Super Set
// Write a program that receives an array of unique integers and prints its
// super set.
// Super set is the set of all subsets of the array. For example, the super set
// of 4, 5, 6 is:
// •
// • 4
// • 5
// • 6
// • 4 5
// • 4 6
// • 5 6
// • 4 5 6
// Input
// • On the first line you will receive an array of integers in the following
// format: "{el1}, …, {elN}".
// Output
// • Print the super set.
// o Each subset should be printed on a single line.
//  Elements in the subset should be printed in the following format: "{el1},
// …, {elN}".
// Constraints
// • Elements in the array will be unique.
// • Elements in the array will be positive integers in the range [-2147483648,
// 2147483647].
// • Array’s length will be in the range [1, 50].
// Input - 4, 5, 6
// Output
//
// 4
// 5
// 6
// 4 5
// 4 6
// 5 6
// 4 5 6
// Input1 - 1, 3, 5, 7
// Output1
//
// 1
// 3
// 5
// 7
// 1 3
// 1 5
// 1 7
// 3 5
// 3 7
// 5 7
// 1 3 5
// 1 3 7
// 1 5 7
// 3 5 7
// 1 3 5 7

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SuperSet {
    
    public static void main(String[] args) throws IOException {
        
        // Read input from console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        
        // Split input string into an array of integers
        String[] inputArray = input.split(", ");
        BigInteger[] inputIntArray = new BigInteger[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            inputIntArray[i] = new BigInteger(inputArray[i]);
        }
        
        // Create an empty super set
        ArrayList<ArrayList<BigInteger>> superSet = new ArrayList<ArrayList<BigInteger>>();
        ArrayList<BigInteger> empty = new ArrayList<BigInteger>();
        superSet.add(empty);
        
        // Generate all subsets of the input array
        for (int i = 0; i < inputIntArray.length; i++) {
            ArrayList<ArrayList<BigInteger>> newSuperSet = new ArrayList<ArrayList<BigInteger>>();
            for (ArrayList<BigInteger> subset : superSet) {
                newSuperSet.add(subset);
                ArrayList<BigInteger> newSubset = new ArrayList<BigInteger>(subset);
                newSubset.add(inputIntArray[i]);
                newSuperSet.add(newSubset);
            }
            superSet = newSuperSet;
        }
        
        // Convert subsets to strings and sort them
        ArrayList<String> output = new ArrayList<String>();
        for (ArrayList<BigInteger> subset : superSet) {
            Collections.sort(subset);
            String subsetString = "";
            for (int i = 0; i < subset.size(); i++) {
                subsetString += subset.get(i);
                if (i != subset.size() - 1) {
                    subsetString += " ";
                }
            }
            output.add(subsetString);
        }
        Collections.sort(output, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() == s2.length()) {
                    return s1.compareTo(s2);
                }
                return s1.length() - s2.length();
            }
        });        

        // Print the super set
        for (String subset : output) {
            System.out.println(subset);
        }
    }
}

// Commit mesage - Read input, generate super set, convert subsets to strings, sort and print them