package TankWar;

import java.awt.Frame;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ServerMain {
	public static StartFrame f1 = new StartFrame("̹�˴�ս�����");
	public static Server server;
	public static int model = 0; //0��ʾ����ģʽ,1��ʾ˫��ģʽ
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
				server = null;
				Object[] options = {"ȷ��","ȡ���ȴ�"};
				int option = JOptionPane.showOptionDialog(null, "�ȴ�������", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if(option == 1) {
					StartFrame.flag = 0;
					continue;
				}
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
		
	}
	
	
	
}
