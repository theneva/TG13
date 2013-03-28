package pw.svn.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import pw.svn.ui.UserInterface;

/**
 * A client for connecting to the chat server.
 * @author Theneva
 * @version 1.0
 */
public class ChatClient {

	private Socket serverConnection;
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	private UserInterface ui;
	
	public ChatClient(String serverAddress, int serverPort) {

		try {
			this.serverConnection = new Socket(serverAddress, serverPort);
			this.output = new ObjectOutputStream(this.serverConnection.getOutputStream());
			this.input = new ObjectInputStream(this.serverConnection.getInputStream());
			
			this.ui = new UserInterface();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads a message (String) from the server.
	 * @return the message from the server.
	 */
	private String readMessage() {
		
		String message = null;
		
		try {
			message = (String) this.input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
	public void sendMessage(String message) {
		try {
			this.output.writeObject(message);
			this.output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
