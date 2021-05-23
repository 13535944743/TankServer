package TankWar;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Start {
//	public static class BgPanel extends JPanel {
//		public BgPanel() {
//			
//		}
//		@Override
//		public void paintComponents(Graphics g) {
//			int width = this.getWidth();
//			int height = this.getHeight();
//			g.clearRect(0, 0, width, height);
//			
//			g.drawImage(ResourceMgr.tankwar, 0, 0, width, height, null);
//			
//
//		}
//		
//	}
	
	public static int flag ;
	
	public static int start(JFrame f) throws InterruptedException {
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
		f.setLayout(null);
		f.setSize(800,600);
		f.setVisible(true);
//		BgPanel panel = new BgPanel();
//		panel.setLayout(null);
//		f.setContentPane(panel);
//		panel.paintComponents(panel.getGraphics());
		Button b1 = new Button("单人游戏");
		b1.setFont( new Font("宋体", Font.PLAIN, 16));
		Color c = new Color(94, 108, 46);
		b1.setBackground(c);
		b1.setForeground(Color.WHITE);
		b1.setSize(100, 50);
		b1.addMouseListener(new MouseAdapter() {//鼠标监听事件
			public void mouseReleased(MouseEvent event) {
				if(event.getButton()==MouseEvent.BUTTON1) {  
					flag = 1;
					System.out.println("单人游戏");
				}
			}
			public void mouseEntered(MouseEvent event) {
				System.out.println("enter");
				b1.setBackground(Color.WHITE);
				b1.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				System.out.println("exited");
				b1.setBackground(c);
				b1.setForeground(Color.WHITE);
			}
		});
		b1.setLocation(500, 100);
		
		Button b2 = new Button("双人游戏");
		b2.setFont( new Font("宋体", Font.PLAIN, 16));
		b2.setBackground(c);
		b2.setForeground(Color.WHITE);
		b2.setSize(100, 50);
		b2.setLocation(500, 200);
		b2.addMouseListener(new MouseAdapter() {//鼠标监听事件
			public void mouseReleased(MouseEvent event) {
				if(event.getButton()==MouseEvent.BUTTON1) {  
					flag = 2;
					System.out.println("双人游戏");
				}
			}
			public void mouseEntered(MouseEvent event) {
				System.out.println("enter");
				b2.setBackground(Color.WHITE);
				b2.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				System.out.println("exited");
				b2.setBackground(c);
				b2.setForeground(Color.WHITE);
			}
		});
		Button b3 = new Button("游戏帮助");
		b3.setFont( new Font("宋体", Font.PLAIN, 16));
		b3.setBackground(c);
		b3.setForeground(Color.WHITE);
		b3.setSize(100, 50);
		b3.setLocation(500, 300);
		b3.addMouseListener(new MouseAdapter() {//鼠标监听事件
			public void mouseReleased(MouseEvent event) {
				if(event.getButton()==MouseEvent.BUTTON1) {  
					flag = 3;
				}
			}
			public void mouseEntered(MouseEvent event) {
				System.out.println("enter");
				b3.setBackground(Color.WHITE);
				b3.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				System.out.println("exited");
				b3.setBackground(c);
				b3.setForeground(Color.WHITE);
			}
		});
		Button b4 = new Button("退出游戏");
		b4.setFont( new Font("宋体", Font.PLAIN, 16));
		
		b4.setBackground(c);
		b4.setForeground(Color.WHITE);
		b4.setSize(100, 50);
		b4.setLocation(500, 400);
		b4.addMouseListener(new MouseAdapter() {//鼠标监听事件
			public void mouseReleased(MouseEvent event) {
				if(event.getButton()==MouseEvent.BUTTON1) {
					flag = 4;
				}
			}
			public void mouseEntered(MouseEvent event) {
				System.out.println("enter");
				b4.setBackground(Color.WHITE);
				b4.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				System.out.println("exited");
				b4.setBackground(c);
				b4.setForeground(Color.WHITE);
			}
		});
		
//		panel.add(b1);
//		panel.add(b2);
//		panel.add(b3);
//		panel.add(b4);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		
		while(flag == 0) {
			Thread.sleep(50);
		}
			f.setVisible(false);
			System.out.println(flag);
			return flag;
	}
	
	public static void help(Frame f1, Frame f2) {
		f2.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				f2.setVisible(false);
				f2.dispose();
				System.exit(0);
			}
		});
		f2.setLayout(null);
		Button b1 = new Button("W     上");
		Color c = new Color(94, 108, 46);
		b1.setBackground(c);
		b1.setForeground(Color.WHITE);
		b1.setSize(100, 50);
		b1.setLocation(300, 50);
		Button b2 = new Button("A     左");
		b2.setBackground(c);
		b2.setForeground(Color.WHITE);
		b2.setSize(100, 50);
		b2.setLocation(300, 120);
		Button b3 = new Button("S     下");
		b3.setBackground(c);
		b3.setForeground(Color.WHITE);
		b3.setSize(100, 50);
		b3.setLocation(300, 190);
		Button b4 = new Button("D     右");
		b4.setBackground(c);
		b4.setForeground(Color.WHITE);
		b4.setSize(100, 50);
		b4.setLocation(300, 260);
		Button b5 = new Button("J     开炮");
		b5.setBackground(c);
		b5.setForeground(Color.WHITE);
		b5.setSize(100, 50);
		b5.setLocation(300, 330);
		Button b6 = new Button("返回");
		b6.setBackground(c);
		b6.setForeground(Color.WHITE);
		b6.setSize(100, 50);
		b6.setLocation(200, 400);
		b6.addMouseListener(new MouseAdapter() {//鼠标监听事件
			public void mouseReleased(MouseEvent event) {
				if(event.getButton() == MouseEvent.BUTTON1) {  
						try {
							f2.setVisible(false);
							f1.setVisible(true);
							//boolean x = f1.getGraphics().drawImage(ResourceMgr.tankwar, 170 , 30, 300, 145, null);
							System.out.println("返回");							
						}
						catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			public void mouseEntered(MouseEvent event) {
				System.out.println("enter");
				b6.setBackground(Color.WHITE);
				b6.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				System.out.println("exited");
				b6.setBackground(c);
				b6.setForeground(Color.WHITE);
			}
		});
		
		f2.add(b1);
		f2.add(b2);
//		f2.add(b3);
		f2.add(b4);
		f2.add(b5);
		f2.add(b6);
		
		f2.setSize(800,600);
		f2.setVisible(true);
	}
}

