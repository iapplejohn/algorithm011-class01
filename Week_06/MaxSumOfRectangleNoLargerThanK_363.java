package Week_06;

/**
 * 363. 矩形区域不超过 K 的最大数值和
 *
 * 给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
 *
 * 示例:
 *
 * 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出: 2
 * 解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 说明：
 *
 * 矩阵内的矩形区域面积必须大于 0。
 * 如果行数远大于列数，你将如何解答呢？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/31
 */
public class MaxSumOfRectangleNoLargerThanK_363 {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        // Clarification: <= k的最大矩形和
        // 方案1：暴力 + 动态规划，4层循环 [超出内存限制]
        // 时间复杂度: O(m^2n^2)
        // 空间复杂度: O(m^2n^2)
        int rows = matrix.length, cols = matrix[0].length;
        int max = Integer.MIN_VALUE;

        // dp 状态：两个位置(row, col)组成的矩形，从 (i1, j1) 到 (i2, j2)
        int[][][][] dp = new int[rows + 1][cols + 1][rows + 1][cols + 1];
        for (int i1 = 1; i1 <= rows; i1++) {
            for (int j1 = 1; j1 <= cols; j1++) {
                // 赋初始值
                dp[i1][j1][i1][j1] = matrix[i1 - 1][j1 - 1];
                for (int i2 = i1; i2 <= rows; i2++) {
                    for (int j2 = j1; j2 <= cols; j2++) {
                        // 状态转移方程
                        dp[i1][j1][i2][j2] = dp[i1][j1][i2 - 1][j2] + dp[i1][j1][i2][j2 - 1] - dp[i1][j1][i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                        if (dp[i1][j1][i2][j2] <= k && dp[i1][j1][i2][j2] > max) {
                            max = dp[i1][j1][i2][j2];
                        }
                    }
                }
            }
        }

        return max;
    }

    public int maxSumSubmatrixTwo(int[][] matrix, int k) {
        // 方案2：暴力 + 动态规划 + 状态压缩
        // 方案1每次更换左上角(i, j)后，之前记录的值都没用过了
        // 尝试每次更换左上角时就重复利用 dp，只需记录右下角即可
        // 时间复杂度: O(m^2n^2)
        // 空间复杂度: O(mn)

        int rows = matrix.length, cols = matrix[0].length;
        int max = Integer.MIN_VALUE;

        for (int i1 = 1; i1 <= rows; i1++) {
            for (int j1 = 1; j1 <= cols; j1++) {
                // dp 状态：两个位置(row, col)组成的矩形，从 (i1, j1) 到 (i2, j2)，只记(i2, j2)，必须使用新的，否则会被之前的值干扰
                int[][] dp = new int[rows + 1][cols + 1];
                // 赋初始值
                dp[i1][j1] = matrix[i1 - 1][j1 - 1];
                for (int i2 = i1; i2 <= rows; i2++) {
                    for (int j2 = j1; j2 <= cols; j2++) {
                        // 状态转移方程
                        dp[i2][j2] = dp[i2 - 1][j2] + dp[i2][j2 - 1] - dp[i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                        if (dp[i2][j2] <= k && dp[i2][j2] > max) {
                            max = dp[i2][j2];
                        }
                    }
                }
            }
        }

        return max;
    }

    public int maxSumSubmatrixThree(int[][] matrix, int k) {
        // 方案3：数组滚动
        // 先固定左右边界，不断压入 行累计数组
        int rows = matrix.length, cols = matrix[0].length;
        int max = Integer.MIN_VALUE;

        // 枚举左边界
        for (int left = 0; left < cols; left++) {
            int[] rowSum = new int[rows];
            // 枚举右边界
            for (int right = left; right < cols; right++) {
                // 按每一行累计
                for (int i = 0; i < rows; i++) {
                    rowSum[i] += matrix[i][right];
                }

                // 求 rowSum 满足条件（不大于k的最大值）的连续子数组之和
                max = Math.max(max, findMax(rowSum, k));
            }
        }

        return max;
    }

    private int findMax(int[] rowSum, int k) {
        // 时间复杂度: O(rows^2)
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < rowSum.length; l++) {
            int sum = 0;
            for (int r = l; r < rowSum.length; r++) {
                sum += rowSum[r];
                if (sum > max && sum <= k) {
                    max = sum;
                }
            }
        }
        return max;
    }

    private int findMaxPlus(int[] rowSum, int k) {
        // 判断之前的和是否大于0，大于0就累加，否则丢弃掉，使用当前位置的值
        // 如果和最大值小于k，找到结果
        int rollSum = rowSum[0], rollMax = rollSum;

        for (int i = 1; i < rowSum.length; i++) {
            if (rollSum > 0) {
                rollSum += rowSum[i];
            } else {
                rollSum = rowSum[i];
            }
            if (rollSum > rollMax) {
                rollMax = rollSum;
            }
        }
        if (rollMax <= k) {
            return rollMax;
        }

        // 如果和最大值大于k，需要暴力查找稍微小一点的值，满足<=k
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < rowSum.length; l++) {
            int sum = 0;
            for (int r = l; r < rowSum.length; r++) {
                sum += rowSum[r];
                if (sum > max && sum <= k) {
                    max = sum;
                }
                if (max == k) {
                    return k;
                }
            }
        }
        return max;
    }
}
