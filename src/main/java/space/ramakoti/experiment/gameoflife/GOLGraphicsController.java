package space.ramakoti.experiment.gameoflife;

import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JFrame;

public class GOLGraphicsController extends Canvas {
	private static final long serialVersionUID = 5270417182943669752L;

	private static GOLGraphicsController _instance = null;

	private GOLGraphicsController() {
	}

	public static synchronized GOLGraphicsController getInstance() {
		if (_instance == null) {
			_instance = new GOLGraphicsController();
			JFrame frame = new JFrame(Constants.WINDOW_TITLE);
			Canvas canvas = _instance;
			canvas.setSize(Constants.MAXX, Constants.MAXY);
			frame.add(canvas);
			frame.pack();
			frame.setVisible(true);
		}
		return _instance;
	}

	public void paintGrid(Graphics g, InMemGrid inMemGrid) {
		int cellX = 0, cellY = 0;
		for (int x = Constants.BORDERGAP; x < Constants.MAXX - (Constants.MAXX % Constants.BORDERGAP)
				- Constants.BORDERGAP; x += Constants.CELLW + 2*Constants.CELL_BORDER_THINKNESS, cellX++) {
			cellY = 0;
			for (int y = Constants.BORDERGAP; y < Constants.MAXY - (Constants.MAXY % Constants.BORDERGAP)
					- Constants.BORDERGAP; y += Constants.CELLH + 2*Constants.CELL_BORDER_THINKNESS, cellY++) {
				CellState currCellState = inMemGrid.getCellState(cellX, cellY);
				if (currCellState == CellState.ALIVE) {
					g.setColor(Constants.ALIVE_COLOR);
				}
				g.drawRect(x, y, Constants.CELLW, Constants.CELLH);
				g.fillRect(x+Constants.CELL_BORDER_THINKNESS, y + Constants.CELL_BORDER_THINKNESS, Constants.CELLW, Constants.CELLH);
				g.setColor(Constants.DEAD_COLOR);
			}
		}
	}
}
