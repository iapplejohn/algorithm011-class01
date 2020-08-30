package Week_03;

/**
 * 50. Pow(x, n)
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/10
 */
public class PowxN_50 {

    public double myPow(double x, int n) {
        // Clarification: 计算 x 的 n 次幂
        // 方案1: 暴力 TODO 有 case 未通过
        // int32 变量 n ∈ [-2147483648, 2147483647]，因此当 n = -2147483648 时
        // 执行 n = -n 会因越界而赋值出错，改用 long 来存储
        // 时间复杂度: O(n)
        // 空间复杂度: O(1)

        double result = x;
        long l = n;

        if (x == 1 || n == 0) {
            return 1;
        }

        if (n < 0) {
            l = -n;
            x = 1 / x;
        }
        for (int i = 1; i < l; i++) {
            result *= x;
        }

        return result;
    }

    public double myPowTwo(double x, int n) {
        // 方案2: 分治，将问题一分为二 n / 2
        // n 可能为负数，奇数
        // 时间复杂度: O(log n)
        // 空间复杂度: O(n) ?

        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        return divideConquer(x, n);
    }

    public double divideConquer(double x, int n) {
        // terminator
        if (n == 0) {
            return 1;
        }

        // conquer subproblems
        double subresult = divideConquer(x, n / 2);

        // process and generate the final result
        double result;
        if ((n & 1) == 0) {
            result = subresult * subresult;
        } else {
            result = subresult * subresult * x;
        }

        // revert the current level states

        return result;
    }
}
