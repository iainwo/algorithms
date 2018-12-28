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

		int lo = 0, hi = (arr.length -1);

		while (lo <= hi && x >= arr[lo] && x <= arr[hi]) {

			/* Two ways of looking at it:
			 * 1) First,
			 * 		1.	(hi-lo) 			- this represents the magnitude of indices difference
			 * 		2.	(arr[hi]-arr[lo]) 	- represents the total discontinuity between hi and lo values
			 *
			 * 		3.  #1/#2				- a ratio of how congruent the indexed value is to it's index.
			 * 								E.g are they 1:1 - where arr[1]:1, arr[1000] ~= 1000
			 * 		4.	*(x - arr[lo])		- multiply the degree of congruence by the degree of discontinuity of the
			 * 								searched item and the lower-bound
			 * -OR-
			 * 2) Second,
			 * 		1.	(x - arr[lo])		- relative search to lower-bound difference
			 * 		2. 	(arr[hi] - arr[lo])	- absolute value difference
			 *
			 * 		3.	#1/#2				- the ratio of relative search difference to the entire range
			 *
			 * 		4.	*(hi-lo)			- multiply the relative search diff. - relative to the absolute range by
			 * 								the total indices difference to interpolate the approximate search values
			 * 								location between the lower-bound (lo) and upper-bound (hi)
			 */

			// this variation isn't great - will fail on divide by (0).
			int pos = lo + ((hi-lo)/(arr[hi]-arr[lo])*(x - arr[lo]));

			if (arr[pos] == x)
				return pos;

			if (arr[pos] < x)
				lo = pos + 1;
			else
				hi = pos - 1;
		}

		return -1;
	}
}
