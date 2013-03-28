package pw.svn.client;

import java.util.Locale;
import java.util.ResourceBundle;

public class ChatClientStarterWaidmann {
	public static void main(String[] args) {
		
		String language, country;
		
		if (args.length != 2) {
			language = new String("en");
			country = new String("US");
		} else {
			language = args[0];
			country = args[1];
		}
		
		Locale currentLocale = new Locale(language, country);
		
		ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle", currentLocale);
		
		new ChatClient("localhost", 8000, bundle);
	}
}
