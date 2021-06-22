package TankWar;


public class Double_Game implements Runnable{
	public void run() {
		try {
			Method.double_game();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
