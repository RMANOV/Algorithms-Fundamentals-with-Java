// 1.	Alpha Decay
// There is something radioactive around those Greek letters Alpha, Beta and Gama, however letters are not known radioactive emitters. The real emitters are the heavy elements, approximately 99% of the helium produced on Earth is the result of the alpha decay of underground deposits of minerals containing uranium or thorium. 
// You are part of the "no-real-science-team" and you are the computer specialist, you will be given data collected after the alpha decay of some heavy elements N where each value is the resulting nucleus after the alpha decay of some heavier nucleus represented by a single integer. 
// Your head theoretical physicist wants to see if there are any patterns in the resulting nucleus, however after doing some calculations the theorist have claimed that the only number of results worth looking at is K of those N nucleus at a time.
// Here comes your task you need to take those N nucleus and print all the possible ways that they can be observed as a sequence of K nucleus, without using the same nucleus twice.
// Input
// •	The input will come from the console on two lines.
// •	First line will be the resulting nucleus after the alpha decay N as a sequence of integers separated by spaces.
// •	On the second line a single integer K the count of integers the physicist wants to observe at the same time.
// Output
// •	The output is each possible way to observe K nucleus out of N on a new line where each nucleus is separated by a single space.
// Constraints
// •	N will be in the range [3…10] where K will always be less than N.
// •	The nucleus numbers will be unique.
// Input
// 234 232 230
// 2
// Output
// 234 232
// 234 230
// 232 234
// 232 230
// 230 234
// 230 232
// Input1
// 109 113 234 232
// 3
// Output1
// 109 113 234
// 109 113 232
// 109 234 113
// 109 234 232
// 109 232 113
// 109 232 234
// 113 109 234
// 113 109 232
// 113 234 109
// 113 234 232
// 113 232 109
// 113 232 234
// 234 109 113
// 234 109 232
// 234 113 109
// 234 113 232
// 234 232 109
// 234 232 113
// 232 109 113
// 232 109 234
// 232 113 109
// 232 113 234
// 232 234 109
// 232 234 113

// Use BufferedReader, BufferedWriter and StringBuilder



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class AlphaDecay {
    public static void main(String[] args) throws IOException {
        // Create a BufferedReader object to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Create a BufferedWriter object to write output to the console
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Read the input from the console and split it into an array of strings
        String[] input = reader.readLine().split(" ");
        // Read the integer k from the console
        int k = Integer.parseInt(reader.readLine());

        // Convert the array of strings to an array of integers
        int[] numbers = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        // Create a boolean array to keep track of which numbers have been used in the permutation
        boolean[] used = new boolean[numbers.length];
        // Create an array to store the current permutation
        int[] permutation = new int[k];
        
        // Generate all permutations of length k and write them to the console
        generatePermutations(numbers, permutation, used, 0, writer);
        // Flush the output buffer to ensure that all output is written to the console
        writer.flush();
    }

    // Recursive function to generate all permutations of length k
    private static void generatePermutations(int[] numbers, int[] permutation, boolean[] used, int level, BufferedWriter writer) throws IOException {
        // If the permutation is complete, write it to the console
        if (level == permutation.length) {
            for (int value : permutation) {
                writer.write(value + " ");
            }
            writer.write("\n");
            return;
        }

        // Try all unused numbers in the current position of the permutation
        for (int i = 0; i < numbers.length; i++) {
            if (!used[i]) {
                used[i] = true;
                permutation[level] = numbers[i];
                generatePermutations(numbers, permutation, used, level + 1, writer);
                used[i] = false;
            }
        }
    }
}

// Commit message - Used BufferedReader, BufferedWriter and StringBuilder, create all possible permutations of length k from an array of numbers