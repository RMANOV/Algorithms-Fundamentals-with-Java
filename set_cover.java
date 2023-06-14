// Set Cover
// Write a program that finds the smallest subset of sets, which contains all elements from a given sequence.
// In the Set Cover Problem, we are given two sets - a set of sets (we’ll call it sets) and a universe (a sequence).
// The sets contain all elements from the universe and no others, however, some elements are repeated. The task is
// to find the smallest subset of sets, which contains all elements from the universe.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Define the Main class
public class Main {
    // Define the main method
    public static void main(String[] args) throws IOException {
        // Create a BufferedReader object to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Read the universe from the console input and split it into an array of
        // strings
        String[] elements = reader.readLine().substring(10).split(", ");
        // Create an integer array to hold the universe
        int[] universe = new int[elements.length];
        // Convert the elements from strings to integers and add them to the universe
        // array
        for (int i = 0; i < elements.length; i++) {
            universe[i] = Integer.parseInt(elements[i]);
        }
        // Read the number of sets from the console input
        int numberOfSets = Integer.parseInt(reader.readLine().substring(16));
        // Create a list to hold the sets
        List<int[]> sets = new ArrayList<>();
        // Loop through the sets and add them to the list
        for (int i = 0; i < numberOfSets; i++) {
            // Read the set from the console input and split it into an array of strings
            String[] setElements = reader.readLine().split(", ");
            // Create an integer array to hold the set
            int[] set = new int[setElements.length];
            // Convert the elements from strings to integers and add them to the set array
            for (int j = 0; j < setElements.length; j++) {
                set[j] = Integer.parseInt(setElements[j]);
            }
            // Add the set to the list of sets
            sets.add(set);
        }
        // Call the chooseSets method to find the smallest subset of sets that contains
        // all elements from the universe
        List<int[]> chosenSets = chooseSets(sets, universe);

        // Create a StringBuilder object to build the output string
        StringBuilder sb = new StringBuilder();
        // Add the number of sets to take to the output string
        sb.append(String.format("Sets to take (%d):%n", chosenSets.size()));
        // Loop through the chosen sets and add them to the output string
        for (int[] set : chosenSets) {
            sb.append("{ ");
            sb.append(Arrays.toString(set).replaceAll("\\[|]", ""));
            sb.append(" }").append(System.lineSeparator());
        }
        // Print the output string to the console
        System.out.println(sb);
    }

    // Define the chooseSets method to find the smallest subset of sets that
    // contains all elements from the universe
    public static List<int[]> chooseSets(List<int[]> sets, int[] universe) {
        // TODO
        // Create a list to hold the chosen sets
        List<int[]> chosenSets = new ArrayList<>();
        // Create a set to hold the elements in the universe
        Set<Integer> universeSet = new HashSet<>();

        // Add the elements from the universe to the universe set
        for (int i = 0; i < universe.length; i++) {
            universeSet.add(universe[i]);
        }

        // Loop until all elements in the universe set have been covered
        while (!universeSet.isEmpty()) {
            // Choose the set that covers the most elements in the universe set
            int[] currentSet = sets.get(0);
            int[] bestSet = currentSet;
            int bestCount = 0;
            for (int i = 0; i < sets.size(); i++) {
                int[] set = sets.get(i);
                int count = 0;
                for (int j = 0; j < set.length; j++) {
                    if (universeSet.contains(set[j])) {
                        count++;
                    }
                }
                if (count > bestCount) {
                    bestCount = count;
                    bestSet = set;
                }
            }
            // Add the chosen set to the list of chosen sets
            chosenSets.add(bestSet);
            // Remove the elements in the chosen set from the universe set
            for (int i = 0; i < bestSet.length; i++) {
                universeSet.remove(bestSet[i]);
            }
        }

        // Return the list of chosen sets
        return chosenSets;

    }
}
