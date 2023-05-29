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




// Read the list of friends and any pre-assigned seats from the input
// Recursively generate all possible seat distributions:
// If all friends have been assigned a seat, print the distribution
// Otherwise, try assigning the current friend to an unassigned seat and continue recursively
// If no seats are available, continue recursively without assigning a seat to the current friend
// The Pair class is used to represent a friend-seat assignment

import java.util.*;

public class Cinema {

    // A helper method to check if a friend can sit in a given place
    public static boolean canSit(String friend, int place, Map<String, Integer> preferences) {
        // If the friend has no preference, they can sit anywhere
        if (!preferences.containsKey(friend)) {
            return true;
        }
        // If the friend has a preference, they can only sit in that place
        return preferences.get(friend) == place;
    }

    // A helper method to generate all possible distributions of friends in the cinema hall
    public static void generateDistributions(List<String> friends, int[] places, Map<String, Integer> preferences, int index) {
        // If we have assigned all the friends, we print the current distribution
        if (index == friends.size()) {
            // print the distribution - names insted of numbers - in format - Simon Garry Buddy Teddy Liam Anna
            StringBuilder sb = new StringBuilder(); // use a string builder to append the names
            for (int i = 0; i < places.length; i++) {
                sb.append(friends.get(places[i] - 1)); // append the name of the friend
                if (i < places.length - 1) { // if it is not the last name, append a space
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString()); // print the string builder without an extra space in the end
            return;
        }
        // Otherwise, we try to assign the current friend to each available place
        String friend = friends.get(index);
        for (int i = 0; i < places.length; i++) {
            // If the place is empty and the friend can sit there, we assign them and recurse
            if (places[i] == 0 && canSit(friend, i + 1, preferences)) {
                places[i] = index + 1; // We use index + 1 to represent the friend's number
                generateDistributions(friends, places, preferences, index + 1);
                places[i] = 0; // We backtrack and unassign the friend
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the input and store the friends in a list
        String[] input = scanner.nextLine().split(", ");
        List<String> friends = new ArrayList<>(Arrays.asList(input));

        // Create a map to store the preferences of some friends
        Map<String, Integer> preferences = new HashMap<>();

        // Read the preferences until we receive "generate"
        String line = scanner.nextLine();
        while (!line.equals("generate")) {
            // Parse the preference and store it in the map
            String[] tokens = line.split(" - ");
            String name = tokens[0];
            int place = Integer.parseInt(tokens[1]);
            preferences.put(name, place);

            line = scanner.nextLine();
        }

        // Create an array to represent the places in the cinema hall
        int[] places = new int[friends.size()];

        // Generate and print all possible distributions of friends
        generateDistributions(friends, places, preferences, 0);
    }
}