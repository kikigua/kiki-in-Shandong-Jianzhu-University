package map;

import java.util.Objects;

//二叉搜索树 二叉排序树
// 二叉链表存储
public class BinarySearchTree<K, V> implements IMap<K, V> {
	private Node<K, V> root;
	private int size;
	private Node<K, V> parent;// target的父结点，put新结点的父结点
	private Node<K, V> target;// 需要remove的结点

	private static class Node<K, V> {
		K key;
		V value;
		Node<K, V> left;
		Node<K, V> right;

		Node(K key, V value, Node<K, V> left, Node<K, V> right) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		root = null;// 简单处理了，没有去帮助GC
		size = 0;
	}

	public V get(K key) { // 获取指定key的value，如果key不存在，返回null
		Objects.requireNonNull(key);
		Node<K, V> t = root;
		while (t != null) {
			@SuppressWarnings("unchecked")
			int cmp = ((Comparable<? super K>) key).compareTo(t.key);
			if (cmp == 0)
				break;
			if (cmp < 0)
				t = t.left;
			else
				t = t.right;
		}
		return t == null ? null : t.value;
	}

	@SuppressWarnings("unchecked")
	private boolean getTarget(K key) {// 设置parent、target，返回target是不是parent的左孩子
		target = root;
		parent = null;
		int cmp = 0;
		while (target != null) {
			cmp = ((Comparable<? super K>) key).compareTo(target.key);
			if (cmp == 0)
				break;
			parent = target;
			if (cmp < 0)
				target = target.left;
			else
				target = target.right;
		}
		// 不能仅仅判断parent.left == target而决定target是不是parent的左孩子，因为target和parent都可能为null
		return target == null ? cmp < 0 : parent != null ? parent.left == target : true;
	}

	private void connectToParent(Node<K, V> node, boolean isLeft) {
		if (parent == null) {
			root = node;
			return;
		}
		if (isLeft)
			parent.left = node;
		else
			parent.right = node;
	}

	public V put(K key, V value) { // 存入1个<key, value>, 如果已经有相同的key，则修改value，返回原有的value, 如果没有，则插入一个叶子结点，返回null
		Objects.requireNonNull(key);
		boolean isLeft = getTarget(key);
		if (target != null) {// key存在，修改value
			V oldValue = target.value;
			target.value = value;
			return oldValue;
		}
		Node<K, V> node = new Node<>(key, value, null, null);
		connectToParent(node, isLeft);
		++size;
		return null;
	}

//	public V remove(K key) { // 删除指定key的<key, value>,返回value，如果key不存在，返回null
//		Objects.requireNonNull(key);
//		boolean isLeft = getTarget(key);
//		if (target == null)
//			return null;
//		if (target.left != null && target.right != null) {// 有左孩子和右孩子
//			Node<K, V> q = target.right;
//			Node<K, V> p = null;
//			while (q.left != null) {// 最大的关键字，一定在最右下脚
//				p = q;
//				q = q.left;
//			}
//			// 以下交换结点q和target的位置，需要重新设置q和target的父结点、左孩子和右孩子
//			q.left = target.left;
//			target.left = null;// 因为 q.right = null
//			connectToParent(q, isLeft);// target的父结点成为q的父结点
//			if (p != null) {
//				Node<K, V> tmp = q.right;
//				q.right = target.right;
//				target.right = tmp;
//				parent = p; // p成为target的父结点
//				parent.left = target;
//			} else {
//				target.right = q.right;
//				// q.left = target;
//				parent = q;// q成为target的父结点
//				parent.right = target;// 所以注释掉上上一句
//			}
//		}//这里额外包含了target只有左（右）子树，或者是叶子结点时
//		Node<K, V> orphan = target.left != null ? target.left : target.right;
//		isLeft = parent != null ? parent.left == target : false;
//		connectToParent(orphan, isLeft);
//		--size;
//		return target.value;
//	}

	//重写remove方法 ->采用位置交换的方式
	public V remove(K key) { // 删除指定key的<key, value>,返回value，如果key不存在，返回null
		Objects.requireNonNull(key);
		boolean isLeft = getTarget(key);
		if (target == null)//空树
			return null;
		if (target.left != null && target.right != null) {// 有左孩子和右孩子
			Node<K, V> q = target.right;
			Node<K, V> p = null;
			while (q.left != null) {// 最大的关键字，一定在最右下脚
				p = q;
				q = q.left;
			}
			// 以下交换结点q和target的位置，需要重新设置q和target的父结点、左孩子和右孩子
			q.left = target.left;
			target.left = null;// 因为 q.right = null
			connectToParent(q, isLeft);// target的父结点成为q的父结点
			if (p != null) {
				Node<K, V> tmp = q.right;
				q.right = target.right;
				target.right = tmp;
				parent = p; // p成为target的父结点
				parent.left = target;
			} else {
				target.right = q.right;
				// q.left = target;
				parent = q;// q成为target的父结点
				parent.right = target;// 所以注释掉上上一句
			}
		}//这里额外包含了target只有左（右）子树，或者是叶子结点时
		Node<K, V> orphan = target.left != null ? target.left : target.right;
		isLeft = parent != null ? parent.left == target : false;
		connectToParent(orphan, isLeft);
		--size;
		return target.value;
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
	// 折半查找 -> 在有重复关键字的情况下，返回与待查关键字相等的最左边的数据的位置
	public int BinarySearch(int a[],int x){
		int p,i=0,j= a.length-1;
		while (i<=a.length-1){
			p=(i+j)/2;
			int q=a[p];
			if (x<q){
				j=p;
			}
			else if (x>q){
				i=p+1;
			}
			else {
				for (int z=p-1;z<0;z--){
					if (a[z]!=a[p]){
						return z+1;
					}
					if (a[z]==a[p]){
						return z;
					}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer, Character> bs = new BinarySearchTree<>();

		//折半查找
//		int a[] = { 2, 3, 3, 3, 4 };
//        System.out.println(bs.BinarySearch(a, 4));

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
		System.out.println(bs.remove(7));// 测试各种情况2，4，8，10，5，7，3，9，6; 不put5和4，然后remove 3

//		bs.put(2, 'a');
////		bs.put(4, 'b');
//		bs.put(8, 'c');
//		bs.put(10, 'd');
////		bs.put(5, 'e');
//		bs.put(7, 'f');
//		bs.put(3, 'g');
//		bs.put(9, 'h');
//		bs.put(6, 'i');
//
//		bs.inOrder();
//
//		System.out.println(bs.remove(7));

		System.out.println("------after remove------");
		bs.inOrder();
		System.out.println(bs.get(9));
	}
}
