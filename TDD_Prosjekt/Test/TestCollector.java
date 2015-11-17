import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;


@RunWith(MockitoJUnitRunner.class)
public class TestCollector {
	
	File file;
	Collector collector;
	Collector.Entry entryList;
	HashMap<String, Collector.Entry> expectedMap;
	
	@Before
	public void setUp(){
		file = mock(File.class);
		collector = new Collector(file);
		
		entryList = new Collector.Entry();
		expectedMap = new HashMap<>();
	}
	
	// File - tester
	
	@Test
	public void openFile_WhenOpenFileRequested_ShouldReturnData_WithMockito(){
		
		when(collector.openFile("fil")).thenReturn("03ac0f 1 110101000000110111001101 001000011110011101001111");
		assertThat(collector.openFile("fil"), equalTo("03ac0f 1 110101000000110111001101 001000011110011101001111"));
		verify(file, times(1)).openFile();
		
	}
	
	@Test
	public void readLine_WhenReadLineRequested_ShouldReturnSingleLine_WithMockito(){
		
		when(collector.readLine()).thenReturn("03ac0f");
		assertThat(collector.readLine(), equalTo("03ac0f"));
		verify(file, times(1)).readLine();
	}
	
	@Test
	public void nextLine_WhenNextLineRequested_ShouldReturnTrue_WithMockito(){
		
		when(collector.nextLine()).thenReturn(true);
		assertThat(collector.nextLine(), is(true));
		verify(file, times(1)).nextLine();
	}
	
	// Collector - tester
	
	@Test
	public void saveData_LineOfData_ShouldReturnMapWithCorrectValues(){
		
		String key = "03ac0f";
		entryList.value = "110101000000110111001101" + ", " + 13897165 + ", " + "001000011110011101001111" + ", " + 2221903 + ", " + "000000000000010101001101" + ", " + 1357;		
		expectedMap.put(key, entryList);
		
		assertThat(collector.createEntry("03ac0f", "110101000000110111001101", "001000011110011101001111", 1).toString(), equalTo(expectedMap.toString()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void saveData_MissingParameters_ShouldThrowIllegalArgumentException(){
		
		collector.createEntry("03ac0f", "", "001000011110011101001111", 1);
	}
	
	@Test
	public void saveDuplicateData_DuplicateData_ShouldReturnListOfDuplicatesWithAllParameters(){
		
		collector.createEntry("03ac0f", "110101000000110111001101", "001000011110011101001111", 3);
		collector.createEntry("03ac0f", "110101000000110111001101", "001000011110011101001111", 1);
		

		assertThat(collector.saveDuplicateData("03ac0f", "110101000000110111001101", "001000011110011101001111", 1).size(), equalTo(8));
	}

}
