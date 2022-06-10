package wasteland.journal;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JournalTest {
    
    @Test
    public void testJournalTabs() {
        Journal journal = new Journal();
        int j = 0;
        for (int i = 0; i < 5; i++) {
            assertEquals(j, journal.getTab());
            journal.nextTab();
            if (j < 3) {
                j++;
            } else {
                j = 0;
            }
        }
    }

    @Test
    public void testJournalDay() {
        Journal journal = new Journal();
        assertEquals(0, journal.getDay());
        journal.nextDay();
        assertEquals(1, journal.getDay());
    }
}
