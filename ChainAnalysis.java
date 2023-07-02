// Chain Analysis
// You are given a list of Bitcoin transactions, represented as tuples of three elements: (sender, receiver, amount), where sender and receiver are unique Bitcoin addresses, and the amount is a positive integer representing the amount of Bitcoin being transferred. 
// Your task is to determine the number of groups of Bitcoin addresses that have participated in at least one transaction with each other.
// In other words, given a transaction between two addresses, those addresses are considered to be in the same group. Two addresses that are indirectly connected through other addresses participating in the transactions are also considered to be in the same group.
// For example, given the following list of transactions: 
// [('A', 'B', 1), ('B', 'C', 2), ('C', 'D', 3), ('E', 'F', 1), ('F', 'G', 2), ('G', 'H', 3)]
// We have 2 groups of Bitcoin addresses.
// Input
// •	On the first line, you will receive an integer n - representing the number of transactions.
// •	On the next n lines, you will receive each transaction in the following format: "{from} {to} {amount}".
// Output
// •	The output consists of one number - how many groups are found out of all transactions.
// Constraints
// •	The input will always be in the format described in the Input section.
// •	Transactions will be unique.
// •	The number of transactions will be in the range [1… 100].
// •	The number of groups will be in the range [1… 100].
// Input
// 6
// A B 1
// B C 2
// C D 3
// E F 1
// F G 2
// G H 3
// Output
// 2
// Input1
// 6
// A B 1
// B C 2
// C D 3
// E F 1
// F G 2
// H K 4
// Output1
// 3

import java.util.*;

public class ChainAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int transactions = Integer.parseInt(scanner.nextLine()); // read the number of transactions
        Map<String, List<String>> graph = new HashMap<>(); // create a graph to store the transactions
        for (int i = 0; i < transactions; i++) { // loop through each transaction
            String[] tokens = scanner.nextLine().split("\\s+"); // read the transaction and split it into sender,
                                                                // receiver and amount
            String from = tokens[0]; // get the sender
            String to = tokens[1]; // get the receiver
            graph.putIfAbsent(from, new ArrayList<>()); // add the sender to the graph if it doesn't exist
            graph.putIfAbsent(to, new ArrayList<>()); // add the receiver to the graph if it doesn't exist
            graph.get(from).add(to); // add the receiver to the sender's list of transactions
            graph.get(to).add(from); // add the sender to the receiver's list of transactions
        }
        Set<String> visited = new HashSet<>(); // create a set to store visited nodes
        int count = 0; // initialize the count of groups to 0
        for (String node : graph.keySet()) { // loop through each node in the graph
            if (!visited.contains(node)) { // if the node has not been visited
                count++; // increment the count of groups
                dfs(node, graph, visited); // perform a depth-first search on the node and its connected nodes
            }
        }
        System.out.println(count); // print the count of groups
    }

    private static void dfs(String node, Map<String, List<String>> graph, Set<String> visited) {
        if (visited.contains(node)) { // if the node has already been visited
            return; // return without doing anything
        }
        visited.add(node); // mark the node as visited
        for (String child : graph.get(node)) { // loop through each connected node
            dfs(child, graph, visited); // perform a depth-first search on the connected node and its connected nodes
        }
    }
}
