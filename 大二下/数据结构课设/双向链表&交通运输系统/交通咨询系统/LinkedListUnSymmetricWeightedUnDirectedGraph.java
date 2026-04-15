package graph1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListUnSymmetricWeightedUnDirectedGraph<T> implements IGraph<T> {
	public final static GraphKind graghKind = GraphKind.WeightedUnDirectedGraph;
	public final static double noEdge = Double.POSITIVE_INFINITY;
	private Object[] vertices;
	private ArrayList<LinkedList<Pair>> edges;// 表套表， 也可以使用数组
	private int e;// 边
	private final int n;// 顶点个数
	private final Pair pair = new Pair();// 用于链表的indexOf和remove

	private static class Pair {
		int vertex;// 邻接点
		double weight;// 权重

		Pair(int v, double w) {
			vertex = v;
			weight = w;
		}

		Pair() {

		}

		Pair setNode(int u) {
			vertex = u;
			return this;
		}

		public boolean equals(Object o) {// List的indexOf、remove要使用
			if (o instanceof Pair) // 左边是对象，右边是类；当对象是右边类或子类所创建对象时，返回true；否则，返回false。
				return this.vertex == ((Pair) o).vertex;
			return false;
		}
	}

	public LinkedListUnSymmetricWeightedUnDirectedGraph(T[] data) {
		n = data.length;
		vertices = new Object[n];
		System.arraycopy(data, 0, vertices, 0, n);
		edges = new ArrayList<>(n);
		for (int i = 0; i < n; ++i)
			edges.add(new LinkedList<>());
	}

	private void rangeCheck(int v) {
		if (v < 0 || v >= n)
			throw new IndexOutOfBoundsException();// 索引越界异常
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
		int count = edges.get(v).size();
		for (int i = 0; i < n; i++) {
			if (i == v)
				continue;
			for (Pair pair : edges.get(i)) {
				if (pair.vertex == v) {
					count++;
				}
			}
		}
		return count;

	}

	public boolean addEdge(int u, int v) {
		throw new UnsupportedOperationException();// 请求的方法不被支持的异常
	}

	public boolean removeEdge(int u, int v) {
		rangeCheck(u);
		rangeCheck(v);
		if (edges.get(u).remove(pair.setNode(v))) {
			--e;
			return true;
		} else if (edges.get(v).remove(pair.setNode(u))) {
			--e;
			return true;
		} else
			return false;
	}

	public void addWeightedEdge(int u, int v, double w) {
		rangeCheck(u);
		rangeCheck(v);
		boolean flag = false;
		LinkedList<Pair> list = edges.get(u);
		Iterator<Pair> it = list.iterator();
		while (it.hasNext()) {
			Pair pair = it.next();
			if (pair.vertex == v) {// 边存在，认为修改
				pair.weight = w;
				flag = true;
			}
		}
		if (!flag) {
			LinkedList<Pair> list1 = edges.get(v);
			Iterator<Pair> it1 = list1.iterator();
			while (it1.hasNext()) {
				Pair pair = it1.next();
				if (pair.vertex == u) {// 边存在，认为修改
					pair.weight = w;
					flag = true;
				}
			}
		}
		if (!flag) {
		list.add(new Pair(v, w));
		++e;
	}
	}

	public double getWeight(int u, int v) {
		rangeCheck(u);
		rangeCheck(v);
		Iterator<Pair> it = edges.get(u).iterator();
		while (it.hasNext()) {
			Pair pair = it.next();
			if (pair.vertex == v)
				return pair.weight;
		}
		Iterator<Pair> its = edges.get(v).iterator();
		while (its.hasNext()) {
			Pair pair = its.next();
			if (pair.vertex == u)
				return pair.weight;
		}
		return noEdge;// 无u->v的边，用正无穷大表示
	}

	// 返回城市的编号
	public Object getvertices(int i) {
		return vertices[i];
	}

	public void display(int v) {// 使用增强型的for，比上面的简洁一些
		for (Pair pair : edges.get(v))
			System.out.print(v + " " + pair.vertex + " " + pair.weight + "\n");
		for (int i = 0; i < n; i++) {
			if (i == v)
				continue;
			for (Pair pair : edges.get(i)) {
				if (pair.vertex == v) {
					System.out.print(v + " " + i + " " + pair.weight + "\n");
				}
			}
		}
	}

	public Iterator<Integer> iterator(int v) {
		rangeCheck(v);
		return new Itr(v);
	}

	private class Itr implements Iterator<Integer> {
		private Iterator<Pair> it;
		private boolean flag = true;
		private int count = 0;
		private int v = 0, u = 0;
		public Itr(int v) {
			this.v = v;
			it = edges.get(v).iterator();
		}

		public boolean hasNext() {
			if (!it.hasNext()) {
				if (count == n) {
					return false;
				}
				it = edges.get(count).iterator();
				u = count;
				flag = false;
				if (count == v - 1)
					count = count + 2;
				else
					count++;

			}
			return it.hasNext();

		}

		public Integer next() {
			if(!flag) {
				if (it.next().vertex != v) {
					return -1;
				}
				return u;
			}
			return it.next().vertex;
		}
	}

}
