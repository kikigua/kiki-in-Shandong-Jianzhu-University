import java.io.*; 
import java.net.*; 
  
public class UDPClient { 
    public static void main(String args[]) throws Exception { 
        byte[] sendData = new byte[1024]; 
        byte[] receiveData = new byte[100]; 
        //创建输入流
        BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in)); 
        
        //构造数据报
        DatagramSocket clientSocket = new DatagramSocket(); 
        //Client所在主机的IP地址
        InetAddress IPAddress = InetAddress.getByName("localhost");     
        
        //接收通过键盘输入的字符串并将字符串转换为字节数组
        String sentence = inFromUser.readLine(); 
        sendData = sentence.getBytes();
        /*构造发送给Server的IP数据报
               sendData:发送给Server的数据
               IPAddress:Server所在主机的IP地址
               65000:Server的端口号
        */
        DatagramPacket sendPacket = new DatagramPacket (sendData, sendData.length, IPAddress, 65000); 
        clientSocket.send(sendPacket); 
        //构造接收Server返回的数据报
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
        clientSocket.receive(receivePacket); 
        //抽取数据报中的数据，即返回的变为大写的字符串
        String modifiedSentence = new String (receivePacket.getData()); 
        System.out.println("from server:" + modifiedSentence); 

        clientSocket.close(); 
    } 
} 
