package Week_04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 127. 单词接龙
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: 0
 *
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/26
 */
public class WordLadder_127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Clarification: 最短转换序列，每次改变一个字母，中间单词必须在字典中，所有单词长度相同，均为小写字母
        // 方案1：广度优先搜索
        // 将字典中的单词的每个字母，转成*号的形式，放到 Map 中
        // 然后从起始单词开始，每个字母变成*号，进行匹配

        // 所有单词长度相同
        int len = beginWord.length();

        // 单词（其中一位为*号）-> 匹配的单词列表
        int capacity = wordList.size();
        Map<String, List<String>> patternWords = new HashMap<>(capacity);
        for (String word : wordList) {
            for (int i = 0; i < len; i++) {
                String pattern = word.substring(0, i) + '*' + word.substring(i + 1);
                List<String> list = patternWords.computeIfAbsent(pattern, k -> new ArrayList<>());
                list.add(word);
            }
        }

        // 记录访问过的单词
        Map<String, Boolean> visited = new HashMap<>(capacity);

        // BFS，使用队列辅助
        LinkedList<Pair<String, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(beginWord, 1));

        while (!queue.isEmpty()) {
            Pair<String, Integer> pair = queue.poll();
            String word = pair.k;
            Integer level = pair.v;

            // 对当前单词每个位置转换为 * 处理
            for (int j = 0; j < len; j++) {
                String pattern = word.substring(0, j) + '*' + word.substring(j + 1);

                // 找到匹配的单词列表
                List<String> matchWords = patternWords.get(pattern);
                if (matchWords != null) {
                    for (String matchWord : matchWords) {
                        if (matchWord.equals(endWord)) {
                            return level + 1;
                        }

                        // 未访问过
                        if (!visited.containsKey(matchWord)) {
                            visited.put(matchWord, Boolean.TRUE);

                            // 继续匹配
                            queue.offer(new Pair<>(matchWord, level + 1));
                        }
                    }
                }
            }
        }

        return 0;
    }

    public int ladderLengthTwo(String beginWord, String endWord, List<String> wordList) {
        // 方案2：双端广度优先搜索

        if (!wordList.contains(endWord)) {
            return 0;
        }

        // 所有单词长度相同
        int len = beginWord.length();

        // 遍历字典中的单词，依次将每个字符转成 * 号，映射匹配的单词列表
        int capacity = wordList.size();
        Map<String, List<String>> patternWords = new HashMap<>(capacity);
        for (String word : wordList) {
            for (int i = 0; i < len; i++) {
                String pattern = word.substring(0, i) + '*' + word.substring(i + 1);
                List<String> list = patternWords.computeIfAbsent(pattern, k -> new ArrayList<>());
                list.add(word);
            }
        }

        // 记录已访问过的单词
        Map<String, Integer> beginVisited = new HashMap<>(capacity);
        Map<String, Integer> endVisited = new HashMap<>(capacity);

        // 分别从头尾开始广度优先搜索，使用队列辅助
        LinkedList<Pair<String, Integer>> beginQueue = new LinkedList<>();
        LinkedList<Pair<String, Integer>> endQueue = new LinkedList<>();

        // 初始化队列元素
        beginQueue.offer(new Pair<>(beginWord, 1));
        endQueue.offer(new Pair<>(endWord, 1));
        beginVisited.put(beginWord, 1);
        endVisited.put(endWord, 1);

        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            int res1 = scanWord(beginQueue, beginVisited, endVisited, patternWords);
            if (res1 > -1) {
                return res1;
            }

            int res2 = scanWord(endQueue, endVisited, beginVisited, patternWords);
            if (res2 > -1) {
                return res2;
            }
        }

        return 0;
    }

    private int scanWord(LinkedList<Pair<String, Integer>> queue,
        Map<String, Integer> visited, Map<String, Integer> otherVisited,
        Map<String, List<String>> patternWords) {

        Pair<String, Integer> node = queue.poll();
        String word = node.k;
        Integer level = node.v;

        // 依次将每个字符转成 * 号，从字典中查找
        for (int i = 0; i < word.length(); i++) {
            String pattern = word.substring(0, i) + '*' + word.substring(i + 1);

            List<String> matchWords = patternWords.get(pattern);
            if (matchWords != null) {
                for (String matchWord : matchWords) {
                    if (otherVisited.containsKey(matchWord)) {
                        return level + otherVisited.get(matchWord);
                    }

                    if (!visited.containsKey(matchWord)) {
                        visited.put(matchWord, level + 1);

                        queue.offer(new Pair<>(matchWord, level + 1));
                    }
                }
            }
        }

        return -1;
    }
}
