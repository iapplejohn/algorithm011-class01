package Week_02;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. 前 K 个高频元素
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2 输出: [1,2] 示例 2:
 *
 * 输入: nums = [1], k = 1 输出: [1]  
 *
 * 提示：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 *
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/top-k-frequent-elements 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/5
 */
public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        // Clarification: 非空整数数组，返回前k个高频元素
        // 方案1: 哈希-存储元素出现的个数，优先堆-存储基于个数排序的元素
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int num1 : nums) {
            map.put(num1, map.getOrDefault(num1, 0) + 1);
        }

        // 优先堆-存储个数排序
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Integer num : map.keySet()) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.remove();
            }
        }

        int[] result = new int[k];
        int j = k - 1;
        while (!queue.isEmpty()) {
            Integer num = queue.poll();
            result[j--] = num;
        }

        return result;
    }

    public int[] topKFrequentPlus(int[] nums, int k) {
        // Clarification: 非空整数数组，返回前k个高频元素
        // 方案2: 哈希-存储元素出现的个数，优先堆-存储基于个数排序的元素
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int num1 : nums) {
            map.put(num1, map.getOrDefault(num1, 0) + 1);
        }

        // 优先堆-存储个数排序
        // 优化点：queue的size大于等于k时，只有个数更多（与堆顶元素的个数（堆中最少个数）比较）的元素才能添加
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Integer num : map.keySet()) {
            if (queue.size() < k) {
                queue.offer(num);
            } else if (map.get(num) > map.get(queue.peek())) {
                queue.remove();
                queue.offer(num);
            }
        }

        int[] result = new int[k];
        int j = k - 1;
        while (!queue.isEmpty()) {
            Integer num = queue.poll();
            result[j--] = num;
        }

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentElements instance = new TopKFrequentElements();
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;
        instance.topKFrequent(nums, k);
    }
}
