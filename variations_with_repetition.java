// Variations with Repetition
// Given a set of elements, find all variations of k elements with repetitions.
// Examples
// Input Output
// A B C
// 2
// A A
// A B
// A C
// B A
// B B
// B C
// C A
// C B
// C C

// 1. Get the input elements
// 2. Get the number of elements to choose from the set - k
// 3. Create an array to hold the variations
// 4. Create a list to hold the result
// 5. Create a method to permute the elements
// 6. Create a method to swap the elements
// 7. Print the result - use the same method as in permutations with repetitions

import java.util.*;

public class Main {
    public static String[] elements;
    public static String[] variations;
    public static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
        int k = Integer.parseInt(scanner.nextLine());
        variations = new String[k];

        permute(0);

        result.forEach(System.out::println);
    }

    private static void permute(int index) {
        if (index >= variations.length) {
            result.add(String.join(" ", variations));
        } else {
            for (int i = 0; i < elements.length; i++) {
                variations[index] = elements[i];
                permute(index + 1);
            }
        }
    }
}
