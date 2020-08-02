package Week_04;

/**
 * 69. x 的平方根
 *
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/25
 */
public class Sqrtx_69 {

    public int mySqrt(int x) {
        // Clarification x 为非负整数
        // 方案1: 袖珍计数器算法
        if (x <= 1) {
            return x;
        }

        int res = (int) Math.exp(0.5 * Math.log(x));
        return (long)(res + 1) * (res + 1) <= x ? res + 1 : res;
    }

    public int mySqrtTwo(int x) {
        // 方案1: 二分 逐渐逼近
        if (x <= 1) {
            return x;
        }

        int left = 0, right = x, res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // mid * mid 可能会越界，用long接
            long square = mid * mid;
            if (square <= x) {
                // 记住当前 mid 值，最后一个满足 square <= x 的值就是结果
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    public int mySqrtThree(int x) {
        // 方案3：牛顿迭代法 TODO
        return 0;
    }
}
