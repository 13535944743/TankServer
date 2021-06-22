package TankWar;

import java.awt.Frame;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Method {
	public static TankFrame tf;
	public static boolean updateEnemies(TankFrame tf) {
		if( ServerMain.model == 1) {
			if(tf.enemies.size() >= 1)
			return false;
		}
		else {
			if(tf.enemies.size() >= 5)
				return false;
		}
		Random random = new Random();
		int ran = random.nextInt(5) + 1; //�з�������������
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
		
		ran = random.nextInt(4) + 1;//������ķ������
		Direction dir = Direction.UP;
		switch(ran) {
		case 1:dir = Direction.UP;
		break;
		case 2:dir = Direction.RIGHT;
		break;
		case 3:dir = Direction.DOWN;
		break;
		case 4:dir = Direction.LEFT;
		break;
		default:
		break;
		}
		Tank ene;
		ene = new Tank(x, 31,dir,Group.Enemy, tf);
		ene.setIndex(tf.EnemyId++);
		tf.enemies.add(ene);
		tf.data = "e_up@" + x + "@31@" + dir + "@" +  (tf.EnemyId - 1);
		if(ServerMain.model == 1)
			try {
				Thread.sleep(tf.sec);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		tf.setTitle("̹�˴�ս");
		int i;
		Random random = new Random();
		
		Wall.buildwall(tf);
		SteelWall.buildSteelWall(tf);
		
		tf.player1.add(new Tank(187,575,Direction.UP, Group.Player, tf));
		tf.enemies.add(new Tank(95, 31,Direction.UP,Group.Enemy, tf));
		for(i = 1; i < 5; i++) {
			tf.enemies.add(new Tank(185 + 90 * i, 31,Direction.UP,Group.Enemy, tf));//�з�̹�˳�ʼλ������Ϊ���У��������ϣ������ϰ�������ĸ�����
		}
		
		while(true) {
			Thread.sleep(50);	
	/**************ÿ�������Զ����򣬷�ֹ�����й���		
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
			/***************************************��Ϸʧ��**********/
			if(tf.getChance() <= 0 || (tf.home.isLiving() == false && tf.blast.isLiving() == false)) {
				tf.setVisible(false);
				tf.dispose();
				LostFrame f = new LostFrame("̹�˴�ս");
//				tf.home.setLiving(true);
//				tf.setChance(3);
				return;
			}
			/***********************************************/
			
			/***************************************��Ϸʤ��**********/
			if(tf.getStep_to_win() <= 0) {
				tf.setVisible(false);
				tf.dispose();
				
				WinFrame wf = new WinFrame("̹�˴�ս");
				return;
			}
			/***********************************************/
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size(); j++) {
					tf.bullets.get(i).collide(tf.enemies.get(j),tf.enemies.get(j).getGroup());
				}
			}//�ӵ��͵з�̹����ײ���
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.bullets.size() && j != i; j++) {
					tf.bullets.get(i).collide(tf.bullets.get(j),tf.bullets.get(j).getGroup());
				}
			}//�ӵ��͵з��ӵ���ײ���
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.bullets.get(i).collide(tf.player1.get(j),tf.player1.get(j).getGroup());
				}
			}//�ӵ����ҷ�̹����ײ���
			for(i = 0; i < tf.enemies.size(); i++ ) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.enemies.get(i).collide(tf.player1.get(j),tf.player1.get(j).getGroup());
				}
			}//̹����ײ���
			for(i = 0; i < tf.enemies.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size() && i != j; j++)
				tf.enemies.get(i).collide(tf.enemies.get(j),tf.enemies.get(j).getGroup());
			}//�з�̹����ײ���,��⵽��̹�����ת��ֱ��������
			for(i = 0; i < tf.walls.size(); i++ ) {
				for(int j = 0; j < tf.bullets.size(); j++) {
					tf.walls.get(i).collide(tf.bullets.get(j));
				}
			}//��ͨǽ���ӵ���ײ���
			for(i = 0; i < tf.steelwalls.size(); i++ ) {
				for(int j = 0; j < tf.bullets.size(); j++) {
					tf.steelwalls.get(i).collide(tf.bullets.get(j));
				}
			}//��ǽ���ӵ���ײ���
			for(i = 0; i < tf.steelwalls.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size(); j++) {
					tf.steelwalls.get(i).collide(tf.enemies.get(j));
				}
			}//�з�̹�˺���ǽ
			for(i = 0; i < tf.walls.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size(); j++) {
					tf.walls.get(i).collide(tf.enemies.get(j));
				}
			}//�з�̹�˺���ͨǽ
			for(i = 0; i < tf.walls.size(); i++) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.walls.get(i).collide(tf.player1.get(j));
				}
			}////�ҷ�̹�˺���ͨǽ
			for(i = 0; i < tf.steelwalls.size(); i++) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.steelwalls.get(i).collide(tf.player1.get(j));
				}
			}////�ҷ�̹�˺���ǽ
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
			}  //����̹��*/
			
			tf.repaint();
		}
	}
	public static void double_game() throws InterruptedException  {
		tf = new TankFrame();	
		tf.setTitle("̹�˴�ս�����");
		tf.setChance(6);
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
		if(ServerMain.model == 1)
			try {
				Thread.sleep(tf.sec);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		Tank ene = new Tank(95, 31,Direction.UP,Group.Enemy, tf);
		ene.setIndex(tf.EnemyId++);
		tf.enemies.add(ene);
		tf.data = "e@95@31@" + (tf.EnemyId - 1);
		if(ServerMain.model == 1)  Thread.sleep(tf.sec);
//		for(i = 1; i < 3; i++) {
//			ene = new Tank(185 + 90 * i, 31,Direction.UP,Group.Enemy, tf);//�з�̹�˳�ʼλ������Ϊ���У��������ϣ������ϰ�������ĸ�����
//			ene.setIndex(tf.EnemyId++);
//			tf.enemies.add(ene);
//			switch(i) {
//			case 1:
//				tf.data = "e@275@31@" + (tf.EnemyId - 1);
//				break;
//			case 2:
//				tf.data = "e@365@31@" + (tf.EnemyId - 1);
//				break;
//			case 3:
//				tf.data = "e@455@31@" + (tf.EnemyId - 1);
//				break;
//			case 4:
//				tf.data = "e@545@31@ + (tf.EnemyId - 1)";
//				break;
//			}
//			if(ServerMain.model == 1)  Thread.sleep(tf.sec);
//		}
		
		int mytimer = 0, myTimer_updatePlayer = 0, myTimer_updateEnemies = 0;
		while(true) {
			Thread.sleep(61);	
			mytimer += 61;
			if(mytimer / 61 == 100) {
				mytimer = 0;
				for(i = 0; i < tf.enemies.size(); i++ )
				{
						int ran = random.nextInt(4) + 1;
						Direction dir = Direction.UP;
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
						tf.enemies.get(i).setDir(dir);
						if(ServerMain.model == 1) {
							switch(dir) {
							case UP:
								tf.data = "d@" + tf.enemies.get(i).getX() + "@" + tf.enemies.get(i).getY() + "@1@" +tf.enemies.get(i).index;
								break;
							case RIGHT:
								tf.data = "d@" + tf.enemies.get(i).getX() + "@" + tf.enemies.get(i).getY() + "@2@" + tf.enemies.get(i).index;
								break;
							case DOWN:
								tf.data = "d@" + tf.enemies.get(i).getX() + "@" + tf.enemies.get(i).getY() + "@3@" + tf.enemies.get(i).index;
								break;
							case LEFT:
								tf.data = "d@" + tf.enemies.get(i).getX() + "@" + tf.enemies.get(i).getY() + "@4@" + tf.enemies.get(i).index;
								break;
							}
							try {
								Thread.sleep(tf.sec);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
				}
			}
			if( tf.enemies.size() < 3 ) {
//				Timer timer = new Timer();
//				timer.schedule(new TimerTask() {
//					public void run() {
//						if(updateEnemies(tf) == false) {
//							timer.cancel();
//						}
//					}
//				}, 1000, 100);
				myTimer_updateEnemies += 61;
				if(myTimer_updateEnemies / 61 == 16) {
					updateEnemies(tf);
					myTimer_updateEnemies = 0;
				}
			}
				
			if( (tf.player1.size() == 0 || tf.player2.size() == 0 ) && tf.getChance() != 0) {
				myTimer_updatePlayer += 61;
				if(myTimer_updatePlayer / 61 == 16) {
					updatePlayer(tf);
					myTimer_updatePlayer = 0;
				}
//				Timer timer = new Timer();
//				timer.schedule(new TimerTask() {
//					public void run() {
//						try {
//							if(updatePlayer(tf) == false) {
//								timer.cancel();
//							}
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}, 1000, 100);
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
			/***************************************��Ϸʧ��**********/
			if(tf.getChance() <= 0 || (tf.home.isLiving() == false && tf.blast.isLiving() == false)) {
				tf.finish = true;
				Thread.sleep(100);
				tf.setVisible(false);
				tf.dispose();
				LostFrame f = new LostFrame("̹�˴�ս");
//				try {
//					Server.socket.close();
//					Server.server.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
//				tf.home.setLiving(true);
//				tf.setChance(3);
				return;
			}
			/***********************************************/
			
			/***************************************��Ϸʤ��**********/
			if(tf.getStep_to_win() <= 0) {
				tf.finish = true;
				tf.setVisible(false);
				tf.dispose();
				
				WinFrame wf = new WinFrame("̹�˴�ս");
				return;
			}
			/***********************************************/
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size(); j++) {
					tf.bullets.get(i).collide(tf.enemies.get(j),tf.enemies.get(j).getGroup());
				}
			}//�ӵ��͵з�̹����ײ���
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.bullets.size() && j != i; j++) {
					tf.bullets.get(i).collide(tf.bullets.get(j),tf.bullets.get(j).getGroup());
				}
			}//�ӵ��͵з��ӵ���ײ���
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.bullets.get(i).collide(tf.player1.get(j),tf.player1.get(j).getGroup());
				}
			}//�ӵ����ҷ�̹����ײ���
			for(i = 0; i < tf.enemies.size(); i++ ) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.enemies.get(i).collide(tf.player1.get(j),tf.player1.get(j).getGroup());
				}
			}//̹����ײ���
			for(i = 0; i < tf.bullets.size(); i++ ) {
				for(int j = 0; j < tf.player2.size(); j++) {
					tf.bullets.get(i).collide(tf.player2.get(j),tf.player2.get(j).getGroup());
				}
			}//�ӵ����ҷ�̹����ײ���
			for(i = 0; i < tf.enemies.size(); i++ ) {
				for(int j = 0; j < tf.player2.size(); j++) {
					tf.enemies.get(i).collide(tf.player2.get(j),tf.player2.get(j).getGroup());
				}
			}//̹����ײ���
			for(i = 0; i < tf.enemies.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size() && i != j; j++)
				tf.enemies.get(i).collide(tf.enemies.get(j),tf.enemies.get(j).getGroup());
			}//�з�̹����ײ���,��⵽��̹�����ת��ֱ��������
			for(i = 0; i < tf.walls.size(); i++ ) {
				for(int j = 0; j < tf.bullets.size(); j++) {
					tf.walls.get(i).collide(tf.bullets.get(j));
				}
			}//��ͨǽ���ӵ���ײ���
			for(i = 0; i < tf.steelwalls.size(); i++ ) {
				for(int j = 0; j < tf.bullets.size(); j++) {
					tf.steelwalls.get(i).collide(tf.bullets.get(j));
				}
			}//��ǽ���ӵ���ײ���
			for(i = 0; i < tf.steelwalls.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size(); j++) {
					tf.steelwalls.get(i).collide(tf.enemies.get(j));
				}
			}//�з�̹�˺���ǽ
			for(i = 0; i < tf.walls.size(); i++ ) {
				for(int j = 0; j < tf.enemies.size(); j++) {
					tf.walls.get(i).collide(tf.enemies.get(j));
				}
			}//�з�̹�˺���ͨǽ
			for(i = 0; i < tf.walls.size(); i++) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.walls.get(i).collide(tf.player1.get(j));
				}
			}////�ҷ�̹�˺���ͨǽ
			for(i = 0; i < tf.steelwalls.size(); i++) {
				for(int j = 0; j < tf.player1.size(); j++) {
					tf.steelwalls.get(i).collide(tf.player1.get(j));
				}
			}////�ҷ�̹�˺���ǽ
			for(i = 0; i < tf.walls.size(); i++) {
				for(int j = 0; j < tf.player2.size(); j++) {
					tf.walls.get(i).collide(tf.player2.get(j));
				}
			}////�ҷ�̹�˺���ͨǽ
			for(i = 0; i < tf.steelwalls.size(); i++) {
				for(int j = 0; j < tf.player2.size(); j++) {
					tf.steelwalls.get(i).collide(tf.player2.get(j));
				}
			}////�ҷ�̹�˺���ǽ
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
			}  //����̹��*/
			
			tf.repaint();
		}
	}
}
