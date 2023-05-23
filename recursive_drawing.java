//  Recursive Drawing
// Write a program that draws the figure below depending on n. Use recursion.
// Examples
// Input Output
// 2 
// **
// *
// #
// ##
// 5 
// *****
// ****
// ***
// **
// *
// #
// ##
// ###
// ####
// #####
// Hints
// Set the bottom of the recursion.
// Define pre- and post- recursive behavior.

import java.util.Scanner;

public class RecursiveDrawing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        draw(n);
    }

    private static void draw(int n) {
        if (n == 0) {
            return;
        }
        System.out.println(repeatStr("*", n));
        draw(n - 1);
        System.out.println(repeatStr("#", n));
    }

    private static String repeatStr(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}
