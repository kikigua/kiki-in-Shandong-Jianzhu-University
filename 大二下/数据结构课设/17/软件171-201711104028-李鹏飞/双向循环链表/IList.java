package 双向循环列表;
//接口类
public interface IList<T> {
public void insert(int i,T x)throws Exception;
public void remove(int i)throws Exception;
public void clear();
public boolean isEmpty();
public Object get(int i)throws Exception;
public int length();
public void display();
public void  inverse();
}