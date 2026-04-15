package graph1;

import graph.GraphKind;

import java.util.Scanner;

//课本上图的邻接表的表示方法
public class LinkedListUnSymmetricWeightedUndirected<T> implements IGraph<T> {
    //图的类型
    public final static GraphKind graphKind1=GraphKind.WeightedUnDirectedGraph;
    //顶点数和边数
    private int vexNum,arcNum;
    //顶点
    private VNode[] vexs;//比较于Object[],一个里面存储Object类型的数组
    // VNode[]一个里面存储结点的数组


    //图的类型
    public GraphKind getGraphKind1(){
        return graphKind1;
    }

    public LinkedListUnSymmetricWeightedUndirected(){
        this.vexNum=0;
        this.arcNum=0;
        this.vexs=null;
    }
    public LinkedListUnSymmetricWeightedUndirected(int vexNum,int arcNum,VNode[] vexs){
        this.vexs=vexs;
        this.arcNum=arcNum;
        this.vexNum=vexNum;
    }
    //在位置为u，v的顶点之间，添加一条边，其权值为value
    public void addArc(int u,int v,int value){
        ArcNode arc=new ArcNode(u,value);
        arc.nextArc=vexs[v].firstArc;
        vexs[v].firstArc=arc;
    }
    //返回顶点数
    public int getVexNum(){
        return vexNum;
    }
    //返回边数
    public int getArcNum(){
        return arcNum;
    }
    //给定顶点的值vex，返回其在途中的位置，若图中不包含此顶点，返回-1
    public int locateVex(Object vex){
        for (int v=0;v<vexNum;v++){
            if (vexs[v].equals(vex)){
                return v;
            }
        }
        return -1;
    }
    public void createLinkedListUnSymmetricWeightedUndirected(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请分别输入图的顶点数，图的边数：");
        vexNum=sc.nextInt();
        arcNum=sc.nextInt();
        vexs=new VNode[vexNum];
        System.out.println("请分别输入图的各顶点");
        for (int v=0;v<vexNum;v++){
            vexs[v]=new VNode(sc.next());
        }
        System.out.println("请输入各边的顶点及其权值");
        for (int l=0;l<arcNum;l++) {
            int u = locateVex(sc.next())+1;
            int v = locateVex(sc.next())+1;
            int value = sc.nextInt();
            addArc(u,v,value);
            addArc(v,u,value);
        }
    }
    public VNode[] getVexs(){
        return vexs;
    }
    //返回v的第一个邻接点，若v没有邻接点，则返回-1，0《=v《=vexNum
    public int firstAdjVex(int v) throws Exception{
        if (v<0&&v>=vexNum){
            throw new Exception("第"+v+"个顶点不存在");
        }
        VNode vex=vexs[v];
        if (vex.firstArc!=null){
            return vex.firstArc.adjVex;
        }
        else {
            return -1;
        }
    }
    //返回v相对于w的下一个邻接点，若w是v的最后一个邻接点，则返回-1，其中，0《=v，w《vexNum
    public int nextAdjVex(int v,int w) throws Exception{
        if (v<0&&v>vexNum){
            throw new Exception("第"+v+"个顶点不存在");
        }
        VNode vex=vexs[v];
        ArcNode arcvw=null;
        for (ArcNode arc = vex.firstArc;arc!=null;arc=arc.nextArc){
            if (arc.adjVex==w){
                arcvw=arc;
                break;
            }
        }
        if (arcvw!=null&&arcvw.nextArc!=null){
            return arcvw.nextArc.adjVex;
        }
        else
            return -1;
    }

    public static void main(String[] args) {
        LinkedListUnSymmetricWeightedUndirected<Character> graph=new LinkedListUnSymmetricWeightedUndirected<>();
        graph.createLinkedListUnSymmetricWeightedUndirected();
    }

}
