package ArrayList_01;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

//顺序表（利用数组顺序存储）
public class CArrayList<T> implements IList<T>, Iterable<T>,Cloneable {

    private Object[] listElem;// 存储数据的数组
    private int size;// 已经存储的数据的个数,也就是顺序表的当前长度

    //顺序表类的构造函数，构造一个存储空间容量为maxSize的线性表
    public CArrayList(int maxSize) {
        listElem = new Object[maxSize];  //为顺序表分配maxSize个存储单元
         size = 0;                       //置顺序表的当前长度为0
    }

    //判断顺序表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //返回顺序表的长度
    public int size() {
        return size;
    }

    // 将索引号的合法性检查抽出作为函数
    private void rangeCheckForAdd(int index) {// add用
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(String.valueOf(index));
    }
    private void rangeCheck(int index) {// 其它函数用
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(String.valueOf(index));
    }

    //返回索引为index处的值
    @SuppressWarnings("unchecked")
    public T get(int index) {
        rangeCheck(index);
        return (T) listElem[index];
    }

    //顺序表的插入操作
    public void add(int index, T x) {
        ensureCapacity(size + 1);
        rangeCheckForAdd(index);
        // moveBackward(i);
        // if (size - index > 0)//在表尾增加数据
        System.arraycopy(listElem, index, listElem, index + 1, size - index);
        listElem[index] = x;
        ++size;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        rangeCheck(index);
        T value = (T) listElem[index];
        // moveForward(i);
        // if(size - index -1 > 0 )//删除表尾的数据
        System.arraycopy(listElem, index + 1, listElem, index, size - index - 1);
        listElem[--size] = null;
        return value;
    }

    // 注意： x不能是null
    public int indexOf(T x) {
        for (int i = 0; i < size; i++) {
            if (x.equals(listElem[i]))
                return i;// 必须调用equals,不能使用 x == listElem[i]
        }
        return -1;
    }

    // 使用下面的代码，则CArrayList的数据就可以是null
    public int indexOf1(T x) {
        if (x == null) {
            for (int i = 0; i < size; i++) {
                if (listElem[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (x.equals(listElem[i]))
                    return i;
            }
        }
        return -1;
    }

    public void clear() {
        // Arrays.fill(listElem, null);//将数组listElem的各元素赋值为null
        for (int i = 0; i < size; i++)
            listElem[i] = null;// 为了防止内存泄漏
        size = 0;
    }

    // 以下来自java类库的ArrayList
    /**
     * The maximum size of array to allocate. Some VMs reserve some header words in
     * an array. Attempts to allocate larger arrays may result in OutOfMemoryError:
     * Requested array size exceeds VM limit
     */

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = listElem.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;

        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);

        // minCapacity is usually close to size, so this is a win:
        listElem = Arrays.copyOf(listElem, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    private void ensureCapacity(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - listElem.length > 0)
            grow(minCapacity);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; ++i)
            str.append(listElem[i] + "  ");
        return str.toString();
    }

    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof CArrayList<?>) {// 语法要求，不能写成CArrayList<T>
            CArrayList<T> rhd = (CArrayList<T>) obj;
            if (this.size != rhd.size)
                return false;
            // if(Arrays.equals(listElem, rhd.listElem)) return true;
            for (int i = 0; i < size; i++) {
                if (!listElem[i].equals(rhd.listElem[i]))
                    return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(listElem);
    }

    @SuppressWarnings("unchecked")
    public Object clone() {
        try {
            CArrayList<T> v = (CArrayList<T>) super.clone();
            v.listElem = Arrays.copyOf(listElem, listElem.length);
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        private int curPos;

        // public Itr() {
        // curPos = 0;
        // }

        public boolean hasNext() {
            return curPos != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            return (T) listElem[curPos++];
        }
    }

    //顺序表的就地逆置
    public void reverse() {
        int i = 0;
        int j = size-1;
        Object temp;
        while (i < j) {
            temp = listElem[i];
            listElem[i] = listElem[j];
            listElem[j] = temp;
            i++;
            j--;
        }
    }

    public void removeRange(int from, int to) {
        if (from < 0 || to > size - 1)
            throw new IndexOutOfBoundsException();
        System.arraycopy(listElem, to + 1, listElem, from, size - to - 1);
        int deletedsize = to - from + 1;
        while (deletedsize-- > 0) {
            listElem[--size] = null;
        }

    }

    public int lastindexOf(T x) {
        for (int i=size-1;i>=0;i--) {
            if (listElem[i].equals(x))
                return i;
        }
        return -1;
    }

    public void insertSortedList(T x){
        for (int i = 0; i < size; i++) {
            if((int)listElem[i]>(Integer) x){
                for (int j=size;j>i;j--){
                    listElem[j]=listElem[j-1];
                }
                size++;
                listElem[i]=x;
                break;
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		CArrayList<Integer> sl = new CArrayList<>(20);
//
//		sl.add(0, 1);
//		sl.add(0, 2);
//		sl.add(0, 3);
//		System.out.println(sl);
//
//		System.out.println(sl.size());
//		int pos = sl.indexOf(Integer.valueOf(3));
//		if (pos == -1)
//			System.out.println("Not Found");
//		else
//			System.out.println("At " + pos);
//
//		Integer elem = sl.get(1);
//		System.out.println(elem);
//
//		sl.remove(0);
//		// sl.remove(1);
//		// 因为覆盖了toString，可以直接输出CArrayList的对象
//		System.out.println(sl);
//
//		if (sl.isEmpty())
//			System.out.println("empty");
//		else
//			System.out.println("Not empty");
//
//		sl.add(2, 4);
//		// 因为实现了Iterable接口，所以可用以下的方式输出
//		for (Iterator<Integer> i = sl.iterator(); i.hasNext();)
//			System.out.print(i.next() + " ");
//		System.out.println();
//
//		for (Integer ele : sl)
//			System.out.print(ele + " ");
//		System.out.println();
//
//		sl.forEach(System.out::print);
//		System.out.println();
//
//		for (int i = 0; i < sl.size(); i++)
//			System.out.print(sl.get(i) + " ");
//		System.out.println();
//
//		@SuppressWarnings("unchecked")
//		CArrayList<Integer> sc = (CArrayList<Integer>) sl.clone();
//		System.out.println(sc);
//
//		if (sl.equals(sc))
//			System.out.println("equal");
//		else
//			System.out.println("not equal");
//
//		sc.add(1, 3);
//
//		System.out.println(sc);
//
//		if (sl.equals(sc))
//			System.out.println("equal");
//		else
//			System.out.println("not equal");
//
//		// 线性表的数据是线性表
//		CArrayList<CArrayList<Integer>> sl1 = new CArrayList<>(4);
//		sl1.add(0, sl);
//		sl1.add(1, sc);
//
//		System.out.println(sl1);
//
//		System.out.println(sl1.get(0).get(1));
//		System.out.println(sl1.get(1).get(1));


        Scanner scanner=new Scanner(System.in);
        CArrayList<Integer> test = new CArrayList<>(20);

        test.add(0, 1);
        test.add(1, 3);
        test.add(2, 5);
        test.add(3, 9);
        test.add(4, 10);


        System.out.println(test.size());
        System.out.println("就地逆序前"+test);
        test.reverse();
        System.out.println("就地逆序后"+test);
        test.reverse();
        System.out.println("请输入要删除的位置范围，中间用空格隔开");
        int from=scanner.nextInt();
        int to=scanner.nextInt();
        System.out.println("删除前线性表为"+test);
        test.removeRange(from,to);
        System.out.println("删除["+from+","+to+"]位置的数据后为"+test);

        System.out.println("请输入要查找的数据元素");
        Object x=scanner.next();
        System.out.println("与"+x+"相等的数据元素的最大下标为"+test.lastindexOf(Integer.parseInt(String.valueOf(x))));


        System.out.println("请输入要插入的数据:");
        int number=scanner.nextInt();
        System.out.println("插入前线性表为"+test);
        test.insertSortedList(number);
        System.out.println("插入"+number+"后为"+test);
    }
}
