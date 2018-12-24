import java.util.Objects;
import java.util.Stack;

public class BfsArray<T> {
    public static void main(String[] args) {
        BfsArray<String> tree = new BfsArray<>();
        
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
        Stack<Integer> stack = new Stack<>();
        if (null != a && null != a[0]) stack.push(0);
        while (!stack.empty()) {
            int i = stack.pop();
            int l = 2*i+1;
            int r = 2*i+2;
            if (Objects.equals(key, a[i])) return a[i];
            if (a.length > r && null != a[r]) stack.push(r);
            if (a.length > l && null != a[l]) stack.push(l);
        }
        return null;
    }
    
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
