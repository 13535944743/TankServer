package TankWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	private int x, y;
	public static int width = 12;
	public static int height = 12;
	private Direction dir;
	private final int speed = 12;
	private TankFrame tf;
	private boolean live = true;//live用来处理不在游戏窗口里的子弹
	private Group group = Group.Enemy;
	
	public Bullet(int x, int y, Direction dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.group = group;
		this.dir = dir;
		this.tf = tf;
	}
	public void paint(Graphics g) {
		if(!live)  tf.bullets.remove(this); //删除不在窗口里的子弹
		if(group == Group.Player)   g.drawImage(ResourceMgr.bullet, x, y,width,height, null);
		else if(group == Group.Enemy)  g.drawImage(ResourceMgr.enemybullet, x, y,width,height, null);
		move();
	} 
	public void move() {
		switch(dir) {
		case LEFT:	x -= speed;break;
		case RIGHT:	x += speed;break;
		case UP:	y -= speed;break;
		case DOWN:	y += speed;break;
		default:break;
		}
		if(x < 0 || y < 0 || x > tf.Window_Width || y > tf.Window_Height) {
			live = false;
		}
	}
	public void collide(Tank tank, Group group) {
		if(group == this.group)  {
			if(this == tank.getBullet())  return;
			else {
				Rectangle bullet_rec = new Rectangle(this.x, this.y, this.width, this.height);
				Rectangle tank_rec = new Rectangle(tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
				if( bullet_rec.intersects(tank_rec) )  {  //使用intersects方法实现进行碰撞测试
					this.die();
				}//敌方子弹和非发射子弹的坦克相撞，子弹消失，坦克无变化
				return;
			}
		}
		Rectangle bullet_rec = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle tank_rec = new Rectangle(tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
		if( bullet_rec.intersects(tank_rec) )  {  //使用intersects方法实现进行碰撞测试
			tank.die();
			this.die();
		}
	}
	public void die() {
		live = false;
	}
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
	public static int getWidth() {
		return width;
	}
	public static void setWidth(int width) {
		Bullet.width = width;
	}
	public static int getHeight() {
		return height;
	}
	public static void setHeight(int height) {
		Bullet.height = height;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public void collide(Bullet bullet, Group group) {
		if(group == this.group)  return;
		Rectangle bullet_rec = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle bullet_enemy_rec = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
		if( bullet_rec.intersects(bullet_enemy_rec) )  {  //使用intersects方法实现进行碰撞测试
			bullet.die();
			this.die();
		}
		
	}
	
}
