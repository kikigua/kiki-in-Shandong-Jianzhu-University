package TIMU1;

import java.util.Arrays;


public class SqList  {

	public RecordNode r[];//顺序表记录节点数组
	public int curlen;   //顺序表长度
	
 public SqList(int maxSize){
	 curlen=0;
	 r=new RecordNode[maxSize];
 }
	public int length() {
		return curlen;
	}
	
	public void display() {
		for(int j=0;j<curlen;j++){
			System.out.print(r[j].key+" ");
		}
	}
	
	public  void SortComparable(int maxSize){
		this.r=new RecordNode[maxSize];
		this.curlen=0;
	}
	//在当前顺序表的第i个节点之前插入一个RecordNode类型的结点x
	public void insert(int i,RecordNode x)throws Exception{
		if(curlen==r.length){
			throw new Exception("顺序表已满");
		}
		if(i<0||i>curlen){
			throw new Exception("插入的位置不合理");
		}
		for(int j=curlen;j>i;j--){
			r[j]=r[j-1];
		}
		r[i]=x;
		this.curlen++;
	}
	
	//直接插入排序
			public void insertSort(){
			RecordNode temp;
			int i,j;
			for(i=1;i<this.curlen;i++){
				temp=r[i];
				for(j=i-1;j>=0&&temp.key.compareTo(r[j].key)<0;j--){
					r[j+1]=r[j];
				}
				r[j+1]=temp;
			}
		}
		//希尔排序
			public void shellSort(int[] d){ //d为增量数组
				RecordNode temp;
				int i,j;
				//控制增量，增量减半，若干趟扫描
				for(int k=0;k<d.length;k++){
					int  dk=d[k];
					for(i=dk;i<this.curlen;i++){
						temp=r[i];
						for(j=i-dk;j>=0&&temp.key.compareTo(r[j].key)<0;j-=dk){
							r[j+dk]=r[j];
						}
						r[j+dk]=temp;
					}
				}
			}
			//改进的冒泡排序
			public void bubbleSort(){
				RecordNode temp;//辅助结点
				boolean flag=true;//是否交换的标记
				for(int i=1;i<this.curlen&&flag;i++){
					flag=false;
					for(int j=0;j<this.curlen-i;j++){//一次比较、交换
						if(r[j].key.compareTo(r[j+1].key)>0){
							temp=r[j];
							r[j]=r[j+1];
							r[j+1]=temp;
							flag=true;
						}
					}
				}
			}
			//快速排序
			public int Partition(int i,int j){
				RecordNode pivot=r[i];
				while(i<j){
					while(i<j&&pivot.key.compareTo(r[j].key)<=0){
						j--;
					}
					if(i<j){
						r[i]=r[j];
						i++;
					}
					while(i<j&&pivot.key.compareTo(r[i].key)>0){
						i++;
					}
						if(i<j){
							r[j]=r[i];
							j--;
						}
					}
				r[i]=pivot;
				
				return i;
				}
			public void qSort(int low,int high){
				if(low<high){
					int pivotloc =Partition(low,high);
					qSort(low,pivotloc-1);
					qSort(pivotloc+1,high);
				}
			}
			public void quickSort(){
				qSort(0,this.curlen-1);
			}
			//直接选择排序
			public void selecrSort(){
				RecordNode temp;
				for(int i=0;i<this.curlen-1;i++){
					int min=1;
					for(int j=i+1;j<this.curlen;j++){
						if(r[j].key.compareTo(r[min].key)<0){
							min=j;
						}
					}
					if(min!=i){
						temp=r[i];
						r[i]=r[min];
						r[min]=temp;
					}
				}
			}
			//堆排序
			public void sift(int low,int high){
				int i=low;
				int j=2*i+1;
				RecordNode temp=r[i];
				while(j<high){
					if(j<high-1&&r[j].key.compareTo(r[j+1].key)>0){
						j++;
					}
					if(temp.key.compareTo(r[j].key)>0){
						r[i]=r[j];
						i=j;
						j=2*i+1;
					}
					else{
						j=high+1;
					}
				}
				r[i]=temp;
			}
			public void heapSort(){
				int n=this.curlen;
				RecordNode temp;
				for(int i=n/2-1;i>=0;i--){
					sift(i,n); 
				}
				for(int i=n-1;i>0;i--){
					temp=r[0];
					r[0]=r[i];
					r[i]=temp;
					sift(0,i);
				}
			}
			//归并排序
			public void merge(RecordNode[] r,RecordNode[] order,int h,int m,int t){
				int i=h,j=m+1,k=h;
				while(i<=m&&j<=t){
					if(r[i].key.compareTo(r[j].key)<=0){
						order[k++]=r[i++];
					}else{
						order[k++]=r[j++];
					}
				}
				while(i<=m){
					order[k++]=r[i++];
				}
				while(j<=t){
					order[k++]=r[j++];
					
				}
				
			}
			public void mergepass(RecordNode[]r,RecordNode[] order,int s,int n){
				int p=0;
				while(p+2*s-1<=n-1){
					merge(r,order,p,p+s-1,p+2*s-1);
					p+=2*s;
				}
				if(p+s-1<n-1){
					merge(r,order,p,p+s-1,n-1);
				}else{
					for(int i=p;i<=n-1;i++){
						order[i]=r[i];
					}
				}
			}
			public void mergeSort(){
				int s=1;
				int n=this.curlen;
				RecordNode[] temp=new RecordNode[n];
				while(s<n){
					mergepass(r,temp,s,n);
					//display();
					s*=2;
					mergepass(temp,r,s,n);
					//display();
					s*=2;
				}
			}
		}   