package ks3;

import java.util.Random;

public class SortTest {
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
    public void insertSort(){
        int  tmp;
        int i,j;
        for(i=1;i<len;i++){
            tmp=a[i];
            for(j=i-1;j>=0&&tmp<a[j];j--)
                a[j+1]=a[j];
            a[j+1]=tmp;
        }
    }
    /**
     * 希尔排序
     */
    public void shellSort(int[] d){
        int tmp,i,j;
        for(int k=0;k<d.length;k++){
            int dk=d[k];
            for(i=dk;i<len;i++){
                tmp=a[i];
                for(j=i-dk;j>=0&&tmp<a[j];j-=dk)
                    a[j+dk]=a[j];
                a[j+dk]=tmp;
            }
         
        }
    }
    /**
     * 改进后的冒泡排序
     */
    public void bubbleSort() {
        boolean tmp = true;
        for (int i=1;i<len&&tmp;i++) {
            tmp=false;
            for (int j=0;j<len-1;j++) { 
                if (a[j]>a[j+1]) { 
                		swap(j, j+1);
                    tmp = true;
                }
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
    }
    int cnt=0;
    public void quickSort(int l,int h){
        if (l<h){
            int pl=Partition(l,h);
            quickSort(l,pl-1);
            quickSort(pl+1,h);
        }
    }
    /**
     * 快速排序
     */
    public void quickSort() {
    		cnt=0;
        quickSort(0,this.len-1);
    }
    /**
     * 选择排序
     */
    public void selectSort() {
           for (int i=0;i<len-1;i++) {
               int min=i; 
               for (int j=i+1;j<len;j++) {
                   if(a[j]<a[min]) min=j;
               }
               if(min!=i) swap(i, min);
             
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
    public void heapSort(){
    	cnt=0;
        for(int i=0;i<len-1;i++){
            sift(len-1-i);
            swap(0,len-1-i);
         
        }
    }
    public void sift(int n){
        for (int i=(n-1)/2;i>=0;i--){
            int k=i;
            while(2*k+1<=n){
                int bn=2*k+1;
                if(bn<n&&a[bn]<a[bn+1])bn++;
                if(a[k]<a[bn]){
                    swap(k,bn);k=bn;
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
     * 基数排序
     * @param flag 是否输出每一趟
     */
    public void radixSort() {
        int div = 1;
        int[][] res = new int[10][len];
        int[] cnt = new int[10];
        int digit;
        for (int i = 1; i <= 3; i++) {
            for (int tmp : a) {
                digit=(tmp/div)%10;
                res[digit][cnt[digit]++]=tmp;
            }
            int k = 0;
            for (int b = 0; b < 10; b++) {
                if(cnt[b]==0)continue;
                for(int w=0;w<cnt[b];w++)
                    a[k++]=res[b][w];
                cnt[b]=0;
            }
           div*=10;
        }
    }
}


