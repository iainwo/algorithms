public class ExponentialSearch {

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
				+ "exponential_search_v2"
				+ " for (" + searchVal + ")"
				+ " returned idx [" + exponential_search_v2(arrayToSearch, searchVal) + "]");
	}

	public static int exponential_search_v2(int arr[], int x) {
		int bound=1;

		while (bound < arr.length && arr[bound] <= x)
			bound*=2;

		return binary_search(arr, x, bound/2, Math.min(bound, arr.length-1));
	}
	public static int binary_search(int arr[], int x, int leftIdx, int rightIdx) {

		while(leftIdx<=rightIdx) {
			int midPoint = leftIdx + (rightIdx-leftIdx)/2;

			if (arr[midPoint]==x)
				return midPoint;

			if (arr[midPoint]<x)
				leftIdx=midPoint+1;
			else
				rightIdx=midPoint-1;
		}

		return -1;
	}
}
