package timu7;
import java.util.Scanner;
public class Test {
	public static void main(String[] args) {
		IGraph g=null;
		BothGraph g1=new BothGraph();
		Scanner sc=new Scanner(System.in);
		boolean fl=true;
		while(fl){
			System.out.println("请输入想要创建的存储结构");
			System.out.println("1.创建一个以邻接矩阵为存储结构的图         2.创建一个以邻接表为存储结构的图");
			int sel=sc.nextInt();
			switch(sel){
			case 1:
				g=new MGraph();
				g.createGraph();
				g.display();
				break;
			case 2:
				g=new ALGraph();
				g.createGraph();
				g.display();
				break;
				}
		boolean flag=true;//设立一个标志变量flag，让选择功能的语句一直输出
		while(flag){
			System.out.println("请输入想要进行的操作");
			System.out.println("3.增加一条边");
			System.out.println("4.删除一条边");
			System.out.println("5.增加一个顶点");
			System.out.println("6.删除一个顶点");
			System.out.println("7.求各顶点的度");
			System.out.println("8.存储结构的转换");
			System.out.println("9.判断图的连通性并输出连通分量");
			System.out.println("10.判断两顶点是否存在简单路径");
			System.out.println("11.退出");
			int select=sc.nextInt();
			switch(select){
			case 1:
				g=new MGraph();
				g.createGraph();
				g.display();
				break;
			case 2:
				g=new ALGraph();
				g.createGraph();
				g.display();
				break;
			case 3:
				g.addArc();
				g.display();
				break;

			case 4:
				g.deleteArc();
				g.display();
				break;
			case 5:
				g.addVex();
				g.display();
				break;
			case 6:
				g.deleteVex();
				g.display();
				break;
			case 7:
				g.getDegree();
				break;
			case 8:
				System.out.println("请输入你要选择的转换方式1或2！");
				System.out.println("1：从邻接矩阵转为邻接表 ；2：从邻接表转为邻接矩阵 ");
				int select1=sc.nextInt();
				//选择两种转变方式，1：从邻接矩阵转为邻接表 ；2：从邻接表转为邻接矩阵
				switch(select1){
				case 1:
					g1.convertMToAL((MGraph) g).display();
					break;
				case 2:
					g1.convertALToM((ALGraph) g).display();
					break;
				}
				break;
			case 9:
				g1.judge_BFS(g);
				break;
			case 10:
				System.out.println("请输入所查找简单路径的两顶点");
				int v=g.locateVex(sc.next());
				int u=g.locateVex(sc.next());
				g1.Path(g, v, u);
				break;
			 case 11:
                 flag=false;
                 break;
             default:
                 break;
			}				
		}
	}
		}}