package keshe;

public class Node{
	  public Object data;
	  public Node prve;
	  public Node next;
	  public Node(){
	  	this(null);
	  }
	  public Node(Object data){
	  	this.data=data;
	  	this.prve=null;
	  	this.next=null;
	  }
	  }