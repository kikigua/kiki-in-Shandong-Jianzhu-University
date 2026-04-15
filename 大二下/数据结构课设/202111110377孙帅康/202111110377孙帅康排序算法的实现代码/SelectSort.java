package sort;

//直接选择排序
public class SelectSort {
    @SuppressWarnings("unchecked")
    public static <T> T[] selectMinSort(T[] a) {
        for (int i = 0; i < a.length - 1; ++i) {// 找到最小的，交换到位置i
            int k = i;// 最小数据的下标
            for (int j = i + 1; j <= a.length - 1; ++j) {// 在[i,n]区间找最小的数据
                if (((Comparable<T>) a[k]).compareTo(a[j]) > 0)
                    k = j;
            }
            T t = a[i];
            a[i] = a[k];
            a[k] = t;
        }
        return a;
    }

    public static void main(String[] args) {
        Integer[] data = { 10, 1, 2, 8, 3, 4, 9, 5, 6, 7, 8, 9 };
        for (int i : selectMinSort(data))
            System.out.print(i + " ");
        System.out.println();

    }

}