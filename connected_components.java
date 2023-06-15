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




import java.util.*;

public class ConnectedComponents {
    // Declare graph and visited arrays
    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the number of nodes
        int n = Integer.parseInt(scanner.nextLine());
        // Initialize graph and visited arrays
        graph = new ArrayList[n];
        visited = new boolean[n];
        // Read the graph from the console
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            String[] input = scanner.nextLine().split("\\s+");
            for (String s : input) {
                graph[i].add(Integer.parseInt(s));
            }
        }
        // Initialize a list to store the connected components
        List<List<Integer>> connectedComponents = new ArrayList<>();
        // Traverse the graph and find the connected components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> connectedComponent = new ArrayList<>();
                dfs(i, connectedComponent);
                connectedComponents.add(connectedComponent);
            }
        }
        // Print the number of connected components
        System.out.println("Number of connected components: " + connectedComponents.size());
        // Print the nodes in each connected component
        for (List<Integer> connectedComponent : connectedComponents) {
            for (Integer integer : connectedComponent) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    // DFS algorithm to traverse the graph and find the connected components
    private static void dfs(int node, List<Integer> connectedComponent) {
        if (!visited[node]) {
            visited[node] = true;
            for (Integer child : graph[node]) {
                dfs(child, connectedComponent);
            }
            connectedComponent.add(node);
        }
    }
}
