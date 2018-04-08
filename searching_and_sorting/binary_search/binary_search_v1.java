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
				+ "linear_search"
				+ " for (" + searchVal + ")"
				+ " returned idx [" + binary_search_v1(arrayToSearch, arrayToSearch.length, searchVal) + "]");
	}

	public static int binary_search_v1(int arr[], int n, int x) {
		int rangeStart=0;
		int rangeEnd=n-1;
		int rangeLen = (rangeEnd-rangeStart);
		while (rangeLen>1) { // while there is more than one element to search
			rangeLen = (rangeEnd-rangeStart);
			if (1 >= rangeLen) {
				if (arr[rangeStart] == x)
					return rangeStart;
				else if (arr[rangeEnd] == x)
					return rangeEnd;
				else
					return -1;
			}

			int midPointIdx = rangeStart + (rangeLen)/2; // first half is bigger when odd
			if (arr[midPointIdx]<x) {
				rangeStart=midPointIdx+1;
			} else {
				rangeEnd=midPointIdx;
			}
		}

		return -1;
	}
}
