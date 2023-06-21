// Road Reconstruction
// You have to rebuild some roads in your city. Write a program that finds all
// the roads without which buildings in the
// city will become unreachable. You will receive how many buildings the town
// has on the first line, then you will
// receive the number of streets and finally, for each street, you will receive
// which buildings it connects. Find all the
// streets that are important and cannot be removed and print them as shown in
// the examples.
// Input
// • On the first line, you will receive the amount of the buildings.
// • On the second line, you will receive the amount of the streets (n).
// • On the next "n" lines you will receive which buildings each street
// connects.
// Output
// • On the first line print: "Important streets:"
// • On the next lines (if any) print the street in the format: "{firstBuilding}
// {secondBuilding}"
// • The order of the output does not matter as long as you print all of the
// important streets.
// Expamle input
// 5
// 5
// 1 - 0
// 0 - 2
// 2 - 1
// 0 - 3
// 3 - 4
// Example output
// Important streets:
// 3 4
// 0 3

//use bufered reader for input



import java.util.*;
import java.io.*;

public class Road_Reconstructions {
    static int time = 0;
    static class Edge {
        int node1;
        int node2;
        Edge(int node1, int node2) {
            this.node1 = node1;
            this.node2 = node2;
        }
    }
    // Implementing Tarjan's algorithm for finding bridge edges in a graph as suggested before. 
    // This algorithm runs in linear time and correctly identifies all edges whose removal would increase the number of connected components in the graph.
    // A recursive function that finds and prints bridges using DFS traversal
    private static void bridgeUtil(int u, boolean visited[], int disc[], int low[], int parent[], List<Integer> adj[]) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        // Go through all vertices adjacent to this
        for (int v : adj[u]) {
            // If v is not visited yet, then recur for it
            if (!visited[v]) {
                parent[v] = u;
                bridgeUtil(v, visited, disc, low, parent, adj);
                // Check if the subtree rooted with v has a connection to one of the ancestors of u
                low[u] = Math.min(low[u], low[v]);
                // If the lowest vertex reachable from subtree under v is below u in DFS tree, then u-v is a bridge
                if (low[v] > disc[u])
                    System.out.println(u + " " + v);
            } else if (v != parent[u])
                // Update low value of u for parent function calls.
                low[u] = Math.min(low[u], disc[v]);
        }
    }
    
    // DFS based function to find all bridges. It uses recursive function bridgeUtil()
    private static void bridge(List<Integer> adj[], int V) {
        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        int parent[] = new int[V];
        // Initialize parent and visited arrays
        for (int i = 0; i < V; i++) {
            parent[i] = -1;
            visited[i] = false;
        }
        // Call the recursive helper function for all vertices
        for (int i = 0; i < V; i++)
            if (!visited[i])
                bridgeUtil(i, visited, disc, low, parent, adj);
    }
    
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int buildings = Integer.parseInt(reader.readLine());
        int streets = Integer.parseInt(reader.readLine());
        List<Integer> adj[] = new ArrayList[buildings];
        // Create an adjacency list for each building
        for (int i = 0; i < buildings; i++)
            adj[i] = new ArrayList<>();
        // Add edges to the adjacency list
        for (int i = 0; i < streets; i++) {
            String[] input = reader.readLine().split(" - ");
            int building1 = Integer.parseInt(input[0]);
            int building2 = Integer.parseInt(input[1]);
            adj[building1].add(building2);
            adj[building2].add(building1);
        }
        // Print the header
        System.out.println("Important streets:");
        // Find and print all bridges
        bridge(adj, buildings);
    }
}
