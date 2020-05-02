package space.ramakoti.experiment.gameoflife;

import java.awt.Color;

public class Constants {
	private Constants() {}
	
	static final String WINDOW_TITLE = "Game of life";
	static final int MAXX = 750;
	static final int MAXY = 600;
	static final int BORDERGAP = 15;
	static final int CELLW = 5;
	static final int CELLH = 5;
	static final int CELL_BORDER_THINKNESS = 1;
	static final int NUM_CELLS_X_AXIS = (MAXX - 2 * BORDERGAP) / CELLW;
	static final int NUM_CELLS_Y_AXIS = (MAXY - 2 * BORDERGAP) / CELLH;

	static final int INIT_MAX_LIVE_CELLS_PERCENT = 20;
	static final int INIT_MIN_LIVE_CELLS_PERCENT = 1;
	
	static final Color DEAD_COLOR = Color.WHITE;
}
