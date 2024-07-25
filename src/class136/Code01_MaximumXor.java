package class136;

// 最大异或和
// 测试链接 : https://www.luogu.com.cn/problem/P3812
// 提交以下的code，提交时请把类名改成"Main"，可以通过所有测试用例

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code01_MaximumXor {

	public static int MAXN = 51;

	public static int MAXM = 64;

	public static long[] arr = new long[MAXN];

	public static int n, m;

	public static long[] basis = new long[MAXM];

	public static void maxbit() {
		long max = arr[1];
		for (int i = 2; i <= n; i++) {
			max = Math.max(max, arr[i]);
		}
		m = 0;
		while ((max >> (m + 1)) != 0) {
			m++;
		}
	}

	// 计算最大异或和
	public static long compute() {
		for (int i = 1; i <= n; i++) {
			insert(arr[i]);
		}
		long ans = 0;
		for (int i = m; i >= 0; i--) {
			ans = Math.max(ans, ans ^ basis[i]);
		}
		return ans;
	}

	// 普通消元
	// 线性基里插入num
	// 如果线性基增加了，返回true，否则返回false
	public static boolean insert(long num) {
		for (int i = m; i >= 0; i--) {
			if (num >> i == 1) {
				if (basis[i] == 0) {
					basis[i] = num;
					return true;
				}
				num ^= basis[i];
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		n = (int) in.nval;
		for (int i = 1; i <= n; i++) {
			in.nextToken();
			arr[i] = (long) in.nval;
		}
		maxbit();
		compute();
		out.println(compute());
		out.flush();
		out.close();
		br.close();
	}

}