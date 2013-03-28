

import pw.svn.client.ChatClient;
import pw.svn.server.ChatServer;

/**
 * Demonstrates the chat server.
 * @author Theneva
 *
 */
public class Demo {
	public static void main(String[] args) throws Exception {
		
		final String serverAddress = "localhost";
		final int serverPort = 8000;
		
		ChatServer server = new ChatServer(serverAddress, serverPort);
		
		Thread.sleep(6000);
		
		ChatClient client = new ChatClient(serverAddress, serverPort);
	}
}
