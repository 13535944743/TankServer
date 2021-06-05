package TankWar;

import java.io.IOException;

public class ReceiveThread extends Thread{
	TankFrame tf;
	public ReceiveThread(TankFrame tf) {
		this.tf = tf;
	}
	public void run() {
		try {
			receiveMessage();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void receiveMessage() throws IOException, InterruptedException {
		String readline;
		while(true) {
			readline = Server.in.readLine();
			String[] buff = readline.split("@");
			if(buff[0].equals("b")) {
				Direction direction = Direction.UP;
				Group group = Group.Player;
				int x = Integer.parseInt(buff[1]);
				int y = Integer.parseInt(buff[2]);
				int d = Integer.parseInt(buff[3]);
				switch(d) {
				case 1:
					direction = Direction.UP;
					break;
				case 2:
					direction = Direction.LEFT;
					break;
				case 3:
					direction = Direction.DOWN;
					break;
				case 4:
					direction = Direction.LEFT;
					break;
				}
				tf.bullets.add(new Bullet(x, y, direction, Group.Player, tf));
			}
			if(buff[0].equals("stop")) {
				for(int j = 0; j < tf.player2.size(); j++) {
					tf.player2.get(j).setMoving(false);
				}
			}
			if(buff[0].equals("playerchange")) {
				for(int j = 0; j < tf.player2.size(); j++) {
					Direction dir = Direction.valueOf(buff[1]);
					tf.player2.get(j).setDir(dir);
					tf.player2.get(j).setMoving(true);
				}
			}
			Thread.sleep(30);
		}
		
	}
}