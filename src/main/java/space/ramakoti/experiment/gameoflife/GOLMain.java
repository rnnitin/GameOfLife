package space.ramakoti.experiment.gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class GOLMain {
	public static void main(String[] args) {
		InMemGrid gridInMem = new InMemGrid();
		gridInMem.init();

		GOLGraphicsController gController = GOLGraphicsController.getInstance();
		Graphics g = gController.getGraphics();
		initGraphicsGrid(g);
		changeAliveColor();
		gController.paintGrid(g, gridInMem);

		HashSet<Integer> gridCkSumSet = new HashSet<Integer>();
		while (true) {
			sleepInMills(100);
			
			int hashCode = gridInMem.hashCode();
			if (gridCkSumSet.contains(hashCode)) {
				sleepInMills(1000);
				
				gridInMem.init();
				changeAliveColor();
				gController.paintGrid(g, gridInMem);
				
				sleepInMills(200);
				
				gridCkSumSet.clear();
			}
			
			gridInMem.computeNextGen();
			gController.paintGrid(g, gridInMem);
			gridCkSumSet.add(hashCode);
		}
	}

	private static void initGraphicsGrid(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(Constants.BORDERGAP, Constants.BORDERGAP, Constants.CELLW * Constants.NUM_CELLS_X_AXIS,
				Constants.CELLH * Constants.NUM_CELLS_Y_AXIS);
		sleepInMills(2000);
	}
	
	private static void changeAliveColor() {
		GOLGraphicsController.ALIVE_COLOR = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
				(int) (Math.random() * 255));
	}
	
	private static void sleepInMills(int sleepMills) {
		try {
			TimeUnit.MILLISECONDS.sleep(sleepMills);
		} catch (InterruptedException e) {
			// IGNORE
		}
	}
}
