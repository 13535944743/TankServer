package TankWar;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket server;
	Socket socket;
	public Server() throws IOException {
		server = new ServerSocket(5679);
		socket = server.accept();
		System.out.println("连接成功");
	}
	
}
