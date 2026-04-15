package Sort;

import java.util.Scanner;

public class MergeSort {
	public static void main(String[] args) {
			// TODO Auto-generated method stub
			Scanner sc =new Scanner (System.in);
			int []a=new int[10];
			int[] tmp = new int[a.length]; 
			for(int i=0;i<10;i++)
			{
				a[i]=sc.nextInt();
			}
			mergeSort(a,0,a.length-1,tmp);
			for(int i=0;i<10;i++) {
				System.out.print(a[i]);
			}
	}
	
	public static void merge(int[] arr,int low,int mid,int high,int[] tmp){
		int i = 0;
		int j = low,k = mid+1;  
		while(j <= mid && k <= high){
			if(arr[j] < arr[k]){ 
				tmp[i++] = arr[j++];
			}else{
				tmp[i++] = arr[k++];
			}
		}
		//若左边序列还有剩余，则将其全部拷贝进tmp[]中
		while(j <= mid){   
			tmp[i++] = arr[j++];
		}
		//若友边序列还有剩余，则将其全部拷贝进tmp[]中
		while(k <= high){

			tmp[i++] = arr[k++];
		}
		for(int t=0;t<i;t++){
			arr[low+t] = tmp[t];
		}
	}
	public static void mergeSort(int[] arr,int low,int high,int[] tmp){
		if(low<high){
			int mid = (low+high)/2;
			mergeSort(arr,low,mid,tmp); 
			mergeSort(arr,mid+1,high,tmp);  
			merge(arr,low,mid,high,tmp);    
		}
	}	
	
}




