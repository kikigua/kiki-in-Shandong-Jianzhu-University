package Sort;
import java.util.Random;
import java.util.Scanner;
public class Test
{
	public  static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner (System.in);	
		int []arr=new int[10];
		for(int i=0;i<10;i++)
		{
			arr[i]=sc.nextInt();
		} 
			
		System.out.println("7-¿ìËÙÅÅÐò");
		System.out.println("6-¶ÑÅÅÐò");
		System.out.println("5-¹é²¢ÅÅÐò");
		System.out.println("4-Ã°ÅÝÑ­»·");
		System.out.println("3-Ñ¡ÔñÅÅÐò");
		System.out.println("2-Ï£¶ûÅÅÐò");
		System.out.println("1-Ö±½Ó²åÈëÅÅÐò");
		int x=sc.nextInt();
		
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
				h.heapSort(arr);
				for(int i=0;i<10;i++) {
					System.out.print(arr[i]+" ");
				}
	}
	
	}
