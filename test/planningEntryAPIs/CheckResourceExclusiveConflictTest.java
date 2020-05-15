package planningEntryAPIs;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import planningEntry.CourseEntry;
import planningEntry.FlightEntry;
import planningEntry.PlanningEntry;
import planningEntryCollection.CourseCollection;
import planningEntryCollection.FlightCollection;

public class CheckResourceExclusiveConflictTest {

	/**
	 * ≤‚ ‘checkResourceExclusiveConflict∑Ω∑®
	 * ≤‚ ‘≤ﬂ¬‘£∫
	 * Œﬁ√¨∂‹£¨”–√¨∂‹
	 * @throws IOException 
	 */
	@Test
	public void testCheckResourceExclusiveConflict() throws IOException {
		ResourceExclusiveConflictAPI rec = new CheckResourceExclusiveConflict();
		FlightCollection fc = new FlightCollection("test/txt/test_1.txt");
		List<FlightEntry> lf = fc.getFlightEntry();
		List<PlanningEntry> lp = new ArrayList<PlanningEntry>();
		for(FlightEntry fe:lf) {
			lp.add(fe);
		}
		assertEquals(false,rec.checkResourceExclusiveConflict(lp));
		
		CourseCollection co = new CourseCollection("test/txt/test_2.txt");
		List<PlanningEntry> entries = new ArrayList<PlanningEntry>();
		for(CourseEntry ce:co.getCourseEntry()) {
			entries.add(ce);
		}
		assertEquals(true,rec.checkResourceExclusiveConflict(entries));
	}

}
