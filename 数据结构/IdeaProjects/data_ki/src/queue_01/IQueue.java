package queue_01;

public interface IQueue<T> {

    int size();

    void clear();

    boolean isEmpty();

    T peek();

    void offer(T x);

    T poll();



}
