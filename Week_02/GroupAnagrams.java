package Week_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/2
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        // Clarification: 在判断是否异位词的基础上，将异位词分组并输出，小写字母，不考虑输出顺序
        // 方案1: 哈希 + 排序，key为排序过的字符串，value为异位词列表
        // 时间复杂度: O(N KlogK)) - N是strs的长度，K是strs中字符串的最大长度
        // 空间复杂度: O(N K)

        if (strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chs = s.toCharArray();
            Arrays.sort(chs);
            String key = new String(chs);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(s);
        }

        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagramsTwo(String[] strs) {
        // 方案2: 哈希 + 计数，key为点位计数字符串，value为异位词列表
        // 时间复杂度: O(N K)
        // 空间复杂度: O(N K)

        if (strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] count = new int[26];
            for (char ch : s.toCharArray()) {
                count[ch - 'a']++;
            }

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                builder.append('#').append(count[i]);
            }

            String key = builder.toString();
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(s);
        }

        return new ArrayList<>(map.values());
    }
}
