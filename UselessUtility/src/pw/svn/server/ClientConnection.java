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

	@Override
	public void run() {
		this.initializeClientConnection();
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
}
