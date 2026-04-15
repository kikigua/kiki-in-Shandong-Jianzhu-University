package graph2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import graph.GraphKind;
import priorityqueue.PriorityQueueWithDecrease;
import priorityqueue.PriorityQueueWithDecrease.Entry;

public class GraphAlgorithms<T> {
    private IGraph<T> graph;// 使用的接口
    int n;
    boolean[] reached;

    public GraphAlgorithms(IGraph<T> graph) {
        this.graph = graph;
        n = graph.numberOfVertices();
        // reached = new boolean[n];也可以这里new，后面的方法中就不用再 new,
        // 但是要使用Arrays.fill(reached,false)，避免不同的方法之间互相干扰
    }

    public void bfs(int u) {
        reached = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();// java队列
        queue.offer(u);
        reached[u] = true;
        while (!queue.isEmpty()) {
            u = queue.poll();
            System.out.print(graph.value(u)); // 将其替换为期望的处理

            for (Iterator<Integer> it = graph.iterator(u); it.hasNext();) {
                int next = it.next();
                if (!reached[next]) {
                    queue.offer(next);
                    reached[next] = true;
                }
            }
        }
        System.out.println();
    }

    public void dfs(int u) {
        reached = new boolean[n];
        rdfs(u);
        System.out.println();
    }

    private void rdfs(int u) {
        reached[u] = true;
        System.out.print(graph.value(u)); // 替换为期望的处理
        for (Iterator<Integer> it = graph.iterator(u); it.hasNext();) {
            int next = it.next();
            if (!reached[next])
                rdfs(next);
        }
    }

    public boolean connected() {
        if (graph.getGraphKind() != GraphKind.UnDirectedGraph)
            return false;
        reached = new boolean[n];
//		bfs(0);
        dfs(0);
        for (int i = 0; i < n; i++) {
            if (!reached[i])
                return false;
        }
        return true;
    }

    public int connectedComponent() {
        if (graph.getGraphKind() != GraphKind.UnDirectedGraph)
            throw new IllegalStateException();
        int[] labeled = new int[n];// 未搜索过的顶点的标记为0
        int label = 1;
        for (int i = 0; i < n; i++) {
            if (labeled[i] == 0)
                bfs(i, label++, labeled);
        }
        return --label;
    }

    private void bfs(int u, int label, int[] labeled) {
        Deque<Integer> queue = new ArrayDeque<>();// java队列
        queue.offer(u);
        labeled[u] = label;
        while (!queue.isEmpty()) {
            u = queue.poll();
            for (Iterator<Integer> it = graph.iterator(u); it.hasNext();) {
                int next = it.next();
                if (labeled[next] == 0) {
                    queue.offer(next);
                    labeled[next] = label;
                }
            }
        }
    }

    public void findAllSimplePath(int source, int destination) {
        reached = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();// 使用Deque的右端栈
        if (!rfindAllSimplePath(source, destination, stack))
            System.out.println("there isn't simple path");
    }

    public boolean rfindAllSimplePath(int source, int destination, Deque<Integer> stack) {
        reached[source] = true;
        if (source == destination) {
            reached[source] = false;
            for (int vertex : stack) // 只是为了演示用了println，一般方法中不要用print!!!
                System.out.print(graph.value(vertex) + " ");
            System.out.print(graph.value(destination) + "\n");
            return true;
        }
        stack.offerLast(source);
        Iterator<Integer> it = graph.iterator(source);
        boolean exist = false;
        while (it.hasNext()) {
            int next = it.next();
            if (!reached[next]) {
                if (rfindAllSimplePath(next, destination, stack))
                    exist = true;
            }
        }
        stack.pollLast();
        reached[source] = false;// 否则只找其中的一条
        return exist;
    }

    public int[] shortestPath(int source, int destination) {
        reached = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();// java队列
        int[] tree = new int[n];
        tree[source] = -1;
        queue.offer(source);
        reached[source] = true;
        while (source != destination && !queue.isEmpty()) {
            source = queue.poll();
            for (Iterator<Integer> it = graph.iterator(source); it.hasNext();) {
                int next = it.next();
                if (!reached[next]) {
                    queue.offer(next);
                    reached[next] = true;
                    tree[next] = source;
                }
            }
        }
        if (source == destination) {
            int[] reversePath = new int[n + 1];
            int pos = 0;
            int k = destination;
            while (tree[k] != -1) {
                reversePath[pos++] = k;
                k = tree[k];
            }
            reversePath[pos] = k;
            int[] path = new int[pos + 1];
            for (int i = 0; i < path.length; i++) {
                path[i] = reversePath[pos--];
            }
            return path;
        }
        return null;
    }

    public void dijkstra(int source) {
        if (graph.getGraphKind() != GraphKind.WeightedDirectedGraph)
            return;
        double[] distance = new double[n];
        int[] path = new int[n];
//		dijkstraWithArray(source, distance, path);
        dijkstraWithPriorityQueue(source, distance, path);
        // 以下求源点到各顶点最短路径上的顶点
        int[] tmp = new int[n];
        for (int i = 0; i < n; ++i) {
            int count = 0;
            int back = i;
            while (path[back] != -1)
                tmp[count++] = back = path[back];
            System.out.print(source);
            for (int j = count - 2; j >= 0; --j)
                System.out.print("->" + tmp[j]);
            System.out.println("->" + i + "   " + distance[i]);
        }
    }

    @SuppressWarnings("unused")
    private void dijkstraWithArray(int source, double[] distance, int[] path) {
        int[] S = new int[n];// S[i] = 0，表示还没有求出顶点source到顶点i的最短距离
        for (int i = 0; i < S.length; ++i) {
            path[i] = -1;
            distance[i] = Double.POSITIVE_INFINITY;
        }
        distance[source] = 0.0;
        int count = n;
        while (count-- != 0) {
            int u = Integer.MAX_VALUE;// 任意值
            double min = Double.POSITIVE_INFINITY;
            // 查找目前的最短路径
            for (int i = 0; i < n; ++i) {
                if (S[i] == 0 && distance[i] <= min) {// 必须 <=，因为min的初值是无穷大
                    u = i;
                    min = distance[i];
                }
            }
            S[u] = 1;
            // 扩展找到的这条最短路径
            Iterator<Integer> it = graph.iterator(u);
            while (it.hasNext()) {
                int v = it.next();
                double sum;// = distance[u] + graph.getWeight(u, v);
                if (S[v] == 0 && distance[v] > (sum = distance[u] + graph.getWeight(u, v))) {
                    distance[v] = sum;
                    path[v] = u;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void dijkstraWithPriorityQueue(int source, double[] distance, int[] path) {
        class Vertex implements Comparable<Vertex> {
            int id;// 顶点编号
            double d;// d值

            Vertex(int v, double d) {
                id = v;
                this.d = d;
            }

            public int compareTo(Vertex rhd) {// 顶点的大小由d值决定
                return Double.compare(d, rhd.d);
            }
        }
        // queue ppt中的Q
        PriorityQueueWithDecrease<Vertex> queue = new PriorityQueueWithDecrease<>(n);
        Entry<Vertex>[] data = new Entry[n];// 引用所有的顶点
        // 初始化部分
        int[] S = new int[n];// 初始化S，S为空集
        for (int i = 0; i < n; ++i) {// 初始化队列Q，包含所有的顶点
            path[i] = -1;
            if (i != source)
                data[i] = new Entry<>(new Vertex(i, Double.POSITIVE_INFINITY));// 其他顶点，d值=∞
            else
                data[i] = new Entry<>(new Vertex(i, 0.0));// 源点，d值=0
            queue.offer(data[i]);// 顶点入队
        }
        while (queue.size() != 0) {
            int u = queue.poll().id;// 具有最小d值的顶点u
            S[u] = 1;// S = S ∪ {u}
            Iterator<Integer> it = graph.iterator(u);
            while (it.hasNext()) {
                int v = it.next();// u→v的弧
                if (S[v] == 0) {
                    // Relax(u, v wuv)
                    if (data[v].getData().d > data[u].getData().d + graph.getWeight(u, v)) {// d[v] > d[u] + w
                        data[v].getData().d = data[u].getData().d + graph.getWeight(u, v);
                        queue.decrease(data[v].getIndex());
                        path[v] = u;// 在源点到v的最短路径上，u是v的前驱
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i)// 获取各顶点的d值
            distance[i] = data[i].getData().d;
    }

    public void floyd() {
        if (graph.getGraphKind() != GraphKind.WeightedDirectedGraph)
            return;
        double[][] distance = new double[n][n];
        int[][] path = new int[n][n];
        // 初始化
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = graph.getWeight(i, j);
                path[i][j] = -1;
            }
        }
        // 因为实现带权有向图时用无穷大表示无边，所以顶点到自己的路径长度为无穷大，下面改回来
        for (int i = 0; i < n; ++i)
            distance[i][i] = 0.0;
        // 核心
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    double tmp = distance[i][k] + distance[k][j];
                    if (tmp < distance[i][j]) {
                        distance[i][j] = tmp;
                        path[i][j] = k;
                    }
                }
            }
        }
        // 输出路径
        int[] itoj = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print("path[" + i + "][" + j + "]=");
                int pos = rfindpath(itoj, 0, i, j, path);
                for (int k = 0; k < pos; k++) {
                    if (k != pos - 1)
                        System.out.print(itoj[k] + "->");
                    else
                        System.out.print(itoj[k]);
                }
                System.out.println("   " + distance[i][j]);
            }
        }
    }

    private int rfindpath(int itojpath[], int pos, int i, int j, int path[][]) {
        int k = path[i][j];
        if (k == -1) {
            itojpath[pos++] = i;
            itojpath[pos++] = j;
        } else {
            int mid = rfindpath(itojpath, pos, i, k, path);
            pos = rfindpath(itojpath, --mid, k, j, path);// 覆盖前面的k，否则有重复的顶点
        }
        return pos;
    }

    public static void main(String[] args) {
        // ppt的无向图
        Character[] data = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i' };

        IGraph<Character> graph = new AjacencyMatrixUnDirectedGraph<>(data);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);

        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(1, 6);

        graph.addEdge(2, 0);
        graph.addEdge(2, 1);
        graph.addEdge(2, 7);

        graph.addEdge(3, 0);
        graph.addEdge(3, 7);

        graph.addEdge(4, 0);
        graph.addEdge(4, 8);

        graph.addEdge(5, 0);
        graph.addEdge(5, 6);
        graph.addEdge(5, 8);

        graph.addEdge(6, 0);
        graph.addEdge(6, 1);
        graph.addEdge(6, 5);

        graph.addEdge(7, 2);
        graph.addEdge(7, 3);

        graph.addEdge(8, 4);
        graph.addEdge(8, 5);

        GraphAlgorithms<Character> alg = new GraphAlgorithms<>(graph);

        System.out.println("==========DFS===========");
        alg.dfs(0);

        System.out.println("==========BFS===========");
        alg.bfs(0);
        if (alg.connected())
            System.out.println("connected");
        else
            System.out.println("don't connected");

        System.out.println(alg.connectedComponent());

        System.out.println("==========shortestPath===========");
        for (int i : alg.shortestPath(1, 8)) {
            System.out.print(graph.value(i) + " ");
        }
        System.out.println();
        System.out.println("==========findAllSimplePath===========");
        alg.findAllSimplePath(1, 8);

        // 下图是ppt中Dijsktra算法的示意图
        Character[] weightedData = { '0', '1', '2', '3', '4', '5' };
        IGraph<Character> weightedGraph = new LinkedListWeightedDirectedGraph2<>(weightedData);

        weightedGraph.addWeightedEdge(0, 2, 5.0);
        weightedGraph.addWeightedEdge(1, 2, 10.0);
        weightedGraph.addWeightedEdge(1, 4, 30.0);
        weightedGraph.addWeightedEdge(1, 5, 100.0);
        weightedGraph.addWeightedEdge(2, 3, 50.0);
        weightedGraph.addWeightedEdge(3, 5, 10.0);
        weightedGraph.addWeightedEdge(4, 3, 20.0);
        weightedGraph.addWeightedEdge(4, 5, 60.0);

        GraphAlgorithms<Character> weightedPathAlg = new GraphAlgorithms<>(weightedGraph);

        System.out.println("==========dijkstra===========");
        weightedPathAlg.dijkstra(1);

        System.out.println("==========floyd===========");
        weightedPathAlg.floyd();
    }
}