// . Cinema
// Write a program that prints all of the possible distributions of a group of friends in a cinema hall. In the first line,
// you will be given all of the friends‘ names separated by a comma and space. Until you receive the command
// "generate" you will be given some of those friends‘s names and a number of the place that they want to have.
// (format: "{name} - {place}") So here comes the tricky part. Those friends want only to sit in the place that they
// have chosen. They cannot sit in other places. For more clarification see the examples below.
// Output
// Print all the possible ways to distribute the friends having in mind that some of them want a particular place and
// they will sit there only. The order of the output does not matter.
// Constrains
// • The friend‘s names and the number of the place will always be valid.
// Examples
// Input Output Comments
// Peter, Amy, George, Rick
// Amy - 1
// Rick - 4
// generate
// Amy Peter George Rick
// Amy George Peter Rick
// Amy only wants to sit on
// the first seat and Rick
// wants to sit on the
// fourth, so we only switch
// the other friends
// Garry, Liam, Teddy, Anna,
// Buddy, Simon
// Buddy - 3
// Liam - 5
// Simon - 1
// generate
// Simon Garry Buddy Teddy Liam Anna
// Simon Garry Buddy Anna Liam Teddy
// Simon Teddy Buddy Garry Liam Anna
// Simon Teddy Buddy Anna Liam Garry
// Simon Anna Buddy Garry Liam Teddy
// Simon Anna Buddy Teddy Liam Garry





import java.util.*;

public class Main {
    private static String[] friends;
    private static int[] places;
    private static boolean[] used;
    private static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        friends = scanner.nextLine().split(", ");
        places = new int[friends.length];
        used = new boolean[friends.length];
        String line = scanner.nextLine();
        while (!line.equals("generate")) {
            String[] tokens = line.split(" - ");
            String name = tokens[0];
            int place = Integer.parseInt(tokens[1]);
            int index = findIndex(name);
            places[index] = place;
            used[index] = true;
            line = scanner.nextLine();
        }
        permute(0);
        System.out.println(String.join(System.lineSeparator(), result));
    }

    private static void permute(int index) {
        if (index == friends.length) {
            result.add(String.join(" ", friends));
            return;
        }
        permute(index + 1);
        for (int i = index + 1; i < friends.length; i++) {
            if (!used[index] && !used[i]) {
                swap(index, i);
                permute(index + 1);
                swap(index, i);
            }
        }
    }

    private static void swap(int i, int j) {
        String temp = friends[i];
        friends[i] = friends[j];
        friends[j] = temp;
    }

    private static int findIndex(String name) {
        for (int i = 0; i < friends.length; i++) {
            if (friends[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
