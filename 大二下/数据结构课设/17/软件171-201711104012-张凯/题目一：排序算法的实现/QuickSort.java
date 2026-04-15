package Sort;

import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
	public  static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner (System.in);	
		int []a=new int[10];
		for(int i=0;i<10;i++)
		{
			a[i]=sc.nextInt();
		} 
		quickSort(a,0,9);
		for(int i=0;i<10;i++) {
			System.out.print(a[i]);
		}
	}
	public static void quickSort(int [] array,int l,int r) {
		if(l<r) {
			int i=l,j=r,x=array[l];
			while(i<j) {
			while(i<j&&array[j]>=x)
				j--;                                                                                                                                     
			if(i<j)
				array[i++]=array[j];
			while(i<j&&array[i]<x)
				i++;
			if(i<j)
				array[j--]=array[i];
			}
		array[i]=x;
		
		quickSort(array,l,i-1);
		quickSort(array,i+1,r);
		}
	}
}
