import java.util.List;
import java.util.ArrayList;
import java.lang.Math;
import java.lang.reflect.Array;


/**
 * Hashing algo came from this web page,
 * https://opendatastructures.org/ods-java/5_1_ChainedHashTable_Hashin.html
 */
interface IHashLocker<T> {
    boolean add(T t);
    T remove(T t);
    T find(T t);
}

public class HashChainImpl<T> implements IHashLocker<T> {
    public static void main(String[] args) {
        IHashLocker<Integer> ids = new HashChainImpl<>();
        System.out.println(HASH_MODULUS);
        
        for (int i = 0; i < 100; i++) {
            System.out.println(""
                + "Adding " + i + " to HashChain");
            ids.add(i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(""
                + "Finding " + i + " in HashChain."
                + " Was found [" + ids.find(i) + "].");
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(""
                + "Removing " + i + " in HashChain."
                + " Was found [" + ids.remove(i) + "].");
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(""
                + "Finding " + i + " in HashChain."
                + " Was found [" + ids.find(i) + "].");
        }
    }
    
    List<T>[] a; // Chained hash storage
    int n; // number of elements stored
    final static int RND_DISTRIB = 0xFADE;
    final static int HASH_MODULUS = (0x1 << 31);
    

    /**
     * Add elements to the set
     */
    @Override
    public boolean add(T t) {
        if (null == a || n + 1 > a.length) restructure();
        if (null != find(t)) return false;
        
        int hash = hash(t);
        if (null == a[hash])
            a[hash] = new ArrayList<T>();
        a[hash].add(t);
        n++;
        
        return true;
    }
    
    /**
     * Remove matching element from the set
     * @param the element to remove
     * @return the matched element if found
     */
    @Override
    public T remove(T t) {
        T retVal = null;
        if (null != a && 0 != n) {
            List<T> tmp = a[hash(t)];
            if (null != tmp)
                for (int i = 0; i < tmp.size(); i++)
                    if (t == tmp.get(i)) {
                        retVal = tmp.remove(i);
                        break;
                    }
        }
        if (null != a && a.length >= 3*n) restructure();
        return retVal;
    }
    
    /**
     * Determine if the element exists in the set.
     * @param the element to match
     * @return the matched element in the set
     */
     @Override
     public T find(T t) {
         if (null != a && 0 != n) {
            List<T> tmp = a[hash(t)];
            if (null != tmp)
                for (int i = 0; i < tmp.size(); i++)
                    if (t == tmp.get(i))
                        return tmp.get(i);
         }
         return null;
     }
     
     /**
      * Return object hash
      * @param the element to generate the hash index for
      * @return the hash calculation
      * 
      * Probability of collision is = 2/(2**a.length)
      * 
      * Multiplicative Hasing -
      * Example,
      *                          1000000000000001
      *                          1000000000000011
      * |____________||____________||___________|
      *  (above mod)    in div rng.   below div.
      * 
      * The hash function: (rnd_dist * int) % 2**w div 2**(w-d),
      * Basically just takes a bunch of numbers and chops of the tops and bottoms.
      * It's a way of reducing a large amount of numbers into smaller categories,
      * base of their "central trunk".
      * 
      * Due to the "div" operation/ bitshifting, some numbers will be categorized
      * into the same bucket. The algo multiplies a rnd_dist by the int that is
      * being hashed, so that their is some randomness to the distribution so that
      * if you don't insert consecutive numbers there is less likelyhood you will
      * insert into the same bucket.
      * 
      * The algo removes (31 - d) bits from the right of the hash calc.
      * (31 - d) is really just 2**n, where "n" is the number of indices in
      * the backing array - so that their is congruency between the hash index
      * your generating and the total size of the backing array.
      * 
      * You can this of a hash function as a way of transforming a number of vectors
      * into a smaller vector space.
      * 
      * The (31 - d), can be used to determine the number of normal distributions
      * (i.e that is vector transformations) which share the same categorization
      * from the large vector space to the subvector space supported by this 
      * hashing algo and it's current params.
      */
     private int hash(T t) {
         int d = (int)Math.floor(Math.log(n)/Math.log(2));
         return (RND_DISTRIB * t.hashCode()) >>> (31 - d);
     }
     
     private void restructure() {
         int newSize = Math.max(2*n, 1);
         
         List<T>[] b = (List<T>[]) Array.newInstance(List.class, newSize);
         
         List<T>[] tmp = a;
         a = b;
         n = 0;
         
         if (null != tmp)
             for (int i = 0; i < tmp.length; i++)
                if (null != tmp[i])
                    for (int j = 0; j < tmp[i].size(); j++) {
                        T val = tmp[i].get(j);
                        if (null != val)
                            add(val);   
                    }
     }
}
