import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A utility class to handle security features
 */
public class SecurityHandler {
	
	/**
	 * Hashes a given password using SHA-512
	 * 
	 * @param password	The password to be hashed
	 * @return hash		The hashed password as a String
	 */
	public static String hashPassword(String password){
		//Declare and initialise hash
		byte[] hash = null;
		
		//Try and use SHA-512 to encrypt password String as an array of bytes
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//Convert the array of bytes into hexadecimal
		StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hash) {
            stringBuilder.append(String.format("%02x", b));
        }
        
        //Return hash as a String
        return stringBuilder.toString();
	}
	
	/**
	 * Checks to see if a String contains any illegal characters that are associated with SQL injection
	 * Returns true if it passes the check, false if it does not
	 * 
	 * @param data 		The data to be checked
	 * @return boolean	The result of the check
	 */
	public static boolean sqlInjectionCheck(String data){
		//Declare result and initially set to true
		boolean result = true;
		
		//Check to see if data contains any illegal characters, if it does then result will be set to false
		if(data.contains("'") || data.contains(";") || data.contains("=") || data.contains("*") || data.contains("(") || data.contains(")")){
			result = false;
		}
		//Return result
		return result;
	}
}