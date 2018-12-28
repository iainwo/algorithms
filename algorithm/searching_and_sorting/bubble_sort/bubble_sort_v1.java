public class BubbleSort {
	public static void main(String[] args) throws Exception {
		test(new int[] {-99, 1, 5, 5, 6, 2, -33, 2, -993, 24});
	}

	public static void test(int[] unsortedArr) throws Exception {
		int[] validArr = new int[] {-993, -99, -33, 1, 2, 2, 5, 5, 6, 24};

		unsortedArr = bubble_sort_v1(unsortedArr);

		System.out.print("bubble_sort_v1 sorted: ");
		for (int i=0; i<unsortedArr.length-1; i++) {
			System.out.print(unsortedArr[i] + ", ");
		}
		System.out.print(unsortedArr[unsortedArr.length-1]);
		for (int i=0; i<validArr.length; i++) {
			if (validArr[i]!=unsortedArr[i])
				throw new Exception("NOTSORTED v:" + validArr[i] + " u:" + unsortedArr[i]);
		}
	}

	public static int[] bubble_sort_v1(int[] arr) {

		for (int i=0; i<arr.length-1; i++) {
			for (int j=1; j<arr.length; j++) {
				if (arr[j]<arr[j-1]) {
					int tmp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = tmp;
				}
			}
		}
		return arr;
	}
}
