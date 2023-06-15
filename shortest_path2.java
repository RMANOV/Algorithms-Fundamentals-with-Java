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




import java.util.Scanner;


public class ShortestPath {
    public static void main(String[] args) {
        // Step 2: Read input from the console
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int e = scanner.nextInt();

        // Step 3: Create a graph using adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Step 4: Add edges to the graph
        for (int i = 0; i < e; i++) {
            int source = scanner.nextInt() - 1;
            int destination = scanner.nextInt() - 1;

            graph.get(source).add(destination);
            graph.get(destination).add(source);
        }

        // Step 5: Read the start and end nodes
        int source = scanner.nextInt() - 1;
        int destination = scanner.nextInt() - 1;

        // Step 6: Initialize visited and prevNodes arrays
        boolean[] visited = new boolean[n];
        int[] prevNodes = new int[n];
        Arrays.fill(prevNodes, -1);

        // Step 7: Find the shortest path using BFS
        bfs(graph, visited, prevNodes, source, destination);

        // Step 8: Print the result
        if (!visited[destination]) {
            System.out.println("No path found.");
        } else {
            List<Integer> path = new ArrayList<>();
            int current = destination;

            while (current != -1) {
                path.add(current + 1);
                current = prevNodes[current];
            }

            Collections.reverse(path);

            System.out.println("Shortest path length is: " + (path.size() - 1));
            System.out.println("Path: " + path);
        }
    }

    // BFS algorithm to find the shortest path
    private static void bfs(List<List<Integer>> graph, boolean[] visited, int[] prevNodes, int source, int destination) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == destination) {
                return;
            }

            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    prevNodes[neighbor] = current;
                    queue.add(neighbor);
                }
            }
        }
    }
}
