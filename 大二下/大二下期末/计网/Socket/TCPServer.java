import java.io.*;                          
import java.net.*; 

public class TCPServer { 
    public static void main(String argv[]) throws Exception { 
        String clientSentence; 
        String capitalizedSentence; 
        int buffSize;
        int count=0;
        System.out.println("The server is listening at:65123");
        
        //创建侦听socket
        ServerSocket welcomeSocket = new ServerSocket(65123); 
        buffSize = welcomeSocket.getReceiveBufferSize();
        System.out.println("receiving buffer:"+buffSize+" bytes.");
      
        while(count<100) { 
          count++;
          //客户连接请求到达，创建具体与客户通信的connection socket
          Socket connectionSocket = welcomeSocket.accept(); 
          System.out.println("sending buffer:"+connectionSocket.getSendBufferSize());
          
          //获取TCP连接相关联的输入/出流
          BufferedReader inFromClient =new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
          DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 
          
          //读取客户发送来的字符串
          clientSentence = inFromClient.readLine(); 
          System.out.println("received msg from client:"+clientSentence);
          capitalizedSentence = clientSentence.toUpperCase() + '\n'; 

          //返回变为大写后的字符串
          outToClient.writeBytes(capitalizedSentence);   
          connectionSocket.close();
        } 
        welcomeSocket.close();
     } 
} 
 
                                         
