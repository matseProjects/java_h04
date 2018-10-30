import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * Repraesentiert eine positive Ganzzahl im Dezimalsystem. Get und Set-Methoden funktionieren auch ueber andere Basen
 * @author lukas
 *
 */
public class PositiveNumber {

	private int value; //Dezimal
	
	//Zuordnung der Zeichen zu dem Stellenwert in einer Hashmap
	private static HashMap<Character, Integer> map;
	
	public static void main(String[] args) {
		PositiveNumber n = new PositiveNumber();
		n.setDecimal("15");
		n.setHexadecimal("FC2");
		System.out.println(n.getDecimal());
		System.out.println(n.getHexadecimal());
	}
	
	//initialisiert die Hashmap
	public PositiveNumber() {
		map = new HashMap<>();
		for (int i = '0';i <= '9';i++) {
			map.put((char)i,i - '0');
		}
		for (int i = 'A';i <= 'F';i++) {
			map.put((char)i, i - 'A' + 10);
		}
		for (int i = 'a';i <= 'f';i++) {
			map.put((char)i, i - 'a' + 10);
		}
	}
	
	/**
	 * Setzt als Dezimalzahl
	 * @param s Input-String der umgewandelt werden soll
	 */
	public void setDecimal(String s) {
		value = toDecimal(s, 10);
		
	}
	
	/**
	 * Setzt als Hexadezimalzahl
	 * @param s Input-String der umgewandelt werden soll
	 */
	public void setHexadecimal(String s) {
		value = toDecimal(s, 16);
	}
	
	/**
	 * Setzt als Binaerzahl
	 * @param s Input-String der umgewandelt werden soll
	 */
	public void setBinary(String s) {
		value = toDecimal(s, 2);
	}
	
	/**
	 * 
	 * @return gibt this.value als Dezimalwert zurueck
	 */
	public String getDecimal() {
		return String.valueOf(value);
	}
	
	/**
	 * 
	 * @return gibt this.value als Hexdezimalwert zurueck
	 */
	public String getHexadecimal() {
		return toBase(16);
	}
	
	/**
	 * 
	 * @return gibt this.value als Binaerzahl zurueck
	 */
	public String getBinary() {
		return toBase(2);
	}
	
	/**
	 * Rueckwaertssuchfunktion der Hashmap
	 * @param value value der Hashmap
	 * @return key der Hashmap
	 */
	private Character getKey(int value) {
		for (Character e : map.keySet()) {
			if (map.get(e) == value) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param base Basis des Stellenwertsystems
	 * @return 
	 */
	private String toBase(int base) {
		if (base > 16) {
			throw new InputMismatchException();
		}
		String ergebnis = "";
		int v = this.value;
		int rest = v % base;
		int erg = (v - rest) / base;
		while (erg != 0) {
			ergebnis = getKey(rest) + ergebnis;
			rest = erg % base;
			erg = (erg - rest) / base;
		}
		ergebnis = getKey(rest) + ergebnis;
		return ergebnis;
	}
	
	/**
	 * 
	 * @param input input
	 * @param base Basis des Stellenwertsystems
	 * @return
	 */
	private int toDecimal(String input, int base) {
		int value = 0;
		for (int i = 0;i < input.length();i++) {
			char c = input.charAt(input.length() - 1 - i);
			Integer z = map.get(c);
			if (z == null || z + 1 > base) {
				throw new NumberFormatException("Falsches Zahlenformat");
			}
			value += Math.pow(base, i) * z;
		}
		return value;
	}
}
