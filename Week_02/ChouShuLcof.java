package Week_02;

/**
 * 剑指 Offer 49. 丑数
 *
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 * 示例:
 *
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 *
 * 1 是丑数。
 * n 不超过1690。
 * 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chou-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/2
 */
public class ChouShuLcof {

    public int nthUglyNumber(int n) {
        // Clarification: 求第n个丑数，1也是丑数，n不超过1690
        // 方案1: 暴力 - 超出时间限制
        if (n <= 6) {
            return n;
        }

        int k = 6;
        int num = 7;
        while (k < n) {
            int d = ++num;
            while (d % 2 == 0) {
                d /= 2;
            }

            if (d > 1) {
                while (d % 3 == 0) {
                    d /= 3;
                }

                if (d > 1) {
                    while (d % 5 == 0) {
                        d /= 5;
                    }
                }
            }

            if (d == 1) {
                k++;
            }
        }

        return num;
    }

    public int nthUglyNumberTwo(int n) {
        // 方案2: 动态规划
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;

        for (int i = 1; i < n; i++) {
            int n2 = dp[p2] * 2, n3 = dp[p3] * 3, n5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);

            if (dp[i] == n2) {
                p2++;
            }
            if (dp[i] == n3) {
                p3++;
            }
            if (dp[i] == n5) {
                p5++;
            }
        }

        return dp[n-1];
    }

    public static void main(String[] args) {
        ChouShuLcof instance = new ChouShuLcof();
        int result = instance.nthUglyNumber(1352);
        System.out.println(result);
    }
}
