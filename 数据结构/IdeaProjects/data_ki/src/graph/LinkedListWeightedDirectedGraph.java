package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListWeightedDirectedGraph<T> implements IGraph<T> {
	public final static GraphKind graghKind = GraphKind.WeightedDirectedGraph;
	public final static double noEdge = Double.POSITIVE_INFINITY;
	private Object[] vertices;
	private ArrayList<LinkedList<Vertex>> edges;// 表套表， 也可以使用数组
	private int e;
	private final int n;
	private final Vertex pair = new Vertex();// 用于链表的indexOf和remove

	private static class Vertex {
		int vertex;// 邻接点
		double weight;// 权重

		Vertex(int v, double w) {
			vertex = v;
			weight = w;
		}

		Vertex() {

		}

		Vertex setNode(int u) {
			vertex = u;
			return this;
		}

		public boolean equals(Object o) {// List的indexOf、remove要使用
			if (o instanceof Vertex)
				return this.vertex == ((Vertex) o).vertex;
			return false;
		}
	}

	public LinkedListWeightedDirectedGraph(T[] data) {
		n = data.length;
		vertices = new Object[n];
		System.arraycopy(data, 0, vertices, 0, n);
		edges = new ArrayList<>(n);
		for (int i = 0; i < n; ++i)
			edges.add(new LinkedList<>());
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
		rangeCheck(v);
		int count = 0;
		for (int u = 0; u < n; ++u) {
			if (edges.get(u).indexOf(pair.setNode(v)) != -1)
				++count;
		}
		return count;
	}

	public int outDegree(int v) {
		rangeCheck(v);
		int count = edges.get(v).size();
		return count;
	}

	public int degree(int v) {
		throw new UnsupportedOperationException();
	}

	public boolean addEdge(int u, int v) {
		throw new UnsupportedOperationException();
	}

	public boolean removeEdge(int u, int v) {
		rangeCheck(u);
		rangeCheck(v);
		if (edges.get(u).remove(pair.setNode(v))) {
			--e;
			return true;
		}
		return false;
	}

	public void addWeightedEdge(int u, int v, double w) {
		rangeCheck(u);
		rangeCheck(v);
		LinkedList<Vertex> list = edges.get(u);
		Iterator<Vertex> it = list.iterator();
		while (it.hasNext()) {
			Vertex pair = it.next();
			if (pair.vertex == v) {// 边存在，认为修改
				pair.weight = w;
				return;
			}
		}

		list.add(new Vertex(v, w));
		++e;
	}

	public double getWeight(int u, int v) {
		rangeCheck(u);
		rangeCheck(v);
		Iterator<Vertex> it = edges.get(u).iterator();
		while (it.hasNext()) {
			Vertex pair = it.next();
			if (pair.vertex == v)
				return pair.weight;
		}
		return noEdge;// 无u->v的边，用正无穷大表示
	}

	public void display(int v) {// 使用增强型的for，比上面的简洁一些
		for (Vertex pair : edges.get(v))
			System.out.print(v + " " + pair.vertex + " " + pair.weight + "\n");
	}

	public Iterator<Integer> iterator(int v) {
		rangeCheck(v);
		return new Itr(v);
	}

	private class Itr implements Iterator<Integer> {
		private Iterator<Vertex> it;

		public Itr(int v) {
			it = edges.get(v).iterator();
		}

		public boolean hasNext() {
			return it.hasNext();
		}

		public Integer next() {
			return it.next().vertex;
		}
	}

	public static void main(String[] args) {
		Character[] data = { 'a', 'b', 'c', 'd', 'e', 'f' };
		LinkedListWeightedDirectedGraph<Character> graph = new LinkedListWeightedDirectedGraph<>(data);
		System.out.println("nodes=" + graph.numberOfVertices());

		graph.addWeightedEdge(0, 2, 5.0);
		graph.addWeightedEdge(1, 2, 10.0);
		graph.addWeightedEdge(1, 4, 30.0);
		graph.addWeightedEdge(1, 5, 100.0);
		graph.addWeightedEdge(2, 3, 50.0);
		graph.addWeightedEdge(3, 5, 10.0);
		graph.addWeightedEdge(4, 3, 20.0);
		graph.addWeightedEdge(4, 5, 60.0);
		graph.addWeightedEdge(1, 2, 200.0);

		System.out.println("edges=" + graph.numberOfEdges());

		System.out.println("outdegree of 1=" + graph.outDegree(1));
		System.out.println("indegree of 5=" + graph.inDegree(5));

		graph.removeEdge(1, 4);

		System.out.println("after deleteing edges=" + graph.numberOfEdges());

		System.out.println("weight of (1,5)=" + graph.getWeight(1, 5));

		System.out.println("outdegree of 1=" + graph.outDegree(1));

		System.out.println("value of 4=" + graph.value(4));

		System.out.println("---------");
		Iterator<Integer> it = graph.iterator(1);
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		graph.display(1);

	}
}
