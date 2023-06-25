// Molecules
// You are part of science team which is on an exploration mission in newly discovered planet inhabiting the goldilock zone of a distant star (insert random digits as name here). 
// You have found a peace of tissue which consist of different molecules connected in order. 
// The biology team want from you do develop a program which by given molecule as a source determines to which other molecules there is no way to transport energy. On the way to any other molecule you may have to pass through other molecules etc.
// Print on a new line separated by spaces print the numbers of molecules you cannot transport energy to from the start molecule. Print them in increasing order.
// Input
// The input will come from the console:
// •	On the first line the number of molecules N
// •	On the second line the number of connections between the molecules M
// •	On each M line the data describing the connections:
// {fromMolecule} {toMolecule}
// •	On the next line single integer the start molecule number 
// Output
// •	On the single output line print the molecules in increasing order to which there is no connection from start molecule.
// Constraints
// •	All input lines will be valid integers you do not need to check that.
// •	The range of the integers will be in the range [1…1000]
// •	The molecules number will be numbers from one increasing for each molecule.
// input:
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
// output:
// 9 10 11



import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class Molecules {
    public static void main(String[] args) throws IOException {
        // Create a BufferedReader to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Create a BufferedOutputStream to write output to the console
        BufferedOutputStream out = new BufferedOutputStream(System.out);

        // Read the number of molecules from the first line of input
        int n = Integer.parseInt(reader.readLine());
        // Create an array of ArrayLists to represent the graph of molecules
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        // Read the number of connections between molecules from the second line of input
        int m = Integer.parseInt(reader.readLine());
        // Read each connection and add it to the graph
        for (int i = 0; i < m; i++) {
            String[] line = reader.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            graph[from].add(to);
        }
        // Read the start molecule from the next line of input
        int start = Integer.parseInt(reader.readLine());
        // Create a boolean array to keep track of which molecules have been visited
        boolean[] visited = new boolean[n + 1];
        // Perform a depth-first search of the graph starting at the start molecule
        dfs(graph, start, visited);

        // Create a StringBuilder to build the output string
        StringBuilder sb = new StringBuilder();
        // Append the numbers of molecules that were not visited to the StringBuilder
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                sb.append(i).append(" ");
            }
        }
        // Write the output string to the console
        out.write(sb.toString().getBytes());
        out.flush();
    }

    // Perform a depth-first search of the graph starting at the given molecule
    private static void dfs(ArrayList<Integer>[] graph, int start, boolean[] visited) {
        // If the molecule has already been visited, return
        if (visited[start]) {
            return;
        }
        // Mark the molecule as visited
        visited[start] = true;
        // Recursively visit each child molecule
        for (Integer child : graph[start]) {
            dfs(graph, child, visited);
        }
    }
}
