package space.ramakoti.experiment.gameoflife;

public enum CellState {
	ALIVE(1), DEAD(0);

	private int stateValue;

	private CellState(int stateVal) {
		this.stateValue = stateVal;
	}
	
	public int getValue() {
		return this.stateValue;
	}
	
	@Override
	public String toString() {
		return Integer.toString(this.stateValue);
	}
}
