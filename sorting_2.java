import java.util.*;

class Sorting2Solution {
    private static final Map<Integer, Sort> sortRegistry = new HashMap<>();

    static {
        sortRegistry.put(1, new MergeSort());
        sortRegistry.put(2, new QuickSort());
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
        System.out.println("2 - Quick Sort");

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
     * array and high represents the end of same
     * Follows divide and conquer
     * Break array till one element and then arrange them in the correct order
     * Time complexity: O(NlogN) because N for traversing the full array and log N
     * for merging/arranging each broken piece
     * Space complexity: O(N) as we use temporary array to store the correct
     * arranged order before applying it to the actual array
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

class QuickSort implements Sort {
    public void sort(int[] arr, int size) {
        quickSort(arr, 0, size - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low >= high)
            return;

        int partition = partitionIndex(arr, low, high);
        quickSort(arr, low, partition - 1);
        quickSort(arr, partition + 1, high);
    }

    private int partitionIndex(int[] arr, int low, int high) {
        int pivot = low;
        int i = low;
        int j = high;

        while (i < j) {
            while (arr[i] <= arr[pivot] && i <= high - 1)
                i++;
            while (arr[j] > arr[pivot] && j >= low + 1)
                j--;

            if (i < j)
                Swap.swapByIndex(i, j, arr);

        }
        Swap.swapByIndex(pivot, j, arr);
        return j;
    }
}

class Swap {
    public static void swapByIndex(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
