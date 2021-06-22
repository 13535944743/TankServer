package TankWar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Server{
	public static ServerSocket server;
	public static  Socket socket;
	public static PrintWriter out;
	public static BufferedReader in;
	public TankFrame tf;
	public Server(JFrame f) throws IOException, InterruptedException {
		this.server = new ServerSocket(5679);
		this.socket = server.accept();
		this.out = new PrintWriter(socket.getOutputStream());        //由socket对象得到输出流，并构造PrintWriter对象
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));    //构造BufferReader对象
		f.setVisible(false);
		Thread dg = new Thread(new Double_Game());
		dg.start();
		while(Method.tf == null) {
			Thread.sleep(50);
		}
		this.tf = Method.tf;
		ReceiveThread rt = new ReceiveThread(this.tf);
		rt.start();
		sendMessage();
//		this.socket.close();
//		this.server.close();
//		out.println("连接成功");
//		out.flush();
//		out.println(true);
//		out.flush();
//		String readline = in.readLine();
		
	}
	private void sendMessage() throws InterruptedException {
		
		while(true) {
			if(tf.finish) {
				out.println("finish");
				out.flush();
				break;
			}
			if(tf.data == "") {
				Thread.sleep(30);
			}
			if(tf.data != "") {
				String[] buff = tf.data.split("@");
				if(buff[0].equals("e_up") || buff[0].equals("e")) {
					System.out.println(tf.data);
				}
				out.println(tf.data);
//				System.out.println(tf.data);
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
