import java.util.*;

class ArrayEasySolution {
    private static final Map<Integer, Solution<?>> arraySolutions = new HashMap<>();

    static {
        arraySolutions.put(1, new LargestElement());
        arraySolutions.put(2, new SecondLargestElement());
        arraySolutions.put(3, new CheckIfArrayIsSorted());
        arraySolutions.put(4, new LinearSearch());
        arraySolutions.put(5, new UnionOfSortedArrays());
        arraySolutions.put(6, new LongestSubArrayWithGivenSum());
        arraySolutions.put(7, new LengthOfLogngestSubarrayWithZeroSum());
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
        System.out.println("4 - Linear search");
        System.out.println("5 - Union of sorted arrays");
        System.out.println("6 - Longest Subarray with given sum");
        System.out.println("7 - Longest Subarray with zero sum");

        int choice = scanner.nextInt();

        ExecutionContext ctx = new ExecutionContext(arr);

        if (choice == 4) {
            System.out.print("\nEnter target: ");
            ctx.put("target", scanner.nextInt());
        }
        if (choice == 5) {
            System.out.print("\nEnter second array size: \n");
            int size2 = scanner.nextInt(); // Read number of elements
            int[] arr2 = new int[size2]; // Create array

            for (int j = 0; j < size2; j++) {
                arr2[j] = scanner.nextInt(); // Read each number
            }
            ctx.put("size2", size2);
            ctx.put("array2", arr2);
        }
        if (choice == 6) {
            System.out.print("\nEnter the value of sum: \n");
            int givenSum = scanner.nextInt(); // Read number of elements
            ctx.put("k", givenSum);
        }
        scanner.close();
        Solution<?> solutionFinder = arraySolutions.get(choice);

        if (solutionFinder == null) {
            System.out.println("Invalid choice!");
            return;
        }

        Object result = solutionFinder.execute(ctx);

        // Just to verify input was stored correctly
        System.out.println("You entered:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println("\nResult: \n" + result);
    }
}

class ExecutionContext {
    private final int[] arr;
    private final int length;
    private final Map<String, Object> params = new HashMap<>();

    public ExecutionContext(int[] arr) {
        this.arr = arr;
        this.length = arr.length;
    }

    public int[] arr() {
        return arr;
    }

    public int length() {
        return length;
    }

    public void put(String key, Object value) {
        params.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) params.get(key);
    }
}

interface Solution<T> {
    T execute(ExecutionContext ctx);
}

class LargestElement implements Solution<Integer> {
    @Override
    public Integer execute(ExecutionContext ctx) {
        int[] arr = ctx.arr();
        int largest = arr[0];

        for (int i = 1; i < ctx.length(); i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }
        return largest;
    }
}

class SecondLargestElement implements Solution<Integer> {
    @Override
    public Integer execute(ExecutionContext ctx) {
        int[] arr = ctx.arr();
        int largest = arr[0];
        int secondLargest = arr[1];

        for (int i = 1; i < ctx.length(); i++) {
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
    public Boolean execute(ExecutionContext ctx) {
        int[] arr = ctx.arr();
        for (int i = 1; i < ctx.length(); i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }

        return true;
    }
}

class LinearSearch implements Solution<Integer> {
    @Override
    public Integer execute(ExecutionContext ctx) {
        int[] arr = ctx.arr();
        int target = ctx.get("target");

        for (int i = 0; i < ctx.length(); i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }
}

class UnionOfSortedArrays implements Solution<ArrayList<Integer>> {
    @Override
    public ArrayList<Integer> execute(ExecutionContext ctx) {
        int[] arr1 = ctx.arr();
        int size = ctx.length();
        int[] arr2 = ctx.get("array2");
        int size2 = ctx.get("size2");
        ArrayList<Integer> union = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < size && j < size2) {
            if (arr1[i] <= arr2[j]) {
                addIfDifferent(union, arr1[i]);
                i++;
            } else if (arr2[j] < arr1[i]) {
                addIfDifferent(union, arr2[j]);
                j++;
            }
        }

        while (i < size) {
            addIfDifferent(union, arr1[i]);
            i++;
        }

        while (j < size2) {
            addIfDifferent(union, arr2[j]);
            j++;
        }

        return union;
    }

    private void addIfDifferent(ArrayList<Integer> union, int val) {
        if (union.isEmpty() || union.get(union.size() - 1) != val) {
            union.add(val);
        }
    }
}

class LongestSubArrayWithGivenSum implements Solution<Integer> {
    @Override
    public Integer execute(ExecutionContext ctx) {
        int[] arr = ctx.arr();
        int length = ctx.length();
        int k = ctx.get("k");

        int i = 0;
        int j = 0;
        int longestSubarray = 0;
        int windowSum = 0;

        while (j < length) {
            if (windowSum < k) {
                windowSum += arr[j];
                j++;
            } else if (windowSum > k) {
                windowSum -= arr[i];
                i++;
            } else {
                longestSubarray = Math.max(longestSubarray, j - i);
                windowSum += arr[j];
                j++;
            }
        }

        return longestSubarray;
    }
}

class LengthOfLogngestSubarrayWithZeroSum implements Solution<Integer> {
    /**
     * Sliding window won't work here
     * It's because sliding window is based on the principle of montonic increase of the contiguous list
     * Negative numbers defeat this principle
     * Monotonic -> adding only increases sum, sutracting decreases sum.
     * With negative numbers -> Adding -ve number might decrease sum, subtracting -ve number might increase sum.
     * We need to use prefix sum + hashmap.
     * If Prefix sum from 0 to i and 0 to j are equal then the sum between i to j is 0.
     * We'd store unique occurence of prefix sum in 
     */
    @Override
    public Integer execute(ExecutionContext ctx) {
        int[] arr = ctx.arr();
        int length = ctx.length();
        int prefixSum = 0;
        int i = 0;
        int maxLen = 0;
        Map<Integer, Integer> prefixRegistry = new HashMap<>();
        prefixRegistry.put(0, -1);
        while(i < length) {
            prefixSum += arr[i];
            if (prefixRegistry.containsKey(prefixSum)) {
                maxLen = Math.max(i - prefixRegistry.get(prefixSum), maxLen);
            } else {
                prefixRegistry.put(prefixSum, i);
            }
            i++;
        }

        return maxLen;
    }
}
