package edu.aueb.cs.uw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import edu.aueb.cs.uw.core.GameConfigs;
import edu.aueb.cs.uw.core.GameEngine;
import edu.aueb.cs.uw.core.Player;

public class BoardActivity extends Activity 
{
	private GameEngine ge;
	private GameConfigs gc;
	private BoardView bv;	
    
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
        
        
    }
    
}