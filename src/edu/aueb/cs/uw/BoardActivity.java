package edu.aueb.cs.uw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import edu.aueb.cs.uw.core.GameConfigs;
import edu.aueb.cs.uw.core.GameEngine;
import edu.aueb.cs.uw.core.Player;

public class BoardActivity extends Activity 
{
	
	private GameEngine ge;
	private GameConfigs gc;
	private BoardView bv;	
	private PopupWindow popUp;
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        Bundle extras=getIntent().getExtras();
        gc=extras.getParcelable("edu.aueb.cs.uw.core.GameConfigs");
        ge=new GameEngine(gc);
        setContentView(R.layout.board);
        bv=(BoardView)findViewById(R.id.board_view);
        ge.beginGame();
        bv.setGameEngine(ge);        
      
        //bv.setContext(setApplicationContext()); 
        
        ImageButton ExitButton = (ImageButton)findViewById(R.id.exit_button_horizontal);
        ExitButton.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		AlertDialog.Builder builder = new AlertDialog.Builder(BoardActivity.this);
        		builder.setMessage("Are you sure you want to quit current game?")
        		       .setCancelable(false)
        		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        		           public void onClick(DialogInterface dialog, int id) {
        		                BoardActivity.this.finish();
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
        
        
        ImageButton GiveUpTurn = (ImageButton)findViewById(R.id.giveturn_button_horizontal);
        GiveUpTurn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ge.giveUpTurn();
				if(ge.isEndOfGame()){
					String message;
					Player p=ge.endGame();
					if(p==null)
						message="The game is a draw";
					else
						message="Player "+p.getNickname()+" wins!!!";
					AlertDialog.Builder builder = new AlertDialog.Builder(BoardActivity.this);
	        		builder.setMessage(message)
	        		       .setCancelable(false)
	        		       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        		           public void onClick(DialogInterface dialog, int id) {
	        		                BoardActivity.this.finish();
	        		           }
	        		       });
	        		AlertDialog alert = builder.create();
	        		alert.show();
				}
			}
		});
        
        
        ImageButton UndoAll = (ImageButton)findViewById(R.id.undo_button_horizontal);
        UndoAll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ge.undoAll();
			}
		});
        
        
        ImageButton EndTurn = (ImageButton)findViewById(R.id.endturn_button_horizontal);
        bv.setEndTurn(EndTurn);
        EndTurn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder b = new AlertDialog.Builder(BoardActivity.this);
        		b.setMessage("Finish turn?")
        		       .setCancelable(false)
        		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        		           public void onClick(DialogInterface dialog, int id) {
        		        	   ge.nextRound();
        						if(ge.isEndOfGame()){
        							String message;
        							Player p=ge.endGame();
        							if(p==null)
        								message="The game is a draw";
        							else
        								message="Player "+p.getNickname()+" wins!!!";
        							AlertDialog.Builder builder = new AlertDialog.Builder(BoardActivity.this);
        			        		builder.setMessage(message)
        			        		       .setCancelable(false)
        			        		       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        			        		           public void onClick(DialogInterface dialog, int id) {
        			        		                BoardActivity.this.finish();
        			        		           }
        			        		       });
        			        		AlertDialog alrt = builder.create();
        			        		alrt.show();
        						}
        		           }
        		       })
        		       .setNegativeButton("No", new DialogInterface.OnClickListener() {
        		           public void onClick(DialogInterface dialog, int id) {
        		                dialog.cancel();
        		           }
        		       });
        		AlertDialog alert = b.create();
        		alert.show();	
			}
		});
        
        ImageButton switchTile = (ImageButton)findViewById(R.id.switch_button_horizontal);
        switchTile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				bv.setSwitchMode(true);
				ge.undoAll();
				Dimensions dims=bv.getDimensions();
				LayoutInflater inflater=(LayoutInflater)BoardActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View pview=inflater.inflate(R.layout.popup_message, (ViewGroup)findViewById(R.layout.board));
				popUp =new PopupWindow(pview, 3*dims.getTotalWidth()/4,dims.getBoardheight()/3,false);
				popUp.setTouchable(true);
				popUp.setOutsideTouchable(true);
				
				Button popupCancel = (Button)pview.findViewById(R.id.cancel_popup);
		        popupCancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						popUp.dismiss();
					}
				});
		        bv.setPopupWindow(popUp);
		        popUp.showAtLocation(findViewById(R.id.board_view), Gravity.CENTER, 0, 0);
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
        		this.finish();
    	    	break;
    	    	
    	    case R.id.quick_startb:
    	    	
    	    	Intent quickGameIntent = new Intent(this,BoardActivity.class);
        		Bundle b = new Bundle();
                b.putParcelable("edu.aueb.cs.uw.core.GameConfigs",new GameConfigs());
        		quickGameIntent.putExtras(b);
        		startActivity(quickGameIntent);
    	    	this.finish();
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
        		                BoardActivity.this.finish();
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