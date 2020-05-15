package entryState;

public class StateCancelled implements State {

	static StateCancelled instance = new StateCancelled();
	
	private StateCancelled() {}
	
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
		return "Cancelled";
	}
}
