package com.example.jahirul.arraysorting;

/**
 * Created by Jahirul on 9/10/2015.
 * Project utility class for different sorting mechanism
 * all of the algorithm created without using any system library
 */
public class SortingUtility {

    // Counting Sort function
    // complexity Ω(n log n)
    public void countingSort( int[] arr )
    {
        int MAX_RANGE = 1000000;
        int N = arr.length;
        if (N == 0)
            return;
        // find max and min values
        int max = arr[0], min = arr[0];
        for (int i = 1; i < N; i++)
        {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }
        int range = max - min + 1;

        // check if range is small enough for count array
        // else it might give out of memory exception while allocating memory for array
        if (range > MAX_RANGE)
        {
            System.out.println("\nError : Range too large for sort");
            return;
        }

        int[] count = new int[range];
        // make count/frequency array for each element
        for (int i = 0; i < N; i++)
            count[arr[i] - min]++;
        // modify count so that positions in final array is obtained
        for (int i = 1; i < range; i++)
            count[i] += count[i - 1];
        // modify original array
        int j = 0;
        for (int i = 0; i < range; i++)
            while (j < count[i])
                arr[j++] = i + min;
    }
    // Bubble Sort function
    // complexity О(n2)
    public void bubbleSort(int[] arr) {

        int n = arr.length;
        int temp = 0;

        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){

                if(arr[j-1] > arr[j]){
                    //swap the elements!
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }

            }
        }

    }

    // Insertion Sort function
    // complexity О(n2)
    public static void insertionSort( int[] arr )
    {
        int N = arr.length;
        int i, j, temp;
        for (i = 1; i< N; i++)
        {
            j = i;
            temp = arr[i];
            while (j > 0 && temp < arr[j-1])
            {
                arr[j] = arr[j-1];
                j = j-1;
            }
            arr[j] = temp;
        }
    }

    // Merge Sort function
    // complexity O(n log n)
    public void mergeSort(int[] arr, int low, int high)
    {
        int N = high - low;
        if (N <= 1)
            return;
        int mid = low + N/2;
        // recursively sort
        mergeSort(arr, low, mid);
        mergeSort(arr, mid, high);
        // merge two sorted subarrays
        int[] temp = new int[N];
        int i = low, j = mid;
        for (int k = 0; k < N; k++)
        {
            if (i == mid)
                temp[k] = arr[j++];
            else if (j == high)
                temp[k] = arr[i++];
            else if (arr[j]<arr[i])
                temp[k] = arr[j++];
            else
                temp[k] = arr[i++];
        }
        for (int k = 0; k < N; k++)
            arr[low + k] = temp[k];
    }
}
