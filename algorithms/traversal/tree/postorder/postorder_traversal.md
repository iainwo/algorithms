# Postorder Traversal
Notes from - https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/

- Like reading wikipedia from granular to broader concepts, respects hierarchy
- "bottom-to-top" w- hierarchy
- Node order: left, right, root

## Algorithm
1. Traverse the left subtree "postorder"
2. Travers the right subtree "postorder"
3. Visit the Root

## Notes
- used to create postfix expression / reverse Polish Notation
- used to for tree deletion. Could use other traversals + memory to do deletion but postorder give correct order for same time complexity.
