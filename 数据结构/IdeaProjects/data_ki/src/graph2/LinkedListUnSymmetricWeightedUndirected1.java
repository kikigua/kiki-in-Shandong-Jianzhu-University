package graph2;

import graph.GraphKind;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListUnSymmetricWeightedUndirected1<T> implements IGraph<T>{
    public final static GraphKind graghKind = GraphKind.WeightedUnDirectedGraph;
    //！！
    public final static double noEdge = Double.POSITIVE_INFINITY;
    private Object[] vertices;
    private ArrayList<LinkedList<Pair>> edges;// 表套表， 也可以使用数组
    private int e;// 边
    private final int n ;// 顶点个数
    //！！
    private final Pair pair = new Pair();// 用于链表的indexOf和remove

    //!!
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

    public LinkedListUnSymmetricWeightedUndirected1(T[] data) {
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

    //有向图
    //入度
    public int inDegree(int v) {
        throw new UnsupportedOperationException();
    }

    //出度
    public int outDegree(int v) {
        throw new UnsupportedOperationException();
    }

    //无向图11111111
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

    //移除边
    public boolean removeEdge(int u, int v) {
        rangeCheck(u);
        rangeCheck(v);
        //无向图所以移除边的时候，要注意（u，v）（v,u）
        if (edges.get(u).remove(pair.setNode(v))) {
            --e;
            return true;
        } else if (edges.get(v).remove(pair.setNode(u))) {
            --e;
            return true;
        } else
            return false;
    }

    //11
    //添加具有权重的边
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

    public static void main(String[] args) {
        Character[] data = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
        LinkedListUnSymmetricWeightedUndirected1<Character> graph = new LinkedListUnSymmetricWeightedUndirected1<>(data);
        System.out.println("nodes=" + graph.numberOfVertices());

        graph.addWeightedEdge(0, 3, 1.0);
        graph.addWeightedEdge(0, 1, 2.0);
        graph.addWeightedEdge(0, 2, 3.0);
        graph.addWeightedEdge(2, 4, 4.0);
        graph.addWeightedEdge(1, 2, 5.0);
        graph.addWeightedEdge(1, 4, 6.0);
        graph.addWeightedEdge(1, 3, 7.0);
        graph.addWeightedEdge(3, 4, 8.0);
        graph.addWeightedEdge(3, 5, 9.0);
        graph.addWeightedEdge(4, 5, 10.0);
        graph.addWeightedEdge(4, 6, 11.0);
        graph.addWeightedEdge(3, 7, 12.0);
        graph.addWeightedEdge(5, 7, 13.0);
        graph.addWeightedEdge(5, 6, 14.0);
        graph.addWeightedEdge(6, 7, 15.0);
        System.out.println("edges=" + graph.numberOfEdges());

        graph.removeEdge(4, 3);
        graph.removeEdge(0, 2);
        graph.removeEdge(7, 6);
        System.out.println("after deleteing edges=" + graph.numberOfEdges());

        System.out.println("weight of (2,4)=" + graph.getWeight(2, 4));
        System.out.println("weight of (0,2)=" + graph.getWeight(0, 2));
        System.out.println("weight of (0,6)=" + graph.getWeight(0, 6));

        System.out.println("degree of 5=" + graph.degree(5));
        System.out.println("degree of 3=" + graph.degree(3));

        System.out.println("value of 5=" + graph.value(5));
        System.out.println("value of 6=" + graph.value(6));

        System.out.println("---------");
        Iterator<Integer> it = graph.iterator(1);
        while (it.hasNext()) {
            int m = it.next();
            if (m == -1)
                continue;
            System.out.print(m + "   ");
        }
        System.out.println("\r---------");
        Iterator<Integer> it1 = graph.iterator(5);
        while (it1.hasNext()) {
            int m = it1.next();
            if (m == -1)
                continue;
            System.out.print(m + "   ");
        }
        System.out.println("\r---------");
        Iterator<Integer> it2 = graph.iterator(4);
        while (it2.hasNext()) {
            int m = it2.next();
            if (m == -1)
                continue;
            System.out.print(m + "   ");
        }
        System.out.println("\r---------");
        graph.display(1);
        System.out.println("---------");
        graph.display(6);
        System.out.println("---------");
        graph.display(3);

    }

}
