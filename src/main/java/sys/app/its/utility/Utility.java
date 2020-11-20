package sys.app.its.utility;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utility {
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	private String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}

	public String generateUserId(int length) {
		StringBuilder str = new StringBuilder();
		str.append("UID").append(generateRandomString(length));
		return str.toString();
	}
	
	public String generateAddressId(int length) {
		StringBuilder str = new StringBuilder();
		str.append("UAID").append(generateRandomString(length));
		return str.toString();
	}
	
	public String generateImageId(int length) {
		StringBuilder str = new StringBuilder();
		str.append("UIMGID").append(generateRandomString(length));
		return str.toString();
	}
	
	public String generateFullName(String f, String m, String l, String s) {
		StringBuilder str = new StringBuilder();
		str.append(f+" ").append(m+" ");
		if(s.equals(null)) {
			str.append(l);
		}
		else {
			str.append(l+" "+s);
		}
		return str.toString();
	}
}
