package edu.aueb.cs.uw;


import edu.aueb.cs.uw.R;
import edu.aueb.cs.uw.core.GameEngine;
import android.app.Activity;
import android.os.Bundle;

public class Board extends Activity 
{
	GameEngine ge;
	BoardView bv;	
	
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
        
       /* bv=(BoardView)findViewById(R.id.widget10);
      
        
        Bundle extras=getIntent().getExtras();        
        
        String [] data=extras.getStringArray("data");
        
        if(data.length==1)
        {
        	this.ge=new GameEngine();
        }
        else
        {
        	int numPlayers=data.length;
        	String [] names=new String[numPlayers];
        	int [] colors=new int[numPlayers];
        	
        	for(int i=0;i<numPlayers;i++)
        	{
        		StringTokenizer st=new StringTokenizer(data[i],"-");
        		
        		names[i]=st.nextToken();
        		
        		colors[i]=Integer.parseInt(st.nextToken());
        	}
        	
            this.ge=new GameEngine(new GameConfigs(numPlayers,names,colors));
        }
        	
        bv.setGameEngine(ge);	
       
        bv.setContext(getApplicationContext()); */
        
    }
    
   /* @Override
    protected void onStart()
    {
    	ge.beginGame();
    	
    	//kai polla akoma
    }*/
}