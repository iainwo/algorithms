import java.util.HashMap;
import java.util.Objects;
import java.util.NoSuchElementException;

/**
* Sequence Object -
* Stores a sequence and provides methods to calculate the longest common substring
*/
public class Sequence<T extends Comparable<T>> {
	public static void main(String[] args) {
		String[] iainGenome = { "iain", "g", "o", "o", "g", "l", "y" };
		Sequence<String> iainDna = new Sequence<>(iainGenome);
		
		String[] gglGenome = { "ggl", "g", "o", "o", "g", "l", "y" };
		Sequence<String> gglDna = new Sequence<>(gglGenome);

		System.out.println(""
			+ "the longest sequence iainDna and gglDna share is "
            + iainDna.getSharedLen(gglDna)
			+ " elements long");
		
		/* TESTS */
		sequence(null, null);
		sequence(null, new String[] {});
		sequence(new String[] {}, null);
		sequence(new String[] {"1"}, null);
		sequence(new String[] {}, new String[] {});
		sequence(new String[] {""}, new String[] {});
		sequence(new String[] {"1"}, new String[] {});
		sequence(new String[] {"1", "2"}, new String[] {});
		sequence(new String[] {"1", "3"}, new String[] {});
		sequence(new String[] {"1", "2", "3"}, new String[] {"1", "2", "3"});
		sequence(new Integer[] {1, 2, 3, 4, 5}, new Integer[] {1, 2, 3, 4, 5});
		sequence(new Integer[] {1, 2, 3, 4, 4}, new Integer[] {1, 2, 3, 4, 5});
		sequence(new Integer[] {1, 2, 4, 4, 4}, new Integer[] {4, 4, 3, 4, 5});
	}
	
	private static <T extends Comparable<T>> void sequence(T[] a1, T[] a2) {
	    Sequence<T> s1 = new Sequence<>(a1);
	    Sequence<T> s2 = new Sequence<>(a2);
	    
	    System.out.println(""
			+ "the longest sequence S1 and S2 share is "
            + s1.getSharedLen(s2)
			+ " elements long");
	}
	
	/**
	* Get the longest contiguous subsequence of the parameterized sequence and 
    * this sequence. Return the size of that subsequence.
    * @param o the sequence which to compare
    * @return the size of the longest shared sequence
    */
    public int getSharedLen(Sequence<T> o) {
    	SequenceMap m = new SequenceMap();
        SequenceMap r = new SequenceMap();
    	return getSharedLenImpl(o, size(), o.size(), m, r);
    }

    /**
    * Implementation of getShareLen.
    * Get the longest contiguous subsequence between two sequences.
    * @param other the second sequence
    * @param x the size of this sequence
    * @param y the size of the second sequence
    * @param h the hashmap to store memoized stack recursions
    * @param r the hashmap to store memoized root recursions
    * @return the length
    */
    private int getSharedLenImpl(
        Sequence<T> o, 
        int x, 
        int y,
        SequenceMap m,
        SequenceMap r) {
            
        if (0 >= x || 0 >= y) return 0;
    
        // the length of the sequence rooted at these indices
    	int rootedSeq = r.getSeq(x,y);
    	if (!m.containsSeq(x,y)) {
    		rootedSeq = getRootedSharedLen(o, x, y, r);
    		r.putSeq(x, y, rootedSeq);
    	}
    
    	// the len of the seq with one less element in this seq
        int leftSeq = m.getSeq(x,y);
    	if (!m.containsSeq(x,y)) {
    		leftSeq = getSharedLenImpl(o, x-1, y, m, r);
    		m.putSeq(x-1, y, leftSeq);
    	}
    
    	// the len of the seq with one less element in the other seq
        int rightSeq = m.getSeq(x,y);
    	if (!m.containsSeq(x,y)) {
    		rightSeq = getSharedLenImpl(o, x, y-1, m, r);
    		m.putSeq(x, y-1, rightSeq);
    	}
    	m.putSeq(x, y, max(rootedSeq, leftSeq, rightSeq));
    
    	return m.getSeq(x,y);
    }

    private int max(int... nums) {
        int max = Integer.MIN_VALUE;
        for (int no : nums)
            max = Math.max(no, max);
        return max;
    }
    
    /**
    * Get the length of the shared subsequence rooted at the parameterized indices.
    * In order for a subsequence to contiguous the longest substrings have to be adjacent.
    * @param o the other sequence.
    * @param x the size of this sequence
    * @param y the size of the other sequence
    * @return the length of the shared rooted sequence.
    */
    private int getRootedSharedLen(
        Sequence<T> o, 
        int x, 
        int y, 
        SequenceMap m) {
    
    	if (0 >= x || 0 >= y) return 0; // base case
    	if (!Objects.equals(get(x-1), o.get(y-1))) return 0; // commonality ends
    
    	int rootedSeq = m.getSeq(x,y);
    	if (!m.containsSeq(x,y)) {
    		rootedSeq = 1 + getRootedSharedLen(o, x-1, y-1, m);
    		m.putSeq(x, y, rootedSeq);
    	}
    	return m.getSeq(x,y); // commonality continues
    }

    /**
    * Get the size of the underlying sequence.
    * @return the size of the underlying sequence array
    */
    public int size() { return (null == a) ? 0 : a.length; }
    
    /**
    * Get the element of the sequence at a given index.
    * @param i the index to retrieve
    * @return element found at index
    */
    public T get(int i) {
    	if (null == a || 0 == a.length) throw new NoSuchElementException();
    	if (a.length <= i) throw new IndexOutOfBoundsException();
    	return a[i];
    }

	private T[] a; // underlying sequence

	/**
	* DO NOT USE.
    * Privatized base constructor.
	* Sequences must be created with their sequence data.
	*/
	private Sequence() {}

	/**
	* Default Constructor.
	* Used to provision sequence data and create a Sequence.
	* @param a the sequence
	*/
	public Sequence(T[] a) {
		this();
		this.a = a;
	}

	static class SequenceMap extends HashMap<String, Integer> {
		public boolean containsSeq(int x, int y) {
			return containsKey(getSeqKey(x,y));
        }
        public int putSeq(int x, int y, int len) {
        	String key = getSeqKey(x,y);
        	Integer i = put(key, len);
        	return (null != i) ? i : 0;
        }
        public String getSeqKey(int x, int y) {
        	return x + "," + y;
        }
        public int getSeq(int x, int y) {
        	Integer i = get(getSeqKey(x, y));
        	return (null != i) ?  i : 0;
        }
	}
}