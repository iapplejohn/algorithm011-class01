package Week_03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 236. 二叉树的最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *
 *
 *  
 *
 * 示例 1:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *  
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/12
 */
public class LowestCommonAncestorOfABinaryTree_236 {

    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Clarification: 给定一个二叉树（节点值唯一），以及树中的两个不同节点，找出他们的最近公共祖先（可以为节点本身）
        // 递归 - 自顶向下
        // p与q三种可能性: 都在左子树, 都在右子树，一个左子树一个右子树
        // root递归，判断节点是否p或q
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)

        recursive(root, p, q);
        return ans;
    }

    private boolean recursive(TreeNode node, TreeNode p, TreeNode q) {
        // terminator
        if (node == null) {
            return false;
        }

        // process current

        // drill down
        // 从左子树和右子树查找，判断是否包含p或q
        boolean lSon = recursive(node.left, p, q);
        boolean rSon = recursive(node.right, p, q);

        // 最近公共祖先: 左右子树包含p和q，或者当前节点是p或q，且左右子树包含q或p
        if (lSon && rSon || node.val == p.val || node.val == q.val && (lSon || rSon)) {
            ans = node;
        }

        // reverse current

        return lSon || rSon || (node.val == p.val || node.val == q.val);
    }

    private Map<Integer, TreeNode> allParentMap = new HashMap<>();
    private Set<Integer> parentSet = new HashSet<>();

    public TreeNode lowestCommonAncestorTwo(TreeNode root, TreeNode p, TreeNode q) {
        // Clarification: 给定一个二叉树（节点值唯一），以及树中的两个不同节点，找出他们的最近公共祖先（可以为节点本身）
        // 自底向上
        // 分别从p和q出发，循环找到自己的父节点，记录这些父节点，最早出现的交集的就是最近公共祖先
        // 辅助：通过哈希表记录每个节点的父节点
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)

        // 遍历所有节点，记录每个节点的父节点
        dfs(root);

        // 从p节点往上找父节点，并记录
        while (p != null) {
            // 当前节点可能就是公共祖先
            parentSet.add(p.val);
            p = allParentMap.get(p.val);
        }

        // 从q节点往上找父节点，判断是否在p的父节点集合中
        while (q != null) {
            // 当前节点可能就是公共祖先
            if (parentSet.contains(q.val)) {
                return q;
            }
            q = allParentMap.get(q.val);
        }

        return null;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            allParentMap.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            allParentMap.put(root.right.val, root);
            dfs(root.right);
        }
    }

}
