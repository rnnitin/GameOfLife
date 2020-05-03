package space.ramakoti.experiment.gameoflife;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class GOLGraphicsController extends Canvas {
	private static final long serialVersionUID = 5270417182943669752L;
	static Color ALIVE_COLOR = Color.MAGENTA;

	private static GOLGraphicsController _instance = null;

	private String[] toPrint = { "", "", "" };

	private GOLGraphicsController() {
	}

	public static synchronized GOLGraphicsController getInstance() {
		if (_instance == null) {
			_instance = new GOLGraphicsController();
			JFrame frame = new JFrame(Constants.WINDOW_TITLE);
			Canvas canvas = _instance;
			canvas.setSize(Constants.MAXX, Constants.MAXY + Constants.TEXT_HEIGHT);
			frame.add(canvas);
			frame.pack();
			frame.setVisible(true);
		}
		return _instance;
	}

	public void paintGrid(Graphics g, InMemGrid inMemGrid) {
		int cellX = 0, cellY = 0;
		for (int x = Constants.BORDERGAP; x < Constants.MAXX - (Constants.MAXX % Constants.BORDERGAP)
				- Constants.BORDERGAP; x += Constants.CELLW + 2 * Constants.CELL_BORDER_THINKNESS, cellX++) {
			cellY = 0;
			for (int y = Constants.BORDERGAP; y < Constants.MAXY - (Constants.MAXY % Constants.BORDERGAP)
					- Constants.BORDERGAP; y += Constants.CELLH + 2 * Constants.CELL_BORDER_THINKNESS, cellY++) {
				CellState currCellState = inMemGrid.getCellState(cellX, cellY);
				if (currCellState == CellState.ALIVE) {
					g.setColor(ALIVE_COLOR);
				}
				g.drawRect(x, y, Constants.CELLW, Constants.CELLH);
				g.fillRect(x + Constants.CELL_BORDER_THINKNESS, y + Constants.CELL_BORDER_THINKNESS, Constants.CELLW,
						Constants.CELLH);
				g.setColor(Constants.DEAD_COLOR);
			}
		}

		int x = Constants.BORDERGAP * 2;
		int y = (int) (Constants.BORDERGAP * 4)
				+ Constants.NUM_CELLS_Y_AXIS * (Constants.CELLH + Constants.CELL_BORDER_THINKNESS);

		Font font = new Font("Serif", Font.PLAIN, 24);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(this.toPrint[0], x, y);
		g.drawString(this.toPrint[1], x + Constants.TEXT_WIDTH, y);
		g.drawString(this.toPrint[2], x + Constants.TEXT_WIDTH * 2, y);

		try {
			TimeUnit.MILLISECONDS.sleep(20);
		} catch (InterruptedException e) {
			// IGNORE
		}

		g.setColor(Color.RED);
		toPrint[0] = "Generation # " + inMemGrid.getGenCount();
		toPrint[1] = "Alive # " + inMemGrid.gridSum();
		toPrint[2] = String.format("Alive %% %f",
				(inMemGrid.gridSum() * 100d / (Constants.NUM_CELLS_X_AXIS * Constants.NUM_CELLS_Y_AXIS)));

		g.drawString(toPrint[0], x, y);
		g.drawString(toPrint[1], x + Constants.TEXT_WIDTH, y);
		g.drawString(toPrint[2], x + Constants.TEXT_WIDTH * 2, y);
		g.setColor(Constants.DEAD_COLOR);
	}
}
