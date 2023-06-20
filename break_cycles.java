// Break Cycles
// You are given an undirected multi-graph. Remove a minimal number of edges to
// make the graph acyclic (to break all
// cycles). As a result, print the number of edges removed and the removed
// edges. If several edges can be removed to
// break a certain cycle, remove the smallest of them in alphabetical order
// (smallest start vertex in alphabetical order
// and smallest end vertex in alphabetical order).
// Hint
// • Enumerate edges {s, e} in alphabetical order. For each edge {s, e} check whether it closes a cycle. If yes -
// remove it.
// o To check whether an edge {s, e} closes a cycle, temporarily remove the edge {s, e} and then try to find
// a path from s to e using DFS or BFS.
// o If you find a path from s to e, then the edge {s, e} closes a cycle.
// Input
// 1 -> 2 5 4
// 2 -> 1 3
// 3 -> 2 5
// 4 -> 1
// 5 -> 1 3
// 6 -> 7 8
// 7 -> 6 8
// 8 -> 6 7
// Output
// Edges to remove: 2
// 1 - 2
// 6 - 7

import java.util.*;

public class break_cycles {
    private static List<String> removedEdges = new ArrayList<>(); // create a list to store the removed edges

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Set<String>> graph = new HashMap<>(); // create a HashMap to store the graph
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) {
                break;  // if an empty line is encountered, break the loop
            }
            String[] nodes = line.split(" -> "); // split the line into parent and children nodes
            String node = nodes[0]; // get the parent node
            String[] children = nodes[1].split(" "); // get the children nodes
            graph.putIfAbsent(node, new HashSet<>()); // add the parent node to the graph if it doesn't exist
            for (String child : children) { // add each child node to the parent node's set of children
                graph.get(node).add(child);
                graph.putIfAbsent(child, new HashSet<>()); // ensure that every child node is present as a key in the graph map
            }
        }
        for (String node : graph.keySet()) {
            Set<String> children = graph.get(node);
            Set<String> sortedChildren = new TreeSet<>(children); // sort children of each node
            graph.put(node, sortedChildren);
        }
        int edgesToRemove = 0;
        while (true) {
            Set<String> cycle = findCycle(graph);
            if (cycle == null) {
                break; // no more cycles
            }
            String nodeToRemove = null;
            int minEdges = Integer.MAX_VALUE;
            for (String node : cycle) {
                int edges = countEdgesInCycle(graph, cycle, node);
                if (edges == 1) {
                    nodeToRemove = node;
                    break;
                } else if (edges < minEdges) {
                    nodeToRemove = node;
                    minEdges = edges;
                }
            }
            if (nodeToRemove != null) {
                removeEdgeFromCycle(graph, cycle, nodeToRemove);
                edgesToRemove++;
            }
        }
        System.out.println("Edges to remove: " + edgesToRemove); // print the number of removed edges
        removedEdges.stream().sorted().forEach(System.out::println); // print the removed edges in alphabetical order
        scanner.close(); // close the scanner to prevent resource leak
    }

    private static Set<String> findCycle(Map<String, Set<String>> graph) {
        Set<String> visited = new HashSet<>();
        Set<String> cycle = new HashSet<>();
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (dfs(graph, visited, cycle, null, node)) {
                    return cycle;
                }
            }
        }
        return null;
    }

    private static boolean dfs(Map<String, Set<String>> graph, Set<String> visited, Set<String> cycle, String parent, String node) {
        visited.add(node);
        cycle.add(node);
        for (String child : graph.get(node)) {
            if (child.equals(parent)) {
                continue; // don't go back to parent
            }
            if (cycle.contains(child)) { // if child is visited, we found a cycle
                return true;
            }
            if (dfs(graph, visited, cycle, node, child)) { // recursively visit child
                return true;
            }
        }
        cycle.remove(node); // backtrack
        return false;
    }

    private static int countEdgesInCycle(Map<String, Set<String>> graph, Set<String> cycle, String node) {
        int count = 0;
        for (String child : graph.get(node)) {
            if (cycle.contains(child)) {
                count++;
            }
        }
        return count;
    }

    private static void removeEdgeFromCycle(Map<String, Set<String>> graph, Set<String> cycle, String nodeToRemove) {
        String parent = null;
        for (String child : graph.get(nodeToRemove)) {
            if (cycle.contains(child)) {
                parent = child;
                break;
            }
        }
        if (parent != null) {
            removedEdges.add(parent.compareTo(nodeToRemove) < 0 ? parent + " - " + nodeToRemove : nodeToRemove + " - " + parent);
            graph.get(parent).remove(nodeToRemove); // remove this edge
            graph.get(nodeToRemove).remove(parent); // remove this edge
        }
    }
}
