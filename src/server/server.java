package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class server {
	
	private static DatagramSocket socket;
	private static boolean running;
	private static int clientId;
	private static ArrayList<clientInfo> clients=new ArrayList<clientInfo>();
	
	public static void start(int port){
		try {
			socket=new DatagramSocket(port);
			running=true;
			listen();
			System.out.println("server start on port" + port);
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	/**
	 * send a message to every connected client
	 */
	private static void broadcast(String message) {
		
		
	}
	
	/**
	 * send message to indivduel client
	 */
	private static void send(String message, InetAddress adress,int port) {
		try {
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
	private static void listen() {
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
							broadcast(message);
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
			String name=message.substring(message.indexOf(":"+1));
			clients.add(new clientInfo(name,clientId++,packet.getAddress(),packet.getPort()));
			broadcast("User " +name+ ",conected");
			return true;
		}
		
		
		
		return false;
	}
	
	/**
	 * stop the server
	 */
	public static void stop() {
		
		
	}

}
