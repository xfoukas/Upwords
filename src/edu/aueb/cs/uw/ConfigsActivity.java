
package edu.aueb.cs.uw;

import edu.aueb.cs.uw.core.GameConfigs;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.AdapterView.OnItemSelectedListener;

public class ConfigsActivity extends TabActivity implements OnClickListener ,OnItemSelectedListener
{		
	View [][] widget;
	int numOfPlayers;
	String [] name,nameForConstructor;
	int [] color,colorForConstructor;
	String [] colors={"White","Pink","Orange","Red","Green","Blue","Black","Yellow"};
	int [] colorCodes={-1,Color.rgb(255,105,180),Color.rgb(255,140,0),-65536,-1671193,-1677696,-16777216,-256};
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {		
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.configs);
		    
		    name=new String [4];
		    color=new int[4];  
		    numOfPlayers=0;
		    widget=new View [4][7];
		    
		    takeViewReferences();		    
		    
		    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);
		    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    ((Spinner)widget[0][4]).setAdapter(adapter1);
		    
		    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);
		    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    ((Spinner)widget[1][4]).setAdapter(adapter2);		    
            
		    ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);
		    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    ((Spinner)widget[2][4]).setAdapter(adapter3);
		    
		    ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);
		    adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    ((Spinner)widget[3][4]).setAdapter(adapter4);

		    TabHost th = getTabHost();	    
		    
		    th.addTab(th.newTabSpec("tab1").setIndicator("Player 1").setContent(R.id.rl1));
		    th.addTab(th.newTabSpec("tab2").setIndicator("Player 2").setContent(R.id.rl2));
		    th.addTab(th.newTabSpec("tab3").setIndicator("Player 3").setContent(R.id.rl3));
		    th.addTab(th.newTabSpec("tab4").setIndicator("Player 4").setContent(R.id.rl4));
		    
		    th.setCurrentTab(0);		    
		    
		    tabEnabled(false,0);
		    tabEnabled(false,1);
		    tabEnabled(false,2);
		    tabEnabled(false,3);		    
		    
		    widget[0][0].setOnClickListener(this);
		    widget[1][0].setOnClickListener(this);
		    widget[2][0].setOnClickListener(this);
		    widget[3][0].setOnClickListener(this);
		    ((Spinner)widget[0][4]).setOnItemSelectedListener(this);
		    ((Spinner)widget[1][4]).setOnItemSelectedListener(this);
		    ((Spinner)widget[2][4]).setOnItemSelectedListener(this);
		    ((Spinner)widget[3][4]).setOnItemSelectedListener(this);
		    widget[0][5].setOnClickListener(this);
		    widget[0][6].setOnClickListener(this);
		    widget[1][5].setOnClickListener(this);
		    widget[1][6].setOnClickListener(this);
		    widget[2][5].setOnClickListener(this);
		    widget[2][6].setOnClickListener(this);
		    widget[3][5].setOnClickListener(this);
		    widget[3][6].setOnClickListener(this);		    
    }

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		   case R.id.cb1:
			   
			   if(((CheckBox)widget[0][0]).isChecked())
			   {
				   tabEnabled(true,0);
			   }
			   else
			   {
				   color[0]=0;
				   
				   tabEnabled(false,0);
			   }
			   
			   break;
		   case R.id.cb2:
			   
			   if(((CheckBox)widget[1][0]).isChecked())
			   {
				   tabEnabled(true,1);
			   }
			   else
			   {
				   color[1]=0;
				   
				   tabEnabled(false,1);
			   }
			   
			   break;
		   case R.id.cb3: 
			   
			   if(((CheckBox)widget[2][0]).isChecked())
			   {
				   tabEnabled(true,2);
			   }
			   else
			   {
				   color[2]=0;
				   
				   tabEnabled(false,2);
			   }
			   
			   break;
		   case R.id.cb4:
			   
			   if(((CheckBox)widget[3][0]).isChecked())
			   {
				   tabEnabled(true,3);
			   }
			   else
			   {
				   color[3]=0;
				   
				   tabEnabled(false,3);
			   }
			   
			   break;
		   case R.id.begin_game1:
		   case R.id.begin_game2:
		   case R.id.begin_game3:
		   case R.id.begin_game4: 
			   
			   for(int i=0;i<4;i++)
			   {
				   if(((CheckBox)widget[i][0]).isChecked())
				   {
					   name[i]=((EditText)widget[i][2]).getText().toString();
					   numOfPlayers=numOfPlayers+1;
				   }
			   }
			   
			   nameForConstructor=new String [numOfPlayers];
			   colorForConstructor=new int [numOfPlayers];
			   
			   for(int i=0;i<numOfPlayers;i++)
			   {
				   for(int j=0;j<4;j++)
				   {
					   if(name[j]!=null)
					   {
						   nameForConstructor[i]=name[j];
						   name[j]=null;
					   }
					   
					   if(color[j]!=0)
					   {
						   colorForConstructor[i]=color[j];
						   color[j]=0;
					   }
				   }
			   }
			   
			   Intent gameIntent = new Intent(this,BoardActivity.class);
       		
       		   Bundle b = new Bundle();
               b.putParcelable("edu.aueb.cs.uw.core.GameConfigs",new GameConfigs(this.numOfPlayers,nameForConstructor,colorForConstructor));
       		   gameIntent.putExtras(b);
       		   startActivity(gameIntent);
			   break;
		   case R.id.cancel1:
		   case R.id.cancel2:
		   case R.id.cancel3:
		   case R.id.cancel4: this.finish(); 
		       break;
	       
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> p, View v, int place,long row) 
	{
		String colorString=p.getItemAtPosition(place).toString();
		
		int colorCode=1;
		
		for(int i=0;i<8;i++)
		{
			if(colorString.equals(colors[i]))
			{
				colorCode=colorCodes[i];
			}
		}
		
		int id=p.getId();
		
		switch(id)
		{
		    case R.id.spinner1: color[0]=colorCode; break;
		    case R.id.spinner2: color[1]=colorCode; break;
		    case R.id.spinner3: color[2]=colorCode; break;
		    case R.id.spinner4: color[3]=colorCode; break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) 
	{
		
	}
	
	private void tabEnabled(boolean enable,int tabId)
	{
		for(int i=1;i<7;i++)
		{
			widget[tabId][i].setEnabled(enable);
		}
		
		if(enable)
		{
			widget[tabId][2].setBackgroundColor(Color.WHITE);
		}
		else
		{
			widget[tabId][2].setBackgroundColor(Color.GRAY);
		}
	}
	
	private void takeViewReferences()
	{
		widget[0][4] =(Spinner)findViewById(R.id.spinner1);
	    widget[1][4] =(Spinner)findViewById(R.id.spinner2);
	    widget[2][4] =(Spinner)findViewById(R.id.spinner3);
	    widget[3][4] =(Spinner)findViewById(R.id.spinner4);
		widget[0][0]=findViewById(R.id.cb1);
	    widget[1][0]=findViewById(R.id.cb2);
	    widget[2][0]=findViewById(R.id.cb3);
	    widget[3][0]=findViewById(R.id.cb4);
	    widget[0][2]=findViewById(R.id.et1);
	    widget[1][2]=findViewById(R.id.et2);
	    widget[2][2]=findViewById(R.id.et3);
	    widget[3][2]=findViewById(R.id.et4);
	    widget[0][5]=findViewById(R.id.begin_game1);
	    widget[0][6]=findViewById(R.id.cancel1);
	    widget[1][5]=findViewById(R.id.begin_game2);
	    widget[1][6]=findViewById(R.id.cancel2);
	    widget[2][5]=findViewById(R.id.begin_game3);
	    widget[2][6]=findViewById(R.id.cancel3);
	    widget[3][5]=findViewById(R.id.begin_game4);
	    widget[3][6]=findViewById(R.id.cancel4);
	    widget[0][1]=findViewById(R.id.tv1);
	    widget[0][3]=findViewById(R.id.tv1b);
	    widget[1][1]=findViewById(R.id.tv2);
	    widget[1][3]=findViewById(R.id.tv2b);
	    widget[2][1]=findViewById(R.id.tv3);
	    widget[2][3]=findViewById(R.id.tv3b);
	    widget[3][1]=findViewById(R.id.tv4);
	    widget[3][3]=findViewById(R.id.tv4b);
	}
}
