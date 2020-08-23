package Week_09;

/**
 * 91. 解码方法
 *
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/22
 */
public class DecodeWays_91 {

    public int numDecodings(String s) {
        // Clarification: 数字解码为26个大写字母，有几种解法
        // 方案1：递归+剪枝 TODO

        if (s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        return 0;
    }

    public int numDecodingsTwo(String s) {
        // 方案2：动态规划
        // dp[i]: 截止到i位置，解法个数
        // dp[i]
        //  = dp[i - 2], nums[i] == '0' && nums[i - 1] in ('1', '2')：合并解码
        //  = 0, nums[i] == '0' && nums[i - 1] in ('3', '4', '5', '6', '7', '8', '9')
        //  = dp[i - 2] + dp[i - 1], nums[i - 1] == '1' || nums[i - 1] == '2' && nums[i] between '1' and '6': 分开或合并解码
        //  = dp[i - 1]: 分开解码

        if (s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            char prev = s.charAt(i - 1);
            char curr = s.charAt(i);
            if (curr == '0') {
                char ch = s.charAt(i - 1);
                if (ch == '1' || ch == '2') { // 10, 20：合并解码
                    dp[i] = i >= 2 ? dp[i - 2] : 1;
                } else { // 30, 40, 50, 60, 70, 80, 90
                    return 0;
                }
            } else if (prev == '1' || (prev == '2' && curr >= '1' && curr <= '6')) { // 11-19, 21-26：分开或合并解码
                dp[i] = (i >= 2 ? dp[i - 2] : 1) + dp[i - 1];
            } else { // 27-29, 31-39, 41-49, 51-59, 61-69, 71-79, 81-89, 91-99：分开解码
                dp[i] = dp[i - 1];
            }
        }

        return dp[s.length() - 1];
    }
}
