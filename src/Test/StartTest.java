package Test;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import TankWar.ResourceMgr;

public class StartTest {
	public static int flag ;
	
	public static int start() throws InterruptedException {
		Frame f = new Frame("坦克大战");
//		String url = "img/cursor.png";
//		Toolkit tk = Toolkit.getDefaultToolkit();
//		Image image = new ImageIcon(url).getImage();
//		Cursor cursor= tk.createCustomCursor(image, new Point(10, 10), url);
		
//		Toolkit tk = Toolkit.getDefaultToolkit();
//		URL classUrl = f.getClass().getResource("");
//		Image imageCursor = tk.getImage(classUrl);
//		Cursor cursor= tk.createCustomCursor(imageCursor, new Point(0, 0), "cursor");
//		f.setCursor(cursor);
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
//		f.addMouseListener(new MouseAdapter() {//鼠标监听事件
//			public void mouseReleased(MouseEvent event) {
//				if(event.getButton()==MouseEvent.BUTTON1) {  
//					flag = 1;
//					System.out.println("单人游戏");
//				}
//			}
//			public void mouseEntered(MouseEvent event) {
////				System.out.println(event.getX());
////				System.out.println(event.getY());
//			}
//			public void mouseExited(MouseEvent event) {
//				
//			}
//			public void mouseMoved(MouseEvent event){
//				System.out.println(event.getX());
//				System.out.println(event.getY());
//			}
//			
//		});
		f.setLayout(null);
		f.setSize(800,600);
		f.setVisible(true);
		f.getGraphics().drawImage(ResourceMgr.tankwar, 0, 0, ResourceMgr.tankwar.getWidth(), ResourceMgr.tankwar.getWidth(), null);
		f.getGraphics().drawImage(ResourceMgr.enemy1L, 100, 200, ResourceMgr.enemy1L.getWidth(), ResourceMgr.enemy1L.getWidth(), null);
//		Button b1 = new Button("单人游戏");
//		b1.setSize(100, 50);
//		b1.addMouseListener(new MouseAdapter() {//鼠标监听事件
//			public void mouseReleased(MouseEvent event) {
//				if(event.getButton()==MouseEvent.BUTTON1) {  
//					System.out.println("Yes");
//					flag = 1;
//				}
//			}
//			public void mouseEntered(MouseEvent event) {
//				System.out.println("enter");
//				b1.setFont( new Font("宋体", Font.BOLD, 16));
//				b1.setForeground(Color.WHITE);
//				b1.setBackground(Color.GREEN);
//			}
//			public void mouseExited(MouseEvent event) {
//				System.out.println("exited");
//				b1.setFont( new Font("宋体", Font.BOLD, 16));
//				b1.setForeground(Color.getHSBColor(0, 0, 0));
//				b1.setBackground(Color.getHSBColor(0, 0, 0));
//			}
//		});
//		b1.setLocation(300, 100);
//		Button b2 = new Button("双人游戏");
//		b2.setSize(100, 50);
//		b2.setLocation(300, 200);
//		Button b3 = new Button("游戏帮助");
//		b3.setSize(100, 50);
//		b3.setLocation(300, 300);
//		
//		Button b4 = new Button("退出游戏");
//		b4.setSize(100, 50);
//		b4.setLocation(300, 400);
//		b4.addMouseListener(new MouseAdapter() {//鼠标监听事件
//			public void mouseReleased(MouseEvent event) {
//				if(event.getButton()==MouseEvent.BUTTON1) {
//					flag = 4;
//				}
//			}
//		});
//		
//		f.add(b1);
//		f.add(b2);
//		f.add(b3);
//		f.add(b4);
		return flag;
	}
}

