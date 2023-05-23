// Permutations without Repetitions
// Given a set of elements, find all permutations without repetitions
// Example: {A, B, C} -> {ABC, ACB, BAC, BCA, CAB, CBA}
// A C B
// B A C
// B C A
// C B A
// C A B


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class permutations_without_repetitions {
    public static String[] elements;
    public static String[] variations;
    public static boolean[] used;
    public static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
        variations = new String[elements.length];
        used = new boolean[elements.length];

        permute(0);
        
        result.forEach(System.out::println);
    }

    private static void permute(int index) {
        if (index >= elements.length) {
            result.add(String.join(" ", variations));
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    variations[index] = elements[i];
                    permute(index + 1);
                    used[i] = false;
                }
            }
        }
    }
}
