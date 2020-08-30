package Week_09;

/**
 * 72. 编辑距离
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/25
 */
public class EditDistance_72 {

    public int minDistance(String word1, String word2) {
        // Clarification: word1 转换成 word2，每次操作一个字符（插入、删除、替换），最少操作数
        // 方案1：动态规划 二维数组 顺推
        // dp[0...i][0...j]: 表示word1 i个字符、word2 j个字符需要的最少操作数
        // dp[i][j] = dp[i - 1][j - 1], word1[i] == word2[j]
        // = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1, word1[i] != word2[j]

        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }

        // 数组一维和二维长度，等于字符串长度 + 1，为的是空字符作为起始值
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 第一列: 和 '' 的编辑距离
        for (int i = 1; i < len1 + 1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }

        // 第一行: 和 '' 的编辑距离
        for (int j = 1; j < len2 + 1; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }

        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                // 字符相同，等于之前字符的编辑距离
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 字符不相同: 等于替换、删除一个字符的较小编辑距离 + 1
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[len1][len2];
    }

    public int minDistanceTwo(String word1, String word2) {
        // 方案2: BFS, two-ended BFS TODO
        return 0;
    }

}
