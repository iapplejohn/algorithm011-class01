package Week_09;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 151. 翻转字符串里的单词
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 *
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * 说明：
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * 进阶：
 *
 * 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/24
 */
public class ReverseWordsInAString_151 {

    public String reverseWords(String s) {
        // Clarification: 翻转字符串中的单词
        // 方案1: 从右往左遍历字符，使用 StringBuilder 存储单词

        int len = s.length();
        // 单词右边的空格，赋初始值为字符串长度(Exclusive)
        int end = len;
        boolean findWord = false;
        StringBuilder builder = new StringBuilder(len);

        char[] source = s.toCharArray();
        for (int i = source.length - 1; i >= 0; i--) {
            if (source[i] == ' ') {
                if (!findWord) {
                    end = i;
                } else {
                    builder.append(s.subSequence(i + 1, end)).append(' ');
                    findWord = false;
                    end = i;
                }
            } else {
                findWord = true;
            }
        }

        // 开头不是空格：有个单词还未处理
        if (findWord) {
            builder.append(s.subSequence(0, end)).append(' ');
        }

        // 去除最后的空格
        if (builder.length() == 0) {
            return builder.toString();
        } else {
            return builder.substring(0, builder.length() - 1);
        }
    }

    public String reverseWordsTwo(String s) {
        // 方案2: 先拆分单词，再 reverse，使用StringBuilder

        String[] words = s.trim().split("\\s+");
        List<String> list = Arrays.asList(words);
        Collections.reverse(list);
        StringBuilder builder = new StringBuilder(s.length());
        for (String word : list) {
            builder.append(word).append(' ');
        }

        return builder.substring(0, builder.length() - 1);
    }

    public String reverseWordsTwoPlus(String s) {
        // 方案2': 先拆分单词，再 reverse，使用String.join

        String[] words = s.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public String reverseWordsThree(String s) {
        // 方案3: reverse 字符串，再 reverse 单词

        int len = s.length();
        char[] source = s.toCharArray();

        // reverse 字符串
        int left = 0, right = len - 1;
        reverse(source, left, right);

        int begin = -1;
        boolean findWord = false;
        StringBuilder builder = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            if (source[i] == ' ') {
                if (!findWord) {
                    begin = i;
                } else {
                    reverse(source, begin + 1, i - 1);
                    // 注意：append 字符数组时，第三个参数是 len
                    builder.append(source, begin + 1, i - begin - 1).append(' ');
                    findWord = false;
                    begin = i;
                }
            } else {
                findWord = true;
            }
        }

        // 结尾不是空格：有个单词还未处理
        if (findWord) {
            reverse(source, begin + 1, len - 1);
            // 注意：append 字符数组时，第三个参数是 len
            builder.append(source, begin + 1, len - begin - 1).append(' ');
        }

        // 去除最后的空格
        if (builder.length() == 0) {
            return builder.toString();
        } else {
            return builder.substring(0, builder.length() - 1);
        }
    }

    private void reverse(char[] source, int begin, int end) {
        while (begin < end) {
            char temp = source[begin];
            source[begin] = source[end];
            source[end] = temp;
            begin++;
            end--;
        }
    }

    public static void main(String[] args) {
        ReverseWordsInAString_151 instance = new ReverseWordsInAString_151();
//        String result = instance.reverseWordsTwo("  hello world!  ");
//        String result = instance.reverseWords("the sky is blue");
        String result = instance.reverseWordsThree("the sky is blue");
        System.out.println(result);
    }

}
