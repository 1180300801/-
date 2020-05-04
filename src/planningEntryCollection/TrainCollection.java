package planningEntryCollection;

import java.util.ArrayList;
import java.util.List;

import planningEntry.TrainEntry;

public class TrainCollection {

private List<TrainEntry> flightCollection = new ArrayList<TrainEntry>();
	
	public void addEntry(TrainEntry trainEntry) {
		flightCollection.add(trainEntry);
	}
}
