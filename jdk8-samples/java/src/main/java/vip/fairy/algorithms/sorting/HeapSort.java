package vip.fairy.algorithms.sorting;

public class HeapSort implements Sort {

  private int N;

  //O(N log(N)) - iterating over every element and building BST
  @Override
  public void sort(int[] arr) {
    heapify(arr);
    //O(N) * log(n)
    for (int i = N; i > 0; i--) {
      swap(arr, 0, i);
      N = N - 1;
      maxheap(arr, 0);
    }
  }

  /* Function to build a heap */
  public void heapify(int arr[]) {
    N = arr.length - 1;
    //log(n)
    for (int i = N / 2; i >= 0; i--) {
      maxheap(arr, i);
    }
  }

  /* Function to swap largest element in heap */
  public void maxheap(int arr[], int i) {
    int left = 2 * i;
    int right = 2 * i + 1;
    int max = i;
    if (left <= N && arr[left] > arr[i]) {
      max = left;
    }
    if (right <= N && arr[right] > arr[max]) {
      max = right;
    }

    if (max != i) {
      swap(arr, i, max);
      maxheap(arr, max);
    }
  }

  /* Function to swap two numbers in an array */
  public void swap(int arr[], int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
