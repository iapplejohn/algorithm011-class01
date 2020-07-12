package Week_03;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/9
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal_105 {

    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Clarification: 给出前序和中序遍历的结果，返回二叉树，没有重复元素
        // 特征: 前序的首节点是根节点，在中序遍历中定位到根节点，我们就可以分别知道左子树和右子树中的节点数目
        // 方案1: 递归
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)

        int preLen = preorder.length;
        int inLen = inorder.length;
        if (preLen != inLen) {
            throw new IllegalArgumentException("The lengths are different");
        }

        // 空间换时间，通过映射存储：中序遍历 元素值-下标
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return recursiveBuildTree(preorder, 0, preLen - 1, inorder, 0, inLen - 1);
    }

    public TreeNode recursiveBuildTree(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        // terminator
        if (preLeft > preRight) {
            return null;
        }

        // process current
        // 前序遍历，第一个节点是根节点
        int rootVal = preorder[preLeft];
        // 从中序遍历中，根据根节点的值找到根节点位置（因为数字不重复，所以唯一确定）
        int inRootIndex = map.get(rootVal);
        // 左子树的节点个数：中序遍历的根节点位置 - 左子树的开始位置
        int sizeLeftSub = inRootIndex - inLeft;

        // 构建根节点
        TreeNode root = new TreeNode(rootVal);

        // drill down
        // 构造左子树，赋给当前根节点的left引用
        root.left = recursiveBuildTree(preorder, preLeft + 1, preLeft + sizeLeftSub, inorder, inLeft, inRootIndex - 1);
        // 构造右子树，赋给当前根节点的right引用
        root.right = recursiveBuildTree(preorder, preLeft + sizeLeftSub + 1, preRight, inorder, inRootIndex + 1, inRight);

        // reverse the current

        return root;
    }

    public TreeNode buildTreeTwo(int[] preorder, int[] inorder) {
        // 方案2: 迭代
        return null;
    }
}
