package Week_03;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/12
 */
public class Permutations_46 {

    public List<List<Integer>> permute(int[] nums) {
        // Clarification: 没有重复数字的数组，返回全排列
        // 回溯
        // 使用布尔数组记录数字是否被使用
        // 终止条件：递归深度=数组长度后，得到的结果是一个排列
        // 记录路径的变量，在整个过程中共用，所以需要回溯（撤销本次选择），方便其他排列使用这个数字

        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }

        // 记录数字是否被使用
        boolean[] used = new boolean[len];

        // 路径，也就是一个排列
        List<Integer> path = new ArrayList<>();
        dfs(nums, len, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            // path被后面的排列复用，所以需要拷贝一份
            res.add(new ArrayList<>(path));
            return;
        }

        // 全排列: 第1位数字(1...n)，第2位数字(排除第1位元素)...第n位数字
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;

                dfs(nums, len, depth + 1, used, path, res);

                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }

}
