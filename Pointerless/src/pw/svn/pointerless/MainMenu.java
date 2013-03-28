package pw.svn.pointerless;

import java.util.Locale;

import pw.svn.pointerless.chat.ChatActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainMenu extends Activity {
	
	private Button btnStartChat;
	private RadioButton rbEnglishChoice, rbNorwegianChoice;
	private EditText etIP;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initGui();
        initListeners();
        Configuration c = new Configuration(getResources().getConfiguration());
        if(c.locale.equals(Locale.ITALIAN))
        	rbNorwegianChoice.setChecked(true);
        else
        	rbEnglishChoice.setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }
    
    public void initGui(){
    	btnStartChat = (Button) findViewById(R.id.btnStartChat);
    	rbEnglishChoice = (RadioButton) findViewById(R.id.rbEnglishChoice);
    	rbNorwegianChoice = (RadioButton) findViewById(R.id.rbNorwegianChoice);
    	etIP = (EditText) findViewById(R.id.etIP);
    }
    
    public void initListeners(){
    	btnStartChat.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(isInputValidIP(etIP.getText().toString())){
					Intent intent = new Intent(getBaseContext(), ChatActivity.class);
					intent.putExtra("IP", etIP.getText().toString());
					startActivity(intent);
				}
				
			}
			public boolean isInputValidIP(String input){
				return input.matches("[1-9]{1,3}\\.[1-9]{1,3}\\.[1-9]{1,3}\\.[1-9]{1,3}");
			}
		});
    	rbEnglishChoice.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(rbEnglishChoice.isChecked()){
					Configuration c = new Configuration(getResources().getConfiguration());
	
				       c.locale = Locale.ENGLISH;
	
				      getResources().updateConfiguration(c, getResources().getDisplayMetrics());
	
				      Intent intent = getIntent();
	
				      overridePendingTransition(0, 0);
	
				      intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	
				      finish();
	
				      overridePendingTransition(0, 0);
	
				      startActivity(intent);
				}
				
			}
		});
    	rbNorwegianChoice.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(rbNorwegianChoice.isChecked()){
					Configuration c = new Configuration(getResources().getConfiguration());
	
				       c.locale = Locale.ITALIAN;
	
				      getResources().updateConfiguration(c, getResources().getDisplayMetrics());
	
				      Intent intent = getIntent();
	
				      overridePendingTransition(0, 0);
	
				      intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	
				      finish();
	
				      overridePendingTransition(0, 0);
	
				      startActivity(intent);
				}
			}
		});
    }
}
