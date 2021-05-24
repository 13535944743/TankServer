package Test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import TankWar.ResourceMgr;

public class PaintTest extends JFrame{
	public PaintTest() {
		setSize(800,625);
		setResizable(false);
		setTitle("̹�˴�ս");
//		setBackground(Color.BLACK);
		setLayout(null);
		JPanel root = new JPanel();
		add(root);
		
		root.setLayout(null);
		
		root.setBounds(600, 300, 200, 300);
		root.setBackground(Color.WHITE);
		JButton b1 = new JButton("������ҳ");
		b1.setOpaque(true);
		b1.setFont( new Font("�ź�", Font.BOLD, 16));
		Color c = new Color(94, 108, 46);
		b1.setBackground(Color.RED);
		b1.setForeground(Color.BLACK);
		b1.setBounds(50, 0, 100, 100);
		b1.addMouseListener(new MouseAdapter() {//�������¼�
			public void mouseReleased(MouseEvent event) {
				if(event.getButton()==MouseEvent.BUTTON1) {  
					System.out.println("������ҳ");
				}
			}
			public void mouseEntered(MouseEvent event) {
				System.out.println("enter");
				b1.setBackground(Color.WHITE);
				b1.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent event) {
				System.out.println("exited");
				b1.setBackground(Color.WHITE);
				b1.setForeground(Color.BLACK);
			}
		});
		root.add(b1);
		
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
	}
	public static void main(String[] args) {
		PaintTest p = new PaintTest();
	}

}
