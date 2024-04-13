import java.util.Scanner;

public class BST_Array {

  public static void main(String[] args) {
    // asc order array
    int[] arr = {
      -20,
      -15,
      -10,
      -7,
      -4,
      -2,
      0,
      1,
      3,
      5,
      7,
      9,
      12,
      15,
      18,
      20,
      22,
      25,
      28,
      30,
    };

    // desc order array
    // int[] arr = {
    //   30,
    //   28,
    //   25,
    //   22,
    //   20,
    //   18,
    //   15,
    //   12,
    //   9,
    //   7,
    //   5,
    //   3,
    //   1,
    //   0,
    //   -2,
    //   -4,
    //   -7,
    //   -10,
    //   -15,
    //   -20,
    // };

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a target element to serach:");
    int target = sc.nextInt();

    // Iterative Approach
    int index = binarySearch(arr, target);

    // Recursive Appraoch
    // int index = binarySearchRecursive(arr, target, 0, arr.length - 1, true);

    // floor
    // int index = floor(arr, target);

    // ceil
    // int index = ceil(arr, target);

    if (index == -1) {
      System.out.println("Element not found");
    } else {
      System.out.println("Element found at index: " + index);
    }
  }

  static int binarySearch(int[] arr, int target) {
    int start = 0;
    int end = arr.length - 1;

    // check if arr is in asc or desc order
    boolean isAcs = arr[0] < arr[arr.length - 1];

    while (start <= end) {
      int mid = start + (end - start) / 2;

      // if element found
      if (target == arr[mid]) return mid;

      if (isAcs) {
        if (target < arr[mid]) end = mid - 1; else start = mid + 1;
      } else {
        if (target > arr[mid]) end = mid - 1; else start = mid + 1;
      }
    }

    // if element not found
    return -1;
  }

  static int binarySearchRecursive(
    int[] arr,
    int target,
    int start,
    int end,
    boolean isAcs
  ) {
    if (start <= end) {
      int mid = start + (end - start) / 2;

      // if element found
      if (target == arr[mid]) return mid;

      if (isAcs) {
        if (target < arr[mid]) return binarySearchRecursive(
          arr,
          target,
          start,
          mid - 1,
          isAcs
        ); else return binarySearchRecursive(arr, target, mid + 1, end, isAcs);
      } else {
        if (target > arr[mid]) return binarySearchRecursive(
          arr,
          target,
          start,
          mid - 1,
          isAcs
        ); else return binarySearchRecursive(arr, target, mid + 1, end, isAcs);
      }
    }
    return -1;
  }

  // only for asc
  // return a greatest number that is <= to the target element (i,e., target element or just before the target element)
  static int floor(int[] arr, int target) {
    // if target itself is smaller than first element, return -1 (not found)
    if (target < arr[0]) return -1;
    int start = 0;
    int end = arr.length - 1;

    while (start <= end) {
      int mid = start + (end - start) / 2;

      if (target < arr[mid]) end = mid - 1; else if (target > arr[mid]) start =
        mid + 1; else return mid;
    }

    // instead of returning (-1) just return the end (or mid)
    return arr[end];
  }

  // only for asc
  // return a smallest number that is >= to the target element (i.e., target element or just after the target element)
  static int ceil(int[] arr, int target) {
    // if target itself is greater than last element, return -1 (not found)
    if (target > arr[arr.length - 1]) return -1;
    int start = 0;
    int end = arr.length - 1;

    while (start <= end) {
      int mid = start + (end - start) / 2;

      if (target < arr[mid]) end = mid - 1; else if (target > arr[mid]) start =
        mid + 1; else return mid;
    }

    // instead of returning (-1) just return the start (or mid+1) (cuz, at this point the loop would have broken only when start > end)
    return arr[start];
  }
}
