// Gateways
// You are wrapping through space and time using different gateways that are connected with each other in some way. You will be given the gateways, their connections, your start gateway number, and your target gateway. Then the task is simple you need to print the shortest distance from the start to the target gateway and print the gateways you used in the process.
// Input
// •	The input will come from the console on multiple lines:
// o	first line N single integer – the number of gateways
// o	second line M single integer – the number of connections
// o	M lines describing the connections in the format: {from} {to}
// o	S single integer – the start gateway
// o	T the target gateway
// Output
// •	The output is a sequence of integers that represents the path from the start gateway to the target one.
// •	If there is no path found - there is no output 
// Constraints
// •	The input will not contain more than 30 gateways or connections.
// Input
// 6
// 5
// 1 2
// 2 4
// 3 4
// 6 5
// 6 4
// 1
// 4
// Output
// 1 2 4
// Input1
// 4
// 4
// 1 4
// 2 3
// 3 4
// 4 1
// 2
// 1
// Output1
// 2 3 4 1
// Use buffer reader to read the input and print the output to the console.
// Add comments at each step of the program describing the function of the code below the line.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Gateways {
    public static void main(String[] args) throws IOException {
        // Create a BufferedReader object to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Read the number of gateways from the console
        BigInteger gateways = new BigInteger(reader.readLine());
        // Check if the number of gateways is valid
        if (gateways.compareTo(BigInteger.ZERO) <= 0) {
            // System.out.println("Invalid number of gateways. It must be a positive integer.");
            return;
        }

        // Read the number of connections from the console
        BigInteger connections = new BigInteger(reader.readLine());
        // Check if the number of connections is valid
        if (connections.compareTo(BigInteger.ZERO) < 0) {
            // System.out.println("Invalid number of connections. It cannot be negative.");
            return;
        }

        // Create an adjacency list to store the connections between the gateways
        ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
        for (int i = 0; i < gateways.intValue(); i++) {
            adjList.add(new ArrayList<>());
        }

        // Read the connections from the console and store them in the adjacency list
        for (int i = 0; i < connections.intValue(); i++) {
            String[] input = reader.readLine().split(" ");
            int from = Integer.parseInt(input[0]) - 1;
            int to = Integer.parseInt(input[1]) - 1;
            int weight = 1;

            // Check if the gateway numbers are valid
            if (from < 0 || to < 0 || from >= gateways.intValue() || to >= gateways.intValue()) {
                // System.out.println("Invalid gateway number. It should be between 1 and " + gateways);
                return;
            }

            // Add the connection to the adjacency list
            adjList.get(from).add(new Edge(to, weight));
            adjList.get(to).add(new Edge(from, weight));
        }

        // Read the start and end gateways from the console
        int start = Integer.parseInt(reader.readLine()) - 1;
        int end = Integer.parseInt(reader.readLine()) - 1;

        // Check if the start and end gateways are valid
        if (start < 0 || end < 0 || start >= gateways.intValue() || end >= gateways.intValue()) {
            // System.out.println("Invalid start or end gateway. It should be between 1 and " + gateways);
            return;
        }

        // Create an array to store the distances from the start gateway to each gateway
        BigInteger[] distances = new BigInteger[gateways.intValue()];
        Arrays.fill(distances, BigInteger.valueOf(Integer.MAX_VALUE));
        distances[start] = BigInteger.ZERO;

        // Create a priority queue to store the gateways to be processed
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, BigInteger.ZERO));

        // Perform Dijkstra's algorithm
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.u;
            BigInteger dist = node.dist;

            if (dist.compareTo(distances[u]) > 0) {
                continue;
            }

            for (Edge edge : adjList.get(u)) {
                int v = edge.v;
                int weight = edge.weight;

                if (distances[u].add(BigInteger.valueOf(weight)).compareTo(distances[v]) < 0) {
                    distances[v] = distances[u].add(BigInteger.valueOf(weight));
                    pq.add(new Node(v, distances[v]));
                }
            }
        }

        // If there is no path from the start to the end gateway, print "no path"
        if (distances[end].equals(BigInteger.valueOf(Integer.MAX_VALUE))) {
            return;
            // System.out.println("no path");
        } else {
            // Otherwise, create an array to store the path from the start to the end gateway
            ArrayList<Integer> path = new ArrayList<>();
            int current = end;
            // Traverse the distances array from the end gateway to the start gateway and add
            // the gateways to the path
            while (current != start) {
                path.add(current + 1);
                for (Edge edge : adjList.get(current)) {
                    int v = edge.v;
                    int weight = edge.weight;
                    if (distances[v].add(BigInteger.valueOf(weight)).equals(distances[current])) {
                        current = v;
                        break;
                    }
                }
            }
            path.add(start + 1);

            // Reverse the path and print it to the console
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + " ");
            }
        }
    }

    static class Edge {
        int v;
        int weight;

        public Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int u;
        BigInteger dist;

        public Node(int u, BigInteger dist) {
            this.u = u;
            this.dist = dist;
        }

        public int compareTo(Node other) {
            return dist.compareTo(other.dist);
        }
    }
}
