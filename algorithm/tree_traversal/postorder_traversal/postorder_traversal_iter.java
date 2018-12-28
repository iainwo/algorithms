import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Arrays;
import java.lang.IllegalArgumentException;
public class PostorderTraversal{

     public static void main(String[] args){
        System.out.println("Beginning PreorderTraversal.");
        
        Node leftTwo = new Node("4");
        Node rightTwo = new Node("5");
        Node leftOne = new Node(leftTwo, rightTwo, "2");
        
        Node leftThree = new Node("6");
        Node rightThree = new Node("7");
        Node rightOne = new Node(leftThree, rightThree, "3");
        
        Node parent = new Node(leftOne, rightOne, "1");
        
        PostorderTraversal.postorderTraversalIter(rightThree);
     }
     
     static class Node {
         Node parent = null;
         Node left = null;
         Node right = null;
        
         String val = "";
         
         private Node() {/* Do nothing. */}
         public Node(String val) { 
             this();
             this.val = val;
         }
         public Node(Node left, Node right, String val) {
             this(val);
             this.left = left;
             this.left.parent = this;
             this.right = right;
             this.right.parent = this;
         }
         @Override public String toString() { 
             return "Visited [" + val + "]."; 
         }
     }
     /**
      * Get the root of the tree
      * @param n an arbitrary Node in the tree
      * @return retNode the root node of the tree
      */
     private static Node getRoot(Node n) {
         Node retNode = n;
         for (; null != retNode.parent; retNode = retNode.parent);
         return retNode;
     }
     
    /**
      * Algorithm,
      * 1. Traverse left subtree postorder
      * 2. Traverse right subtree postorder
      * 3. Visit root
      * 
      * NOTE: does not run preorder from Root!
      */
     public static void postorderTraversalIter(Node n) {
        Node root = getRoot(n);
        root = n;
        
        Deque<Node> work = new LinkedList<>();
        work.push(n);
        
        Deque<Node> postorder = new LinkedList<>();
        
        Set<Node> visited = new HashSet<>();
        
        while (null != work && !work.isEmpty()) {
            Node curr = work.removeFirst();
            
            if (null != curr) {
                visited.add(curr);
                
                /* The parent node can be placed before after previous orderings
                 * because the algo. is guaranteed to,
                 * 1) expand & process subtrees first
                 * 2) never visit the same node twice.
                 *
                 * this works because the postorder requires the subtrees to
                 * be ordered first, and since the subtrees are processed first the
                 * nodes are therefor guaranteed to be added to the ordering before the parent
                 * node is added. Since subtrees are added before parents and parents
                 * are ordered after the subtrees; as the program iterates
                 * up to the root of the tree, it will sustain the
                 * subtree-before-parent invariant
                 */
                if (null != curr.left && visited.contains(curr.left)) {
                    postorder.addFirst(curr);
                } else if (null != curr.right && visited.contains(curr.right)) {
                    postorder.addFirst(curr);
                } else {
                    postorder.addLast(curr);
                }
                
                /* Subtree expansion */
                if (null != curr.parent && !visited.contains(curr.parent))
                    work.addFirst(curr.parent);
                if (null != curr.right && !visited.contains(curr.right))
                    work.addFirst(curr.right);
                if (null != curr.left && !visited.contains(curr.left))
                    work.addFirst(curr.left);
            }
        }
        
        while (null != postorder && !postorder.isEmpty())
            System.out.println(postorder.removeLast());
     }
}
