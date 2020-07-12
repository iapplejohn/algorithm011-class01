学习笔记

### 回溯
#### 为什么要回溯
1. 减少中间变量的产生，共用一个路径变量
2. 由深层到浅层，变化少

#### 使用场景
1. 组合
2. 全排列
3. 验证搜索树

### 递归收敛
> 递归代码中，调了自己多次，结果很可能像树一样发散开来，如果获取结果？

1. 返回列表，作为实例变量，中间值作为递归方法参数，在某些条件满足（如terminator）时，存储结果
```
private List<Integer> res = new ArrayList<>();

public void recursive(int level, int max, String path) {
    // terminator
    if (level == max) {
        res.add(new ArrayList<>(path));
        return;
    }

    // process current
    path += '(';

    // drill down
    recursive(level + 1, max, path);

    // reverse current
}
```

II. 返回集合，作为方法参数；中间值作为递归方法参数，在某些条件满足（如terminator）时，存储结果
```
public void recursive(int level, int max, String path, List<Integer> res) {
    // terminator
    if (level == max) {
        res.add(new ArrayList<>(path));
        return;
    }

    // process current
    path += '(';

    // drill down
    recursive(level + 1, max, path);

    // reverse current
}
```

III. 递归函数定义返回值，在递归函数体中，聚合这些返回值
> 在递归模板的基础上，drill down（多次调用函数）后面加了聚合逻辑

```
private TreeNode ans;

public boolean recursive(TreeNode node, TreeNode p, TreeNode q) {
    // terminator
    if (level == max) {
        result.add(new ArrayList<>(path));
        return;
    }

    // process current

    // drill down
    boolean lSon = recursive(node.left, p, q);
    boolean rSon = recursive(node.right, p, q);

    // aggregation
    if (lSon && rSon) {
        ans = node;
    }

    // reverse current

    return lSon || rSon;
}
```