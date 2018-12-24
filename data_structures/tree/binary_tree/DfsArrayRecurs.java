import java.util.Objects;
import java.util.Stack;

public class DfsArray<T> {
    public static void main(String[] args) {
        DfsArray<String> tree = new DfsArray<>();
        
        for (int i=0; i<100; i++)
            tree.insert(Integer.toString(i));
        
        System.out.println("Found '0' ["  + tree.search("0") + "].");
        System.out.println("Found '33' [" + tree.search("33") + "].");
        System.out.println("Found '99' [" + tree.search("99") + "].");
        System.out.println("Found '-1' [" + tree.search("-1") + "].");
    }
    
    T[] a; // array
    int n; // size
    
    public void insert(T data) {
        if (null == a || n + 1 > a.length) resize();
        a[n++] = data;
    }
    
    public T search(T key) {
        if (null == a) return null;        
        return dfsHelper(key, 0);
    }
    /***********************************************************************/
    public T dfsHelper(T key, int idx) {
        T retVal = null;
        int l = 1+idx*2;
        int r = 2+idx*2;
        
        if (Objects.equals(key, a[idx])) return a[idx];
        
        T tmpVal = null;
        if (a.length > l && null != a[l]) tmpVal = dfsHelper(key, l);
        if (null != tmpVal) retVal = tmpVal;
        if (a.length > r && null != a[r]) tmpVal = dfsHelper(key, r);
        if (null != tmpVal) retVal = tmpVal;
        
        return retVal;
    }
    /***********************************************************************/
    
    public void resize() {
        int newLen = Math.max(2*n, 1);
        
        T[] b = (T[]) new Object[newLen];
        copy(b, a);
        a = b;
    }
    public void copy(T[] newArr, T[] oldArr) {
        if (null != newArr && null != oldArr)
            for (int i=0; i<n; i++)
                newArr[i] = oldArr[i];
    }
}
