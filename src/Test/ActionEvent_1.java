package Test;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ActionEvent_1 extends JFrame implements ActionListener{
	JPanel jp1;
	JButton jb1,jb2,jb3,jb4;
	JTextArea jta1;
	public static void main(String[] args) {
		ActionEvent_1 a=new ActionEvent_1();
	}
	ActionEvent_1()
	{
		jp1=new JPanel();
		jb1=new JButton("W");
		jb1.addActionListener(this);
		jb2=new JButton("A");
		jb2.addActionListener(this);
		jb3=new JButton("S");
		jb3.addActionListener(this);
		jb4=new JButton("D");
		jb4.addActionListener(this);
		jta1=new JTextArea();
		
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		jp1.add(jb4);
		this.add(jp1,BorderLayout.NORTH);
		this.add(jta1);
		
		this.setTitle("我的小程序");
		this.setSize(400, 300);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1)
			jta1.setText("W");
		else if(e.getSource()==jb2)
			jta1.setText("A");
		else if(e.getSource()==jb3)
			jta1.setText("S");
		else if(e.getSource()==jb4)
			jta1.setText("D");	
	}
}
