package Week_01;

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
 * @since 2020/6/28
 */
public class TwoSum {

    public int[] twoSumOne(int[] nums, int target) {
        // 审题：整数数组不是有序的，找到一个答案后，返回即可，同一个元素不能使用两遍
        // 方案1：暴力 两重循环
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
        // 方案2: 两遍哈希表
        // 第一遍：将数组的值和对应的索引，存到哈希表中
        // 第二遍: 遍历数组每个元素，计算满足条件的另一个值，是否存在于哈希表中（不能是当前元素）
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)
        int capacity = (int) (nums.length / 0.75);
        Map<Integer, Integer> map = new HashMap<>(capacity);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int j = 0; j < nums.length; j++) {
            int another = target - nums[j];
            if (map.containsKey(another) && map.get(another) != j) {
                return new int[]{j, map.get(another)};
            }
        }

        return new int[0];
    }

    public int[] twoSumThree(int[] nums, int target) {
        // 方案3: 一遍哈希表
        // 第一遍: 遍历数组每个元素，计算满足条件的另一个值，是否存在于哈希表中（不能是当前元素）
        // 存在就返回，不存在就将当前值和索引放到哈希表中
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)
        int capacity = (int) (nums.length / 0.75);
        Map<Integer, Integer> map = new HashMap<>(capacity);

        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another) && map.get(another) != i) {
                return new int[]{i, map.get(another)};
            } else {
                map.put(nums[i], i);
            }
        }

        return new int[0];
    }
}
