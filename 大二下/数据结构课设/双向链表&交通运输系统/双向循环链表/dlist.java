package doublelist;

import java.util.Iterator;

public interface dlist<T> {
	void add(int i, T x);// 在第i个位置插入新的元素x

	T remove(int i);// 删除第i个位置上的元素

	T get(int i);// 取第i个位置上的元素

	int indexOf(T x);// 返回元素x第一次出现在双向循环链表中的位置号

	int size();// 求双向循环链表的长度，即元素个数

	boolean isEmpty();// 是不是为空表

	Iterator<T> iterator();// 迭代器，枚举线性表中的数据

}

