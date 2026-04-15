package sort;

import java.util.Comparator;

//直接插入排序
public class InsertSort {
    // a[0],..., a[length-1]存放数据
    @SuppressWarnings("unchecked")
    public static <T> T[] insertSort(T[] a) {// 使用T的自然序，即它实现的compareTo
        for (int i = 1; i < a.length; ++i) {
            T ai = a[i];
            int j;
            for (j = i - 1; j >= 0 && ((Comparable<T>) ai).compareTo(a[j]) < 0; j--)
                a[j + 1] = a[j];
            a[j + 1] = ai;
        }
        return a;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] insertSortSentinel(T a[]) { // 使用a[0]作为哨兵
        for (int i = 1; i < a.length; i++) {
            a[0] = a[i];
            int j;
            for (j = i - 1; ((Comparable<T>) a[0]).compareTo(a[j]) < 0; j--)
                a[j + 1] = a[j];
            a[j + 1] = a[0];
        }
        return a;
    }

    public static <T> T[] insertSort(T[] a, Comparator<T> cmp) {// 使用其它的序
        for (int i = 1; i < a.length; i++) {
            T ai = a[i];
            int j;
            for (j = i - 1; j >= 0 && cmp.compare(ai, a[j]) < 0; j--)
                a[j + 1] = a[j];
            a[j + 1] = ai;
        }
        return a;
    }


    public static int[] insertSort(int[] a) {// 针对基本数据类型
        for (int i = 1; i < a.length; i++) {
            int ai = a[i];
            int j;
            for (j = i - 1; j >= 0 && ai < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = ai;
        }
        return a;
    }

    public static void main(String[] args) {
        Integer[] data = { 9, 1, 8, 2, 3, 10, 4, 5, 6, 7, 9 };
//		insertSort(data, (l, r)-> Integer.compare(l, r));
        for (int i : insertSort(data))
            System.out.print(i + " ");
        System.out.println();
    }
}


