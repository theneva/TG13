package pw.svn.util;

import java.util.Random;

public class SaltGenerator {

	Random rand;
	
	public SaltGenerator(){
		rand = new Random();
	}
	
	public String getSalt(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 30; i++){
			sb.append(Integer.toHexString(rand.nextInt(16)));
		}
		return sb.toString();
	}
}
