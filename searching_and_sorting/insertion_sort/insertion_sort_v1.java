
public class InsertionSort {
	public static void main(String[] args) throws Exception {
		test(new int[] {-99, 1, 5, 5, 6, 2, -33, 2, -993, 24});
	}

	public static void test(int[] unsortedArr) throws Exception {
		int[] validArr = new int[] {-993, -99, -33, 1, 2, 2, 5, 5, 6, 24};

		unsortedArr = insertion_sort_v1(unsortedArr);

		for (int i=0; i<validArr.length; i++) {
			if (validArr[i]!=unsortedArr[i])
				throw new Exception("NOTSORTED v:" + validArr[i] + " u:" + unsortedArr[i]);
		}
		System.out.print("insertion_sort_v1 sorted: ");
		for (int i=0; i<unsortedArr.length-1; i++) {
			System.out.print(unsortedArr[i] + ", ");
		}
		System.out.print(unsortedArr[unsortedArr.length-1]);
	}

	public static int[] insertion_sort_v1(int[] arr) {

		int n=arr.length;
		for (int i=1; i<n; i++) {
			int insertVal=arr[i];

			for (int j=i-1; j>=0 && arr[j]>insertVal; j--) {
				arr[j+1]=arr[j];
				arr[j]=insertVal;
			}
		}
		return arr;
	}
}
