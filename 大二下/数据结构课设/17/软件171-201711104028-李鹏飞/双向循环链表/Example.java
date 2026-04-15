package 双向循环列表;
import java.util.Scanner;
/*
 * 1.建立一个空表。
2.在第i个位置插入新的元素x。
3.删除第i个位置上的元素。
4.取第i个位置上的元素。
5.返回元素x第一次出现在双向循环链表中的位置号。
6.求双向循环链表的长度，即元素个数。
7.输出双向循环链表中所有的元素值。
8.实现双向循环链表的就地逆置。
 */
public class Example {
	static Scanner cin=new Scanner(System.in);
 	public static String showMenu(){
		System.out.println("系统功能：   创建 一个双向链表");
		System.out.println("          1 在第i个位置插入新元素x ");
		System.out.println("          2 删除第i个位置上的元素 ");
		System.out.println("          3 提取第i个位置上的元素 ");
		System.out.println("          4 返回元素x第一次出现在双向循环链表中的位置号 ");
		System.out.println("          5 求双向循环链表的长度，即元素个数 ");
		System.out.println("          6 输出双向循环链表中所有的元素值 ");
		System.out.println("          7 双向循环链表的就地逆置 ");
		System.out.println("          0 退出");
		System.out.println("请输入指令:");
		return cin.next();
		}
	public static <T> void main(String[] args)throws Exception {
		//从键盘上依次输入数据逆序创建双向循环链表，并输出双向循环链表中的各元素值；
    	DuLinkList<String> L=new DuLinkList<String>();
        Scanner sc=new Scanner(System.in);  
        System.out.println("请输入链表的大小:");  
        int m=sc.nextInt();   
        System.out.println("请输入双向循环链表中的数据元素：");  
        for(int i=0;i<m;i++){  
            String l=sc.next();  
            L.insert(i,l);  
            }   
        while(true){
    		String choice=showMenu();
    		if(choice.equals("1")){//在第i个位置插入新元素
    			System.out.println("请输入要插入元素的位置：");  
    			Scanner s1=new Scanner(System.in);
    			int i=s1.nextInt();
    			System.out.println("请输入要插入的数据元素：");
    			Scanner s2=new Scanner(System.in);
    			String x=s2.next();
    			try {
					L.insert(i,x);
					System.out.println("在第"+i+"个位置插入"+x+"后双向循环链表中的各个元素值为：");
					L.display();
    			} catch (Exception e) {
    				System.out.println("在第"+i+"个位置插入"+x+"失败！！！");
				}  
    		}
            else if(choice.equals("2")){//删除第i个位置上的元素 ");
            	System.out.println("请输入要删除元素的位置：");  
            	Scanner s3=new Scanner(System.in);
            	int i=s3.nextInt();
            	 try {
					L.remove(i);
					 System.out.println("删除第"+i+"个元素后双向循环链表中的各个元素值为：");
					 L.display();
				} catch (Exception e) {
					System.out.println("删除失败！！！第"+i+"个元素不存在");
				}   
            }
            else if(choice.equals("3")){//提取第i个位置上的元素 
            	System.out.println("请输入要提取元素的位置：");  
            	Scanner s4=new Scanner(System.in);
            	int i=s4.nextInt();
            	try {
					if(L.get(i)!=null)
						System.out.println("双向循环链表的第"+i+"个元素为："+L.get(i));
				} catch (Exception e) {
					System.out.println("双向循环链表的第"+i+"个元素不存在");
				}
            }
            else if(choice.equals("4")){     //返回元素x第一次出现在双向循环链表中的位置号
            	System.out.println("请输入要提取的元素的值：");  
            	Scanner s5=new Scanner(System.in);
            	String x=s5.next();
            	int order =L.indexOf(x);
					if(order!=-1)
						System.out.println("双向循环链表中值为"+x+"的数据元素出现的位置为："+order);
					else
					    System.out.println("双向循环链表中值为"+x+"的元素不存在");	
            }
            else if(choice.equals("5")){//求双向循环链表的长度，即元素个数 
            	 System.out.println("双向循环链表的长度："+L.length());	
            }
            else if(choice.equals("6")){//输出双向循环链表中所有的元素值 
            	if(L.isEmpty())
             	   System.out.println("双向循环链表为空");
             	else{
             	   System.out.println("双向循环链表中各个元素的值为：") ;//双向链表不为空
             	   L.display();
             	}
            }
            else if(choice.equals("7")){//双向循环链表的就地逆置 
            	System.out.println("双向循环链表逆置后为：");
                L.inverse();
                L.display();
            }
		   else if(choice.equals("0")){
			System.exit(0);//退出系统
		   } 
       }
	}
}