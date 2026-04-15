package graph1;

public class VNode {
    public Object data;     //顶点数据
    public ArcNode firstArc;//指向第一条依附于该顶点的边

    public VNode(){
        this.data=0;
        this.firstArc=null;
    }
    public VNode(Object data){
        this.data=data;
        this.firstArc=null;
    }
    public VNode(Object data,ArcNode firstArc){
        this.data=data;
        this.firstArc=firstArc;
    }
}
