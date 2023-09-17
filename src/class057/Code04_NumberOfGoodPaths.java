package class057;

import java.util.Arrays;

// 好路径的数目
// 给你一棵 n 个节点的树（连通无向无环的图）
// 节点编号从0到n-1，且恰好有n-1条边
// 给你一个长度为 n 下标从 0 开始的整数数组 vals
// 分别表示每个节点的值。同时给你一个二维整数数组 edges
// 其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条 无向 边
// 一条 好路径 需要满足以下条件：
// 开始节点和结束节点的值 相同
// 开始节点和结束节点中间的所有节点值都 小于等于 开始节点的值
//（也就是说开始节点的值应该是路径上所有节点的最大值）
// 请你返回不同好路径的数目
// 注意，一条路径和它反向的路径算作 同一 路径
// 比方说， 0 -> 1 与 1 -> 0 视为同一条路径。单个节点也视为一条合法路径
// 测试链接 : https://leetcode.cn/problems/number-of-good-paths/
public class Code04_NumberOfGoodPaths {

	public static int MAXN = 30001;

	// 需要保证集合中，代表节点的值，一定是整个集合的最大值
	public static int[] father = new int[MAXN];

	// 集合中最大值的次数，也就是 集合中代表节点的值有几个
	public static int[] cnt = new int[MAXN];

	public static void build(int n) {
		for (int i = 0; i < n; i++) {
			father[i] = i;
			cnt[i] = 1;
		}
	}

	// 这个并查集的优化只来自扁平化
	public static int find(int i) {
		if (i != father[i]) {
			father[i] = find(father[i]);
		}
		return father[i];
	}

	// 核心！
	// 注意以下的写法
	// 谁的值大，谁做代表节点
	// 同时注意cnt数组的更新
	public static int union(int x, int y, int[] vals) {
		int fx = find(x);
		int fy = find(y);
		int path = 0;
		if (vals[fx] > vals[fy]) {
			father[fy] = fx;
		} else if (vals[fx] < vals[fy]) {
			father[fx] = fy;
		} else {
			path = cnt[fx] * cnt[fy];
			father[fy] = fx;
			cnt[fx] += cnt[fy];
		}
		return path;
	}

	public static int numberOfGoodPaths(int[] vals, int[][] edges) {
		int n = vals.length;
		build(n);
		int ans = n;
		// 核心排序！
		// 处理边的时候，依次从小节点往大节点处理
		// 课上重点讲这个排序
		Arrays.sort(edges, (e1, e2) -> (Math.max(vals[e1[0]], vals[e1[1]]) - Math.max(vals[e2[0]], vals[e2[1]])));
		for (int[] edge : edges) {
			ans += union(edge[0], edge[1], vals);
		}
		return ans;
	}

}
