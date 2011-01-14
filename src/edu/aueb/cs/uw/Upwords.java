package edu.aueb.cs.uw;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import edu.aueb.cs.uw.core.GameConfigs;
import android.media.SoundPool.OnLoadCompleteListener;

public class Upwords extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        SoundManager.getInstance();
        SoundManager.initSounds(this);
        SoundManager.loadSounds();    
        (SoundManager.sp).setOnLoadCompleteListener(new OnLoadCompleteListener()
        {
        	@Override
        	public void onLoadComplete(SoundPool sp,int s,int status)
        	{
        		SoundManager.playSound(1,1,-1);
        	}
        }
        );
        
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
        		SoundManager.pauseSound(1);
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
        		        	    SoundManager.pauseSound(1);
        		        		SoundManager.cleanup();
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
    
    @Override
    public boolean onCreateOptionsMenu(Menu m) 
    {
        MenuInflater i=getMenuInflater();
        i.inflate(R.menu.options_menu, m);
        
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem mi)
    {
    	switch(mi.getItemId())
    	{
    	    case R.id.new_gameb:
    	    	
    	    	Intent startGameIntent = new Intent(this,ConfigsActivity.class);
        		startActivity(startGameIntent);
        		SoundManager.playSound(1,1,-1);
    	    	break;
    	    	
    	    case R.id.quick_startb:
    	    	
    	    	Intent quickGameIntent = new Intent(this,BoardActivity.class);
        		Bundle b = new Bundle();
                b.putParcelable("edu.aueb.cs.uw.core.GameConfigs",new GameConfigs());
        		quickGameIntent.putExtras(b);
        		startActivity(quickGameIntent);
    	    	break;
    	    	
    	    case R.id.aboutb:
    	    	
    	    	Intent aboutIntent = new Intent(this,AboutActivity.class);
        		startActivity(aboutIntent);
    	    	
    	    	break;
    	    	
    	    case R.id.exitb:
    	    	
    	    	AlertDialog.Builder bu=new AlertDialog.Builder(this);
        		bu.setMessage("Are you sure you want to exit?");
        		bu.setCancelable(false);
        		bu.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
        		{
        		           public void onClick(DialogInterface d, int id) 
        		           {
        		        	   SoundManager.playSound(1,1,-1);
        		                Upwords.this.finish();
        		           }
        		});
        		
        		bu.setNegativeButton("No", new DialogInterface.OnClickListener() 
        		{
        		           public void onClick(DialogInterface d, int id)
        		           {
        		                d.cancel();
        		           }
        		});
        		
        		AlertDialog a=bu.create();
        		a.show();
    	    	
    	    	break;
    	    	
    	    default: 
    	    	
    	    	return super.onOptionsItemSelected(mi);
    	}
    	
		return super.onOptionsItemSelected(mi);
    }
    
    
}