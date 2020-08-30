package Week_09;

/**
 * 63. 不同路径 II
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 *
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/24
 */
public class UniquePathsII_63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Clarification: 每次向下或向右，到达右下角，中间可能有障碍物
        // 方案1: 动态规划
        // dp[i][j]: i - 行, j - 列
        // dp[i][j] = dp[0][j - 1], i == 0
        //      dp[i - 1][0], j == 0
        //      dp[i - 1][j] + dp[i][j - 1], i > 0 && j > 0
        // 时间复杂度: O(rows * cols)
        // 空间复杂度: O(rows * cols)

        int rows = obstacleGrid.length;
        if (rows == 0) {
            return 0;
        }

        int cols = obstacleGrid[0].length;
        if (cols == 0) {
            return 0;
        }

        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        int[][] dp = new int[rows][cols];
        dp[0][0] = 1;
        for (int c = 1; c < cols; c++) {
            if (obstacleGrid[0][c] == 1) {
                dp[0][c] = 0;
            } else {
                dp[0][c] = dp[0][c - 1];
            }
        }

        for (int r = 1; r < rows; r++) {
            if (obstacleGrid[r][0] == 1) {
                dp[r][0] = 0;
            } else {
                dp[r][0] = dp[r - 1][0];
            }
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (obstacleGrid[r][c] == 1) {
                    dp[r][c] = 0;
                } else {
                    dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
                }
            }
        }

        return dp[rows - 1][cols - 1];
    }
}
