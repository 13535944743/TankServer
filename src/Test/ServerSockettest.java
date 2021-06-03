package Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSockettest {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8888);
		System.out.println("服务器启动!");
		Socket s = ss.accept();
		boolean b = true;
		InputStream is = s.getInputStream();
		
		Thread t = new Thread() {
			OutputStream os = s.getOutputStream();
			Scanner sc = new Scanner(System.in);
			public void run() {
				boolean b = true;
				while(b) {
					try {
						System.out.println("服务器输入：");
						os.write(sc.next().getBytes());
						os.flush();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
		while(b) {
			byte[] bt = new byte[1024];
			int length = is.read(bt);
			String str = new String(bt, 0, length);
			System.out.println("\t\t\t" + str + ":客户端");
		}
		
		is.close();
		s.shutdownInput();
		s.close();
		ss.close();
	}

}
