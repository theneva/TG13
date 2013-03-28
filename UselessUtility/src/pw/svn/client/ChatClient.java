package pw.svn.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A client for connecting to the chat server.
 * @author Theneva
 * @version 1.0
 */
public class ChatClient {

	private Socket serverConnection;
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	private JFrame ui;
	
	public ChatClient(String serverAddress, int serverPort) {

		try {
			this.serverConnection = new Socket(serverAddress, serverPort);
			this.output = new ObjectOutputStream(this.serverConnection.getOutputStream());
			this.input = new ObjectInputStream(this.serverConnection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.initializeUI();
	}
	
	private void initializeUI() {

		this.ui = new JFrame("Useless chat client");
		
		this.ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextArea textAreaContent = new JTextArea();
		textAreaContent.setEditable(false);
		this.ui.add(new JScrollPane(textAreaContent));
		
		final JTextField textFieldMessage = new JTextField();

		textFieldMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage(textFieldMessage.getText());
				textFieldMessage.setText("");
			}
		});

		this.ui.add(textFieldMessage, BorderLayout.SOUTH);
		
		this.ui.setLocationRelativeTo(null);
		this.ui.setSize(600, 400);
		this.ui.setVisible(true);
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
