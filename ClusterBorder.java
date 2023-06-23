// Cluster Border
// Still after decades of space voyagers we have discovered new worlds and our species has spread all over the universe. 
// We have not forgotten our old ways and we tend to stick to them as something important and something to worship something foolish like… tradition. 
// There is a war going on between different Clusters and borders have been set, which means there is border control. 
// You are part of the Laniakea Supercluster border control IT team. 
// You have been assigned task to develop a program which maximizes the work of the administration.
// There is the way in which this works all the spaceships come in line one after the other. 
// There are two ways of processing the ships the first is to check a single ship 
// and the other is to check one ship but let two pass as long as the first guaranties for the second (which means that those two ships have to agree for that) 
// or said in a simpler way to pass as a pair, however a ship can only make an agreement with the ship before him or the ship after him in the line, 
// since reorder is not allowed.
// But here is the problem since the paperwork (yes, the public administration will still use paper even thousands of decades from now), 
// takes different amount of time for each type of ship and for each pair of ships making a deal with other ships will not always benefit in time.
// You will be given two integer sequences the first is the time for each ship to pass as single client, 
// the second one will represent the time of ships passing if they go as pair. 
// You have to find the minimum time of ships processing and display that then you need to print the order of the ships passing as numbers, not times.
// 1.	Input
// The input will come from the console on two lines.
// •	On the first line the sequence representing the time for each ship to go through.
// •	On the second line the sequence representing the time for two ships combination.
// 2.	Output
// First print the minimum time for processing all the ships. In the format: "Optimal Time: {optimalTime}"
// •	Then print the ships with their pair.
// •	If ship has no pair "Single {shipNumber}"
// •	If there is pair "Pair of {shipNumber} and {shipNumber}"
// 3.	Constraints
// •	All input lines will be valid integers you do not need to check that.
// •	The range of the sequences will be [1…1000]

import java.io.*;
import java.util.*;

public class ClusterBorder {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // Read the input sequences of times for single and pair ships
        String[] singleTimesStr = br.readLine().split("\\s+");
        String[] pairTimesStr = br.readLine().split("\\s+");

        // Convert the input sequences from strings to integers
        int[] singleTimes = new int[singleTimesStr.length];
        int[] pairTimes = new int[pairTimesStr.length];
        for (int i = 0; i < singleTimesStr.length; i++) {
            singleTimes[i] = Integer.parseInt(singleTimesStr[i]);
        }
        for (int i = 0; i < pairTimesStr.length; i++) {
            pairTimes[i] = Integer.parseInt(pairTimesStr[i]);
        }

        // Calculate the minimum time to process all the ships
        int n = singleTimes.length;
        int[] dp = new int[n+1]; // dp[i] is the minimum time to process the first i ships
        int[] prev = new int[n+1]; // prev[i] is the index of the ship that the i-th ship is paired with, or -1 if it is not paired
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1] + singleTimes[i-1]; // Process the i-th ship as a single ship
            prev[i] = -1;
            if (i > 1 && dp[i] > dp[i-2] + pairTimes[i-2]) { // Try to pair the i-th ship with the (i-1)-th ship
                dp[i] = dp[i-2] + pairTimes[i-2]; // Process the (i-1)-th and i-th ships as a pair
                prev[i] = i-2;
            }
        }

        // Print the minimum time to process all the ships
        bw.write("Optimal Time: " + dp[n]);
        bw.newLine();

        // Print the order of the ships passing as numbers, not times
        List<String> ships = new ArrayList<>();
        for (int i = n; i > 0; ) {
            if (prev[i] == -1) {
                ships.add("Single " + i); // The i-th ship is processed as a single ship
                i--;
            } else {
                ships.add("Pair of " + (i-1) + " and " + i); // The (i-1)-th and i-th ships are processed as a pair
                i -= 2;
            }
        }
        for (int i = ships.size() - 1; i >= 0; i--) {
            bw.write(ships.get(i));
            bw.newLine();
        }
        bw.flush();
    }
}
