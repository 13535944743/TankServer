package TankWar;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall {
	private int x, y;
	private static int width = 45;
	private static int height = 45;
	private boolean live = true;
	private static TankFrame tf;

	public Wall(int x, int y,TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	
	public void paint(Graphics g) {
		if(live == false) {
			tf.walls.remove(this);
			tf.blast.setLiving(true);
			tf.blast.setX(this.x);
			tf.blast.setY(this.y);
			tf.blast.paint(g);
		}
		g.drawImage(ResourceMgr.wall, x, y, width, height, null);
	}
	public void collide(Bullet bullet) {
		Rectangle wall_rec = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle bullet_rec = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
		if( wall_rec.intersects(bullet_rec) )  {  //使用intersects方法实现进行碰撞测试
			bullet.die();
			this.die();
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
		
		if( wall_rec.intersects(tank_rec) )  {  //使用intersects方法实现进行碰撞测试
			tank.setMoving(false);
		}
		
	}
	public static void buildwall(TankFrame tf) {
		//第一列
		for(int i = 0; i < 2; i++)
			tf.walls.add(new Wall(50 , 75 + i * 45, tf));
    	for(int i = 0; i < 3; i++)
    		tf.walls.add(new Wall(50 + i * 45, 305 , tf));
		for(int i = 0; i < 4; i++)
			tf.walls.add(new Wall(50, 395 + 45 * i, tf));
		
		//第二列
		for(int i = 0; i < 2; i++)
			tf.walls.add(new Wall(140, 165 + 45 * i, tf));
		for(int i = 0; i < 2; i++)
			tf.walls.add(new Wall(140 , 440 + i * 45, tf));
		tf.walls.add(new Wall(140 , 575, tf));
		
		tf.walls.add(new Wall(185, 165, tf));
		
		tf.walls.add(new Wall(230, 260 , tf));
		for(int i = 0; i < 3; i++)
			tf.walls.add(new Wall(230 , 350 + i * 45, tf));
		for(int i = 0; i < 2; i++)
			tf.walls.add(new Wall(230 , 530 + i * 45, tf));
		
		tf.walls.add(new Wall(275 , 121, tf));
		tf.walls.add(new Wall(275 , 440, tf));
		tf.walls.add(new Wall(275 , 530, tf));
		
		for(int i = 0; i < 2; i++)
			tf.walls.add(new Wall(320 , 76 + 45 * i, tf));
		for(int i = 0; i < 3; i++)
			tf.walls.add(new Wall(320 , 350 + 45 * i, tf));
		for(int i = 0; i < 2; i++)
			tf.walls.add(new Wall(320 , 530 + 45 * i, tf));
		
		for(int i = 0; i < 2; i++)
			tf.walls.add(new Wall(410 , 76 + 45 * i, tf));
		tf.walls.add(new Wall(410 , 211, tf));
		tf.walls.add(new Wall(410 , 350, tf));
		tf.walls.add(new Wall(410 , 440, tf));
		for(int i = 0; i < 2; i++)
			tf.walls.add(new Wall(410 , 530 + 45 * i, tf));
		
		tf.walls.add(new Wall(455 , 575, tf));
		
		for(int i = 0; i < 2; i++)
			tf.walls.add(new Wall(500 , 76 + 45 * i, tf));
		tf.walls.add(new Wall(500 , 211, tf));
		for(int i = 0; i < 4; i++)
			tf.walls.add(new Wall(500 , 305 + 45 * i, tf));
		for(int i = 0; i < 2; i++)
			tf.walls.add(new Wall(500 , 530 + 45 * i, tf));
	}
}
