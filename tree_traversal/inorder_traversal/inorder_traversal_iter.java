import java.util.List;
import java.util.Stack;
import java.util.Arrays;
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
        
        InorderTraversal.inorderTraversalIter(rightThree);
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
      * 1. Traverse left subtree inorder
      * 2. Visit root
      * 3. Traverse right subtree inorder
      */
     public static void inorderTraversalIter(Node n) {
         Node root = getRoot(n);
         
         Stack<Node> work = new Stack();
         work.push(root);
         
         Stack<Node> inorder = new Stack();
         
         while (null != work && !work.empty()) {
             Node curr = work.pop();
             
             /* sequence the output */
             inorder.push(curr);
             
             /* sequence the discovery */
             if (null != curr)
                 if (null != curr.left)  work.push(curr.left);
                 if (null != curr.right) work.push(curr.right);
         }
         
         while (null != inorder && !inorder.empty())
            System.out.println(inorder.pop());
     }
}
