package 大二上课设选做题二;

public class LinkStack implements IStack {
	private Node top;
	public void clear() {//将栈置空
		top=null;
	}

	public boolean isEmpty() {//判断栈是否为空
		return top==null;
	}

	public int length() {//求栈链长度方法
		Node p=top;
		int length=0;
		while(p!=null){
			p=p.next;
			++length;
		}
		return length;
	}

	public Object peek() {//取栈顶元素并返回它的值
		if(!isEmpty())
			return top.data;
		else
		return null;
	}

	public Object pop() {//出栈方法
		if(isEmpty()){
		return null;
		}
		else{
			Node p=top;
			top=top.next;
			return p.data;
		}
	}

	public void push(Object x) {//入栈方法
		Node p=new Node(x);
		p.next=top;
		top=p;
	}
}
