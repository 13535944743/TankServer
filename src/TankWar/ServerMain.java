package TankWar;

import java.awt.Frame;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class ServerMain {
	public static StartFrame f1 = new StartFrame("̹�˴�ս�����");
	public static Server server;
	public static int model = 0; //0��ʾ����ģʽ,1��ʾ˫��ģʽ
	public static boolean game_over = false;
	public static void main(String[] args) throws InterruptedException, IOException{
		
		HelpFrame f2 = new HelpFrame("��Ϸ����");
		f2.setVisible(false);
		while(true) {
			Thread.sleep(50);
			if(StartFrame.flag == 1) {
				model = 0;
				f1.setVisible(false);
				Method.single_game();
				StartFrame.flag = 0;
			}
			else if(StartFrame.flag == 2) {
				model = 1;
				server = new Server(f1);
				StartFrame.flag = 0;
			}
			else if(StartFrame.flag == 3) {
				f1.setVisible(false);
				f2.setVisible(true);
				StartFrame.flag = 0;
			}
			else if(StartFrame.flag == 4) {
				System.out.println("�˳���Ϸ");
				StartFrame.flag = 0;
				System.exit(0);
			}
			if(HelpFrame.flag == 1) {
				f2.setVisible(false);
				f1.setVisible(true);
				HelpFrame.flag = 0;
				
			}
			
		}
//		JFrame f1 = new JFrame("̹�˴�ս");
//		JFrame f2 = new JFrame("��Ϸ����");
//		Start.start(f1);
//		while(true) {
//			Thread.sleep(50);
//			if(Start.flag == 1) { 
//				f1.setVisible(false);
//				single_game(f1);
//				Start.flag = 0;
//			 } 
//			else if(Start.flag == 3) {
//				f1.setVisible(false);
//				Start.flag = 0;
//				Start.help(f1, f2);
//			}
//			else if(Start.flag == 4) {
//				System.out.println("�˳���Ϸ");
//				Start.flag = 0;
//				System.exit(0);
//			}
//		}
		
	}
	
	
	
}
