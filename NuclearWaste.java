// Nuclear Waste
// The way it works is simple any fuel we use to produce energy has some need of disposal solution, for the unused waste. For example we think it is a "good idea" to dispose the carbon dioxide straight in to the atmosphere, when burning coal or any other fossil fuel.
// It is a bit different when a nuclear power plant needs to replace the fuel inside a reactor or the cooling rods, the old one become waste. They are highly radioactive and require much more complex process which often includes transporting the radioactive materials to disposal facilities often far away from the power plants and somewhere deep underground. 
// Still business is business and you are looking for the cheapest possible way to transport the nuclear waste to some of the facilities. The transport company offers to transport flasks of waste at different cost for the different count of flasks. You will be given this information as a sequence of integers as an example:
// 12 20 30 40 means that 1 flask transport costs 12, two costs 20, three costs 30 etc.
// You will also be given N the number of flasks you want to transport.
// Input
// The input will come from the console on two lines.
// •	On the first line the sequence representing the cost of the flasks transport.
// •	On the second line single integer N the number of flasks you need to transport.
// Output
// •	First print the minimum cost for all the flasks transport. In the format: "Cost: {minimumCost}"
// •	Then print the optima setup as the count of flasks and the corresponding cost.
// •	"{flasksCount} => {cost}"
// •	Note: the flasks should be printed in increasing order by count of flasks transported NOT by cost.
// Constraints
// •	All input lines will be valid integers you do not need to check that.
// •	The range of the integers will be [1…10000]
// Input
// 12 21 31 43 79
// 10
// Output
// Cost: 104
// 2 => 21
// 2 => 21
// 3 => 31
// 3 => 31
// Input1
// 12 21 31 40 49 58 69 79 90 101
// 15
// Output1
// Cost: 147
// 3 => 31
// 6 => 58
// 6 => 58

// Use BufferedReader, BufferedWriter and StringBuilder
// Use dynamic programming

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class NuclearWaste {
    public static void main(String[] args) throws IOException {
        // Create a BufferedReader object to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Read the input sequence of integers representing the cost of the flasks
        // transport
        String[] inputCosts = reader.readLine().split(" ");
        int[] costs = new int[inputCosts.length];
        for (int i = 0; i < inputCosts.length; i++) {
            costs[i] = Integer.parseInt(inputCosts[i]);
        }

        // Read the number of flasks to be transported
        int n = Integer.parseInt(reader.readLine());

        // Create two arrays to store the minimum cost and the corresponding count of
        // flasks for each number of flasks up to n
        int[] minCosts = new int[n + 1];
        int[] minCounts = new int[n + 1];

        // For each number of flasks up to n, find the minimum cost and the
        // corresponding count of flasks
        for (int i = 1; i <= n; i++) {
            // Initialize the minimum cost to the maximum possible value
            minCosts[i] = Integer.MAX_VALUE;

            // For each possible count of flasks, calculate the cost and update the minimum
            // cost and the corresponding count of flasks if necessary
            for (int j = 0; j < costs.length; j++) {
                if (i - j - 1 >= 0 && minCosts[i - j - 1] + costs[j] < minCosts[i]) {
                    minCosts[i] = minCosts[i - j - 1] + costs[j];
                    minCounts[i] = j + 1;
                }
            }
        }

        // Create a StringBuilder object to store the output
        StringBuilder sb = new StringBuilder();

        // Append the minimum cost to the output
        sb.append("Cost: ").append(minCosts[n]).append("\n");

        // Append the count of flasks and the corresponding cost for each step of the
        // optimal setup to the output
        while (n > 0) {
            sb.append(minCounts[n]).append(" => ").append(costs[minCounts[n] - 1]).append("\n");
            n -= minCounts[n];
        }

        // Create a BufferedWriter object to write the output to the console
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(sb.toString());
        writer.flush();
    }
}

// Commit message - "Use BufferedReader, BufferedWriter and StringBuilder. Use dynamic programming."