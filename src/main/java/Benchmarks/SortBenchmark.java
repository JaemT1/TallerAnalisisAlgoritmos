package Benchmarks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import SortingAlgorithms.SortingAlgorithms;

public class SortBenchmark {

    public static double measureSortTime(int[] array, Runnable sortAlgorithm) {
        long startTime = System.nanoTime();
        sortAlgorithm.run();  // Ejecutar el algoritmo de ordenamiento
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;  // Convertir nanosegundos a segundos
    }

    public static Map<String, Double> benchmarkSorts(int[] arr) {
        Map<String, Double> results = new HashMap<>();

        results.put("Bubble Sort", measureSortTime(arr, () -> SortingAlgorithms.bubbleSort(arr)));
        results.put("Quick Sort", measureSortTime(arr, () -> SortingAlgorithms.quickSort(arr, 0, arr.length - 1)));
        results.put("Stooge Sort", measureSortTime(arr, () -> SortingAlgorithms.stoogeSort(arr, 0, arr.length - 1)));
        results.put("Pigeonhole Sort", measureSortTime(arr, () -> SortingAlgorithms.pigeonholeSort(arr)));
        results.put("Merge Sort", measureSortTime(arr, () -> SortingAlgorithms.mergeSort(arr, 0, arr.length - 1)));
        results.put("Bitonic Sort", measureSortTime(arr, () -> SortingAlgorithms.bitonicSort(arr, 0, arr.length, true)));

        return results;
    }


}
