package TankWar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Server{
	private ServerSocket server;
	private Socket socket;
	public static PrintWriter out;
	public BufferedReader in;
	public static boolean isChanged = true;
	public TankFrame tf;
	
	public Server(JFrame f) throws IOException, InterruptedException {
		System.out.println("进入");
		this.server = new ServerSocket(5679);
		this.socket = server.accept();
		this.out = new PrintWriter(socket.getOutputStream());        //由socket对象得到输出流，并构造PrintWriter对象
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));    //构造BufferReader对象
		f.setVisible(false);
		Double_Game dg = new Double_Game();
		dg.start();
		Thread.sleep(50);
		tf = Method.tf;
		sendMessage();
//		out.println("连接成功");
//		out.flush();
//		out.println(true);
//		out.flush();
//		String readline = in.readLine();
		
	}
	private void sendMessage() throws InterruptedException {
		while(true) {
			if(tf.data == "") {
				Thread.sleep(50);
			}
			if(tf.data != "") {
				out.println(tf.data);
				System.out.println(tf.data);
				out.flush();
				tf.data = "";
			}
		}
		
	}
	public ServerSocket getServer() {
		return server;
	}
	public void setServer(ServerSocket server) {
		this.server = server;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
