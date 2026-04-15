package ks1;

public class SeqList {
public RecordNode[]r;
public int curlen;
public SeqList(int maxSize) {
	this.r=new RecordNode[maxSize];
	this.curlen=0;
}
//在当前顺序表的第i个结点之前插入一个RecordNode类型的结点x
public void insert(int i,RecordNode x) throws Exception{
	if(curlen==r.length){//判断顺序表是否已满
		System.out.println("顺序表已满！");
		//throw new Exception("顺序表已满！");
	}
	if(i<0||i>curlen){//i小于0或者i大于表长 
		System.out.println("插入位置不合理");
		//throw new Exception("插入位置不合理！");
	}
	for(int j=curlen;j<i;j--){
		r[j]=r[j-1];//插入位置及之后的元素位移
	}
	r[i]=x;//插入x
	this.curlen++;//表的长度加1
}
public int length() {
	for(int j=curlen;j<0;j--){
		r[j]=r[j-1];//插入位置及之后的元素位移
	}
	this.curlen++;//表的长度加1
	return  curlen;
}
public void display() {
	for(int j = 0;j<curlen;j++) {
		System.out.print(" "+r[j].key.toString());
	}
}

public void insertSort() {
	RecordNode temp;
	int i,j;
	for(i=1;i<this.curlen;i++) {
		temp=r[i];
		for(j=i-1;j>=0&&temp.key.compareTo(r[j].key)<0;j--) {
			r[j+1]=r[j];
			
		}
		r[j+1]=temp;
		System.out.print("第"+i+"次循环结果为：");
		for(int k= 0;k<curlen;k++) {
		System.out.print(" "+r[k].key.toString());
		}
		System.out.println();
	}
}
public void shellSort(int[] b) {
	// TODO Auto-generated method stub
	RecordNode temp;
	int i,j;
	for(int k=0;k<b.length;k++) {
		int ak=b[k];
		for(i=ak; i<this.curlen;i++){
			temp=r[i];
			for(j=i - ak;j>=0&&temp.key.compareTo(r[j].key)<0;j-= ak) {
				r[j+ak]=r[j];
			}
			r[j+ak]=temp;
		}
			int q=k+1;
			System.out.print("第"+q+"次循环结果为：");
			for(int m= 0;m<curlen;m++) {
			System.out.print(" "+r[m].key.toString());
			}
			System.out.println();
	
	}
}
public void bubbleSort() {
	// TODO Auto-generated method stub
	RecordNode temp;
	boolean flag=true;
	for(int i=1;i<this.curlen&&flag;i++) {
		flag=false;
		for(int j=0;j<this.curlen-i;j++) {
		if(r[j].key.compareTo(r[j+1].key)>0) {
		temp=r[j];
		r[j]=r[j+1];
		r[j+1]=temp;
		
		
		}
		}
		if(flag=true) {
		System.out.print("第"+i+"次循环结果为：");
		for(int k= 0;k<curlen;k++) {
		System.out.print(" "+r[k].key.toString());
		}
		System.out.println();
		}
		
	}
}
//快排
int cnt=0;
public void quickSort() {
	// TODO Auto-generated method stub
	
qSort(0,this.curlen-1);	
}
public void qSort (int low,int high) {
	boolean flag=true;
	if(low<high) {
		int pivotloc =Partition(low,high);
		if(flag) {
    		System.out.print("第"+(++cnt)+"趟: ");
    		for(int k= 0;k<curlen;k++) {
    			System.out.print(" "+r[k].key.toString());
    			}
    			System.out.println();
    }
		qSort(low,pivotloc-1);
		qSort(pivotloc+1,high);
		
	}
}
public int  Partition(int i,int j){
	RecordNode pivot=r[i];
	while(i<j) {
		while(i<j&&pivot.key.compareTo(r[j].key)<=0) {
			j--;
		}
		if(i<j) {
			r[i]=r[j];
			i++;
		}
		while(i<j&&pivot.key.compareTo(r[i].key)>0) {
			i++;
		}
		if(i<j) {
			r[j]=r[i];
			j--;
		}
	}
	r[i]=pivot;
	return i;
}
//直接排序
public void selectSort() {
	// TODO Auto-generated method stub
	RecordNode temp;
	for(int i=0;i<this.curlen-1;i++) {
		int min=1;
		for(int j=i+1;j<this.curlen;j++) {
			if(r[j].key.compareTo(r[min].key)<0) {
				min=j;
			}
		}
		if(min!=i) {
			temp =r[i];
			r[i]=r[min];
			r[min]=temp;
		}
		int m=i+1;
		System.out.print("第"+m+"次循环结果为：");
		for(int k= 0;k<curlen;k++) {
		System.out.print(" "+r[k].key.toString());
		}
		System.out.println();
	}
	
}
//堆排序
public void sift(int low,int high) {
	int i=low;
	int j=2*i+1;
	RecordNode temp=r[i];
	while(j<high) {
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
public void heapSort() {
	// TODO Auto-generated method stub
	System.out.println("堆排序");
	int n=this.curlen;
	RecordNode temp;
	for(int i=n/2-1;i>=0;i--) {
		sift(i,n);
		
	}
	for(int i=n-1;i>0;i--) {
		temp=r[0];
		r[0]=r[i];
		r[i]=temp;
		sift(0,i);
		int q=n-i;
		System.out.print("第"+q+"次循环结果为：");
		for(int k= 0;k<curlen;k++) {
		System.out.print(" "+r[k].key.toString());
		}
		System.out.println();
	}
}
//归并排序

public void merge(RecordNode[ ] r, RecordNode[ ] order, int h, int m, int t) {
int i=h,j=m+1,k=h;boolean flag=true;
while(i<= m&&j<= t) { 
if (r[i].key.compareTo(r[j].key)<=0){
	order[k++] = r[i++];
	flag=true;
	}
       else {
		order[k++] = r[j++];
		}
}
		while (i<= m) {
		order[k++] = r[ i++ ];
		}
		while (j<= t) {
		order[k++] = r[j++ ];
		}
		if(flag=true) {
	System.out.print("第"+(++cnt)+"次循环结果为：");
	for(int n= 0;n<curlen;n++) {
	System.out.print(" "+r[n].key.toString());
	}
	System.out.println();
	}
}
public void mergepass( RecordNode[ ] r, RecordNode[ ] order, int s, int n) {
	
	int cnt=0;
int p = 0; 
while(p+2 * s-1<=n-1){
merge(r, order, p,p+s-1,p+2*s-1);
p+=2*s;
}
if(p+s-1<n-1) { 
merge(r, order, p,p+s-1,n- 1);

} 

else {
for (int i = p; i<= n- 1; i++){
order[i] = r[i];
}
}
}
public void mergeSort() {
	// TODO Auto-generated method stub
	System. out. println("归并排序");
	int s=1;
	int n = this. curlen;
	RecordNode[] temp = new RecordNode[n]; 
	while (s<n) {
	mergepass(r, temp, s, n);
	s*=2;
	mergepass(temp, r, s, n);
	s*=2;
}
}


  /*public void mergeSort(){
 
	boolean flag=true; 
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
    		shuchu();
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
	public int a[];
	public int len;
	public void shuchu(){
    	for(int i=0;i<len;i++)
    		System.out.printf("%-2d ",a[i]);
    	System.out.println();
    }*/
}
























