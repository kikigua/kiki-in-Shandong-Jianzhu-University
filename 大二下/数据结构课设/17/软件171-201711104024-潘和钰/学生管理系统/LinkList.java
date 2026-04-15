package 选做题三;
import java.util.Scanner; 
public class LinkList {
 public Node head;
 public LinkList() {
	 head=new Node();
 }
 public LinkList(int n,boolean Order)throws Exception {
	 this();
	 if(Order)
		 creat1(n);//尾插法
	 else 
		 creat2(n);//头插法
 }
 public void creat1(int n)throws Exception{
	 Scanner sc=new Scanner(System.in);
	 for(int i=0;i<n;i++) {
		 insert(length(),sc.next());
	 }
 }
 public void creat2(int n)throws Exception {
	 Scanner sc=new Scanner(System.in);
	 for(int i=0;i<n;i++) {
		 insert(0,sc.next());
	 }
 }
 public void clear() {
	 head.data=null;
	 head.next=null;
 }
 public boolean isEmpty() {
	 return head.next==null;
 }
 public int length() {
	 Node p=head.next;
	 int j=0;
	 while(p!=null)
	 {
		 p=p.next;
		 ++j;
	 }
	 return j;
 }
 public Object get(int i)throws Exception{
	Node p=head.next;
	int j=0;
	while(j<i&&p!=null) {
		 p=p.next;
		 ++j;
	 }
	if(j>i||p==null) {
		System.out.println("此元素不存在");
	}
	return p.data;
 }
 public void insert(int i,Object x )throws Exception {
	 Node p=head;
	 int j=-1;
	 while(p!=null&&j<i-1) {
		 p=p.next;
		 ++j;
	 }
	 if(i-1<j||p==null) {
		 throw new Exception("插入位置不合理");
	 }
	 Node s=new Node(x);
	 s.next=p.next;
	 p.next=s;
 }
 public void remove(int i)throws Exception{
	 Node p=head.next;
	 int j=-1;
	 while(p.next!=null&&j<i-1) {
		 p=p.next;
		 ++j;
	 }
	 if(p.next==null||i-1<j) {
		 throw new Exception("删除位置不合法");
		 
	 }
	 p.next=p.next.next;
 }
 public int indexOf(Object x) {
	 Node p=head.next;
	 int j=0;
	 while(p!=null&&!p.data.equals(x)) {
		 p=p.next;
		 ++j;
	 }
	 if(p!=null)
		 return j;
	 else
		 return -1;
 }
 public void display() {
	 Node p=head.next;
	 while(p!=null)
	 { System.out.print(p.data);
	 p=p.next;
	 }
	 System.out.println();
 }
}