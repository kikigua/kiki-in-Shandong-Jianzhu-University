package sort;

import java.lang.reflect.Array;

//归并排序
public class MergeSort {
		// 数组a需要合并的2部分为[l,m]和[m+1,r]，合并的结果存储到数组b从n开始的位置
		@SuppressWarnings("unchecked")
		private static <T> void twoWayMerge(T[] a, T[] b, int lo, int m, int hi, int t) {
			int q = m + 1;
			while (lo <= m && q <= hi) {
				if (((Comparable<T>) a[lo]).compareTo(a[q]) < 0)
					b[t++] = a[lo++];
				else
					b[t++] = a[q++];
			}
			if (lo <= m)
				//数组扩容（将a数组里从索引为lo的元素开始, 复制到数组b里的索引为t的位置, 复制的元素个数为（m-lo+1）个）
				System.arraycopy(a, lo, b, t, m - lo + 1);
			if (q <= hi)
				System.arraycopy(a, q, b, t, hi - q + 1);
		}
	
		// 实现非递归归并排序
		public static <T> T[] mergeSort(T[] a) {
			@SuppressWarnings("unchecked")
			T[] b = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
			// 合并的长度从1开始，逐次加倍
			for (int length = 1; length < a.length; length <<= 1) {
				int t = 0;
				int lo = 0;// 待合并的第1部分的开始位置
				while (lo < a.length) {
					int m = lo + length - 1;// 待合并的第1部分的结束位置
					if (m >= a.length) {// 需要合并的只有1部分
						System.arraycopy(a, lo, b, t, a.length - lo);
						break;
					}
					int hi = m + length;// 待合并的第2部分的结束位置
					if (hi >= a.length)
						hi = a.length - 1;// 需要合并的第2部分比length短
					twoWayMerge(a, b, lo, m, hi, t);
					t += hi - lo + 1;
					lo = hi + 1;
				}
				// 交换2个数组
				T[] tmp = a;
				a = b;
				b = tmp;
	//			System.out.println(Arrays.toString(a));// 输出每一趟排序的结果
			}
			return a;
		}
	
		// 实现递归归并排序
		public static <T> T[] rMergeSort(T[] a) {
		@SuppressWarnings("unchecked")
		T[] b = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
		rMergeSort(a, b, 0, a.length - 1);
		return a;
	    }
		
	    private static <T> void rMergeSort(T[] a, T[] b, int lo, int hi) {
		if (lo == hi)
			return;
			int m = (lo + hi) >>> 1;
			rMergeSort(a, b, lo, m);
			rMergeSort(a, b, m + 1, hi);
			twoWayMerge(a, b, lo, m, hi, lo);
			System.arraycopy(b, lo, a, lo, hi - lo + 1);
	//		System.out.println(Arrays.toString(a));// 输出每一趟排序的结果
		}
	    
	    //测试
	    public static void main(String[] args) {
	        Integer[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
	        for (int i : rMergeSort(data))
	            System.out.print(i + " ");
	        System.out.println();
	    }
}
