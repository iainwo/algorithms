
public class LevelorderTraversal{

     public static void main(String[] args){
        System.out.println("Beginning ArrayCompleteTree.");
        
        Node leftTwo = new Node("4");
        Node rightTwo = new Node("5");
        Node leftOne = new Node(leftTwo, rightTwo, "2");
        
        Node leftThree = new Node("6");
        Node rightThree = new Node("7");
        Node rightOne = new Node(leftThree, rightThree, "3");
        
        Node parent = new Node(leftOne, rightOne, "1");
        
        MyTree t = new MyTree();
        for (int i=0; i<7; i++) {
            t.addAt(i, i + "");
        }
        t.print();
     }
     
     static class MyTree {
         private String[] tree = null;
         int length = -1;
         
         private int getImplicitLength(int idx) {
             int maxLen = 1;
             int tgtLen = 1 + idx;
             
             int height = 0;
             for(; maxLen < tgtLen; maxLen=1+maxLen*2);
             
             return maxLen;
         }
         private void grow(int idx) {
             int newLen = getImplicitLength(idx);
             String[] tmp = new String[newLen];
             
             if (0 < length)
                System.arraycopy(tree, 0, tmp, 0, length);
             
             tree = tmp;
             length = newLen;
         }
         private void add(int idx, String val) {
             if (getImplicitLength(idx) > length) {
                 grow(idx);
             }
             tree[idx] = val;
         }
         public void addAt(int idx, String val) {
             add(idx, val);
         }
         public void addLeft(int idx, String val) {
             add(getLeftIdx(idx), val);
         }
         public void addRight(int idx, String val) {
             add(getRightIdx(idx), val);
         }
         private int getLeftIdx(int idx) {
             return 2*idx+1;
         }
         private int getRightIdx(int idx) {
             return 2*idx+2;
         }
         
         public void print() {
             System.out.println("Tree of size [" + length + "].");
             System.out.println("Tree of real size [" + tree.length + "].");
             System.out.print("Contents: ");
             for (int i = 0; i < length; i++) {
                 System.out.print (tree[i] + " ");
             }
         }
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
}
