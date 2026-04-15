package Sort;

import java.util.Scanner;

public class HeapSort {
	private RecordNode[] r;
	private int curlen;
	public void sift(int low,int high) {
		
		int i=low;
		int j=2*i+1;
		RecordNode temp=r[i];
		while(j<high)
		{
			if(j<high-1&&r[j].key.compareTo(r[j+1].key)>0) {
				j++;
			}
			if(temp.key.compareTo(r[j].key)>0) {
				r[i]=r[j];
				i=j;
				j=2*i+1;
			}
			else {
				j=high+1;
			}
		}
	r[i]=temp;
	}
	 public  void heapSort(int[] arr) {
		RecordNode temp;
		int n=this.curlen;
		for(int i=n/2-1;i>=0;i--)
		{
			sift(i,n);
		}
		for(int i=n-1;i>0;i--) {
			temp=r[0];
			r[0]=r[i];
			r[i]=temp;
			sift(0,i);
		}
	}
	 public static void main(String[] args) {
			// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Scanner sc =new Scanner (System.in);
				int []a=new int[10];
				HeapSort b=new HeapSort();
				for(int i=0;i<10;i++)
				{
					a[i]=sc.nextInt();
				} 
				b.heapSort(a);
				for(int i=0;i<10;i++) {
					System.out.print(a[i]);
				}
	 
	 }
}

