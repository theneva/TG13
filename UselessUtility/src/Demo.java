

import java.util.Locale;
import java.util.ResourceBundle;

import pw.svn.client.ChatClient;
import pw.svn.server.ChatServer;

/**
 * Demonstrates the chat server.
 * @author Theneva
 *
 */
public class Demo {
	public static void main(String[] args) {
		
		if (args.length != 2) {
			new Demo("en", "US");
		} else {
			new Demo(args[0], args[1]);
		}
	}
	
	public Demo(String language, String country) {
		
		final String serverAddress = "localhost";
		final int port = 8000;
		
		Locale currentLocale = new Locale(language, country);
		ResourceBundle messages = ResourceBundle.getBundle("MessageBundle", currentLocale);
		
		new ChatServer(serverAddress, port, messages);
		
		try {
			Thread.sleep(2000);
			
			for (int i = 0; i < 5; i++) {
				new ChatClient(serverAddress, port, messages);
				Thread.sleep(500);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
