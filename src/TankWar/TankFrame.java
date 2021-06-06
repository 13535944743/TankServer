package TankWar;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.BorderLayout;
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


class TankFrame extends Frame{	
	
	List<Bullet> bullets = new ArrayList<>();//存放子弹，实现发射多颗子弹
	List<Tank> player1 = new ArrayList<>();
	List<Tank> player2 = new ArrayList<>();
	List<Tank> enemies = new ArrayList<>();
	List<Wall> walls = new ArrayList<>();
	List<SteelWall> steelwalls = new ArrayList<>();
	Home home = new Home(277, 575,true, this);
	Blast blast = new Blast(100, 100, this);
	int chance = 3;
	int step_to_win = 40;
	public final int Window_Width = 800, Window_Height = 625;
	int flag = 0; 
	String data = "";
	public static int sec = 50;
	public static int EnemyId = 1;
	public static boolean pressed = false;
	public static boolean pressed_fire = false;
	
	public TankFrame() {
		setSize(Window_Width,Window_Height);
		setResizable(false);
//		setTitle("坦克大战");
		setBackground(Color.BLACK);
		setLayout(null);
		setVisible(true);
		this.addKeyListener( new MyKeyListener());
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
	}
	
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(Window_Width, Window_Height);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, Window_Width, Window_Height);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}  //双缓冲，处理闪烁问题，相当于在其他地方画好后，再直接用
	
	public void paint(Graphics g) {
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
		for(int i = 0; i < player1.size(); i++) {
			try {
				player1.get(i).paint(g);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i = 0; i < player2.size(); i++) {
			try {
				player2.get(i).paint(g);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		for(int i = 0; i < enemies.size(); i++) {
			try {
				enemies.get(i).paint(g);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			try {
				setMainTankDirection();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
				for(int j = 0; j < player1.size(); j++) {
					try {
						player1.get(j).fire();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}break;
				}
				break;
			}
			default:break;
			}
			try {
				setMainTankDirection();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}  
		public void setMainTankDirection() throws InterruptedException {
			if(!flag_A && !flag_D && !flag_W && !flag_S)  {
				for(int j = 0; j < player1.size(); j++) {
					player1.get(j).setMoving(false);
					data = "stop@1";
					if(ServerMain.model == 1)  Thread.sleep(sec);
					pressed = false;
				}
			}
			else {
				Direction dir = Direction.LEFT;
				if(flag_A) {
					for(int j = 0; j < player1.size(); j++) {
						player1.get(j).setDir(Direction.LEFT);
						dir = Direction.LEFT;
					}
				}
				if(flag_D) {
					for(int j = 0; j < player1.size(); j++) {
						player1.get(j).setDir(Direction.RIGHT);
						dir = Direction.RIGHT;
					}
				}
				if(flag_W) {
					for(int j = 0; j < player1.size(); j++) {
						player1.get(j).setDir(Direction.UP);
						dir = Direction.UP;
					}
				}
				if(flag_S) {
					for(int j = 0; j < player1.size(); j++) {
						player1.get(j).setDir(Direction.DOWN);
						dir = Direction.DOWN;
					}
				}
				if(!pressed) {
					data = "playerchange@" + dir;
					System.out.println(data);
					if(ServerMain.model == 1)  Thread.sleep(sec);
				}
				for(int j = 0; j < player1.size(); j++) {
					player1.get(j).setMoving(true);
				}
				pressed = true;
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