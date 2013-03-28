

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import pw.svn.client.ChatClient;
import pw.svn.server.ChatServer;

/**
 * Demonstrates the chat server.
 * @author Theneva
 *
 */
public class Demo {
//	public static void main(String[] args) {
//		
//		if (args.length != 2) {
//			new Demo("en", "US");
//		} else {
//			new Demo(args[0], args[1]);
//		}
//	}
	
	private JFrame frame;
	private JButton btnAddClient, btnAddServer;
	private JLabel lblClient, lblServer;
	final String serverAddress = "localhost";
	final int port;
	Locale currentLocale;
	ResourceBundle messages;
	public Demo(String language, String country) {
		
		port = 8000;
		currentLocale = new Locale(language, country);
		messages = ResourceBundle.getBundle("MessageBundle", currentLocale);
		
		
		initGUI();
		initListeners();
//		new ChatServer(serverAddress, port, messages);
//		
//		try {
//			Thread.sleep(2000);
//			
//			for (int i = 0; i < 3; i++) {
//				new ChatClient(serverAddress, port, messages, new Point(40 + ChatClient.DEFAULT_WIDTH * i, 40));
//				Thread.sleep(500);
//			}
//			
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	public void initGUI(){
		frame = new JFrame("Pointerless Admin");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLayout(new GridLayout(2, 2));
		
		btnAddClient = new JButton("Add client");
		btnAddServer = new JButton("Add server");
		lblClient = new JLabel("Legg til ny klient: ");
		lblServer = new JLabel("Legg til ny server: ");
		
		frame.add(lblServer);
		frame.add(btnAddServer);
		frame.add(lblClient);
		frame.add(btnAddClient);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void initListeners(){
		btnAddClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ChatClient(serverAddress, port, messages, new Point(40 + ChatClient.DEFAULT_WIDTH, 40));
				
			}
		});
		btnAddServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ChatServer(serverAddress, port, messages);
				
			}
		});
		
	}
	
	public static void main(String[] args) {
		new Demo("no", "NO");
	}
}
