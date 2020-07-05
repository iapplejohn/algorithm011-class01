package Week_02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 590. N叉树的后序遍历
 *
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 *
 * 例如，给定一个 3叉树 :
 *
 *  
 *
 *
 *
 *  
 *
 * 返回其后序遍历: [5,6,3,2,4,1].
 *
 *  
 *
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/3
 */
public class NAryTreePostorderTraversal {

    private List<Integer> list = new ArrayList<>();

    public List<Integer> postorder(Node root) {
        // Clarification: N 叉树，返回后序遍历的节点值
        // 方案1: 递归
        // terminator
        if (root == null) {
            return list;
        }

        // process current layer

        // drill to next layer

        if (root.children != null) {
            for (Node child : root.children) {
                postorder(child);
            }
        }
        list.add(root.val);

        // clear cache
        return list;
    }

    public List<Integer> postorderTwo(Node root) {
        // 方案2: 迭代
        // 逆向遍历: 先根，然后右子树、左子树，
        // 然后将结果逆向输出

        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<Node> stack = new LinkedList<>();
        if (root == null) {
            return result;
        }

        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();

            // 根
            // addFirst: 逆向输出，最先输出的在最后
            result.addFirst(node.val);
            // 顺序push，保证下一次遍历子节点的时候，是逆序的
            for (Node child : node.children) {
                if (child != null) {
                    stack.push(child);
                }
            }
        }

        return result;
    }
}
