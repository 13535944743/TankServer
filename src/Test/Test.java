package Test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class Test {
	public static void main(String[] args) {
		Frame f = new Frame("̹�˴�ս");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
		f.setLayout(null);
		JButton b1 = new JButton("������Ϸ");
		b1.setSize(300, 200);
		b1.setFont( new Font("����", Font.BOLD, 50));
		b1.setForeground(Color.RED);
		 b1.setLocation(300, 100); 
		 JButton b2 = new JButton("˫����Ϸ");
			b2.setSize(500, 600);
			 b2.setLocation(300, 100); 
		 /*
		 JButton b2 = new JButton("˫����Ϸ"); b2.setSize(100,
		 * 50); b2.setLocation(300, 200); JButton b3 = new JButton("��Ϸ����");
		 * b3.setSize(100, 50); b3.setLocation(300, 300); JButton b4 = new
		 * JButton("�˳���Ϸ"); b4.setSize(100, 50); b4.setLocation(300, 400);
		 */
		
		f.add(b1);
		f.add(b2);
		
//		f.add(b3);
//		f.add(b4);
		f.setSize(900,720);
		f.setVisible(true);

	}

}

