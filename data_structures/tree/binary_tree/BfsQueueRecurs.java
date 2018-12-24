import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Predicate;

public class GoodBinaryTree<T> {
    public static void main(String[] args) {
        GoodBinaryTree<String> tree = new GoodBinaryTree<>();
        
        for (int i=0; i<100; i++)
            tree.insert(Integer.toString(i));
        tree.print();
        
        Predicate<String> is64 = s -> "64".equals(s);
        Predicate<String> is0  = s -> "0".equals(s);
        Predicate<String> isBad = s -> "".equals(s);
        Predicate<String> is99  = s -> "99".equals(s);
        System.out.println("Found 64 [" + tree.bfs(is64) + "].");
        System.out.println("Found 0 [" + tree.bfs(is0) + "].");
        System.out.println("Found 'BAD' [" + tree.bfs(isBad) + "].");
        System.out.println("Found 99 [" + tree.bfs(is99) + "].");
    }
    
    Node root;
    
    public T bfs(Predicate<T> p) {
        if (null != root) {
            Queue<Node> level = new LinkedList<>();
            level.add(root);
            
            return (T)root.bfs(level, p);
        }
        return null;
    }   
    public void insert(String key) {
        Node<T> n = new Node(key);
        if (null == root)
            root = n;
        else
            bfsInsert(n);
    }
    public void print() {
        System.out.println("Tree:");
        if (null != root) {
            Queue<Node> level = new LinkedList<>();
            level.add(root);
           
            Queue<Node> next = new LinkedList<>();
            
            while (!level.isEmpty()) {
                Node<T> curr = level.remove();
                System.out.print(curr + "\t");
                if (null != curr.getLeft()) next.add(curr.getLeft());
                if (null != curr.getRight()) next.add(curr.getRight());
                
                if (level.isEmpty()) {
                    Queue<Node> tmp = level;
                    level = next;
                    next = tmp;
                    
                    System.out.println("");
                }
            }
        }
        
    }
    private void bfsInsert(Node n) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        bfsInsertHelper(q, n);
    }
    private void bfsInsertHelper(Queue<Node> level, Node<T> n) {
        if (null != level && !level.isEmpty()) {
            Node<T> curr = level.remove();
            
            Node<T> l = curr.getLeft();
            Node<T> r = curr.getRight();
            
            if (null == l) {
                curr.setLeft(n);
                n.setParent(curr);
                return;
            } else {
                level.add(l);
            }
            
            if (null == r) {
                curr.setRight(n);
                n.setParent(curr);
                return;
            } else {
                level.add(r);
            }
            
            bfsInsertHelper(level, n);
        }
    }
    
    static class Node<T> {
        private Node parent, left, right;
        T data;
        
        public Node() {
            parent = left = right = null;
            data = null;
        }
        public Node(T data) {
            this();
            this.data = data;
        }
        
        /**************************************************************************/
        // Recursive BFS with Queue
        /**************************************************************************/
        public T bfs(Queue<Node> level, Predicate<T> p) {
            
            if (null == level || level.isEmpty() || null == p) return null;
            
            Node<T> c = level.remove();
            if (null != c) {
                if (p.test(c.getData())) return c.getData();
                if (null != c.getLeft()) level.add(c.getLeft());
                if (null != c.getRight()) level.add(c.getRight());
            }
            
            return bfs(level, p);
        }
        /**************************************************************************/
        
        @Override
        public String toString() {
            return "Node [" + data + "].";
        }
        
        public Node getLeft() { return left; }
        public Node getRight() { return right; }
        public Node getParent() { return parent; }
        public void setLeft(Node<T> n) { this.left = n; }
        public void setRight(Node<T> n) { this.right = n; }
        public void setParent(Node<T> n) { this.parent = n; }
        
        public T getData() { return data; }
        public void setData(T data) { this.data = data; }
    }
    
}
