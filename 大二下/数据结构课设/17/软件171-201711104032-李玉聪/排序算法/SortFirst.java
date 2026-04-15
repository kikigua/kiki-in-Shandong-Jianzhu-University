package TIMU1;

import java.util.Scanner;

public class SortFirst {
	
	static int maxsize=30000;//随机产生3万个数
	static int[] d =new int[maxsize];
	
public void number() throws Exception{
		
		for(int i=0;i<maxsize;i++){
			d[i]=(int)(Math.random()*100);
		}
		
	}
	public static void main(String args[]) throws Exception{
		
		SortFirst in=new SortFirst();
		in.number();
		in.textSortTime(in.createList(d));
	}
	private static SqList createList(int[] d)throws Exception{
		SqList L=new SqList(maxsize);
		RecordNode r;
		for(int i=0;i<d.length;i++){
			r=new RecordNode(d[i]);
			L.insert(L.length(), r);
		}
		return L;
	}
	public  void textSortTime(SqList L) throws Exception{
		long startTime,endTime,testTime=0;
					
					startTime=System.currentTimeMillis();
					L.quickSort();//快速排序
					endTime=System.currentTimeMillis();
					testTime=endTime-startTime;
					System.out.println("快速排序的时间为："+testTime+"毫秒");
		
		            startTime=System.currentTimeMillis();
					L.insertSort();//直接插入排序
					endTime=System.currentTimeMillis();
				    testTime=endTime-startTime;
					System.out.println("直接插入排序的时间为："+testTime+"毫秒");
			
					startTime=System.currentTimeMillis();
			    	int []a={5,3,1};
				    L.shellSort(a);//希尔排序
				    endTime=System.currentTimeMillis();
				    testTime=endTime-startTime;
					System.out.println("希尔排序的时间为："+testTime+"毫秒");
					
				    startTime=System.currentTimeMillis();
					L.bubbleSort();//冒泡排序
					endTime=System.currentTimeMillis();
				    testTime=endTime-startTime;
					System.out.println("改进的冒泡排序的时间为："+testTime+"毫秒");
					
					startTime=System.currentTimeMillis();
					L.selecrSort();//直接选择排序
					endTime=System.currentTimeMillis();
				    testTime=endTime-startTime;
					System.out.println("直接选择排序的时间为："+testTime+"毫秒");
					
					startTime=System.currentTimeMillis();
					L.heapSort();//堆排序
					endTime=System.currentTimeMillis();
				    testTime=endTime-startTime;
					System.out.println("堆排序的时间为："+testTime+"毫秒");
					
					startTime=System.currentTimeMillis();
					L.mergeSort();//归并排序
					endTime=System.currentTimeMillis();
				    testTime=endTime-startTime;
					System.out.println("归并排序的时间为："+testTime+"毫秒");

	}
}
