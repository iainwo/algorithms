
public class BinarySearch {

	public static void main(String[] args) {
		int ARR_LEN=100;
		int[] searchableArr = new int[ARR_LEN];
		for (int i=0; i<ARR_LEN; i++) {searchableArr[i]=i;}

		int testVal1 = 57;
		int testVal2 = 1000;
		int testVal3 = 0;
		int testVal4 = 99;
		int testVal5 = -10;
		test(testVal1, searchableArr);
		test(testVal2, searchableArr);
		test(testVal3, searchableArr);
		test(testVal4, searchableArr);
		test(testVal5, searchableArr);
	}

	public static void test(int searchVal, int[] arrayToSearch) {
		System.out.println(""
				+ "binary_search"
				+ " for (" + searchVal + ")"
				+ " returned idx [" + binary_search_v2(arrayToSearch, 0, arrayToSearch.length-1,  searchVal) + "]");
	}

	public static int binary_search_v2(int arr[], int leftIdx, int rightIdx, int x) {
		if (rightIdx>=leftIdx) { // beauty of this is that the match performs the out-of-bounds checking
			// the midpoint is the only actual array access and it is only valid if the right and left bounds are good
			int midPointIdx = leftIdx + (rightIdx-leftIdx)/2;

			if ( arr[midPointIdx] == x )
				return midPointIdx;

			if (arr[midPointIdx] > x)
				return binary_search_v2(arr, leftIdx, midPointIdx-1, x);

			return binary_search_v2(arr, midPointIdx+1, rightIdx, x);
		}

		return -1;
	}

}
