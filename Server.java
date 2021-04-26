package serversite;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) {
		// Hier starten wir später das Programm (den Server)
		Server TestServer = new Server(8000);
		TestServer.startListening();
	}
	private int port;
	
	public Server(int port) {		//jeder mit einem Netzwerkverbundenen Rechner hat eine Internetprotokolladresse(zB Die der Version 4)
									//Neben dieser Adresse gibt es noch einen Port (Die Netzwerkkarte muss Daten, die sie bekommt, irgendwie separieren können)
		this.port = port;
		
	}
	
	public void startListening() {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true) {
					try {
						System.out.println("[Server]: Server starten...");
						ServerSocket serverSocket = new ServerSocket(port);
						System.out.println("[Server]: Warten auf Verbindung...");
						Socket remoteClient = serverSocket.accept();
						System.out.println("[Server]: Client verbunden..." + remoteClient.getRemoteSocketAddress());
					
						Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(remoteClient.getInputStream())));
						if (sc.hasNextLine()) {
							System.out.println("[Server]: Message from client: " + sc.nextLine());
						
						}
					
						//Verbindung schließen
						sc.close();
						remoteClient.close();
						serverSocket.close();
					
					
					}catch(Exception e) {
						e.printStackTrace();
						
					}
						
				}
			}
		}).start();
		
	}

}
