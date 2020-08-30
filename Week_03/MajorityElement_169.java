package Week_03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,3] 输出: 3 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2] 输出: 2
 *
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/majority-element 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/30
 */
public class MajorityElement_169 {

    public int majorityElement(int[] nums) {
        // Clarification: 找到多数元素，个数大于 n / 2，最多只有一个
        // 方案1: 计数法，哈希表统计个数，如果大于 n / 2，返回
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)

        int len = nums.length;
        int target = len / 2;
        int capacity = (int) (len / 0.75);
        Map<Integer, Integer> countMap = new HashMap<>(capacity);

        for (int i = 0; i < len; i++) {
            Integer count = countMap.computeIfAbsent(nums[i], k -> 0);
            if (++count > target) {
                return nums[i];
            }
            countMap.put(nums[i], count);
        }

        return -1;
    }

    public int majorityElementTwo(int[] nums) {
        // 方案2: 排序法，返回下标为 n / 2 的元素
        // 时间复杂度: O(nlogn)
        // 空间复杂度: O(1)

        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int majorityElementThree(int[] nums) {
        // 方案3: 摩尔投票法
        // 候选人初始化为 nums[0]，票数初始化为 1，遇到相同的数，count++，否则count--
        // 当票数为 0 时，更换候选人，并将票数 count 置为 1
        // 因为多数元素个数 > 2/n，其他元素综合 <= 2/n，两两抵消后，至少还有1个"多数元素"

        int candidate = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
            } else {
                if (--count == 0) {
                    candidate = nums[i];
                    count = 1;
                }
            }
        }

        return candidate;
    }
}
