package Week_03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 47. 全排列 II
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/30
 */
public class PermutationsII_47 {

    private Set<String> set;

    public List<List<Integer>> permuteUnique(int[] nums) {
        // Clarification: 包含重复数字，返回的全排列不重复
        // 方案1: 回溯
        // 递归参数: 数组, 当前 level, 是否使用数组，当前 路径，结果集

        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;

        // 元素是否被使用
        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();
        set = new HashSet<>(len * 2);

        dfs(nums, 0, len, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int depth, int len, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        // terminator
        if (depth == len) {
            StringBuilder builder = new StringBuilder();
            for (Integer num : path) {
                builder.append(num).append('_');
            }
            String key = builder.substring(0, builder.length() - 1);
            if (!set.contains(key)) {
                res.add(new ArrayList<>(path));
                set.add(key);
            }
            return;
        }

        // process current logic
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            path.add(nums[i]);

            // drill down
            dfs(nums, depth + 1, len, used, path, res);

            // reverse the current states
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

}
