package map;

import java.util.Objects;

public class LinearProbeHash<K, V> implements IMap<K, V> {
	private int size;// <key,value>的个数
	private float loadFactor;// 装载因子
	private int threshold;// 阈值
	private Entry<?, ?>[] table;

	private static class Entry<K, V> {
		private final int hash;
		private final K key;
		private V value;

		protected Entry(int hash, K key, V value) {
			this.hash = hash;
			this.key = key;
			this.value = value;
		}
	}

	public LinearProbeHash(int maxSize, float loadFactor) {// 表长最好为素数
		this.loadFactor = loadFactor;
		threshold = (int) (maxSize * loadFactor);
		table = new Entry<?, ?>[maxSize];
	}

	public LinearProbeHash(int initialCapacity) {
		this(initialCapacity, 0.75f);// 默认装载因子0.75
	}

	public LinearProbeHash() {
		this(11, 0.75f);// 默认表长11
	}

	@SuppressWarnings("unchecked")
	public V get(K key) {
		Objects.requireNonNull(key);
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % table.length;
		int i = index;
		do {
			if (table[i] == null || (table[i] != null && table[i].hash == hash && table[i].key.equals(key)))
				break;
			i = (i + 1) % table.length;
		} while (i != index);
		V result = null;
		if (table[i] != null)
			result = ((Entry<K, V>) table[i]).value;
		return result;
	}

	@SuppressWarnings("unchecked")
	public V put(K key, V value) {
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % table.length; // hashcode的值有可能为负数，负数的%操作达不到想要的结果,按位与操作后，保证为正数
		int i = index;
		do {
			if (table[i] == null || (table[i] != null && table[i].hash == hash && table[i].key.equals(key)))
				break;
			i = (i + 1) % table.length;
		} while (i != index);
		if (table[i] == null) {
			table[i] = new Entry<>(hash, key, value);
			++size;
			if (size >= threshold)
				rehash();
			return null;
		}
		V old = ((Entry<K, V>) table[i]).value;
		((Entry<K, V>) table[i]).value = value;
		return old;

	}

	private void put(Entry<K, V> e) {
		int hash = e.hash;
		int index = (hash & 0x7FFFFFFF) % table.length;
		int i = index;
		do {
			if (table[i] == null)
				break;
			i = (i + 1) % table.length;
		} while (i != index);
		table[i] = e;
	}

	@SuppressWarnings("unchecked")
	public V remove(K key) {
		Objects.requireNonNull(key);
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % table.length;
		int i = index;
		do {
			if (table[i] == null || (table[i] != null && table[i].hash == hash && table[i].key.equals(key)))
				break;
			i = (i + 1) % table.length;
		} while (i != index);
		if (table[i] != null) {
			V old = ((Entry<K, V>) table[i]).value;
			table[i] = null;
			int c = 0;
			for (int j = (i + 1) % table.length; table[j] != null; j = (j + 1) % table.length)
				++c;
			Entry<?, ?>[] list = new Entry<?, ?>[c];
			// 重新分配这个范围的<key, value>
			c = 0;
			for (int j = (i + 1) % table.length; table[j] != null; j = (j + 1) % table.length) {
				list[c++] = table[j];
				table[j] = null;
			}
			for (Entry<?, ?> e : list)
				put((Entry<K, V>) e);
			return old;
		}
		return null;
	}

	public void clear() {
		for (int i = 0; i < table.length; ++i)
			table[i] = null;
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
		Entry<?, ?>[] oldMap = table;
		int newCapacity = (oldCapacity << 1) + 1;
		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			if (oldCapacity == MAX_ARRAY_SIZE)
				return;
			newCapacity = MAX_ARRAY_SIZE;
		}
		Entry<?, ?>[] newMap = new Entry<?, ?>[newCapacity];

		threshold = (int) Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
		table = newMap;
		for (int i = 0; i < oldCapacity; ++i) {
			if (oldMap[i] != null) {
				Entry<K, V> e = (Entry<K, V>) oldMap[i];
				put(e.key, e.value);
			}
		}
	}

	public String toString() {
		StringBuilder st = new StringBuilder();
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				@SuppressWarnings("unchecked")
				Entry<K, V> n = (Entry<K, V>) table[i];
				st.append("i=" + i + ":");
				st.append(n.hash + " " + n.key + " " + n.value + " ");
			}
		}
		return st.toString();
	}

	public static void main(String[] args) {
		LinearProbeHash<Integer, String> ht = new LinearProbeHash<>();
//		ht.put(1, "xyzbbb");
//		ht.put(2, "xyz");
//		ht.put(4, "xyz");
//		ht.put(5, "xyz");
		ht.put(11, "xyz");
		ht.put(12, "xyz");
		ht.put(22, "xyz");
		ht.put(33, "xyz");
		System.out.println(ht);
		ht.remove(11);
		System.out.println("-------------------");
		System.out.println(ht);
		System.out.println(ht.get(22));
	}
}
