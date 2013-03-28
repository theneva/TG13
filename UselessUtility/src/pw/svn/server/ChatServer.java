package pw.svn.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import pw.svn.util.MessageManipulator;
import pw.svn.util.NameGenerator;

/**
 * A useless chat server.
 * @author Theneva
 * @version 1.0
 */
public class ChatServer implements Runnable {
	
	private transient List<ClientConnection> clients;
	private transient int port;
	private transient ResourceBundle messages;
	
	public ChatServer(String ip, int port, ResourceBundle messages) {
		this.clients = new ArrayList<ClientConnection>();
		this.port = port;
		
		this.messages = messages;
		
		new Thread(this).start();
	}

	public void run() {
		this.startServer();
	}
	
	private void startServer() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(this.port);
			
			for (;;) {

				System.out.println(this.messages.getString("listeningForConnections"));
				
				final Socket clientSocket = serverSocket.accept();

				ClientConnection connection = new ClientConnection(this.clients.size(), clientSocket);
				this.clients.add(connection);
				
				System.out.printf("%s %d%n", this.messages.getString("connectionAdded"), this.clients.size() - 1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Represents the connection between the server and each user.
	 * @author Theneva
	 * @version 1.0
	 */
	private class ClientConnection implements Runnable {
		
		private final int id;
		private final String name;
		private final Socket socket;
		private ObjectOutputStream output;
		private ObjectInputStream input;
		
		public ClientConnection(int id, Socket socket) {
			this.id = id;
			this.name = NameGenerator.getNewName();
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
				
			} catch (IOException e){
				e.printStackTrace();
			}
		}

		/**
		 * Enters the chat and keeps the connection alive.
		 */
		private void enterChat() {

			this.sendToAllf("\t%s %s", this.name, messages.getString("hasEnteredChat"));
			
			for (;;) {
				String message = this.readMessage();
				if (message == null) {
					this.leaveChat();
					System.out.println("leave");
					break;
				}
				else
					this.sendToAllf("%s: %s", this.name, MessageManipulator.manipulate(message));
				System.out.println("end");
			}
		}

		/**
		 * Notify the server that the client is leaving and remove it from the list.
		 */
		private void leaveChat() {
			clients.remove(this);
			sendToAllf("\t%s %s", this.name, messages.getString("hasLeftChat"));
			
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
				//this.leaveChat();
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
		 * Sends a message to all connected clients.
		 * @param message the message to send.
		 */
		private void sendToAll(String message) {
			if (message == null) return;
			
			for (ClientConnection client : clients) {
				client.sendMessage(message);
			}
		}

		private void sendToAllf(String s, Object... o) {
			String message = String.format(s, o);
			this.sendToAll(message);
		}
		
		public boolean equals(Object o) {
			
			if (!(o instanceof ClientConnection)) return false;
			if (o == this) return true;
			
			return this.id == ((ClientConnection) o).id;
		}
	}
}
