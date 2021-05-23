package TankWar;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import TankWar.StartFrame.BgPanel;

public class LostFrame extends JFrame{
	public static int flag = 0;
	public LostFrame(String title) throws InterruptedException {
		super(title);
		BgPanel root = new BgPanel();
		setContentPane(root);
		this.setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		setResizable(false);
		setLayout(null);
		JButton b1 = new JButton("返回首页");
		b1.setFont( new Font("雅黑", Font.BOLD, 16));
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b1.setSize(100, 50);
		b1.setLocation(350, 450);
		b1.addMouseListener(new MouseAdapter() {//鼠标监听事件
			public void mouseReleased(MouseEvent event) {
				if(event.getButton() == MouseEvent.BUTTON1) {  
						try {
							dispose();
							Main.f1.setVisible(true);
						}
						catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			public void mouseEntered(MouseEvent event) {
				System.out.println("enter");
				b1.setBackground(Color.WHITE);
				b1.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				System.out.println("exited");
				b1.setBackground(Color.BLACK);
				b1.setForeground(Color.WHITE);
			}
		});
		
		JButton b2 = new JButton("退出游戏");
		b2.setFont( new Font("雅黑", Font.BOLD, 16));
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);
		b2.setSize(100, 50);
		b2.setLocation(500, 450);
		b2.addMouseListener(new MouseAdapter() {//鼠标监听事件
			public void mouseReleased(MouseEvent event) {
				if(event.getButton() == MouseEvent.BUTTON1) {  
						try {
							System.exit(0);
						}
						catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			public void mouseEntered(MouseEvent event) {
				System.out.println("enter");
				b2.setBackground(Color.WHITE);
				b2.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				System.out.println("exited");
				b2.setBackground(Color.BLACK);
				b2.setForeground(Color.WHITE);
			}
		});
		JButton b3 = new JButton("再玩一次");
		b3.setFont( new Font("雅黑", Font.BOLD, 16));
		b3.setBackground(Color.BLACK);
		b3.setForeground(Color.WHITE);
		b3.setSize(100, 50);
		b3.setLocation(200, 450);
		b3.addMouseListener(new MouseAdapter() {//鼠标监听事件
			public void mouseReleased(MouseEvent event) {
				if(event.getButton() == MouseEvent.BUTTON1) {  
						try {
							dispose();
							StartFrame.flag = 1;
						}
						catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			public void mouseEntered(MouseEvent event) {
				System.out.println("enter");
				b3.setBackground(Color.WHITE);
				b3.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				System.out.println("exited");
				b3.setBackground(Color.BLACK);
				b3.setForeground(Color.WHITE);
			}
		});
		
		root.add(b1);
		root.add(b2);
		root.add(b3);
		setSize(800,600);
		setVisible(true);
	}
	private class BgPanel extends JPanel {
		public BgPanel() {
			
		}
		@Override
		protected void paintComponent(Graphics g) {
			int width = this.getWidth();
			int height = this.getHeight();
			g.clearRect(0, 0, width, height);
			
			g.drawImage(ResourceMgr.gameover, 0, 0, width, height, null);
		}
		
	}
}
