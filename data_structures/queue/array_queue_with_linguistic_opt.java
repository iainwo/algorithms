import java.lang.Math;
/**
 * Derived from,
 * https://opendatastructures.org/ods-java/2_3_ArrayQueue_Array_Based_.html#tex2html18
 */
interface IQueue<T> {
    boolean add(T t);
    T remove();
    int size();
}
public class ArrayQueue<T> implements IQueue<T> {
    public static void main(String[] args) {
        IQueue<String> queue = new ArrayQueue<>();
        
        for (int i = 0; i < 500; i ++) {
            String val = Integer.toString(i);
            System.out.println(""
                + "Adding queue value [" + val + "]."
                + " Queue of size [" + queue.size() + "].");
            queue.add(val);
        }
        for (int i = 0; i < 500; i ++) {
            System.out.println(""
                + "Removed queue value [" + queue.remove() + "]."
                + " Queue of size [" + queue.size() + "].");
        }

    }
    
    int n; // Size of Queue
    T[] a; // Array
    int v; // Current index
    
    @Override
    public int size() {
        return n;
    }
    
    @Override
    public boolean add(T t) {
        if (null == a || a.length < n + 1) restructure();
        a[(v + n) % a.length] = t;
        n++;
        return true;
    }
    @Override
    public T remove() {
        T val = a[(v % a.length)];
        a[(v % a.length)] = null;
        v++;
        n--;
        if (a.length >= 3*n) restructure();
        return val;
    }
    private void restructure() {
        int newSize = Math.max(2*n, 1);
        T[] b = (T[]) new Object[newSize];
        
        if (null != a) {
            int delta1 = Math.min(a.length - (v % a.length), n + (v % a.length));
            int delta2 = (a.length > (v % a.length) + n) ? 0 : ((v % a.length) + n - 1) % a.length;
            
            // if the array does overflow to the beg. then the second delta
            // is equal to the remainder/modulus of that overflow
            // easy peasy :)
            
            int startIdx = (v % a.length);

            /* Language efficiency. 
             * System arraycopy is 2x or 3x faster than for */
            System.arraycopy(a, startIdx, b, 0, delta1);
            System.arraycopy(a, 0, b, delta1, delta2);
        }
        
        a = b;
        v = 0;
    }
}
