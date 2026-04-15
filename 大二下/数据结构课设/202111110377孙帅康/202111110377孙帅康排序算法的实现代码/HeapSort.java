package sort;

//堆排序
public class HeapSort<T> {
    private T[] data;
    int size;

    // 大顶堆
    @SuppressWarnings("unchecked")
    private void siftDown(int k, T x) {// 将x放到数组的位置k，向下进行调整
        Comparable<T> key = (Comparable<T>) x;
        int half = size >>> 1; // 最后一个有孩子的结点的编号
        while (k < half) {
            int child = (k << 1) + 1; // 左孩子的编号
            T c = data[child];
            int right = child + 1;// 右孩子的编号
            if (right < size && ((Comparable<T>) c).compareTo(data[right]) < 0)
                c = data[child = right];// c是左右孩子最大的值，child是这个孩子的编号
            if (key.compareTo(c) > 0)
                break;// x比左右孩子都大
            data[k] = c;
            k = child;
        }
        data[k] = x;
    }

    private void heapify() {
        for (int i = (size >>> 1) - 1; i >= 0; i--)
            siftDown(i, data[i]);
    }

    public T[] heapSort(T[] a) {
        data = a;
        size = data.length;
        heapify();
        for (int i = data.length - 1; i > 0; i--) {
            T tmp = data[i];
            data[i] = data[0];
            size--;
            siftDown(0, tmp);
        }
        return data;
    }

    public static void main(String[] args) {
        HeapSort<Integer> ip1 = new HeapSort<>();
        Integer[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
        for (int i : ip1.heapSort(a))
            System.out.print(i + " ");
        System.out.println();
    }
}

