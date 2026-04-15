package package1;

import java.util.Iterator;

public class CircularLinkedListWithHeadNodeUsingTail<T> implements Ilist<T>,Iterable<T>{
    private Node<T> tail;
    private int size;

    CircularLinkedListWithHeadNodeUsingTail() {
        tail = new Node<>(null, null);// 尾结点
        tail.next=tail;
        size=0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int indexOf(T x) {// 对比LinkListWithSize
        int index = 0;
        for (Node<T> p = tail.next.next; p != tail.next; p = p.next) {
            if (x.equals(p.data))
                return index;
            ++index;
        }
        return -1;
    }

    // 将索引号的合法性检查抽出作为函数
    private void rangeCheckForAdd(int index) {// add用
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(String.valueOf(index));
    }

    private void rangeCheck(int index) {// 其它函数用
        if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException(String.valueOf(index));
    }

    public T get(int index) {
        rangeCheck(index);
        Node<T> p = tail.next.next;// 对比LinkListWithSize
        for (int i = 0; i < index; i++) // i次
            p = p.next;
        return p.data;
    }

    public void add(int index, T x) {// 对比LinkListWithSize
        rangeCheckForAdd(index);
        Node<T> p = tail.next;
        for (int i = 0; i < index; i++) // 找到index的前驱
            p = p.next;
        if(size == 0){
            Node<T> q=new Node<>(x,tail);
            tail.next=q;
            tail=tail.next;
        }
        else{
            p.next=new Node<>(x,p.next);
            if (size==index){
                tail=p.next;
            }
        }
        ++size;
        return;
    }

    public T remove(int index) {// 对比LinkListWithSize
        rangeCheck(index);
        Node<T> p = tail.next;
        for (int i = 0; i < index; i++) // 找到index的前驱
            p = p.next;// p待删除结点的前驱
        if (index==size-1){
            tail=p;
        }
        Node<T> q = p.next;// q待删除结点
        p.next = q.next;// 将待删除结点从链表中移除
        T value = q.data; // q指向待删除结点
        q.data = null;// 帮助GC
        q.next = null;
        --size;
        return value;
    }
    public void putMinToFirst(){
        Node<T> q=tail.next.next;
        Node<T> c=tail.next;
        Node<T> p=tail.next;
        T min = q.data;
        for (int i=0;i<size-1;i++){
            Comparable<? super T> min1 =(Comparable<? super T>) min;
            q=q.next;
            c=c.next;
            if (min1.compareTo(q.data)>0){
                min=q.data;
                p=c;
            }
        }
        if (p.next==tail){
            tail=p;
        }
        ///1111
        Node<T> M=p.next;
        p.next=M.next;
        M.next=tail.next.next;
        tail.next.next=M;
    }

    public void clear() {
        while (tail.next.next != tail) {// head一直作为前驱，删除它后面的结点
            Node<T> q = tail.next.next;// q待删除结点
            tail.next.next = q.next;// 将q从链表中移除
            q.data = null;// 帮助GC
            q.next = null;
        }
        tail=tail.next;
        Node<T> p=tail.next;
        tail.next=tail;
        p.data=null;
        p.next=null;
        size = 0;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (T elem : this)// 利用了实现了的迭代器
            str.append(elem + "  ");
        return str.toString();
    }

    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        private Node<T> currentNode;

        public Itr() {
            currentNode = tail.next.next;
        }
//111
        public boolean hasNext() {
            return currentNode != tail.next;
        }

        public T next() {
            T data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        CircularLinkedListWithHeadNodeUsingTail<Integer> sl = new CircularLinkedListWithHeadNodeUsingTail<>();

        sl.add(0, 1);
        sl.add(0, 2);
        sl.add(0, 3);
        System.out.println(sl);

        sl.clear();

        sl.add(0, 1);
        sl.add(0, 2);
        sl.add(0, 3);
        System.out.println(sl);
        sl.putMinToFirst();
        System.out.println(sl);

        System.out.println("size= " + sl.size());

        if (sl.isEmpty())
            System.out.println("empty");
        else
            System.out.println("Not empty");

        int pos = sl.indexOf(3);
        if (pos == -1)
            System.out.println("Not Found");
        else
            System.out.println("At " + pos);

        Integer elem = sl.get(1);
        System.out.println(elem);

        sl.remove(0);
        sl.remove(1);
        System.out.println(sl);


    }
}
