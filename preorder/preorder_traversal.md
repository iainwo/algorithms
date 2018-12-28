# Preorder Traversal
Notes from - https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/

- Like reading wikipedia from broad to granular concepts, respects hierarchy
- "top-to-bottom" w- hierarchy
- Node order: root, left, right

## Algorithm
1. Visit the Root
2. Traverse the left subtree "pre-order"
3. Travers the right subtree "pre-order"

## Notes
- useful for copying a tree
- useful for getting prefix notation / polish notation (i.e someOp someA someB)
