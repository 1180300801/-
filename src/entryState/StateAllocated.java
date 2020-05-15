package entryState;

public class StateAllocated implements State {

	static StateAllocated instance = new StateAllocated();
	
	private StateAllocated() {}
	
	@Override
	public State move(String c) {
		// TODO Auto-generated method stub
		switch(c) {
		case "r":
			return StateRunning.instance;
		case "c":
			return StateCancelled.instance;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return "Allocated";
	}
}
