package TankWar;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Tank {
	private int x, y;
	private static int width = 40;
	private static int height = 40;
	private Direction dir = Direction.LEFT;
	private Random random = new Random();
	private Bullet bullet;
	private boolean havedfired = false; //记录有没有发射过子弹
	
	private boolean moving = false;
	private int speed = 6;  //6
	private TankFrame tf;
	private boolean live = true;
	private Group group = Group.Enemy;
	private int id = 0;//辨别客户端服务端坦克,0 为服务器端，1 为客户端
	public int index = 0;  //编号，确定变换方向时是哪辆坦克
	public Tank(int x, int y, Direction dir,Group group, TankFrame tf) {
		super();
		this.setX(x);
		this.setY(y);
		this.group = group;
		this.dir = dir;
		this.tf = tf;
		if(ServerMain.model == 1)  this.speed = 4;
		if(group == Group.Enemy)  moving = true;
	}
	
	public void paint(Graphics g) throws InterruptedException, IllegalArgumentException{
		if(!live)  {
			tf.enemies.remove(this);
			tf.player1.remove(this);   //坦克死掉移除
			tf.player2.remove(this);   //坦克死掉移除
			tf.blast.setLiving(true);
			tf.blast.setX(this.x);
			tf.blast.setY(this.y);
			tf.blast.paint(g);
			return;
		}
		if(group == Group.Player && id == 0) {
			switch(dir) {
			case LEFT:	g.drawImage(ResourceMgr.tankL, getX(), getY(), width, height, null);
             break;
			case RIGHT:	g.drawImage(ResourceMgr.tankR, getX(), getY(), width, height, null);
		     break;
			case UP:	g.drawImage(ResourceMgr.tankU, getX(), getY(), width, height, null);
		     break;
			case DOWN:	g.drawImage(ResourceMgr.tankD, getX(), getY(), width, height, null);
		     break;
			default:break;
			}
		}
		else if(group == Group.Player && id == 1) {
			switch(dir) {
			case LEFT:	g.drawImage(ResourceMgr.tank1L, getX(), getY(), width, height, null);
             break;
			case RIGHT:	g.drawImage(ResourceMgr.tank1R, getX(), getY(), width, height, null);
		     break;
			case UP:	g.drawImage(ResourceMgr.tank1U, getX(), getY(), width, height, null);
		     break;
			case DOWN:	g.drawImage(ResourceMgr.tank1D, getX(), getY(), width, height, null);
		     break;
			default:break;
			}
		}
		else if(group == Group.Enemy) {
			switch(dir) {
			case LEFT:	g.drawImage(ResourceMgr.enemy1L, getX(), getY(), width, height, null);
             break;
			case RIGHT:	g.drawImage(ResourceMgr.enemy1R, getX(), getY(), width, height, null);
		     break;
			case UP:	g.drawImage(ResourceMgr.enemy1U, getX(), getY(), width, height, null);
		     break;
			case DOWN:	g.drawImage(ResourceMgr.enemy1D, getX(), getY(), width, height, null);
		     break;
			default:break;
			}
		}
		if(dir == Direction.LEFT && getX() - speed < 5)    moving = false;//再继续向左走会超出窗口
		else if(dir == Direction.RIGHT && getX() + speed > tf.Window_Width - 40)    moving = false;
		else if(dir == Direction.UP && getY() - speed < 30)    moving = false;
		else if(dir == Direction.DOWN && getY() + speed > tf.Window_Height - 47)    moving = false;
		if(!moving) {
			if(group == Group.Enemy) {
/********************************************增加Timer每隔一段时间检测Moving是否为false,为false则要随机换方向，每隔一段时间，避免转换过快，坦克卡在墙壁里继续随机***************************************************************************************/
				if( moving == false ) {
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						public void run() {
							int[] saizi = {0, 1, 2, 3, 4};         //实现每个方向只会随机出一次
							int index_sizi = 4;
							while( moving == false) {
								try {
									int ran = random.nextInt(4) + 1;
									switch(ran) {
									case 1:{
										dir = Direction.LEFT;
									}
									break;
									case 2:{
										dir = Direction.RIGHT;
									}
									break;
									case 3:{
										dir = Direction.UP;
									}
									break;
									case 4:{
										dir = Direction.DOWN;
									}
									break;
									default:
									break;
									}
//									if(index_sizi <= 0)  index_sizi = 1;
//									int ran = random.nextInt(index_sizi) + 1;
//									switch(saizi[ran]) {
//									case 1:{
//										dir = Direction.LEFT;
//										int temp = saizi[index_sizi];
//										saizi[index_sizi] = saizi[1];
//										saizi[1] = temp;
//									}
//									break;
//									case 2:{
//										dir = Direction.RIGHT;
//										int temp = saizi[index_sizi];
//										saizi[index_sizi] = saizi[2];
//										saizi[1] = temp;
//									}
//									break;
//									case 3:{
//										dir = Direction.UP;
//										int temp = saizi[index_sizi];
//										saizi[index_sizi] = saizi[3];
//										saizi[1] = temp;
//									}
//									break;
//									case 4:{
//										dir = Direction.DOWN;
//										int temp = saizi[index_sizi];
//										saizi[index_sizi] = saizi[4];
//										saizi[1] = temp;
//									}
//									break;
//									default:
//									break;
//									}
								} catch(ArrayIndexOutOfBoundsException e) {
									System.out.println(e);
								}
								
								index_sizi--;
								if(dir == Direction.LEFT && getX() - speed < 5)    moving = false;//再继续向左走会超出窗口
								else if(dir == Direction.RIGHT && getX() + speed > tf.Window_Width - 40)    moving = false;
								else if(dir == Direction.UP && getY() - speed < 30)    moving = false;
								else if(dir == Direction.DOWN && getY() + speed > tf.Window_Height - 30)    moving = false;
								else
								{
									if(ServerMain.model == 1) {
										switch(dir) {
										case UP:
											tf.data = "d@" + x + "@" + y + "@1@" + index;    //把x和y坐标重新发送过去
											break;
										case RIGHT:
											tf.data = "d@" + x + "@" + y + "@2@" + index;
											break;
										case DOWN:
											tf.data = "d@" + x + "@" + y + "@3@" + index;
											break;
										case LEFT:
											tf.data = "d@" + x + "@" + y + "@4@" + index;
											break;
										}
//										System.out.println(tf.data);
										try {
											Thread.sleep(tf.sec);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									moving = true;
								}
							}
						}
					}, 0, 1000);
				}
//				while(moving == false) {
//					int ran = random.nextInt(4) + 1;
//					switch(ran) {
//					case 1:{
//						dir = Direction.LEFT;
//					}
//						break;
//					case 2:{
//						dir = Direction.RIGHT;
//					}
//						break;
//					case 3:{
//						dir = Direction.UP;
//					}
//						break;
//					case 4:{
//						dir = Direction.DOWN;
//					}
//						break;
//					default:
//						break;
//					}
//					
//					if(dir == Direction.LEFT && getX() - speed < 5)    moving = false;//再继续向左走会超出窗口
//					else if(dir == Direction.RIGHT && getX() + speed > tf.Window_Width - 40)    moving = false;
//					else if(dir == Direction.UP && getY() - speed < 30)    moving = false;
//					else if(dir == Direction.DOWN && getY() + speed > tf.Window_Height - 30)    moving = false;
//					else moving = true;
//				}
			}
		}
		move();
	} 
	public void move() throws InterruptedException {
		if(!moving) return;
		switch(dir) {
		case LEFT:	setX(getX() - speed);break;
		case RIGHT:	setX(getX() + speed);break;
		case UP:	setY(getY() - speed);break;
		case DOWN:	setY(getY() + speed);break;
		default:break;
		}
		if( random.nextInt(100) > 90  && group == Group.Enemy)  this.fire();
	}
	public void fire() throws InterruptedException { 
		if(havedfired == true)   {
			if(bullet.isLive() == false) {
				havedfired = false;
			}
			else return;
		}
		int bullet_x = this.getX() + Tank.width/2 - Bullet.width/2;
		int bullet_y = this.getY() + Tank.height/2 - Bullet.height/2;
		
		switch(this.dir) {
		case LEFT:	bullet_x -= Tank.width/2;
		        break;
		case RIGHT:	bullet_x += Tank.width/2;
                break;
		case UP:	bullet_y -= Tank.height/2;
                break;
		case DOWN:	bullet_y += Tank.height/2;
                break;
		default:break;
		}
		if(group == Group.Player)     {
			bullet = new Bullet(bullet_x, bullet_y, this.dir,group.Player,  this.tf);
			int d = 0;
			switch(this.dir) {
			case UP:
				d = 1;
				break;
			case RIGHT:
				d = 2;
				break;
			case DOWN:
				d = 3;
				break;
			case LEFT:
				d = 4;
				break;
			}
			tf.data = "b@" + bullet_x + "@" + bullet_y + "@" + d + "@1";    //最后的1代表player
			if(ServerMain.model == 1)  Thread.sleep(tf.sec);
			tf.bullets.add(bullet);  //把坦克的位置和方向传给子弹作为子弹的属性
		}
		else if(group == Group.Enemy)  {
			bullet = new Bullet(bullet_x, bullet_y, this.dir,group.Enemy,  this.tf);
			int d = 0;
			switch(this.dir) {
			case UP:
				d = 1;
				break;
			case RIGHT:
				d = 2;
				break;
			case DOWN:
				d = 3;
				break;
			case LEFT:
				d = 4;
				break;
			}
			tf.data = "b@" + bullet_x + "@" + bullet_y + "@" + d + "@0@" + tf.chance + "@" + tf.step_to_win;
			if(ServerMain.model == 1)  Thread.sleep(tf.sec);
			tf.bullets.add(bullet);
		}
		
		havedfired = true;
	}
	public void collide(Tank tank, Group group) {
		if(group == this.group)  { 
//	/****************************************实现敌方坦克相撞往相反方向换方向
//			Rectangle enemy1_rec = new Rectangle(this.x, this.y, this.width, this.height);
//			Rectangle enemy2_rec = new Rectangle(tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
//			if( enemy1_rec.intersects(enemy2_rec) )  {  //使用intersects方法实现进行碰撞测试
//				switch(this.dir) {
//				case LEFT: this.setDir(Direction.RIGHT);
//					break;
//				case RIGHT:this.setDir(Direction.LEFT);
//					break;
//				case UP: this.setDir(Direction.DOWN);
//					break;
//				case DOWN:this.setDir(Direction.UP);
//					break;
//				default:break;
//				}
//				
//				switch(tank.dir) {
//				case LEFT: tank.setDir(Direction.RIGHT);
//					break;
//				case RIGHT:tank.setDir(Direction.LEFT);
//					break;
//				case UP: tank.setDir(Direction.DOWN);
//					break;
//				case DOWN:tank.setDir(Direction.UP);
//					break;
//				default:break;
//				}
//			}
//              ******************/
			return;
		}
		Rectangle player_rec = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle enemy_rec = new Rectangle(tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
		if( player_rec.intersects(enemy_rec) )  {  //使用intersects方法实现进行碰撞测试
			tank.die();
			this.die();
		}
	}
	public void die() {
		live  = false;
		if(group == Group.Enemy)   tf.setStep_to_win(tf.getStep_to_win() - 1);
		else tf.setChance(tf.getChance() - 1);
	}
	/*十字方向上出现我方坦克，立马转方向开炮  智能坦克public void fire_enemy(Tank tank) {
		int bullet_x = tank.getX() + tank.width/2 - Bullet.width/2;
		int bullet_y = tank.getY() + tank.height/2 - Bullet.height/2;
		
		switch(tank.dir) {
		case LEFT:	bullet_x -= tank.width/2;
		        break;
		case RIGHT:	bullet_x += tank.width/2;
                break;
		case UP:	bullet_y -= tank.height/2;
                break;
		case DOWN:	bullet_y += tank.height/2;
                break;
		default:break;
		}
		tf.bullets.add(new Bullet(bullet_x, bullet_y, tank.dir, group.Enemy, tank.tf));  //把坦克的位置和方向传给子弹作为子弹的属性
	}
	public void collide(Tank tank, TankFrame tf) {
		Rectangle tank_rec = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle enimy_rec;
		
		Rectangle enemy_left_rec, eniemy_right_rec, enemy_up_rec, enemy_down_rec;
			enemy_left_rec = new Rectangle(0, tank.getY(), tank.getX(), tank.getHeight());
			eniemy_right_rec = new Rectangle(tank.getX(), tank.getY(), tf.Window_Width, tank.getHeight());
			enemy_up_rec = new Rectangle(tank.getX(), 0, tank.getWidth(), tank.getY());
			enemy_down_rec = new Rectangle(tank.getX(), tank.getY(), tank.getWidth(), tf.Window_Height);
		if(tank.dir == Direction.LEFT) {//通过列举判断我方坦克在不在敌方坦克非正面方向上
			if( tank_rec.intersects(eniemy_right_rec) ) {
				tank.setDir(Direction.RIGHT);
			}
			if( tank_rec.intersects(enemy_up_rec) ) {
				tank.setDir(Direction.UP);
			}
			if( tank_rec.intersects(enemy_down_rec) ) {
				tank.setDir(Direction.DOWN);
			}
		}
		if(tank.dir == Direction.RIGHT) {
			if( tank_rec.intersects(enemy_left_rec) ) {
				tank.setDir(Direction.LEFT);
			}
			if( tank_rec.intersects(enemy_up_rec) ) {
				tank.setDir(Direction.UP);
			}
			if( tank_rec.intersects(enemy_down_rec) ) {
				tank.setDir(Direction.DOWN);
			}
		}
		if(tank.dir == Direction.UP) {
			if( tank_rec.intersects(eniemy_right_rec) ) {
				tank.setDir(Direction.RIGHT);
			}
			if( tank_rec.intersects(enemy_left_rec) ) {
				tank.setDir(Direction.LEFT);
			}
			if( tank_rec.intersects(enemy_down_rec) ) {
				tank.setDir(Direction.DOWN);
			}
		}
		if(tank.dir == Direction.DOWN) {
			if( tank_rec.intersects(eniemy_right_rec) ) {
				tank.setDir(Direction.RIGHT);
			}
			if( tank_rec.intersects(enemy_up_rec) ) {
				tank.setDir(Direction.UP);
			}
			if( tank_rec.intersects(enemy_left_rec) ) {
				tank.setDir(Direction.LEFT);
			}
		}
		
		switch(tank.dir) {
		case LEFT:enimy_rec = enemy_left_rec;
		       break;
		case RIGHT:enimy_rec = eniemy_right_rec;
	          break;
		case UP:enimy_rec = enemy_up_rec;
	       break;
		case DOWN:enimy_rec = enemy_down_rec;
			break;
		default: enimy_rec = new Rectangle(0, 0, 0, 0);
			break;
		}
		 
		if( tank_rec.intersects(enimy_rec) )  {  //使用intersects方法实现进行碰撞测试
			fire_enemy(tank);
		}
	}*/
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public static int getWidth() {
		return width;
	}
	public static void setWidth(int width) {
		Tank.width = width;
	}
	public static int getHeight() {
		return height;
	}
	public static void setHeight(int height) {
		Tank.height = height;
	}
	public Bullet getBullet() {
		return bullet;
	}
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean isMoving() {
		return moving;
	}
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	public Direction getDir() {
		return dir;
	}
	public void setDir(Direction dir) {
		this.dir = dir;
	}
	public int getSpeed() {
		return speed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
