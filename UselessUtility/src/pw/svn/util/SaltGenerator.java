package pw.svn.util;

import java.util.Random;
/**
 * 
 * @author Waidmann
 * @version 1.0
 */
public class SaltGenerator {

	Random rand;
	
	public SaltGenerator(){
		rand = new Random();
	}
	/**
	 * Creates a String containing 'random' hex values
	 * @return a String of hex values
	 */
	public String getSalt(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 30; i++){
			sb.append(Integer.toHexString(rand.nextInt(16)));
		}
		return sb.toString();
	}
}
