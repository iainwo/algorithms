public class LinearSearch {

	public static void main(String[] args) {
		int ARR_LEN=100;
		int[] searchableArr = new int[ARR_LEN];
		for (int i=0; i<ARR_LEN; i++) {searchableArr[i]=i;}

		int testVal1 = 57;
		int testVal2 = 1000;
		test(testVal1, searchableArr);
		test(testVal2, searchableArr);
	}

	public static void test(int searchVal, int[] arrayToSearch) {
		System.out.println(""
				+ "linear_search"
				+ " for (" + searchVal + ")"
				+ " returned idx [" + linear_search_v2(arrayToSearch, arrayToSearch.length, searchVal) + "]");
	}

	public static int linear_search_v2(int arr[], int n, int x) {

		for (int i=0; i<n; i++) {
			if (arr[i]==x)
				return i;
		}

		return -1;
	}
}
