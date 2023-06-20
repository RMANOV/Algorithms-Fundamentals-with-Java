import java.util.*;
import java.util.regex.*;

class BreakCycles {

    public static void main(String[] args) {
        // Create a Scanner object to get input from the console
        Scanner scanner = new Scanner(System.in);

        // Create the graph as a HashMap of adjacency sets
        HashMap<String, HashSet<String>> graph = new HashMap<>();

        // Get the number of nodes in the graph
        System.out.println("Enter the number of nodes in the graph:");
        int n = scanner.nextInt();

        // Skip the next line
        scanner.nextLine();

        // Get the edges for each node and add them to the graph
        for (int i = 1; i <= n; i++) {
            System.out.println("Enter the edges for node " + i + " in the format 'i -> j k l':");
            String line = scanner.nextLine();
            // Use a regular expression to match the node and its edges
            Pattern pattern = Pattern.compile("(\\d+) -> (.+)");
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                // Get the node and its edges from the matcher groups
                String node = matcher.group(1);
                String[] edges = matcher.group(2).split(" ");
                graph.put(node, new HashSet<>(Arrays.asList(edges)));
            }
        }

        // Close the scanner
        scanner.close();

        // Create a list to store the edges to remove
        List<String> edgesToRemove = new ArrayList<>();

        // Loop through all the edges in alphabetical order
        for (String node : graph.keySet()) {
            for (String edge : graph.get(node)) {
                // Only consider edges where node < edge to avoid duplicates
                if (node.compareTo(edge) < 0) {
                    // Check if the edge {node, edge} closes a cycle
                    if (closesCycle(graph, node, edge)) {
                        // Add the edge {node, edge} to the list of edges to remove
                        edgesToRemove.add(node + " - " + edge);
                        // Remove the edge {node, edge} from the graph
                        graph.get(node).remove(edge);
                        graph.get(edge).remove(node);
                    }
                }
            }
        }

        // Create a list to store the edges to restore
        List<String> edgesToRestore = new ArrayList<>();

        // Loop through all the edges in alphabetical order
        for (String node : graph.keySet()) {
            for (String edge : graph.get(node)) {
                // Only consider edges where node < edge to avoid duplicates
                if (node.compareTo(edge) < 0) {
                    // Check if the edge {node, edge} was removed from the graph
                    if (edgesToRemove.contains(node + " - " + edge)) {
                        // Check if the graph and its values are not null before adding the edge back
                        if (graph != null && graph.get(node) != null && graph.get(edge) != null) {
                            // Restore the edge {node, edge} to the graph
                            graph.get(node).add(edge);
                            graph.get(edge).add(node);
                            // Add the edge {node, edge} to the list of edges to restore
                            edgesToRestore.add(node + " - " + edge);
                        }
                    }
                }
            }
        }

        // Print the number of edges to remove and the removed edges
        System.out.println("Edges to remove: " + edgesToRemove.size());
        for (String edge : edgesToRemove) {
            System.out.println(edge);
        }

        // Print the number of edges to restore and the restored edges
        System.out.println("Edges to restore: " + edgesToRestore.size());
        for (String edge : edgesToRestore) {
            System.out.println(edge);
        }
    }

    /**
     * Checks if the edge {node1, node2} closes a cycle in the graph.
     *
     * @param graph the graph represented as a HashMap of adjacency sets
     * @param node1 the first node of the edge
     * @param node2 the second node of the edge
     * @return true if the edge closes a cycle, false otherwise
     */
    private static boolean closesCycle(HashMap<String, HashSet<String>> graph, String node1, String node2) {
        // Create a set to store the visited nodes
        HashSet<String> visited = new HashSet<>();
        // Create a stack to store the nodes to visit
        Stack<String> stack = new Stack<>();
        // Add the first node to the stack
        stack.push(node1);
        // Loop until the stack is empty
        while (!stack.isEmpty()) {
            // Pop the top node from the stack
            String node = stack.pop();
            // If the node is the second node of the edge, the edge closes a cycle
            if (node.equals(node2)) {
                return true;
            }
            // If the node has not been visited yet, mark it as visited and add its
            // neighbors to the stack
            if (!visited.contains(node)) {
                visited.add(node);
                for (String neighbor : graph.get(node)) {
                    stack.push(neighbor);
                }
            }
        }
        // If the stack is empty and the second node of the edge has not been visited,
        // the edge does not close a cycle
        return false;
    }
}