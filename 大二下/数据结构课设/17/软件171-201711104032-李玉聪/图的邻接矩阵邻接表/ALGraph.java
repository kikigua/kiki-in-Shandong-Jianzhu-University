package timu7;
import java.util.Scanner;


//邻接表：
public class ALGraph implements IGraph{
	private GraphKind kind;
	private int vexNum,arcNum;
	private VNode vexs[];
	public GraphKind getKind(){
		return kind;
	}
	public void setKind(GraphKind k){
		kind=k;
	}
	public int getArcNum(){
		return arcNum;
	}
	public int getVexNum(){
		return vexNum;
	}
	public VNode[] getVexs(){
		return vexs;
	}
	public void setVexNum(int n){
		vexNum=n;
	}
	public void setArcNum(int n){
		arcNum=n;
	}
	public void setVexs(VNode p[]){
		vexs=p;
	}
	public ALGraph(){
		this(0,0,null);
	}
	public ALGraph(int vexNum,int arcNum,VNode vexs[]){
		this.vexNum=vexNum;
		this.arcNum=arcNum;
		this.vexs=vexs;
	}
	public void createGraph(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入图的类型：");
		kind=GraphKind.valueOf(sc.next());
		switch(kind){
		case UDG:
			createUDG();
			break;
		case DG:
			createDG();
			break;
		default:
			break;
		}
	}
	public void createUDG(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请分别输入图的顶点数、图的边数");
		vexNum=sc.nextInt();
		arcNum=sc.nextInt();
		vexs=new VNode[vexNum];
		System.out.println("请分别输入图的各顶点：");
		for(int i=0;i<vexNum;i++){
			vexs[i]=new VNode(sc.next());
		}
		System.out.println("请输入各边的顶点：");
		for(int i=0;i<arcNum;i++){
			int v=locateVex(sc.next());
			int u=locateVex(sc.next());
			addArc(v,u);
			addArc(u,v);
		}
	}
	public void createDG(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请分别输入图的顶点数、图的边数");
		vexNum=sc.nextInt();
		arcNum=sc.nextInt();
		vexs=new VNode[vexNum];
		System.out.println("请分别输入图的各顶点：");
		for(int i=0;i<vexNum;i++){
			vexs[i]=new VNode(sc.next());
		}
		System.out.println("请输入各边的顶点：");
		for(int i=0;i<arcNum;i++){
			int v=locateVex(sc.next());
			int u=locateVex(sc.next());
			addArc(v,u);
		}
	}
	public int locateVex(Object vex){
		for(int i=0;i<vexNum;i++){
			if(vexs[i].data.equals(vex))
				return i;
				
		}
		return -1;
	}//采用头插法将边结点插入对应表
	public void addArc(int v,int u){
		ArcNode arc=new ArcNode(u);
		arc.nextArc=vexs[v].firstArc;//将之前指向u的firstArc赋值给新插入结点的nextArc
		vexs[v].firstArc=arc;//再把新插入的结点地址传给重新赋值给firstArc
	
	}
	public void addArc(){
		System.out.println("请输入所加边的两个顶点");
		Scanner sc=new Scanner(System.in);
		int i=locateVex(sc.next());
		int j=locateVex(sc.next());
		switch(kind){
		case DG:
			addArc(i,j);
			arcNum++;
			break;
		case UDG:
			addArc(i,j);
			addArc(j,i);
			arcNum++;
		default:
			break;
		}
	}
	public void deleteArc(int v,int u){
		ArcNode q=vexs[v].firstArc;
		if(q.adjVex==u)
			vexs[v].firstArc=q.nextArc;
		while(q.nextArc!=null){
			if(q.nextArc.adjVex==u){
				q.nextArc=q.nextArc.nextArc;
				break;
			}
			q=q.nextArc;
		}
		
	}
	public void deleteArc(){
		System.out.println("请输入所删除边的两个顶点");
		Scanner sc=new Scanner(System.in);
		int c=locateVex(sc.next());
		int d=locateVex(sc.next());
		switch(kind){
		case DG:
			deleteArc(c,d);
			arcNum--;
			break;
		case UDG:
			deleteArc(c,d);
			deleteArc(d,c);
			arcNum--;
			break;
		default:
			break;
			
		}	
	}
	public void addVex(){
		System.out.println("请输入所加顶点");
		Scanner sc=new Scanner(System.in);
		VNode s=new VNode(sc.next());
		VNode vexs2[]=new VNode[vexNum+1];
		System.arraycopy(vexs, 0, vexs2, 0, vexNum);
		vexs2[vexNum]=s;
		vexs=new VNode[vexNum+1];
		System.arraycopy(vexs2,0,vexs,0,vexNum+1);
		vexNum++;
	}
	public void deleteVex(){
		System.out.println("请输入所删除顶点");
		Scanner sc=new Scanner(System.in);
		int n=locateVex(sc.next());
		vexs[n]=null;
		for(int j=n;j<vexNum-1;j++){
			vexs[j]=vexs[j+1];
		}
		vexs[vexNum-1]=null;
		
		for(int i=0;i<vexNum-1;i++){
			deleteArc(i,n);
				ArcNode p=vexs[i].firstArc;
				while(p!=null){
					
					if(p.adjVex>n){
						p.adjVex--;
					}
					p=p.nextArc;
				}
			
		}
		
		vexNum--;
	}
	public int firstAdjVex(int v){
		if(v<0||v>=vexNum)
			System.out.println("第"+v+"个顶点不存在");
		VNode vex=vexs[v];
		if(vex.firstArc!=null)
			return  vex.firstArc.adjVex;
		else
			return -1;
	}
	public int nextAdjVex(int v,int w){
		if(v<0||v>=vexNum)
			System.out.println("第"+v+"个顶点不存在");
		VNode vex=vexs[v];
		ArcNode arcvw=null;
		for(ArcNode arc=vex.firstArc;arc!=null;arc=arc.nextArc){
			if(arc.adjVex==w){
				arcvw=arc;
			    break;
		    }
		}
		if(arcvw!=null&&arcvw.nextArc!=null){
			return arcvw.nextArc.adjVex;
		}
		else
			return -1;
	}
	public Object getVex(int v) {
		if(v<0||v>=vexNum)
			System.out.println("第"+v+"个顶点不存在");
		return vexs[v].data;
	
	}
	public void getDegree(int v){
		int in=0;
		int out=0;
		int num=0;
		switch(kind){
		case DG:
			ArcNode p=vexs[v].firstArc;
			while(p!=null){
				p=p.nextArc;
				out++;
			}
			for(int i=0;i<vexNum;i++){
				for(ArcNode ar=vexs[i].firstArc;ar!=null;ar=ar.nextArc){
					if(ar.adjVex==v){
						in++;
					break;
					}
				}
			}
			System.out.println("顶点"+getVex(v)+"的入度为："+in+"出度为："+out);
			break;
		case UDG:
			ArcNode q=vexs[v].firstArc;
			while(q!=null){
				q=q.nextArc;
				num++;
			}
			System.out.println("顶点"+getVex(v)+"的度为:"+num);
			break;
		default:
			break;
		
		}
		
	}
	public void getDegree(){
		for(int i=0;i<vexNum;i++){
			getDegree(i);
		}
	}
	
	public void display(){
		for(int i=0;i<vexNum;i++){
			for(ArcNode p=vexs[i].firstArc;p!=null;p=p.nextArc){
				System.out.print(getVex(i)+"->"+getVex(p.adjVex)+"  ");
			
			}
		}
		System.out.println();
		System.out.println("该图的存储结构：");
		for(int i=0;i<vexNum;i++){
			System.out.print(vexs[i].data);
			for(ArcNode p=vexs[i].firstArc;p!=null;p=p.nextArc){
				System.out.print("-->"+getVex(p.adjVex));
			}
			System.out.println();
		}
	}

}