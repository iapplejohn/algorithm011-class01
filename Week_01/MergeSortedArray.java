package Week_01;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 *
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 *  
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 *
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/6/27
 */
public class MergeSortedArray {

    public void mergeOne(int[] nums1, int m, int[] nums2, int n) {
        // 审题: nums1有足够的空间，两个都是有序数组
        // 方案1: 将nums2放到nums1的尾部，并排序
        // 时间复杂度: O((m + n)log(m + n))
        // 空间复杂度: O(1)

        if (nums2 == null || n == 0) {
            return;
        }

        System.arraycopy(nums2, 0, nums1, m, n);

        // 对整个数组进行排序，没有利用两个数组有序的特性
        // 而且会将多余的0排进来，导致结果错误
        // [0,0,0,0,0,0,0,0]
        // 3
        // [2,5,6]
        // 3
        Arrays.sort(nums1);
    }

    public void mergeTwo(int[] nums1, int m, int[] nums2, int n) {
        // 方案2: 双指针 / 从前往后
        // 额外数组暂存nums1的m个数，然后相互比较，小的放到nums1中
        // 时间复杂度: O(m + n)
        // 空间复杂度: O(m)

        if (nums2 == null || n == 0) {
            return;
        }

        int[] nums1Copy = new int[m];
        System.arraycopy(nums1, 0, nums1Copy, 0, m);

        int i = 0, j = 0, cur = 0;
        while (i < m && j < n) {
            if (nums1Copy[i] < nums2[j]) {
                nums1[cur] = nums1Copy[i];
                i++;
            } else {
                nums1[cur] = nums2[j];
                j++;
            }
            cur++;
        }

        if (i < m) {
            System.arraycopy(nums1Copy, i, nums1, cur, m - i);
        } else if (j < n) {
            System.arraycopy(nums2, j, nums1, cur, n - j);
        }
    }

    public void mergeThree(int[] nums1, int m, int[] nums2, int n) {
        // 方案3: 双指针 / 从后往前
        // nums1和nums2，从后面开始比较，放到nums1中
        // 时间复杂度: O(m + n)
        // 空间复杂度: O(m)

        if (nums2 == null || n == 0) {
            return;
        }

        int p1 = m - 1, p2 = n - 1, p = m + n - 1;
        while (p1 >=0 && p2 >= 0) {
            if (nums1[p1] < nums2[p2]) {
                nums1[p] = nums2[p2];
                p2--;
            } else {
                nums1[p] = nums1[p1];
                p1--;
            }
            p--;
        }

        // p1 >=0 时，不需移动
        if (p2 >= 0) {
            System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
        }
    }
}
