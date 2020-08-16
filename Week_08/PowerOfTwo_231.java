package Week_08;

/**
 * 231. 2的幂
 *
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: true
 * 解释: 20 = 1
 * 示例 2:
 *
 * 输入: 16
 * 输出: true
 * 解释: 24 = 16
 * 示例 3:
 *
 * 输入: 218
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-two
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/15
 */
public class PowerOfTwo_231 {

    public boolean isPowerOfTwo(int n) {
        // Clarification: 是否2的 幂次方
        // 方案1: 暴力，如果被2整除，则除以2，判断最后是否为1
        // 时间复杂度: O(logn)
        // 空间复杂度: O(1)

        if (n <= 0) {
            return false;
        }

        while (n % 2 == 0) {
            n /= 2;
        }

        return n == 1;
    }

    public boolean isPowerOfTwoTwo(int n) {
        // 方案2: 位运算

        if (n <= 0) {
            return false;
        }

        return (n & (n - 1)) == 0;
    }

}
