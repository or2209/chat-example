package server;

import java.net.InetAddress;

/**
 * this class store our connected client information
 * @author or laharty
 *
 */
public class clientInfo {
	
	private InetAddress adress;
	private int port;
	private String name;
	private int id;
	
	public clientInfo(String name,int id,InetAddress adress,int port) {
		this.name=name;
		this.id=id;
		this.adress=adress;
		this.port=port;
	}

	public InetAddress getAdress() {
		return adress;
	}

	public void setAdress(InetAddress adress) {
		this.adress = adress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	

}
