package 双向循环列表;
//双向链表的节点类
public class DulNode<T> {
public T data;
public DulNode<T> prior;
public DulNode<T> next;
public DulNode(){
	this(null);
}
public DulNode(T data){
	this.data=data;
	this.prior=null;
	this.next=null;
}
}