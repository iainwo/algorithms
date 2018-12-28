public class JumpSearch {

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
				+ "jump_search_v2"
				+ " for (" + searchVal + ")"
				+ " returned idx [" + jump_search_v2(arrayToSearch, searchVal) + "]");
	}

	public static int jump_search_v2(int arr[], int x) {

		int maxIdx=arr.length-1;
		int squareRootOfLen = (int) Math.floor(Math.sqrt(arr.length));

		int searchIdx = 0;
		int prevIdx = 0;

		while (arr[Math.min(searchIdx, maxIdx)] < x) {
			prevIdx=searchIdx;
			searchIdx+=squareRootOfLen;

			if (prevIdx > maxIdx) { // due to the min function, implies even the biggest element is < than x
				return -1;
			}
		}
		while (arr[prevIdx] < x) {
			prevIdx++;
			if (prevIdx==Math.min(searchIdx, maxIdx+1)) {
				return -1;
			}
		}
		if (arr[prevIdx]==x) {
			return prevIdx;
		}

		return -1;
	}
}
