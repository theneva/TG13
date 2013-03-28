package at.mads.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatActivity extends Activity {
	
	private Socket serverConnection;
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	private Button btnSend;
	private TextView tvOutput;
	private EditText etInput;
	private LongOperation lo;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeUI();
        lo = new LongOperation();
        lo.execute("");
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    private void initializeUI() {
    	btnSend = (Button) findViewById(R.id.btnSend);
    	tvOutput = (TextView) findViewById(R.id.textArea);
    	etInput = (EditText) findViewById(R.id.etInput);
    	btnSend.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE); 

				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                           InputMethodManager.HIDE_NOT_ALWAYS);
				if(etInput.getText().toString() != "") {
					lo.sendMessage(etInput.getText().toString());
					etInput.setText("");
				}
			}
		});
    }
    
    private class LongOperation extends AsyncTask<String, Void, Void>{

		@Override
		protected Void doInBackground(String... params) {
			this.startClient("151.216.74.138", 8000);
			
			return null;
		}
		private void startClient(String serverAddress, int serverPort) {
			System.out.println("start");
			try {
				serverConnection = new Socket(serverAddress, serverPort);
				System.out.println("con");
				output = new ObjectOutputStream(serverConnection.getOutputStream());
				input = new ObjectInputStream(serverConnection.getInputStream());
				System.out.println("streams");
				for (;;) {
					this.displayMessage(this.readMessage());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				this.displayMessage("Shutting down."); // TODO internationalization
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.exit(0);
			}
		}
	    private void displayMessage(final String message) {
	    	runOnUiThread(new Runnable() {
	    	     public void run() {

	    	    	 tvOutput.append(message.concat(System.getProperty("line.separator")));

	    	    }
	    	});
	    	
		}
	    private String readMessage() {
			
			String message = null;
			
			try {
				message = (String) input.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return message;
		}
	    public void sendMessage(String message) {
			try {
				output.writeObject(message.concat(" "));
				output.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
    	
    }
}
