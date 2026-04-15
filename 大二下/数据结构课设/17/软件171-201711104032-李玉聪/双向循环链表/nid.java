package keshe;
import java.util.Scanner;
import keshe.Node;
/*
  双向循环链表
 */
  public class nid{
	  public Node head;
	  public nid() {
		  head=new Node();
		  head.prve=head;
		  head.next=head;
	  }   
    //插入
 public void insert(int i,Object x) throws Exception {
        Node p=head.next;
        int j=0;
    while(!p.equals(head)&&j<i){
    	p=p.next ;
    	++j;
    }
    if(j!=i){
    	throw new Exception("插入不正确");
    }
    Node s=new Node(x);
    p.prve.next =s;
    s.prve=p.prve;
    s.next=p;
    p.prve=s;
 }
 //删除
   public int remove(int i) {
        Node p=head.next;
        int j=0;
   while(!p.equals(head)&&j<i){
	   p=p.next ;
	   ++j;
   }
   if(j!=i){
	   return -1;
   }
   p.prve.next =p.next ;
   p.next.prve =p.prve;
   return j;
   }
   
    /*
      根据位置找元素
      取第i个位置上的元素
     */
    public Object get(int i) throws Exception{
        Node p=head.next;
        int j=0;
       while(!p.equals(head)&&j<i){
    	   p=p.next ;
    	   ++j;
       }
       if(j!=i){
    	   throw new Exception ("查找位置不合法");
       }
    	  return p.data;
    }   
    /*根据元素找位置
      返回元素x第一次出现在双向循环链表中的位置号
     */
    public int indexof(Object x){
        Node p=head.next;
        int j=0;
      while(!p.equals(head)&&!p.data.equals(x)){
    	  p=p.next ;
    	  ++j;
      }
      if(!p.equals(head)){
    	  return j;
    	  }
      else return -1;
    }

    /*
    输出双向循环链表中所有的元素值
     */
    public void display(){
        Node p=head.next;
        while(!p.equals(head)){
        	System.out.print(p.data+" ");
        	p=p.next ;
        }
        System.out.println( );
    }
    
    
    /*
    求双向循环链表的长度，即元素个数
     */
    public  int length(){
        Node p=head.next;
        int j=0;
        while(!p.equals(head)){
        	p=p.next ;
        	++j;
        }
        return j;
    }
    
    /*
     * 实现双向循环链表的就地逆置
     */
    public void resever()  {
        Node p=head.next;
        Node q=p.next;
      while(!p.equals(head)){
    	  Node s=q.next ;//临时变量
    	  q.next=p;
    	  p.prve=q;
    	  p=q;
    	  q=s;
      }
      //补上   头
      q.next=p;
	  p.prve=q;
	  p=head;
	  this.display();
    }
    

    
    public static void main(String args[])throws  Exception{
        nid d=new nid( );
        Scanner in=new Scanner(System.in);
        System.out.println("输入想要创建的双向循环链表节点的个数");
        int i=in.nextInt();
        System.out.println("依次输入所有节点");
        for(int j=0;j<i;j++){
            Object x=in.next();
            try{
            d.insert(j,x);
            }
            catch (Exception e){
            System.out.println("依次输入所有节点");
            }
        }
        d.display();
        boolean flag=true;
        while(flag) {
            System.out.println("1.插入一个节点");
            System.out.println("2.删除一个节点");
            System.out.println("3.根据元素查找结点位置");
            System.out.println("4.根据节点位置查找元素");
            System.out.println("5.输出链表");
            System.out.println("6.链表长度");
            System.out.println("7.就地逆置");
            System.out.println("8.退出");
            int o=in.nextInt();
            switch (o){
                case 1:
                    System.out.println("输入你要插入的节点位置");
                    int n = in.nextInt();
                    System.out.println("输入你要插入的节点的值");
                    Object s=in.next();
                    try{
                    	d.insert(n,s);
                    	System.out.println("插入成功");
                        d.display();
                    }
                    catch(Exception e){
                    	System.out.println(e.getMessage());
                    }
                                      

                    break;
                case 2:
                    System.out.println("输入你要删除的节点位置");
                    int m= in.nextInt();
                    if(d.remove(m)==-1){
                        System.out.println("删除位置不合法");
                    }
                    else{
                        System.out.println("删除成功");
                        d.display();
                    }
                    break;
                case 3:
                    System.out.println("输入你要查找元素");
                    Object x = in.next();
                   if (d.indexof(x) == -1) {
                        System.out.println("该元素不存在");
                    } else {
                       System.out.println("该元素的位置为" + d.indexof(x));
                    }
                    break;
                case 4:
                    System.out.println("输入你要查找的节点位置");
                    int j = in.nextInt();
                    try {
                        System.out.println("第" + j + "个元素为" + d.get(j));
                    }
                    catch (Exception e){
                        System.out.println(" 查找位置不合法");
                    }
                    break;
                case 5:
                    d.display();
                    break;
                case 6:
                    System.out.println("长度为" + d.length());
                    break;
                case 7:
                    System.out.println("逆置的结果为：");
                    d.resever();
                    break;
                case 8:
                    flag=false;
                    break;
                default:
                    break;
            }
        }
    }
}
 