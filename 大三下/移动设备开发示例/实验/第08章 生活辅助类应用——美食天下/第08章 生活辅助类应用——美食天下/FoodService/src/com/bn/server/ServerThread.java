package com.bn.server;

import java.net.*;
public class ServerThread extends Thread{
	ServerSocket ss;				//定义一个ServerSocket对象
	int technique;					//定一个义端口号
	public ServerThread (int technique){	//构造函数，
		this.technique=technique;			
	}
	@Override
	public void run(){							//重写run方法	
		try{
			ss=new ServerSocket(technique);			//创建一个ServerSocket对象
			System.out.println("start on "+technique);
			while(true){				
				Socket sk=ss.accept();
				new ServerAgentThread(sk).start();	//创建并开启一个代理线程
			}}
		catch(Exception e){e.printStackTrace();}
	}
	public static void main(String args[]){
		new ServerThread(8887).start();		//创建3个不同端口号的线程
		new ServerThread(8888).start();
		new ServerThread(8889).start();
	}
}
