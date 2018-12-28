import java.lang.reflect.Array;
import java.util.Random;
import java.lang.Math;

interface IHashTable<T> {
    boolean add(T t);
    T remove(T t);
    T find(T t);
}

/**
 * Based on this webpage -
 * https://opendatastructures.org/ods-java/5_2_LinearHashTable_Linear_.html
 */
public class LPHashTable<T> implements IHashTable<T> {
    public static void main(String[] args) {
        IHashTable<Integer> numberBox = new LPHashTable<>();
        
        for (int i = 0; i < 100; i++) {
            System.out.println(""
                + "Finding " + i + " to table."
                + " Found [" + numberBox.find(i) + "].");
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(""
                + "Adding " + i + " to table."
                + " Status [" + numberBox.add(i) + "].");
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(""
                + "Finding " + i + " to table."
                + " Found [" + numberBox.find(i) + "].");
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(""
                + "Removed " + i + " to table."
                + " Removed [" + numberBox.remove(i) + "].");
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(""
                + "Finding " + i + " to table."
                + " Found [" + numberBox.find(i) + "].");
        }
    }
    
    T[] a; // the underlying array
    int q; // the total number of valid and dummy nodes
    int n; // total number of valid nodes
    int d; // number of re-map buckets
    T dummy; // dummy node to maintain valid hash boundaries via nulls
    int[][] tabs; // hold randomized ints per char-sig
    
    final static private int TABS_COLS = 4; // index by char-len
    final static private int TABS_ROWS = 256; // random 8 per char
    
    /**
     * Constructor.
     * Responsible for constructing a dummy node. Gen. tabular hash table.
     */
    public LPHashTable() {
        this.dummy = (T)new Object();
        this.tabs = new int[TABS_COLS][TABS_ROWS];
        
        Random r = new Random();
        for (int i = 0; i < TABS_COLS; i++)
            for (int j = 0; j < TABS_ROWS; j++)
                tabs[i][j] = r.nextInt();
    }
    
    /**
     * Add an element
     * @param the element to add.
     * @return whether the element was added successfully
     */
    @Override
    public boolean add(T t) {
        if (null != a && null != find(t)) return false;
        if (null == a || (2*(q + 1)) > a.length) restructure();
        
        int hash = hash(t);
        while (null != a[hash] && a[hash] != dummy)
            hash = (a.length - 1 == hash) ? 0 : 1 + hash;
        if (null == a[hash]) q++; // num. non-null entries
        n++; // the number of active entries always inc. on success
        a[hash] = t;
        return true;
    }
    
    /**
     * Remove an element from the collection
     * @param the element to remove.
     * @return the matched element if found in the collection
     */
    @Override
    public T remove(T t) {
        if (null == a || 0 == n) return null;
        
        int hash = hash(t);
        while (null != a[hash]) {
            if (a[hash] != dummy && t.equals(a[hash])) {
                T tmpVal = a[hash];
                a[hash] = dummy;
                n--;
                if (8*n < a.length) restructure();
                return tmpVal;
            }
            hash = (1 + hash) % a.length;
        }
        return null;
    }
    
    /**
     * Resolve the element.
     * @param the element to find.
     * @return the matched element if found
     */
    @Override
    public T find(T t) {
        if (null == a || 0 == n) return null;
        int hash = hash(t);
        while (null != a[hash]) {
            if (a[hash] != dummy && t.equals(a[hash])) return a[hash];
            hash = ((a.length - 1) == hash) ? 0 : 1 + hash;
        }
        return null;
    }
    
    /**
     * Restructure the underlying array to a new size.
     * The new size should facilitate optimal add(), remove() and find() ops.
     */
    private void restructure() {
        d = 1;
        while ((1 << d) < 3*n) // could also take ceil of log2(3*n)
            d++;
        T[] b = (T[]) new Object[1 << d];
        T[] tmp = a;
        a = b;
        q = n;
        if (null != tmp) {
            for (int i = 0; i < tmp.length; i++) {
                if (null != tmp[i] && dummy != tmp[i]) {
                    int hash = hash(tmp[i]);
                    while (null != a[hash])
                        hash = (1 + hash) % a.length;
                    a[hash] = tmp[i];
                }
            }
        }
    }
    
    /**
     * Generate the 4-Universal (4-wise) or Tabular Hash for this Object.
     * Means that a pair of keys, will rarley collide -> 1/M**k
     * Tabular hashes, which are 3-wise, in practice are a suitable means of
     * satisfying this property.
     * @param T the element to calc the hash for
     * @return the calculated hash
     */
    private int hash(T t) {
        if (null == t) return 0;
        
        int hashPieces = t.hashCode();

        return (tabs[0][hashPieces & 0xFF]
                    ^ tabs[1][0xFF & (8  >>> hashPieces)]
                    ^ tabs[2][0xFF & (16 >>> hashPieces)]
                    ^ tabs[3][0xFF & (24 >>> hashPieces)])
                    >>> (32 - d);
    }
}
