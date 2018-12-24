import java.util.function.Predicate;
import java.util.Queue;
import java.util.ArrayDeque;
public class BinaryTree<T> {
    public static void main(String[] args) {
        BinaryTree<String> t = new BinaryTree<>();
        
        Node<String> r = new Node<>("root");
        Node<String> one = new Node<>("one");
        Node<String> two = new Node<>("two");
        Node<String> three = new Node<>("three");
        Node<String> four = new Node<>("four");
        Node<String> five = new Node<>("five");
        
        r.setLeft(one);
        r.setRight(two);
        one.setLeft(three);
        one.setRight(four);
        two.setLeft(five);
        
        t.setRoot(r);
        
        Predicate<String> isFive = s -> "five".equals(s);
        String result = t.bfs(isFive);
        
        System.out.println("BinaryTree BFS found Node [" + result + "].");
    }
    
    Node root;
    
    public Node setRoot(Node n) {
        root = n;
        return root;
    }
    
    public T bfs(Predicate<T> p) {
        if (null != getRoot()) {
            Queue<Node> q = new ArrayDeque<>();
            q.add(getRoot());
            
            while (null != q && !q.isEmpty()) {
                Node c = q.remove();
                if (null != c && p.test((T)c.getData())) return (T)c.getData();
                if (null != c && null != c.getLeft()) q.add(c.getLeft());
                if (null != c && null != c.getRight()) q.add(c.getRight());
            }
        }
        
        return null;
    }
    
    public Node getRoot() { return this.root; }
    static class Node<T> {
        T data;
        Node left, right;
        
        public Node() {
            left = right = null;
            data = null;
        }
        public Node(T t) {
            this();
            this.data = t;
        }
        
        public T getData() { return this.data; }
        public void setLeft(Node t) { this.left = t; }
        public void setRight(Node t) { this.right = t; }
        public Node getLeft() { return this.left; }
        public Node getRight() { return this.right; }
        
        @Override
        public String toString() {
            return ""
                + "Node -"
                + " data [" + getData() + "]";
        }
    }
}
