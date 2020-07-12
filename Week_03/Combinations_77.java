package Week_03;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/11
 */
public class Combinations_77 {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        // Clarification: 所有可能的k个数的组合
        // 递归 - 回溯

        if (n <= 0 || k <= 0 || n < k) {
            return res;
        }

        recursiveCombine(n, k, 1, new LinkedList<>());
        return res;
    }

    private void recursiveCombine(int n, int k, int begin, LinkedList<Integer> stack) {
        // terminator
        if (stack.size() == k) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = begin; i <= n; i++) {
            // process current
            stack.push(i);

            // drill down
            recursiveCombine(n, k, i + 1, stack);

            // reverse current
            stack.pop();
        }
    }

}
