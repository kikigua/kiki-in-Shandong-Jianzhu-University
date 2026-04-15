package 双向循环列表;
import java.util.Scanner;
//DuLinkList类 ：创建双向循环链表
public class DuLinkList<T> implements IList<T>{
public DulNode<T> head;   

public DuLinkList(){
	head= new DulNode<T>(); 
	head.prior= head;  
	head.next= head;
}
//从表头到表尾逆向创建双向循环列表的算法，其中n为该双向循环列表的结点个数
public DuLinkList(int n)throws Exception{
	this();
	Scanner sc=new Scanner(System.in);  
	for(int j=0;j<n;j++)
		insert(j,(T)sc.next());  
}


//insert()函数：插入元素
public void insert(int i,T x)throws Exception{
	DulNode<T> p=head.next;  
	int j=0;
	while(!p.equals(head)&&j<i){   
		p=p.next;                 
		++j;
	}
	if(j!=i&&p.equals(head))     
		throw new Exception("插入位置不合法！"); 
	DulNode<T> s=new DulNode<T>(x);  
	p.prior.next=s;
	s.prior=p.prior;
	s.next=p;
	p.prior=s;
	
}


//remove()函数：删除元素
public void remove(int i)throws Exception{
	DulNode<T> p=head.next;
	int j=0;
	while (!p.equals(head)&&j<i){
		p=p.next;
		++j;
	}
	if(j!=i)
		throw new Exception("删除位置不合法");
		p.prior.next=p.next;
		p.next.prior=p.prior;	
}


//将一个已经存在的双向链表置空
public void clear() {
	head.prior=head;
	head.next=head;
}


//判断双向链表是否为空
public boolean isEmpty() {
	return head.equals(head.next);
}


//提取元素
public Object get(int i)throws Exception {
	DulNode<T> p=head.next;
	int j=0;
	while(!p.equals(head)&&j<i){
		p=p.next;
		++j;
	}
	if(j>i||p.equals(head)){
		throw new Exception("第"+i+"个元素不存在");
	}
	return p.data;
}


//求长度
public int length() {
	DulNode<T> p=head.next;
	int length=0;
	while (!p.equals(head)){
		p=p.next;
		++length;
	}
	return length;
}


//输出结点值
public void display() {
	DulNode<T> node=head.next;
	while (!node.equals(head)){
		System.out.print(node.data+" ");
		node=node.next;
	}
	System.out.println();	
}


//在双向链表中查找值为x的元素，如果找到则返回该元素的位置，否则返回-1
public int indexOf(T x) {
	DulNode<T> p=head.next;
	int j=0;
	while (!p.equals(head)&&!p.data.equals(x)){
		p=p.next;
		++j;
	}
	if(!p.equals(head))
		return j;
	else
	    return -1;
}


//就地逆置
public void  inverse(){
	DulNode<T> p=head;
	DulNode<T> q; 
  while(!p.next.equals(head)){
	  q=p.next;
	  p.next=p.prior;
	  p.prior=q;
	  p=q;
  }	
  q=p.next;
	  p.next=p.prior;
	  p.prior=q;
}
}

