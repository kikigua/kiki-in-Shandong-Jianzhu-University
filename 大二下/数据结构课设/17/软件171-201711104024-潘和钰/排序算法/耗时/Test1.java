package ks3;
import java.util.Scanner;
import java.util.Random;

public class Test1 {
		
		/**
		 * 随机生成3万个随机数。并对每种排序算法进行测试
		 */
	public static void main(String[] args){
		System.out.println("随机生成三万个随机数测试结果：");
		randTest();
		
	}
		public static void randTest(){
			Random rand = new Random();
			int n=30000;
			
			int[] a=new int[n];
			int[] tmp;
			long startTime,endTime;
			for(int i=0;i<n;i++) {
				a[i]= (int) (Math. random()*30000
						);
			}

			tmp=a.clone();
			startTime = System.currentTimeMillis();
			new SortTest(tmp).insertSort();
			endTime = System.currentTimeMillis();
			System.out.println("直接插入排序："+(endTime-startTime)+"ms");
			
			tmp=a.clone();
			startTime = System.currentTimeMillis();
			int[] d={5,3,1};
			new SortTest(tmp).shellSort(d);
			endTime = System.currentTimeMillis();
			System.out.println("希尔排序："+(endTime-startTime)+"ms");
			
			tmp=a.clone();
			startTime = System.currentTimeMillis();
			new SortTest(tmp).bubbleSort();
			endTime = System.currentTimeMillis();
			System.out.println("改进后的冒泡排序："+(endTime-startTime)+"ms");
			
			tmp=a.clone();
			startTime = System.currentTimeMillis();
			new SortTest(tmp).quickSort();
			endTime = System.currentTimeMillis();
			System.out.println("快速排序："+(endTime-startTime)+"ms");
			
			tmp=a.clone();
			startTime = System.currentTimeMillis();
			new SortTest(tmp).selectSort();
			endTime = System.currentTimeMillis();
			System.out.println("直接选择排序"+(endTime-startTime)+"ms");
			
			tmp=a.clone();
			startTime = System.currentTimeMillis();
			new SortTest(tmp).heapSort();
			endTime = System.currentTimeMillis();
			System.out.println("堆排序："+(endTime-startTime)+"ms");
			
			tmp=a.clone();
			startTime = System.currentTimeMillis();
			new SortTest(tmp).mergeSort(false);
			endTime = System.currentTimeMillis();
			System.out.println("归并排序："+(endTime-startTime)+"ms");
			
			tmp=a.clone();
			startTime = System.currentTimeMillis();
			new SortTest(tmp).radixSort();
			endTime = System.currentTimeMillis();
			System.out.println("链式基数排序："+(endTime-startTime)+"ms");
			/*for(int q=0;q<n;q++) {
				System.out.println(a[q]);
			}
			System.out.println("结束。");
		for(int m=0;m<n;m++) {
				System.out.println(tmp[m]);
		}*/
		}
}
