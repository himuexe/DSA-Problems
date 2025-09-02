# Flatten Binary Tree to Linked List

**Source:** Kunal | **Topic:** Binary Tree, LinkedList | **Difficulty:** Medium

---

## Problem Statement
Flatten a binary tree into a linked list in-place, using the TreeNode class where the right child points to the next node and the left child is null, following pre-order traversal order.

## Intuition/Approach
Use reverse pre-order traversal (right → left → root) to build the list by connecting each node to the previously processed node. A global prev pointer tracks the last processed node.

## Key Observations
- Reverse pre-order (right → left → root) matches pre-order when building forward links.
- Setting left to null and right to prev creates the linked list.
- In-place operation modifies the tree structure directly.
- Recursion stack depth is proportional to tree height.

## Algorithm Steps
1. If root is null, return.
2. Recursively flatten right subtree.
3. Recursively flatten left subtree.
4. Set root.right = prev, root.left = null.
5. Update prev = root.

## Complexity Analysis
- **Time Complexity:** O(n) - Visit each node once.
- **Space Complexity:** O(h) - Recursion stack, h is tree height.
- **Justification:** Single traversal with recursive stack bounded by height.

## Edge Cases Considered
- [x] Empty tree.
- [x] Single node.
- [x] Tree with only left children.
- [x] Tree with only right children.
- [x] Complete binary tree.
- [x] Unbalanced tree.

## Solution Code
```java
public class Solution {
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}
```

## Alternative Approaches
- **Iterative with Stack:** Simulate recursion (O(h) space).
- **Morris Traversal:** Threaded tree approach (O(1) space).
- **Pre-order List:** Store nodes in list, rebuild (O(n) space).

## Personal Notes
- Reverse pre-order traversal elegantly solves the ordering problem.
- The prev pointer simplifies in-place construction.
- This problem bridges tree and linked list concepts effectively.

---
**Tags:** #binary_tree #linkedlist #preorder #recursion #medium