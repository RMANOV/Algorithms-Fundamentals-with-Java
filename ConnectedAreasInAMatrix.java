// Connected Areas In A Matrix
// Given a matrix (city map) of 't's (tunnel) and 'd's (dirt), count the number of connected tunnels in the city. 
// A connected tunnel is surrounded by dirt and is formed by connecting adjacent 't's horizontally, vertically or diagonally. 
// Input
// •	On the first line you will receive an integer – r – representing the number of rows in the map.
// •	On the second line you will receive an integer – c – representing the number of columns in the map.
// •	On the next r lines, you will receive values for each row in a single line.
// Output
// •	Print the number of connected tunnels.
// Constraints
// •	You may assume all four edges of the city map are all surrounded by dirt.
// •	r and c will be in the range [1, 1000].
// •	Map elements will be either t or d.
// Input
// 4
// 6
// tddddt
// dtdddt
// ddtddt
// dddtdt
// Output
// 2
// Input
// 4
// 6
// tdttdt
// tdttdt
// tdttdt
// tdttdt
// Output
// 3



import java.util.Scanner;

public class ConnectedAreasInAMatrix {
    private static int numRows; // number of rows in the matrix
    private static int numColumns; // number of columns in the matrix
    private static char[][] matrix; // the matrix itself
    private static boolean[][] visited; // a matrix to keep track of visited cells
    private static final int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } }; // all possible directions to move in the matrix

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        numRows = Integer.parseInt(scanner.nextLine()); // read the number of rows
        numColumns = Integer.parseInt(scanner.nextLine()); // read the number of columns

        matrix = new char[numRows][numColumns]; // initialize the matrix
        visited = new boolean[numRows][numColumns]; // initialize the visited matrix

        // read the matrix
        for (int i = 0; i < numRows; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < numColumns; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        int totalAreas = 0; // initialize the total number of areas

        // iterate over all cells in the matrix
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (matrix[i][j] == 't' && !visited[i][j]) { // if the cell is a tunnel and hasn't been visited yet
                    dfs(i, j); // perform a depth-first search to find all connected tunnels
                    totalAreas++; // increment the total number of areas
                }
            }
        }

        System.out.println(totalAreas); // print the total number of connected areas
    }

    // perform a depth-first search to find all connected tunnels
    private static void dfs(int row, int col) {
        visited[row][col] = true; // mark the current cell as visited

        // iterate over all possible directions to move
        for (int[] direction : directions) {
            int nextRow = row + direction[0]; // calculate the row of the next cell
            int nextCol = col + direction[1]; // calculate the column of the next cell

            if (isValid(nextRow, nextCol)) { // if the next cell is valid
                dfs(nextRow, nextCol); // perform a depth-first search on the next cell
            }
        }
    }

    // check if a cell is valid
    private static boolean isValid(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numColumns // check if the cell is within the bounds of the matrix
                && !visited[row][col] && matrix[row][col] == 't'; // check if the cell hasn't been visited yet and is a tunnel
    }
}

// Commit message: Read the matrix and perform a depth-first search to find all connected tunnels in horizontal, vertical and diagonal directions.
