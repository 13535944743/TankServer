package TankWar;

import java.awt.Frame;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Method {
	public static TankFrame tf;
	public static boolean updateEnemies(TankFrame tf) {
		if( tf.enemies.size() >= 5)  return false;
		Random random = new Random();
		int ran = random.nextInt(5) + 1; //敌方随机五个重生点
		int x = 50;
		switch(ran) {
		case 1:x += 45;
		break;
		case 2:x += 495;
		break;
		case 3:x += 225;
		break;
		case 4:x += 315;
		break;
		case 5:x += 405;
		break;
		default:x += 0;
		break;
		}
		
		ran = random.nextInt(4) + 1;//重生后的方向随机
		switch(ran) {
		case 1:tf.enemies.add(new Tank(x, 31, Direction.LEFT,Group.Enemy, tf));
		break;
		case 2:tf.enemies.add(new Tank(x, 31, Direction.RIGHT,Group.Enemy, tf));
		break;
		case 3:tf.enemies.add(new Tank(x, 31, Direction.UP,Group.Enemy, tf));
		break;
		case 4:tf.enemies.add(new Tank(x, 31, Direction.DOWN,Group.Enemy, tf));
		break;
		default:tf.enemies.add(new Tank(x, 31, Direction.LEFT,Group.Enemy, tf));
		break;
		}
		return true;
	}
	
	public static boolean updatePlayer(TankFrame tf) throws InterruptedException {
		if(ServerMain.model == 0) {
			if( tf.player1.size() == 1)  return false;
			tf.player1.add(new Tank(187,575,Direction.UP, Group.Player, tf));
		}
		else if(ServerMain.model == 1) {
			if( tf.player2.size() == 1 && tf.player1.size() == 1)  return false;
			else if(tf.player2.size() == 0) {
				
				Tank temp = new Tank(367, 575,Direction.UP, Group.Player, tf);
				temp.setId(1);
				tf.player2.add(temp);
				tf.data = "q@367@575";  
				Thread.sleep(tf.sec);
			}
			else if(tf.player1.size() == 0) {
				tf.player1.add(new Tank(187,575,Direction.UP, Group.Player, tf));
				tf.data = "p@187@575";  
				Thread.sleep(tf.sec);
			}
		}
		return true;
	}
	public static void single_game() throws InterruptedException {
		tf = new TankFrame();	
		tf.setTitle("坦克大战");
		int i;
		Random random = new Random();
		
		Wall.buildwall(tf);
		SteelWall.buildSteelWall(tf);
		
		tf.player1.add(new Tank(187,575,Direction.UP, Group.Player, tf));
		tf.enemies.add(new Tank(95, 31,Direction.UP,Group.Enemy, tf));
		for(i = 1; i < 5; i++) {
			tf.enemies.add(new Tank(185 + 90 * i, 31,Direction.UP,Group.Enemy, tf));//敌方坦克初始位置设置为顶行，方向向上，遇到障碍，随机四个方向
		}
		
		while(true) {
			Thread.sleep(50);	
	/**************每隔五秒自动变向，防止过于有规律		
			for(i = 0; i < tf.enemies.size(); i++) {
					
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						public void run() {
							tf.enemies.get(1).setMoving(false);
							timer.cancel();
						}
					}, 5000, 5000);
			}
	************************/
			if( tf.enemies.size() < 5 ) {
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						if(updateEnemies(tf) == false) {
							timer.cancel();
						}
					}
				}, 1000, 100);
			}
			if( tf.player1.size() == 0 && tf.getChance() != 0) {
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						try {
							if(updatePlayer(tf) == false) {
								timer.cancel();
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, 1000, 100);
			}
			/***************************************游戏失败**********/
			if(tf.getChance() <= 0 || (tf.home.isLiving() == false && tf.blast.isLiving() == false)) {
				tf.setVisible(false);
				tf.dispose();
				LostFrame f = new LostFrame("坦克大战");
//				tf.home.setLiving(true);
//				tf.setChance(3);
				return;
			}
			/***********************************************/
			
			/***************************************游戏胜利**********/
			if(tf.getStep_to_win() <= 0) {
				tf.setVisible(false);
				tf.dispose();
				
				WinFrame wf = new WinFrame("坦克大战");
				return;
			}
			/***********************************************/
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size(); j++) {
					tf.bullets.get(i).collide(tf.enemies.get(j),tf.enemies.get(j).getGroup());
				}
			}//子弹和敌方坦克碰撞检测
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.bullets.size() && j != i; j++) {
					tf.bullets.get(i).collide(tf.bullets.get(j),tf.bullets.get(j).getGroup());
				}
			}//子弹和敌方子弹碰撞检测
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.bullets.get(i).collide(tf.player1.get(j),tf.player1.get(j).getGroup());
				}
			}//子弹和我方坦克碰撞检测
			for(i = 0; i < tf.enemies.size(); i++ ) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.enemies.get(i).collide(tf.player1.get(j),tf.player1.get(j).getGroup());
				}
			}//坦克碰撞检测
			for(i = 0; i < tf.enemies.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size() && i != j; j++)
				tf.enemies.get(i).collide(tf.enemies.get(j),tf.enemies.get(j).getGroup());
			}//敌方坦克碰撞检测,检测到后坦克随机转向直到不相碰
			for(i = 0; i < tf.walls.size(); i++ ) {
				for(int j = 0; j < tf.bullets.size(); j++) {
					tf.walls.get(i).collide(tf.bullets.get(j));
				}
			}//普通墙和子弹碰撞检测
			for(i = 0; i < tf.steelwalls.size(); i++ ) {
				for(int j = 0; j < tf.bullets.size(); j++) {
					tf.steelwalls.get(i).collide(tf.bullets.get(j));
				}
			}//铁墙和子弹碰撞检测
			for(i = 0; i < tf.steelwalls.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size(); j++) {
					tf.steelwalls.get(i).collide(tf.enemies.get(j));
				}
			}//敌方坦克和铁墙
			for(i = 0; i < tf.walls.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size(); j++) {
					tf.walls.get(i).collide(tf.enemies.get(j));
				}
			}//敌方坦克和普通墙
			for(i = 0; i < tf.walls.size(); i++) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.walls.get(i).collide(tf.player1.get(j));
				}
			}////我方坦克和普通墙
			for(i = 0; i < tf.steelwalls.size(); i++) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.steelwalls.get(i).collide(tf.player1.get(j));
				}
			}////我方坦克和铁墙
			for(i = 0; i < tf.bullets.size(); i++ ) {
					tf.home.collide(tf.bullets.get(i));
			}
			for(int j = 0; j < tf.enemies.size(); j++) {
				tf.home.collide(tf.enemies.get(j));
			}
			for(int j = 0; j < tf.player1.size(); j++) {
				tf.home.collide(tf.player1.get(j));
			}
			/*for(int j = 0; j < tf.enemies.size(); j++) {
				tf.myTank.collide(tf.enemies.get(j), tf);
			}  //智能坦克*/
			
			tf.repaint();
		}
	}
	public static void double_game() throws InterruptedException {
		ServerMain.game_over = false;    //每次开始游戏，游戏重置为没有失败
		tf = new TankFrame();	
		System.out.println("TF");
		tf.setTitle("坦克大战服务端");
		tf.setChance(10086);
		int i;
		Random random = new Random();
		
		Wall.buildwall(tf);
		SteelWall.buildSteelWall(tf);
		
		tf.player1.add(new Tank(187,575,Direction.UP, Group.Player, tf));
		tf.data = "p@187@575";  
		if(ServerMain.model == 1)  Thread.sleep(tf.sec);
		Tank temp = new Tank(367,575,Direction.UP, Group.Player, tf);
		temp.setId(1);
		tf.player2.add(temp);
		tf.data = "q@367@575";  
		if(ServerMain.model == 1)  Thread.sleep(tf.sec);
		Tank ene;
		ene = new Tank(95, 31,Direction.UP,Group.Enemy, tf);
		ene.setIndex(tf.EnemyId++);
		tf.enemies.add(ene);
		tf.data = "e@95@31@" + + (tf.EnemyId - 1);
		if(ServerMain.model == 1)  Thread.sleep(tf.sec);
		for(i = 1; i < 3; i++) {
			ene = new Tank(185 + 90 * i, 31,Direction.UP,Group.Enemy, tf);//敌方坦克初始位置设置为顶行，方向向上，遇到障碍，随机四个方向
			ene.setIndex(tf.EnemyId++);
			tf.enemies.add(ene);
			switch(i) {
			case 1:
				tf.data = "e@275@31@" + (tf.EnemyId - 1);
				break;
			case 2:
				tf.data = "e@365@31@" + (tf.EnemyId - 1);
				break;
//			case 3:
//				tf.data = "e@455@31@" + (tf.EnemyId - 1);
//				break;
//			case 4:
//				tf.data = "e@545@31@ + (tf.EnemyId - 1)";
//				break;
			}
			if(ServerMain.model == 1)  Thread.sleep(tf.sec);
		}
		
		while(true) {
			Thread.sleep(50);	
			if( tf.enemies.size() < 5 ) {
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						if(updateEnemies(tf) == false) {
							timer.cancel();
						}
					}
				}, 1000, 100);
			}
				
			if( (tf.player1.size() == 0 || tf.player2.size() == 0 ) && tf.getChance() != 0) {
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						try {
							if(updatePlayer(tf) == false) {
								timer.cancel();
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, 1000, 100);
			}
			if( tf.player2.size() == 0 && tf.getChance() != 0) {
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						try {
							if(updatePlayer(tf) == false) {
								timer.cancel();
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, 1000, 100);
			}
			/***************************************游戏失败**********/
			if(tf.getChance() <= 0 || (tf.home.isLiving() == false && tf.blast.isLiving() == false)) {
				tf.setVisible(false);
				tf.dispose();
				ServerMain.game_over = true;
				LostFrame f = new LostFrame("坦克大战");
//				tf.home.setLiving(true);
//				tf.setChance(3);
				return;
			}
			/***********************************************/
			
			/***************************************游戏胜利**********/
			if(tf.getStep_to_win() <= 0) {
				tf.setVisible(false);
				tf.dispose();
				
				WinFrame wf = new WinFrame("坦克大战");
				return;
			}
			/***********************************************/
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size(); j++) {
					tf.bullets.get(i).collide(tf.enemies.get(j),tf.enemies.get(j).getGroup());
				}
			}//子弹和敌方坦克碰撞检测
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.bullets.size() && j != i; j++) {
					tf.bullets.get(i).collide(tf.bullets.get(j),tf.bullets.get(j).getGroup());
				}
			}//子弹和敌方子弹碰撞检测
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.bullets.get(i).collide(tf.player1.get(j),tf.player1.get(j).getGroup());
				}
			}//子弹和我方坦克碰撞检测
			for(i = 0; i < tf.enemies.size(); i++ ) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.enemies.get(i).collide(tf.player1.get(j),tf.player1.get(j).getGroup());
				}
			}//坦克碰撞检测
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.player2.size(); j++) {
					tf.bullets.get(i).collide(tf.player2.get(j),tf.player2.get(j).getGroup());
				}
			}//子弹和我方坦克碰撞检测
			for(i = 0; i < tf.enemies.size(); i++ ) {
				for(int j = 0; j < tf.player2.size(); j++) {
					tf.enemies.get(i).collide(tf.player2.get(j),tf.player2.get(j).getGroup());
				}
			}//坦克碰撞检测
			for(i = 0; i < tf.enemies.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size() && i != j; j++)
				tf.enemies.get(i).collide(tf.enemies.get(j),tf.enemies.get(j).getGroup());
			}//敌方坦克碰撞检测,检测到后坦克随机转向直到不相碰
			for(i = 0; i < tf.walls.size(); i++ ) {
				for(int j = 0; j < tf.bullets.size(); j++) {
					tf.walls.get(i).collide(tf.bullets.get(j));
				}
			}//普通墙和子弹碰撞检测
			for(i = 0; i < tf.steelwalls.size(); i++ ) {
				for(int j = 0; j < tf.bullets.size(); j++) {
					tf.steelwalls.get(i).collide(tf.bullets.get(j));
				}
			}//铁墙和子弹碰撞检测
			for(i = 0; i < tf.steelwalls.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size(); j++) {
					tf.steelwalls.get(i).collide(tf.enemies.get(j));
				}
			}//敌方坦克和铁墙
			for(i = 0; i < tf.walls.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size(); j++) {
					tf.walls.get(i).collide(tf.enemies.get(j));
				}
			}//敌方坦克和普通墙
			for(i = 0; i < tf.walls.size(); i++) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.walls.get(i).collide(tf.player1.get(j));
				}
			}////我方坦克和普通墙
			for(i = 0; i < tf.steelwalls.size(); i++) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.steelwalls.get(i).collide(tf.player1.get(j));
				}
			}////我方坦克和铁墙
			for(i = 0; i < tf.walls.size(); i++) {
				for(int j = 0; j < tf.player2.size(); j++) {
					tf.walls.get(i).collide(tf.player2.get(j));
				}
			}////我方坦克和普通墙
			for(i = 0; i < tf.steelwalls.size(); i++) {
				for(int j = 0; j < tf.player2.size(); j++) {
					tf.steelwalls.get(i).collide(tf.player2.get(j));
				}
			}////我方坦克和铁墙
			for(i = 0; i < tf.bullets.size(); i++ ) {
					tf.home.collide(tf.bullets.get(i));
			}
			for(int j = 0; j < tf.enemies.size(); j++) {
				tf.home.collide(tf.enemies.get(j));
			}
			for(int j = 0; j < tf.player1.size(); j++) {
				tf.home.collide(tf.player1.get(j));
			}
			for(int j = 0; j < tf.player2.size(); j++) {
				tf.home.collide(tf.player2.get(j));
			}
			/*for(int j = 0; j < tf.enemies.size(); j++) {
				tf.myTank.collide(tf.enemies.get(j), tf);
			}  //智能坦克*/
			
			tf.repaint();
		}
	}
}
