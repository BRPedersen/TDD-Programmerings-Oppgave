
public class Converter {
	
	static String hexValues = "0123456789ABCDEF";		// String som brukes til � definerer tallverdier for karakterene
	
	public static int hexToInt(String hex) {
		
		if (hex.length() > 6) throw new IllegalArgumentException();		// Sjekker om stringen som kommer inn er for lang, kaster IllegalArgumentException.
		
		else {
			for (int i = 0; i < hex.length(); i++)		// Sjekker om en karakter i stringen er en hexadecimal karakter, hvis ikke kastest IllegalArgumentException 
				if (Character.digit(hex.charAt(i), 16) == -1) 
					throw new IllegalArgumentException();
			
			
			int counter = hex.length() -1;			// Lengde p� hex-verdi
			int sum = 0;							// int som skal returneres, hvis hex stringen er tom vil det returneres 0
			
		
			for (char c : hex.toCharArray()) {			// G�r gjennom alle verdiene som sendes inn
				char d = Character.toUpperCase(c);		// Gj�r om hver karakter til upperCase, slik at koden finner den i hexValues
				int i = hexValues.indexOf(d);			// "konverterer" verdiene til tallverdien de har i posisjonen i stringen
				sum = (int) (sum + (Math.pow(16, counter))*i);	// Bruker Math.pow til � regne ut desimal-verdien av hex-verdien
				counter--;
			}
			return sum;	
		}
	}

}
