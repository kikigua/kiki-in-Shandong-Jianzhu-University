package search;

public class BinarySearch {
	private BinarySearch() {

	}

	public static <K extends Comparable<? super K>> int binarySearch(K[] a, K x) {
		int l = 0;
		int u = a.length - 1;
		while (l <= u) {
			int i = l + (u - l >> 1);
			if (x.compareTo(a[i]) == 0)
				return i;
			else if (x.compareTo(a[i]) > 0)
				l = i + 1;
			else
				u = i - 1;
		}
		return -1;
	}

	public static <K extends Comparable<? super K>> int binarySearchByRecursion(K[] a, K x) {
		return binarySearchByRecursion(a, 0, a.length - 1, x);
	}

	public static <K extends Comparable<? super K>> int binarySearchByRecursion(K[] a, int l, int u, K x) {
		if (l > u)
			return -1;
		int i = l + (u - l >> 1);
		if (x.compareTo(a[i]) == 0)
			return i;
		else if (x.compareTo(a[i]) > 0)
			return binarySearchByRecursion(a, i + 1, u, x);
		else
			return binarySearchByRecursion(a, l, i - 1, x);
	}

	public static void main(String[] args) {
		Integer[] b = { 2, 3, 4, 5, 7, 8 };
		System.out.println(BinarySearch.binarySearch(b, 2));
		System.out.println(BinarySearch.binarySearch(b, 4));
		System.out.println(BinarySearch.binarySearch(b, 8));
		System.out.println(BinarySearch.binarySearch(b, 1));
		System.out.println(BinarySearch.binarySearch(b, 9));
		System.out.println("-----------------------------------");
		System.out.println(BinarySearch.binarySearchByRecursion(b, 2));
		System.out.println(BinarySearch.binarySearchByRecursion(b, 4));
		System.out.println(BinarySearch.binarySearchByRecursion(b, 8));
		System.out.println(BinarySearch.binarySearchByRecursion(b, 1));
		System.out.println(BinarySearch.binarySearchByRecursion(b, 9));
	}

}
