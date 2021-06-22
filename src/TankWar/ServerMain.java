package TankWar;

import java.awt.Frame;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ServerMain {
	public static StartFrame f1 = new StartFrame("坦克大战服务端");
	public static Server server;
	public static int model = 0; //0表示单人模式,1表示双人模式
	public static void main(String[] args) throws InterruptedException, IOException{
		
		HelpFrame f2 = new HelpFrame("游戏帮助");
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
				Object[] options = {"确定","取消等待"};
				int option = JOptionPane.showOptionDialog(null, "等待连接中", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
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
				System.out.println("退出游戏");
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
