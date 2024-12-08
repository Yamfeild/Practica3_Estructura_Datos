package com.example.rest;

import java.util.Arrays;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        int size = 25000;
        int[] originalArray = generateRandomArray(size);

        // Quick Sort
        int[] quickSortArray = Arrays.copyOf(originalArray, originalArray.length);
        long startQuickSort = System.nanoTime();
        quickSort(quickSortArray, 0, quickSortArray.length - 1);
        long endQuickSort = System.nanoTime();
        System.out.println("Quick Sort Time: " + (endQuickSort - startQuickSort) / 1e6 + " ms");

        // Merge Sort
        int[] mergeSortArray = Arrays.copyOf(originalArray, originalArray.length);
        long startMergeSort = System.nanoTime();
        mergeSort(mergeSortArray, 0, mergeSortArray.length - 1);
        long endMergeSort = System.nanoTime();
        System.out.println("Merge Sort Time: " + (endMergeSort - startMergeSort) / 1e6 + " ms");

        // Shell Sort
        int[] shellSortArray = Arrays.copyOf(originalArray, originalArray.length);
        long startShellSort = System.nanoTime();
        shellSort(shellSortArray);
        long endShellSort = System.nanoTime();
        System.out.println("Shell Sort Time: " + (endShellSort - startShellSort) / 1e6 + " ms");
    }

    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100000); // Numbers between 0 and 99999
        }
        return array;
    }

    // Quick Sort
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Merge Sort
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < n1) {
            array[k++] = leftArray[i++];
        }
        while (j < n2) {
            array[k++] = rightArray[j++];
        }
    }

    // Shell Sort
    public static void shellSort(int[] array) {
        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }
}
