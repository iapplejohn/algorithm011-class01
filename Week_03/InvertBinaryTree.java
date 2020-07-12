package Week_03;

import java.util.LinkedList;
import java.util.List;

/**
 * 226. 翻转二叉树
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/11
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        // Clarification: 翻转左右子树
        // 方案1：递归
        // 交换左右子树
        // 时间复杂度：O(n)
        // 空间复杂度: O(n)

        // terminator
        if (root == null) {
            return null;
        }

        // process current
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // drill down
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public TreeNode invertTreeOne(TreeNode root) {
        // Clarification: 翻转左右子树
        // 方案1：递归
        // 交换左右子树
        // 时间复杂度：O(n)
        // 空间复杂度: O(n)

        // terminator
        if (root == null) {
            return null;
        }

        // process current

        // drill down
        TreeNode left = invertTreeOne(root.left);
        TreeNode right = invertTreeOne(root.right);

        // reverse current
        root.left = right;
        root.right = left;

        return root;
    }

    public TreeNode invertTreeTwoDFS(TreeNode root) {
        // 方案2：迭代，深度优先，使用栈辅助
        // 时间复杂度：O(n)
        // 空间复杂度: O(n)

        if (root == null) {
            return null;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return root;
    }

    public TreeNode invertTreeTwoBFS(TreeNode root) {
        // 方案2：迭代，广度优先，使用队列辅助
        // 时间复杂度：O(n)
        // 空间复杂度: O(n)

        if (root == null) {
            return null;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offer(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.poll();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return root;
    }
}
