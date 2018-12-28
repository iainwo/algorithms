import java.util.function.Predicate;


/**
 * Technique learned from this blog - http://mishadoff.com/blog/dfs-on-binary-tree-array/
 * *** However - I fixed his Algo's bug ;-) and made it work.
 */
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
        
        Predicate<String> isSeven = s -> "7".equals(s);
        Predicate<String> isFour = s -> "4".equals(s);
        MyTree t = new MyTree();
        for (int i=0; i<7; i++) {
            t.addAt(i, i + "");
        }
        t.print();
        System.out.print("\n");
        System.out.println("BFS result for 7 [" + t.bfs(isSeven) + "]");
        System.out.println("BFS result for 4 [" + t.bfs(isFour) + "]");
        System.out.println("DFS result for 7 [" + t.dfs(isSeven) + "]");
        System.out.println("DFS result for 4 [" + t.dfs(isFour) + "]");
     }
     
     static class MyTree {
         private String[] tree = null;
         int length = -1;
         
         public int bfs(Predicate<String> p) {
             for (int i = 0; i < length; i++)
                if (null != tree && null != tree[i] && p.test(tree[i]))
                    return i;
             return -1;
         }
         public int dfs(Predicate<String> p) {
             int i = 0;
             int leafIdx = 0;
             while (i >= 0 && i < length) {
                 System.out.println("Check idx [" + i + "].");

                 if (null != tree && null != tree[i] && p.test(tree[i]))
                    return i;
                    
                 if (i < length/2) {
                    i = 2*i + 1; 
                 } else {
                    int binPower = 1;
                    
                    /* The Parent Node differs with this height from the
                     * current child node (inorder traversal):
                     * 
                     * JumpHeight = JumpHeight(-1) + Height + JumpHeight(-1)
                     * 1
                     * 1 2 1
                     * 1 2 1 3 1 2 1
                     *
                     * - index value is height
                     * - occurring at an interval of 2**height
                     * - starting at height - 1
                     * => from this a function for calculating the height
                     *    difference can be formed - a piecemeal function.
                     *
                     *    the components of these piecemeal functions are
                     *    bi-jective and project all integers from the image
                     *    in a way that,
                     *    - transform non-intersecting vector-spaces
                     *    - the collection of piecemeal functions span the
                     *      entirety of the domain of Integers.
                     *
                     * That piecemeal function can be manipulated in to a form
                     * which expresses the height calculation as a linear
                     * equation. That linear equation can be used to test whether
                     * the leaf index is an Integer in the span of the piecemeal
                     * sub-function. 
                     */
                    for(;;) {
                        int interval = 2 * binPower;
                        int offset = binPower - 1;

                        // Calc. parent node index
                        // BEWARE: this will convert -1/2 -> 0. Which fails on the return to root.
                        i = (i - 1)/2; 
                        
                        // hit root again, tree fully explored
                        // make sure to exclude the case when we are on idx [0].
                        if (0 == i && length / 2 == leafIdx)
                            return -1;
                        // linear equation to test membership;
                        // 0 vector = [interval]x - [offset]
                        // f(x) = 2**x = interval(x1)
                        // -> due to the number of child nodes per node
                        if (0 == (leafIdx % interval) - offset) {
                            i = 2*i + 2;
                            break;
                        }
                        binPower = interval;
                    }
                    
                    // Keep track of the leaf the algo has hit
                    leafIdx++;
                 }
             }
             return -1;
         }
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
