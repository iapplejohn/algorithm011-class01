学习笔记

### 并查集
#### 适用场景
1. 组团、配对
2. 是否属于同个群组？

#### 基本操作
1. makeSet(s): 建立一个并查集，其中包含s个单元集合
2. unionSet(x, y): 把元素x和元素y所在的集合合并，要求x和y所在的集合不相交，如果相交则不合并
3. find(x): 找到元素x所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合，
只要将他们各自的代表比较一下就可以了

### 剪枝
#### 定义
1. 状态树较多，对于不满足条件的分支，可以去除，减少性能损耗

#### 场景
1. 生成括号问题

### 双向 BFS
#### 定义
1. 分别从头和尾进行广度优先搜索，在中间相遇，即为找到目标解

#### 为什么需要双向？
1. 提升性能

#### 模板 TODO

### 启发式搜索
1. 基于 BFS，使用优先队列实现一定的智能。
2. 估价函数：返回一个非负实数，评价哪些结点最有希望是我们要找的结点，可以认为从结点n到目标结点路径的估计成本。

### AVL 树和红黑树
#### AVL 树
##### 为什么会有 AVL 树
1. 普通的二叉搜索树，极端情况下会退化为链表，搜素时间复杂度降为 O(n)
2. 为了解决上面这个问题，出现了 AVL 树：插入和删除元素时，维护树的平衡

##### AVL 树特性
1. Balance Factor（平衡因子） 
  - 左子树的高度减去右子树的高度（有时相反）。balance factor = {-1, 0, 1}
2. 通过旋转操作来进行平衡（四种），子树形态：
  - 右右子树 -> 左旋
  - 左左子树 -> 右旋
  - 左右子树 -> 左右旋
  - 右左子树 -> 右左旋
3. 带有子树的旋转 https://zhuanlan.zhihu.com/p/63272157
  - 左旋
  - 右旋

##### AVL 总结
1. 平衡二叉搜素树（严格）
2. 每个结点存 balance factor = {-1, 0, 1}

不足：结点需要存储额外信息，且调整次数频繁

#### 红黑树
##### 定义
> 红黑树是一种近似平衡的二叉搜索树（Binary Search Tree），它能够确保任何一个结点的左右子树的高度差小于两倍。具体来说，红黑树是满足如下条件的二叉搜索树：
1. 每个结点要么是红色，要么是黑色
2. 根结点是黑色
3. 每个叶结点（NIL结点，空结点）是黑色的。
4. 不能有相邻接的两个红色结点
5. 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点

##### 为什么会有红黑树
1. AVL 树严格要求平衡，但维护平衡的开销也大，实际很多场景不需要这么严格，所以出现了近似平衡的红黑树

##### AVL 树 vs 红黑树
1. 查询速度：AVL 更优
2. 插入和删除：红黑树 更优
3. 存储：红黑树存储占用 更少
4. 场景：红黑树 - 内存操作（高级语言库）；AVL - DB