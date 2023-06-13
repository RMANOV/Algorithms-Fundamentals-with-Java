// binary search
// input - array of integers
// in first line - sorted array
// in second line - target element
// output - index of target element

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinarySearch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] array = readArray(br);
        int target = Integer.parseInt(br.readLine());
        System.out.println(binarySearch(array, target));
    }

    private static int[] readArray(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] array = new int[st.countTokens()];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        return array;
    }

    private static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (array[middle] < target) {
                left = middle + 1;
            } else if (array[middle] > target) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
