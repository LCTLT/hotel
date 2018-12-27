package util;

public class BoolIsUtil {
	
	public static boolean isBool(String name) {
		if(name != null && !name.equals("") && !name.equals("undefined")) {
			return true;
		}
		return false;
	}
	
}
