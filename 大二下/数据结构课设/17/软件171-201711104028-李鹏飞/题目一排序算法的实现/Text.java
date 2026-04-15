package 题目一;
import java.util.Random;
import java.util.Scanner;
public class Text{
	
	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		System.out.println("请输入整数的个数：");
		int n=jin.nextInt();
		int[] a=new int[n];
		System.out.println("请输入"+n+"个整数");
		for(int i=0;i<n;i++) {
			a[i]=jin.nextInt();
		}
		//jin.close();
		//System.out.println("各种排序每一趟输出结果：");
		boolean flag=true;
		while (flag){
			System.out.println("系统功能：   创建");
			System.out.println("          1 直接插入排序 ");
			System.out.println("          2 希尔排序 ");
			System.out.println("          3 冒泡排序 ");
			System.out.println("          4 快速排序");
			System.out.println("          5 直接选择排序 ");
			System.out.println("          6 堆排序 ");
			System.out.println("          7 归并排序 ");
			System.out.println("          8 各排序的执行效率和排序");
			System.out.println("          0 退出");
			System.out.println("请输入指令:");
		
		
		switch(jin.nextInt()){
		case 1:
		System.out.println("直接插入排序：");
		new SortTest(a.clone()).insertSort(true);break;
		case 2:
		System.out.println("希尔排序：");
		int[] d={5,3,1};
		new SortTest(a.clone()).shellSort(d,true);break;
		case 3:	
		System.out.println("改进后的冒泡排序：");
		new SortTest(a.clone()).bubbleSort(true);break;
		case 4:
        
		System.out.println("快速排序：");
		new SortTest(a.clone()).quickSort(true);break;
		case 5:
    		System.out.println("直接选择排序");
		new SortTest(a.clone()).selectSort(true);break;
		case 6:
		System.out.println("堆排序：");
		new SortTest(a.clone()).heapSort(true);break;
		case 7:
		System.out.println("归并排序：");
		new SortTest(a.clone()).mergeSort(true);break;
		case 8:  
		System.out.println("随机生成3万个随机数测试结果：");
		new Text().randTest();
		break;
		case 0:
			System.out.println("退出系统");
			
			flag=false;break;
	default:System.out.println("输入有误，请重新输入");
		break;
		}
		}
		}
	
	// 随机生成3万个随机数。并对每种排序算法进行测试
	 
	public static void randTest(){
		Random rand = new Random();
		int n=30000;
		
		int[] a=new int[n];
		int[] tmp;
		long startTime,endTime;
		for(int i=0;i<n;i++) {
			a[i]=rand.nextInt(n)%n;
		}

		tmp=a.clone();
		startTime = System.currentTimeMillis();
		new SortTest(tmp).insertSort(false);
		endTime = System.currentTimeMillis();
		System.out.println("直接插入排序："+(endTime-startTime)+"ms");
		
		tmp=a.clone();
		startTime = System.currentTimeMillis();
		int[] d={5,3,1};
		new SortTest(tmp).shellSort(d, false);
		endTime = System.currentTimeMillis();
		System.out.println("希尔排序："+(endTime-startTime)+"ms");
		
		tmp=a.clone();
		startTime = System.currentTimeMillis();
		new SortTest(tmp).bubbleSort(false);
		endTime = System.currentTimeMillis();
		System.out.println("改进后的冒泡排序："+(endTime-startTime)+"ms");
		
		tmp=a.clone();
		startTime = System.currentTimeMillis();
		new SortTest(tmp).quickSort(false);
		endTime = System.currentTimeMillis();
		System.out.println("快速排序："+(endTime-startTime)+"ms");
		
		tmp=a.clone();
		startTime = System.currentTimeMillis();
		new SortTest(tmp).selectSort(false);
		endTime = System.currentTimeMillis();
		System.out.println("直接选择排序"+(endTime-startTime)+"ms");
		
		tmp=a.clone();
		startTime = System.currentTimeMillis();
		new SortTest(tmp).heapSort(false);
		endTime = System.currentTimeMillis();
		System.out.println("堆排序："+(endTime-startTime)+"ms");
		
		tmp=a.clone();
		startTime = System.currentTimeMillis();
		new SortTest(tmp).mergeSort(false);
		endTime = System.currentTimeMillis();
		System.out.println("归并排序："+(endTime-startTime)+"ms");
		
		/*System.out.println("排序之前的数");
		for(int l=0;l<n;l++) {
			if(l%999==0) {
				System.out.println();
			}
			System.out.println(" "+a[l]);
		}
		System.out.println("排序之后的数");
		for(int o=0;o<n;o++) {
			if(o%999==0) {
				System.out.println();
			}
			System.out.print(" "+tmp[o]);
		
		}
		System.out.println();
		*/
	}
}
