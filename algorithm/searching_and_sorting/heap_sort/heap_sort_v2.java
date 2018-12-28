
public class HeapSortRecursive {
	public static void main(String[] args) throws Exception {
		test(new int[] {-99, 1, 5, 5, 6, 2, -33, 2, -993, 24});
	}

	public static void test(int[] unsortedArr) throws Exception {
		int[] validArr = new int[] {-993, -99, -33, 1, 2, 2, 5, 5, 6, 24};

		unsortedArr = heap_sort_recursive(unsortedArr, unsortedArr.length);

		System.out.print("heap_sort_v1 sorted: ");
		for (int i=0; i<unsortedArr.length-1; i++) {
			System.out.print(unsortedArr[i] + ", ");
		}
		System.out.print(unsortedArr[unsortedArr.length-1] + "\n");

		for (int i=0; i<validArr.length; i++) {
			if (validArr[i]!=unsortedArr[i])
				throw new Exception("NOTSORTED v:" + validArr[i] + " u:" + unsortedArr[i]);
		}
	}

	public static int[] heap_sort_recursive(int[] arr, int n) {
		build_heap(arr, n);

		for (int sort_idx=n-1; sort_idx>0; sort_idx--) {
			swap(arr, 0, sort_idx);
			heapify(arr,0, sort_idx-1);
		}
		return arr;
	}

	private static void heapify(int[] arr, int i, int n) {
		int largest_idx=i;
		int l_idx=2*largest_idx+1;
		int r_idx=l_idx+1;

		if (l_idx<n && arr[l_idx]>arr[largest_idx]) {
			largest_idx=l_idx;
		}
		if (r_idx<n && arr[r_idx]>arr[largest_idx]) {
			largest_idx=r_idx;
		}
		if (largest_idx!=i) {
			swap(arr,largest_idx,i);
			heapify(arr,largest_idx,n);
		}
	}
	private static void swap(int[] arr, int i, int sort_idx) {
		int tmp=arr[i];
		arr[i]=arr[sort_idx];
		arr[sort_idx]=tmp;
	}
	private static void build_heap(int[] arr, int n) {
		for (int i=(int)Math.floor(n/2-1); i>=0; i--) {
			heapify(arr,i, n);
		}
	}
}
