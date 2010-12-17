package edu.aueb.cs.uw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Upwords extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        Button NewGameButton = (Button)findViewById(R.id.new_game);
        NewGameButton.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent StartGameIntent = new Intent(Upwords.this,Configs.class);
        		startActivity(StartGameIntent);
        	}
        });
        
        Button QuickStartButton = (Button)findViewById(R.id.quick_start);
        QuickStartButton.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent StartGameIntent = new Intent(Upwords.this,Board.class);
        		startActivity(StartGameIntent);
        	}
        });
        
        Button AboutButton = (Button)findViewById(R.id.about);
        AboutButton.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent StartGameIntent = new Intent(Upwords.this,About.class);
        		startActivity(StartGameIntent);
        	}
        });
        
        Button ExitButton = (Button)findViewById(R.id.exit);
        ExitButton.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		System.exit(0);
        	}
        });
    }
}