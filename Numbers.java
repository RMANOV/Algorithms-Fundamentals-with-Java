// 1.	Numbers
// By given number N print all the possible ways to get that number by summing other numbers which may repeat, see the example below. 
// Input
// •	The input will come from the console as a single integer N
// Output
// •	The output is the ways you can get that number N by summing other numbers. See the examples below.
// Constraints
// •	The input numbers will be valid integer in the range [0…100]
// Input
// 3
// Output
// 3
// 2 + 1
// 1 + 1 + 1
// Input
// 5
// Output
// 5
// 4 + 1
// 3 + 2
// 3 + 1 + 1
// 2 + 2 + 1
// 2 + 1 + 1 + 1
// 1 + 1 + 1 + 1 + 1
// Use buffered reader to read the input from the console and buffered writer to write the output.
// Use dynamic programming to solve the problem.
//use this code - store each line of result in current list as buffer - 
// sort each line like this - minimum number of elements to maximum number of elements in line; in each line first shold be largest of the elements.

// read input from the console with buffered reader
//print N on the console with buffered writer
// print N-1 +1 on the console with buffered writer
// print (N-1)-1 + N-1 + 1 on the console with buffered writer

import java.io.BufferedReader;  
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;  
import java.util.Comparator;
import java.util.List;

public class Numbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BigInteger inputNumber = new BigInteger(reader.readLine());
        List<List<BigInteger>> numberCombinations = generateCombinations(inputNumber);
        
        // Sort the combinations by size in ascending order and then reverse the order of elements in each combination
        Collections.sort(numberCombinations, Comparator.comparingInt(List::size)); 
        numberCombinations.forEach(Collections::reverse);  
        
        printCombinations(numberCombinations);
    }

    private static List<List<BigInteger>> generateCombinations(BigInteger inputNumber) {
        List<List<BigInteger>> combinations = new ArrayList<>();
        List<BigInteger> currentCombination = new ArrayList<>();
        generateCombinations(inputNumber, combinations, currentCombination, BigInteger.ONE, inputNumber);
        return combinations;
    }

    private static void generateCombinations(BigInteger inputNumber, List<List<BigInteger>> combinations, 
                                            List<BigInteger> currentCombination, BigInteger startValue, 
                                            BigInteger remainingSum) {
        if (remainingSum.compareTo(BigInteger.ZERO) < 0) return;
        if (remainingSum.equals(BigInteger.ZERO)) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (BigInteger i = startValue; i.compareTo(inputNumber) <= 0; i = i.add(BigInteger.ONE)) {
            currentCombination.add(i);
            generateCombinations(inputNumber, combinations, currentCombination, i, remainingSum.subtract(i));
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    private static void printCombinations(List<List<BigInteger>> combinations) {
        for (List<BigInteger> combination : combinations) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < combination.size(); i++) {
                if (i != 0) sb.append(" + ");
                sb.append(combination.get(i));
            }
            System.out.println(sb.toString());
        }
    }
}
