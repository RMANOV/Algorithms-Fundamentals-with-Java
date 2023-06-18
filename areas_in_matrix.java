// Areas in Matrix
// We are given a matrix of letters of size N * M. Two cells are neighbors if
// they share a common wall. Write a program
// to find the connected areas of neighbor cells holding the same letter.
// Display the total number of areas and
// the number of areas for each alphabetical letter (ordered by alphabetical
// order).
// On the first line is given the number of rows.
// Hint
// Initially mark all cells as unvisited. Start a recursive DFS traversal (or BFS) from each unvisited cell and mark all
// reached cells as visited. Each DFS traversal will find one of the connected areas.
// input
// 5
// asssaadas
// adsdasdad
// sdsdadsas
// sdasdsdsa
// ssssasddd
// output
// Areas: 21
// Letter 'a' -> 6
// Letter 'd' -> 7
// Letter 's' -> 8

import java.util.*;


public class areas_in_matrix {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Read the matrix from the input
        int n = Integer.parseInt(sc.nextLine());
        char[][] matrix = new char[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = sc.nextLine().toCharArray();
        }

        // Create a map to store the areas
        Map<Character, Integer> areas = new TreeMap<>();

        // Find the areas
        boolean[][] visited = new boolean[n][matrix[0].length];
        for (int row = 0; row < n; row++) { // for each row
            for (int col = 0; col < matrix[row].length; col++) { // for each column
                if (!visited[row][col]) { // if the cell is not visited
                    char letter = matrix[row][col]; // get the letter
                    areas.putIfAbsent(letter, 0); // add the letter to the map if it is not present
                    areas.put(letter, areas.get(letter) + 1); // increment the count of the letter
                    findArea(matrix, visited, row, col, letter); // find the area
                }
            }
        }

        // Print the result
        System.out.println("Areas: " + areas.values().stream().mapToInt(Integer::valueOf).sum());
        areas.forEach((letter, count) -> System.out.printf("Letter '%c' -> %d%n", letter, count));
    }

    // Find the area of a letter using DFS
    private static void findArea(char[][] matrix, boolean[][] visited, int row, int col, char letter) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length) { // if the cell is out of the matrix
            return;
        }

        if (visited[row][col]) { // if the cell is visited
            return;
        }

        if (matrix[row][col] != letter) { // if the cell does not contain the letter
            return;
        }

        visited[row][col] = true; // mark the cell as visited

        findArea(matrix, visited, row - 1, col, letter); // up 
        findArea(matrix, visited, row + 1, col, letter); // down
        findArea(matrix, visited, row, col - 1, letter); // left
        findArea(matrix, visited, row, col + 1, letter); // right


    }

}