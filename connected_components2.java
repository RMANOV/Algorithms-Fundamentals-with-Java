// Connected Components
// The first part of this lab aims to implement the DFS algorithm (Depth-First-Search) to traverse a graph and find its
// connected components(nodes connected to each other either directly, or through other nodes). The graph nodes are
// numbered from 0 to n-1. The graph comes from the console in the following format:
// • First line: number of lines n
// • Next n lines: list of child nodes for the nodes 0 … n-1 (separated by a space)
// Print the connected components in the same format as in the examples below:
// • First line: number of connected components
// • Next lines: list of nodes in each connected component (separated by a space)
// Example 1:
// Input:
// 9
// 3 6
// 3 4 5 6
// 8
// 0 1 5
// 1 6
// 1 3
// 0 1 4
// 2
// Output:
// Connected component: 6 4 5 1 3 0
// Connected component: 8 2
// Connected component: 7






import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import java.util.*;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // read the number of nodes in the graph
        List<List<Integer>> graph = new ArrayList<>(); // create an empty list to store the graph
        for (int i = 0; i < n; i++) { // iterate over each node in the graph
            List<Integer> children = new ArrayList<>(); // create an empty list to store the children of the current node
            while (scanner.hasNextInt()) { // read the children of the current node until there are no more integers
                int child = scanner.nextInt(); // read the next child of the current node
                if (child == -1) { // if the child is -1, then there are no more children for the current node
                    break; // exit the loop
                }
                children.add(child); // add the child to the list of children for the current node
            }
            graph.add(children); // add the list of children for the current node to the graph
        }
        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph); // find the connected components of the graph
        System.out.println("Number of connected components: " + connectedComponents.size()); // print the number of connected components
        for (Deque<Integer> component : connectedComponents) { // iterate over each connected component
            System.out.print("Connected component: "); // print the label for the connected component
            for (int node : component) { // iterate over each node in the connected component
                System.out.print(node + " "); // print the node
            }
            System.out.println(); // print a new line
        }
    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> connectedComponents = new ArrayList<>(); // create an empty list to store the connected components
        Set<Integer> visited = new HashSet<>(); // create an empty set to store the visited nodes
        for (int i = 0; i < graph.size(); i++) { // iterate over each node in the graph
            if (!visited.contains(i)) { // if the node has not been visited
                Deque<Integer> component = new ArrayDeque<>(); // create an empty deque to store the nodes in the connected component
                dfs(i, graph, visited, component); // find the nodes in the connected component using DFS
                connectedComponents.add(component); // add the connected component to the list of connected components
            }
        }
        return connectedComponents; // return the list of connected components
    }

    private static void dfs(int node, List<List<Integer>> graph, Set<Integer> visited, Deque<Integer> component) {
        visited.add(node); // mark the node as visited
        component.add(node); // add the node to the connected component
        for (int child : graph.get(node)) { // iterate over each child of the node
            if (!visited.contains(child)) { // if the child has not been visited
                dfs(child, graph, visited, component); // recursively find the nodes in the connected component
            }
        }
    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        throw new AssertionError("Not Implemented"); // throw an error because this method is not implemented
    }
}
