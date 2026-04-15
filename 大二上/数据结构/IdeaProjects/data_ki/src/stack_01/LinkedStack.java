package stack_01;

import  java.util.Iterator;

//实现链式栈
public class LinkedStack<T> implements IStack<T>,Iterable<T> {
    private Node<T> top;
    private int size;

    public LinkedStack(){
        top=null;
        size=0;
    }

    //结点类描述
    public class Node<T>{
        public T data;
        public Node<T> next;

        public Node(T data,Node<T> next){
            this.data=data;
            this.next=next;
        }
    }
    public int size(){
        return size;
    }

    public void clear(){
        top=null;
    }

    public boolean isEmpty(){
        return top==null;
    }

    public void push(T x){
        Node<T> p=new Node<>(x,null);
        p.next=top;
        top=p;
    }

    public T pop(){
        if (isEmpty()){
            return null;
        }
        else {
            Node p=top;
            top=top.next;
            return (T) p.data;
        }
    }
    public T peek(){
        if (isEmpty()){
            return null;
        }
        else {
            return top.data;
        }
    }
    //输出
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
            currentNode = top;
        }
        //111
        public boolean hasNext() {
            return currentNode != null;
        }

        public T next() {
            T data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }
    }

    public static void main(String[] args) {
        LinkedStack<Integer> sl = new LinkedStack<>();
        sl.push(1);
        sl.push(2);
        sl.push(3);
        sl.push(4);
        sl.push(5);
        sl.push(6);
        System.out.println(sl);
        System.out.println(sl.pop());
        System.out.println(sl);
        sl.clear();
        if (sl.isEmpty())
            System.out.println("empty");
        else
            System.out.println("Not empty");
        sl.push(10);
        sl.push(11);
        sl.push(12);
        sl.push(13);
        sl.push(14);
        sl.push(15);
        System.out.println(sl);
    }
}


