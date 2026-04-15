package sort;

import java.util.HashMap;
//基数排序
public class CardinalitySort<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    public class CLinkedList<T> {
        Node<T> head;
        Node<T> tail;
        private int size;

        public CLinkedList() {
            // TODO Auto-generated constructor stub
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void clear() {
            while (head != null) {// 从第1个结点开始，逐个删除
                Node<T> q = head;// q待删除结点
                head = head.next;// 下一个结点

                q.data = null;// 帮助GC
                q.next = null;
            }
            size = 0;
        }

        public void addlast(T x) {
            Node<T> t = new Node<T>(x, null);
            if (size == 0) {
                head = tail = t;
            } else {
                tail.next = t;
                tail = t;
            }
            ++size;
        }

        public void print() {
            Node<T> p = head;
            while (p != null) {
                System.out.print(p.data + " ");
                p = p.next;
            }
            System.out.println();
        }

        // 将other链表接到s的后面
        public void merge(CLinkedList<T> other) {
            if (other.head == null) {
                return;
            } else if (this.head == null) {
                this.head = other.head;
            } else {
                this.tail.next = other.head;
            }
            this.tail = other.tail;
            this.size += other.size;
        }
    }

    //返回需要几次分配与收集
    public int getn(Integer[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(arr[i]) > max) {
                if (arr[i] <= 0) {
                    max = -arr[i];
                } else {
                    max = arr[i];
                }
            }
        }
        int k = 0;
        while(max!=0) {
            max /=10;
            k++;
        }
        return k;
    }

    public int Value(int k, int n) {
        int result = 0;
        for (int i = 0; i < k; i++) {
            n = n / 10;
        }
        n = n % 10;
        return n;
    }
    public void sort(Integer[] arr) {
        int n = getn(arr);// 要分配和收集n次
        CLinkedList<Integer> list = new CLinkedList<Integer>();// 这个链表用来保存a中的数据，也是最终的结果
        for (int i = 0; i < arr.length; i++) {
            list.addlast(arr[i]);
        }
        // 创建链表
        HashMap<Integer, CLinkedList<Integer>> l = new HashMap<Integer, CLinkedList<Integer>>();
        // 要分配和收集n次
        int k = 0;// 第一轮从个位开始比较
        while (n > 0) {
            for (int i = -9; i <= 9; i++) {
                l.put(i, new CLinkedList<Integer>());
            }
            // 要把list链表中所有的数字放进去
            Node<Integer> p = list.head;
            while (p != null) {
                int x = p.data;
                x = Value(k, x);
                l.get(x).addlast(p.data);// 放进去
                p = p.next;
            }
            // 然后要清空list
            list.clear();
            // 再将数据挨个放到list中// !!!!!还要把各个l里的元素清除!!!
            for (int i = -9; i <= 9; i++) {
                if (!l.get(i).isEmpty()) {
                    list.merge(l.get(i));// 原先这里的链表被合并了就没有了连到别处了,所以需要再次创建(自动删除了)
                }
            }
            k++;
            n--;
        }
        list.print();
    }

    public static void main(String[] args) {
        CardinalitySort<Integer> s = new CardinalitySort<Integer>();
        Integer[] a = { -1, -3, 2, 4, 60, 946, -546, 0, -1, -2, -54, -23, -32, -78 };
//		s.hhh();
        s.sort(a);
    }
}

