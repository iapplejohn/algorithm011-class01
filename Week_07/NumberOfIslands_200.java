package Week_07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *  
 *
 * 示例 1:
 *
 * 输入:
 * [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/5
 */
public class NumberOfIslands_200 {

    public int numIslands(char[][] grid) {
        // Clarification: 相邻的陆地构成岛屿，求岛屿数量
        // 方案1: 贪心法 遍历二维网格，发现陆地'1'，周围的陆地都置为'0'，防止统计多次
        // 时间复杂度: O(rc) r 和 c 分别为行数和列数
        // 空间复杂度: O(rc) 最大递归深度

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int res = 0, rows = grid.length, cols = grid[0].length;
        // 遍历二维网格
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    res++;
                    // 处理周围的陆地
                    recursive(grid, r, c);
                }
            }
        }

        return res;
    }

    private void recursive(char[][] grid, int r, int c) {
        // terminator
        int rows = grid.length, cols = grid[0].length;
        if (grid[r][c] == '0') {
            return;
        }

        // process current logic
        grid[r][c] = '0';

        // drill down
        if (r - 1 >= 0 && grid[r - 1][c] == '1') {
            recursive(grid, r - 1, c);
        }
        if (c + 1 <= cols - 1 && grid[r][c + 1] == '1') {
            recursive(grid, r, c + 1);
        }
        if (r + 1 <= rows - 1 && grid[r + 1][c] == '1') {
            recursive(grid, r + 1, c);
        }
        if (c - 1 >= 0 && grid[r][c - 1] == '1') {
            recursive(grid, r, c - 1);
        }

        // reverse current status
    }

    public int numIslandsTwo(char[][] grid) {
        // 方案2: BFS + 队列辅助

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int res = 0, rows = grid.length, cols = grid[0].length;
        // 遍历二维网格
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    res++;
                    grid[r][c] = '0';

                    // 处理周围的陆地，使用队列辅助
                    Queue<Integer> neighbours = new LinkedList<>();
                    neighbours.offer(r * cols + c);

                    while (!neighbours.isEmpty()) {
                        Integer pos = neighbours.poll();
                        int ri = pos / cols;
                        int ci = pos % cols;

                        if (ri - 1 >= 0 && grid[ri - 1][ci] == '1') {
                            neighbours.offer((ri - 1) * cols + ci);
                            grid[ri - 1][ci] = '0';
                        }
                        if (ri + 1 <= rows - 1 && grid[ri + 1][ci] == '1') {
                            neighbours.offer((ri + 1) * cols + ci);
                            grid[ri + 1][ci] = '0';
                        }
                        if (ci - 1 >= 0 && grid[ri][ci - 1] == '1') {
                            neighbours.offer(ri * cols + ci - 1);
                            grid[ri][ci - 1] = '0';
                        }
                        if (ci + 1 <= cols - 1 && grid[ri][ci + 1] == '1') {
                            neighbours.offer(ri * cols + ci + 1);
                            grid[ri][ci + 1] = '0';
                        }
                    }
                }
            }
        }

        return res;
    }

    public int numIslandsThree(char[][] grid) {
        // 并查集 TODO
        return 0;
    }
}
