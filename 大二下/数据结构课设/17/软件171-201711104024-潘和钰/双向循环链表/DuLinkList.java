package ch1;
import java.util.Scanner;
public class DuLinkList {
	 public DuLNode head;
	 public DuLinkList() {//构造空的双向循环链表
		 head=new DuLNode();
		 head.prior=head;
		 head.next=head;
	 }
	 public DuLinkList(int n)throws Exception{
		 this();
		 System.out.println("请输入：");
		 Scanner sc=new Scanner(System.in);
		 for(int i=0;i<n;i++)
		 {
			 insert(i,sc.next());
		 }
		 
		 System.out.println("创建成功！");
	 }
	 //插入
	 public void insert(int i,Object x)throws Exception{
		 DuLNode p= head.next;
		 int j=0;
		 while(!p.equals(head)&&j<i) {
			 p=p.next;
			 ++j;
		 }
		 if(j!=i) 
			 throw new Exception("插入位置不合法");
		 DuLNode s=new DuLNode(x);
		 p.prior.next=s;
		 s.prior=p.prior;
		 s.next=p;
		 p.prior=s;
	 }
	 //删除
	 public void remove(int i)throws Exception{
		 DuLNode p=head.next;
		 int j=0;
		 while(!p.equals(head)&&j<i) {
			 p=p.next;
			 ++j;
		 }
		 if(j!=i) {
			 throw new Exception("删除位置不合法");
		 }
		 p.prior.next=p.next;
		 p.next.prior=p.prior;
	 }
	  	//取值
	 public Object get(int i)throws Exception{
		 DuLNode p=head.next;
		 int j=0;
		 while(!p.equals(head)&&j<i) {
			 p=p.next;
			 ++j;
		 }
		 if(j>i||p.equals(head)) 
		 {
			 throw new Exception("第i个元素不存在");
		 }
		 return p.data;
	 }
	 
	 
	 //
	 public int  indexOf(Object x){
		 DuLNode p=head.next;
		 int j=0;
		 while(!p.equals(head) &&!p.data.equals(x)) {
			 p=p.next;
			 ++j;
		 }
		 if(!p.equals(head))
			 return j;
		 else 
			 return -1;
	 }
	 //长度
	 public int length(){
		 DuLNode p=head.next;
		 int j=0;
		 while(!p.equals(head)) {
			 p=p.next;
			 ++j;
		 }
		 return j;
	 }
	 //所有元素的值
	 public void display() {
		 DuLNode p=head.next;
		 while(p!=head) {
			 System.out.print(p.data+" ");
			 p=p.next;
		 }
		 System.out.println();
	 }
	 public void  inverse() throws Exception{
			DuLNode p=head.next;
			while(!p.next.equals(head)) {
				DuLNode temp = p.next;
				p.next = temp.next;
				temp.next.prior = p;
				insert(0,temp.data);
			}
				
			}
}
