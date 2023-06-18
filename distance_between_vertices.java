// Distance between Vertices
// We are given a directed graph. We are given also a set of pairs of vertices.
// Find the shortest distance between each
// pair of vertices or -1 if there is no path connecting them.
// On the first line, you will get N, the number of vertices in the graph. On
// the second line, you will get P, the number of
// pairs between which to find the shortest distance.
// On the next N, lines will be the edges of the graph and on the next P lines,
// the pairs.


import java.util.*;


public class distance_between_vertices {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Read the number of vertices and pairs from the input
        int n = Integer.parseInt(sc.nextLine());
        int p = Integer.parseInt(sc.nextLine());

        // Create a map to store the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Read the edges of the graph from the input
        for (int i = 0; i < n; i++) { // for each edge
            String[] line = sc.nextLine().split(":"); // read the edge
            int key = Integer.parseInt(line[0]); // get the key
            if (line.length == 1) { // if the edge has no children
                graph.put(key, new ArrayList<>()); // add the key with an empty list of children
            } else { // if the edge has children
                List<Integer> children = new ArrayList<>(); // create a list of children
                for (String child : line[1].split("\\s+")) { // add all children to the list
                    children.add(Integer.parseInt(child));
                }
                graph.put(key, children); // add the key with the list of children
            }
        }

        // Find the shortest distance between each pair of vertices
        for (int i = 0; i < p; i++) { // for each pair of vertices
            String[] line = sc.nextLine().split("-"); // read the pair
            int start = Integer.parseInt(line[0]); // get the start vertex
            int end = Integer.parseInt(line[1]); // get the end vertex
            int distance = findDistance(graph, start, end); // find the distance
            System.out.printf("{%d, %d} -> %d%n", start, end, distance); // print the result
        }
    }

    // Find the shortest distance between two vertices using BFS
    private static int findDistance(Map<Integer, List<Integer>> graph, int start, int end) { // start and end are the vertices
        if (!graph.containsKey(start) || !graph.containsKey(end)) { // if the graph does not contain the vertices
            return -1;
        }

        Set<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> distances = new HashMap<>();

        queue.offer(start);
        visited.add(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            int node = queue.poll(); // remove the first element from the queue
            for (int child : graph.get(node)) { // add all children of the node to the queue
                if (!visited.contains(child)) { // if the child is not visited
                    queue.offer(child);
                    visited.add(child);
                    distances.put(child, distances.get(node) + 1); // update the distance
                }
            }
        }

        return distances.containsKey(end) ? distances.get(end) : -1; // return the distance or -1 if there is no path
    }
}