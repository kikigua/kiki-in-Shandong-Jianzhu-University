package graph1;

import java.util.Iterator;

public interface IGraph<T> {
	public GraphKind getGraphKind();// 图的类型

	public int numberOfVertices();// 顶点的个数

	public int numberOfEdges();// 边数

	public int index(T x);// 值等于x的顶点编号，-1，表示没找到

	public T value(int v);// 编号为v的顶点的值

	public int inDegree(int v); // 有向图的编号为v的顶点的入度

	public int outDegree(int v);// 有向图的编号为v的顶点的出度

	public int degree(int v);// 无向图的编号为v的顶点的度

	public boolean addEdge(int u, int v);// 加入边<u, v>

	public boolean removeEdge(int u, int v);// 删除边<u, v>

	public void addWeightedEdge(int u, int v, double w); // 加入边<u, v>，或修改边<u，
															// v>的权重

	public double getWeight(int u, int v);// 获取边<u, v>的权重

	public Iterator<Integer> iterator(int v); // 用于迭代访问顶点v的邻接点，不同于
												// Iterable，这里需要1个参数

	public Object getvertices(int i);
}
