// Tower of Hanoi
// Your task is to solve the famous Tower of Hanoi puzzle using recursion.
// In this problem, you have three rods (let’s call them source, destination, and spare). Initially, there are n disks, all
// placed on the source rod like in the picture below:
// Your objective is to move all disks from the source rod to the destination rod. There are several rules:
// © SoftUni – about.softuni.bg. Copyrighted document. Unauthorized copy, reproduction or use is not permitted.

// Follow us: Page 3 of 9
// 1) Only one disk can be moved at a time
// 2) Only the topmost disk on a rod can be moved
// 3) A disk can only be placed on top of a larger disk or on an empty rod

// Step 1. Choose Appropriate Data Structures
// First, we need to decide how to model the problem in our program. The size of a disk can be represented by an integer
// number – the larger the number, the larger the disk.
// How about the rods? According to the rules outlined above, we can either take a disk from the top of the rod or place
// a disk on top of it. This is an example of Last-In-First-Out (LIFO), therefore, an appropriate structure to represent a
// rod would be Stack we need three of them to be precise – the source, the destination, and the spare.
// Step 2. Setup
// Now that we have an idea of what structures we’ll be using, it’s time for the initial setup. Before solving the puzzle for
// any number of disks, let’s solve it with 3 and use hardcoded values. With 3 disks, it will be easier to keep track of the
// steps we’ll take.
// Initially, the destination and spare are empty. In the source, we need to have the numbers 1, 2, and 3, 1 being on top.
// Step 3. Breaking down the Problem
// The Tower of Hanoi is solved by breaking it down into sub-problems. What we’ll try to do is:
// 1) Move all disks from source to destination starting with the largest (bottom disk)
// a) If the bottom disk is equal to 1, we can simply move it
// b) If the bottom disk is larger than 1
// I. move all disks above it (starting from bottom – 1) to the spare rod
// II. move the bottom disk to the destination
// III. finally, move the disks now on spare to destination (back on top of the bottom disk)
// In essence, steps 1.b.i and 1.b.iii repeat step 1, the only difference is that we’re viewing different rods as a source,
// destination, and spare.
// Step 4. Solution
// Looking at step 3 above, it’s apparent that we’ll need a method that takes 4 arguments: the value of the bottom disk
// and the three rods (stacks).
// We need an if-statement to check if disk == 1 (the bottom of our recursion). If that’s the case, we’ll pop an element
// from the source and push it to the destination. We can do it on a single line like this:
// In the else clause, we need to do three things: 1) move all disks from bottomDisk - 1 from source to spare; 2) move
// the bottomDisk from source to destination; 3) move all disks from bottomDisk – 1 from spare to destination.
// Complete the TODOs in the above picture, by calling MoveDisks recursively. If you did everything correctly, this should
// be it! Now it’s time to test it.
// Step 5. Check Solution with Hardcoded Value
// In order to check this solution, let’s make the three stacks static and declare an additional variable that will keep track
// of the current number of steps taken.
// We’ll need a method that prints the contents of all stacks, so we know which disk is where after each step:
// After running the program, you should now see each step of the process like this:
// The Tower of Hanoi puzzle always takes exactly 2
// n – 1 steps. With n == 3, all seven steps should be shown and in the
// end all disks should end up on the destination rod.
// Using the output of your program and the debugger, follow each step and try to understand how this recursive
// algorithm works. It’s much easier to see this with three disks.
// Step 6. Remove Hardcoded Values and Retest
// If everything went well and you’re confident you’ve understood the process, you can replace 3 with input from the
// user, just read a number from the console.
// Test with several different values, and make sure that the steps taken are 2
// n – 1 and that all disks are successfully
// moved from source to destination.
// Here is the full example with 3 disks:
// Input - 3
// Output:
// Source: 3, 2, 1
// Destination:
// Spare:
// Step #1: Moved disk
// Source: 3, 2
// Destination: 1
// Spare:
// Step #2: Moved disk
// Source: 3
// Destination: 1
// Spare: 2
// Step #3: Moved disk
// Source: 3
// Destination:
// Spare: 2, 1
// Step #4: Moved disk
// Source:
// Destination: 3
// Spare: 2, 1
// Step #5: Moved disk
// Source: 1
// Destination: 3
// Spare: 2
// Step #6: Moved disk
// Source: 1
// Destination: 3, 2
// Spare:
// Step #7: Moved disk
// © SoftUni – about.softuni.bg. Copyrighted document. Unauthorized copy, reproduction or use is not permitted.

// Follow us: Page 6 of 9
// Source:
// Destination: 3, 2, 1
// Spare:

import java.util.*;

public class TowerOfHanoi {
    private static int stepsTaken = 0;
    private static Stack<Integer> source = new Stack<>();
    private static Stack<Integer> destination = new Stack<>();
    private static Stack<Integer> spare = new Stack<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfDisks = Integer.parseInt(scanner.nextLine());

        for (int i = numberOfDisks; i >= 1; i--) {
            source.push(i);
        }

        printRods();
        moveDisks(numberOfDisks, source, destination, spare);
    }

    private static void moveDisks(int bottomDisk, Stack<Integer> source, Stack<Integer> destination,
            Stack<Integer> spare) {
        if (bottomDisk == 1) {
            stepsTaken++;
            destination.push(source.pop());
            System.out.println("Step #" + stepsTaken + ": Moved disk");
            printRods();
        } else {
            moveDisks(bottomDisk - 1, source, spare, destination);
            stepsTaken++;
            destination.push(source.pop());
            System.out.println("Step #" + stepsTaken + ": Moved disk");
            printRods();
            moveDisks(bottomDisk - 1, spare, destination, source);
        }
    }

    private static void printRods() {
        System.out.println("Source: " + source.toString());
        System.out.println("Destination: " + destination.toString());
        System.out.println("Spare: " + spare.toString());
        System.out.println();
    }
}
