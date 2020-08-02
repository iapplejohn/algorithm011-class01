package Week_06;

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
 * @since 2020/8/1
 */
public class DecodeWays_91 {

    public int numDecodings(String s) {
        // Clarification: 输入只包含数字，计算解码方法，有些分组不合法
        // 方案1：递归 TODO

        return 0;
    }

    public int numDecodingsTwo(String s) {
        // 方案2：动态规划

        if (s.charAt(0) == '0') {
            return 0;
        }

        int pre = 1, curr = 1;
        for (int i = 1; i < s.length(); i++) {
            int temp = curr;
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
                    curr = pre;
                } else {
                    return 0;
                }
            } else if (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) >= '1' && s.charAt(i) <= '6')) {
                curr = curr + pre;
            }
            pre = temp;
        }

        return curr;
    }
}
