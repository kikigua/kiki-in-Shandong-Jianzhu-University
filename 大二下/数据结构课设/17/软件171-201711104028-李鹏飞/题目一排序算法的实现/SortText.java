 package 题目一;
import java.util.*;
class SortTest{
	public int a[];
	public int len;
	public SortTest(int a[]){
		this.a=a;
		len=a.length;
	}
	/**
	 * 直接插入排序
	 * @param flag 是否输出每一趟
	 */
    public void insertSort(boolean flag){
        int tmp,i,j;
        for(i=1;i<len;i++){
            tmp=a[i];
            for(j=i-1;j>=0&&tmp<a[j];j--)
                a[j+1]=a[j];
            a[j+1]=tmp;
            if(flag) {
            		System.out.print("第"+i +"趟: ");
            		display();
            }
        }
    }
    /**
     * 希尔排序
     */
    public void shellSort(int[] d,boolean flag){
        int tmp,i,j;
        for(int k=0;k<d.length;k++){
            int dk=d[k];
            for(i=dk;i<len;i++){
                tmp=a[i];
                for(j=i-dk;j>=0&&tmp<a[j];j-=dk)
                    a[j+dk]=a[j];
                a[j+dk]=tmp;
            }
            if(flag) {
            		System.out.print("dk="+dk+":");
            		display();
            }
        }
    }
    /**
     * 改进后的冒泡排序
     */
    public void bubbleSort(boolean flag) {
        boolean tmp = true;
        for (int i=1;i<len&&tmp;i++) {
            tmp=false;
            for (int j=0;j<len-1;j++) { 
                if (a[j]>a[j+1]) { 
                		swap(j, j+1);
                    tmp = true;
                }
            }
            if(flag) {
            		System.out.print("第"+i +"趟: ");
            		display();
            }
        }
    }
    public int Partition(int i,int j){
        int p=a[i];
        while(i<j){
            while(i<j&&p<=a[j])j--;
            if(i<j){a[i]=a[j];i++;}
            while(i<j&&p>a[i])i++;
            if(i<j){a[j]=a[i];j--;}
        }
        a[i]=p;
        return i;
    }/**
     * 快速排序
     */
    int cnt=0;
    public void quickSort(int l,int h,boolean flag){
        if (l<h){
            int pl=Partition(l,h);
            if(flag) {
            		System.out.print("第"+(++cnt)+"趟: ");
            		display();
            }
            quickSort(l,pl-1,flag);
            quickSort(pl+1,h,flag);
        }
    }
    
    public void quickSort(boolean flag) {
    		cnt=0;
        quickSort(0,this.len-1,flag);
    }
    /**
     * 选择排序
     */
    public void selectSort(boolean flag) {
           for (int i=0;i<len-1;i++) {
               int min=i; 
               for (int j=i+1;j<len;j++) {
                   if(a[j]<a[min]) 
                	   min=j;
               }
               if(min!=i) 
            	   swap(i, min);
               if(flag) {
            	   		System.out.print("第"+(i+1)+"趟: ");
            	   		display();
               }
           }
       }
    /**
     * 交换元素i和元素j
     */
    public void swap(int i,int j){
        if(i==j)return;
        a[i]=a[i]+a[j];
        a[j]=a[i]-a[j];
        a[i]=a[i]-a[j];
    }
    /**
     * 堆排序
     */
    public void heapSort(boolean flag){
    	cnt=0;
        for(int i=0;i<len-1;i++){
            sift(len-1-i);
            swap(0,len-1-i);
            if(flag) {
            		System.out.print("第"+(++cnt)+"趟: ");
            		display();
            }
        }
    }
    public void sift(int n){
        for (int i=(n-1)/2;i>=0;i--){
            int k=i;
            while(2*k+1<=n){
                int bn=2*k+1;
                if(bn<n&&a[bn]<a[bn+1])bn++;
                if(a[k]<  a[bn]){
                    swap(k,bn);
                    k=bn;
                }else break;
            }
        }
    }
    /**
     * 归并排序
     */
    public void mergeSort(boolean flag){
    	cnt=0;
    	mergeSort(a,0,len-1,flag);
    }
    public void mergeSort(int[] a,int l,int r,boolean flag) {  
        if(l>=r)return;  
        int mid=(l+r)/2;
        mergeSort(a,l,mid,flag);
        mergeSort(a,mid+1,r,flag);
        merge(a,l,mid,r);
        if(flag) {
        		System.out.print("第"+(++cnt)+"趟: ");
        		display();
        }
    }
    public void merge(int[] a,int l,int ctr,int r){
        int[] arr=new int[len];
        int mid=ctr+1,td=l,tmp=l;  
        while(l<=ctr&&mid<=r){
            if(a[l]<=a[mid])arr[td++]=a[l++];  
            else arr[td++]=a[mid++];  
        }  
        while(mid<=r)arr[td++]=a[mid++];
        while(l<=ctr)arr[td++]=a[l++];
        while(tmp<=r)a[tmp]=arr[tmp++];
    }
    
    /**
     * 输出排序后的数组
     */
    public void display(){
    	for(int i=0;i<len;i++)
    		System.out.printf("%-2d ",a[i]);
    	System.out.println();
    }
}