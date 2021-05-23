package TankWar;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;


class TankFrame extends JFrame{	
	//test
	List<Bullet> bullets = new ArrayList<>();//存放子弹，实现发射多颗子弹
	List<Tank> player = new ArrayList<>();
	List<Tank> enemies = new ArrayList<>();
	List<Wall> walls = new ArrayList<>();
	List<SteelWall> steelwalls = new ArrayList<>();
	Home home = new Home(277, 575,true, this);
	Blast blast = new Blast(100, 100, this);
	int chance = 3;
	int step_to_win = 40;
	public final int Window_Width = 800, Window_Height = 625;
	int flag = 0; 
	
	public TankFrame() {
		setSize(Window_Width,Window_Height);
		setResizable(false);
		setTitle("坦克大战");
		setBackground(Color.BLACK);
		setLayout(null);
		JPanel root = new JPanel();
		add(root);
		root.setLayout(null);
		root.setOpaque(true);
		root.setBounds(600, 300, 200, 300);
		root.setBackground(Color.WHITE);
//		JButton b1 = new JButton("返回首页");
//		b1.setFont( new Font("雅黑", Font.BOLD, 16));
//		Color c = new Color(94, 108, 46);
//		b1.setBackground(Color.RED);
//		b1.setForeground(Color.BLACK);
//		b1.setBounds(650, 300, 100, 100);
//		b1.addMouseListener(new MouseAdapter() {//鼠标监听事件
//			public void mouseReleased(MouseEvent event) {
//				if(event.getButton()==MouseEvent.BUTTON1) {  
//					System.out.println("返回首页");
//				}
//			}
//			public void mouseEntered(MouseEvent event) {
//				System.out.println("enter");
//				b1.setBackground(Color.WHITE);
//				b1.setForeground(Color.BLACK);
//			}
//			public void mouseExited(MouseEvent event) {
//				System.out.println("exited");
//				b1.setBackground(Color.WHITE);
//				b1.setForeground(Color.BLACK);
//			}
//		});
//		root.add(b1);
		
		setVisible(true);
		this.addKeyListener( new MyKeyListener());
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
	}
	
	
	@Override
	public void repaint() {
		super.repaint();
		update(getGraphics());
		flag = 1;
	}


	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(Window_Width, Window_Height);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
//		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, Window_Width, Window_Height);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}  //双缓冲，处理闪烁问题，相当于在其他地方画好后，再直接用
	
	public void paint(Graphics g) {
		if(flag == 1) {
			flag = 0;
			return;
		}
//		System.out.println("画");
		int width = this.getWidth();
		int height = this.getHeight();
		g.clearRect(0, 0, width, height);
		
		g.setColor(Color.RED);
		g.drawString("子弹数量："+ bullets.size(), 60, 50);
		g.drawString("敌人数量："+ enemies.size(), 60, 70);
		g.drawString("Life:"+ chance, 650, 50);
		g.drawString("剩余敌人:"+ step_to_win, 650, 70);
		g.setColor(Color.RED);
        
		home.paint(g);
		for(int i = 0; i < player.size(); i++) {
			player.get(i).paint(g);
		}
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).paint(g);
		}
		for(int i = 0; i < walls.size(); i++) {
			walls.get(i).paint(g);
		}
		for(int i = 0; i < steelwalls.size(); i++) {
			steelwalls.get(i).paint(g);
		}
		blast.paint(g);
	}  //重写paint方法
	
	class MyKeyListener extends KeyAdapter{//键盘监听
		
		boolean flag_A = false;
		boolean flag_D = false;
		boolean flag_W = false;
		boolean flag_S = false;
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_A:	flag_A = true;break;
			case KeyEvent.VK_D:	flag_D = true;break;
			case KeyEvent.VK_W:	flag_W = true;break;
			case KeyEvent.VK_S:	flag_S = true;break;
			default:break;
			}
			setMainTankDirection();
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_A:	flag_A = false;break;
			case KeyEvent.VK_D:	flag_D = false;break;
			case KeyEvent.VK_W:	flag_W = false;break;
			case KeyEvent.VK_S:	flag_S = false;break;
			case KeyEvent.VK_J:	{
				for(int j = 0; j < player.size(); j++) {
					player.get(j).fire();break;
				}
				break;
			}
			default:break;
			}
			setMainTankDirection();
		}  
		public void setMainTankDirection() {
			if(!flag_A && !flag_D && !flag_W && !flag_S)  {
				for(int j = 0; j < player.size(); j++) {
					player.get(j).setMoving(false);
				}
			}
			else {
				if(flag_A) {
					for(int j = 0; j < player.size(); j++) {
						player.get(j).setDir(Direction.LEFT);
					}
				}
				if(flag_D) {
					for(int j = 0; j < player.size(); j++) {
						player.get(j).setDir(Direction.RIGHT);
					}
				}
				if(flag_W) {
					for(int j = 0; j < player.size(); j++) {
						player.get(j).setDir(Direction.UP);
					}
				}
				if(flag_S) {
					for(int j = 0; j < player.size(); j++) {
						player.get(j).setDir(Direction.DOWN);
					}
				}
				for(int j = 0; j < player.size(); j++) {
					player.get(j).setMoving(true);
				}
			}
		}
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	public int getStep_to_win() {
		return step_to_win;
	}

	public void setStep_to_win(int step_to_win) {
		this.step_to_win = step_to_win;
	}
	
}