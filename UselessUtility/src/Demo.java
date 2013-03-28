

import pw.svn.server.ChatServer;

/**
 * Demonstrates the chat server.
 * @author Theneva
 *
 */
public class Demo {
	public static void main(String[] args) {
		new ChatServer("localhost", 8000);
	}
}
