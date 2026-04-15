package priorityqueue;

//小顶堆实现的最小优先队列，具有decrease功能
public class PriorityQueueWithDecrease<T> {
	private Object[] queue;
	private int size = 0;

	// Entry封装了数据T，增加了在堆中的位置
	public static class Entry<T> {
		private int index;// 堆中的位置
		private T data;// 数据，需要能比较大小

		public Entry(T value) {
			this.data = value;
		}

		public int getIndex() {
			return index;
		}

		public void setData(T data) {
			this.data = data;
		}

		public T getData() {
			return data;
		}

		public String toString() {
			return index + ":" + data;
		}
	}

	public PriorityQueueWithDecrease(int maxSize) {
		queue = new Object[maxSize];
	}

	// 新增加的方法，位置k的优先级减少了，需要重新调整堆
	@SuppressWarnings("unchecked")
	public void decrease(int index) {
		Entry<T> e = (Entry<T>) queue[index];
		siftUp(index, e);
	}

	@SuppressWarnings("unchecked")
	private void siftDown(int k, Entry<T> x) {
		Comparable<? super T> key = (Comparable<? super T>) x.data;
		int half = size >>> 1;
		while (k < half) {
			int child = (k << 1) + 1;
			Entry<T> c = (Entry<T>) queue[child];
			int right = child + 1;
			if (right < size && ((Comparable<? super T>) c.data).compareTo(((Entry<T>) queue[right]).data) > 0)
				c = (Entry<T>) queue[child = right];
			if (key.compareTo(c.data) < 0)
				break;
			c.index = k;// 设置数据在堆中的位置
			queue[k] = c;
			k = child;
		}
		x.index = k;// 设置数据在堆中的位置
		queue[k] = x;
	}

	@SuppressWarnings("unchecked")
	private void siftUp(int k, Entry<T> x) {
		Comparable<? super T> key = (Comparable<? super T>) x.data;
		while (k > 0) {
			int parent = (k - 1) >>> 1;
			Entry<T> e = (Entry<T>) queue[parent];
			if (key.compareTo(e.data) > 0)
				break;
			e.index = k;// 设置数据在堆中的位置
			queue[k] = e;
			k = parent;
		}
		x.index = k;// 设置数据在堆中的位置
		queue[k] = x;
	}

	public boolean offer(Entry<T> e) {
		if (e == null)
			throw new NullPointerException();
		int i = size;
		if (i >= queue.length)
			throw new IllegalStateException(String.valueOf(i));
		size = i + 1;
		if (i == 0)
			queue[0] = e;// e的index=0,Entry对象初始化时已经设置
		else
			siftUp(i, e);
		return true;
	}

	@SuppressWarnings("unchecked")
	public T poll() {
		if (size == 0)
			return null;
		int s = --size;
		Entry<T> result = (Entry<T>) queue[0];
		result.index = -1;// 测试用
		Entry<T> x = (Entry<T>) queue[s];
		queue[s] = null;
		if (s != 0)
			siftDown(0, x);
		return result.data;
	}

	@SuppressWarnings("unchecked")
	public T peek() {
		return (size == 0) ? null : ((Entry<T>) queue[0]).data;
	}

	public void clear() {
		for (int i = 0; i < size; i++)
			queue[i] = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < size; i++)
			str.append(queue[i] + "  ");
		return str.toString();
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Integer[] distance = { 8, 1, 10, 2, 9, 7, 6, 4, 5 };
		Entry<Integer>[] data = (Entry<Integer>[]) new Entry<?>[distance.length];
		for (int i = 0; i < distance.length; i++) {
			data[i] = new Entry<>(distance[i]);
		}
		PriorityQueueWithDecrease<Integer> queue = new PriorityQueueWithDecrease<>(distance.length);
		for (int i = 0; i < distance.length; i++) {
			queue.offer(data[i]);
		}
		System.out.println(queue);
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue);
		for (Entry<Integer> i : data) {
			System.out.print(i + " ");
		}
		System.out.println();
		data[8].setData(3);
		queue.decrease(data[8].getIndex());
		System.out.println();
		System.out.println(queue);
		while (queue.size() != 0) {
			Integer p = queue.poll();
			System.out.print(p + " ");
		}
		System.out.println();
	}
}
