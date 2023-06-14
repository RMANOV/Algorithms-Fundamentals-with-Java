// Merge Sort
// Sort an array of elements using the famous merge sort algorithm.
//input - from line 1, array of integers
//output - sorted array of integers - with each element separated by a space

import java.util.*;

public class MergeSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" "); // read input as string array and split by space

        int[] arr = new int[input.length]; // create an integer array with the same length as the input array
        for (int i = 0; i < input.length; i++) {
            arr[i] = Integer.parseInt(input[i]); // convert each element in the input array to integer and store in the
                                                 // integer array
        }

        mergeSort(arr, 0, arr.length - 1); // call the mergeSort function to sort the integer array

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " "); // print the sorted integer array
        }
    }

    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2; // find the middle index of the array

            mergeSort(arr, l, m); // recursively call mergeSort on the left half of the array
            mergeSort(arr, m + 1, r); // recursively call mergeSort on the right half of the array

            merge(arr, l, m, r); // merge the two sorted halves of the array
        }
    }

    static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1, n2 = r - m; // find the sizes of the two subarrays

        int[] L = new int[n1], R = new int[n2]; // create two temporary arrays to store the two subarrays

        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i]; // copy the left subarray to the temporary array L
        }

        for (int i = 0; i < n2; i++) {
            R[i] = arr[m + 1 + i]; // copy the right subarray to the temporary array R
        }

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++]; // if the current element in L is smaller than or equal to the current element
                                   // in R, copy the element from L to the original array
            } else {
                arr[k++] = R[j++]; // otherwise, copy the element from R to the original array
            }
        }

        while (i < n1) {
            arr[k++] = L[i++]; // copy any remaining elements in L to the original array
        }

        while (j < n2) {
            arr[k++] = R[j++]; // copy any remaining elements in R to the original array
        }
    }
}