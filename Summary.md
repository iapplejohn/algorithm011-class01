# 毕业总结

## 感受
1. 刚开始觉得10个星期太长，现在感觉好快，很多题目还没做，就要毕业了，有点不舍。
2. 通过这次算法训练营的培训，不仅提升了算法水平，熟悉常见算法解题思路，增强了信心。
3. 更重要的是老师对思维上的指导：
  - 人脑思维模式，适合于简单的场景，以及初始情况下的推导，不适合复杂和大批量的计算
  - 计算机能做的不过是：条件判断、循环、递归，所有其他的算法，都是这3个基本的组合，所以要找最近相似性
  - 如果半个小时都想不出如何解题，直接看答案
  - 看官方和其他优秀的题解，以及国际站高票回答
  - 五毒神掌（根据遗忘曲线）
4. 算法能提升我们写业务代码的水平，拓宽思路
5. 算法和人类社会的很多场景相似 

## 未来
1. 继续刷题
2. 多总结

## 技术总结
### 深度优先搜索
> 对每一个节点，逐层下探到每层的第一个子节点，直到最后一层，然后再逐层返回，处理其它节点

#### 实现方案
1. 利用递归，栈由计算机帮我们维护
2. 利用栈辅助，手动维护


### 广度优先搜索
> 依次处理下一层的所有节点后，再处理下下一层，像水波纹一样，一圈一圈荡漾开来

#### 实现方案
1. 利用队列辅助，手动维护

### 贪心算法
> 局部的最优解就是全局的最优解

#### 思路
1. 从后往前
2. 从局部开始贪心

### 二分查找
1. 适用于：
  - 有序（旋转）查找一个元素或最大最小值
  - 双指针查找目标值

### Trie 树（也叫字典树、前缀树）
#### 通用模板
```
class Trie {
  private Node root;

  private class Node {
    private TreeMap<Character, Node> next;
    private boolean isWord;

    public Node(boolean isWord) {
      this.next = new TreeMap<>();
      this.isWord = isWord;
    }
    
    public Node() {
      this(false);
    }
  }

  public Trie() {
    root = new Node();
  }

  public void insert(String word) {
    Node cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      cur.next.putIfAbsent(c, new Node());
      cur = cur.next.get(c);
    }
    if (!cur.isWord) {
      cur.isWord = true;
    }
  }
  
  public boolean search(String word) {
    Node cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (!cur.next.containsKey(c)) {
        return false;
      } else {
        cur = cur.next.get(c);
      }
    }
    return cur.isWord;
  }
}
```

#### 26个字母模板
```
class Trie {

  class TrieNode {
    private boolean isEnd;
    TrieNode[] next;
    
    public TrieNode() {
      isEnd = false;
      next = new TrieNode[26];
    }
  }
  
  private TrieNode root;
  
  public Trie() {
    root = new TrieNode();
  }

  public void insert(String word) {
    TrieNode node = root;
    for (char c : word.toCharArray()) {
      if (node.next[c - 'a'] == null) {
        node.next[c - 'a'] = new TrieNode();
      }
      node = node.next[c - 'a'];
    }
    node.isEnd = true;
  }
  
  public boolean search(String word) {
    TrieNode node = root;
    for (char c : word.toCharArray()) {
      node = node.next[c - 'a'];
      if (node == null) {
        return false;
      }
    }
    return node.isEnd;
  }
```

### 并查集
#### 代码模板
```
class UnionFind {
  private int count = 0;
  private int[] parent;
  
  public UnionFind(int n) {
    count = n;
    parent = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }
  }
  
  public int find(int p) {
    while (p != parent[p]) {
      parent[p] = parent[parent[p]];
      p = parent[p];
    }
    return p;
  }
  
  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) return;
    parent[rootP] = rootQ;
    count--;
  }
} 
```

### Bloom Filter
1. 作为存储前的一道过滤器，减轻查询存储的压力
2. 采用二进制位，存的是哈希，没有存储值
3. 在过滤器中找到，不一定存在存储中；没找到，一定不存在存储中

### LRU Cache
1. Hash Table + Double LinkedList
2. O(1) 查询
   O(1) 修改、更新
3. LRU (Least Recently Used)
4. 其他
  - FIFO
  - FILO
  - LRU
  - LFU
  - LFRU (Least Frequently Recently Used)
  - ARC (Adaptive Replacement Cache)
5. https://leetcode-cn.com/problems/lru-cache-lcci/

### 位运算
1. 将 x 最右边的 n 位清零: x & (~0 << n)
2. 获取 x 的第 n 位值 (0 或者 1): (x >> n) & 1
3. 获取 x 的第 n 位的幂值: x & (1 << n)
4. 仅将第 n 位置为 1: x | (1 << n)
5. 仅将第 n 位置为 0: x & (~(1 << n))

#### 实战位运算
1. 判断奇偶
  - x % 2 == 1 -> (x & 1) == 1
  - x % 2 == 0 -> (x & 1) == 0
2. 除以2
  - x / 2 -> x >> 1
3. 清零最低位的 1
  - x & (x - 1): 如 x = 01101000, x - 1= 01100111
4. 得到最低位的 1
  - x & -x
5. 结果为0
  - x & ~x = 0

### N 皇后
```
class Solution {
  private int size;
  private int count;

  private void solve(int row, int ld, int rd) {
    if (row == size) {
      count++;
      return;
    }
    int pos = size & (~(row | ld | rd));
    while (pos != 0) {
      int p = pos & (-pos);
      pos -= p; // pos = pos & (pos - 1); 去掉最低位的 1
      solve(row | p, (ld | p) << 1, (rd | p) >> 1);
    }
  }
  
  public int totalNQueens(int n) {
    count = 0;
    size = (1 << n) = 1;
    solve(0, 0, 0);
    return count;
  }
}
```

### 高级二叉搜索树
#### AVL，2-3树，红黑树，B树，B+树
1. AVL 是完全平衡二叉树，层级相差不超过1
2. 红黑树是近似平衡二叉树，层级相差不超过2倍
3. B树有很多叉，主要是二叉的层级太高，不适合存储和查询
4. B+树在二叉树基础上做了改进：
  - 非叶子节点不存储数据，这样容纳的节点越多，提升性能（一般都是作为索引，缓存在内存中）
  - 兄弟节点连起来，方便全表扫描或范围查询

#### 参考
1. https://juejin.im/post/6844903859974848520
2. https://zhuanlan.zhihu.com/p/27700617

### 分治
#### 分治代码模板
```
def divide_conquer(problem, param1, param2, ...):
  # recursion terminator
  if problem is None:
    print_result
    return
  
  # prepare data
  data = prepare_data(problem)
  subproblems = split_problem(problem, data)
  
  # conquer subproblems
  subresult1 = self.divide_conquer(subproblems[0], p1, ...)
  subresult2 = self.divide_conquer(subproblems[1], p1, ...)
  subresult3 = self.divide_conquer(subproblems[2], p1, ...)

  # process and generate the final result
  result = process_result(subresult1, subresult2, subresult3, ...)
  
  # revert the current level states
```

### 动态规划
1. "Simplifying a complicated problem by breaking it down into simpler sub-problems"
  (in a recursive manner)

2. Divide & Conquer + Optimal substructure
  分治 + 最优子结构
3. 顺推形式: 动态递推

#### DP 模板
```
function DP():
  dp = [][] # 二维情况
  for i = 0 .. m {
    for j = 0 .. n {
      dp[i][j] = _function(dp[i'][j'] ...)
    }
  }
  
  return dp[m][n]
```

#### 关键点
> 动态规划 和 递归或者分治 没有根本上的区别（关键看有无最优的子结构）

> 拥有共性：找到重复子问题

> 差异性：最优子结构、中途可以淘汰次优解

#### LC 70. Climbing Stairs
```
Recursion formula:
f(n) = f(n - 1) + f(n - 2)
f(1) = 1, f(0) = 1
```

```
def f(n):
  if n <= 1: return 1
  return f(n - 1) + f(n - 2)
```

```
def f(n):
  if n <= 1: return 1
  if n not in mem:
    mem[n] = f(n - 1) + f(n - 2)
  return mem[n]
```

```
def f(n):
  dp = [1] * (n + 1)
  for i in range(2, n + 1):
    dp[i] = dp[i - 1] + dp[i - 2]
  return dp[n] 
```

```
def f(n):
  dp1, dp2 = 1, 1
  for i in range(2, n + 1):
    dp2, dp1 = dp1 + dp2, dp2
  return dp2
```

#### LC 62. Unique Paths
```
Recursion formula
f(x, y) = f(x - 1, y) + f(x, y - 1)
f(0, 0) = 1, out of board = 0
```

```
def f(x, y):
  if x <= 0 or y <= 0: return 0
  if x == 1 and y == 1: return 1
  return f(x - 1, y) + f(x, y - 1)
```

```
def f(x, y);
  if x <= 0 or y <= 0: return 0
  if x == 1 and y == 1: return 1
  if (x, y) not in mem:
    mem[(x, y)] = f(x - 1, y) + f(x, y - 1)
  return mem[(x, y)]
```

```
def f(x, y):
  dp = [[0] * (m + 1) for _ in range(n + 1)]
  dp[1][1] = 1
  for i in range(1, y + 1):
    for j in range(1, x + 1):
      dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
  return dp[y][x]
```

#### LC 64. Minimum Path Sum
```
dp[i][j] := minPath(A[0-i][0->j])
dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + A[i][j]
```

#### LC 198 House Robber
```
dp[i] := max $ of robbing A[0->i]
dp[i] = max(dp[i - 2] + A[i], dp[i - 1])
```

```
dp[i][0] := max $ of robbing A[0->i] w/o A[i]
dp[i][1] := max $ of robbing A[0->i] w/ A[i]
dp[i][0] = max(dp[i - 1][0], dp[i - 2][1]);
dp[i][1] = max(dp[i - 2][0], dp[i - 2][1]) + nums[i - 1];
```

#### 股票买卖
```
dp[i][k][0 or 1]
0 <= i <= n - 1, 1 <= k <= K
n 为天数，大 K 为最多交易数
此问题共 n * K * 2 中状态，全部穷举就能搞定。

for 0 <= i < n:
  for 1 <= k <= K:
    for s in {0, 1}:
      dp[i][k][s] = max(buy, sell, rest)
```
https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/

```
dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
```
解释：今天我没有持有股票，有两种可能：

- 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
- 要么是我昨天持有股票，但是我今天 sell 了，所以我今天没有持有股票了。

```
dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
```

解释：今天我持有股票，有两种可能：
- 要么我昨天就持有股票，然后今天选择 rest，所以我今天还持有着股票；
- 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。

```
base case:
dp[-1][k][0] = dp[i][0][0] = 0
dp[-1][k][1] = dp[i][0][1] = -infinity

状态转移方程:
dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
```