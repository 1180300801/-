package entryState;

public class StateBlocked implements State {

    static StateBlocked instance = new StateBlocked();
	
	private StateBlocked() {}
	
	@Override
	public State move(String c) {
		// TODO Auto-generated method stub
		switch(c) {
		case "r":
			return StateRunning.instance;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return "Blocked";
	}
}
