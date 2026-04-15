package LinearList;

public class DuLNode {
	public Object data;//存放结点值
	public DuLNode prior;//前驱结点引用
	public DuLNode next;//后继节点的引用
	public DuLNode() {//无参书构造函数
		this(null);
	}
	public DuLNode(Object data)//构造值为data结点
	{
		this.data=data;
		this.prior=null;
		this.next=null;
	}
}
