package Week_02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 *
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/2
 */
public class BinaryTreeInorderTraversal {

    private List<Integer> list = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        // Clarification: 普通二叉树，返回中序遍历
        // 方案1: 递归

        // terminator
        if (root == null) {
            return list;
        }

        // process current layer
        inorderTraversal(root.left);

        // drill to next layer
        list.add(root.val);

        // clean temporary variables
        inorderTraversal(root.right);

        return list;
    }

    public List<Integer> inorderTraversalTwo(TreeNode root) {
        // 方案2: 迭代 + 栈辅助
        // 方案3: 莫里斯遍历 TODO
        LinkedList<TreeNode> stack = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            // 将左节点放入栈中，直到最左节点
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            result.add(curr.val);

            curr = curr.right;
        }

        return result;
    }
}
