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

    public static List<List<Integer>> graph = new ArrayList<>(); // create a graph to store the hierarchy of the employees

    public static long[] salaries; // create an array to store the salaries of the employees
    public static boolean[] visited; // create an array to store whether an employee has been visited during the DFS traversal

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int employees = Integer.parseInt(scanner.nextLine()); // read the number of employees

        salaries = new long[employees]; // initialize the salaries array with the number of employees
        visited = new boolean[employees]; // initialize the visited array with the number of employees

        int[] managersCount = new int[employees]; // create an array to store the number of managers for each employee

        for (int i = 0; i < employees; i++) {
            graph.add(new ArrayList<>()); // add a new list to the graph for each employee
            String line = scanner.nextLine(); // read the hierarchy of the current employee

            for (int emp = 0; emp < line.length(); emp++) {
                char letter = line.charAt(emp);
                if (letter == 'Y') {
                    managersCount[emp]++; // increment the number of managers for the current employee
                    graph.get(i).add(emp); // add the current employee to the list of employees managed by the current manager
                }
            }
        }

        List<Integer> sources = new ArrayList<>(); // create a list to store the sources (employees with no managers)

        for (int i = 0; i < managersCount.length; i++) {
            if (managersCount[i] == 0) {
                sources.add(i); // add the current employee to the sources list if they have no managers
            }
        }

        for (Integer source : sources) {
            dfs(source); // perform a DFS traversal starting from each source
        }


        long sum = Arrays.stream(salaries)
                .sum(); // calculate the sum of all salaries

        System.out.println(sum); // print the sum of all salaries
    }

    private static void dfs(int node) {
        if (visited[node]) {
            return; // if the current employee has already been visited, return
        }

        visited[node] = true; // mark the current employee as visited

        for (Integer child : graph.get(node)) { // for each employee managed by the current manager
            if (!visited[child]) { // if the employee has not been visited
                dfs(child); // perform a DFS traversal starting from the employee
                long sum = graph.get(child).stream()
                        .mapToLong(c -> salaries[c])
                        .sum(); // calculate the sum of the salaries of the employees managed by the employee

                salaries[child] = sum == 0 ? 1 : sum; // set the salary of the employee to the sum of the salaries of the employees managed by the employee, or 1 if the employee has no subordinates
            }
        }

        long sum = graph.get(node).stream()
                .mapToLong(c -> salaries[c])
                .sum(); // calculate the sum of the salaries of the employees managed by the current manager

        salaries[node] = sum == 0 ? 1 : sum; // set the salary of the current manager to the sum of the salaries of the employees managed by the current manager, or 1 if the manager has no subordinates
    }
}
