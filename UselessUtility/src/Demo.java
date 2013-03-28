

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Demonstrates the chat server.
 * @author Theneva
 *
 */
public class Demo extends JFrame {
	public static void main(String[] args) {
		new Demo();
	}
	
	public Demo() {
		super("Hello");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		final JTextArea txa = new JTextArea();
		
		add(txa);
		
		txa.append("Hello");
		
//		txa.setFont(new Font(null, Font.BOLD, 15));
//		
//		txa.append("Banana");
		
		JButton btn = new JButton("KNAPP FOR FAEN");
		final MYINT fontsize = new MYINT(15);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fontsize.add(5);
				txa.setFont(new Font(null, Font.BOLD, fontsize.i));
			}
		});
		
		add(btn, BorderLayout.SOUTH);
		
		setSize(400, 400);
		setVisible(true);
	}
	
	class MYINT {
		int i = 0;
		public MYINT(int i) {
			this.i = i;
		}
		
		public void add(int i) {
			this.i += i;
		}
	}
}
