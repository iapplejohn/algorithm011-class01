package Week_03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 51. N皇后
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 *
 * 提示：
 *
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步，可进可退。（引用自 百度百科 - 皇后 ）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/30
 */
public class NQueens_51 {

    private Set<Integer> cols = new HashSet<>();
    private Set<Integer> pies = new HashSet<>();
    private Set<Integer> nas = new HashSet<>();

    List<List<Integer>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // Clarification: n 皇后问题，返回所有不同的解决方案
        // 方案1: 回溯
        // n 皇后，每个皇后占一行（同一行就会相互攻击），所以，针对每个皇后，要在不同列上试
        // 找到一个位置后，再试后一个皇后（行 + 1，利用递归），直到行索引 = n，记录结果，该分支结束
        // 需要找到所有解，如何处理？回溯
        // 前面皇后放好后，如何标记攻击范围？利用Set：列集合、撇集合、捺集合（回溯时需要清除）
        // 撇和纳都是斜线，如何标记? 撇 row + col; 捺 row - col
        // 递归参数: n: 行数,

        // 每个皇后对应的列索引
        List<Integer> colList = new ArrayList<>();

        dfs(n, 0, colList);

        List<List<String>> ans = new ArrayList<>();

        buildResult(n, ans);

        return ans;
    }

    private void dfs(int n, int row, List<Integer> colList) {
        // terminator
        if (row == n) {
            result.add(colList);
            return;
        }

        // process current logic
        for (int col = 0; col < n; col++) {
            // 在之前皇后的攻击范围内：列集合、撇集合、捺集合
            if (cols.contains(col) || pies.contains(row + col) || nas.contains(row - col)) {
                continue;
            }

            // 添加到攻击范围内
            cols.add(col);
            pies.add(row + col);
            nas.add(row - col);

            // drill down
            dfs(n, row + 1, colList);

            // reverse the current state
            cols.remove(col);
            pies.remove(row + col);
            nas.remove(row - col);
        }
    }

    private void buildResult(int n, List<List<String>> ans) {
        for (List<Integer> colStates : result) {
            List<String> an = new ArrayList<>(n);
            for (Integer colState : colStates) {
                StringBuilder builder = new StringBuilder(n);
                for (int i = 0; i < colState; i++) {
                    builder.append('.');
                }
                builder.append('Q');
                for (int j = colState + 1; j < n; j++) {
                    builder.append('.');
                }
                an.add(builder.toString());
            }
            ans.add(an);
        }
    }
}
