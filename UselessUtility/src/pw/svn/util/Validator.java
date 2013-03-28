package pw.svn.util;

import java.util.Random;

/**
 * 
 * @author Waidmann
 *
 */
public class Validator {
	
	static String[] emoticons = { "o_o", ":D", ":I", "xD", ":|", ":O", ":P", "._." };
	static Random rand = new Random();
	
	public static String swapEmoticons(String message){
		Boolean done = false;
		String tmp = message;
		StringBuilder sb = new StringBuilder();
		while(!done){
			int r = rand.nextInt(emoticons.length);
			
			tmp = tmp.replaceFirst(" ?:-?[a-zA-Z\\(\\)\\[\\]]{1,1} ", " " + emoticons[r] + " " + (char) 0x3);
			if(tmp.indexOf((char) 0x3) != -1){
				sb.append(tmp.substring(0, tmp.indexOf((char) 0x3)));
				tmp = tmp.substring(tmp.indexOf((char) 0x3) + 1);
			}else
				done = true;
			
		}
		message = sb.toString();
		
		return message.replace((char) 0x03, (char) 0x20);
		
	}
	public static void main(String[] args) {
		System.out.println(swapEmoticons("dfrtyguhinojk :D vgubhsdø.a:A hdu :O gsdayuasd :) "));
//		System.out.println("drftygvhujbk :D gyudhajnk :( ".replaceFirst(" ?:-?[a-zA-Z\\(\\)\\[\\]]{1,1} ", (char) 0x3 + "").indexOf((char) 0x3));
//		System.out.println((char) 0x03);
	}
	
}
