import java.util.Queue;
import java.util.LinkedList;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.function.Predicate;

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
        
        Predicate<Node> p = n -> "6".equals(n.val) || "1".equals(n.val);
        Set<Node> matches = LevelorderTraversal.levelorderSearchRecurs(
                                rightThree, 
                                p);
        matches.stream().forEach(System.out::println);
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

     public static Set<Node> levelorderSearchRecurs(Node n, Predicate<Node> p) {
        
        Queue<Node> root = new LinkedList<>();
        root.add(getRoot(n));
        
        return LevelorderTraversal.recursHelper(root, p);
     }
     
     private static Set<Node> getMatches(Queue<Node> q, Predicate<Node> p) {
         return q.stream().filter(p).collect(Collectors.toSet());
     }
     private static Set<Node> recursHelper(Queue<Node> children, Predicate<Node> p) {
         Set<Node> retSet = null;
         
         if (null != children && !children.isEmpty()) {
            Set<Node> currLevel = LevelorderTraversal.getMatches(children, p);
            Set<Node> subLevel  = recursHelper(getNextLevel(children), p); 
            
            Set<Node> aggMatches = new HashSet<>();
            if (null != currLevel)  aggMatches.addAll(currLevel);
            if (null != subLevel)   aggMatches.addAll(subLevel);
            retSet = aggMatches;
         }
         
         return retSet;
     }
}
