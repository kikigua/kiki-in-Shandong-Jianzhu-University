package graph1;

import java.util.Scanner;

public class Traffic_Consultation<T> {
	private IGraph<T> graph;// 使用的接口
	int n;

	public Traffic_Consultation(IGraph<T> graph) {// 载入类，初始化
		this.graph = graph;
		n = graph.numberOfVertices();// 图中有几个顶点
	}

	public void floyd(int op) {
		if (graph.getGraphKind() != GraphKind.WeightedUnDirectedGraph) // 若图的种类不是加权无向图
			return;
		double[][] distance = new double[n][n];// distance指的是两个城市间的距离
		int[][] path = new int[n][n];// path指的是两个城市间的最短路径中经过的那些城市

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; j++) {
				distance[i][j] = graph.getWeight(i, j);// 初始化
				path[i][j] = -1;// 两个城市在最短路径的前提下，中间不存在其他城市时
			}
		}

		for (int i = 0; i < n; ++i)
			distance[i][i] = 0.0;// 初始化自己到自己时

		// 基于动态规划->floyd：n^3
		for (int k = 0; k < n; ++k) {// 枚举中间城市
			for (int i = 0; i < n; ++i) {// 起始城市
				for (int j = 0; j < n; ++j) {// 结尾城市
					double tmp = distance[i][k] + distance[k][j];
					if (tmp < distance[i][j]) {
						distance[i][j] = tmp;
						path[i][j] = k;// 若tmp变小且小于i—>j的distance，则把i—>j的distance更新，再把i—>j的path更新为k
					}
				}
			}
		}
		// 输出路径
		int[] itoj = new int[n];// 该数组存的是最短路径
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				System.out.print(graph.getvertices(i) + "—>" + graph.getvertices(j) + "路径:");// 城市的名字用getvertices()获取
				int pos = rfindpath(itoj, 0, i, j, path);// 调用递归算法 itoj:存的最短路径，0:初始值，i:起始城市，j:结束城市
				for (int k = 0; k < pos; k++) {
					if (k != pos - 1)
						System.out.print(graph.getvertices(itoj[k]) + "->");
					else
						System.out.print(graph.getvertices(itoj[k]));
				} // 输出最短路径
				if (op == 1)
					System.out.println("  最短路径距离：" + distance[i][j]);
				else if (op == 2)
					System.out.println("  最低花费：" + distance[i][j]);
				else
					System.out.println("  最少时间：" + distance[i][j]);
			}
		}
	}

	private int rfindpath(int itojpath[], int pos, int i, int j, int path[][]) {
		int k = path[i][j];
		if (k == -1) {// -1时无中间城市
			itojpath[pos++] = i;
			itojpath[pos++] = j;
		} else {
			int mid = rfindpath(itojpath, pos, i, k, path);// 二分思想
			pos = rfindpath(itojpath, --mid, k, j, path);// --mid：覆盖前面的k，否则有重复的顶点
		}
		return pos;
	}

	public static void main(String[] args) {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		Character[] weightedData = { 'A', 'B', 'C', 'D', 'E' };// 城市名称
		// graph1:
		IGraph<Character> Graph1 = new LinkedListUnSymmetricWeightedUnDirectedGraph<>(weightedData);
		IGraph<Character> Graph2 = new LinkedListUnSymmetricWeightedUnDirectedGraph<>(weightedData);
		IGraph<Character> Graph3 = new LinkedListUnSymmetricWeightedUnDirectedGraph<>(weightedData);

		while (flag) {
			System.out.println("请输入你要进行的操作：");
			System.out.println("1、查询最短路径");
			System.out.println("2、查询最低花费");
			System.out.println("3、查询最少时间");
			System.out.println("4、结束");
			// switch语句选择目录
			int op = sc.nextInt();
			switch (op) {
			case 1:
				Graph1.addWeightedEdge(0, 1, 20.0);
				Graph1.addWeightedEdge(0, 2, 7.0);
				Graph1.addWeightedEdge(1, 2, 3.0);
				Graph1.addWeightedEdge(1, 3, 10.0);
				Graph1.addWeightedEdge(1, 4, 12.0);
				Graph1.addWeightedEdge(2, 3, 15.0);
				Graph1.addWeightedEdge(2, 4, 9.0);
				Traffic_Consultation<Character> weightedPathAlg = new Traffic_Consultation<>(Graph1);
				weightedPathAlg.floyd(1);// 函数传入到op
				break;
			case 2:
				Graph2.addWeightedEdge(0, 1, 100.0);
				Graph2.addWeightedEdge(0, 2, 30.0);
				Graph2.addWeightedEdge(1, 2, 10.0);
				Graph2.addWeightedEdge(1, 3, 15.0);
				Graph2.addWeightedEdge(1, 4, 17.0);
				Graph2.addWeightedEdge(2, 3, 35.0);
				Graph2.addWeightedEdge(2, 4, 22.0);
				Traffic_Consultation<Character> weightedPathAlg1 = new Traffic_Consultation<>(Graph2);
				weightedPathAlg1.floyd(2);
				break;
			case 3:
				Graph3.addWeightedEdge(0, 1, 6.0);
				Graph3.addWeightedEdge(0, 2, 5.0);
				Graph3.addWeightedEdge(1, 2, 1.0);
				Graph3.addWeightedEdge(1, 3, 2.0);
				Graph3.addWeightedEdge(1, 4, 7.0);
				Graph3.addWeightedEdge(2, 3, 6.0);
				Graph3.addWeightedEdge(2, 4, 4.0);
				Traffic_Consultation<Character> weightedPathAlg2 = new Traffic_Consultation<>(Graph3);
				weightedPathAlg2.floyd(3);
				break;
			case 4:
				flag = false;
				System.out.println("查询结束");
				break;
			default:
				System.out.println("输入有错误，请重新输入！");
				break;
			}
		}

	}
}
