
import java.lang.Math;

interface IList<T> {
    boolean add(int i, T t);
    T remove(int i);
    
    T get(int i);
    T set(int i, T t);
}

public class ArrayDeque<T> implements IList<T> {
    public static void main(String[] args) {
        IList<String> list = new ArrayDeque<>();
        
        for (int i = 0; i < 50; i++) {
             System.out.println(""
                + "Adding index #" + 0 + " value [" + i + "].");
            list.add(0, Integer.toString(i));
        }
        for (int i = 0; i < 50; i++) {
             System.out.println(""
                + "Getting index #" + i + " value [" + list.get(i) + "].");
        }
        for (int i = 0; i < 50; i++) {
             System.out.println(""
                + "Setting index #" + i + " value [" + i + "].");
            list.set(i, Integer.toString(i));
        }
        for (int i = 0; i < 50; i++) {
            System.out.println(""
                + "Removed index #" + 0 + " of value [" + list.remove(0) + "].");
        }
    }
    
    int n; // num. elements
    T[] a; // array back
    int v; // curr idx
    
    @Override
    public boolean add(int i, T val) {
        if (0 > i || n < i) throw new IndexOutOfBoundsException();
        if (null == a || n + 1 > a.length) restructure();
        
        if (i < n/2) {
            v = (v == 0) ? a.length - 1 : v - 1; // to avoid -1 mod a.length
            for (int j = 0; j < i - 1; j++) {
                a[(v + j) % a.length] = a[(v + j + 1) % a.length];
            }
        } else {
            for (int j = n; j > i; j--) {
                a[(v + j) % a.length] = a[(v + j - 1) % a.length];
            }
        }
        a[(v + i) % a.length] = val;
        n++;
        return true;
    }
    
    @Override
    public T remove(int i) {
        if (null == a || n - 1 < i) throw new IndexOutOfBoundsException();
        T prev = a[(v + i) % a.length];
        if (i < n/2) {
            for (int j = i; j > 0; j--)
                a[(v + j) % a.length] = a[(v + j - 1) % a.length];
            v = (v + 1) % a.length;
        } else {
            for (int j = i; j < n; j++)
                a[(v + j) % a.length] = a[(v + j + 1) % a.length];
        }
        n--;
        
        if (a.length >= 3*n) restructure();
        return prev;
    }
    
    @Override
    public T get(int i) {
        if (null == a || 0 > i || n - 1 < i) throw new IndexOutOfBoundsException();
        return a[(v + i) % a.length];
    }
    
    @Override
    public T set(int i, T val) {
        if (null == a || 0 > i || n - 1 < i) throw new IndexOutOfBoundsException();
        T prev = a[(v + i) % a.length];
        a[(v + i) % a.length] = val;
        return prev; // former val
    }
    
    private void restructure() {
        int newSize = Math.max(2*n, 1);
        
        T[] b = (T[]) new Object[newSize];
        
        for (int j = 0; j < n; j++)
            b[j] = a[(v + j) % a.length];
        
        a = b;
        v = 0; // reset pointer
    }
}
