package map;

public interface IMap<K, V> {
	public V put(K key, V value); // 存入1个<key, value>,如果已经有相同的key，用新的value替换原有的value，并返回原有的value，否则返回null

	public V get(K key); // 获取指定key的value，如果key不存在，返回null

	public V remove(K key); // 删除指定key的<key, value>,返回value，如果key不存在，返回null

	public void clear();

	public int size();// 数据的个数

	public boolean isEmpty();
}
