// Find All Paths in a Labyrinth
// You are given a labyrinth. Your goal is to find all paths from the start (cell 0, 0) to the exit, marked with 'e'.
// • Empty cells are marked with a dash '-'.
// • Walls are marked with a star '*'.
// On the first line, you will receive the dimensions of the labyrinth. Next, you will receive the actual labyrinth.
// The order of the paths does not matter.
// Examples
// Input Output
// 3
// 3
// ---
// -*-
// --e
// RRDD
// DDRR
// 3
// 5
// -**-e
// -----
// *****
// DRRRRU
// DRRRUR
// Hints
// Create methods for reading and finding all paths in the labyrinth.
// Create a static list that will hold every direction (basically the path).
// Finding all paths should be recursive.
// Implement all helper methods that are present in the code above.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PathsInALabyrinth {
    private static char[][] labyrinth;
    private static List<Character> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());
        labyrinth = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            labyrinth[row] = scanner.nextLine().toCharArray();
        }
        findPath(0, 0, 'S');
    }

    private static void findPath(int row, int col, char direction) {
        if (!isInBounds(row, col)) {
            return;
        }
        path.add(direction);
        if (labyrinth[row][col] == 'e') {
            printPath();
        } else if (!isVisited(row, col) && !isWall(row, col)) {
            mark(row, col);
            findPath(row, col + 1, 'R');
            findPath(row + 1, col, 'D');
            findPath(row, col - 1, 'L');
            findPath(row - 1, col, 'U');
            unmark(row, col);
        }
        path.remove(path.size() - 1);
    }

    private static void unmark(int row, int col) {
        labyrinth[row][col] = '-';
    }

    private static void mark(int row, int col) {
        labyrinth[row][col] = 'v';
    }

    private static boolean isWall(int row, int col) {
        return labyrinth[row][col] == '*';
    }

    private static boolean isVisited(int row, int col) {
        return labyrinth[row][col] == 'v';
    }

    private static boolean isInBounds(int row, int col) {
        return row < labyrinth.length && row >= 0 && col < labyrinth[row].length && col >= 0;
    }

    private static void printPath() {
        for (Character direction : path) {
            if (direction != 'S') {
                System.out.print(direction);
            }
        }
        System.out.println();
    }
}


