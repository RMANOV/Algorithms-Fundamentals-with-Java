// Word Differences
// Write a program that finds all the differences in two strings with equal lengths. You have to determine the smallest
// set of deletions and insertions to make the first string equal to the second. Finally, you have to print the count of
// the minimum insertions and deletions.
// Input
// • You will receive the two strings on separate lines.
// Output
// • Print the minimum amount of deletions and insertions as described below.
// Use dynamic programming to solve this problem.
// Examples
// Input
// abcde
// abcf
// Output
// 2

import java.util.Scanner;

public class WordDifferences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the two input strings
        String firstWord = scanner.nextLine();
        String secondWord = scanner.nextLine();

        // Create a matrix to store the minimum number of deletions and insertions
        // needed to transform the first string into the second string
        int[][] matrix = new int[firstWord.length() + 1][secondWord.length() + 1];

        // Initialize the first row and column of the matrix
        for (int row = 1; row < matrix.length; row++) {
            matrix[row][0] = row;
        }

        for (int col = 1; col < matrix[0].length; col++) {
            matrix[0][col] = col;
        }

        // Fill in the rest of the matrix using dynamic programming
        for (int row = 1; row < matrix.length; row++) { // first word
            char firstWordChar = firstWord.charAt(row - 1); // first word char
            for (int col = 1; col < matrix[0].length; col++) { // second word
                char secondWordChar = secondWord.charAt(col - 1); // second word char
                if (firstWordChar == secondWordChar) { // if chars are equal
                    matrix[row][col] = matrix[row - 1][col - 1]; // copy the value from the upper left diagonal
                } else { // if chars are not equal
                    matrix[row][col] = Math.min(matrix[row - 1][col], matrix[row][col - 1]) + 1; // get the minimum value from the upper and left cells and add 1
                }
            }
        }

        // Print the minimum number of deletions and insertions
        System.out.println("Deletions and Insertions: " + matrix[firstWord.length()][secondWord.length()]);
    }
}