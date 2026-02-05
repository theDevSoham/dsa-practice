import java.util.*;

class Sorting2Solution {
    private static final Map<Integer, Sort> sortRegistry = new HashMap<>();

    static {
        sortRegistry.put(1, new MergeSort());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt(); // Read number of elements
        int[] arr = new int[size]; // Create array

        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt(); // Read each number
        }

        System.out.println("\nChoose sorting algorithm:");
        System.out.println("1 - Merge Sort");

        int choice = scanner.nextInt();

        scanner.close();

        // Just to verify input was stored correctly
        System.out.println("You entered:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        Sort sorter = sortRegistry.get(choice);

        if (sorter == null) {
            System.out.println("Invalid choice!");
            return;
        }

        sorter.sort(arr, size);

        System.out.println("\nSorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

interface Sort {
    void sort(int[] arr, int size);
}

class MergeSort implements Sort {
    /**
     * Divide and Merge
     * In arrays we have to play with indexes and not actually divide the array
     * We take low and high: Low represents the starting point of a hypothetical
     * arrat and high represents the end of same
     */
    public void sort(int[] arr, int size) {
        mergeSort(arr, size, 0, size - 1);
    }

    private void mergeSort(int[] arr, int size, int low, int high) {
        if (low >= high)
            return;
        int mid = (low + high) / 2; // can break for extreme cases of integer overflow
        mid = low + (high - low) / 2;

        mergeSort(arr, size, low, mid);
        mergeSort(arr, size, mid + 1, high);
        merge(arr, low, mid, high);
    }

    private void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int left = low;
        int right = mid + 1;
        int i = 0;

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp[i] = arr[left];
                left++;
            } else {
                temp[i] = arr[right];
                right++;
            }
            i++;
        }

        while (left <= mid) {
            temp[i] = arr[left];
            left++;
            i++;
        }

        while (right <= high) {
            temp[i] = arr[right];
            right++;
            i++;
        }

        for (int index = low; index <= high; index++) {
            arr[index] = temp[index - low];
        }
    }
}
