package clientsite;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import serversite.Server;

@SuppressWarnings("unused")
public class Client {

	public static void main(String[] args) {
		Client c = new Client("192.168.178.63", 8000);
		c.sendMessage("Guten Tag, Herr Server. Wie geht es Ihnen?");
	}
		
	
	
	private InetSocketAddress address;
	
	public Client(String hostname, int port) {
		address = new InetSocketAddress(hostname, port);  
	
			
	}
	
	public void sendMessage(String msg) {
		
		
			
		
		try {
			
		System.out.println("[Client]: Verbinde zu Server...");	
		Socket s = new Socket();
		s.connect(address, 5000);
		System.out.println("[Client]: Verbunden!");
		
		System.out.println("[Client]: Sende Nachricht...");
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		pw.println(msg);
		pw.flush();
		System.out.println("[Client]: Nachricht gesendet!");
		
		//Verbindung schlieﬂen 
		pw.close();
		s.close();
		
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}