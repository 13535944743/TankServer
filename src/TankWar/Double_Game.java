package TankWar;

import java.awt.event.ActionListener;

public class Double_Game extends Thread{
	ActionListener al;
	public void run() {
		try {
			Method.double_game();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void addActionListener(ActionListener action) {
		al = action;
	}
}
