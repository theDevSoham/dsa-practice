import java.util.*;

class ArrayEasySolution {
    private static final Map<Integer, Solution<?>> arraySolutions = new HashMap<>();

    static {
        arraySolutions.put(1, new LargestElement());
        arraySolutions.put(2, new SecondLargestElement());
        arraySolutions.put(3, new CheckIfArrayIsSorted());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt(); // Read number of elements
        int[] arr = new int[size]; // Create array

        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt(); // Read each number
        }

        System.out.println("\nChoose sorting algorithm:");
        System.out.println("1 - Find Largest Element");
        System.out.println("2 - Find Second Largest Element");
        System.out.println("3 - Check if array is sorted");

        int choice = scanner.nextInt();

        scanner.close();

        // Just to verify input was stored correctly
        System.out.println("You entered:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        Solution<?> solutionFinder = arraySolutions.get(choice);

        if (solutionFinder == null) {
            System.out.println("Invalid choice!");
            return;
        }

        Object result = solutionFinder.solution(arr, size);
        System.out.println("\nResult: \n" + result);
    }
}

interface Solution<T> {
    public T solution(int[] arr, int length);
}

class LargestElement implements Solution<Integer> {
    @Override
    public Integer solution(int[] arr, int length) {
        int largest = arr[0];
        for (int i = 1; i < length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }
        return largest;
    }
}

class SecondLargestElement implements Solution<Integer> {
    @Override
    public Integer solution(int[] arr, int length) {
        int largest = arr[0];
        int secondLargest = arr[1];

        for (int i = 1; i < length; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] > secondLargest && arr[i] < largest) {
                secondLargest = arr[i];
            }
        }

        return secondLargest;
    }
}

class CheckIfArrayIsSorted implements Solution<Boolean> {
    @Override
    public Boolean solution(int[] arr, int length) {
        for (int i = 1; i < length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }

        return true;
    }
}
 