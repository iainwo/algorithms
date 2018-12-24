import java.util.Queue;
import java.util.LinkedList;
import java.util.function.Consumer;

public class BinaryTreeOps<T> {
    public static void main(String[] args) {
        BinaryTreeOps<String> btOps = new BinaryTreeOps<>();
        btOps.insert("root");
        btOps.insert("one");
        btOps.insert("two");
        btOps.insert("three");
        btOps.insert("four");
        btOps.insert("five");
        btOps.insert("six");
        btOps.insert("seven");
        btOps.insert("eight");
        btOps.insert("nine");
        btOps.insert("ten");
        
        btOps.dfsPrint();
        btOps.bfsPrint();
        
        btOps.remove("ten");
        btOps.bfsPrint();
    }
    
    private Node root;
    
    public T remove(T key) {
        T retVal = null;
        if (null != getRoot()) {
            Queue<Node> q = new LinkedList<>();
            q.add(getRoot());
            retVal = (T) remove(
                key,
                q, 
                null,
                null);
        }
        return retVal;
    }
    private boolean isSingleNodeTree() {
        return null != getRoot()
                && null == getRoot().getLeft()
                && null == getRoot().getRight();
    }
    /**
     * Recurse through the tree in-level order and delete the given key.
     * Returns null if there is no match.
     * @param key they data key to delete
     * @param level the nodes of the current level
     * @param match the deepest key-matching node
     * @return the value of the matching key which was deleted
     */
    private T remove(T key, Queue<Node> level, Node<T> match, Node<T> parent) {
        if (isSingleNodeTree() && getRoot().isMatch(key))
            setRoot(null);
        else if (null != level && !level.isEmpty()) {
            
            Queue<Node> next = new LinkedList<>();
            Node<T> last = parent;

            while (!level.isEmpty()) {
                Node<T> curr = level.remove();
                if (null != curr) {
                    if (curr.isMatch(key))
                        match = curr;
                    
                    Node<T> l = curr.getLeft();
                    Node<T> r = curr.getRight();
                    
                    if (null != l) {
                        next.add(l);
                    }
                    if (null != r) {
                        next.add(r);
                    }
                    
                    last = curr;
                    if (null != l || null != r) parent = curr;
                }
            }

            if (next.isEmpty()
                && null != match
                && null != last
                && null != parent) {
                match.setData(last.getData());
                parent.orphan(last);
                
                return last.getData();
            }
            
            return remove(key, next, match, parent);
        }
        return null;
    }
    public void dfsPrint() {
        Consumer<Node> printNode = n ->
            System.out.println(""
                + "Node [" + n.getData() + "].");
            
            System.out.println("DFS has this data:");
            dfs(getRoot(), printNode);
            System.out.println("---");
    }
    public void bfsPrint() {
        Consumer<Node> printNode = n ->
            System.out.print(""
                + "Node [" + n.getData() + "].\t");
            
            System.out.println("BFS has this data:");
            bfs(getRoot(), printNode);
            System.out.println("---");
    }
    
    private void bfs(Node n, Consumer<Node> func) {
        if (null != n) {
            Queue<Node> level = new LinkedList<>();
            level.add(n);
            
            bfsHelper(level, func);
        }
    }
    private void bfsHelper(Queue<Node> q, Consumer<Node> func) {
        if (null != q && !q.isEmpty() && null != func) {
            Queue<Node> next = new LinkedList<>();
            
            while (!q.isEmpty()) {
                Node<T> curr = q.remove();
                if (null != curr) {
                    func.accept(curr);
                    if (null != curr.getLeft()) next.add(curr.getLeft());
                    if (null != curr.getRight()) next.add(curr.getRight());
                }
            }
            System.out.print("\n");
            bfsHelper(next, func);
        }
    }
    private void dfs(Node n, Consumer<Node> func) {
        if (null != n) {
            func.accept(n);
            if (null != n.getLeft()) dfs(n.getLeft(), func);
            if (null != n.getRight()) dfs(n.getRight(), func);
        }
    }
    public boolean insert(String s) {
        boolean retBool = false;
        
        Node<String> n = new Node<>(s);
        if (null == getRoot()) {
            setRoot(n);
            retBool = true;
        } else {
            Queue<Node> q = new LinkedList<>();
            q.add(getRoot());
            
            while (!q.isEmpty()) {
                Node c = q.remove();
                if (null != c) {
                    if (null == c.getLeft()) {
                        c.setLeft(n);
                        retBool = true;
                        break;
                    } else {
                        q.add(c.getLeft());
                    }
                    
                    if (null == c.getRight()) {
                        c.setRight(n);
                        retBool = true;
                        break;
                    } else {
                        q.add(c.getRight());
                    }
                } 
            }
        }
        
        return retBool;
    }
    
    public void setRoot(Node n) { this.root = n; }
    public Node getRoot() { return root; }
    
    static class Node<T> {
        private Node left, right;
        private T data;
        
        public Node() {
            left = right = null;
            data = null;
        }
        public Node(T data) {
            this();
            this.data = data;
        }
        
        public void orphan(Node n) {
            if (null != getLeft() && getLeft() == n) 
                setLeft(null);
            if (null != getRight() && getRight() == n) 
                setRight(null);
        }
        public boolean isMatch(T key) {
            boolean retBool = false;
            
            if ( null != getData() && getData().equals(key))
                retBool = true;
                
            return retBool;
        }
        
        public Node getLeft() { return this.left; }
        public void setLeft(Node n) { this.left = n; }
        public Node getRight() { return this.right; }
        public void setRight(Node n) { this.right = n; }
        public T getData() { return data; }
        public void setData(T data) { this.data = data; }
    }
}
