import java.util.HashMap;

public class PositiveNumber {

	private int value; //Dezimal
	
	private static HashMap<Character, Integer> map;
	
	public static void main(String[] args) {
		PositiveNumber n = new PositiveNumber();
		n.setHexadecimal("FC2");
		n.setBinary("110101");
		System.out.println(n.getDecimal());
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
		return null;
	}
	
	public String getBinary() {
		return null;
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
