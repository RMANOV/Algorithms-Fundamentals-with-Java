import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().substring(5));

        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);

        for (Map.Entry<Integer, Integer> usedCoin : usedCoins.entrySet()) {
            System.out.println(usedCoin.getKey() + " -> " + usedCoin.getValue());
        }
    }

    public static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
        // TODO
        Map<Integer, Integer> usedCoins = new LinkedHashMap<>();
        List<Integer> sortedCoins = Arrays.stream(coins).boxed().sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        int currentSum = 0;
        int index = 0;
        while (currentSum != targetSum && index < sortedCoins.size()) {
            int currentCoin = sortedCoins.get(index);
            int remainingSum = targetSum - currentSum;
            int numberOfCoins = remainingSum / currentCoin;
            if (numberOfCoins > 0) {
                usedCoins.put(currentCoin, numberOfCoins);
                currentSum += currentCoin * numberOfCoins;
            }
            index++;
        }
        if (currentSum != targetSum) {
            throw new IllegalArgumentException();
        }
        return usedCoins;
    }
}