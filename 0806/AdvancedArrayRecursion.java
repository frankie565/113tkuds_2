import java.util.*;

public class AdvancedArrayRecursion {


    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

  
    public static int[] mergeSortedArrays(int[] A, int[] B) {
        return mergeRecursive(A, 0, B, 0);
    }

    private static int[] mergeRecursive(int[] A, int i, int[] B, int j) {
        if (i == A.length) return Arrays.copyOfRange(B, j, B.length);
        if (j == B.length) return Arrays.copyOfRange(A, i, A.length);

        if (A[i] <= B[j]) {
            return prepend(A[i], mergeRecursive(A, i + 1, B, j));
        } else {
            return prepend(B[j], mergeRecursive(A, i, B, j + 1));
        }
    }

    private static int[] prepend(int val, int[] arr) {
        int[] result = new int[arr.length + 1];
        result[0] = val;
        System.arraycopy(arr, 0, result, 1, arr.length);
        return result;
    }


    public static int quickSelect(int[] arr, int k) {
        return quickSelectHelper(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelectHelper(int[] arr, int low, int high, int k) {
        if (low == high) return arr[low];
        int pivotIndex = partition(arr, low, high);
        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelectHelper(arr, low, pivotIndex - 1, k);
        } else {
            return quickSelectHelper(arr, pivotIndex + 1, high, k);
        }
    }

    public static boolean hasSubsetSum(int[] arr, int target) {
        return subsetSumHelper(arr, 0, target);
    }

    private static boolean subsetSumHelper(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index == arr.length) return false;

   
        return subsetSumHelper(arr, index + 1, target - arr[index]) ||
               subsetSumHelper(arr, index + 1, target);
    }

  
    public static void main(String[] args) {
        int[] arr = {7, 2, 1, 8, 5};
        System.out.println("原始陣列: " + Arrays.toString(arr));

   
        int[] quickSorted = Arrays.copyOf(arr, arr.length);
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("快速排序後: " + Arrays.toString(quickSorted));

     
        int[] A = {1, 3, 5}, B = {2, 4, 6};
        int[] merged = mergeSortedArrays(A, B);
        System.out.println("遞迴合併結果: " + Arrays.toString(merged));

        int k = 3;
        int[] selectArr = Arrays.copyOf(arr, arr.length);
        System.out.println("第 " + k + " 小元素: " + quickSelect(selectArr, k));

       
        int[] subsetArr = {3, 34, 4, 12, 5, 2};
        int target = 9;
        System.out.println("是否存在子集總和為 " + target + "? " + hasSubsetSum(subsetArr, target));
    }
}

