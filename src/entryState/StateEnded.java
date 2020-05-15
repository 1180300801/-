package entryState;

public class StateEnded implements State {

	static StateEnded instance = new StateEnded();
	
	private StateEnded() {}
	
	@Override
	public State move(String c) {
		// TODO Auto-generated method stub
		switch(c) {
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return "Ended";
	}
}
