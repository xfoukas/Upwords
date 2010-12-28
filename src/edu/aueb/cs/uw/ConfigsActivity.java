
package edu.aueb.cs.uw;

import java.util.Arrays;
import java.util.LinkedList;
import edu.aueb.cs.uw.core.GameConfigs;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
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
	int [] colorCodes={-1,Color.rgb(255,105,180),Color.rgb(255,140,0),-65536,-16711936,-16776961,-16777216,-256};
	LinkedList<ArrayAdapter<String>> adapter;
	LinkedList<String> colorList;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {		
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.configs);
		    
		    name=new String [4];
		    color=new int[4];  
		    Arrays.fill(color,0);
		    numOfPlayers=0;
		    widget=new View [4][7];
		    adapter=new LinkedList<ArrayAdapter<String>>();
		    colorList=new LinkedList<String>();
		    
		    Resources r=getResources();
		    String [] colors_array=r.getStringArray(R.array.colors_array);
		    
		    colorList.addAll(Arrays.asList(colors_array));
		    /*
		    adapter.add(ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item));
		    adapter.add(ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item));
		    adapter.add(ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item));
		    adapter.add(ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item));*/
		    
		    adapter.add(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,colorList));
		    adapter.add(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,colorList));
		    adapter.add(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,colorList));
		    adapter.add(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,colorList));
		  /*  LinkedList<View> l=null;
		    new ArrayAdapter<View>(this,android.R.layout.simple_spinner_item,l);*/
		    System.out.println("the adapters are set in the list");
		    takeViewReferences();		    
		    
		  
		    adapter.get(0).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    ((Spinner)widget[0][4]).setAdapter(adapter.get(1));
		    
		    
		    adapter.get(1).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    ((Spinner)widget[1][4]).setAdapter(adapter.get(1));		    
            
		    
		    adapter.get(2).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    ((Spinner)widget[2][4]).setAdapter(adapter.get(2));
		    
		    
		    adapter.get(3).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    ((Spinner)widget[3][4]).setAdapter(adapter.get(3));

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
		    
		    for(int i=0;i<4;i++)
		    {
		    	((EditText)widget[i][2]).setText("Player_"+(i+1));
		    	
		    	((Spinner)widget[i][4]).setSelection(i);System.out.println("******************eklithei i setSelection");
			    color[i]=wordToNumberColor((String)adapter.get(i).getItem(i));
			    removeColorFromOthers((String)adapter.get(i).getItem(i),i);			    
		    }		    
		    
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
			   
			   numOfPlayers=0;
			   for(int i=0;i<4;i++)
			   {
				   if(((CheckBox)widget[i][0]).isChecked())
				   {					  
				       name[i]=((EditText)widget[i][2]).getText().toString();					   
					   numOfPlayers=numOfPlayers+1; 
				   }
			   }
			   
			   boolean sameName=false;
			   
			   loop2:
			   for(int i=0;i<4;i++)
			   {
				   for(int j=0;j<4;j++)
				   {
					   if(i!=j&&name[i]!=null&&name[j]!=null&&name[i].equals(name[j]))
					   {
						   sameName=true;
						   break loop2;
					   }
				   }
			   }
			   
			   if(sameName)
			   {
				   showDialog("No players can have the same name.");
				   break;
			   }
			   else
			   {
				   boolean wrongName=false;
				   
				   loop1:
				   for(int i=0;i<4;i++)
				   {
					   if(((CheckBox)widget[i][0]).isChecked()&&(((EditText)widget[i][2]).getText().toString()).equals("")||(((EditText)widget[i][2]).getText().toString()).indexOf(' ')!=-1)
					   {
						   showDialog("You did not define some player's name or you included a space in it.");
						   wrongName=true;
						   break loop1;
					   }
				   }
				   
				   if(!wrongName)
				   {
					   if(numOfPlayers<2)
					   {
						   showDialog("At least two players must be enabled.");
						   
						   break;
					   }
					   else
					   {
						   nameForConstructor=new String [numOfPlayers];
						   colorForConstructor=new int [numOfPlayers];
						   Arrays.fill(colorForConstructor,0);
						   
						   for(int i=0;i<numOfPlayers;i++)
						   {
							   for(int j=0;j<4;j++)
							   {
								   if(((CheckBox)widget[j][0]).isChecked()&&name[j]!=null&&nameForConstructor[i]==null)
								   {
									   nameForConstructor[i]=name[j];
									   name[j]=null;
								   }
								   
								   if(((CheckBox)widget[j][0]).isChecked()&&color[j]!=0&&colorForConstructor[i]==0)
								   {
									   colorForConstructor[i]=color[j];
									   color[j]=0;
								   }
							   }
						   }						   
						   
						   
						   Intent gameIntent = new Intent(this,BoardActivity.class);
					      		
				       	   Bundle b = new Bundle();
				       	   
				           b.putParcelable("edu.aueb.cs.uw.core.GameConfigs",new GameConfigs(this.numOfPlayers,nameForConstructor,colorForConstructor));
				               
				           Arrays.fill(name,null);
				           Arrays.fill(color,0);
				               
				       	   gameIntent.putExtras(b);
				       	   startActivity(gameIntent);						   					   
					   }
				   }
				   
				   if(wrongName)
				   {
					   break;
				   }
			   }			   
			   
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
		
		int colorCode=wordToNumberColor(colorString);
		
		int id=p.getId();
		
		switch(id)
		{
		    case R.id.spinner1: 
		    restorePreviousColorToOthers(0);	
		    color[0]=colorCode;
		    removeColorFromOthers(colorString,0);
		    
		    break;
		    case R.id.spinner2: 
		    restorePreviousColorToOthers(1);	
		    color[1]=colorCode; 
		    removeColorFromOthers(colorString,1);
		    break;
		    case R.id.spinner3:
		    restorePreviousColorToOthers(2);	
		    color[2]=colorCode; 
		    removeColorFromOthers(colorString,2);
		    break;
		    case R.id.spinner4: 
		    restorePreviousColorToOthers(3);	
		    color[3]=colorCode; 
		    removeColorFromOthers(colorString,3);
		    break;
		}
	}
	
	private int wordToNumberColor(String colorString)
	{
        int colorCode=1;
		
		for(int i=0;i<8;i++)
		{
			if(colorString.equals(colors[i]))
			{
				colorCode=colorCodes[i];
			}
		}
		
		return colorCode;
	}
	
	private void removeColorFromOthers(String color,int spinner)
	{
		for(int i=0;i<4;i++)
		{
			if(i!=spinner)
			{
				adapter.get(i).remove(color);
			}
		}
	}
	
	private void restorePreviousColorToOthers(int spinner)
	{
		int index=0;
		
		for(int i=0;i<8;i++)
		{
			if(colorCodes[i]==color[spinner])
			{
				index=i;
			}
		}
		
		for(int i=0;i<4;i++)
		{
			if(i!=spinner)
			{
				adapter.get(i).add(colors[index]);
			}
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) 
	{
		
	}
	
	private void tabEnabled(boolean enable,int tabId)
	{
		for(int i=1;i<6;i++)
		{		
			if(i==5&&!enable)
			{
				if(allOtherTabsUnchecked(tabId))
				{					
					for(int j=0;j<4;j++)
					{
						widget[j][i].setEnabled(enable);
					}
				}
			}
			else
			{
				widget[tabId][i].setEnabled(enable);
			}
		}
		
		if(enable)
		{
			widget[tabId][2].setBackgroundColor(Color.WHITE);
			
			for(int i=0;i<4;i++)
			{
				if(i!=tabId)
				{
				    widget[i][5].setEnabled(true);
				}
			}
		}
		else
		{
			widget[tabId][2].setBackgroundColor(Color.GRAY);
		}
	}
	private boolean allOtherTabsUnchecked(int tabId)
	{
		for(int i=0;i<4;i++)
		{
			if(i!=tabId&&((CheckBox)widget[i][0]).isChecked())
			{
				return false;
			}
		}
		
		return true;
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
	
	private void showDialog(String text)
	{
		AlertDialog.Builder b=new AlertDialog.Builder(this);
		   b.setMessage(text);
		   b.setCancelable(false);
		   b.setPositiveButton("OK",new DialogInterface.OnClickListener()
		   {
			   public void onClick(DialogInterface d,int id)
			   {
				   d.cancel();
			   }
		   });
		   
		   AlertDialog a=b.create();
		   a.show();
	}
}
