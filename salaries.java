// Salaries
// We have a hierarchy between the employees in a company. Employees can have one or several direct managers.
// People who manage nobody are called regular employees and their salaries are 1. People who manage at least one
// employee are called managers. Each manager takes a salary that is equal to the sum of the salaries of their directly
// managed employees. Managers cannot manage directly or indirectly (transitively) themselves. Some employees
// might have no manager (like the big boss). See a sample hierarchy in a company along with the salaries computed
// following the above-described rule:
// © SoftUni – about.softuni.bg. Copyrighted document. Unauthorized copy, reproduction or use is not permitted.

// Follow us: Page 5 of 10
// In the above example, employees 0 and 3 are regular employees and take salary 1. All others are managers and take
// the sum of the salaries of their directly managed employees. For example, manager 1 takes salary 3 + 2 + 1 = 6 (sum
// of the salaries of employees 2, 5 and 0). In the above example employees, 4 and 1 have no manager.
// If we have N employees, they will be indexed from 0 to N – 1. For each employee, you’ll be given a string with N
// symbols. The symbol at given index i, either 'Y' or 'N', shows whether the current employee is a direct manager of
// employee i.
// Hint: find the node with no parent and start a DFS traversal from it to calculate the salaries on the tree recursively.
// Input
// • The input data should be read from the console.
// • On the first line, you’ll be given an integer N.
// • On the next N lines, you’ll be given strings with N symbols (either 'Y' or 'N').
// • The input data will always be valid and in the format described. There is no need to check it explicitly.
// Output
// • The output should be printed on the console. It should consist of one line.
// • On the only output line print the sum of the salaries of all employees.
// Constraints
// • N will be an integer in the range [1 … 50].
// • For each i-th line, the i-th symbol will be 'N'.
// • If employee A is the manager of employee B, B will not be a manager of A.
// • Allowed working time for your program: 0.1 seconds. Allowed memory: 16 MB.
// Examples
// Input
// 5


import java.util.*;

public class Salaries {

    private static int[] salaries; // array to store the salaries of each employee
    private static boolean[][] subordinates; // 2D array to store the hierarchy of the employees

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // read the number of employees
        scanner.nextLine();

        subordinates = new boolean[N][N]; // initialize the 2D array with the size of N
        salaries = new int[N]; // initialize the salaries array with the size of N

        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine(); // read the hierarchy of the i-th employee
            for (int j = 0; j < N; j++) {
                subordinates[i][j] = (line.charAt(j) == 'Y'); // store the hierarchy of the i-th employee
            }
        }

        int totalSalary = 0; // initialize the total salary to 0
        for (int i = 0; i < N; i++) {
            totalSalary += getSalary(i); // calculate the salary of the i-th employee and add it to the total salary
        }

        System.out.println(totalSalary); // print the total salary
    }

    private static int getSalary(int employee) {
        if (salaries[employee] > 0) { // if the salary of the employee is already calculated, return it
            return salaries[employee];
        }
        for (int i = 0; i < subordinates.length; i++) {
            if (subordinates[employee][i]) { // if the i-th employee is a subordinate of the employee, add their salary to the employee's salary
                salaries[employee] += getSalary(i);
            }
        }
        if (salaries[employee] == 0) { // if the employee has no subordinates, their salary is 1
            salaries[employee] = 1;
        }
        return salaries[employee]; // return the salary of the employee
    }
}
