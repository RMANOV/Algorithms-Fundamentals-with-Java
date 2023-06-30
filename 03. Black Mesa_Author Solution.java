import java.util.*;

public class BlackMesa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // read the number of nodes and edges
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        // create an adjacency matrix to represent the graph
        int[][] graph = new int[n + 1][n + 1];

        // read the edges and mark them in the adjacency matrix
        for (int i = 0; i < k; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph[u][v] = 1;
        }

        // read the source and destination nodes
        int source = scanner.nextInt();
        int destination = scanner.nextInt();

        // create a boolean array to keep track of visited nodes
        boolean[] visited = new boolean[n + 1];

        // create an array list to store the path from source to destination
        ArrayList<Integer> path = new ArrayList<>();

        // create a queue to perform BFS
        Deque<Integer> queue = new ArrayDeque<>();

        // add the source node to the queue
        queue.offer(source);

        // flag to check if destination node is found
        boolean isNotFound = true;

        // perform BFS
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;
            if (isNotFound) {
                path.add(node);
                isNotFound = node != destination; // if node is destination, set isNotFound to false
            }
            for (int i = 0; i < graph[node].length; i++) { // iterate through the adjacency matrix
                if (graph[node][i] != 0 && !visited[i]) { // if there is an edge and the node is not visited
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }

        // create a string builder to store the output
        StringBuilder out = new StringBuilder();

        // append the path from source to destination
        for (Integer node : path) {
            out.append(node).append(" ");
        }

        // append the unreachable nodes
        out.append(System.lineSeparator());
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                out.append(i).append(" ");
            }
        }

        // print the output
        System.out.println(out.toString().trim());
    }
}
