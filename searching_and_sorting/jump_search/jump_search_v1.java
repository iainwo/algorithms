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
		int squareRootOfArr = 1;
		for (int i=1; i<arrayToSearch.length; i*=2) squareRootOfArr++;
		System.out.println(""
				+ "jump_search_v1"
				+ " for (" + searchVal + ")"
				+ " returned idx [" + jump_search_v1(arrayToSearch, 0, arrayToSearch.length-1, squareRootOfArr, searchVal) + "]");
	}

	public static int jump_search_v1(int arr[], int startIdx, int endIdx, int blockSize, int x) {

		int searchIdx=0;
		while (searchIdx<=endIdx) {
			// Indice disqualifications
			if(arr[searchIdx]>x && 0==searchIdx) return -1;
			if (arr[searchIdx]<x && searchIdx==endIdx) return -1;

			// Jump Logic
			if(arr[searchIdx]==x) return searchIdx;
			if(arr[searchIdx]>x) {

				for (int i=searchIdx-blockSize+1; i<searchIdx; i++) {
					if (arr[i]==x) return i;
				}
			}
			searchIdx+=blockSize;
			if(searchIdx>endIdx && searchIdx-blockSize>endIdx) return -1;

			// Remaps
			if(searchIdx>endIdx) searchIdx=endIdx;
		}

		return -1;
	}
}
