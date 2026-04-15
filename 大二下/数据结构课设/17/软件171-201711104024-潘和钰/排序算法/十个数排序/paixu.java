package ks1;
import java.util.Scanner;

public class paixu {
public static void main(String[] args)throws Exception {
	System.out.print("请输入数据的个数：");
	Scanner input =new Scanner(System.in);
	int n=input.nextInt();
	int[] a =new int[n];
	System.out.print("请输入数据：");
	for(int i=0;i<a.length;i++) {
	a[i]=input.nextInt();
	}
int [] d= {5,3,1};
int maxSize=20;

SeqList L=new SeqList(maxSize);
for (int i=0;i<a.length;i++) {
	RecordNode r=new RecordNode(a[i]);
	L.insert(i, r);
}
System.out.println("排序前：");
for(int i=0;i<a.length;i++) {
	System.out.print(a[i]+" ");
	System.out.print(" ");
}
System.out.println();
System.out.println("请选择排序方法：");
System.out.println("1-直接插入排序：");
System.out.println("2-希尔排序："); 
System.out.println("3-冒泡排序：");
System.out.println("4-快速排序：");
System.out.println("5-直接选择排序：");
System.out.println("6-堆排序：");
System.out.println("7-递归排序：");
Scanner s=new Scanner(System.in);
int xz=s.nextInt();
switch(xz) {
case 1:
L.insertSort();
break;
case 2:
	L.shellSort(d);
	break;
case 3:
	L.bubbleSort();
	break;
case 4:
	L.quickSort();
	break;
case 5:
	L.selectSort();
	break;
case 6:
	L.heapSort();
	break;
case 7:
	L.mergeSort();
	break;
}
System.out.println("排序后：");
L.display();
}

}
