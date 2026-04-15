package graph1;

//图的邻接表存储表示中的边结点类
public class ArcNode {
    public int adjVex;//该边指向的顶点位置
    public int value;//该边的权值
    ArcNode nextArc;//指向下一个边
    public ArcNode(){
        this.adjVex=-1;
        this.value=0;
        this.nextArc=null;
    }
    public ArcNode(int adjVex){
        this.adjVex=adjVex;
        this.value=0;
        this.nextArc=null;
    }
    public ArcNode(int adjVex,int value){
        this.adjVex=adjVex;
        this.value=value;
    }
    public ArcNode(int adjVex,int value,ArcNode nextArc){
        this.adjVex=adjVex;
        this.value=value;
        this.nextArc=nextArc;
    }
}
