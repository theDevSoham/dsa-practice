import java.util.*;

class Sorting1Solution {
    private static final Map<Integer, Sort> sortRegistry = new HashMap<>();

    static {
        sortRegistry.put(1, new SelectionSort());
        sortRegistry.put(2, new BubbleSort());
        sortRegistry.put(3, new InsertionSort());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt(); // Read number of elements
        int[] arr = new int[size]; // Create array

        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt(); // Read each number
        }

        System.out.println("\nChoose sorting algorithm:");
        System.out.println("1 - Selection Sort");
        System.out.println("2 - Bubble Sort");
        System.out.println("3 - Insertion Sort");

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

class BubbleSort implements Sort {
    /**
     * Select maximums and and sort at the end of the array (swap)
     * Continue with next elements
     * Run till the 2nd last element because if ith element is accessed as the last element i + 1 will be null
     * Time complexity (worst, average) = O(n^2)
     * Time complexity (best) = O(n)
     */
    public void sort(int[] arr, int size) {
        for (int i = size - 1; i > 0; i--) {
            int countSwaps = 0; 
            /**
             * This will keep count of swaps performed. 
             * If no swaps performed in a single loop then the array is already sorted.
             * Loop should break out.
            */
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    Swap.swapByIndex(j, j + 1, arr);
                    countSwaps++;
                }
            }
            /**
             * Best case: array is already sorted. 
             * Then coutSwaps is 0
             * Break out of loop making is O(n)
             */
            if (countSwaps == 0) break;
        }
    }
}

class SelectionSort implements Sort {
    /**
     * Select minimums and sort (swap with the first element)
     * continue with the next elements
     * Run for n - 1 times
     * Time complexity (Best, worst, average) = O(n^2)
     */
    public void sort(int[] arr, int size) {
        for (int i = 0; i <= size - 1; i++) {
            int minIndex = i;
            for (int j = i; j <= size - 1; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            Swap.swapByIndex(minIndex, i, arr);
        }
    }
}

class InsertionSort implements Sort {
    /**
     * Create a window size and expand it till n - 1 represented by i
     * inside the window take j as the last element and think of it as it is in the correct position
     * Check if it is correct position or not by checking with it's left adjacent element
     * if left adjacent element is greater then swap.
     * In this way, each ith element will be inserted at it's correct position when the inner loop finishes
     * Time complexity (worst, average) = O(n^2)
     * Time complexity (best) = O(n) because if the array is already sorted, it never goes into the while condition hence no inner loop
     */
    public void sort(int[] arr, int size) {
        for (int i = 0; i <= size - 1; i++) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                Swap.swapByIndex(j - 1, j, arr);
                j--;
            } 
        }
    }
}

class Swap {
    public static void swapByIndex(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
