import java.util.HashMap;
import java.util.InputMismatchException;

public class PositiveNumber {

	private int value; //Dezimal
	
	private static HashMap<Character, Integer> map;
	
	public static void main(String[] args) {
		PositiveNumber n = new PositiveNumber();
		n.setDecimal("15");
		n.setHexadecimal("FC2");
		System.out.println(n.getDecimal());
		System.out.println(n.getHexadecimal());
	}
	
	public PositiveNumber() {
		map = new HashMap<>();
		for (int i = '0';i <= '9';i++) {
			map.put((char)i,i - '0');
		}
		for (int i = 'A';i <= 'F';i++) {
			map.put((char)i, i - 'A' + 10);
		}
	}
	
	public void setDecimal(String s) {
		value = toDecimal(s, 10);
		
	}
	
	public void setHexadecimal(String s) {
		value = toDecimal(s, 16);
	}
	
	public void setBinary(String s) {
		value = toDecimal(s, 2);
	}
	
	public String getDecimal() {
		return String.valueOf(value);
	}
	
	public String getHexadecimal() {
		return toBase(16);
	}
	
	public String getBinary() {
		return toBase(2);
	}
	
	private Character getKey(int value) {
		for (Character e : map.keySet()) {
			if (map.get(e) == value) {
				return e;
			}
		}
		return null;
	}
	
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
