import java.util.*;

public class NumberArrayProcessor {


    public static int[] removeDuplicates(int[] arr) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }

 
    public static int[] mergeSortedArrays(int[] A, int[] B) {
        int[] result = new int[A.length + B.length];
        int i = 0, j = 0, k = 0;
        while (i < A.length && j < B.length) {
            if (A[i] <= B[j]) result[k++] = A[i++];
            else result[k++] = B[j++];
        }
        while (i < A.length) result[k++] = A[i++];
        while (j < B.length) result[k++] = B[j++];
        return result;
    }

    public static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0, mostFrequent = arr[0];
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }

    public static void splitArray(int[] arr) {
        int total = Arrays.stream(arr).sum();
        int sum = 0;
        List<Integer> part1 = new ArrayList<>();
        List<Integer> part2 = new ArrayList<>();

        for (int num : arr) {
            if (Math.abs((sum + num) - (total - sum - num)) <= Math.abs(sum - (total - sum))) {
                part1.add(num);
                sum += num;
            } else {
                part2.add(num);
            }
        }

        System.out.println("子陣列 1: " + part1);
        System.out.println("子陣列 2: " + part2);
    }


    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4, 4, 5};
        int[] sorted1 = {1, 3, 5, 7};
        int[] sorted2 = {2, 4, 6, 8};

        System.out.println("原始陣列:");
        printArray(arr1);

        System.out.println("\n移除重複元素:");
        printArray(removeDuplicates(arr1));

        System.out.println("\n合併已排序陣列:");
        printArray(mergeSortedArrays(sorted1, sorted2));

        System.out.println("\n最常出現的元素:");
        System.out.println(findMostFrequent(arr1));

        System.out.println("\n分割陣列為兩個總和近似的子陣列:");
        splitArray(arr1);
    }
}
