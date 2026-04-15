package timu7;

import java.util.Scanner;

public class MGraph implements IGraph{
    private GraphKind kind; // 图的种类标志
    private int vexNum, arcNum; // 图的当前顶点数和边数
    private Object[] vexs; // 顶点
    private int[][] arcs;  // 邻接矩阵
    //无参构造                     
    public MGraph(){
        this(null, 0, 0, null, null);
    }
    //含参构造
    public MGraph(GraphKind kind, int vexNumi, int arcNum, Object[] vexs, int[][] arcs) { 
        this.kind = kind;
        this.vexNum = vexNumi;
        this.arcNum = arcNum;
        this.vexs = vexs;
        this.arcs = arcs;
    }
    //创建图
    public void createGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入图的类型：（UDG、DG）");
        kind=GraphKind.valueOf(sc.next());
        switch (kind) {
        case UDG:
            createUDG();  //构造无向图
            break;
        case DG:
            createDG();   //构造有向图
            break;
        default:
            break;
        }
    }   
    //创建有向图    设权值为1
    public void createDG(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请分别输入图的顶点数、图的边数");
		vexNum=sc.nextInt();
		arcNum=sc.nextInt();
		vexs=new Object[vexNum];//创建一个vexNum大小的数组，用来存放vex
		System.out.println("请输入图的各个顶点；");
		for(int i=0;i<vexNum;i++){//循环向数组中输入顶点
			vexs[i]=sc.next();
		}
		arcs=new int[vexNum][vexNum];//创建邻接矩阵
		for(int i=0;i<vexNum;i++){//矩阵初始化，权值都赋值为0
			for(int j=0;j<vexNum;j++){
				arcs[i][j]=0;
			}
		}
		System.out.println("请输入各个边的两个顶点");
		for(int k=0;k<arcNum;k++){//输入矩阵的信息，都将权值赋值为1
			int i=locateVex(sc.next());
			int j=locateVex(sc.next());
			arcs[i][j]=1;
		}
	}	
    //创建无向图  设权值为1
    public void createUDG(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请分别输入图的顶点数、图的边数");
		vexNum=sc.nextInt();
		arcNum=sc.nextInt();
		vexs=new Object[vexNum];//创建一个vexNum大小的数组，用来存放vex
		System.out.println("请输入图的各个顶点；");
		for(int i=0;i<vexNum;i++){//循环向数组中输入顶点
			vexs[i]=sc.next();
		}
		arcs=new int[vexNum][vexNum];//创建邻接矩阵
		for(int i=0;i<vexNum;i++){//矩阵初始化，权值都赋值为0
			for(int j=0;j<vexNum;j++){
				arcs[i][j]=0;
			}
		}
		System.out.println("请输入各个边的两个顶点");
		for(int k=0;k<arcNum;k++){//输入矩阵的信息，都将权值赋值为1
			int i=locateVex(sc.next());
			int j=locateVex(sc.next());
			arcs[i][j]=arcs[j][i]=1;
		}
	}
    //返回顶点数
    public int getVexNum() { 
        return vexNum;
    }
    public void setVexNum(int n){
		vexNum=n;
	}
    //返回边数
    public int getArcNum() {  
        return arcNum;
    }
    public void setArcNum(int n){
		arcNum=n;
	}
    //返回图的种类标志
    public GraphKind getKind() {
        return kind;
    }
    public void setKind(GraphKind k){
		kind=k;
	}

    public Object[] getVexs(){
		return vexs;
	}
	public void setVexs(Object a[]){
		vexs=a;
	}

	public int[][] getArcs(){
		return arcs;
	}
	public void setArcs(int a[][]){
		arcs=a;
	}
	//返回v表示结点的值，0<=v<vexNum
	public Object getVex(int v){
		if(v<0||v>=vexNum){
			System.out.println("第"+v+"个顶点不存在");
		}
		return vexs[v];
	}
	//给定顶点值vex，返回其在图中的位置，如果图中不包含此顶点，则返回-1
    public int locateVex(Object vex) {  
        for(int v = 0; v < vexNum; v++){
            if(vexs[v].equals(vex))//两个值进行比对 找到了返回数组的下标  
                return v;
        }
        return -1;//找不到返回-1
    }
    //返回v的第一个邻接点，若v没有邻接点则返回-1，0<=v<vexNum
    public int firstAdjVex(int v){
		if(v<0||v>=vexNum){//如果输入的v值小于0或者大于顶点数目：输入不合法，返回第v个顶点不存在
			System.out.println("第"+v+"个顶点不存在");
		}
		for(int i=0;i<vexNum;i++){
			if(arcs[v][i]==1){
				return i;
			}
		}
		return -1;
	}
    //返回v相对于w的下一个邻接点，若w是v的最后一个邻接点，则返回-1，其中0<=v,w<vexNum
    public int nextAdjVex(int v,int w){
		if(v<0||v>=vexNum){//输入不合法
			System.out.println("第"+v+"个顶点不存在");
		}
		for(int i=w+1;i<vexNum;i++){//寻找w以后的邻接点
			if(arcs[v][i]==1){//如果存在这条边则返回这个顶点
				return i;
			}
		}
		return -1;
	}       
 
  	public void addArc(){
  		Scanner sc=new Scanner(System.in);
  		System.out.println("请输入所加边的两个顶点");
  		int i=locateVex(sc.next());//通过locateVex方法，将输入的数据元素转换为数组的下标，赋值给i
  		int j=locateVex(sc.next());//通过locateVex方法，将输入的数据元素转换为数组的下标，赋值给j
  		switch(kind){
  		case DG:
  			if(arcs[i][j]==0){
  				arcs[i][j]=1;
  				arcNum++;
  			}
  			return;
  		case UDG:
  			if(arcs[i][j]==0){
  				arcs[i][j]=arcs[j][i]=1;
  				arcNum++;
  			}
  			return;
  		default:
  			break;
  		}
  	}
  //删除边
  	public void deleteArc(int i,int j ){//此方法用于下面删除点的时候用来调用的方法
  		switch(kind){
  		case DG:
  			if(arcs[i][j]==1){
  				arcs[i][j]=0;
  				arcNum--;
  			}
  			return;
  		case UDG:
  			if(arcs[i][j]==1){
  				arcs[i][j]=arcs[j][i]=0;
  				arcNum--;
  			}
  			return;
  		default:
  			break;
  		}
  	}
  	public void deleteArc(){
  		Scanner sc=new Scanner(System.in);
  		System.out.println("请输入所删除边的两个顶点");
  		int i=locateVex(sc.next());//通过locateVex方法，将输入的数据元素转换为数组的下标，赋值给i
  		int j=locateVex(sc.next());//通过locateVex方法，将输入的数据元素转换为数组的下标，赋值给j
  		switch(kind){
  		case DG:
  			if(arcs[i][j]==1){//如果这条边存在，那么就将其删除（将其权值赋值为0）
  				arcs[i][j]=0;
  				arcNum--;//边数自减1
  			}
  			return;
  		case UDG:
  			if(arcs[i][j]==1){
  				arcs[i][j]=arcs[j][i]=0;
  				arcNum--;
  			}
  			return;
  		default:
  			break;
  		}	
  	}
  //增加点
  	public void addVex(){
  		System.out.println("请输入所加入的顶点");
  		Scanner sc=new Scanner(System.in);
  		Object vexs2[]=new Object[vexNum+1];//数组的扩容 新建一个数组比原数组大一个
  		vexs2[vexNum]=sc.next();
  	    //复制数组arraycopy(源数组,源数组要复制的起始位置,目的数组,目的数组放置的起始位置,复制的长度)
  		System.arraycopy(vexs, 0, vexs2, 0, vexNum);
  		vexs=new Object[vexNum+1];
  	    //复制数组arraycopy(源数组,源数组要复制的起始位置,目的数组,目的数组放置的起始位置,复制的长度)
  		System.arraycopy(vexs2, 0, vexs, 0, vexNum+1);
  		//arcs2起到中间变量的作用
  		int arcs2[][]=new int[vexNum+1][vexNum+1];
  		for(int i=0;i<vexNum;i++){
  			for(int j=0;j<vexNum;j++){
  				arcs2[i][j]=arcs[i][j];//把数组arcs复制到arcs2里面来
  			}
  		}
  		vexNum++;
  		//将最外圈的赋值为0，因为是从 0号位开始进行矩阵行列计数的 此时的vexNum已经自加1 		
  		for(int i=0;i<vexNum;i++){
  			arcs2[vexNum-1][i]=0;
  			arcs2[i][vexNum-1]=0;
  		}
  		//此时arcNum已经+1，重新将arc定义新的arcNum大小的二维数组存放矩阵
  		arcs=new int[vexNum][vexNum];
  		for(int i=0;i<vexNum;i++){
  			for(int j=0;j<vexNum;j++){
  				arcs[i][j]=arcs2[i][j];//把arcs2的矩阵中的数据传给完成扩容后的arcs矩阵
  			}
  		} 		
  	}
  	//删除点
	public void deleteVex(){
	
		System.out.println("请输入所删除的顶点");
		Scanner sc=new Scanner(System.in);
		int i=locateVex(sc.next());//通过locateVex方法，将输入的数据元素转换为数组的下标，赋值给i
		for(int n=0;n<vexNum;n++){
			//删除边操作
			deleteArc(i,n);//删除第i行的元素
			deleteArc(n,i);//删除第i列的元素
		}
		for(int j=i;j<vexNum-1;j++){
			vexs[j]=vexs[j+1];//从删除结点位置开始，将后边的结点赋值给前边的结点，用来补充前边的结点
		}
		vexs[vexNum-1]=null;//最后一个结点置为空值
		for(int k=0;k<vexNum;k++){
			for(int l=i;l<vexNum-1;l++){
				arcs[l][k]=arcs[l+1][k];//把后边一列的权值赋值到前边空缺的一列，以此类推，直至完成为止
			}
			arcs[vexNum-1][k]=0;//最后一列赋值为0
		}
		for(int k=0;k<vexNum;k++){
			for(int l=i;l<vexNum-1;l++){
				arcs[k][l]=arcs[k][l+1];//将刚才移动列以后的矩阵进行 行的移动
			}
			arcs[k][vexNum-1]=0;//最后一行赋值为0
		}
		vexNum--;//结点数自减1  完成矩阵的减小	
	}
	//求各个顶点的度
	public void getDegree(int v){
		//初始化出度和入度都为0
		int inDegree=0;
		int outDegree=0;
		int Degree=0;
		int DEGREE=0;
		switch(kind){
		case DG://有向图中
			for(int i=0;i<vexNum;i++){
				if(arcs[v][i]==1)//由v指向其他结点的边  出度+1
					outDegree++;
			}
			for(int j=0;j<vexNum;j++){
				if(arcs[j][v]==1)//由其他结点指向v的边  入度+1
					inDegree++;
			}
			System.out.println("顶点"+getVex(v)+"的入度为："+inDegree+" "+"出度为："+outDegree+" ");
			DEGREE=inDegree+outDegree;
			System.out.println("顶点"+getVex(v)+"的度为："+DEGREE);
			break;
		case UDG:
			for(int i=0;i<vexNum;i++){
				if(arcs[v][i]==1)//只要存在边度自加1
					Degree++;	
			}
			System.out.println("顶点"+getVex(v)+"度为"+Degree);
		default:
			break;
			
		
		}	
	}
	//递归调用得到度的方法
	public void getDegree(){
		for(int i=0;i<vexNum;i++){
			getDegree(i);
		}
	}
	//输出矩阵 存在边的话权值为1 不存在的话权值为0
	public void display(){
		for(int i=0;i<vexNum;i++){
			for(int j=0;j<vexNum;j++){
				if(arcs[i][j]==1){//如果这条边存在，通过getVex方法，得出结点1->结点2
					System.out.print(getVex(i)+"->"+getVex(j)+"  ");
				}
			}
		}
		System.out.println();
		System.out.println("该图的存储结构为：");
		for(int i = 0; i < vexNum; i++){
            for(int j = 0; j< vexNum; j++){

                    System.out.print(arcs[i][j] + " ");
            }
            System.out.println();
		}
	}
}