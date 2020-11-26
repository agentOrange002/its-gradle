package sys.app.its.utility;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sys.app.its.security.SecurityConstants;

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
	
	public String generateCategoryId(int length) {
		StringBuilder str = new StringBuilder();
		str.append("CATID").append(generateRandomString(length));
		return str.toString();
	}
	
	public String generateIssueId(int length) {
		StringBuilder str = new StringBuilder();
		str.append("IID").append(generateRandomString(length));
		return str.toString();
	}
	
	public String generateIssueLogId(int length) {
		StringBuilder str = new StringBuilder();
		str.append("ILID").append(generateRandomString(length));
		return str.toString();
	}
	
	public String generateTaskId(int length) {
		StringBuilder str = new StringBuilder();
		str.append("TAID").append(generateRandomString(length));
		return str.toString();
	}
	
	public String generateTicketId(int length) {
		StringBuilder str = new StringBuilder();
		str.append("TIID").append(generateRandomString(length));
		return str.toString();
	}

	public String generateFullName(String f, String m, String l, String s) {
		StringBuilder str = new StringBuilder();		
		if (s.equals(null)) {
			str.append(f +" "+m+" "+l);
		} else {
			str.append(f +" "+m+" "+l+" "+s);
		}
		return str.toString().toUpperCase();
	}

	public String generateEmailVerificationToken(String userId) {
		String token = Jwts.builder().setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();
		return token;
	}

	public static boolean hasTokenExpired(String token) {
		boolean returnValue = false;
		try {
			Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token)
					.getBody();

			Date tokenExpirationDate = claims.getExpiration();
			Date todayDate = new Date();

			returnValue = tokenExpirationDate.before(todayDate);
		} catch (ExpiredJwtException ex) {
			returnValue = true;
		}

		return returnValue;
	}

	public String generatePasswordResetToken(String userId) {
		String token = Jwts.builder().setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();
		return token;
	}
}
