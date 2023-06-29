// Path Finder
// Write a program to check if a given path is existing in a graph.
// Input
// •	You will receive an integer – n – number of nodes in a graph.
// o	The graph nodes are numbered from 0 to n - 1.
// •	On the next n lines, you will receive a list of children for the nodes 0 … n - 1 (separated by a space).
// •	On the next line you will receive an integer – p – number of paths to check.
// •	On the next p lines, you will receive a path of nodes (separated by a space).
// Output
// •	For each path print either "yes" – if the path exists, or "no" if the path does not exist.
// Constraints
// •	Path will always contain at least 2 nodes.
// •	Nodes in the path will always be in the range [0… n – 1].
// Input
// 7
// 3 6
// 4 5

// 1

// 1 2
// 3
// 0 3 1 5
// 0 3 1 5 6
// 0 6 2
// Output
// yes
// yes
// no
// Explanation - The first path is 0 -> 3 -> 1 -> 5. The second path is 0 -> 3 -> 1 -> 5 -> 6. The third path is 0 -> 6 -> 2.
// Use the depth-first search algorithm
// Use bufferedReader to read the input from the console and print the output on the console
// Use BigInteger to store the input values


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class PathFinder {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        // Skip empty lines
        do {
            line = reader.readLine();
        } while (line != null && line.isEmpty());

        if (line == null) {
            return;  // No input
        }

        // Read the number of nodes
        int numberOfNodes = Integer.parseInt(line);
        // Initialize the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numberOfNodes; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Read the graph
        for (int i = 0; i < numberOfNodes; i++) {
            line = reader.readLine();
            // Split the line by whitespace and parse the integers
            List<Integer> children = line.isEmpty() ? new ArrayList<>() 
                                                    : Arrays.stream(line.split("\\s+"))
                                                            .map(Integer::parseInt)
                                                            .collect(Collectors.toList());
            // Add the children to the current node in the graph
            graph.get(i).addAll(children);
        }

        // Skip empty lines
        do {
            line = reader.readLine();
        } while (line != null && line.isEmpty());

        if (line == null) {
            return;  // No input
        }

        // Read the number of paths
        int numberOfPaths = Integer.parseInt(line);
        // Check each path
        for (int i = 0; i < numberOfPaths; i++) {
            line = reader.readLine();
            if (line == null || line.isEmpty()) {
                continue;  // Skip empty lines or end of input
            }

            // Split the line by whitespace and parse the integers
            List<Integer> path = Arrays.stream(line.split("\\s+"))
                                       .map(Integer::parseInt)
                                       .collect(Collectors.toList());
            // Check if the path exists
            System.out.println(checkPath(path, graph) ? "yes" : "no");
        }
    }

    // Check if a path exists in the graph
    private static boolean checkPath(List<Integer> path, Map<Integer, List<Integer>> graph) {
        for (int i = 0; i < path.size() - 1; i++) {
            // Check if there is a path between each pair of adjacent nodes in the path
            if (!DFS(path.get(i), path.get(i + 1), graph)) {
                return false;
            }
        }
        return true;
    }

    // Depth-first search algorithm to check if there is a path between two nodes
    private static boolean DFS(int start, int target, Map<Integer, List<Integer>> graph) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        stack.push(start);
        visited.add(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (current == target) {
                return true;
            }
            for (int child : graph.get(current)) {
                if (!visited.contains(child)) {
                    stack.push(child);
                    visited.add(child);
                }
            }
        }
        return false;
    }
}

// Commit message - Read input - including empty lines, store the graph and check if a path exists in the graph - dfs algorithm.