package timu7;
//创建队列：

//创建链队列
public class LinkQueue {
	private Node front;  //队首指针
	private Node rear;   //队尾指针
	//入队
	public void offer(Object x){
		Node p = new Node(x);//初始化新结点
		if(front != null){   //队列非空
			rear.next = p;   
			rear = p;        //改变队尾的位置
		}
		else
			front = rear = p;
	}
	//出队
	public Object poll(){
		if(front!=null){
			Node p=front;       //p指向队首结点
			front=front.next;   //队首结点出列
			if(p == rear)       //被删除的结点是队尾结点时
				rear = null;
			return p.data;      //返回队首结点的数据域值
		}
		else
			return null;
	}
	//判断队列是否为空
	public boolean isEmpty(){
		return front == null;
	}

}