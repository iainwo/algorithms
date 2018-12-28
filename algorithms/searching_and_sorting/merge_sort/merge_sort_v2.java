public class MergeSortV2 {
	public static void main(String[] args) throws Exception {
		test(new int[] {-99, 1, 5, 5, 6, 2, -33, 2, -993, 24});
	}

	public static void test(int[] unsortedArr) throws Exception {
		int[] validArr = new int[] {-993, -99, -33, 1, 2, 2, 5, 5, 6, 24};

		unsortedArr = merge_sort_v2(unsortedArr, 0, unsortedArr.length-1);

		System.out.print("merge_sort_v1 sorted: ");
		for (int i=0; i<unsortedArr.length-1; i++) {
			System.out.print(unsortedArr[i] + ", ");
		}
		System.out.print(unsortedArr[unsortedArr.length-1] + "\n");

		for (int i=0; i<validArr.length; i++) {
			if (validArr[i]!=unsortedArr[i])
				throw new Exception("NOTSORTED v:" + validArr[i] + " u:" + unsortedArr[i]);
		}
	}

	public static int[] merge_sort_v2(int[] arr, int l, int r) {

		if (0<r-l) {
			int m = l + (r-l)/2;

			merge_sort_v2(arr, l, m);
			merge_sort_v2(arr, m+1, r);

			merge(arr, l, m, r);
		}

		return arr;
	}

	public static void merge(int[] arr, int l, int m, int r) {

		int n1 = m-l+1;
		int n2 = r-m;

		int L[] = new int[n1];
		int R[] = new int[n2];

		for (int i=0; i<n1; i++) {
			L[i] = arr[l + i];
		}
		for (int i=0; i<n2; i++) {
			R[i] = arr[m+1+i];
		}

		int sortedIdx=l;
		int leftIdx=0;
		int rightIdx=0;
		while(leftIdx<n1 && rightIdx<n2) {
			if (L[leftIdx]<R[rightIdx]) {
				arr[sortedIdx]=L[leftIdx];
				leftIdx++;
				sortedIdx++;
			} else {
				arr[sortedIdx]=R[rightIdx];
				rightIdx++;
				sortedIdx++;
			}
		}

		while (leftIdx<n1) {
			arr[sortedIdx]=L[leftIdx];
			leftIdx++;
			sortedIdx++;
		}
		while (rightIdx<n2) {
			arr[sortedIdx]=R[rightIdx];
			rightIdx++;
			sortedIdx++;
		}
	}
}
