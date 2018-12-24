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
    
    /***********************************************************************/
    public T search(T key) {
        if (null == a) return null;
        
        int leafIdx = 0;
        int i = 0;
        int binLen = (int)Math.pow(2, Math.ceil(Math.log(a.length)/Math.log(2)));
        
        while (0 <= i && binLen > i) {
            if (a.length > i)
                if (Objects.equals(key, a[i])) return a[i];
            
            if ((binLen-1) / 2 > i) {
                i = 2*i + 1;
            } else {
                int k = 1;
                for (;;) {
                    int interval = (int)Math.pow(2,k);
                    int offset = (int)Math.pow(2,k-1) - 1;
                    
                    i = (i - 1) / 2;
                    
                    if (0 == i &&  (binLen-1) / 2 == leafIdx) {
                        return null;
                    }
                    if (0 == (leafIdx % interval) - offset) {
                        i = i*2 + 2;
                        leafIdx++;
                        break;
                    }
                    k++;
                }
            }
        }
        
        return null;
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
