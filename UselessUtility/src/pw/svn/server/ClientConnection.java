package pw.svn.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Represents the connection between the server and each user.
 * @author Theneva
 * @version 1.0
 */
public class ClientConnection implements Runnable {
	
	private final Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	public ClientConnection(Socket socket) {
		this.socket = socket;
		new Thread(this).start();
	}

	public void run() {
		this.initializeClientConnection();
		this.enterChat();
	}
	
	private void initializeClientConnection() {
		
		try {
			this.output = new ObjectOutputStream(this.socket.getOutputStream());
			this.output.flush();
			this.input = new ObjectInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Enters the chat and keeps the connection alive.
	 */
	private void enterChat() {
		// this.readMessage();
	}

	/**
	 * Reads a message from the client.
	 * @return the message.
	 */
	private String readMessage() {
		String data = null;

		try {
			data = (String) this.input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * Sends a message to the client.
	 * @param message the message.
	 */
	private void sendMessage(String message) {
		try {
			this.output.writeObject(message);
			this.output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a message ending with a line break to the client.
	 * @param message the message.
	 */
	private void sendMessageln(String message) {
		this.sendMessage(message.concat(System.getProperty("line.separator")));
	}
}

