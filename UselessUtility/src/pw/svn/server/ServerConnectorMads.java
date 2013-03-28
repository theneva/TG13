package pw.svn.server;

import java.util.Locale;
import java.util.ResourceBundle;

public class ServerConnectorMads {
	public static void main(String[] args) {
		String language, country;
		
		if (args.length != 2) {
			language = "en";
			country = "US";
		} else {
			language = args[0];
			country = args[1];
		}
		
		Locale currentLocale = new Locale(language, country);
		
		ResourceBundle messages = ResourceBundle.getBundle("MessageBundle", currentLocale);
		
		new ChatServer("151.216.14.39", 8000, messages);
	}
}
