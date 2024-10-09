package SearchingAlgorithms;

public class SearchingAlgorithms {

    //----------------------------LINEAR SEARCH ------------------------------------------------------
    public static void linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                System.out.println("Linear Search: Número encontrado en la posición " + i);
                return; // Sale de la función si encuentra el elemento
            }
        }
        System.out.println("Linear Search: Número no encontrado");
    }

    //----------------------------LIMITED LINEAR SEARCH ------------------------------------------------------
    public static void limitedLinearSearch(int[] arr, int target, int limit) {
        for (int i = 0; i < Math.min(arr.length, limit); i++) {
            if (arr[i] == target) {
                System.out.println("Limited Linear Search: Número encontrado en la posición " + i);
                return; // Sale de la función si encuentra el elemento
            }
        }
        System.out.println("Limited Linear Search: Número no encontrado");
    }

    //----------------------------BINARY SEARCH ------------------------------------------------------
    public static void binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Comprobar si el elemento está en el medio
            if (arr[mid] == target) {
                System.out.println("Binary Search: Número encontrado en la posición " + mid);
                return; // Sale de la función si encuentra el elemento
            }

            // Si el elemento es mayor, ignorar la mitad izquierda
            if (arr[mid] < target) {
                left = mid + 1;
            }
            // Si el elemento es menor, ignorar la mitad derecha
            else {
                right = mid - 1;
            }
        }
        System.out.println("Binary Search: Número no encontrado");
    }

    //----------------------------JUMP SEARCH ------------------------------------------------------
    public static void jumpSearch(int[] arr, int x) {
        int n = arr.length;

        // Finding block size to be jumped
        int step = (int) Math.floor(Math.sqrt(n));

        // Finding the block where the element is present (if it is present)
        int prev = 0;
        while (prev < n && arr[Math.min(step, n) - 1] < x) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) {
                System.out.println("Jump Search: Número no encontrado");
                return; // Sale de la función si hemos pasado del tamaño del arreglo
            }
        }

        // Doing a linear search for x in block beginning with prev.
        while (prev < n && arr[prev] < x) {
            prev++;

            // If we reached the next block or end of array, element is not present.
            if (prev == Math.min(step, n)) {
                System.out.println("Jump Search: Número no encontrado");
                return; // Sale de la función si no encuentra el número en el bloque
            }
        }

        // If element is found
        if (prev < n && arr[prev] == x) {
            System.out.println("Jump Search: Número encontrado en la posición " + prev);
            return; // Sale de la función si encuentra el elemento
        }

        System.out.println("Jump Search: Número no encontrado");
    }

}
