public class SelectionSort {
	public static void main(String[] args) throws Exception {
		test(new int[] {-99, 1, 5, 5, 6, 2, -33, 2, -993, 24});
	}

	public static void test(int[] unsortedArr) throws Exception {
		int[] validArr = new int[] {-993, -99, -33, 1, 2, 2, 5, 5, 6, 24};

		unsortedArr = selection_sort_v1(unsortedArr);

		for (int i=0; i<validArr.length; i++) {
			if (validArr[i]!=unsortedArr[i])
				throw new Exception("NOTSORTED v:" + validArr[i] + " u:" + unsortedArr[i]);
		}
		System.out.print("Sorted: ");
		for (int i=0; i<unsortedArr.length-1; i++) {
			System.out.print(unsortedArr[i] + ", ");
		}
		System.out.print(unsortedArr[unsortedArr.length-1]);
	}

	public static int[] selection_sort_v1(int[] arr) {

		for (int unsortedIdx=0; unsortedIdx<arr.length-1; unsortedIdx++) {
			int minIdx=unsortedIdx;
			for (int j=unsortedIdx; j<arr.length; j++) {
				if(arr[j]<arr[minIdx])
					minIdx=j;
			}
			if(minIdx>unsortedIdx) {
				int otherVal=arr[unsortedIdx];
				arr[unsortedIdx]=arr[minIdx];
				arr[minIdx]=otherVal;
			}
		}
		return arr;
	}
}
