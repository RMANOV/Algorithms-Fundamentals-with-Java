// Quicksort
// Sort an array of elements using the famous quicksort.
// input - from line 1, array of integers
// output - sorted array of integers - with each element separated by a space

import java.util.*;

public class Quicksort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" "); // read input as string array and split by space

        int[] arr = new int[input.length]; // create an integer array with the same length as the input array
        for (int i = 0; i < input.length; i++) {
            arr[i] = Integer.parseInt(input[i]); // convert each element in the input array to integer and store in the
                                                 // integer array
        }

        quickSort(arr, 0, arr.length - 1); // call the quickSort function to sort the integer array

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " "); // print the sorted integer array
        }
    }

    static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int p = partition(arr, l, r); // find the pivot element and partition the array around it

            quickSort(arr, l, p - 1); // recursively call quickSort on the left half of the array
            quickSort(arr, p + 1, r); // recursively call quickSort on the right half of the array
        }
    }

    static int partition(int[] arr, int l, int r) {
        int pivot = arr[r]; // choose the last element as the pivot
        int i = l - 1; // initialize the index of the smaller element

        for (int j = l; j < r; j++) {
            if (arr[j] < pivot) {
                i++; // increment the index of the smaller element
                swap(arr, i, j); // swap the current element with the smaller element
            }
        }

        swap(arr, i + 1, r); // swap the pivot element with the element at the index of the smaller element
        return i + 1; // return the index of the pivot element
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; // swap the elements at indices i and j
        arr[i] = arr[j];
        arr[j] = temp;
    }
}