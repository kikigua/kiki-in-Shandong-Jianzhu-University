package cList;

import java.util.Iterator;

public class DoubleCircularLinkedList<T> implements list<T>, Iterable<T> {
    private Node<T> head;
    private int size;

    //结点类
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> pre;

        Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.next = next;
            this.pre = pre;
        }
    }

    // 1.建立一个空表
    public DoubleCircularLinkedList() {
        head=new Node<T>(null,null,null);
        head.pre=head.next=head;
    }

                                                                                                              //2.3为p=head//4.5.6.7.8p=head.next
    // 2.在第i个位置插入新的元素x
    public void add(int i, T x) {
        rangeCheckForAdd(i);
        Node<T> p=head;
        for(int n=0;n<i;n++)//找到index的前驱
        	p=p.next;
        Node<T> s=new Node<T>(x,p,p.next);
        s.next.pre=s;
        p.next=s;
        ++size;
        return;
    }

    // 3.删除第i个位置上的元素
    public T remove(int i) {
        rangeCheck(i);
        Node<T> p=head;
        for(int n=0;n<i;n++)//找到index的前驱
        	p=p.next;
        Node<T>q=p.next;//q待删除结点
        T value =q.data;//q指向待删除结点
        p.next=q.next;
        q.next.pre=p;
        q.next=null;//帮助GC
        q.pre=null;
        q.data=null;
        return value;
    }

    // 4.取第i个位置上的元素
    public T get(int i) {
        rangeCheck(i);
        Node<T> p = head.next;
        for (int n = 0; n < i; n++)
            p = p.next;
        return p.data;
    }

    // 5.返回元素x第一次出现在双向循环链表中的位置号
    public int indexOf(T x) {
        int i = 0;
        for (Node<T> p = head.next; p != head; p = p.next) {
            if (x.equals(p.data))
                return i;
            ++i;
        }
        return -1;
    }

    // 6.求双向循环链表的长度，即元素个数
    public int size() {
        return size;
    }

    // 7.输出双向循环链表中所有的元素值
    public T display() {
        Node<T> p = head.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        return p.data;
    }

    // 8.实现双向循环链表的就地逆置
    public void reverse() {
        Node<T> p = head.next;
        Node<T> m = p.next, q;
        while (m != head) {
            q = m.next;
            m.next = p;
            m.pre = q;
            p = m;
            m = q;
        }
        head.next.pre = head.next.next;
        head.next.next = head;
        head.pre = head.next;
        head.next = p;

    }
    // 9.清除链表
    public void clear() {
        while (head != head.next) {
            Node<T> q = head.next;
            head.next = q.next;
            q.data = null;
            q.next = null;
            q.pre = null;
        }
        size = 0;
    }
    // 将索引号的合法性检查抽出作为函数
    private void rangeCheckForAdd(int i) {// add用
        if (i < 0 || i > size)
            throw new IndexOutOfBoundsException(String.valueOf(i));
    }

    private void rangeCheck(int i) {// 其它函数用
        if (i < 0 || i > size - 1)
            throw new IndexOutOfBoundsException(String.valueOf(i));
    }

    public boolean isEmpty() {
        return size == 0;
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
            currentNode = head.next;
        }

        public boolean hasNext() {
            return currentNode != head;
        }

        public T next() {
            T data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }
    }



    public static void main(String[] args) {
        DoubleCircularLinkedList<Integer> sl = new DoubleCircularLinkedList<>();
        sl.add(0, 10);
        sl.add(1, 20);
        sl.add(2, 30);
        sl.add(3, 40);
        sl.add(4, 50);
        System.out.println("双向循环链表");
        sl.display();//System.out.println(sl);
        System.out.println();
        System.out.println("=========remove&&add========");
        System.out.println("删除3号位置的元素");
        sl.remove(3);
        sl.display();
        System.out.println();
        System.out.println("在0号位置填加元素60");
        sl.add(0, 60);
        System.out.println(sl);
        System.out.println("在2号位置填加元素40");
        sl.add(2, 40);
        System.out.println(sl);
        System.out.println("在6号位置填加元素80");
        sl.add(6, 80);
        System.out.println(sl);
        System.out.println("===========indexOf==========");
        System.out.println("返回元素10第一次出现在双向链表中的位置号");
        System.out.println(sl.indexOf(10));
        System.out.println("返回元素30第一次出现在双向链表中的位置号");
        System.out.println(sl.indexOf(30));
        System.out.println("返回元素50第一次出现在双向链表中的位置号");
        System.out.println(sl.indexOf(50));
        System.out.println("============size===========");
        System.out.println("链表中元素的个数");
        System.out.println(sl.size());
        System.out.println("============get===========");
        System.out.println("取第0个位置上的元素");
        System.out.println(sl.get(0));
        System.out.println("取第3个位置上的元素");
        System.out.println(sl.get(3));
        System.out.println("取第6个位置上的元素");
        System.out.println(sl.get(6));
        System.out.println("===========reverse=========");
        System.out.println("就地逆置链表");
        sl.reverse();
        System.out.println(sl);
        System.out.println("===========clear=========");
        System.out.println("清除链表");
        sl.clear();
        System.out.println(sl);

    }

}

