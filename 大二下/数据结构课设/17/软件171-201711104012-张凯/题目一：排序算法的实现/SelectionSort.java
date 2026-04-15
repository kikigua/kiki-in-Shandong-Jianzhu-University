package Sort;

import java.util.Scanner;

public class SelectionSort {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner (System.in);
		
		int []a=new int[10];
		for(int i=0;i<10;i++)
		{
			a[i]=sc.nextInt();
		} 
		selectionSort(a);
		for(int i=0;i<10;i++) {
			System.out.print(a[i]);
		}
	}
	public static void selectionSort(int[] arr) {
		int k = 0;
		int tmp = 0;
		for(int i = 0; i < arr.length - 1; i++) {
			k = i;
			for(int j = i; j < arr.length; j++) {
				if(arr[j] < arr[k]) {
					k = j;
				}
			}
			tmp = arr[i];
			arr[i] = arr[k];
			arr[k] = tmp;
		}
	}
	
}
