import java.util.function.IntPredicate;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
 
/**
 * This is a generic binary invariant checker that is limited by Java's
 * Object hashing implementation.
 * This operates in linear time, and the hashing add, contains and resize 
 * operates in O(1).
 * 
 * The attractiveness to a hashmap is that instead of attempting to assert an
 * invariant against all the other nodes of a population; instead, the hash
 * mechanics perform the assert that the current node is unlike any-other node.
 * This mechanic performs in O(1) and allows us to infer that the current node
 * does not satisfy the invariant with any other node. This done subsequently to
 * the remainder of the list yields an O(1) cost per further index.
 * 
 * A intuitive approach would be to iterate through the array and compare all
 * combinations of indices. This is great in that no additional memory is
 * required! However, this comes at the expense of a more expensive runtime.
 * Where the number of possible combinations equates to the number of times
 * the invariant needs to be checked. The numerity of elements contributes
 * quadratically to the number of times an invariant needs to be checked,
 * 
 * If there are `n` indices in an array, then there are (n choose k), where k
 * is '2' - because this is a binary invariant, number of combinations.
 * 
 * This boils down to,
 * = n!/[(n-k)!k!]
 * = 1/2 * (n**2 - n)
 * 
 * => :. O(n**2) worst case.
 */
public class BinaryInvariantChecker {
    public static void main(String[] args) {
        
        /* Setup transformations and invariants */
        Predicate<Integer> hasSum = i -> 5 == i;
        Function<Integer, Integer> genIntPair = i -> (0 > i) ? 5 + i : 5 - i;
        
        Predicate<String> isEqual = s -> "test".equals(s);
        Function<String, String> genStrPair = s -> s;
        
        Predicate<Boolean> isTrue = b -> true == b;
        Function<Boolean, Boolean> genBoolPair = arr -> true;
        
        /* Passing Invariants */
        Integer[] intArr = {0, 1, 2, 10, 11, 4};
        String[] strArr = {"abc", "def", "test", "face", "test"};
        Boolean[] boolArr = {true, false, false, true};
        
        BinaryInvariantChecker.test(hasSum, genIntPair, intArr);
        BinaryInvariantChecker.test(isEqual, genStrPair, strArr);
        BinaryInvariantChecker.test(isTrue, genBoolPair, boolArr);
        
        /* Failing Invariants */
        Integer[] intFailArr = {0, 1, 2, 10, 11};
        String[] strFailArr = {"abc", "def", "notest", "face"};
        Boolean[] boolFailArr = {false, false, false, true};
        
        BinaryInvariantChecker.test(hasSum, genIntPair, intFailArr);
        BinaryInvariantChecker.test(isEqual, genStrPair, strFailArr);
        BinaryInvariantChecker.test(isTrue, genBoolPair, boolFailArr);
    }
    
    public static <T> boolean test(Predicate<T> p, Function<T,T> f, T[] arr) {
        boolean retBool = checkInvariant(p, f, arr);
        System.out.print("Result [" + retBool + "].");
        System.out.println(""
            + " Checking arr [" + Arrays.toString(arr) + "]"
            + " for invariant [" + p + "]"
            + " using resolver [" + f + "].");
        
        return retBool;
    }
    /**
     * Determine if the array satisfies the invariant
     * @param p the invariant to satisfy
     * @param f method to resolve the paired value which will satisfy the invariant
     * @param arr the population from which combos can be derived
     */
    private static <T> boolean checkInvariant(
        Predicate<T> p,
        Function<T,T> f,
        T[] arr) {
        
        Set<T> multiNodeCheck = new HashSet<>();
        for (int i = 0; i < arr.length; i++)
            if (multiNodeCheck.contains(f.apply(arr[i])))
                return true; // jack pot
            else
                multiNodeCheck.add(arr[i]);
        return false;
    }
}
