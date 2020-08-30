package Week_03;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/29
 */
public class Subsets_78 {

    public List<List<Integer>> subsets(int[] nums) {
        // Clarification: 所有的子集，包括空和所有元素
        // 方案1: 分治，对于某一个位置，可以取或者不取

        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null) {
            return ans;
        }

        List<Integer> list = new ArrayList<>(nums.length);
        dfs(nums, list, 0, ans);
        return ans;
    }

    private void dfs(int[] nums, List<Integer> list, int index, List<List<Integer>> ans) {
        // terminator
        if (index == nums.length - 1) {
            // process and generate the final result
            ans.add(new ArrayList<>(list));
            return;
        }

        // conquer sub-problems 不选当前元素
        dfs(nums, list, index + 1, ans);

        list.add(nums[index]);

        // 选当前元素
        dfs(nums, list, index + 1, ans);

        // revert the current level states
        list.remove(list.size() - 1);
    }

    public List<List<Integer>> subsetsTwo(int[] nums) {
        // 方案2: 迭代
        // 往输出列表添加空列表，然后对于每个元素，都往已有的输出数组上追加

        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        List<Integer> emptyList = new ArrayList<>(0);
        ans.add(emptyList);

        for (int num : nums) {
            // 每换一个 num，子循环中都需要固定 size
            int size = ans.size();
            for (int i = 0; i < size; i++) {
                List<Integer> list = new ArrayList<>(ans.get(i));
                list.add(num);
                ans.add(list);
            }
        }

        return ans;
    }
}
