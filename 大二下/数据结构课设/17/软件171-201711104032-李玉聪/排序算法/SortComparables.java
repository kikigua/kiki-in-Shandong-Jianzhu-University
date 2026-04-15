package TIMU1;
import java.util.Scanner;

public class SortComparables {
	
	public static void main(String[] args)throws Exception{
			Scanner in=new Scanner(System.in);
			System.out.println("请输入需要排序的个数：");
			int n=in.nextInt();
			System.out.println("请输入需要排序的数：");
			int[] d=new int[n];
			for(int i=0;i<n;i++) {
				d[i]=in.nextInt();
			}
			int[] xier={5,3,1};//增量数组
			int maxSize=20;
			boolean flag=true;
			SqList S=new SqList(maxSize);
			S.SortComparable(maxSize);
			for(int i=0;i<d.length;i++){
				RecordNode r=new RecordNode(d[i]);
				S.insert(S.length(), r);
			}
			System.out.println("排序前：");
			S.display();
			
				S.insertSort();//直接插入排序
				System.out.println("\n进行直接插入排序后：");
				S.display();
				
				S.shellSort(xier);//希尔排序
				System.out.println("\n进行希尔排序后：");
				S.display();
				
				S.bubbleSort();//冒泡排序
				System.out.println("\n进行改进的冒泡排序后：");
				S.display();

				S.quickSort();//快速排序
				System.out.println("\n进行快速排序后：");
				S.display();
			
				S.selecrSort();//直接选择排序
				System.out.println("\n进行直接选择排序后：");
				S.display();
			
				S.heapSort();//堆排序
				System.out.println("\n进行堆排序后：");
				S.display();
				
				S.mergeSort();//归并排序
				System.out.println("\n进行归并排序后：");
				S.display();

			}
			
	}



	


