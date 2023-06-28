// Guards
// The Ankh Morpork guards need your help there has been a flood in the city and some outposts can no longer be reached from a specific starting point your task is to report those.
// You will be given the map of the outposts, as a graph from the console with a given start node, print all the unreachable nodes in order by their number ascending.
// 1.	Input
// •	The input will come from the console on multiple lines:
// o	first line N single integer – the number of nodes
// o	second line M single integer – the number of edges
// o	M lines describing the edges connections in the format: {from} {to}
// o	S single integer – the start node
// 2.	Output
// •	The output is a sequence of integers that represents the unreachable outposts.
// 3.	Constraints
// •	The input graph will not contain more than 30 nodes or edges.
// Input
// 6
// 5
// 1 2
// 2 4
// 3 4
// 6 5
// 6 4
// 1
// Output
// 3 5 6

import java.util.*;

public class Guards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodes = Integer.parseInt(scanner.nextLine()); // read the number of nodes
        int edges = Integer.parseInt(scanner.nextLine()); // read the number of edges
        Map<Integer, List<Integer>> graph = new HashMap<>(); // create a graph as a map
        for (int i = 0; i < edges; i++) {
            String[] edge = scanner.nextLine().split(" "); // read each edge
            int from = Integer.parseInt(edge[0]); // get the starting node of the edge
            int to = Integer.parseInt(edge[1]); // get the ending node of the edge
            graph.putIfAbsent(from, new ArrayList<>()); // add the starting node to the graph if it doesn't exist
            graph.get(from).add(to); // add the ending node to the list of edges for the starting node
        }
        int startNode = Integer.parseInt(scanner.nextLine()); // read the start node
        Set<Integer> visited = new HashSet<>(); // create a set to store visited nodes
        visited.add(startNode); // add the start node to the visited set
        dfs(graph, startNode, visited); // perform depth-first search on the graph starting from the start node
        for (int i = 1; i <= nodes; i++) { // loop through all nodes
            if (!visited.contains(i)) { // if the node is not visited
                System.out.print(i + " "); // print the node
            }
        }
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int startNode, Set<Integer> visited) {
        if (graph.containsKey(startNode)) { // if the start node is in the graph
            for (Integer child : graph.get(startNode)) { // loop through all edges of the start node
                if (!visited.contains(child)) { // if the child node is not visited
                    visited.add(child); // add the child node to the visited set
                    dfs(graph, child, visited); // perform depth-first search on the child node
                }
            }
        }
    }
}

// Commit message: 
// Read the input, create a graph, perform depth-first search on the graph starting from the start node and print the unreachable nodes.