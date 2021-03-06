import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.lang.IllegalArgumentException;
public class LevelorderTraversal{

     public static void main(String[] args){
        System.out.println("Beginning LevelorderTraversal.");
        
        Node leftTwo = new Node("4");
        Node rightTwo = new Node("5");
        Node leftOne = new Node(leftTwo, rightTwo, "2");
        
        Node leftThree = new Node("6");
        Node rightThree = new Node("7");
        Node rightOne = new Node(leftThree, rightThree, "3");
        
        Node parent = new Node(leftOne, rightOne, "1");
        
        LevelorderTraversal.levelorderTraversalIter(rightThree);
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
     
     private static Queue getNextLevel(Queue<Node> candidates) {
         Queue<Node> retLevel = new LinkedList<>();
         
         for (Node parent : candidates) {
             if (null != parent) {
                 if (null != parent.left)
                    retLevel.add(parent.left);
                if (null != parent.right)
                    retLevel.add(parent.right);
             }
         }
         
         return retLevel;
     }
    /**
      * Algorithm,
      * 1. Traverse left subtree postorder
      * 2. Traverse right subtree postorder
      * 3. Visit root
      * 
      * NOTE: does not run preorder from Root!
      */
     public static void levelorderTraversalIter(Node n) {
        Node root = getRoot(n);
        
        Queue<Node> level = new LinkedList<>();
        level.add(root);
        
        while (null != level && !level.isEmpty()) {
            for (Node i : level) System.out.println(i);
            level = getNextLevel(level);
        }
        
     }
}
