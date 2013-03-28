package pw.svn.client;

import java.awt.Point;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChatClientStarterTheneva {
	
	public static void main(String[] args) {
		
		String language = "en", country = "US";
		
		Locale currentLocale = new Locale(language, country);
		
		ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle", currentLocale);
		
		new ChatClient("151.216.14.39", 8000, bundle, new Point(0, 0));
	}
}
