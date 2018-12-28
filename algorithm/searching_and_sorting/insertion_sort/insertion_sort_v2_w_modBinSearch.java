
public class InsertionSort {
	public static void main(String[] args) throws Exception {
		test(new int[] {-99, 1, 5, 5, 6, 2, -33, 2, -993, 24});
	}

	public static void test(int[] unsortedArr) throws Exception {
		int[] validArr = new int[] {-993, -99, -33, 1, 2, 2, 5, 5, 6, 24};

		unsortedArr = insertion_sort_v2(unsortedArr);

		for (int i=0; i<validArr.length; i++) {
			if (validArr[i]!=unsortedArr[i])
				throw new Exception("NOTSORTED v:" + validArr[i] + " u:" + unsortedArr[i]);
		}
		System.out.print("insertion_sort_v2 sorted: ");
		for (int i=0; i<unsortedArr.length-1; i++) {
			System.out.print(unsortedArr[i] + ", ");
		}
		System.out.print(unsortedArr[unsortedArr.length-1]);
	}

	public static int[] insertion_sort_v2(int[] arr) {

		int n=arr.length;
		for (int i=1; i<n; i++) {
			int insertVal=arr[i];
			int smallerIdx = modified_binary_search(arr, insertVal, 0,i-1);
			for (int j=i; j>smallerIdx; j--) {
				arr[j]=arr[j-1];
			}
			arr[smallerIdx]=insertVal;
		}
		return arr;
	}
	public static int modified_binary_search(int[] arr, int x, int l, int r) {

		/* More efficient than a full binary traverse to find out the search item is smaller or greater than the
		 * range size.
		 */
		if (arr[0] > x) return 0;
		if (arr[r]<x && arr.length-1<r+1) return arr.length-1;
		if (arr[r]<x) return r+1;

		while(l<=r) {
			int mid = l + (r-l)/2;

			if (arr[mid]==x) return mid;

			/* Two logic checks which account for the (2) 2-node cases and the (2) 3-node cases */
			if (arr[l]>x) return l;
			if (l==r) return 1 + l;

			if (arr[mid]>x) r=mid-1;
			else l=mid+1;
		}
		return -1;
	}
}
