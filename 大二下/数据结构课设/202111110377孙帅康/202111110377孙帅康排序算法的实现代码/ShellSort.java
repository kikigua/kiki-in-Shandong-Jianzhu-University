package sort;

//Ï£¶ûÅÅÐò
public class ShellSort {
    public static <T> T[] shellSort(T[] a) {
        int d = a.length;
        while ((d >>>= 1) >= 1) // d = d/2
            shellSort(a, d);
        return a;
    }

    @SuppressWarnings("unchecked")
    private static <T> void shellSort(T[] a, int d) {
        for (int i = d; i < a.length; ++i) {
            T e = a[i];
            int j = i - d;
            for (; j >= 0 && ((Comparable<T>) e).compareTo(a[j]) < 0; j -= d)
                a[j + d] = a[j];
            a[j + d] = e;
        }
    }
    public static void main(String[] args) {
        Integer[] data = { 10, 1, 2, 8, 3, 4, 9, 5, 6, 7, 8 };
        for (int i : shellSort(data))
            System.out.print(i + " ");
        System.out.println();
    }
}
