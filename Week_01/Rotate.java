package Week_01;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/6/27
 */
public class Rotate {

    public void rotateOne(int[] nums, int k) {
        // 审题: 右移k个位置，k大于0，尾部元素会到前面，空间复杂度为 O(1)，原地操作
        // 方案1: 使用额外的数组，从k位置拿数据，放到新数组的起始位置，注意超出右边界的处理（不满足O(1))
        // 时间复杂度: O(n)
        // 空间复杂度: O(k)
        // 使用System.arrayCopy本地方法
        if (nums == null || nums.length == k) {
            return;
        }

        if (k > nums.length) {
            k %= nums.length;
        }

        int[] numsNew = new int[k];
        System.arraycopy(nums, nums.length - k, numsNew, 0, k);

        System.arraycopy(nums, 0, nums, k, nums.length - k);
        System.arraycopy(numsNew, 0, nums, 0, k);
    }

    public void rotateOnePlus(int[] nums, int k) {
        // 方案1: 使用额外的数组: 将原数组的数据放到指定位置，再放回原数组
        // 时间复杂度: O(n)
        // 空间复杂度: O(k)
        if (nums == null || nums.length == k) {
            return;
        }

        if (k > nums.length) {
            k %= nums.length;
        }

        int[] array = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[(i + k) % nums.length] = nums[i];
        }

        System.arraycopy(array, 0, nums, 0, array.length);
    }

    public void rotateTow(int[] nums, int k) {
        // 方案2: 暴力，每次移动一个元素
        // 时间复杂度: O(n * k)
        // 空间复杂度: O(1)
        // 初始元素: 最后一个

        if (nums == null || nums.length == k) {
            return;
        }

        // k 可能大于数组长度
        if (k > nums.length) {
            k %= nums.length;
        }

        // 移动k次
        for (int i = 0; i < k; i++) {
            int previous = nums[nums.length - 1];

            // 每次移动一个位置
            for (int j = 0; j < nums.length; j++) {
                int temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }

    public void rotateThree(int[] nums, int k) {
        // 方案3: 环状替换, 直接把每一个数字放到它最后的位置
        // 时间复杂度: O(n)
        // 空间复杂度: O(1)

        if (nums == null || nums.length == k) {
            return;
        }

        // 移动元素的个数
        int count = 0;
        // 遍历（外层）每个位置，终止条件：移动了nums.length个元素
        // 循环（内层）往前移动k个位置, k个位置的元素往前移k个位置, 终止条件: 到了起始位置
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int previous = nums[start];

            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = previous;
                previous = temp;
                current = next;
                count++;
            } while (current != start);
        }
    }

    public void rotateFour(int[] nums, int k) {
        // 方案4: 使用反转 TODO
        // 时间复杂度: O(n)
        // 空间复杂度: O(1)

        if (nums == null || nums.length == k) {
            return;
        }
    }

    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        int[] nums = new int[]{1,2,3,4,5,6,7};
        rotate.rotateOne(nums, 3);
        System.out.println(nums);
    }
}
