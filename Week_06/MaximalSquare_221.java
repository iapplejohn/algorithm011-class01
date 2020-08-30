package Week_06;

/**
 * 221. 最大正方形
 *
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/3
 */
public class MaximalSquare_221 {

    public int maximalSquare(char[][] matrix) {
        // Clarification: 最大正方形 TODO
        // 方案1: 暴力
        return 0;
    }

    public int maximalSquareTwo(char[][] matrix) {
        // 方案2: 动态规划
        // 状态 dp[row][col]: 左上方到当前位置能组成最大正方形的变长 row - 行索引, col 列索引
        // dp[row][col] = 1, row = 0 or col = 0, matrix[row][col] = '1'
        // = min(dp[row - 1][col], dp[row][col - 1], dp[row - 1][col - 1]), row > 0 and col > 0
        // 时间复杂度: O(m * n)
        // 空间复杂度: O(m * n)

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length, cols = matrix[0].length, maxSide = 0;
        int[][] dp = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == '1') {
                    if (row == 0 || col == 0) {
                        dp[row][col] = 1;
                    } else {
                        dp[row][col] = Math.min(Math.min(dp[row - 1][col], dp[row][col - 1]), dp[row - 1][col - 1]);
                    }
                    maxSide = Math.max(maxSide, dp[row][col]);
                }
            }
        }

        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }
}
