package sort;

//快速排序
public class QuickSort {
    public static <T> T[] quickSort(T[] a) {
        quickSort(a, 0, a.length - 1);
        return a;
    }

    // 范围[s, e]
    private static <T> void quickSort(T[] a, int s, int e) {
        if (s < e) {
            int mid = partition(a, s, e);
            quickSort(a, s, mid - 1);
            quickSort(a, mid + 1, e);
        }
    }
    @SuppressWarnings("unchecked")
    private static <T> int partition(T[] a, int low, int high) {
        T pivot = a[low];// 取第1个作为pivot；也可以增加左，中，右三者取中，然后，将中值存放到a[low]
        while (true) {
            for (; high > low && ((Comparable<T>) a[high]).compareTo(pivot) > 0; --high)
                ;
            if (low == high)
                break;
            a[low++] = a[high];
            for (; low < high && ((Comparable<T>) a[low]).compareTo(pivot) < 0; ++low)
                ;
            if (low == high)
                break;
            a[high--] = a[low];
        }
        a[low] = pivot;
        return low;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Integer[] data = { 9, 2, 13, 4, 5, 8, 7, 6, 1 };
        for (int i : quickSort(data))
            System.out.print(i + " ");
        System.out.println();
    }
}

