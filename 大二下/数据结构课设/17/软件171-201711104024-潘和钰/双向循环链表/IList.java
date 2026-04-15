package ch1;

public interface IList {
	public void clear();
	public boolean isEmpty();
	public int length();
	public Object get(int i);
	public void insert(int i,Object x) ;
	public void remove (int i);
	public int indexOf(Object i);
	public void display();

}
