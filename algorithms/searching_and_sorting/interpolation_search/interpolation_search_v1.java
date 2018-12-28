public class InterpolationSearch {

	public static void main(String[] args) {
		int ARR_LEN=100;
		int[] searchableArr = new int[ARR_LEN];
		for (int i=0; i<ARR_LEN; i++) {searchableArr[i]=i*i;}

		int testVal1 = 9;		// in the middle of the indices
		int testVal2 = 999999;	// out-of-bounds (right) of the indices
		int testVal3 = 99*99;	// final (right) index
		int testVal4 = 0;		// final (left) index
		int testVal5 = -10;		// out-of-bounds (left) of the indices
		test(testVal1, searchableArr);
		test(testVal2, searchableArr);
		test(testVal3, searchableArr);
		test(testVal4, searchableArr);
		test(testVal5, searchableArr);
	}

	public static void test(int searchVal, int[] arrayToSearch) {

		System.out.println(""
				+ "interpolation_search_v2"
				+ " for (" + searchVal + ")"
				+ " returned idx [" + interpolation_search_v2(arrayToSearch, searchVal) + "]");
	}

	public static int interpolation_search_v2(int arr[], int x) {
		int leftIdx = 0;
		int rightIdx = arr.length-1;

		while (leftIdx<=rightIdx) {
			// Approximate where the 'x' value falls on the index; multiply the approx ratio by the indices difference
			int pivotIdx = leftIdx;
			try {
				pivotIdx = (int)Math.floor(leftIdx + ((x-arr[leftIdx])/(arr[rightIdx]-arr[leftIdx]))*(rightIdx-leftIdx));
			} catch (Exception e) {
				// do nothing, likely a divide by (0)e.
				// default to linear search
			}
			pivotIdx = Math.min(pivotIdx, rightIdx);
			pivotIdx = Math.max(pivotIdx, leftIdx);

			if (arr[pivotIdx]==x) {
				return pivotIdx;
			}
			if (arr[pivotIdx]<x) {
				leftIdx=pivotIdx+1;
			} else {
				rightIdx=pivotIdx-1;
			}
		}

		return -1;
	}
}
