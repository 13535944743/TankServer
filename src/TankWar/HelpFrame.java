package TankWar;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import TankWar.StartFrame.BgPanel;

public class HelpFrame extends JFrame{
	public static int flag = 0;
	public HelpFrame(String title) throws InterruptedException {
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
		JLabel b0 = new JLabel("Tips:Ë«ÈËÄ£Ê½,¹²ÏíÉúÃü,×¢ÒâÅäºÏ", JLabel.CENTER);
		b0.setFont( new Font("ÑÅºÚ", Font.BOLD, 16));
		b0.setForeground(Color.RED);
		
		b0.setBackground(Color.WHITE);
		b0.setSize(300, 50);
		b0.setLocation(400, 100);
		JLabel b1 = new JLabel("W£ºÉÏ", JLabel.CENTER);
		b1.setFont( new Font("ÑÅºÚ", Font.BOLD, 16));
		b1.setBorder(BorderFactory.createLineBorder(Color.BLACK));   //Ìí¼Ó±ß¿ò
		b1.setBackground(Color.WHITE);
		b1.setSize(100, 50);
		b1.setLocation(500, 170);
		JLabel b2 = new JLabel("A£º×ó",JLabel.CENTER);
		b2.setFont( new Font("ÑÅºÚ", Font.BOLD, 16));
		b2.setBorder(BorderFactory.createLineBorder(Color.BLACK));   //Ìí¼Ó±ß¿ò
		b2.setBackground(Color.WHITE);
		b2.setSize(100, 50);
		b2.setLocation(500, 240);
		JLabel b3 = new JLabel("S£ºÏÂ", JLabel.CENTER);
		b3.setFont( new Font("ÑÅºÚ", Font.BOLD, 16));
		b3.setBorder(BorderFactory.createLineBorder(Color.BLACK));   //Ìí¼Ó±ß¿ò
		b3.setBackground(Color.WHITE);
		b3.setSize(100, 50);
		b3.setLocation(500, 310);
		JLabel b4 = new JLabel("D£ºÓÒ", JLabel.CENTER);
		b4.setFont( new Font("ÑÅºÚ", Font.BOLD, 16));
		b4.setBorder(BorderFactory.createLineBorder(Color.BLACK));   //Ìí¼Ó±ß¿ò
		b4.setBackground(Color.WHITE);
		b4.setSize(100, 50);
		b4.setLocation(500, 380);
		JLabel b5 = new JLabel("J£º¿ªÅÚ", JLabel.CENTER);
		b5.setFont( new Font("ÑÅºÚ", Font.BOLD, 16));
		b5.setBorder(BorderFactory.createLineBorder(Color.BLACK));   //Ìí¼Ó±ß¿ò
//		b5.setOpaque(true); 
		b5.setBackground(Color.WHITE);
		b5.setSize(100, 50);
		b5.setLocation(500, 450);
		JButton b6 = new JButton("·µ»Ø");
		b6.setFont( new Font("ÑÅºÚ", Font.BOLD, 16));
		Color c = new Color(94, 108, 46);
		b6.setBackground(c);
		b6.setForeground(Color.WHITE);
		b6.setSize(100, 50);
		b6.setLocation(650, 450);
		b6.addMouseListener(new MouseAdapter() {//Êó±ê¼àÌýÊÂ¼þ
			public void mouseReleased(MouseEvent event) {
				if(event.getButton() == MouseEvent.BUTTON1) {  
						flag = 1;		
				}
			}
			public void mouseEntered(MouseEvent event) {
				b6.setBackground(Color.WHITE);
				b6.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				b6.setBackground(c);
				b6.setForeground(Color.WHITE);
			}
		});
		
		root.add(b0);
		root.add(b1);
		root.add(b2);
		root.add(b3);
		root.add(b4);
		root.add(b5);
		root.add(b6);
		setSize(800,600);
		setLocation(550, 200);
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
			
			g.drawImage(ResourceMgr.tankwar, 0, 0, width, height, null);
		}
		
	}
}
