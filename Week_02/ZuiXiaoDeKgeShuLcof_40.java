package Week_02;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 剑指 Offer 40. 最小的k个数
 *
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/14
 */
public class ZuiXiaoDeKgeShuLcof_40 {

    public int[] getLeastNumbers(int[] arr, int k) {
        // 无序数组，输出最小的k个数组成的数组
        // 方案1：排序 + 利用工具类复制最小的k个数
        // 时间复杂度: O(nlogn)
        // 空间复杂度: O(logn)

        if (arr.length == 0 || k == 0) {
            return new int[0];
        }

        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }

    public int[] getLeastNumbersTwo(int[] arr, int k) {
        // 方案2：维护大顶堆
        // 时间复杂度: TODO
        // 空间复杂度: O(n)

        if (arr.length == 0 || k == 0) {
            return new int[0];
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(((o1, o2) -> o2 - o1 ));
        for (int num : arr) {
            queue.add(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }

        return res;
    }
}
