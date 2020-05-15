package entryState;

public class StateRunning implements State {

	static StateRunning instance = new StateRunning();
	
	private StateRunning() {}
	
	@Override
	public State move(String c) {
		// TODO Auto-generated method stub
		switch(c) {
		case "b":
			return StateBlocked.instance;
		case "e":
			return StateEnded.instance;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return "Running";
	}
}
