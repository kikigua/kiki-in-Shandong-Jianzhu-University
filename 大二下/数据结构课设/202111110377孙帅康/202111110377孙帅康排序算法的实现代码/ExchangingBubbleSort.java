package sort;

//改进后的冒泡排序
public class ExchangingBubbleSort {

    @SuppressWarnings("unchecked")
    public static <T> T[] bubbleSort1(T[] a) {
        for (int i = 0; i < a.length - 1; ++i) {// 每趟最小的数据的位置
            boolean flag = true;// 是否是没有数据交换
            for (int j = a.length - 1; j > i; --j) {// 通过交换找到[0,i]最小的数据
                if (((Comparable<T>) a[j]).compareTo(a[j - 1]) < 0) {
                    T t = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = t;
                    flag = false;
                }
            }
            if (flag)
                break;
        }
        return a;
    }

    // 大的往下
    @SuppressWarnings("unchecked")
    public static <T> T[] bubbleSort2(T[] a) {
        int right = a.length;
        while (right != 1) {
            int t = 0;
            for (int j = 0; j < right - 1; j++) {
                if (((Comparable<T>) a[j]).compareTo(a[j + 1]) > 0) {
                    T aj = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = aj;
                    t = j;
                }
            }
            right = t + 1;
        }
        return a;
    }

    // 小的往上
    @SuppressWarnings("unchecked")
    public static <T> T[] bubbleSort(T[] a) {
        int left = 0;
        while (left != a.length - 1) {
            int t = 0;
            for (int j = a.length - 1; j > left; --j) {
                if (((Comparable<T>) a[j]).compareTo(a[j - 1]) < 0) {
                    T aj = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = aj;
                    t = j;
                }
            }
            if (t == 0)// 没有变化
                break;
            left = t;// 新的变化，与bubbleSort1和bubbleSort2相比，能减少不必要的比较，速度快，这是bubbleSort的正常实现
        }
        return a;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Integer[] data = { 9, 2, 13, 4, 5, 8, 7, 6, 1 };
        for (int i : bubbleSort(data))
            System.out.print(i + " ");
        System.out.println();
    }

}


