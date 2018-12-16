import java.util.Set;
import java.util.HashSet;
import java.lang.IllegalArgumentException;
public class InorderTraversal{

     public static void main(String []args){
        System.out.println("Beginning InorderTraversal.");
        
        Node leftTwo = new Node("4");
        Node rightTwo = new Node("5");
        Node leftOne = new Node(leftTwo, rightTwo, "2");
        
        Node rightOne = new Node("3");
        
        Node parent = new Node(leftOne, rightOne, "1");
        
        InorderTraversal.inorderTraversalV1(leftTwo, new HashSet<Node>());
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
      * Algorithm,
      * 1. Traverse left subtree inorder
      * 2. Visit root
      * 3. Traverse right subtree inorder
      */
     public static void inorderTraversalV1(Node n, Set<Node> visited) {
         if (null == visited) throw new IllegalArgumentException("visited ds empty");
         
         boolean isVisited = false;
         isVisited = visited.add(n);
         
         if (null != n) {
            if (null != n.left && !visited.contains(n.left)) inorderTraversalV1(n.left, visited);
            System.out.println(n);
            if (null != n.right && !visited.contains(n.right)) inorderTraversalV1(n.right, visited);
            
            if (null != n.parent && !visited.contains(n.parent)) inorderTraversalV1(n.parent, visited);
         }    
    }
}
