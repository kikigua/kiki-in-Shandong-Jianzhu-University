 package Sort;

import java.util.Scanner;

public class BubbleSort {
	public static void bubbleSort(int []a) {
		for(int i=1;i<a.length;i++)
			for(int j=0;j<a.length-i;j++)
			{
				if(a[j]>a[j+1])
				{
					int temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner (System.in);
		
		int []a=new int[10];
		for(int i=0;i<10;i++)
		{
			a[i]=sc.nextInt();
		} 
		bubbleSort(a);
		for(int i=0;i<10;i++) {
			System.out.print(a[i]+" ");
		}
}
}
