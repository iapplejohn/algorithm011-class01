package Week_02;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *  
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/2
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        // Clarification: 数组无序，找出两个数，满足和等于指定值，返回元素下标。每种输入只有一种答案，同个元素只能用一次
        // 方案1: 暴力，双重循环，判断是否满足条件
        // 时间复杂度: O(n^2)
        // 空间复杂度: O(1)
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }

        return new int[0];
    }

    public int[] twoSumTwo(int[] nums, int target) {
        // 方案2: 两遍哈希，a + b = target => a = target - b
        // 将每个元素放到哈希表中(k:元素值,v:元素下标)，遍历数组（每个a)，判断target - b是否存在哈希表中，且不是a
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)

        int capacity = (int) (nums.length / 0.75);
        Map<Integer, Integer> map = new HashMap<>(capacity);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int j = 0; j < nums.length; j++) {
            int b = target - nums[j];
            if (map.containsKey(b) && map.get(b) != j) {
                return new int[]{j, map.get(b)};
            }
        }

        return new int[0];
    }

    public int[] twoSumThree(int[] nums, int target) {
        // 方案3: 一遍哈希，将元素放到哈希表的过程中，判断target - b是否存在哈希表中，且不是a
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)

        int capacity = (int) (nums.length / 0.75);
        Map<Integer, Integer> map = new HashMap<>(capacity);
        for (int i = 0; i < nums.length; i++) {
            int b = target - nums[i];
            if (map.containsKey(b) && map.get(b) != i) {
                return new int[]{i, map.get(b)};
            } else {
                map.put(nums[i], i);
            }
        }

        return new int[0];
    }
}
