package edu.aueb.cs.uw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import edu.aueb.cs.uw.core.GameConfigs;
import edu.aueb.cs.uw.core.GameEngine;

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
        
        View [] v={findViewById(R.id.text_view_one),findViewById(R.id.text_view_two),findViewById(R.id.text_view_three),findViewById(R.id.text_view_four)};
           
        bv.setTextView1(v[0]);
        bv.setTextView2(v[1]);
        bv.setTextView3(v[2]);
        bv.setTextView4(v[3]);       
        
        ImageButton ExitButton = (ImageButton)findViewById(R.id.exit_button_horizontal);
        ExitButton.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		System.exit(0);
        	}
        	
        });
    }
    
}