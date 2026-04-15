package sort;

import java.util.Scanner;

public class ThirtyThousandTest {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        int len=30000;
//        int []tmp = null;
        Integer[] arr= new Integer[len];
        for(int i=0;i<len;i++)
        {
            arr[i]=(int)(Math.random()*100000);

        }
        System.out.println("1-直接插入排序 2-希尔排序 3-直接选择排序 4-冒泡排序 5-归并排序 6-堆排序 7-快速排序8-基数排序");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("请输入所要执行程序的编号");
        Scanner s=new Scanner(System.in);
        int x=s.nextInt();
//        int []tmp1=new int[30000];
        HeapSort h=new HeapSort();
        CardinalitySort<Integer> c = new CardinalitySort<Integer>();
        switch(x){

            case 8:
                c.sort(arr);
                break;
            case 7:
                QuickSort.quickSort(arr);
                break;
            case 6:
                h.heapSort(arr);
                break;
            case 5:
                MergeSort.mergeSort(arr);
                break;
            case 4:
                ExchangingBubbleSort.bubbleSort(arr);
                break;
            case 3:
                SelectSort.selectMinSort(arr);
                break;
            case 2:
                ShellSort.shellSort(arr);
                break;
            case 1:
                InsertSort.insertSort(arr);
                break;
        }
        for(int i=0;i<len;i++) {
            System.out.println(arr[i]);

        }
        long endTime=System.currentTimeMillis();
        System.out.println("该程序的运行时间为");
        System.out.println(endTime-startTime+"ms");
    }
}


