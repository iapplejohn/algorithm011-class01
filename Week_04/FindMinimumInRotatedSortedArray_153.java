package Week_04;

/**
 * 153. 寻找旋转排序数组中的最小值
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * 示例 1:
 *
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/27
 */
public class FindMinimumInRotatedSortedArray_153 {

    public int findMin(int[] nums) {
        // Clarification: 寻找旋转排序数组中的最小值，元素不重复
        // 方案1: 一遍循环
        // 时间复杂度: O(n)
        // 空间复杂度: O(1)

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        return min;
    }

    public int findMinTwo(int[] nums) {
        // 方案2: 二分法
        // 时间复杂度: O(n)
        // 空间复杂度: O(1)

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[right]) {
                // middle 肯定不是最小值
                left = mid + 1;
            } else {
                // middle 可能是最小值
                right = mid;
            }
        }

        return nums[left];
    }

    public int findMinTwoPlus(int[] nums) {
        // 方案2': 二分法
        // 找到改变点：
        // nums[i] > nums[i + 1]，nums[i + 1]为最小值
        // nums[i - 1] > nums[i]，nums[i]为最小值

        int len = nums.length;
        // 只有一个元素
        if (len == 1) {
            return nums[0];
        }

        // 升序数组未旋转
        if (nums[0] < nums[len - 1]) {
            return nums[0];
        }

        int left = 0, right = len - 1;
        while (left < right) {
            int mid = (left + right) >> 1;

            // mid + 1 为改变点
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            // mid 为改变点
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            if (nums[mid] > nums[right]) {
                // middle 肯定不是最小值
                left = mid + 1;
            } else {
                // middle 也肯定不是最小值，前面已判断过
                right = mid - 1;
            }
        }

        return -1;
    }
}
