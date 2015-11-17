import java.util.ArrayList;
import java.util.HashMap;

public class Collector {

	private File file;
	
	public Collector(File file) {
		this.file = file;
	}

	
	public static HashMap<String, ArrayList<String>> saveData(String hex, String one, String two, int operation){
		
		if (hex.isEmpty() || one.isEmpty() || two.isEmpty()) throw new IllegalArgumentException();		// Kaster exception hvis noen av argumentene (som kan være tom) er tom
		
		 HashMap<String, ArrayList<String>> map = new HashMap<>();	// HashMap for å holde på alle verdiene med hex-verdi som nøkkel
		 ArrayList<String> list = new ArrayList<>();				// ArrayList for å holde på måledata; orignal og konvertert
		
		String key = hex;
		
		String bitwise = Converter.bitwise(one, two, operation);		// Utfører bitwise operasjon på måledata
		String intValue = String.valueOf(Converter.bitToInt(bitwise));	// Konverterer måledata til int verdi
		
		list.add(one);					// Første linje med original måledata
		list.add(two);					// Andre linje med original måledata
		list.add(bitwise);				// "Resultatet" av måledata. Linje med måledata som det har blitt utført en bitwise operation på
		list.add(intValue);				// Int verdi av "Resultatet"
		
		if(map.containsKey(hex)) saveDuplicateData(hex, one, two, operation);	// Hvis måledata-id har blitt brukt før, brukes saveDuplicateData() til å legge data i logg
		else if(operation == 1 || operation == 2) map.put(key, list);	// Hvis måledata-id ikke finnes fra før av, og verdiene er gyldige, legges verdiene over i HashMap
		
		return map;	
	}
	
	public static ArrayList<String> saveDuplicateData(String hex, String one, String two, int operation){
		
		ArrayList<String> duplicateList = new ArrayList<>();	// Liste for å holde på duplikat-verdier
		duplicateList.add(hex);
		duplicateList.add(one);
		duplicateList.add(two);
		duplicateList.add(String.valueOf(operation));
		
		return duplicateList;

	}
}
		
	
