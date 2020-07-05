package Week_02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhujiang.cheng
 * @since 2020/7/1
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        // Clarification: 字符串只包含小写字母，异位词: 两个字符串包含相同的字符,且个数相等
        // 方案1: 排序后，判断两个字符串是否相等
        // 时间复杂度: O(NlogN)
        // 空间复杂度: O(1)
        if (s.length() != t.length()) {
            return false;
        }

        char[] chs1 = s.toCharArray();
        char[] chs2 = t.toCharArray();

        Arrays.sort(chs1);
        Arrays.sort(chs2);
        return Arrays.equals(chs1, chs2);
    }

    public boolean isAnagramTwo(String s, String t) {
        // 方案2: 哈希 - map，
        // 记录第一个字符串每个字符出现的次数，
        // 遍历第二个字符串，判断是否存在哈希表中，存在则value（次数）减1，小于0表示不是异位词
        int len = s.length();
        int len2 = t.length();
        if (len != len2) {
            return false;
        }

        int capacity = (int) (len / 0.75);
        Map<Character, Integer> map = new HashMap<>(capacity);
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int j = 0; j < t.length(); j++) {
            char c = t.charAt(j);
            int value = map.getOrDefault(c, 0);
            if (value == 0) {
                return false;
            } else if (value == 1) {
                map.remove(c);
            } else {
                map.put(c, --value);
            }
        }

        return map.size() == 0;
    }

    public boolean isAnagramThree(String s, String t) {
        // 方案3: 数组，位置为26个字母的索引，值为出现的次数
        int len = s.length();
        int len2 = t.length();
        if (len != len2) {
            return false;
        }

        int[] nums = new int[26];
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            nums[ch - 'a']++;
        }

        for (int j = 0; j < len2; j++) {
            char c = t.charAt(j);
            if (nums[c - 'a'] == 0) {
                return false;
            } else {
                nums[c - 'a']--;
            }
        }

        for (int k = 0; k < 26; k++) {
            if (nums[k] != 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isAnagramThreePlus(String s, String t) {
        // 方案3: 哈希 - 数组，位置为26个字母的索引，值为出现的次数
        int len = s.length();
        int len2 = t.length();
        if (len != len2) {
            return false;
        }

        int[] nums = new int[26];
        // 两个for循环内容放到一起
        for (int i = 0; i < len; i++) {
            nums[s.charAt(i) - 'a']++;
            nums[t.charAt(i) - 'a']--;
        }

        for (int k = 0; k < 26; k++) {
            if (nums[k] != 0) {
                return false;
            }
        }

        return true;
    }
}
