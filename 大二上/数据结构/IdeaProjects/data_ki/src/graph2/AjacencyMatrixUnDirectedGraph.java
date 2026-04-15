package graph2;

import graph.GraphKind;

import java.util.Iterator;

//邻接矩阵 无向图  边只存储一次，存在编号大的那条边
public class AjacencyMatrixUnDirectedGraph<T> implements IGraph<T> {
	public final static GraphKind graghKind = GraphKind.UnDirectedGraph;
	private Object[] vertices;
	private int[][] edges;
	private int e;
	private final int n;

	public AjacencyMatrixUnDirectedGraph(T[] data) {
		n = data.length;
		vertices = new Object[n];
		System.arraycopy(data, 0, vertices, 0, n);
		edges = new int[n][]; // 利用了java将各数组元素的初值设置为0，0表示无边,使用下三角矩阵
		for (int i = 0; i < n; ++i)
			edges[i] = new int[i + 1];// 下三角矩阵
	}

	private void rangeCheck(int v) {
		if (v < 0 || v >= n)
			throw new IndexOutOfBoundsException();
	}

	public GraphKind getGraphKind() {
		return graghKind;
	}

	public int numberOfVertices() {
		return n;
	}

	public int numberOfEdges() {
		return e;
	}

	public int index(T x) {
		for (int i = 0; i < n; ++i) {
			if (vertices[i].equals(x))
				return i;
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public T value(int v) {
		rangeCheck(v);
		return (T) vertices[v];
	}

	public int inDegree(int v) {
		throw new UnsupportedOperationException();
	}

	public int outDegree(int v) {
		throw new UnsupportedOperationException();
	}

	public int degree(int v) {
		rangeCheck(v);
		int count = 0;
		for (int i = 0; i <= v; ++i)
			count += edges[v][i];
		for (int i = v + 1; i < n; ++i)
			count += edges[i][v];
		return count;
	}

	public boolean addEdge(int u, int v) {
		rangeCheck(u);
		rangeCheck(v);
		if (v > u) {// 保证 u > v
			int tmp = u;
			u = v;
			v = tmp;
		}
		if (edges[u][v] == 0) {
			edges[u][v] = 1;
			++e;
			return true;
		}
		return false;
	}

	public boolean removeEdge(int u, int v) {
		rangeCheck(u);
		rangeCheck(v);
		if (v > u) {// 保证 u > v, 交换2个整数的另外的写法
			v = v ^ u;
			u = v ^ u;
			v = v ^ u;
		}
		if (edges[u][v] != 0) {
			edges[u][v] = 0;
			--e;
			return true;
		}
		return false;
	}

	public void addWeightedEdge(int u, int v, double w) {
		throw new UnsupportedOperationException();
	}

	public double getWeight(int u, int v) {
		throw new UnsupportedOperationException();
	}

	public Iterator<Integer> iterator(int v) {
		rangeCheck(v);
		return new Itr(v);
	}

	private class Itr implements Iterator<Integer> {
		private int vertex;
		private int curPos;

		public Itr(int v) {
			vertex = v;
		}

		public boolean hasNext() {
			for (; curPos <= vertex; ++curPos) {
				if (edges[vertex][curPos] != 0)
					return true;
			}
			for (; curPos < n; ++curPos) {
				if (edges[curPos][vertex] != 0)
					break;
			}
			return curPos == n ? false : true;
		}

		public Integer next() {
			return curPos++;
		}
	}

	public static void main(String[] args) {
		Character[] data = { 'a', 'b', 'c', 'd', 'e', 'f' };
		AjacencyMatrixUnDirectedGraph<Character> graph = new AjacencyMatrixUnDirectedGraph<>(data);

		System.out.println("nodes=" + graph.numberOfVertices());
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(1, 4);
		graph.addEdge(5, 1);
		graph.addEdge(2, 3);
		graph.addEdge(3, 5);
		graph.addEdge(4, 3);
		graph.addEdge(4, 5);
		// graph.addEdge(3, 3);

		System.out.println("edges=" + graph.numberOfEdges());

		System.out.println("degree of 1=" + graph.degree(1));
		System.out.println("degree of 3=" + graph.degree(3));

		graph.removeEdge(4, 1);

		System.out.println(graph.numberOfEdges());

		System.out.println("degree of 1=" + graph.degree(1));

		System.out.println("value of 4=" + graph.value(4));

		System.out.println("---------");
		Iterator<Integer> it = graph.iterator(5);
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
