package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import server.clientInfo;

public class client {

	private DatagramSocket socket;
	private InetAddress adress;
	private int port;
	private boolean running;
	private String name;
	public client(String name,String adress,int port) {
		try {
			this.adress=InetAddress.getByName(adress);
			this.port=port;
			this.name=name;
			socket=new DatagramSocket();
			running=true;
			listen();
			send("\\con:"+name);
			}
		catch(Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	public void send(String message) {
		try {
			if(!message.startsWith("\\")) {
				message=name+":"+message;
			}
			
			message+="//e";
			byte[]data=message.getBytes();
			DatagramPacket packet=new DatagramPacket(data,data.length,adress,port);
			socket.send(packet);
			System.out.println("send message to" + adress.getHostAddress()+":" +port);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		
	}
	
	/**
	 * has a thread-running the entire time that the server running-wait for message 
	 */
	private void listen() {
		Thread listenThread=new Thread("chatprogram listener") {
			public void run() {
				try {
					while(running) {
						byte[]data=new byte[1024];
						DatagramPacket packet=new DatagramPacket(data, data.length);
						socket.receive(packet);
						String message=new String(data);
						message=message.substring(0, message.indexOf("//e"));
						
						if(!iscommand(message, packet)) {
							clientWindow.printToConsole(message);
							
						}
							
						
						
						
					}
					
				}catch(Exception e){
					e.printStackTrace();
					
				}
				
				
			}
		
	};listenThread.start();
	
	}
	
	
	/**
	 * server command LIST
	 * \\con[name]->connects client to srever
	 * \\dis[id]->disconecct client from server
	 * @param message
	 * @param packet
	 * @return
	 */
	private static boolean iscommand(String message,DatagramPacket packet) {
		
		if(message.startsWith("\\con:")) {
			//run conecction code
			return true;
		}
		
		
		
		return false;
	}

}
