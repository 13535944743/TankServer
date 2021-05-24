package TankWar;

import java.awt.Frame;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Main {
	public static StartFrame f1 = new StartFrame("坦克大战");
	public static void main(String[] args) throws InterruptedException{
		
		HelpFrame f2 = new HelpFrame("游戏帮助");
		f2.setVisible(false);
		while(true) {
			Thread.sleep(50);
			if(StartFrame.flag == 1) {
				f1.setVisible(false);
				Method.single_game(f1);
				StartFrame.flag = 0;
			}
			else if(StartFrame.flag == 2) {
				f1.setVisible(false);
//				Server s = new Server();
			}
			else if(StartFrame.flag == 3) {
				f1.setVisible(false);
				f2.setVisible(true);
				StartFrame.flag = 0;
			}
			else if(StartFrame.flag == 4) {
				System.out.println("退出游戏");
				Start.flag = 0;
				System.exit(0);
			}
			if(HelpFrame.flag == 1) {
				f2.setVisible(false);
				f1.setVisible(true);
				HelpFrame.flag = 0;
				
			}
			
		}
//		JFrame f1 = new JFrame("坦克大战");
//		JFrame f2 = new JFrame("游戏帮助");
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
//				System.out.println("退出游戏");
//				Start.flag = 0;
//				System.exit(0);
//			}
//		}
		
	}
	
	
	
}
