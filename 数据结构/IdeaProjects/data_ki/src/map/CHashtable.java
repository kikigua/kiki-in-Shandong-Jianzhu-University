package map;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
//参考了java类库的 HashTable类的实现
public class CHashtable<K, V> implements IMap<K, V> {
	private int size;// <key,value>的个数
	private float loadFactor;// 装载因子
	private int threshold;// 阈值
	private Node<?, ?>[] table;

	private static class Node<K, V> {
		private final int hash;
		private final K key;
		private V value;
		private Node<K, V> next;

		protected Node(int hash, K key, V value, Node<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	public CHashtable(int maxSize, float loadFactor) {// 表长最好为素数
		this.loadFactor = loadFactor;
		threshold = (int) (maxSize * loadFactor);
		table = new Node<?, ?>[maxSize];
	}

	public CHashtable(int initialCapacity) {
		this(initialCapacity, 0.75f);// 默认装载因子0.75
	}

	public CHashtable() {
		this(11, 0.75f);// 默认表长11
	}

	public V get(K key) {
		Objects.requireNonNull(key);
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % table.length;
		for (Node<?, ?> n = table[index]; n != null; n = n.next) {
			if ((n.hash == hash) && n.key.equals(key)) {
				@SuppressWarnings("unchecked")
				V result = (V) n.value;
				return result;
			}
		}
		return null;
	}

	private void addNode(int hash, K key, V value, int index) {
		Node<?, ?> tab[] = table;
		if (size >= threshold) {
			rehash();// Rehash the table if the threshold is exceeded
			tab = table;
			hash = key.hashCode();// ？？？
			index = (hash & 0x7FFFFFFF) % tab.length;// rehash造成length改变，要重新计算index
		}
		@SuppressWarnings("unchecked")
		Node<K, V> n = (Node<K, V>) tab[index];
		tab[index] = new Node<>(hash, key, value, n);// <k,v>加入链表头
		++size;
	}

	public V put(K key, V value) {
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % table.length; // hashcode的值有可能为负数，负数的%操作达不到想要的结果,按位与操作后，保证为正数
		@SuppressWarnings("unchecked")
		Node<K, V> n = (Node<K, V>) table[index];
		for (; n != null; n = n.next) {// 查找是否已经存在<key, value>
			if ((n.hash == hash) && n.key.equals(key)) {
				V old = n.value;
				n.value = value;
				return old;// 表中有这个key，返回value，不插入
			}
		}
		addNode(hash, key, value, index);
		return null;
	}


	public V remove(K key) {
		Objects.requireNonNull(key);
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % table.length;
		@SuppressWarnings("unchecked")
		Node<K, V> n = (Node<K, V>) table[index];
		for (Node<K, V> prev = null; n != null; prev = n, n = n.next) {
			if ((n.hash == hash) && n.key.equals(key)) {
				if (prev != null) 
					prev.next = n.next;
				else 
					table[index] = n.next;
				--size;
				V oldValue = n.value;
				n.value = null;
				return oldValue;
			}
		}
		return null;
	}

	public void clear() {
		for (int index = table.length; --index >= 0;)
			table[index] = null;//  简单的从头断开链表，没有清空链表中的各个结点
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	@SuppressWarnings("unchecked")
	protected void rehash() {
		int oldCapacity = table.length;
		Node<?, ?>[] oldMap = table;
		int newCapacity = (oldCapacity << 1) + 1;
		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			if (oldCapacity == MAX_ARRAY_SIZE)
				return;
			newCapacity = MAX_ARRAY_SIZE;
		}
		Node<?, ?>[] newMap = new Node<?, ?>[newCapacity];

		threshold = (int) Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
		table = newMap;
		for (int i = oldCapacity; i-- > 0;) {
			for (Node<K, V> old = (Node<K, V>) oldMap[i]; old != null;) {
				Node<K, V> n = old;
				old = old.next;
				int index = (n.hash & 0x7FFFFFFF) % newCapacity;
				n.next = (Node<K, V>) newMap[index];
				newMap[index] = n;
			}
		}
	}

	public String toString() {
		StringBuilder st = new StringBuilder();
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				@SuppressWarnings("unchecked")
				Node<K, V> n = (Node<K, V>) table[i];
				st.append("i=" + i + ":");
				while (n != null) {
					st.append(n.key + " " + n.value + " ");
					n = n.next;
				}
				st.append("\n");
			}
		}
		return st.toString();
	}
	//调用java类库的接口List的方法，将hashtable中的数据逐个存放到list，并返回list
	public List<V> values(){
		LinkedList<V> list =new LinkedList<>();
		for (int i=0;i< table.length;i++){
			for (Node<?,?> d=table[i];d!=null;d=d.next){
				list.add((V) d.value);
			}
		}
		return list;
	}


	public static void main(String[] args) {
		CHashtable<Integer, String> ht = new CHashtable<>();
		ht.put(1, "xyzbbb");
		ht.put(2, "xyz");
		ht.put(4, "xyz");
		ht.put(5, "xyz");
		ht.put(11, "xyz");
		ht.put(12, "xyz");
		ht.put(22, "xyz");
		System.out.println(ht);
		System.out.println(ht.values());
		ht.remove(11);
		System.out.println("-------------------");
		System.out.println(ht);
		System.out.println(ht.get(1));
		ht.clear();
		System.out.println(ht.get(1));	
	}
}
