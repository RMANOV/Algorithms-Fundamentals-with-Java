// School Teams
// Write a program that receives the names of girls and boys in a class and
// generates all possible ways to create teams
// with 3 girls and 2 boys. Print each team on a separate line separated by a
// comma and space ", " (first the girls
// then the boys). For more clarification see the examples below
// Note: "Linda, Amy, Katty, John, Bill" is the same as "Linda, Amy, Katty,
// Bill, John"; so print
// only the first case
// Input
// • On the first line you will receive the girl‘s names separated by a comma
// and space ", ".
// • On the second line you will receive the boy‘s names separated by a comma
// and space ", ".
// Output
// • On separate lines print all the possible teams with exactly 3 girls and 2
// boys separated by comma and space
// and starting with the girls.
// Constrains
// • There will always be at least 3 girls and 2 boys in the input



// Reads the input strings for girls and boys
// Splits the strings into arrays of names
// Generates all combinations of 3 girls using nested for loops
// For each combination of 3 girls, generates all pairs of 2 boys
// Prints each full team (3 girls + 2 boys) on a separate line


import java.util.Scanner;

public class SchoolTeams {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String girls = scanner.nextLine();
        String boys = scanner.nextLine();
        
        String[] girlNames = girls.split(", ");
        String[] boyNames = boys.split(", ");
        
        for (int i = 0; i < girlNames.length - 2; i++) {
            for (int j = i + 1; j < girlNames.length - 1; j++) {
                for (int k = j + 1; k < girlNames.length; k++) {
                    String teamGirls = girlNames[i] + ", " + girlNames[j] + ", " + girlNames[k]; 
                    
                    for (int l = 0; l < boyNames.length - 1; l++) {
                        for (int m = l + 1; m < boyNames.length; m++) {
                            String teamBoys = boyNames[l] + ", " + boyNames[m];
                            System.out.println(teamGirls + ", " + teamBoys);
                        }
                    }
                }
            }
        }
    } 
}