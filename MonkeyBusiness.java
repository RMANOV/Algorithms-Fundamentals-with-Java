// 1.	Monkey Business
// So nothing to do right? But how is that even possible. No matter what there is always something to be done and you never get bored doing the Monkey Business.
// You have found a number N and now you want to see in how many ways you can combine the numbers from 1 to N in such a way 
// that by using addition or subtraction you will end up with zero as a result. 
// Find the possible expressions print them on the console each on a new line then followed by the total number of solutions in the following format:
// •	Total Solutions: {possibleSolutions}
// 1.	Input
// •	The input will come from the console on single line.
// 2.	Output
// •	The output is each expression on a new line followed by the total solutions see the examples below.
// 3.	Constraints
// •	The input will only be a single positive integer.
// •	The input will be in the range [1…25]
// input - 4
// output
// +1 -2 -3 +4 
// -1 +2 +3 -4 
// Total Solutions: 2

import java.util.Scanner;

public class MonkeyBusiness {

    private static int[] numbers;
    private static int solutions = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }
        int[] signs = new int[n];
        generateSigns(signs, 0);
        System.out.println("Total Solutions: " + solutions);
    }

    private static void generateSigns(int[] signs, int index) {
        if (index == signs.length) {
            checkSolution(signs);
            return;
        }
        for (int i = 0; i < 2; i++) {
            signs[index] = i;
            generateSigns(signs, index + 1);
        }
    }

    private static void checkSolution(int[] signs) {
        int sum = 0;
        StringBuilder expression = new StringBuilder();
        for (int j = 0; j < signs.length; j++) {
            if (signs[j] == 0) {
                sum += numbers[j];
                expression.append(" +").append(numbers[j]);
            } else {
                sum -= numbers[j];
                expression.append(" -").append(numbers[j]);
            }
        }
        if (sum == 0) {
            System.out.println(expression);
            solutions++;
        }
    }
}
