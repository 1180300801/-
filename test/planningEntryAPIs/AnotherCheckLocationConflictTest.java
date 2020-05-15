package planningEntryAPIs;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import planningEntry.CourseEntry;
import planningEntry.PlanningEntry;
import planningEntryCollection.CourseCollection;

public class AnotherCheckLocationConflictTest {

	@Test
	public void testAnotherCheckLocationConflict() throws IOException {
		LocationConflictAPI lc = new AnotherCheckLocationConflict();
		CourseCollection co = new CourseCollection("test/txt/test_2.txt");
		List<PlanningEntry> entries = new ArrayList<PlanningEntry>();
		for(CourseEntry ce:co.getCourseEntry()) {
			entries.add(ce);
		}
		assertEquals(false,lc.checkLocationConflict(entries));
		
		co = new CourseCollection("test/txt/test_3.txt");
		entries.clear();
		for(CourseEntry ce:co.getCourseEntry()) {
			entries.add(ce);
		}
		assertEquals(true,lc.checkLocationConflict(entries));
	}

}
