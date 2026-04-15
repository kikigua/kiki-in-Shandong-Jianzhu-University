package ArrayList_01;

import java.util.Iterator;

public interface IList<T> {

    int size();// 返回数据个数

    void clear();// 清楚线性表中的所有数据

    boolean isEmpty();// 是不是为空表

    int indexOf(T x);// 找到x的索引号

    T get(int index);// 返回索引号为index的数据的值

    void add(int index, T x);// 加入数据x, 索引号为index

    T remove(int index);// 删除索引号为index的的数据


    // T set(int index, T x);// 将索引号为index的数据的值更改为x， 返回原来的值

    // boolean remove(Object x);// 删除值为x的数据，如果表中不存在x, 则返回 false，否则，返回true
}