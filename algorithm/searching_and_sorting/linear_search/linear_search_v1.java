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
				+ " returned idx [" + linear_search(searchVal, arrayToSearch) + "]");
	}

	public static int linear_search(int searchVal, int[] arrayToSearch) {
		int retIdx=-1;

		for (int arrIdx=0; arrIdx<arrayToSearch.length; arrIdx++) {
			if (arrayToSearch[arrIdx]==searchVal) {
				retIdx=arrIdx;
				break;
			}
		}

		return retIdx;
	}
}
