package TankWar;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Home {
	private int x, y;
	public static int width = 40;
	public static int height = 30;
	private boolean living = false;
	private TankFrame tf;
	public Home(int x, int y, boolean living, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.living = living;
		this.tf = tf;
	}
	public void paint(Graphics g) {
		if(!living)   {
			tf.blast.setLiving(true);
			tf.blast.setX(this.x);
			tf.blast.setY(this.y);
			return;
		}
		g.drawImage(ResourceMgr.home, x, y + 10,  width, height, null);
	}
	public void collide(Bullet bullet) {
		Rectangle home_rec = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle bullet_rec = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
		if( home_rec.intersects(bullet_rec) )  {  //使用intersects方法实现进行碰撞测试
			bullet.die();
			this.setLiving(false);
		}
	} 
	public void collide(Tank tank) {
		Rectangle home_rec = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle tank_rec;
		Direction dir = tank.getDir();
		switch(dir) {
		case LEFT: tank_rec = new Rectangle(tank.getX() - 4, tank.getY(), tank.getWidth(), tank.getHeight());//加多一点空隙，防止坦克贴住墙
			break;
		case RIGHT: tank_rec = new Rectangle(tank.getX() + 4, tank.getY(), tank.getWidth(), tank.getHeight());
		break;
		case UP: tank_rec = new Rectangle(tank.getX(), tank.getY() - 4, tank.getWidth(), tank.getHeight());
		break;
		case DOWN: tank_rec = new Rectangle(tank.getX(), tank.getY() + 4, tank.getWidth(), tank.getHeight());
		break;
		default:tank_rec = new Rectangle(tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
			break;
		}
		
		if( home_rec.intersects(tank_rec) )  {  //使用intersects方法实现进行碰撞测试
			tank.setMoving(false);
		}
		
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
		Home.width = width;
	}
	public static int getHeight() {
		return height;
	}
	public static void setHeight(int height) {
		Home.height = height;
	}
	public boolean isLiving() {
		return living;
	}
	public void setLiving(boolean living) {
		this.living = living;
	}
	
}
