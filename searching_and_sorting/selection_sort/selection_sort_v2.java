public class SelectionSort {
	public static void main(String[] args) throws Exception {
		test(new int[] {-99, 1, 5, 5, 6, 2, -33, 2, -993, 24});
	}

	public static void test(int[] unsortedArr) throws Exception {
		int[] validArr = new int[] {-993, -99, -33, 1, 2, 2, 5, 5, 6, 24};

		unsortedArr = selection_sort_v2(unsortedArr);

		for (int i=0; i<validArr.length; i++) {
			if (validArr[i]!=unsortedArr[i])
				throw new Exception("NOTSORTED v:" + validArr[i] + " u:" + unsortedArr[i]);
		}
		System.out.print("selection_sort_v2 sorted: ");
		for (int i=0; i<unsortedArr.length-1; i++) {
			System.out.print(unsortedArr[i] + ", ");
		}
		System.out.print(unsortedArr[unsortedArr.length-1]);
	}

	public static int[] selection_sort_v2(int[] arr) {

		int n = arr.length;

		for (int i=0; i<n-1; i++) {
			int min_idx=i;

			for (int j=i+1; j<n; j++) {
				if (arr[j] < arr[min_idx]) {
					min_idx=j;
				}
			}

			int tmp = arr[i];
			arr[i]=arr[min_idx];
			arr[min_idx]=tmp;
		}
		return arr;
	}
}
