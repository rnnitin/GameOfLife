package space.ramakoti.experiment.gameoflife;

public class InMemGrid {
	private CellState[][] grid = new CellState[Constants.NUM_CELLS_X_AXIS][Constants.NUM_CELLS_Y_AXIS];
	private int genCount = 0;

	public void init() {
		double ratioAlive = (Math.random()
				* (Constants.INIT_MAX_LIVE_CELLS_PERCENT - Constants.INIT_MIN_LIVE_CELLS_PERCENT)
				+ Constants.INIT_MIN_LIVE_CELLS_PERCENT) / 100d;
		for (int x = 0; x < Constants.NUM_CELLS_X_AXIS; x++) {
			for (int y = 0; y < Constants.NUM_CELLS_Y_AXIS; y++) {
				double randVal = Math.random();
				grid[x][y] = Double.compare(randVal, ratioAlive) < 0 ? CellState.ALIVE : CellState.DEAD;
			}
		}
		genCount = 0;
	}

	public CellState getCellState(int x, int y) {
		if (x < 0 || x >= Constants.NUM_CELLS_X_AXIS || y < 0 || y >= Constants.NUM_CELLS_Y_AXIS) {
			return CellState.DEAD;
		}
		return this.grid[x][y];
	}

	private int numNeibhorsAlive(int x, int y) {
		if (x == 0) {
			if (y > 0 && y < Constants.NUM_CELLS_Y_AXIS - 1) {
				return this.grid[x][y - 1].getValue() + this.grid[x][y + 1].getValue() + this.grid[x + 1][y].getValue()
						+ this.grid[x + 1][y - 1].getValue() + this.grid[x + 1][y + 1].getValue();
			} else {
				if (y == 0) {
					return this.grid[x][y + 1].getValue() + this.grid[x + 1][y].getValue()
							+ this.grid[x + 1][y + 1].getValue();
				} else {
					return this.grid[x][y - 1].getValue() + this.grid[x + 1][y].getValue()
							+ this.grid[x + 1][y - 1].getValue();
				}
			}
		} else if (x == Constants.NUM_CELLS_X_AXIS - 1) {
			if (y > 0 && y < Constants.NUM_CELLS_Y_AXIS - 1) {
				return this.grid[x][y - 1].getValue() + this.grid[x][y + 1].getValue() + this.grid[x - 1][y].getValue()
						+ this.grid[x - 1][y - 1].getValue() + this.grid[x - 1][y + 1].getValue();
			} else {
				if (y == 0) {
					return this.grid[x][y + 1].getValue() + this.grid[x - 1][y].getValue()
							+ this.grid[x - 1][y + 1].getValue();
				} else {
					return this.grid[x][y - 1].getValue() + this.grid[x - 1][y].getValue()
							+ this.grid[x - 1][y - 1].getValue();
				}
			}
		} else if (y == 0) {
			return this.grid[x - 1][y].getValue() + this.grid[x - 1][y + 1].getValue() + this.grid[x][y + 1].getValue()
					+ this.grid[x + 1][y].getValue() + this.grid[x + 1][y + 1].getValue();
		} else if (y == Constants.NUM_CELLS_Y_AXIS - 1) {
			return this.grid[x - 1][y].getValue() + this.grid[x - 1][y - 1].getValue() + this.grid[x][y - 1].getValue()
					+ this.grid[x + 1][y].getValue() + this.grid[x + 1][y - 1].getValue();
		}
		
		return this.grid[x - 1][y].getValue() + this.grid[x - 1][y - 1].getValue() + this.grid[x - 1][y + 1].getValue()
				+ this.grid[x][y - 1].getValue() + this.grid[x][y + 1].getValue() + this.grid[x + 1][y].getValue()
				+ this.grid[x + 1][y - 1].getValue() + this.grid[x + 1][y + 1].getValue();
	}

	public void computeNextGen() {
		for (int x = 0; x < Constants.NUM_CELLS_X_AXIS; x++) {
			for (int y = 0; y < Constants.NUM_CELLS_Y_AXIS; y++) {
				if (this.grid[x][y] == CellState.ALIVE && (numNeibhorsAlive(x, y) < 2 || numNeibhorsAlive(x, y) > 2)) {
					this.grid[x][y] = CellState.DEAD;
				}
				if (this.grid[x][y] == CellState.DEAD && numNeibhorsAlive(x, y) == 3) {
					this.grid[x][y] = CellState.ALIVE;
				}
			}
		}
		++this.genCount;
	}

	public int gridSum() {
		int sum = 0;
		for (int x = 0; x < Constants.NUM_CELLS_X_AXIS; x++) {
			for (int y = 0; y < Constants.NUM_CELLS_Y_AXIS; y++) {
				sum += this.grid[x][y].getValue();
			}
		}
		return sum;
	}

	public int getGenCount() {
		return genCount;
	}

	@Override
	public int hashCode() {
		StringBuffer sb = new StringBuffer();
		for (int x = 0; x < Constants.NUM_CELLS_X_AXIS; x++) {
			for (int y = 0; y < Constants.NUM_CELLS_Y_AXIS; y++) {
				sb.append(this.grid[x][y]);
			}
		}
		return sb.toString().hashCode();
	}
}
