package Week_09;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 62. 不同路径
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 *
 *
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 *
 *
 *
 * 示例 1:
 *
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 *
 * 输入: m = 7, n = 3
 * 输出: 28
 *
 *
 * 提示：
 *
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10 ^ 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/24
 */
public class UniquePaths_62 {

    public int uniquePaths(int m, int n) {
        // Clarification: 每次向右或向下，有多少种不同的走法
        // 方案1：动态规划，自底向上
        // dp[r][c]: 到[i,j]位置有几种不同的走法
        // dp[r][c] = dp[0][c - 1], r == 0
        //   = dp[r - 1][0], c == 0
        //   = dp[r][c - 1] + dp[r - 1][c], r > 0, c > 0
        // 时间复杂度: O(mn)
        // 空间复杂度: O(mn)

        int[][] dp = new int[n][m];
        dp[0][0] = 1;

        // 第一行
        for (int c = 1; c < m; c++) {
            dp[0][c] = dp[0][c - 1];
        }

        // 第一列
        for (int r = 1; r < n; r++) {
            dp[r][0] = dp[r - 1][0];
        }

        // 其他行和列
        for (int r = 1; r < n; r++) {
            for (int c = 1; c < m; c++) {
                dp[r][c] = dp[r][c - 1] + dp[r - 1][c];
            }
        }

        return dp[n - 1][m - 1];
    }

    public int uniquePathsPlus(int m, int n) {
        // 方案1': 动态规划，空间压缩为 2n
        // 时间复杂度: O(mn)
        // 空间复杂度: O(2n)
        // cur[r]: 到达当前网格（第 r 行），有几种走法
        // cur[r] = cur[r - 1] + pre[r], r >= 1
        //        该列上一行的值  + 该行前一列的值

        int[] pre = new int[n];
        int[] cur = new int[n];
        Arrays.fill(pre, 1);
        Arrays.fill(cur, 1);

        for (int c = 1; c < m; c++) {
            for (int r = 1; r < n; r++) {
                cur[r] = cur[r - 1] + pre[r];
            }
            // 当前列作为上一列，继续循环下一列
            pre = cur.clone();
        }
        return pre[n - 1];
    }

    public int uniquePathsPlusPlus(int m, int n) {
        // 方案1': 动态规划，空间压缩为 n
        // 时间复杂度: O(mn)
        // 空间复杂度: O(n)

        int[] cur = new int[n];
        Arrays.fill(cur, 1);

        for (int col = 1; col < m; col++) {
            for (int row = 1; row < n; row++) {
                cur[row] += cur[row - 1];
            }
        }

        return cur[n - 1];
    }

    public int uniquePathsTwo(int m, int n) {
        // 方案2: 动态规划 自顶向下 + 备忘

        Map<String, Integer> cache = new HashMap<>(m * n);
        return recursive(n - 1, m - 1, cache);
    }

    public int recursive(int r, int c, Map<String, Integer> cache) {
        // terminator
        if (r == 0 && c == 0) {
            return 1;
        }

        // process current logic
        // drill down
        String key = r + "_" + c;
        if (cache.get(key) != null) {
            return cache.get(key);
        }

        int top = 0;
        if (r > 0) {
            top = recursive(r - 1, c, cache);
        }
        int left = 0;
        if ( c > 0) {
            left = recursive(r, c - 1, cache);
        }
        int value = top + left;
        cache.put(key, value);

        return value;
    }
}
