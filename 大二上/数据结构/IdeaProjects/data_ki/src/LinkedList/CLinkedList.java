package LinkedList;

import java.util.Iterator;

//线性表的链表实现
public class CLinkedList<T> implements IList<T>,Iterable<T> {

    //结点类的描述
    public class Node<T> {
         T data;      //存放结点值
         Node<T> next;//后继结点的引用

        //带两个参数时的构造函数
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    //单链表类的描述
    private Node<T> head; //单链表的头指针
    private int size;

    //判断带头结点的单链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    //数据x可以是null
    public int indexOf(T x) {
        if (x == null) {
            int index = 0;
            for (Node<T> p = head; p != null; p = p.next) {
                if (p.data == null)
                    return index;
                ++index;
            }
        } else {
            int index = 0;
            for (Node<T> p = head; p != null; p = p.next) {
                if (x.equals(p.data)) {
                    return index;
                }
                ++index;
            }
        }
        return -1;
    }

    //将索引号的合法性检查抽出作为方法
    //检查合法性
    private void rangCheckForAdd(int index) {
        //add用
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }

    }

    private void rangCheck(int index) {
        //其他方法用
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
    }


    //按位序号查找算法
    public T get(int index) {
        rangCheck(index);
        Node<T> p = head;
        for (int i = 0; i < index; i++) {
            //找到结点index
            p = p.next;
        }
        return p.data;
    }

    //add !!
    public void add(int index, T x) {
        rangCheckForAdd(index);
        //为空表，及插入位置0
        if (index == 0) {
            //!
            head = new Node<>(x, head);
        } else {
            Node<T> p = head;
            //一直找到index的前驱
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            Node<T> s=new Node<>(x,null);
            s.next=p.next;
            p.next=s;
        }
        ++size;
        return;
    }

    public T remove(int index) {
        rangCheck(index);
        Node<T> p = head;
        if (index == 0) {// 肯定不为空
            head = head.next;
        } else {// 至少有2个结点
            // 找到index的前驱
            for (int i = 0; i < index - 1; i++)
                p = p.next;
            Node<T> q = p;
            p = p.next;
            q.next = p.next;
        }
        // p引用待删除结点
        T value = p.data;
        p.data = null;// 帮助GC
        p.next = null;

        --size;
        return value;
    }

    //清除功能
    public void clear() {
        while (head != null) {
            Node<T> p = head;
            head = head.next;
            p.data = null;
            p.next = null;
        }
        size = 0;
    }


    //就地逆置
    public void reverse() {
        //a,b进行逆置
        Node<T> a = head;//本结点  指针
        Node<T> b = null;//上一个结点
        Node<T> aNext = null;//下一个结点
        if (head == null) {
            System.out.println("链表为空");
        } else {//当本结点不为空时
            while (a != null) {
                aNext = a.next;//aNext代表a节点的下一个节点，他提前去占据a要过去的节点位置
                if (aNext == null) {
                    head = a;//此时head被赋值为a，说明只有一个结点
                }
                a.next = b;//本结点指向前一个结点（第一次为空），让它等于后一个结点完成逆置第一步
                b = a;//把这结点的内容赋值给前一个结点，这表示前一个结点所代表的结点改为本结点

                a = aNext;//因为aNext为a的下一个结点，然后给a赋值，则开始下一位置的逆置
            }
        }
    }

    //删除from到to的数据
    public void removeRange(int from, int to) {
        if (from >= size || to >= size || from < 0 || to < 0 || from > to) {
            throw new RuntimeException("超出内容范围");
        }
        Node<T> head1 = new Node<>(null, head);
        Node<T> p = head1;
        for (int i = 0; i < from; i++) {
            p = p.next;
        }//找到from的前驱
        Node<T> q = p;
        for (int i = from - 1; i <= to; i++) {
            q = q.next;
        }//找到to的后驱
        if (from == 0) {
            head = q;
        }
        Node<T> a = p.next;
        while (a != q) {
            Node<T> m = a;//m是待删除节点
            a = a.next;
            m.data = null;
            m.next = null;
        }
        p.next = q;//建立新链接
        size -= (to - from + 1);
    }

    //返回x最后一次出现的位置
    public int lastIndexOf(T x) {

        Node<T> p = new Node<>(head.data, head);
        int flag = -1;
        for (int i = 0; i < size; i++) {
            p = p.next;
            if (p.data.equals(x)) {
                flag = i;
            }
        }
        return flag;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (T elem : this)//利用了实现了的迭代器
            str.append(elem + "");
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

    public static void main(String[] args) {
        CLinkedList<Integer> sl1 = new CLinkedList<>();

        sl1.add(0,1);
        sl1.add(0,2);
        sl1.add(0,3);
        sl1.add(0,4);
        System.out.println(sl1);
        System.out.println(sl1.get(0));

        sl1.remove(1);
        System.out.println(sl1);

//		sl1.reverse();
//		System.out.println(sl1);


//		sl1.removeRange(0,1);
//		System.out.println(sl1);
//        sl1.lastIndexOf(3);
    }
}