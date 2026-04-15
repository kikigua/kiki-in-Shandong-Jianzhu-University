package priorityqueue;

//大顶堆实现的最大优先队列
public class PriorityQueueMax<T> {
	private Object[] queue;
	private int size = 0;

	public PriorityQueueMax(int maxSize) {
		queue = new Object[maxSize];
	}

	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	public T peek() {
		return (size == 0) ? null : (T) queue[0];
	}

	@SuppressWarnings("unchecked")
	public T poll() {
		if (size == 0)
			return null;
		int s = --size;
		T result = (T) queue[0];
		T x = (T) queue[s];// 队列中最后的数据元素
		queue[s] = null;
		if (s != 0)
			siftDown(0, x);
		return result;
	}

	public boolean offer(T e) {
		if (e == null)
			throw new NullPointerException();
		int i = size;
		if (i >= queue.length)
			throw new IllegalStateException();
		size = i + 1;
		if (i == 0)
			queue[0] = e;
		else
			siftUp(i, e);
		return true;
	}

	@SuppressWarnings("unchecked")
	private void siftDown(int k, T x) {// 将x放到k号结点，即数组的位置k，向下进行调整
		Comparable<? super T> key = (Comparable<? super T>) x;
		int half = size >>> 1; // 最后一个有孩子的结点的编号
		while (k < half) {
			int child = (k << 1) + 1; // 左孩子的编号
			Object c = queue[child];
			int right = child + 1;// 右孩子的编号
			if (right < size && ((Comparable<? super T>) c).compareTo((T) queue[right]) < 0)
				c = queue[child = right];// 左右孩子中最大的
			if (key.compareTo((T) c) > 0)
				break;// 比左右孩子都大
			queue[k] = c;
			k = child;
		}
		queue[k] = key;
	}

	@SuppressWarnings("unchecked")
	private void siftUp(int k, T x) {// 将x放到数组的位置k，向上进行调整
		Comparable<? super T> key = (Comparable<? super T>) x;
		while (k > 0) {
			int parent = (k - 1) >>> 1;// 父结点的编号
			Object e = queue[parent];
			if (key.compareTo((T) e) < 0)
				break;// 比父结点小
			queue[k] = e;
			k = parent;
		}
		queue[k] = key;
	}

	public void clear() {
		for (int i = 0; i < size; i++)
			queue[i] = null;
		size = 0;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < size; i++)
			str.append(queue[i] + "  ");
		return str.toString();
	}

	public static void main(String[] args) {

		PriorityQueueMax<Integer> ip1 = new PriorityQueueMax<>(10);
		ip1.offer(12);
		ip1.offer(10);
		ip1.offer(9);
		ip1.offer(30);
		System.out.println(ip1);
		System.out.println(ip1.peek());
		System.out.println(ip1.poll());
		System.out.println(ip1.size());
		System.out.println(ip1.poll());
		System.out.println(ip1.size());

		ip1.offer(8);

		System.out.println(ip1.size());
		System.out.println(ip1.poll());
	}
}
