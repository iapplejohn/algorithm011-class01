package Week_02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 429. N叉树的层序遍历
 *
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 *
 * 例如，给定一个 3叉树 :
 *
 *  
 *
 *
 *
 *  
 *
 * 返回其层序遍历:
 *
 * [
 *      [1],
 *      [3,2,4],
 *      [5,6]
 * ]
 *  
 *
 * 说明:
 *
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/4
 */
public class NAryTreeLevelOrderTraversal {

    private List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> levelOrder(Node root) {
        // Clarification: N叉树，返回层序遍历的节点值
        // 方案1: 递归
        // 时间复杂度: O(n) n是节点的数量
        // 空间复杂度: 正常情况O(logn)，最差情况O(n)。运行时在堆栈上的空间。
        if (root == null) {
            return list;
        }

        levelOrderRecursion(root, 0);
        return list;
    }

    public void levelOrderRecursion(Node node, int level) {
        // terminator
        if (node == null) {
            return;
        }

        if (list.size() < level + 1) {
            list.add(new ArrayList<>());
        }

        // process current layer
        list.get(level).add(node.val);

        // drill to next layer
        if (node.children != null) {
            for (Node child : node.children) {
                levelOrderRecursion(child, level + 1);
            }
        }

        // clear cache
    }

    public List<List<Integer>> levelOrderTwo(Node root) {
        // 方案2: 队列 + 广度优先
        // 队列存储下一层的所有节点
        // 依次取出每个节点（通过size判断每层多少个节点），获取节点值，并将该节点的子节点添加到队列中
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                list.add(node.val);
                if (node.children != null) {
                    queue.addAll(node.children);
                }
            }

            result.add(list);
        }

        return result;
    }

    public List<List<Integer>> levelOrderThree(Node root) {
        // 方案3: 每层一个队列 + 广度优先
        // 队列存储每一层的所有节点
        // 依次取出每个节点，获取节点值，并将该节点的子节点添加到队列中
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        List<Node> previousLayer = Collections.singletonList(root);

        while (previousLayer.size() > 0) {
            List<Integer> previousVals = new ArrayList<>();
            List<Node> currentLayer= new ArrayList<>();
            for (Node node : previousLayer) {
                previousVals.add(node.val);
                if (node.children != null) {
                    currentLayer.addAll(node.children);
                }
            }
            result.add(previousVals);
            previousLayer = currentLayer;
        }

        return result;
    }
}
