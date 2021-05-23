package TankWar;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ResourceMgr {  //资源管理
	public static BufferedImage tankL, tankU, tankR, tankD, bullet, wall, steelwall, enemy1L, enemy1R, enemy1U, enemy1D, enemybullet, home, grey,tankwar, win, gameover;
	public static BufferedImage[] blasts = new BufferedImage[8];
	
	static{
		try {
			tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/p1tankL.gif"));
			tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/p1tankU.gif"));
			tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/p1tankR.gif"));
			tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/p1tankD.gif"));
			bullet = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/tankmissile.gif"));
			wall = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/walls.gif"));
			steelwall = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/steels.gif"));
			enemy1L = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/enemy1L.gif"));
			enemy1U = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/enemy1U.gif"));
			enemy1R = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/enemy1R.gif"));
			enemy1D = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/enemy1D.gif"));
			enemybullet = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/enemymissile.gif"));
			home = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/home.gif"));
			grey = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/grey.jpg"));
			tankwar = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/TankWar.png"));
			win = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/win.png"));
			gameover = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/gameover.png"));
			for(int i = 0; i < 8; i++)
				blasts[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("img/blast"+ (i + 1) + ".gif"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
