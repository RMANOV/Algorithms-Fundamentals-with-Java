// Variations without Repetitions
// Given a set of elements, find all variations of k elements without repetitions.
// Input Output
// A B C
// 2
// A B
// A C
// B A
// B C
// C A
// C B

// 1. Get the input elements
// 2. Get the number of elements to choose
// 3. Create an array to hold the variations
// 4. Create a list to hold the result
// 5. Create a method to permute the elements
// 6. Create a method to swap the elements
// 7. Print the result - use the same method as in permutations without repetitions

import java.util.*;

public class Main {
    public static String[] elements;
    public static String[] variations;
    public static boolean[] used;
    public static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
        int k = Integer.parseInt(scanner.nextLine());
        variations = new String[k];
        used = new boolean[elements.length];

        permute(0);

        result.forEach(System.out::println);
    }

    private static void permute(int index) {
        if (index >= variations.length) {
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
