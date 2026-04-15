package timu7;

//由于以下几种方法不只存在于邻接矩阵或者邻接表，所以新建一个类来存放这几种方法
public class BothGraph {
	//转换：邻接表到邻接矩阵
		public ALGraph convertMToAL(MGraph G){
			ALGraph G2=new ALGraph();
			G2.setKind(G.getKind());//得到图的种类标志 ： 无向图还是有向图
			G2.setVexNum(G.getVexNum());//获取邻接矩阵中结点的个数
			G2.setArcNum(G.getArcNum());//获取邻接矩阵中边的条数
			Object a[]=G.getVexs();//把获得的结点存放到数组a[]
			VNode b[]=new VNode[G.getVexNum()];//新建一个结点数目大小的数组  是顶点结点类的
			for(int i=0;i<G.getVexNum();i++){
				b[i]=new VNode(a[i]);
			}
			G2.setVexs(b);
			int c[][]=G.getArcs();//把邻接矩阵中的边存放到二维数组c中
			for(int i=0;i<G.getVexNum();i++){
				for(int j=0;j<G.getVexNum();j++){
					if(c[i][j]==1){
						G2.addArc(i,j);
					}
				}
			}
			return G2;
			
		}
		//矩阵到邻接表
		public MGraph convertALToM(ALGraph g){
			MGraph G3=new MGraph();
			G3.setKind(g.getKind());
			G3.setVexNum(g.getVexNum());
			G3.setArcNum(g.getArcNum());
			VNode a[]=g.getVexs();
			Object b[]=new Object[g.getVexNum()];
			for(int i=0;i<g.getVexNum();i++){
				b[i]=a[i].data;
			}
			G3.setVexs(b);
			int c[][]=new int[g.getVexNum()][g.getVexNum()];
			for(int i=0;i<g.getVexNum();i++){
				for(int j=0;j<g.getVexNum();j++){
					c[i][j]=0;
				}
			}
			for(int j=0;j<g.getVexNum();j++){
				ArcNode p=a[j].firstArc;
				while(p!=null){
					c[j][p.adjVex]=1;
					p=p.nextArc;	
				}
			}
			G3.setArcs(c);
			return G3;
		}
		private boolean visited[];//访问标志数组
		private boolean find;
		//广度优先
		public void BFS(IGraph G,int v){
			visited[v]=true;
			System.out.print(G.getVex(v).toString()+" ");
			LinkQueue Q=new LinkQueue();//辅助队列Q
			Q.offer(v);//v入队列
			while(!Q.isEmpty()){
				int u=(Integer)Q.poll();//队头的元素出队列并赋值给u
				for(int w=G.firstAdjVex(u);w>=0;w=G.nextAdjVex(u,w)){
					if(!visited[w]){
						visited[w]=true;
						System.out.print(G.getVex(w).toString()+" ");
						Q.offer(w);	
					}
				}
			}
			
			
		}
		//判断图的连通性
		public void judge_BFS(IGraph G){
			int j=0;
			visited=new boolean[G.getVexNum()];
			for(int i=0;i<G.getVexNum();i++){
				visited[i]=false;
			}
		    for(int i=0;i<G.getVexNum();i++){
			   if(!visited[i]){
				  System.out.println("第"+ ++j+"个连通分量是：");//判断连通分量是否大于1，判断连通分量的个数
				  BFS(G,i);
				  System.out.println();
			   }
			   
		    }
		    //若连通分量大于1，就说明此图是非连通图
		    if(j>1)
		    	System.out.println("该图是非连通图");
		    else
		    	System.out.println("该图是连通图");
		}
		//寻找简单路径
		public void FindPath(IGraph G,int u,int v,int  path[],int d){
			int w,i;
			visited[u]=true;
			
			d++;
			path[d]=u;//记录所寻找的简单路径
			if(u==v){
				find=true;//find为是否找到简单路径的标志
				System.out.println("一条简单路径为：");
				for(i=0;i<=d;i++){//输出简单路径
					System.out.print(G.getVex(path[i]).toString()+" ");
				}
				System.out.println();
				return;
			}
			//在u！=v的时候，寻找第一个邻接点，找不到返回-1
			w=G.firstAdjVex(u);
			while(w!=-1){// w不存在的情况下w=-1
				if(visited[w]==false)
					FindPath(G,w,v,path,d);
				w=G.nextAdjVex(u,w);	//找u除了w以外的其他邻接点，找不到返回-1
			}
			
		}
		public void Path(IGraph G,int u,int v){
			find=false;
			int i;
			int path[]=new int[100];
			visited=new boolean[G.getVexNum()];
			for(i=0;i<G.getVexNum();i++){
				//访问标志数组初始化
				visited[i]=false;
			}
			FindPath(G,u,v,path,-1);
			if(!find)
				System.out.println("不存在简单路径");
		}

}