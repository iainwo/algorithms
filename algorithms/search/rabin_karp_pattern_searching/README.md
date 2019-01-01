# Rabin-Karp Pattern Searching
> _Notes based on [geeksforgeeks.org][1]._ <br>

This alogrithm finds subpatterns in a data-set.

Any given dataset with $`M`$ elements and given pattern of $`N`$ elements, has $`{M \choose N}`$ possible substrings, that is $`\frac{M!}{(M-N)!N!}`$ so as $`M`$ increase it begins to dominate, and the quotient will congruently increase.

Pattern matching solutions which seek to compare the individual elements of a pattern against each individual element of the dataset have the cost of doing those individual comparisons.

In large datasets where the pattern is small in relation to that dataset a more efficient way of determining equivalence is by hashing.

The idea behind `Rabin-Karp Pattern Searching` is that all of the $`{M \choose N}`$ combinations are compared - but instead of comparing the individual elements of the pattern and subtring, rather compare another identifying feature which is faster to compare. The bottleneck then gets shifted to the way which you are identifying substrings and the pattern (i.e hashing). Rabin-Karp uses the idea of a _rolling identity_ or a running-sum-like hash to approximate the identity of each substring combination in the dataset. 
> The mechanism for producing this identifying feature is hashing <br>
> Hashing values produces hashes, and hashes are a identifying feature which is fast to compare. <br>
> This technique seems flawed - the rolling hashing, may end-up calculating hashes over sequential combinations of substrings which you could have already derived as not matching. For example, if the pattern was 'ABC' and the next ten combinations in the sequences of all combinations were only composed of letters 'D-Z'. Seems like you could chunk through these.

Here is one implementation, the hashing is based on a very weak version of polynomial-over-primes hashing of a vector. All the coefficients of those polynomials are (1) and furthermore there is no randomized distribution of the vector-index which you are hashing which in theoretical hashing means adjacent or locally-near elements have a greater probability for their hashes to collide. `HOWEVER` - this is okay for this application, because the heuristic is that no "char" character has a value than 256, that is $`2^8`$. Therefor you do not need to randomize the identity of the thing you are hashing, because there is no possible way for that identity at the scope of an individual element to collide with another element.
> Note - this implementation does seem weird depending on the size of the prime. The example `101` seems like a very small bit-width, and the number of compounding hash values which could collide with a given value would imaginably increase. Have to assess the ratio between the number of increased collision combinations grows in proportion to the number of non-colliding combinations in order to determine the probability of collision increases with longer vectors. <br>
> `THIS IMPLEMENTATION USES MODULAR MULTIPLACTION/EXPONENTIATION` - the hashing is maintained by shifting the rolling hash by the radix of the letter system ~256. To determine where the bit values of the first index character have been fragmented after a series of multiplicative shifts equal to the length-`2` of the pattern, you need to calculate, what range of bits could hold the bits of the first index character. This is done by modular exponentation - determining the remainder after so many shifts and that remainder multiplied by the original value will give you the location of those bits! The subtraction can work because the added values to the rolling hash will never exceed the bit-boundaries of 256.
```java
// d is the number of characters in the input alphabet 
    public final static int d = 256; 
    int q = 101; // A prime number 
    
    /* pat -> pattern 
        txt -> text 
        q -> A prime number 
    */
    static void search(String pat, String txt, int q) 
    { 
        int M = pat.length(); 
        int N = txt.length(); 
        int i, j; 
        int p = 0; // hash value for pattern 
        int t = 0; // hash value for txt 
        int h = 1; 
      
        // The value of h would be "pow(d, M-1)%q" 
        for (i = 0; i < M-1; i++) 
            h = (h*d)%q; 
      
        // Calculate the hash value of pattern and first 
        // window of text 
        for (i = 0; i < M; i++) 
        { 
            p = (d*p + pat.charAt(i))%q; 
            t = (d*t + txt.charAt(i))%q; 
        } 
      
        // Slide the pattern over text one by one 
        for (i = 0; i <= N - M; i++) 
        { 
      
            // Check the hash values of current window of text 
            // and pattern. If the hash values match then only 
            // check for characters on by one 
            if ( p == t ) 
            { 
                /* Check for characters one by one */
                for (j = 0; j < M; j++) 
                { 
                    if (txt.charAt(i+j) != pat.charAt(j)) 
                        break; 
                } 
      
                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1] 
                if (j == M) 
                    System.out.println("Pattern found at index " + i); 
            } 
      
            // Calculate hash value for next window of text: Remove 
            // leading digit, add trailing digit 
            if ( i < N-M ) 
            { 
                t = (d*(t - txt.charAt(i)*h) + txt.charAt(i+M))%q; 
      
                // We might get negative value of t, converting it 
                // to positive 
                if (t < 0) 
                t = (t + q); 
            } 
        } 
    }
    // Written by nuclode 
```
> From [geeksforgeeks.org][1]

[1]: https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/