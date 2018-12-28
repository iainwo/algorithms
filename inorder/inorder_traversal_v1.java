import java.util.Set;
import java.util.HashSet;
import java.lang.IllegalArgumentException;
public class InorderTraversal{

     public static void main(String []args){
        System.out.println("Beginning InorderTraversal.");
        
        Node leftTwo = new Node("4");
        Node rightTwo = new Node("5");
        Node leftOne = new Node(leftTwo, rightTwo, "2");
        
        Node leftThree = new Node("6");
        Node rightThree = new Node("7");
        Node rightOne = new Node(leftThree, rightThree, "3");
        
        Node parent = new Node(leftOne, rightOne, "1");
        
        InorderTraversal.inorderTraversalV1(rightThree, new HashSet<Node>());
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
     public static void inorderTraversalV1(Node n, Set<Node> visited) {
         Node root = getRoot(n);
         InorderTraversal.inorderTraversalV1Helper(root, visited);
     }
     /**
      * Algorithm,
      * 1. Traverse left subtree inorder
      * 2. Visit root
      * 3. Traverse right subtree inorder
      */
     private static void inorderTraversalV1Helper(Node n, Set<Node> visited) {
         if (null == visited) throw new IllegalArgumentException("visited ds empty");
         
         boolean isVisited = false;
         isVisited = visited.add(n);
         
         if (null != n) {
            if (null != n.left && !visited.contains(n.left)) 
                inorderTraversalV1Helper(n.left, visited);
            
            System.out.println(n);
            
            if (null != n.right && !visited.contains(n.right)) 
                inorderTraversalV1Helper(n.right, visited);
            
            if (null != n.parent && !visited.contains(n.parent))
                inorderTraversalV1Helper(n.parent, visited);
         }    
    }
}
