package Week_07;

/**
 * 547. 朋友圈
 *
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 *
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 * 示例 1:
 *
 * 输入:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 * 示例 2:
 *
 * 输入:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * 注意：
 *
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/friend-circles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/9
 */
public class FriendCircles_547 {

    public int findCircleNum(int[][] M) {
        // Clarification: 计算朋友圈总数，有关联的算一个
        // 方案1: DFS，遍历每个学生，如果和其他学生是朋友则加到一个圈子，记录该关系是否已访问过

        int n = M.length, res = 0;
        boolean[] visited = new boolean[n];

        // 遍历每个学生
        for (int i = 0; i < n; i++) {
            // 该学生未访问过，则朋友圈数量加1，并递归处理关联学生的关系
            if (!visited[i]) {
                dfs(M, visited, i);
                res++;
            }
        }

        return res;
    }

    /**
     * 递归处理关联学生的关系
     *
     * @param m n * n 矩阵，表示学生之间的朋友关系
     * @param visited 关系是否已访问过
     * @param i 当前是第几个学生，也就是矩阵的第几行（从0开始）
     */
    private void dfs(int[][] m, boolean[] visited, int i) {
        // 针对该学生，和所有其他学生的关系
        // terminator: 遍历完成 + visited
        for (int j = 0; j < m.length; j++) {
            if (m[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(m, visited, j);
            }
        }
    }

    public int findCircleNumTwo(int[][] M) {
        // 方案2: 并查集 TODO
        return 0;
    }
}
