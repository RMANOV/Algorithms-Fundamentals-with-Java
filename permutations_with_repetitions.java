// Permutations with Repetitions
// Given a multi-set of elements, find all permutations.

// Example: {A, B, B} -> 
// A B B
// B A B
// B B A

// 1. Get the input elements
// 2. Create an array to hold the variations
// 3. Create a list to hold the result
// 4. Create a method to permute the elements
// 5. Create a method to swap the elements
// 6. Print the result

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        char[] arr = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            arr[i] = input[i].charAt(0);
        }

        List<String> result = new ArrayList<>();
        Arrays.sort(arr); // sort the array to ensure duplicates are adjacent
        permute(arr, 0, result);

        for (String permutation : result) {
            System.out.println(permutation);
        }
    }

    public static void permute(char[] arr, int l, List<String> result) {
        if (l == arr.length - 1) {
            StringBuilder sb = new StringBuilder();
            for (char c : arr) {
                sb.append(c).append(' ');
            }
            sb.setLength(sb.length() - 1); // remove last space
            result.add(sb.toString());
        } else {
            for (int i = l; i < arr.length; i++) {
                if (shouldSwap(arr, l, i)) { // check if swapping would lead to a duplicate
                    swap(arr, i, l);
                    permute(arr, l + 1, result);
                    swap(arr, i, l); // backtrack
                }
            }
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean shouldSwap(char[] arr, int start, int current) {
        for (int i = start; i < current; i++) {
            if (arr[i] == arr[current]) {
                return false; // don't swap if a duplicate is found
            }
        }
        return true;
    }
}

