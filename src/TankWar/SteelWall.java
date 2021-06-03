package TankWar;

import java.awt.Graphics;
import java.awt.Rectangle;

public class SteelWall {
	private int x, y;
	private static int width = 45;
	private static int height = 45;
	private boolean live = true;
	private static TankFrame tf;

	public SteelWall(int x, int y,TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	
	public void paint(Graphics g) {
		if(live == false) tf.walls.remove(this);
		if(x < 590)  g.drawImage(ResourceMgr.steelwall, x, y, width, height, null);
		else g.drawImage(ResourceMgr.grey, x, y, 1, height, null);
	}
	public void collide(Bullet bullet) {
		Rectangle wall_rec = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle bullet_rec = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
		if( wall_rec.intersects(bullet_rec) )  {  //使用intersects方法实现进行碰撞测试
			bullet.die();
		}
	}
	private void die() {
		live = false;
	}

	public void collide(Tank tank) {
		Rectangle wall_rec = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle tank_rec;
		Direction dir = tank.getDir();
		switch(dir) {
		case LEFT: tank_rec = new Rectangle(tank.getX() - tank.getSpeed(), tank.getY(), tank.getWidth(), tank.getHeight());//加多一点空隙，防止坦克贴住墙
			break;
		case RIGHT: tank_rec = new Rectangle(tank.getX() + tank.getSpeed(), tank.getY(), tank.getWidth(), tank.getHeight());
		break;
		case UP: tank_rec = new Rectangle(tank.getX(), tank.getY() - tank.getSpeed(), tank.getWidth(), tank.getHeight());
		break;
		case DOWN: tank_rec = new Rectangle(tank.getX(), tank.getY() + tank.getSpeed(), tank.getWidth(), tank.getHeight());
		break;
		default:tank_rec = new Rectangle(tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
			break;
		}
		
		if( wall_rec.intersects(tank_rec) )  {  //使用intersects方法实现进行碰撞测试
			tank.setMoving(false);
		}
	}
	public static void buildSteelWall(TankFrame tf) {
		for(int i = 0; i < 1; i++)
			tf.steelwalls.add(new SteelWall(5, 395 + 45 * i, tf));
		
		for(int i = 0; i < 2; i++)
			tf.steelwalls.add(new SteelWall(140 , 31 + i * 45, tf));
		tf.steelwalls.add(new SteelWall(185 , 76, tf));
		tf.steelwalls.add(new SteelWall(230 , 76, tf));
		tf.steelwalls.add(new SteelWall(230 , 31 , tf));
		
		for(int i = 0; i < 2; i++)
			tf.steelwalls.add(new SteelWall(140 , 350 + i * 45, tf));
		
		tf.steelwalls.add(new SteelWall(275 , 211, tf));
		
		tf.steelwalls.add(new SteelWall(320 , 31, tf));
		tf.steelwalls.add(new SteelWall(320 , 305, tf));
		
//		tf.steelwalls.add(new SteelWall(365 , 260, tf));
		
		tf.steelwalls.add(new SteelWall(410 , 166, tf));
		
		tf.steelwalls.add(new SteelWall(455 , 121, tf));
		tf.steelwalls.add(new SteelWall(455 , 440, tf));
		
		tf.steelwalls.add(new SteelWall(545 , 211, tf));
		
			for(int j = 0; j < 13; j++)
				tf.steelwalls.add(new SteelWall(590, 31 + j * 45, tf));
	}
}
