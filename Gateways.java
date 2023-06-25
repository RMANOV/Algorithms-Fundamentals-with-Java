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
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Gateways {
    public static void main(String[] args) throws IOException {
        // Create a BufferedReader object to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Read the number of gateways from the console
        int gateways = Integer.parseInt(reader.readLine());
        // Check if the number of gateways is valid
        if (gateways <= 0) {
            // System.out.println("Invalid number of gateways. It must be a positive integer.");
            return;
        }

        // Read the number of connections from the console
        int connections = Integer.parseInt(reader.readLine());
        // Check if the number of connections is valid
        if (connections < 0) {
            // System.out.println("Invalid number of connections. It cannot be negative.");
            return;
        }

        // Create a matrix to store the connections between the gateways
        int[][] matrix = new int[gateways][gateways];
        // Read the connections from the console and store them in the matrix
        for (int i = 0; i < connections; i++) {
            String[] input = reader.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);

            // Check if the gateway numbers are valid
            if (from <= 0 || to <= 0 || from > gateways || to > gateways) {
                // System.out.println("Invalid gateway number. It should be between 1 and " + gateways);
                return;
            }

            // Store the connection in the matrix
            matrix[from - 1][to - 1] = 1;
            matrix[to - 1][from - 1] = 1;
        }

        // Read the start and end gateways from the console
        int start = Integer.parseInt(reader.readLine());
        int end = Integer.parseInt(reader.readLine());

        // Check if the start and end gateways are valid
        if (start <= 0 || end <= 0 || start > gateways || end > gateways) {
            // System.out.println("Invalid start or end gateway. It should be between 1 and " + gateways);
            return;
        }

        // Create an array to store the parent of each gateway
        int[] parents = new int[gateways];
        // Create a boolean array to keep track of visited gateways
        boolean[] visited = new boolean[gateways];
        // Initialize the parent array with -1
        for (int i = 0; i < gateways; i++) {
            parents[i] = -1;
        }

        // Create a queue to perform a breadth-first search
        Queue<Integer> queue = new LinkedList<>();
        // Add the start gateway to the queue
        queue.add(start - 1);
        // Mark the start gateway as visited
        visited[start - 1] = true;
        // Set the parent of the start gateway to -1
        parents[start - 1] = -1;

        // Perform a breadth-first search
        while (!queue.isEmpty()) {
            // Get the next gateway from the queue
            int current = queue.poll();
            // Check all the gateways connected to the current gateway
            for (int i = 0; i < gateways; i++) {
                if (matrix[current][i] == 1 && !visited[i]) {
                    // If the gateway is connected to the current gateway and has not been visited
                    // yet,
                    // add it to the queue, mark it as visited, and set its parent to the current
                    // gateway
                    queue.add(i);
                    visited[i] = true;
                    parents[i] = current;
                }
            }
        }

        // If there is no path from the start to the end gateway, print "no path"
        if (parents[end - 1] == -1) {
            // System.out.println("no path");
        } else {
            // Otherwise, create a stack to store the path from the end to the start gateway
            Stack<Integer> stack = new Stack<>();
            int current = end - 1;
            // Traverse the parent array from the end gateway to the start gateway and push
            // the gateways onto the stack
            while (current != -1) {
                stack.push(current + 1);
                current = parents[current];
            }
            // Pop the gateways from the stack and print them to the console
            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + " ");
            }
        }
    }
}
