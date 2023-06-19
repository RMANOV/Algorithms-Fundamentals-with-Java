// Salaries
// We have a hierarchy between the employees in a company. Employees can have one or several direct managers.
// People who manage nobody are called regular employees and their salaries are 1. People who manage at least one
// employee are called managers. Each manager takes a salary that is equal to the sum of the salaries of their directly
// managed employees. Managers cannot manage directly or indirectly (transitively) themselves. Some employees
// might have no manager (like the big boss). See a sample hierarchy in a company along with the salaries computed
// following the above-described rule:
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
// 1. Read the number of employees N.
// 2. Create a graf with N nodes.
// 3. Read the hierarchy of each employee and store it in the graf.
// 4. Create an variable to store the total salary.
// 5. For each employee calculate their salary and add it to the total salary.
// * if there is a cycle in the graf - calculate the salary of the employee only once.
// * if the salary of the employee is already calculated - return it.
// * if the employee has no subordinates - their salary is 1.
// * if the employee has subordinates - their salary is the sum of the salaries of their subordinates.
// 6. Print the total salary.



import java.util.*;

public class Salaries {
    private static long[] salaries; // array to store the salaries of each employee
    private static List<Integer>[] subordinates; // adjacency list to store the subordinates of each employee
    private static boolean[] visited; // array to mark visited nodes during DFS traversal

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // read the number of employees
        scanner.nextLine();

        subordinates = new ArrayList[N]; // initialize the adjacency list
        salaries = new long[N]; // initialize the salaries array
        visited = new boolean[N]; // initialize the visited array
        int[] managersCount = new int[N]; // array to store the number of managers for each employee

        for (int i = 0; i < N; i++) {
            subordinates[i] = new ArrayList<>(); // initialize the subordinates list for each employee
        }

        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < N; j++) {
                if (line.charAt(j) == 'Y') {
                    subordinates[i].add(j); // add the subordinate to the list of the current employee
                    managersCount[j]++; // increment the number of managers for the subordinate
                }
            }
        }

        List<Integer> sources = new ArrayList<>(); // list to store the sources (employees with no managers)
        for (int i = 0; i < N; i++) {
            if (managersCount[i] == 0) {
                sources.add(i); // add the employee to the sources list if they have no managers
            }
        }

        for (int source : sources) {
            dfs(source); // perform DFS traversal starting from each source
        }

        long totalSalary = Arrays.stream(salaries).sum(); // calculate the total salary
        System.out.println(totalSalary); // print the total salary
    }

    private static void dfs(int node) {
        if (visited[node]) {
            return; // if the node is already visited, return
        }
        visited[node] = true; // mark the node as visited
        long salarySum = 0;
        for (int child : subordinates[node]) {
            dfs(child); // perform DFS traversal on the child
            salarySum += salaries[child]; // add the salary of the child to the salary sum
        }
        salaries[node] = (salarySum == 0) ? 1 : salarySum; // calculate the salary of the current employee
    }
}
