//  Cycles in a Graph
// Write a program to check whether an undirected graph is acyclic or holds any cycles. The input ends with the "End"
// line.
// Input
// E-Q
// Q-P
// P-B
// End
// Output
// Acyclic: Yes



import java.util.*;


public class cycles_in_graph {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Read the graph from the input
        Map<String, List<String>> graph = new HashMap<>();
        while (true) {
            String line = sc.nextLine();
            if (line.equals("End")) {
                break;
            }
            String[] tokens = line.split("-");
            String parent = tokens[0];
            String child = tokens[1];
            graph.putIfAbsent(parent, new ArrayList<>());
            graph.putIfAbsent(child, new ArrayList<>());
            graph.get(parent).add(child);
            graph.get(child).add(parent);
        }

        // Find the cycles
        Set<String> visited = new HashSet<>();
        boolean hasCycles = false;
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                hasCycles = findCycles(graph, visited, node, null);
                if (hasCycles) {
                    break;
                }
            }
        }

        // Print the result
        System.out.println("Acyclic: " + (hasCycles ? "No" : "Yes"));
    }

    // Find cycles using DFS
    private static boolean findCycles(Map<String, List<String>> graph, Set<String> visited, String node, String parent) {
        if (visited.contains(node)) { // if the node is already visited
            return true;
        }
        visited.add(node);
        for (String child : graph.get(node)) { // for each child of the node
            if (!child.equals(parent) && findCycles(graph, visited, child, node)) { // if the child is not the parent and the child has cycles
                return true;
            }
        }
        return false;
    }
}