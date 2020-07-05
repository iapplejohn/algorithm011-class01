package Week_02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 589. N叉树的前序遍历
 *
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 *
 * 例如，给定一个 3叉树 :
 *
 *  
 *
 *
 *
 *  
 *
 * 返回其前序遍历: [1,3,5,6,2,4]。
 *
 *  
 *
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/2
 */
public class NAryTreePreorderTraversal {

    private List<Integer> list = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        // Clarification: N叉树，无序，返回前序遍历的节点值
        // 方案1: 递归
        // 时间复杂度: O(M) - M为节点个数
        // 空间复杂度: O(M)

        // terminator
        if (root == null) {
            return list;
        }

        // process current layer
        list.add(root.val);

        // drill to next layer
        for (int i = 0; i < root.children.size(); i++) {
            preorder(root.children.get(i));
        }

        // clear the local cache

        return list;
    }

    public List<Integer> preorderTwo(Node root) {
        // 方案2: 迭代法 - 通过栈来辅助
        LinkedList<Node> stack = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // 将根节点放入栈中
        stack.push(root);

        // 循环，直到栈为空
        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (node != null) {
                result.add(node.val);

                // 逆向，从栈中pop出来就是正向的
                Collections.reverse(node.children);
                for (Node child : node.children) {
                    stack.push(child);
                }
            }
        }

        return result;
    }
}
