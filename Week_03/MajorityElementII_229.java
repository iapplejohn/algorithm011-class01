package Week_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 229. 求众数 II
 *
 * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 *
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: [3]
 * 示例 2:
 *
 * 输入: [1,1,1,3,3,2,2,2]
 * 输出: [1,2]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/30
 */
public class MajorityElementII_229 {

    public List<Integer> majorityElement(int[] nums) {
        // Clarification: 出现超过 n / 3 次的所有元素，空间复杂度为 O(1)
        // 方案1: 计数法 哈希表统计 空间复杂度为 O(n)，不符合要求
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)

        List<Integer> res = new ArrayList<>(2);

        int len = nums.length;
        int target = len / 3;
        Map<Integer, Integer> countMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            Integer count = countMap.computeIfAbsent(nums[i], k -> 1);
            if (++count > target && !res.contains(nums[i])) {
                res.add(nums[i]);
            }
            countMap.put(nums[i], count);
        }

        return res;
    }

    public List<Integer> majorityElementTwo(int[] nums) {
        // 方案2: 摩尔投票法
        // 题目隐含了一个条件，最多只能有两位候选人，所以使用两组变量来处理
        // 投票完成后，还需要计数，是否超过 n / 3, 因为可能只有一位，甚至没有
        // 时间复杂度: O(n)
        // 空间复杂度: O(1)

        List<Integer> res = new ArrayList<>(2);
        if (nums == null || nums.length == 0) {
            return res;
        }

        int candidate1 = nums[0], count1 = 0;
        int candidate2 = nums[0], count2 = 0;

        // 投票阶段: 相同则累加，不同则计数减1，不够的时候更换候选人
        for (int num : nums) {
            // 累加计数
            if (candidate1 == num) {
                count1++;
                continue;
            }

            if (candidate2 == num) {
                count2++;
                continue;
            }

            // 更换候选人
            if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
                continue;
            }

            if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
                continue;
            }

            // 计数减1
            count1--;
            count2--;
        }

        // 计数阶段
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
                count2++;
            }
        }

        int threshold = nums.length / 3;
        if (count1 > threshold) {
            res.add(candidate1);
        }
        if (count2 > threshold) {
            res.add(candidate2);
        }

        return res;
    }
}
