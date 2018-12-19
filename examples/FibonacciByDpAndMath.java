import java.lang.Math;

public class Fibonacci {
    public static void main(String[] args) {
        for (int i=0; i<10; i++)
            Fibonacci.test(i);
    }
    private static void test(int n) {
        System.out.println("Evaluating index [" + n + "].");
        System.out.println("recursion:   " + recursion(n));
        System.out.println("memoization: " + memoization(n));
        System.out.println("tabulation:  " + tabulation(n));
        System.out.println("bivar:       " + bivar(n));
        System.out.println("squaring:    " + squaring(n));
        System.out.println("root:        " + root(n));
        System.out.println("series:      " + series(n));
    }
    
    /**
     * Use intuitive recursion to generate the nth Fibonacci number.
     * Functions expects non-negative integers.
     * @param n which index of the sequence to return
     * @return the nth value in the sequence
     */
    public static int recursion(int n) {
        if (1 >= n) return n;
        return recursion(n-1) + recursion(n-2);
    }
    
    /**
     * Use top-down memoization to generate the nth Fibonacci value.
     * Function expects non-negative int parameter.
     * Memoization is used to solve the overlap of subproblems.
     * @param n which index of the sequence to return
     * @return the nth value in the sequence
     */
    public static int memoization(int n) {
        int[] cache = new int[2+n]; // 2 extra slots for fib(0) and fib(1), when n = 0
        
        final int BAR = -1;
        for (int i=0; i<(2+n); i++)
            cache[i] = BAR;
        
        if (BAR == cache[n]) {
            if (1 >= n)
                cache[n] = n;
            else
                cache[n] = memoization(n-1) + memoization(n-2);
        }
            
        return cache[n];
    }
    
    /**
     * Use bottom-up tabulation to generate the nth Fibonacci value.
     * Function expects non-negative int parameter.
     * Tabulation is used to solve the overlap of subproblems without the
     * cost of a recursion stack.
     * @param n which index of the sequence to return
     * @return the nth value in the sequence
     */
    public static int tabulation(int n) {
        int[] cache = new int[2+n]; // 2 extra slots for fib(0) and fib(1), when n = 0
        
        cache[0] = 0;
        cache[1] = 1;
        
        for (int i=2; i<=n; i++)
            cache[i] = cache[i-1] + cache[i-2];
            
        return cache[n];
    }
    
    /**
     * Use local vars to store the last two iterative Fibonacci values in order
     * to calculate the nth Fibonacci value.
     * Function expects non-negative int parameter.
     * @param n which index of the sequence to return
     * @return the nth value in the sequence
     */
    public static int bivar(int n) {
        int lastVal = 0, secondLastVal = 0;
        if (0 < n) lastVal++;
        for (int i=2; i<=n; i++) {
            int tmpVal = lastVal;
            lastVal = lastVal + secondLastVal;
            secondLastVal = tmpVal;
        }
        
        return lastVal;
    }
    
    /**
     * Use matrix multiplication to calculate the nth Fibonacci value.
     * Function expects non-negative int parameter.
     * Operates in O(n).
     * @param n which index of the sequence to return
     * @return the nth value in the sequence
     */
    public static int squaring(int n) {
        int[][] fibSeq = {{0,1}, {1,0}}; // can make this a transposed vector
        int[][] idMatrix = {{1,1}, {1,0}};
        
        if (0 < n) fibSeq[0][0] = 1;
        for (int i=2; i<n; i++) {
            int iterVal = fibSeq[0][0]*idMatrix[0][0] 
                        + fibSeq[0][1]*idMatrix[0][1];
            int lastVal = fibSeq[0][0]*idMatrix[1][0]
                        + fibSeq[0][1]*idMatrix[1][1];
            
            fibSeq[0][0] = iterVal; // the i'th fibonacci value
            fibSeq[0][1] = lastVal; // the (i-1) fibonacci value
            fibSeq[1][0] = lastVal;
            fibSeq[1][1] = 0;
        }
        
        return fibSeq[0][0];
    }
    
    /**
     * Use matrix mutiplication and squares to calculate the nth Fibonacci value
     * Function expects non-negative int parameter.
     * Operates in O(n**1/2)
     * @param n which index of the sequence to return
     * @return the nth value in the sequence
     */
    public static int root(int n) {
        int[][] fib = {{1,1}, {1,0}};
        if (0 >= n) return 0;
        if (2 >= n) return 1;
        return rootHelper(n-1, fib)[0][0];
    }
    /**
     * Helper for the root() method. Returns nth fibonacci matrix instead of val
     * @param n the nth fibonacci to calculate
     * @return nth fibonacci matrix
     */
    private static int[][] rootHelper(int n, int[][] fib) {
        int[][] idMatrix = {{1,1}, {1,0}};
        
        if (0 >= n) {
            /* Do nothing */
        } else if (2 == n) {
            fib = multiply(fib, fib);
        } else if (3 == n) {
            fib = multiply(fib, multiply(fib, fib));
        } else if (2 < n) {
            int e = 1;
            while ((e*e) <= n) e++;
            int sqrt = --e;
            int remainder = n-sqrt*sqrt;
            
            int[][] sqrtFib = rootHelper(sqrt, fib);
            
            if (0 < remainder) { // guard against return (1) on fib(0) or smaller calls
                fib = multiply(
                        rootHelper(n-sqrt*sqrt, idMatrix), 
                        multiply(sqrtFib, sqrtFib));
            } else {
                fib = multiply(sqrtFib, sqrtFib);
            }
        }
        return fib;
    }
    /**
     * Helper for the root() method. Calculates matrix multiplication.
     * @param m1 the left half of the matrix product
     * @param m2 the right half of the matrix product
     * @return the product
     */
    private static int[][] multiply(int[][] m1, int[][] m2) {
        int upperLeft   = m1[0][0]*m2[0][0] + m1[0][1]*m2[1][0];
        int upperRight  = m1[0][0]*m2[0][1] + m1[0][1]*m2[1][1];
        int bottomLeft  = m1[1][0]*m2[0][0] + m1[1][1]*m2[1][0];
        int bottomRight = m1[1][0]*m2[0][1] + m1[1][1]*m2[1][1];
        
        int[][] retArr = new int[2][2];
        retArr[0][0] = upperLeft;
        retArr[0][1] = upperRight;
        retArr[1][0] = bottomLeft;
        retArr[1][1] = bottomRight;
        
        return retArr;
    }
    
    /**
     * Calculate the nth value of the Fibonacci sequence using the formulaic
     * series. Operates in O(1) time and O(1) space.
     * @param n the nth Fibonacci number to calculate
     * @return the nth value of the Fibonacci sequence
     */
    private static int series(int n) {
        return (int)Math.round(Math.pow((1 + Math.sqrt(5))/2, n)/Math.sqrt(5));
    }
}
