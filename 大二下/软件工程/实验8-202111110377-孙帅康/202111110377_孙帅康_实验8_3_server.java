package socketserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
public class NioServer {
    public static final int SERVERPORT=5555;
    public static final String USERNAME="sunshuaikang"; //请改为自己的名字
    public static final String PASSWORD="123456";
    public static final String ISACK="ACK";
    public static final String ISNAK="NAK!";
// Selector selector;//选择器
// SelectionKey key;//key。 一个 key 代表一个 Selector 在 NIO 通道上的注册,类似主
    //键;
    // //取得这个 Key 后就可以对 Selector 在通道上进行操作
    private ByteBuffer echoBuffer = ByteBuffer.allocate( 1024 );// 通道数据缓冲
    //区
    public NioServer(){
    }
    public static void main(String[] args) throws IOException {
        NioServer ns=new NioServer();
        ns.BuildNioServer();
    }
    public void BuildNioServer() throws IOException{
/////////////////////////////////////////////////////////
///////先对服务端的 ServerSocket 进行注册,注册到 Selector ////
/////////////////////////////////////////////////////////
        ServerSocketChannel ssc = ServerSocketChannel.open();//新建 NIO 通道
        ssc.configureBlocking( false );//使通道为非阻塞
        ServerSocket ss = ssc.socket();//创建基于 NIO 通道的 socket 连接
//新建 socket 通道的端口
//        ss.bind(new InetSocketAddress("192.168.50.31",SERVERPORT));
        ss.bind(new InetSocketAddress("127.0.0.1",SERVERPORT));
//ss.bind(new InetSocketAddress("10.217.155.112",SERVERPORT));
        Selector selector=Selector.open();//获取一个选择器
//将 NIO 通道选绑定到择器,当然绑定后分配的主键为 skey
        SelectionKey skey = ssc.register( selector, SelectionKey.OP_ACCEPT );
////////////////////////////////////////////////////////////////////
//// 接收客户端的连接 Socket,并将此 Socket 也接连注册到 Selector ////
///////////////////////////////////////////////////////////////////
        while(true){
            int num = selector.select();//获取通道内是否有选择器的关心事件
            if(num<1){continue; }
            Set selectedKeys = selector.selectedKeys();//获取通道内关心事件的集合
            Iterator it = selectedKeys.iterator();
            while (it.hasNext()) {//遍历每个事件
                try{
                    SelectionKey key = (SelectionKey)it.next();
//有一个新联接接入事件,服务端事件
                    if ((key.readyOps() & SelectionKey.OP_ACCEPT)
                            == SelectionKey.OP_ACCEPT) {
// 接收这个新连接
                        ServerSocketChannel serverChanel =
                                (ServerSocketChannel)key.channel();
//从 serverSocketChannel 中创建出与客户端的连接 socketChannel
                        SocketChannel sc = serverChanel.accept();
                        sc.configureBlocking( false );
// Add the new connection to the selector
// 把新连接注册到选择器
                        SelectionKey newKey = sc.register( selector,
                                SelectionKey.OP_READ );
                        it.remove();
                        System.out.println( "Got connection from "+sc );
                    }else
//读客户端数据的事件,此时有客户端发数据过来,客户端事件
                        if((key.readyOps() & SelectionKey.OP_READ)
                                == SelectionKey.OP_READ){
// 读取数据
                            SocketChannel sc = (SocketChannel)key.channel();
                            int bytesEchoed = 0;
                            while((bytesEchoed = sc.read(echoBuffer))> 0){
                                System.out.println("bytesEchoed:"+bytesEchoed);
                            }
                            echoBuffer.flip();
                            System.out.println("limet:"+echoBuffer.limit());
                            byte [] content = new byte[echoBuffer.limit()];
                            echoBuffer.get(content);
                            String result=new String(content);
                            doPost(result,sc);
                            echoBuffer.clear();
                            it.remove();
                        }
                }catch(Exception e){}
            }
        }
    }
    public void doPost(String str,SocketChannel sc)throws Exception{
        boolean isok=false;
        int index=str.indexOf('|');
        if(index>0){
            String name=str.substring(0,index);
            String pswd=str.substring(index+1);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/text8","root","root");
            String sql="select * from user ";
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()){
                String username=rs.getString("userName");
                String password=rs.getString("userPw");
                if (name.equals(username)&&pswd.equals(password)) {
                    System.out.println("登陆成功");
                    isok=true;
                }
            }
            if (!isok){
                String sql1="INSERT INTO user VALUES (?,?,2)";
                PreparedStatement ps=conn.prepareStatement(sql1);
                ps.setString(1, name);
                ps.setString(2, pswd);
                System.out.println(name+pswd);
                ps.executeUpdate();
            }
        }else{
            isok=false;
        }
        String result="";
        if(isok){
            result="ACK";
        }else{
            result="NCK";
        }
        ByteBuffer bb = ByteBuffer.allocate( result.length() );
        bb.put(result.getBytes());
        bb.flip();
        try {
            sc.write(bb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bb.clear();
    }
}