package Week_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/30
 */
public class LetterCombinationsOfAPhoneNumber_17 {

    public List<String> letterCombinations(String digits) {
        // Clarification: 输入数字字符串，返回它能表示的所有字母结合
        // 方案1: 分治
        // 使用哈希表存储 数字和字母的映射
        // 时间复杂度: O(2^n) n 为数字字符串的长度
        // 空间复杂度: O(n)

        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }

        Map<Character, String> map = new HashMap<>(16);
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        dfs(digits, 0, map, res, "");
        return res;
    }

    public void dfs(String digits, int level, Map<Character, String> map, List<String> res, String path) {
        // terminator
        if (level == digits.length()) {
            res.add(path);
            return;
        }

        // process current logic
        String letters = map.get(digits.charAt(level));
        for (int i = 0; i < letters.length(); i++) {
            // drill down
            dfs(digits, level + 1, map, res, path + letters.charAt(i));
        }

        // reverse current states
    }

}
