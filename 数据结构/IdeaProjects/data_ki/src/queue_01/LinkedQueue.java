package queue_01;


import java.util.Iterator;

//实现链式队列
public class LinkedQueue<T> implements IQueue<T>,Iterable<T>,Cloneable{

    private  Node<T> head;
    private  Node<T> tail;
    private int size;
    public static class Node<T>{
        T data;
        Node<T> next;

        Node(T data,Node<T> next){
            this.data=data;
            this.next=next;
        }
    }
    LinkedQueue(){
        head=tail=null;
        size=0;
    }
    public void clear(){
        head=tail=null;
    }
    public boolean isEmpty(){
        return head==null;
    }
    public int size(){
        return size;
    }

    //取队列元素（但没有真正取出）
    public T peek(){
        if (head !=null){
            return head.data;
        }
        else{
            return null;
        }
    }
    //入队
    public void offer(T x){
        Node<T> p=new Node<>(x,null);
        if (head !=null){
            tail.next=p;
            tail=p;
            size++;
        }
        else{
            head=tail=p;
            size++;
        }
    }
    //出队
    public T poll(){
        if (head !=null){
            Node<T> p=head;
            head=head.next;
            size--;
            if (p==tail){
                tail=null;
                size--;
            }
            return p.data;
        }
        else{
            return null;
        }
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

    private class Itr implements Iterator<T> {// 注意Itr是内部类，而 Node是静态的嵌套类
        private Node<T> currentNode;

        public Itr() {
            currentNode = head;
        }

        public boolean hasNext() {
            return currentNode != null;
        }

        public T next() {
            T data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }
    }
    //
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        LinkedQueue<Integer> lq = new LinkedQueue<>();
        lq.offer(1);
        lq.offer(2);
        System.out.println(lq);
        System.out.println(lq.size());
        System.out.println(lq.peek());
        System.out.println(lq);

        lq.poll();
        lq.poll();
        System.out.println(lq.isEmpty());
        System.out.println(lq.peek());

        lq.offer(3);
        lq.offer(4);
        lq.offer(5);
        lq.offer(6);
        System.out.println(lq);
        LinkedQueue<Integer> lq2 = (LinkedQueue) lq.clone();
        System.out.println(lq2);
        System.out.println(lq.size());

        lq.clear();
        System.out.println(lq.size());
        System.out.println(lq);
        lq.offer(7);
        System.out.println(lq);
        LinkedQueue<Integer> lq3 = (LinkedQueue) lq.clone();
        System.out.println(lq3);
    }

}
