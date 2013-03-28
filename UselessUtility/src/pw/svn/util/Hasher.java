package pw.svn.util;

import java.security.*;
/**
 * Class for hashing messages
 * @author Mads
 * @version 1.0
 */
public class Hasher {
	
	@SuppressWarnings("unused")
	private String message;
	private static MessageDigest digest;
	
	
	/**
	 * Converts the bytes in the MessageDigest into hex
	 * @return sb.toString() - the bytes in the MessageDigest in hex
	 */
	public static String convertToHex(String message){
		message += new SaltGenerator().getSalt();
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(message.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		byte[] byteData = digest.digest();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
	}
	
	
	
}
