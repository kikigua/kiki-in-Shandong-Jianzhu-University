package Sort;

import java.util.Scanner;

public class ShellSort {
	public static void shellSort(int []arr) {
		int h=1;
		while(h<arr.length/3) {
			h=3*h+1;
		}
		while(h>0) {
			for(int i=1;i<arr.length;i++){
	            int tempdata = arr[i];
	            int j;
	            for(j=i-1;j>=0;j--){
	                 if(arr[j]>tempdata){
	                     arr[j+1] = arr[j];
	                 }else{
	                     break;
	                 }
	            }
	            arr[j+1] = tempdata;
	        }
			h = (h - 1) / 3;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			Scanner sc =new Scanner (System.in);
			int []a=new int[10];
			for(int i=0;i<10;i++)
			{
				a[i]=sc.nextInt();
			} 
			shellSort(a);
			for(int i=0;i<10;i++) {
				System.out.print(a[i]);
			}	
	}
}
