// Connected Areas in a Matrix
// Let’s define a connected area in a matrix as an area of cells in which there
// is a path between every two cells.
// Write a program to find all connected areas in a matrix.
// On the console, print the total number of areas found, and on a separate line
// some info about each of the areas – its
// position (top-left corner) and size.
// Order the areas by size (in descending order) so that the largest area is
// printed first. If several areas have the same
// size, order them by their position, first by the row, then by the column of
// the top-left corner. So, if there are two
// connected areas with the same size, the one which is above and/or to the left
// of the other will be printed first.
// On the first line, you will get the number of rows.
// On the second line, you will get the number of columns.
// The rest of the input will be the actual matrix.
// input
// 4
// 9
// ---*---*-
// ---*---*-
// ---*---*-
// ----*-*--
// output
// Total areas found: 3
// Area #1 at (0, 0), size: 13
// Area #2 at (0, 4), size: 10
// Area #3 at (0, 8), size: 5
// Hints
// • Create a method to find the first traversable cell which hasn’t been visited. This would be the top-left corner
// of a connected area. If there is no such cell, this means all areas have been found.
// • You can create a class to hold info about a connected area (its position and size). Additionally, you can
// implement Comparable and store all areas found in a TreeSet.

// a - get the number of rows and columns from the console
// b - create a matrix with the given rows and columns
// 1. Create a class to hold info about a connected area
// 2. Implement Comparable and override the compareTo method
// 3. Create a method that finds the first traversable cell which hasn’t been visited
// 4. Create a method that finds the size of the connected area
// 5. Create a method that finds the connected areas
// 6. Create a method that prints the connected areas
// 7. Create a method that checks if a cell is traversable
// 8. Create a method that checks if a cell is visited
// 9. Create a method that checks if a cell is in the matrix
// 10. Create a method that checks if a cell is equal to a given character



import java.util.*;

class Area implements Comparable<Area> {
    int row;
    int col;
    int size;

    public Area(int row, int col, int size) {
        this.row = row;
        this.col = col;
        this.size = size;
    }

    @Override
    public int compareTo(Area other) {
        if (this.size != other.size) {
            return other.size - this.size;
        } else if (this.row != other.row) {
            return this.row - other.row;
        } else {
            return this.col - other.col;
        }
    }
}

public class Main {
    private static int rows;
    private static int cols;
    private static char[][] matrix;
    private static boolean[][] visited;
    private static List<Area> areas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        scanner.nextLine();
        
        matrix = new char[rows][cols];
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        findConnectedAreas();
        Collections.sort(areas);

        System.out.println("Total areas found: " + areas.size());
        for (int i = 0; i < areas.size(); i++) {
            Area area = areas.get(i);
            System.out.println("Area #" + (i+1) + " at (" + area.row + ", " + area.col + "), size: " + area.size);
        }
    }

    private static void findConnectedAreas() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!visited[row][col] && matrix[row][col] == '-') {
                    int size = dfs(row, col);
                    areas.add(new Area(row, col, size));
                }
            }
        }
    }

    private static int dfs(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col] || matrix[row][col] != '-') {
            return 0;
        }
        visited[row][col] = true;
        return 1
            + dfs(row - 1, col)
            + dfs(row + 1, col)
            + dfs(row, col - 1)
            + dfs(row, col + 1);
    }
}