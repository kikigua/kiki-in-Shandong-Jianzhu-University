package Sort;
import java.util.Random;
import java.util.Scanner;
public class Z {
	
			

			public static void main(String[] args) {
				long startTime=System.currentTimeMillis();
				int len=30000;
				int []tmp = null;
				int[] arr=new int[len];
				for(int i=0;i<len;i++)
				{
					arr[i]=(int)(Math.random()*100000);
					
				}
				System.out.println("7-¿ìËÙÅÅÐò");
				System.out.println("6-¶ÑÅÅÐò");
				System.out.println("5-¹é²¢ÅÅÐò");
				System.out.println("4-Ã°ÅÝÑ­»·");
				System.out.println("3-Ñ¡ÔñÅÅÐò");
				System.out.println("2-Ï£¶ûÅÅÐò");
				System.out.println("1-Ö±½Ó²åÈëÅÅÐò");
				Scanner s=new Scanner(System.in);
				int x=s.nextInt();
				int []tmp1=new int[30000];
				HeapSort h=new HeapSort();
				switch(x){
					
					case 7:
						QuickSort.quickSort(arr, 0, arr.length-1);
						break;
					case 6:
						h.heapSort(arr);
						break;
					case 5:
						MergeSort.mergeSort(arr, 0, arr.length-1, tmp1);
						break;
					case 4:
						BubbleSort.bubbleSort(arr);
						break;
					case 3:
						SelectionSort.selectionSort(arr);
						break;
					case 2:
						ShellSort.shellSort(arr);
						break;
					case 1:
						InsertSort.insertSort(arr);
						break;
				}
				for(int i=0;i<len;i++) {
					System.out.println(arr[i]);
					
				}
				long endTime=System.currentTimeMillis();
				System.out.println("ÔËÐÐÊ±¼ä");
				System.out.println(endTime-startTime);
			}
	}


