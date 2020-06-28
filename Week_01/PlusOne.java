package Week_01;

import java.util.Arrays;

/**
 * 66. 加一
 *
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/6/28
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {
        // 审题：数组表示一个非负整数，不会以零开头，求加1后的数组表示
        // 方案1: 先将数组转换为数字，加1拿到结果后，再赋值数组
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)
        // 问题：当digits表示的数字超出Integer最大值，结果会有问题
        int num = digits[digits.length - 1];
        for (int i = 0; i < digits.length - 1; i++) {
            num += digits[i] * Math.pow(10, (digits.length - i - 1));
        }

        num++;

        String val = String.valueOf(num);
        int[] digitsNew;
        if (val.length() > digits.length) {
            digitsNew = new int[val.length()];
        } else {
            digitsNew = digits;
        }

        for (int j = 0; j < digitsNew.length; j++) {
            int divide = (int) Math.pow(10, (digitsNew.length - j - 1));
            digitsNew[j] = num / divide;
            num %= divide;
        }

        return digitsNew;
    }

    public int[] plusOneTwo(int[] digits) {
        // 方案2: 末尾数字加1，分为进位和不进位两种情况
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)
        int p = digits.length - 1;
        while (p >= 0 && digits[p] == 9) {
            digits[p] = 0;
            p--;
        }

        if (p >= 0) {
            digits[p]++;
            return digits;
        } else {
            int[] digitsNew = new int[digits.length + 1];
            digitsNew[0] = 1;
            System.arraycopy(digits, 0, digitsNew, 1, digits.length);
            return digitsNew;
        }
    }

    public int[] plusOneThree(int[] digits) {
        // 方案3: 从末尾开始，每个位置数字加1并对10取模，不为0则结束循环
        // 考虑位数增加1位的情况
        for (int p = digits.length - 1; p >= 0; p--) {
            digits[p]++;
            digits[p] = digits[p] % 10;
            if (digits[p] != 0) {
                return digits;
            }
        }

        int[] digitsNew = new int[digits.length + 1];
        digitsNew[0] = 1;
        return digitsNew;
    }

    public static void main(String[] args) {
        PlusOne instance = new PlusOne();
        int[] result = instance.plusOne(new int[] {9,9,9});
        System.out.println(Arrays.toString(result));
    }

}
