package entryState;

public class StateWaiting implements State {

    public static StateWaiting instance = new StateWaiting();
	
	private StateWaiting() {}
	
	@Override
	public State move(String c) {
		// TODO Auto-generated method stub
		switch(c) {
		case "a":
			return StateAllocated.instance;
		case "c":
			return StateCancelled.instance;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return "Waiting";
	}
}
