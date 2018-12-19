import java.util.Arrays;

/**
 * Interesting algo, which works based off of number theory.
 * Basic idea is that for two numbers to sum to a value, they must be
 * proportiante to one another. If one number is greater, the other must be less
 * and vice-versa. This applies when numbers are postive and negative.
 * This maxim can be leveraged to infer which numbers in a set might have the
 * possibility of summing to a value.
 * 
 * By ordering a set of numbers, then evaluating the extreminities of that
 * ordering; the previous maxim will indicate which of the extremities could not
 * have a complementing pair. This will allow you to reduce the combinations
 * under consideration, until a point where you have discovered a summing pair
 * or have exhausted the dataset.
 * 
 * For instance,
 *      Say you were trying to sum to 5.
 *      
 *      You have two numbers -3 and positive 9.
 *      Based on the maxim that two numbers must be proportionate to sum to the
 *      same value, it can be said that since 9 is the largest, and -3 is the
 *      smallest, 9 does not have a proportiante integer in this set which
 *      would sum to (5). Therefor, it can be discarded.
 * 
 *      Similarly if you had extremities (-3) and 6, you could determine that
 *      the ordered set may still possibly have (-1) which would sum with 6 to
 *      the desired result (5), but (-3) does not because the greatest element
 *      in the set is 6, whichi is smaller than required - number 8.
 * 
 *      There is no equivalence because if two elements were an even distance
 *      from the sum, then they would sum to that sum. That is,
 * 
 *          If you had (-5) and (10), and you where trying to sum to five then
 *          on evaluation you would find that (-5) + 10 = 5, the pair you were
 *          looking for.
 *      
 *      There are only asymetries upon sorting, and we just discard those
 *      which are more asymetric than the rest of the set.
 * 
 * Slower than using a hashing function to check multiple nodes simultaneously.
 * Illustrates how computational theory can contribute more effectively than
 * an abstracted understanding of numbers. Sometimes the manipulation of data
 * is more costly than actually solving the problem.
 * 
 *      You can say that this algo depends on specific knowledge which other
 *      algos do not. The cost of aquiring that knowledge (order) is
 *      computationally more expensive that the costs of other solutions;
 *      however this solution uses less memory if both a computationally and
 *      memory favourable sorting algorithm is chosen!
 */
public class SumCheckerWithSorting {
    public static void main(String[] arg) {
        SumCheckerWithSorting.test(5, new int[] {1,4});
        SumCheckerWithSorting.test(5, new int[] {1,3});
        SumCheckerWithSorting.test(5, new int[] {-10,-2,-1,5,6});
        SumCheckerWithSorting.test(5, new int[] {6,0,0,0,2,2,3});
    }
    
    /**
     * Test and produce output for invariant check.
     * @param sum the invariant sum to check
     * @param arr the array of ints to check for the sum
     * @return if the invariant holds
     */
    public static boolean test(int sum, int[] arr) {
        boolean retBool = checkForSum(sum, arr);
        
        System.out.println(""
            + "Sum " + sum + " found [" + retBool + "].");
            
        return retBool;
    }
    
    /**
     * Check an array for two indices which sum to a number
     * @param sum the summation predicate
     * @param arr the array of ints which to consider
     * @return if a sum could be found
     */
    private static boolean checkForSum(int sum, int[] arr) {
        Arrays.sort(arr);
        
        for(int left = 0, right = arr.length - 1; left < right;)
            if (arr[left] + arr[right] == sum) return true;
            else if (arr[left] + arr[right] < sum) left++;
            else right--;
        
        return false;
    }
}
