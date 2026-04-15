import java.io.*; 
import java.net.*; 
public class TCPClient
{   public static void main(String argv[]) throws Exception 
    { 
        String sentence; 
        String modifiedSentence; 
        //创建TCP Socket，建立与本机中运行在65123端口的Server的TCP连接
        Socket clientSocket = new Socket("localhost", 65123); 
        
        //获得clientSocket发送缓存大小
        System.out.println("sender buffer:"+clientSocket.getSendBufferSize());  
        
        //创建输入输出流
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
        BufferedReader inFromServer =new BufferedReader ( new InputStreamReader (clientSocket.getInputStream()));         
        DataOutputStream outToServer = new DataOutputStream (clientSocket.getOutputStream()); 
        
        //接收用户通过键盘输入的字符串并通过Socket发送给Server
        System.out.print("input a string:");  
        sentence = inFromUser.readLine(); 
        outToServer.writeBytes(sentence + '\n'); 
        
        //读取Server返回的字符串
        modifiedSentence = inFromServer.readLine(); 
        System.out.println("from server: " + modifiedSentence); 

        clientSocket.close();  //close socket and TCP connection 
  }                 
} 


        
