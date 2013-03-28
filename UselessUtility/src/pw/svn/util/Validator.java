package pw.svn.util;

import java.util.Random;

/**
 * 
 * @author Waidmann
 * 
 */
public class Validator {

	static String[] emoticons = { "o_o", ":D", ":I", "xD", ":|", ":O", ":P",
			"._.", ">.<", "(o_o)", ":L" };
	static Random rand = new Random();
	static Hasher h = new Hasher();
	
	@SuppressWarnings("static-access")
	public static String swapEmoticons(String message) {
		boolean done = false;
		String tmp = message;
		StringBuilder sb = new StringBuilder();
		while (!done) {
			int r = rand.nextInt(emoticons.length);

			tmp = tmp.replaceFirst(" ?:-?[a-zA-Z\\(\\)\\[\\]]{1,1} ", (char) 0x4 + " "
					+ emoticons[r] + " " + (char) 0x3);
			if (tmp.contains((char) 0x3 + "")) {
				sb.append(h.convertToHex(tmp.substring(0, tmp.indexOf((char) 0x4))) + tmp.substring(tmp.indexOf((char) 0x4) + 1, tmp.indexOf((char) 0x3)));
				tmp = tmp.substring(tmp.indexOf((char) 0x3) + 1);
			} else
				done = true;

		}
		message = sb.toString();

		return message.replace((char) 0x03, (char) 0x20).replace((char) 0x4, (char) 0x20);

	}

	public static void main(String[] args) {
		System.out
				.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		System.out
		.println(swapEmoticons("dfrtyguhinojk :D vgubhsd�.a:A hdu :O gsdayuasd :) "));
		// System.out.println("drftygvhujbk :D gyudhajnk :( ".replaceFirst(" ?:-?[a-zA-Z\\(\\)\\[\\]]{1,1} ",
		// (char) 0x3 + "").indexOf((char) 0x3));
		// System.out.println((char) 0x03);
	}

}
