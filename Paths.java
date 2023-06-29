// Paths
// Given a directed acyclic graph (DAG) of n nodes (from 0 to n – 1), find all possible paths from each node to the last (n – 1) node.
// Input
// •	On the first line you will receive an integer – n – number of nodes.
// •	On the next n lines, you will receive a list of children for the nodes 0 … n - 1 (separated by a space).
// Output
// •	Print each path on a new line.
// •	Nodes in the path should be joined by a space.
// Constraints
// •	Nodes in the path will always be in the range [0… n – 1].
// Input
// 5
// 1
// 2 3
// 3
// 4
// Output
// 0 1 2 3 4
// 0 1 3 4
// 1 2 3 4
// 1 3 4
// 2 3 4
// 3 4
// Input1
// 5
// 1 3
// 2 3
// 3 4
// 4
// Output1
// 0 1 2 3 4
// 0 1 2 4
// 0 1 3 4
// 0 3 4
// 1 2 3 4
// 1 2 4
// 1 3 4
// 2 3 4
// 2 4
// 3 4

// Use bufferedReader
// Use BigInteger
// Use sort for output - in the first line - line with the largest number of elements, each next line - with fewer elements; 
// each line - sorted in ascending order


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Paths {
    static ArrayList<Integer> graph[]; // adjacency list representation of the graph
    static ArrayList<ArrayList<Integer>> allPaths; // list of all paths from each node to the last node

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine()); // read the number of nodes

        graph = new ArrayList[n]; // initialize the graph
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>(); // initialize the adjacency list for each node
            String line = reader.readLine().trim(); // read the children of the current node
            if (!line.isEmpty()) { // if the line is not empty
                String[] input = line.split(" "); // split the line by space
                for (String s : input) {
                    graph[i].add(Integer.valueOf(s)); // add the children to the adjacency list
                }
            }
        }

        allPaths = new ArrayList<>(); // initialize the list of all paths
        for (int i = 0; i < n-1; i++) { // for each node except the last one
            ArrayList<Integer> path = new ArrayList<>(); // initialize a new path
            DFS(i, n - 1, path); // find all paths from the current node to the last node
        }

        // Sort all paths by length in descending order
        allPaths.sort((path1, path2) -> Integer.compare(path2.size(), path1.size()));

        // If the longest path does not have the smallest initial node
        if (allPaths.stream().anyMatch(path -> path.get(0) < allPaths.get(0).get(0))) {
            // Sort all paths by length in ascending order
            allPaths.sort((path1, path2) -> Integer.compare(path1.size(), path2.size()));
        }

        // Now sort by the node values
        allPaths.sort((path1, path2) -> {
            for (int i = 0; i < Math.min(path1.size(), path2.size()); i++) {
                int nodeComparison = Integer.compare(path1.get(i), path2.get(i));
                if (nodeComparison != 0) {
                    return nodeComparison;
                }
            }
            return Integer.compare(path1.size(), path2.size());
        });

        for (ArrayList<Integer> currentPath : allPaths) { // for each path
            System.out.println(currentPath.toString().replace("[","").replace("]","").replace(",", "")); // print the path without brackets and commas
        }
    }

    private static void DFS(int node, int destination, ArrayList<Integer> path) { // depth-first search algorithm to find all paths from a node to the destination
        path.add(node); // add the current node to the path
        if (node == destination) { // if the current node is the destination
            allPaths.add(new ArrayList<>(path)); // add the path to the list of all paths
        } else { // if the current node is not the destination
            for (Integer child : graph[node]) { // for each child of the current node
                DFS(child, destination, path); // find all paths from the child to the destination
            }
        }
        path.remove(path.size() - 1);  // backtrack - remove the current node from the path
    }
}

// Commit mesage - Read inputs, find all paths from each node to the last node, sort the paths in 2 stages and print them