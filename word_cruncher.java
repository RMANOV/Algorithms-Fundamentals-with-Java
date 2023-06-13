// Word Cruncher
// Write a program that receives some strings and forms another string that is
// required. In the first line, you will be
// given all of the strings separated by a comma and space. On the next line,
// you will be given the string that needs to
// be formed from the given strings. For more clarification see the examples
// below.
// Input
// • On the first line you will receive the strings (separated by comma and
// space ", ").
// • On the next line you will receive the target string.
// Output
// • Print each of them found ways to form the required string as shown in the
// examples.
// Constrains
// • There might be repeating elements in the input

// Examples
// Input
// text, me, so, do, m, ran
// somerandomtext
// Output
// so me ran do m text


// Input Reading and Preprocessing: The code reads in the words and the target string from the console. It removes any words from the list that are not contained within the target string.

// Index Tracking and Occurrence Counting: The code then creates a table (a map) that maps each index in the target string to a list of words that start at that index. At the same time, it also creates a map that counts the occurrences of each word in the list. This is done by looping through each word in the list and finding all its occurrences in the target string.

// Combination Generation (Permutation): The permute function is a recursive function that generates all possible combinations of the words that can form the target string. It takes an index as a parameter, which represents the current position in the target string. If this index is equal to the length of the target string, it means that the current combination of words (stored in combined) forms the target string, so it calls the print function to add this combination to the result set and returns. If the table contains the current index, it means that there are words that start at this index, so it loops through these words. If a word has remaining occurrences, it adds the word to combined, decrements its count in occurrences, recursively calls permute for the next position in the target string, and then backtracks by removing the word from combined and incrementing its count in occurrences.

// Output Generation: The print function checks if the current combination of words forms the target string by joining the words in combined into a string and checking if it contains the target string. If it does, it adds the combination (with spaces between the words) to the result set out.

// Output Printing: Finally, the code prints all combinations in the result set that can form the target string.


import java.util.*;

public class word_cruncher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] words = scanner.nextLine().split(", ");
        String target = scanner.nextLine();

        List<String> wordsList = new ArrayList<>(Arrays.asList(words));
        wordsList.removeIf(word -> !target.contains(word));

        Map<Integer, List<String>> table = new HashMap<>();
        Map<String, Integer> occurrences = new HashMap<>();

        for (String word : wordsList) {
            int index = target.indexOf(word);
            while (index != -1) {
                table.putIfAbsent(index, new ArrayList<>());
                table.get(index).add(word);
                index = target.indexOf(word, index + 1);
            }
            occurrences.putIfAbsent(word, 0);
            occurrences.put(word, occurrences.get(word) + 1);
        }

        Set<String> out = new TreeSet<>();
        permute(0, target, table, occurrences, new ArrayList<>(), out);

        for (String combination : out) {
            System.out.println(combination);
        }
    }

    private static void permute(int index, String target, Map<Integer, List<String>> table, Map<String, Integer> occurrences, List<String> combined, Set<String> out) {
        if (index == target.length()) {
            String combination = String.join(" ", combined);
            String combinationWithoutSpaces = String.join("", combined);
            if (combinationWithoutSpaces.equals(target)) {
                out.add(combination);
            }
            return;
        }

        if (table.containsKey(index)) {
            for (String word : table.get(index)) {
                if (occurrences.get(word) > 0) {
                    combined.add(word);
                    occurrences.put(word, occurrences.get(word) - 1);
                    permute(index + word.length(), target, table, occurrences, combined, out);
                    occurrences.put(word, occurrences.get(word) + 1);
                    combined.remove(combined.size() - 1);
                }
            }
        }
    }
}
