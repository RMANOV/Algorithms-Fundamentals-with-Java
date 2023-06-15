// Shortest Path
// You will be given a graph from the console  - your task is to find the shortest path and print it back on the console. The
// first line will be the number of Nodes - N the second one the number of Edges - E, then on each E line the edge in form
// {destination} – {source}. On the last two lines, you will read the start node and the end node.
// Print on the first line the length of the shortest path and on the second the path itself, see the examples below.
// Input
// 11
// 11
// 1 5
// 1 4
// 5 7
// 7 8
// 8 2
// 2 3
// 3 4
// 4 1
// 6 2
// 9 10
// 11 9
// 6
// 3
// Output
// Shortest path length is: 2
// 6 2 3

// Define two arrays - visited to keep track of visited nodes and prevNodes to store previous node in path.
// Read number of nodes n and edges e from input.
// Create an adjacency list representation of the graph. This is a list of lists, where graph.get(i) gives the list of neighbors of node i.
// Read source and destination nodes.
// Initialize the visited and prevNodes arrays.
// Call the BFS method by passing the graph, source and destination.
// In the BFS method, do the following:
// Add the source node to a queue.
// Mark it visited.
// While the queue is not empty:
// -- Pop a node from the queue.
// -- If it is the destination, return from the method.
// -- Else loop through its neighbors.
// -- If a neighbor is unvisited, mark it visited, set its previous node, and add it to the queue.
// Construct the path by backtracking from the destination using the prevNodes array.
// Print the path length and the actual path.



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ShortestPath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of nodes n and edges e from input.
        int n = scanner.nextInt();
        int e = scanner.nextInt();

        // Create an adjacency list representation of the graph. This is a list of lists, where graph.get(i) gives the list of neighbors of node i.
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Read edges and add them to the graph.
        for (int i = 0; i < e; i++) {
            int source = scanner.nextInt() - 1;
            int destination = scanner.nextInt() - 1;

            graph.get(source).add(destination);
            graph.get(destination).add(source);
        }

        // Read source and destination nodes.
        int source = scanner.nextInt() - 1;
        int destination = scanner.nextInt() - 1;

        // Initialize the visited and prevNodes arrays.
        int[] prevNodes = new int[n];
        boolean[] visited = new boolean[n];

        // Call the BFS method by passing the graph, source and destination.
        bfs(graph, source, destination, prevNodes, visited);

        // If there is no path from source to destination, print "No path found."
        if (!visited[destination]) {
            System.out.println("No path found.");
        } else {
            // Construct the path by backtracking from the destination using the prevNodes array.
            List<Integer> path = new ArrayList<>();
            int current = destination;
            while (current != source) {
                path.add(current);
                current = prevNodes[current];
            }
            path.add(source);

            // Print the path length and the actual path.
            System.out.println("Shortest path length is: " + (path.size() - 1));
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + 1 + " ");
            }
        }
    }

    // BFS method to find the shortest path from source to destination.
    private static void bfs(List<List<Integer>> graph, int source, int destination, int[] prevNodes, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == destination) {
                return;
            }

            // Loop through the neighbors of the current node.
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    // If a neighbor is unvisited, mark it visited, set its previous node, and add it to the queue.
                    visited[neighbor] = true;
                    prevNodes[neighbor] = current;
                    queue.offer(neighbor);
                }
            }
        }
    }
}
