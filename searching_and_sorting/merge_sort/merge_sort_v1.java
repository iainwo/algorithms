public class MergeSortBasic {
	public static void main(String[] args) throws Exception {
		test(new int[] {-99, 1, 5, 5, 6, 2, -33, 2, -993, 24});
	}

	public static void test(int[] unsortedArr) throws Exception {
		int[] validArr = new int[] {-993, -99, -33, 1, 2, 2, 5, 5, 6, 24};

		unsortedArr = merge_sort_v1(unsortedArr, 0, unsortedArr.length-1);

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

	public static int[] merge_sort_v1(int[] arr, int l, int r) {

		if (0<r-l) {
			int m = l + (r-l)/2;
			return merge(merge_sort_v1(arr, l, m), merge_sort_v1(arr, m+1, r), l, m, m+1, r);
		}

		return arr;
	}

	public static int[] merge(int[] larr, int[] rarr, int leftIdx, int lEndIdx, int rightIdx, int rEndIdx) {

		if (rEndIdx-rightIdx<0) return larr;
		if (lEndIdx-leftIdx<0) return rarr;

		int [] sortedArr = new int[larr.length];

		int idx=leftIdx;
		while (leftIdx<=lEndIdx && rightIdx<=rEndIdx) {
			if (larr[leftIdx]<rarr[rightIdx]) {
				sortedArr[idx++]=larr[leftIdx];
				leftIdx++;
			} else {
				sortedArr[idx++]=rarr[rightIdx];
				rightIdx++;
			}
		}
		for (int i=leftIdx; i<=lEndIdx; i++) {
			sortedArr[idx++]=larr[i];
		}
		for (int i=rightIdx; i<=rEndIdx; i++) {
			sortedArr[idx++]=rarr[i];
		}

		return sortedArr;
	}
}
