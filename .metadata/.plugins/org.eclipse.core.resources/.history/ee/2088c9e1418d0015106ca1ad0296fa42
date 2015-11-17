import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TestCollector {
	
	File file;
	Collector collector;
	HashMap<String, ArrayList<String>> expectedMap;
	ArrayList<String> list;
	
	@Before
	public void setUp(){
		file = mock(File.class);
		collector = new Collector(file);
		
		/** Burde det være slik, eller burde Collector implementere File
		 * og Override metodene fra File, slik at man kaller på dem fra collector
		 * istede for file? 
		 */
		
		expectedMap = new HashMap<>();
		list = new ArrayList<>();
	}
	
	// File - tester
	
	@Test
	public void openFile_WhenOpenFileRequested_ShouldReturnData_WithMockito(){
		
		when(file.openFile()).thenReturn("03ac0f 1 110101000000110111001101 001000011110011101001111");
		
		assertThat(file.openFile(), equalTo("03ac0f 1 110101000000110111001101 001000011110011101001111"));
		
		//file.openFile();
		//verify(file, times(1)).openFile();
	}
	
	@Test
	public void readLine_WhenReadLineRequested_ShouldReturnSingleLine_WithMockito(){
		
		when(file.readLine()).thenReturn("03ac0f");
		assertThat(file.readLine(), equalTo("03ac0f"));
	}
	
	@Test
	public void nextLine_WhenNextLineRequested_ShouldReturnTrue_WithMockito(){
		
		when(file.nextLine()).thenReturn(true);
		assertThat(file.nextLine(), is(true));
	}
	
	// Collector - tester
	
	@Test
	public void saveData_LineOfData_ShouldReturnMapWithCorrectValues(){
		
		/** Er dette formatet ønskelig, eller skal det være:
		 * Første Linje med måledata
		 * Første Linje med måledata konvertert til int
		 * Andre Linje med måledata
		 * Andre Linja med måledata konvertert til int
		 * osv ?
		 */
		
		list.add("110101000000110111001101");
		list.add("001000011110011101001111");
		list.add("000000000000010101001101");
		list.add("1357");
		
		expectedMap.put("03ac0f", list);
		
		assertThat(collector.saveData("03ac0f", "110101000000110111001101", "001000011110011101001111", 1), equalTo(expectedMap));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void saveData_MissingParameters_ShouldThrowIllegalArgumentException(){
		
		collector.saveData("03ac0f", "", "001000011110011101001111", 1);
	}
	
	@Test
	public void saveDuplicateData_DuplicateData_ShouldReturnListOfDuplicatesWithAllParameters(){
		
		collector.saveData("03ac0f", "110101000000110111001101", "001000011110011101001111", 1);
		collector.saveData("03ac0f", "110101000000110111001101", "001000011110011101001111", 1);
		

		assertThat(collector.saveDuplicateData("03ac0f", "110101000000110111001101", "001000011110011101001111", 1).size(), equalTo(4));
	}

}
