// The Matrix
// You are given a matrix (2D array) of lowercase alphanumeric characters (a-z, 0-9), a starting position – defined by a
// start row startRow and a start column startCol – and a filling symbol fillChar. Let’s call the symbol originally
// at startRow and startCol the startChar. Write a program, which, starting from the symbol at startRow and
// startCol, changes to fillChar every symbol in the matrix which:
// • is equal to startChar AND
// • can be reached from startChar by going up (row – 1), down (row + 1), left (col – 1) and right (col
// + 1) and “stepping” ONLY on symbols equal startChar
// So, you basically start from startRow and startCol and can move either by changing the row OR column (not
// both at once, i.e. you can’t go diagonally) by 1 and can only go to positions that have the startChar written on
// them. Once you find all those positions, you change them to fillChar.
// In other words, you need to implement something like the Fill tool in MS Paint, but for a 2D char array instead of a
// bitmap.
// Input
// On the first line, two integers will be entered – the number R of rows and number C of columns.
// On each of the next R lines, C characters separated by single spaces will be entered – the symbols of the R
// th row of
// the matrix, starting from the 0
// the column and ending at the C-1 column.
// On the next line, a single character – the fillChar – will be entered.
// On the last line, two integers – startRow and startCol – separated by a single space, will be entered.
// Output
// The output should consist of R lines, each consisting of exactly C characters, NOT SEPARATED by spaces,
// representing the matrix after the fill operation has been finished.
// Constraints
// 0 < R, C < 20
// 0 <= startRow < R
// 0 <= startCol < C
// All symbols in the input matrix will be lowercase alphanumerics (a-z, 0-9). The fillChar will also be
// alphanumeric and lowercase.
// Example input
// 5 3
// a a a
// a a a
// a b a
// a b a
// a b a
// x
// 0 0
// Example output
// xxx
// xxx
// xbx
// xbx
// xbx
// Example input2
// 5 3
// a a a
// a a a
// a b a
// a b a
// a b a
// x
// 2 1
// Example output2
// aaa
// aaa
// axa
// axa
// axa



import java.util.Scanner;

public class Main {
    // Define a recursive function to perform depth-first search (DFS) on the matrix
    // and change all the symbols equal to startChar to fillChar
    private static void dfs(char[][] matrix, int x, int y, char startChar, char fillChar) {
        // Get the number of rows and columns of the matrix
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Check if the current position is out of bounds or if the symbol is not equal to startChar
        if (x < 0 || x >= rows || y < 0 || y >= cols) return;
        if (matrix[x][y] != startChar) return;

        // Change the symbol at the current position to fillChar
        matrix[x][y] = fillChar;

        // Recursively call the dfs function on the adjacent positions
        dfs(matrix, x - 1, y, startChar, fillChar);
        dfs(matrix, x + 1, y, startChar, fillChar);
        dfs(matrix, x, y - 1, startChar, fillChar);
        dfs(matrix, x, y + 1, startChar, fillChar);
    }

    public static void main(String[] args) {
        // Create a scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Read the number of rows and columns of the matrix
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        // Create a 2D array to store the matrix
        char[][] matrix = new char[rows][cols];

        // Read the symbols of the matrix from the console and store them in the matrix array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.next().charAt(0);
            }
        }

        // Read the fillChar from the console
        char fillChar = scanner.next().charAt(0);

        // Read the startRow and startCol from the console
        int startRow = scanner.nextInt();
        int startCol = scanner.nextInt();

        // Call the dfs function to change all the symbols equal to startChar to fillChar
        dfs(matrix, startRow, startCol, matrix[startRow][startCol], fillChar);

        // Print the matrix to the console
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

        // Close the scanner object
        scanner.close();
    }
}
