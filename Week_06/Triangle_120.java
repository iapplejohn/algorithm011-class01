package Week_06;

import java.util.List;

/**
 * 120. 三角形最小路径和
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 *  
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 *  
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/25
 */
public class Triangle_120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        // Clarification: 最小路径和
        // 方案1: 动态规划
        // dp[i][j]: 到达第i行第j列的最小路径和
        // dp[i][j] = dp[i - 1][0], j == 0
        // = min(dp[i - 1][j - 1], dp[i - 1][j]), j > 0 && j < length
        // = dp[i - 1][j - 1], j == length
        // 时间复杂度: O(n^2)
        // 空间复杂度: O(n^2)

        int n = triangle.size();

        // 状态定义
        int[][] dp = new int[n][n];

        // 初始值
        dp[0][0] = triangle.get(0).get(0);

        // 遍历行
        for (int i = 1; i < n; i++) {
            // 第一列
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);

            // 中间列
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
            }

            // 最后列
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }

        int minSum = dp[n - 1][0];
        // 遍历第 最后一行(n - 1)行的 dp 值，获取最小值
        for (int j = 1; j < n; j++) {
            minSum = Math.min(minSum, dp[n - 1][j]);
        }

        return minSum;
    }

        // 方案2: 递归 + 剪枝
}
