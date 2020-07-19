学习笔记

### 深度优先
1. 深度优先一般是某个分支，探入到最底层，再回到上一层，处理另一个分支
2. 常见的方式：
  - 递归（程序执行时会通过栈来辅助处理现场、操作数、参数等）
  - 栈
3. 代码模板
```
def dfs(node):
  // terminator
  if node in visited:
    # already visited
    return
  
  visited.add(node)

  # process current logic
  # ... # logic here
  
  # drill down
  dfs(node.left)
  dfs(node.right)
```

### 广度优先
1. 广度优先一般是先处理完某一层后，再处理下一层
2. 常见的方式：
  - 队列，循环处理，不断往队列添加元素，然后取出处理，退出条件是队列为空，表示已完毕

### 贪心算法
1. 每一步选择都采取当前状态下最好或最优的选择，从而希望结果是全局最好或最优
2. 适用场景有限
  - 可以解决一些最优化问题，如图中的最小生成树、找零、分饼干等
3. 现实生活中，局部最优往往不是全局最优的，比如学习。

### 二分查找
1. 前提
  - 有序
  - 存在上下界（bounded)
  - 能够通过索引访问
2. 时间复杂度: Olog(nlogn)
3. python模板
  - left + right 可能越界
```
left, right = 0, len(array) - 1
while left <= right:
  mid = (left + right) / 2
  if array[mid] == target:
    # find the target
    break or return result
  elif array[mid] < target:
    left = mid + 1
  else:
    right = mid - 1
```