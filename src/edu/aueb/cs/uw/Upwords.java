package edu.aueb.cs.uw;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import edu.aueb.cs.uw.core.GameConfigs;

public class Upwords extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        Button NewGameButton = (Button)findViewById(R.id.new_game);
        NewGameButton.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent startGameIntent = new Intent(Upwords.this,ConfigsActivity.class);
        		startActivity(startGameIntent);
        	}
        	
        });
        
        /*Manages the quick game*/
        Button QuickStartButton = (Button)findViewById(R.id.quick_start);
        QuickStartButton.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent quickGameIntent = new Intent(Upwords.this,BoardActivity.class);
        		/*pass the default game configs to the activity*/
        		Bundle b = new Bundle();
                b.putParcelable("edu.aueb.cs.uw.core.GameConfigs",new GameConfigs());
        		quickGameIntent.putExtras(b);
        		startActivity(quickGameIntent);
        	}
        	
        });
        
        Button AboutButton = (Button)findViewById(R.id.about);
        AboutButton.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent aboutIntent = new Intent(Upwords.this,AboutActivity.class);
        		startActivity(aboutIntent);
        	}
        	
        });
        
        Button ExitButton = (Button)findViewById(R.id.exit);
        ExitButton.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		AlertDialog.Builder builder = new AlertDialog.Builder(Upwords.this);
        		builder.setMessage("Are you sure you want to exit?")
        		       .setCancelable(false)
        		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        		           public void onClick(DialogInterface dialog, int id) {
        		                Upwords.this.finish();
        		           }
        		       })
        		       .setNegativeButton("No", new DialogInterface.OnClickListener() {
        		           public void onClick(DialogInterface dialog, int id) {
        		                dialog.cancel();
        		           }
        		       });
        		AlertDialog alert = builder.create();
        		alert.show();
        	}
        	
        });
    }
    
    
}