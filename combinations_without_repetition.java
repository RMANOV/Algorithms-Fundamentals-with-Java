// Combinations without Repetition
// Given a set of elements, generate all combinations of k elements without repetition.
// Examples
// Input Output
// A B C
// 2
// A B
// A C
// B C

// 1. Get the input elements
// 2. Get the number of elements to choose from the set - k
// 3. Create an array to hold the combinations
// 4. Create a list to hold the result
// 5. Create a method to combine the elements
// 6. Create a method to swap the elements
// 7. Print the result - use the same method as in permutations without repetitions

import java.util.*;

public class Main {
    public static String[] elements;
    public static String[] combinations;
    public static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
        int k = Integer.parseInt(scanner.nextLine());
        combinations = new String[k];

        combine(0, 0);

        result.forEach(System.out::println);
    }

    private static void combine(int index, int start) {
        if (index >= combinations.length) {
            result.add(String.join(" ", combinations));
        } else {
            for (int i = start; i < elements.length; i++) {
                combinations[index] = elements[i];
                combine(index + 1, i + 1);
            }
        }
    }
}