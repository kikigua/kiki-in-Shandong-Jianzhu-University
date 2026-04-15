package search;

public class SequenceSearch {
	private SequenceSearch() {// 不允许生成对象

	}

	// a的每个数组元素都存放了数据
	// 如果x与某个数组元素相等，则返回其下标，如果和任何的数组元素都不相等，则返回-1
	// 假设数据没有重复，如果有重复，则找到第一个即可
	public static <K> int sequentialSearch(K[] a, K x) {
		for (int i = 0; i < a.length; i++) {
			if (x.equals(a[i]))
				return i;
		}
		return -1;
	}

	public static int sequentialSearch(int[] a, int x) {
		for (int i = 0; i < a.length; i++) {
			if (x == a[i])
				return i;
		}
		return -1;
	}

	// a除了最后一个数组元素，其他每个数组元素都存放了数据
	// 如果x与某个数组元素相等，则返回其下标，如果和任何的数组元素都不相等，则返回-1
	// 假设数据没有重复，如果有重复，则找到第一个即可
	public static <K> int sequentialSearch1(K[] a, K x) {
		a[a.length - 1] = x; // 哨兵
		int i = 0;
		for (;;) {// 减少了比较运算: i < a.length
			if (x.equals(a[i]))
				break;
			i++;
		}
		return i != a.length - 1 ? i : -1;
	}

	public static int sequentialSearch1(int[] a, int x) {
		a[a.length - 1] = x; // 哨兵
		int i = 0;
		for (;;) {// 减少了比较运算: i < a.length
			if (x == a[i])
				break;
			i++;
		}
		return i != a.length - 1 ? i : -1;
	}

	// 以下将循环的一部分展开，减少了做i++的次数
	public static <K> int sequentialSearch2(K a[], K x) {
		a[a.length - 1] = x;
		int i = -2;// 1次循环比较2个关键字
		for (;;) {
			i += 2;
			if (a[i].equals(x))
				break;
			if (a[i + 1].equals(x)) {
				i++;
				break;
			}
		}
		return i < a.length - 1 ? i : -1;
	}

	public static int sequentialSearch2(int a[], int x) {
		a[a.length - 1] = x;
		int i = -2;// 1次循环比较2个关键字
		for (;;) {
			i += 2;
			if (a[i] == x)
				break;
			if (a[i + 1] == x) {
				i++;
				break;
			}
		}
		return i < a.length - 1 ? i : -1;
	}

//	public static void main(String[] args) {
//		Integer[] a = { 3, 7, 1, 5, 4, 8 };
//		System.out.println(SequenceSearch.sequentialSearch(a, 1));
//		System.out.println(SequenceSearch.sequentialSearch(a, 3));
//		System.out.println(SequenceSearch.sequentialSearch(a, 8));
//		System.out.println(SequenceSearch.sequentialSearch(a, 9));
//		System.out.println("-----------------------------------");
//
//		Integer[] b = { 3, 7, 1, 5, 4, 8, Integer.MAX_VALUE };
//		System.out.println(SequenceSearch.sequentialSearch1(b, 1));
//		System.out.println(SequenceSearch.sequentialSearch1(b, 3));
//		System.out.println(SequenceSearch.sequentialSearch1(b, 8));
//		System.out.println(SequenceSearch.sequentialSearch1(b, 9));
//		System.out.println("-----------------------------------");
//		System.out.println(SequenceSearch.sequentialSearch2(b, 1));
//		System.out.println(SequenceSearch.sequentialSearch2(b, 3));
//		System.out.println(SequenceSearch.sequentialSearch2(b, 8));
//		System.out.println(SequenceSearch.sequentialSearch2(b, 9));
//	}

	public static void main(String[] args) {
		int n = 100000;
		int[] a = new int[n];
		for (int i = 0; i < a.length; i++)
			a[i] = i;
		long start = System.nanoTime();// 纳秒
		for (int i = 0; i < n; i++)
			sequentialSearch(a, n);// 找最后一个
		long end = System.nanoTime();
		System.out.println((end - start) / n);

		int[] b = new int[n + 1];
		for (int i = 0; i < a.length; i++)
			b[i] = i;
		start = System.nanoTime();// 纳秒
		for (int i = 0; i < n; i++)
			sequentialSearch1(b, n);// 找最后一个
		end = System.nanoTime();
		System.out.println((end - start) / n);

	}
}
