
public class TernarySearch {
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
				+ "ternary_search_v1"
				+ " for (" + searchVal + ")"
				+ " returned idx [" + ternary_search_v1(arrayToSearch, searchVal) + "]");
	}

	public static int ternary_search_v1(int arr[], int x) {
		int l=0;
		int r=arr.length-1;
		while (r>=l) {
			int m1 = l + (r-l)/3;
			int m2 = l + (r-l)*2/3;

			if (arr[m1]==x)
				return m1;
			if (arr[m2]==x)
				return m2;
			if (arr[m1]>x)
				r=m1-1;
			if (arr[m1]<x)
				l=m1+1;
			if (arr[m2]>x)
				r=m2-1;
			if (arr[m2]<x)
				l=m2+1;
		}
		return -1;
	}
}
