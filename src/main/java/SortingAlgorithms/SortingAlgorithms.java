package SortingAlgorithms;

import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {

    //----------------------------BUBBLE SORT ------------------------------------------------------
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Intercambia arr[j] y arr[j + 1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // QuickSort optimizado con pivote aleatorio
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = randomizedPartition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Método que elige un pivote aleatorio
    private static int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int randomPivot = low + rand.nextInt(high - low + 1);
        swap(arr, randomPivot, high);  // Colocamos el pivote aleatorio al final
        return partition(arr, low, high);
    }

    // Partición estándar de QuickSort
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // Método auxiliar para intercambiar elementos
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void stoogeSort(int[] L) {
        stoogeSort(L, 0, L.length - 1);
    }

    public static void stoogeSort(int[] L, int i, int j) {
        if (L[j] < L[i]) {
            int tmp = L[i];
            L[i] = L[j];
            L[j] = tmp;
        }
        if (j - i > 1) {
            int t = (j - i + 1) / 3;
            stoogeSort(L, i, j - t);
            stoogeSort(L, i + t, j);
            stoogeSort(L, i, j - t);
        }
    }


    //----------------------------PIGEONHOLE SORT ------------------------------------------------------
    public static void pigeonholeSort(int[] arr) {
        int min = Arrays.stream(arr).min().getAsInt();  // Encuentra el valor mínimo en el arreglo
        int max = Arrays.stream(arr).max().getAsInt();  // Encuentra el valor máximo en el arreglo
        int range = max - min + 1;  // Rango de números que necesitamos cubrir

        // Inicializamos los agujeros
        int[] holes = new int[range];

        // Rellenar los agujeros
        for (int num : arr) {
            holes[num - min]++;  // Usamos el valor ajustado por min para evitar índices negativos
        }

        // Volver a llenar el array original
        int index = 0;
        for (int i = 0; i < range; i++) {
            while (holes[i]-- > 0) {
                arr[index++] = i + min;
            }
        }
    }


    //----------------------------MERGE SORT ------------------------------------------------------
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            arr[k++] = leftArr[i++];
        }
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }


    //----------------------------BITONIC SORT ------------------------------------------------------
    public static void bitonicSort(int[] arr, int low, int n, boolean ascending) {
        if (n > 1) {
            int mid = n / 2;
            bitonicSort(arr, low, mid, true);         // Ordena la primera mitad en orden ascendente
            bitonicSort(arr, low + mid, mid, false);  // Ordena la segunda mitad en orden descendente
            bitonicMerge(arr, low, n, ascending);     // Combina las dos mitades
        }
    }

    private static void bitonicMerge(int[] arr, int low, int n, boolean ascending) {
        if (n > 1) {
            int mid = n / 2;
            for (int i = low; i < low + mid; i++) {
                if (ascending == (arr[i] > arr[i + mid])) {
                    // Intercambia si no están en el orden deseado
                    int temp = arr[i];
                    arr[i] = arr[i + mid];
                    arr[i + mid] = temp;
                }
            }
            bitonicMerge(arr, low, mid, ascending);
            bitonicMerge(arr, low + mid, mid, ascending);
        }
    }


}
