package Week_01;

/**
 * 283. 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/6/28
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        // 审题：数组无序，将所有0放到最后，保持非零元素的相对顺序，不能拷贝额外的数组
        // 方案1：使用额外的数组，将非0元素拷贝到前面，0元素拷贝到最后
        // 时间复杂度: O(n)
        // 空间复杂度: O(n) 使用了额外数组
        if (nums == null || nums.length <= 1) {
            return;
        }

        int[] numsCopy = new int[nums.length];
        int p = 0, p0 = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                numsCopy[p0--] = nums[i];
            } else {
                numsCopy[p++] = nums[i];
            }
        }

        for (int j = 0; j < numsCopy.length; j++) {
            nums[j] = numsCopy[j];
        }
    }

    public void moveZeroesTwo(int[] nums) {
        // 方案2: 双指针 - 第一个: 非0元素待放位置，第二个：当前索引
        // 非0元素前移，然后将剩余部分置为0
        // 时间复杂度: O(n)
        // 空间复杂度: O(1)
        if (nums == null || nums.length <= 1) {
            return;
        }

        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (p != i) {
                    nums[p] = nums[i];
                }
                p++;
            }
        }

        for (int j = p; j < nums.length; j++) {
            nums[j] = 0;
        }
    }

    public void moveZeroesThree(int[] nums) {
        // 方案3: 最优解 + 双指针 - 第一个: 非0元素待放位置，第二个：当前指针
        // 当前如果是非0元素，和非0元素待放位置互换(该位置和当前索引之间的值都为0)，两个指针都自增；否则当前指针自增
        // 时间复杂度: O(n)，比方案2好，最坏情况下和方案2一致
        // 空间复杂度: O(1)
        if (nums == null || nums.length <= 1) {
            return;
        }

        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (p != i) {
                    swap(nums, p, i);
                }
                p++;
            }
        }
    }

    private void swap(int[] nums, int p, int i) {
        int temp = nums[p];
        nums[p] = nums[i];
        nums[i] = temp;
    }


}
