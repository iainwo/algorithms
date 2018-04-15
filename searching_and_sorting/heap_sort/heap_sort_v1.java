
public class HeapSort {
	public static void main(String[] args) throws Exception {
		test(new int[] {-99, 1, 5, 5, 6, 2, -33, 2, -993, 24});
	}

	public static void test(int[] unsortedArr) throws Exception {
		int[] validArr = new int[] {-993, -99, -33, 1, 2, 2, 5, 5, 6, 24};

		unsortedArr = heap_sort_v1(unsortedArr, unsortedArr.length);

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

	public static int[] heap_sort_v1(int[] arr, int n) {

		int[] heap = build_heap(arr, n);

		for (int i=n-1; i>=0; i--){
			int maxVal = remove_heap_root(heap, i+1);
			arr[i]=maxVal;
		}

		return arr;
	}
	public static int remove_heap_root(int[] heap, int n) {
		int heap_root = heap[0];

		if (n>1) {
			swap_indices(heap, 0, n-1);
			heap[n-1]=0;

			balance_heap(heap, n-1, 0);
		}

		return heap_root;
	}
	public static int[] build_heap(int[] arr, int n) {

		int[] ret_heap = new int[n];
		for (int i=0; i<n; i++) {
			ret_heap[i]=0;
		}
		for (int i=0; i<n; i++) {
			ret_heap[i]=arr[i];
			balance_heap(ret_heap, i+1, i);
		}

		return ret_heap;
	}
	public static void balance_heap(int[] arr, int n, int i) {
		int balance_point=i;
		while (2*balance_point+2<n) {
			int p = arr[balance_point];
			int l = arr[2*balance_point+1];
			int r = arr[2*balance_point+2];

			if (l > p && r > p)
			{
				// determine bigger
				int big_idx=2*balance_point+1;
				if (l-r<0) {
					big_idx++;
				}

				swap_indices(arr, balance_point, big_idx);
				balance_point=big_idx;
			} else if (l > p) {
				swap_indices(arr, balance_point, 2*balance_point+1);
				balance_point=2*balance_point+1;
			} else if (r > p) {
				swap_indices(arr, balance_point, 2*balance_point+2);
				balance_point=2*balance_point+2;
			} else {
				break;
			}
		}
		if (n>2*balance_point+1 && arr[2*balance_point+1] > arr[balance_point]) {
				swap_indices(arr, balance_point, 2*balance_point+1);
		}

		balance_point=i;
		while(0<balance_point) {
			int p = arr[(int)Math.floor((balance_point-1)/2)];
			int c = arr[balance_point];

			if (c > p) {
				swap_indices(arr, balance_point, (int)Math.floor((balance_point-1)/2));
				balance_point=(int)Math.floor((balance_point-1)/2);
			} else {
				break;
			}
		}
	}
	public static void swap_indices(int[] arr, int idx1, int idx2) {
		int tmp=arr[idx1];
		arr[idx1]=arr[idx2];
		arr[idx2]=tmp;
	}
}
