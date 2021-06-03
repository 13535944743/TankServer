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

public class StartFrame extends JFrame{
	public static int flag;
	public StartFrame(String title) {
		super(title);
		BgPanel root = new BgPanel();
		root.setLayout(null);
		setContentPane(root);
		this.setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		setLayout(null);
		setResizable(false);
		JButton b1 = new JButton("单人游戏");
		b1.setFont( new Font("雅黑", Font.BOLD, 16));
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
		
		JButton b2 = new JButton("双人游戏");
		b2.setFont( new Font("雅黑", Font.BOLD, 16));
		b2.setBackground(c);
		b2.setForeground(Color.WHITE);
		b2.setSize(100, 50);
		b2.setLocation(500, 200);
		b2.addMouseListener(new MouseAdapter() {//鼠标监听事件
			public void mouseReleased(MouseEvent event) {
				if(event.getButton()==MouseEvent.BUTTON1) {  
					flag = 2;
				}
			}
			public void mouseEntered(MouseEvent event) {
				b2.setBackground(Color.WHITE);
				b2.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				b2.setBackground(c);
				b2.setForeground(Color.WHITE);
			}
		});
		JButton b3 = new JButton("游戏帮助");
		b3.setFont( new Font("雅黑", Font.BOLD, 16));
		b3.setBackground(c);
		b3.setForeground(Color.WHITE);
		b3.setSize(100, 50);
		b3.addMouseListener(new MouseAdapter() {//鼠标监听事件
			public void mouseReleased(MouseEvent event) {
				if(event.getButton()==MouseEvent.BUTTON1) {  
					flag = 3;
				}
			}
			public void mouseEntered(MouseEvent event) {
				b3.setBackground(Color.WHITE);
				b3.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				b3.setBackground(c);
				b3.setForeground(Color.WHITE);
			}
		});
		b3.setLocation(500, 300);
		JButton b4 = new JButton("退出游戏");
		b4.setFont( new Font("雅黑", Font.BOLD, 16));
		
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
				b4.setBackground(Color.WHITE);
				b4.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				b4.setBackground(c);
				b4.setForeground(Color.WHITE);
			}
		});
		
		root.add(b1);
		root.add(b2);
		root.add(b3);
		root.add(b4);
		setSize(800,600);
		setVisible(true);
		while(flag == 0) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(flag);
	}
	
	public class BgPanel extends JPanel {
		public BgPanel() {
			
		}

		@Override
		protected void paintComponent(Graphics g) {
			int width = this.getWidth();
			int height = this.getHeight();
			g.clearRect(0, 0, width, height);
			
			g.drawImage(ResourceMgr.tankwar, 0, 0, width, height, null);
		}
		
	}
}
