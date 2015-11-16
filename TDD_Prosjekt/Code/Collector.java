import java.util.ArrayList;
import java.util.HashMap;

public class Collector {

	private File file;
	
	public Collector(File file) {
		this.file = file;
	}

	
	public static HashMap<String, ArrayList<String>> saveData(String hex, String one, String two, int operation){
		
		if (hex.isEmpty() || one.isEmpty() || two.isEmpty()) throw new IllegalArgumentException();		// Kaster exception hvis noen av argumentene (som kan v�re tom) er tom
		
		 HashMap<String, ArrayList<String>> map = new HashMap<>();	// HashMap for � holde p� alle verdiene med hex-verdi som n�kkel
		 ArrayList<String> list = new ArrayList<>();				// ArrayList for � holde p� m�ledata; orignal og konvertert
		
		String key = hex;
		
		String bitwise = Converter.bitwise(one, two, operation);		// Utf�rer bitwise operasjon p� m�ledata
		String intValue = String.valueOf(Converter.bitToInt(bitwise));	// Konverterer m�ledata til int verdi
		
		list.add(one);					// F�rste linje med original m�ledata
		list.add(two);					// Andre linje med original m�ledata
		list.add(bitwise);				// "Resultatet" av m�ledata. Linje med m�ledata som det har blitt utf�rt en bitwise operation p�
		list.add(intValue);				// Int verdi av "Resultatet"
		
		if(map.containsKey(hex)) saveDuplicateData(hex, one, two, operation);	// Hvis m�ledata-id har blitt brukt f�r, brukes saveDuplicateData() til � legge data i logg
		else if(operation == 1 || operation == 2) map.put(key, list);	// Hvis m�ledata-id ikke finnes fra f�r av, og verdiene er gyldige, legges verdiene over i HashMap
		
		return map;	
	}
	
	public static ArrayList<String> saveDuplicateData(String hex, String one, String two, int operation){
		
		ArrayList<String> duplicateList = new ArrayList<>();	// Liste for � holde p� duplikat-verdier
		duplicateList.add(hex);
		duplicateList.add(one);
		duplicateList.add(two);
		duplicateList.add(String.valueOf(operation));
		
		return duplicateList;

	}
}
		
	
