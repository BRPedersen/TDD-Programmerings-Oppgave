import java.util.ArrayList;
import java.util.HashMap;

public class Collector {

	private static File file;
	
	
	private ArrayList<String> duplicateList = new ArrayList<>();		// Liste for å holde på duplikat-verdier
	private Entry e = new Entry();										// Entry som holder på verdier
	private HashMap<String, Entry> map = new HashMap<>();				// HashMap som returneres, fylt med Entries og nøkkler
	
	
	// Metoder fra interface File, blir Mock'et ved hjelp av Mockito
	public Collector(File file){
		this.file = file;
	}
	
	public Object openFile(String filename){
		return file.openFile();
	}
	
	public String readLine(){
		return file.readLine();
	}
	
	public boolean nextLine(){
		return file.nextLine();
	}
	
	/** Metode for å lagre data i et HashMap */
	public HashMap<String, Entry> createEntry(String hex, String one, String two, int operation){
		
		if (hex.isEmpty() || one.isEmpty() || two.isEmpty()) throw new IllegalArgumentException();		// Kaster exception hvis noen av argumentene (som kan være tom) er tom
	
		String key = hex;												// Henter hex-verdi som brukes som nøkkel i HashMap
		int oneInt = Converter.bitToInt(one);							// Int-verdi til første linje med måledata
		int twoInt = Converter.bitToInt(two);							// Int-verdi til andre linje med måledata
		String bitwise = Converter.bitwise(one, two, operation);		// Utfører bitwise operasjon på måledata
		int intBitwise = (Converter.bitToInt(bitwise));					// Konverterer måledata til int verdi
		
		
		if(map.containsKey(key)) saveDuplicateData(hex, one, two, operation);	// Hvis nøkkel finnes fra før kalles saveDuplicate metode	
		else if(operation == 1 || operation == 2){		// Hvis operation verdi er korrekt og nøkkel er original legger man verdier inn i map						
			e.value = one + ", " + oneInt + ", " + two + ", " + twoInt + ", " + bitwise + ", " + intBitwise;	// Lager en Entry av verdier
			map.put(key, e);	
		}
		else saveDuplicateData(hex, one, two, operation);	// Hvis operation er ugyldig kalles saveDuplicate metode
		
		return map;
	}
	
	/** Metode for å lagre stringer som er ugyldige eller har en duplikat nøkkel */
	public ArrayList<String> saveDuplicateData(String hex, String one, String two, int operation){
		
		duplicateList.add(hex);
		duplicateList.add(one);
		duplicateList.add(two);
		duplicateList.add(String.valueOf(operation));
		
		return duplicateList;
	}
	
	/** Indre klasse for Entry til HashMap, basert på Entry.class fra Oblig 3 */
	public static class Entry<V> {
		
		V value;
		
		public V getValue(){
			return value;
		}
		
		 @Override
		 public String toString() {
		    return "[" + value + "]";
		 }
	}

}


		
	
