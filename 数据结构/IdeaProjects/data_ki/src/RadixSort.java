import java.util.Iterator;

//基数排序
public class RadixSort {
    private int radix = 10;
    private MyList[] listArray;

    public RadixSort() {
        listArray = new MyList[2 * radix - 1];
        for (int i = 0; i < 2 * radix - 1; i++)
            listArray[i] = new MyList();
    }

    private int pass(long max) {
        int p = 1;
        while (max > radix) {
            max /= radix;
            ++p;
        }
        return p;
    }

    public void sort(int[] data) {
        MyList list = new MyList();
        long max = 0;
        for (int i = 0; i < data.length; i++) {
            list.add(new MyList.Node(data[i]));
            long datai = Math.abs((long) data[i]);
            if (datai > max) {
                max = datai;
            }
        }
        int p = pass(max);
        radixSort(list, p);
        int pos = 0;
        for (MyList.Node node : list) {
            data[pos++] = node.data;
        }
    }

    private void radixSort(MyList list, int pass) {
        int a = 1;
        for (int i = 0; i < pass; i++) {
            for (MyList.Node node : list) {
                int index = (node.data / a) % radix + 9;//负变正
                listArray[index].add(node);
            }
            list.clear();
            for (MyList mlist : listArray)
                list.mergeList(mlist);
            a *= radix;// 寻找下一位，在新的数位重复排序
        }
    }

    private static class MyList implements Iterable<MyList.Node> {
        private Node head;// 头结点
        private Node tail;// 尾结点

        private static class Node {
            private int data;
            private Node next;

             Node(int d) {
                this.data = d;
            }
            /*
             *Node(int data){
             * this.data=data;
             * }
             */
        }

        void add(Node n) {
            n.next = null;
            if (head != null)
                tail = tail.next = n;
            else
                head = tail = n;
        }

        void clear() {
            head = tail = null;
        }

        void mergeList(MyList next) {
            if (next.head == null)
                return;
            if (head == null) {
                head = next.head;
                tail = next.tail;
            } else {
                this.tail.next = next.head;
                this.tail = next.tail;
            }
            // 保证listArray清空
            next.clear();
        }

        public Iterator<Node> iterator() {
            return new Itr();
        }

        private class Itr implements Iterator<Node> {
            private Node current;

            public Itr() {
                current = head;
            }

            public boolean hasNext() {
                return current != null;
            }

            public Node next() {
                Node cur = current;
                current = current.next;
                return cur;
            }
        }

    }

    public static void main(String[] args) {
//		int[] data = { -399, -198, 222888, 88, -20, -521, 0, 61, -800, 288, 555, 666, 777, 566, Integer.MIN_VALUE,
//				Integer.MAX_VALUE, -2222555 };
//		new RadixSort().sort(data);
//		for (int i : data)
//			System.out.print(i + " ");
//		System.out.println();

//        int[] data = { 1123456, -65894550, -23456, -789, -65, 1478, 0, 222, 6584, 33777 };
//        new RadixSort().sort(data);
//        for (int i : data)
//            System.out.print(i + " ");
//        System.out.println();


        int[] data ={ 1123456, -65894550, -23456, -789, -65, 1478, 0, 222, 6584, 33777 };
        RadixSort rs1=new RadixSort();
        rs1.sort(data);
        for (int i=0;i< data.length;i++){
            System.out.print(data[i]+" ");
        }

    }

}

