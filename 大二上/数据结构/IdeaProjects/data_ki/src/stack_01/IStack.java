package stack_01;

public interface IStack<T> {
    int size();// 返回数据个数

    void clear();// 清楚线性表中的所有数据

    boolean isEmpty();//

    void push(T x);

    Object pop();


}
