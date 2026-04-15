package graph1;

import graph.GraphKind;

import java.util.Iterator;

public interface IGraph<T> {
    public GraphKind getGraphKind1();// 图的类型

    public void addArc(int u,int v,int value);

    public int getVexNum();

    public int getArcNum();

    public int locateVex(Object vex);

    public void createLinkedListUnSymmetricWeightedUndirected();

    public VNode[] getVexs();

    public int firstAdjVex(int v) throws Exception;

    public int nextAdjVex(int v,int w) throws Exception;
}