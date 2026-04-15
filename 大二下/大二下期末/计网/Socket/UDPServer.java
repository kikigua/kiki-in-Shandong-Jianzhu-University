import java.net.*; 
  
class UDPServer { 
  public static void main(String args[]) throws Exception { 
  
      DatagramSocket serverSocket = new DatagramSocket(65000); 
      int count=0;
      
      byte[] receiveData = new byte[1024]; 
      byte[] sendData  = new byte[1024]; 
       
      while(count<100) {  
    	  count++;
    	  //构造用于从客户端接收数据的IP数据报
          DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
          //接收客户发送来的数据报，并抽取数据报中携带的数据
          serverSocket.receive(receivePacket); 
          String sentence = new String(receivePacket.getData()); 
          
          //提取客户机的IP地址及端口号
          InetAddress IPAddress = receivePacket.getAddress(); 
          int port = receivePacket.getPort(); 
          
          String capitalizedSentence = sentence.toUpperCase(); 
          
          //将字符串转换为字节数组
          sendData = capitalizedSentence.getBytes(); 
          
          //创建发送给客户的数据报，指定接收方(客户)的IP地址、端口号，以及携带的数据(sendData)
          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); 
          serverSocket.send(sendPacket); 
     }
     serverSocket.close();
  } 
}  
