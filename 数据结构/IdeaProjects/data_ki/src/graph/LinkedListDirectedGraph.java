package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


//图的邻接表表示方法
//使用LinkedList作为邻接表
public class LinkedListDirectedGraph<T> implements IGraph<T> {
    public final static GraphKind graghKind = GraphKind.DirectedGraph;//图的类型
    private Object[] vertices;//顶点数组
    private ArrayList<LinkedList<Integer>> edges;// 数组+链表    //private ArrayList<LinkedList<Integer>> edges; private Object[] edges
    private int e;//统计边的数目的变量
    private final int n;// 数据 final 修饰基本的数据类型时 必须初始化 否则编译报错。

    //类的声明
    public LinkedListDirectedGraph(T[] data) {
        n = data.length;
        vertices = new Object[n];
        //数组复制
        System.arraycopy(data, 0, vertices, 0, n);
        //new 一个数组来配合LinkList，表示边
        edges = new ArrayList<>();
        for (int i = 0; i < n; i++)
            edges.add(new LinkedList<Integer>());
    }

    private void rangeCheck(int v) {
        if (v < 0 || v >= n)
            throw new IndexOutOfBoundsException();
    }

    //图的类型
    public GraphKind getGraphKind() {
        return graghKind;
    }

    //图的顶点总数
    public int numberOfVertices() {
        return n;
    }

    //边的总数
    public int numberOfEdges() {
        return e;
    }

    //找值等于x的顶点编号
    public int index(T x) {
        for (int i = 0; i < n; i++) {
            if (vertices[i].equals(x))
                return i;
        }
        return -1;
    }

    // 编号为v的顶点的值
    @SuppressWarnings("unchecked")
    public T value(int v) {
        rangeCheck(v);
        return (T) vertices[v];
    }

    //入度
    public int inDegree(int v) {
        rangeCheck(v);
        int count = 0;
        for (int u = 0; u < n; u++) {
            if (((LinkedList<?>) edges.get(u)).indexOf(v) != -1)
                ++count;
        }
        return count;
    }

    //出度
    public int outDegree(int v) {
        rangeCheck(v);
        int count = ((LinkedList<?>) edges.get(v)).size();
        return count;
    }

    public int degree(int v) {
        throw new UnsupportedOperationException();
    }

    //顶点u 边v
    public boolean addEdge(int u, int v) {
        rangeCheck(u);
        rangeCheck(v);
        @SuppressWarnings("unchecked")//告诉编译器忽略指定的警告，不用在编译完成后出现警告信息。
        LinkedList<Integer> list =  edges.get(u);
        if (list.indexOf(v) == -1) {
            list.add(v);
            ++e;
            return true;
        }
        return false;
    }
    // 类库的List接口有3个remove:
    // boolean remove(Object o); 删除第一次出现的数据o。
    // E remove(int index);删除index位置的数据
    // remove()，迭代器的就地删除

    public boolean removeEdge(int u, int v) {
        rangeCheck(u);
        rangeCheck(v);
        if (((LinkedList<?>) edges.get(u)).remove(Integer.valueOf(v))) {
            --e;
            return true;
        }
        return false;
    }

    public boolean removeEdge2(int u, int v) {// 效率比较高，需要new iterator对象
        rangeCheck(u);
        rangeCheck(v);
        @SuppressWarnings("unchecked")
        Iterator<Integer> itr = ((LinkedList<Integer>) edges.get(u)).iterator();
        while (itr.hasNext()) {
            int w = itr.next();
            if (w == v) {
                itr.remove();// 利用迭代器的就地删除
                --e;
                return true;
            }
        }
        return false;
    }

    public boolean removeEdge3(int u, int v) {// indexOf和remove各遍历了一次LinkedList，效率低
        rangeCheck(u);
        rangeCheck(v);
        @SuppressWarnings("unchecked")
        LinkedList<Integer> list = edges.get(u);
        int w = list.indexOf(v);// O(n)
        if (w != -1) {
            list.remove(w);// O(n)
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

    @SuppressWarnings("unchecked")
    public Iterator<Integer> iterator(int v) {
        rangeCheck(v);
        return ((LinkedList<Integer>) edges.get(v)).iterator();// 使用List实现的迭代器
    }

    public static void main(String[] args) {
        Character[] data = { 'a', 'b', 'c', 'd', 'e', 'f' };
        LinkedListDirectedGraph<Character> graph = new LinkedListDirectedGraph<>(data);
        System.out.println("nodes=" + graph.numberOfVertices());

        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 5);
        graph.addEdge(4, 3);
        graph.addEdge(4, 5);

        System.out.println("edges=" + graph.numberOfEdges());

        System.out.println("outdegree of 1=" + graph.outDegree(1));
        System.out.println("indegree of 5=" + graph.inDegree(5));

        graph.removeEdge(1, 4);

        System.out.println("after remove edges=" + graph.numberOfEdges());

        System.out.println("outdegree of 1=" + graph.outDegree(1));

        System.out.println("value of 4=" + graph.value(4));

        System.out.println("---------");
        Iterator<Integer> it = graph.iterator(1);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

