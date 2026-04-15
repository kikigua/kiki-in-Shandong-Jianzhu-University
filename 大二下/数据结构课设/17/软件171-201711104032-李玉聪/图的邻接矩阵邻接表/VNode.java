package timu7;
//VNode类：
//图的邻接表存储表示中的顶点节点类

public class VNode {
	public Object data;//顶点信息
	public ArcNode firstArc;//指向第一条依附于该顶点的弧
	public VNode(){
		this(null,null);
	}
	public VNode(Object data){
		this(data,null);
	}
	public VNode(Object d,ArcNode f){
		data=d;
		firstArc=f;
	}
}