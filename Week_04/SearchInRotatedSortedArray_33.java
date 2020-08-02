package Week_04;

/**
 * 33. 搜索旋转排序数组
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/20
 */
public class SearchInRotatedSortedArray_33 {

    public int search(int[] nums, int target) {
        // Clarification: 升序不重复数组进行了旋转，查找目标值是否存在
        // 方案1：暴力
        // 时间复杂度: O(n)
        // 空间复杂度: O(1)

        int len = nums.length;
        if (len == 0) {
            return -1;
        }

        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public int searchTwo(int[] nums, int target) {
        // 方案2：二分查找，
        // 中间位置将数组一分为二，肯定有一半是有序的，需要判断哪边是有序的，然后和target进行比较
        // 有序，且target在最小和最大值之间，选择这一边，否则另一边
        // 注意: 不能比较mid和target值后，进行选择

        int len = nums.length;
        if (len == 0) {
            return -1;
        }

        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[right]) {
                // 右边有序

                if (nums[right] == target) {
                    return right;
                }
                if (target > nums[mid] && target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                // 左边有序

                if (nums[left] == target) {
                    return left;
                }
                if (target > nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }
}
