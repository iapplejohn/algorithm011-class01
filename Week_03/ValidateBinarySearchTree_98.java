package Week_03;

import java.util.LinkedList;

/**
 * 98. 验证二叉搜索树
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/11
 */
public class ValidateBinarySearchTree_98 {

    public boolean isValidBST(TreeNode root) {
        // Clarification: 二叉树，判断是否二叉搜索树
        // 递归

        if (root == null) {
            return true;
        }

        return dfs(root, null, null);
    }

    private boolean dfs(TreeNode node, Integer lower, Integer upper) {
        // terminator
        if (node == null) {
            return true;
        }

        // process current
        int current = node.val;
        if (lower != null && current <= lower) {
            return false;
        }
        if (upper != null && current >= upper) {
            return false;
        }

        // drill down
        boolean valid = true;
        if (node.left != null) {
            // 既然是left节点，为什么上层的lower要传下去？因为node可能是右节点，所有子节点都要大于node父节点
            valid = dfs(node.left, lower, current);
        }

        if (node.right != null) {
            // 既然是right节点，为什么上层的upper要传下去？因为node可能是左节点，所有子节点都要小于node父节点
            valid = valid && dfs(node.right, current, upper);
        }

        // reverse current

        return valid;
    }

    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> lowers = new LinkedList<>();
    LinkedList<Integer> uppers = new LinkedList<>();

    public boolean isValidBST_2(TreeNode root) {
        // 方案2：迭代，
        // 通过栈来辅助，同时存限界值
        // 1. 存入根节点
        // 2. 然后从栈拿对象，判断当前节点是否满足要求，往栈push子节点
        // 3. 重复2
        update(root, null, null);

        while (!stack.isEmpty()) {
            TreeNode node = stack.poll();
            Integer lower = lowers.poll();
            Integer upper = uppers.poll();

            if (node == null) {
                continue;
            }

            Integer current = node.val;
            if (lower != null && current <= lower) {
                return false;
            }
            if (upper != null && current >= upper) {
                return false;
            }

            if (node.right != null) {
                update(node.right, current, upper);
            }
            if (node.left != null) {
                update(node.left, lower, current);
            }
        }

        return true;
    }

    private void update(TreeNode node, Integer lower, Integer upper) {
        stack.push(node);
        lowers.push(lower);
        uppers.push(upper);
    }
}
