package map;

import java.util.Objects;

// 三叉链表存储，便于自底向上对树进行平衡，类库的TreeMap就是使用的三叉链表
public class BinarySearchTreeTriLink<K, V> implements IMap<K, V> {
	private Node<K, V> root;
	private int size;

	private static class Node<K, V> {
		K key;
		V value;
		Node<K, V> left;
		Node<K, V> right;
		Node<K, V> parent;

		Node(K key, V value, Node<K, V> left, Node<K, V> right, Node<K, V> parent) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	private Node<K, V> getNode(K key) {
		Node<K, V> p = root;
		while (p != null) {
			@SuppressWarnings("unchecked")
			int cmp = ((Comparable<? super K>) key).compareTo(p.key);
			if (cmp == 0)
				break;
			if (cmp > 0)
				p = p.right;
			else
				p = p.left;
		}
		return p;
	}

	private void setChild(Node<K, V> node, Node<K, V> parent, boolean isLeft) {
		if (parent == null) {
			root = node;
			return;
		}
		if (isLeft)
			parent.left = node;
		else
			parent.right = node;
	}

	public V get(K key) { // 获取指定key的value，如果key不存在，返回null
		Objects.requireNonNull(key);
		Node<K, V> p = getNode(key);
		return p == null ? null : p.value;
	}

	@SuppressWarnings("unchecked")
	public V put(K key, V value) { // 存入1个<key, value>, 如果已经有相同的key，则修改value，返回原有的value, 如果没有，则插入一个叶子结点，返回null
		Objects.requireNonNull(key);
		Node<K, V> p = root;
		Node<K, V> parent = null;
		int cmp = 0;
		while (p != null) {
			cmp = ((Comparable<? super K>) key).compareTo(p.key);
			if (cmp == 0) {
				V oldValue = p.value;
				p.value = value;
				return oldValue;
			}
			parent = p;
			if (cmp > 0)
				p = p.right;
			else
				p = p.left;
		}
		Node<K, V> node = new Node<>(key, value, null, null, parent);
		setChild(node, parent, cmp < 0);
		++size;
		return null;
	}

	private Node<K, V> successorNode(Node<K, V> p) {// p的后继结点，即比p的key大的结点中的key最小的结点
		assert (p != null);
		Node<K, V> q = p.right;
		if (q != null) {
			while (q.left != null)
				q = q.left;
			return q;
		}
		q = p.parent;// remove调用这个方法不会执行到这，因为p.right != null
		while (p == q.right) {
			p = q;
			q = q.parent;
		}
		return p;
	}

	public V remove(K key) { // 删除指定key的<key, value>,返回value，如果key不存在，返回null
		Objects.requireNonNull(key);
		Node<K, V> p = getNode(key);
		if (p == null)
			return null;
		V oldValue = p.value;
		if (p.left != null && p.right != null) {// 有左右孩子
			Node<K, V> s = successorNode(p);// 有序序列中p的后继所在的结点
			p.key = s.key;// s的值替换p的值
			p.value = s.value;
			p = s;// 删除的是s
		}
		Node<K, V> orphan = p.left != null ? p.left : p.right;
		if (orphan != null)
			orphan.parent = p.parent;
		setChild(orphan, p.parent, p.parent != null ? p.parent.left == p : true);
		--size;
		return oldValue;
	}

	public void inOrder() {
		inOrder(root);
		System.out.println();
	}

	private void inOrder(Node<K, V> t) {
		if (t == null)
			return;
		inOrder(t.left);
		System.out.print(t.key + "=" + t.value + " ");
		inOrder(t.right);
	}

	public static void main(String[] args) {
		BinarySearchTreeTriLink<Integer, Character> bs = new BinarySearchTreeTriLink<>();
		bs.put(6, 'a');
		bs.put(3, 'b');
		bs.put(2, 'c');
		bs.put(3, 'd');
		bs.put(5, 'e');
		bs.put(4, 'f');
		bs.put(9, 'g');
		bs.put(7, 'h');
		bs.put(10, 'i');
		bs.put(8, 'i');

		bs.inOrder();
		System.out.println(bs.remove(6));// 测试各种情况2，4，8，10，5，7，3，9，6; 不put5和4，然后remove 3
		System.out.println("------after remove------");
		bs.inOrder();
		System.out.println(bs.get(9));
	}
}
