package Benchmarks;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import SearchingAlgorithms.SearchingAlgorithms;
import SortingAlgorithms.SortingAlgorithms;
import Utils.FileReaderUtil;

public class SortBenchmark {

    public static double measureSortTime(int[] array, Runnable sortAlgorithm) {
        long startTime = System.nanoTime();
        sortAlgorithm.run();  // Ejecutar el algoritmo de ordenamiento
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;  // Convertir nanosegundos a segundos
    }

    public static double measrueSearchTime(int[] array, Runnable searchAlgorithm) {
        long startTime = System.nanoTime();
        searchAlgorithm.run();  // Ejecutar el algoritmo de búsqueda
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

    public static Map<String, Double> benchmarkSearch(int[] arr) throws IOException {
        int numberToFind = FileReaderUtil.readRandomLineFromFile("./numbersFiles/OneMillionNumbers.txt");
        //int numberToFind = 99999999;
        Map<String, Double> resultsSearch = new HashMap<>();
        Arrays.sort(arr);

        resultsSearch.put("Lineal Search", measureSortTime(arr, () -> SearchingAlgorithms.linearSearch(arr, numberToFind)));
        resultsSearch.put("Limited Linear Search", measureSortTime(arr, () -> SearchingAlgorithms.limitedLinearSearch(arr, numberToFind, 1000000)));
        resultsSearch.put("Binary Search", measureSortTime(arr, () -> SearchingAlgorithms.binarySearch(arr, numberToFind)));
        resultsSearch.put("Jump Search", measureSortTime(arr, () -> SearchingAlgorithms.jumpSearch(arr, numberToFind)));

        return resultsSearch;
    }


}