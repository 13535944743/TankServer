package Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("127.0.0.1", 8888);
		Scanner sc = new Scanner(System.in);
		OutputStream os = client.getOutputStream();
		
		Thread t = new Thread() {
			InputStream is = client.getInputStream();
			public void run() {
				boolean b = true;
				while(b) {
					try {
						byte[] bt = new byte[1024];
						int length = is.read(bt);
						String str = new String(bt, 0, length);
						System.out.println("\t\t\t" + str + ":������");
					} catch(IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		};
		t.start();
		boolean b = true;
		while(b) {
			System.out.println("�ͻ������룺");
			os.write(sc.next().getBytes());
			os.flush();
		}
		os.close();
		client.shutdownInput();
		client.close();
		
	}
}
