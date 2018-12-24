import java.util.Objects;
import java.util.Queue;
import java.util.LinkedList;

public class UnbalancedBST<T extends Comparable<T>> {
    public static void main(String[] args) {
        UnbalancedBST<String> bst = new UnbalancedBST<>();
        
        for (int i=0; i<100; i++)
            bst.add(Integer.toString(i));
        
        bst.print();
        
        for (int i=0; i<50; i++)
            bst.remove(Integer.toString(i));
            
        bst.print();
        
        for (int i=0; i<25; i++)
            bst.remove(Integer.toString(110-i));
            
        bst.print();
    }
    
    Node<T> root; // root
    int n; // element count
    
    private void splice(Node<T> u) {
        Node<T> s, p;
        
        if (null != u.getLeft()) s = u.getLeft();
        else s = u.getRight();
        
        if (u == root) {
            root = s;
            p = null; // root has no parent
        } else {
            p = u.getParent();
            
            if (p.getLeft() == u) p.setLeft(s);
            else p.setRight(s);
        }
        
        if (null != s) s.setParent(p);
        
        n--; // decrement the count
    }
    public void remove(T x) {
        Node<T> u = findLast(x);
        if (null != u) remove(u);
    }
    private void remove(Node<T> u) {
        if (null == u.getLeft() || null == u.getRight())
            splice(u);
        else {
            Node<T> b = u.getRight();
            while (null != b.getLeft()) b = b.getLeft();
            u.setData(b.getData());
            splice(b);
        }
    }
    public boolean add(T x) {
        Node<T> p = findLast(x);
        return addChild(p, new Node<T>(x));
    }
    private Node<T> findLast(T x) {
        Node<T> c = root, p = null;
        
        while (null != c) {
            p = c;
            int cmp = x.compareTo(c.getData());
            
            if (0 > cmp) c = c.getLeft();
            else if (0 < cmp) c = c.getRight();
            else c = null; 
        }
        return p;
    }
    private boolean addChild(Node<T> p, Node<T> x) {
        if (null == p) {
            root = x;
        } else {
            int cmp = x.getData().compareTo(p.getData());
            if (0 > cmp) p.setLeft(x);
            else if (0 < cmp) p.setRight(x);
            else return false;
            x.setParent(p);
        }
        n++;
        return true;
    }
    
    public void print() {
        if (null != root) root.print();
    }
    
    public UnbalancedBST() {
        root = null;
    }
    static class Node<T extends Comparable<T>> {
        private Node<T> l, r, p;
        private T data;
        public Node() {
            l = r = p = null;
            data = null;
        }
        public Node(T x) {
            this();
            this.data = x;
        }
        
        public void print() {
            Queue<Node<T>> level = new LinkedList<>();
            level.add(this);
            Queue<Node<T>> next = new LinkedList<>();
            
            while (!level.isEmpty()) {
                Node<T> c = level.remove();
                System.out.print(c + "\t");
                if (null != c) {
                    if (null != c.getLeft()) next.add(c.getLeft());
                    if (null != c.getRight()) next.add(c.getRight());
                }
                
                if (level.isEmpty()) {
                    Queue<Node<T>> tmp = level;
                    level = next;
                    next = tmp;
                    
                    System.out.println("");
                }
            }
        }
        
        @Override
        public String toString() {
            return "Node [" + getData() + "].";
        }
        
        public Node<T> getLeft() { return l; }
        public Node<T> getRight() { return r; }
        public Node<T> getParent() { return p; }
        public void setLeft(Node<T> n) { this.l = n; }
        public void setRight(Node<T> n) { this.r = n; }
        public void setParent(Node<T> n) { this.p = n; }
        
        public T getData() { return data; }
        public void setData(T x) { this.data = x; }
    }
}
