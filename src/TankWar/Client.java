package TankWar;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	Socket socket;
	public Client() throws UnknownHostException, IOException {
		socket = new Socket("127.0.0.1", 5679);
	}
	
}
