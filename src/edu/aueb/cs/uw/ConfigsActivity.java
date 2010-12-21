
package edu.aueb.cs.uw;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;

public class ConfigsActivity extends TabActivity implements OnClickListener
{		
	View cb1,cb2,cb3,cb4,et1,et2,et3,et4;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.configs);

		    TabHost th = getTabHost();	    
		    
		    th.addTab(th.newTabSpec("tab1").setIndicator("Player 1").setContent(R.id.rl1));
		    th.addTab(th.newTabSpec("tab2").setIndicator("Player 2").setContent(R.id.rl2));
		    th.addTab(th.newTabSpec("tab3").setIndicator("Player 3").setContent(R.id.rl3));
		    th.addTab(th.newTabSpec("tab3").setIndicator("Player 4").setContent(R.id.rl4));
		    
		    th.setCurrentTab(0);
		    
		    cb1=findViewById(R.id.cb1);
		    cb2=findViewById(R.id.cb2);
		    cb3=findViewById(R.id.cb3);
		    cb4=findViewById(R.id.cb4);
		    et1=findViewById(R.id.et1);
		    et2=findViewById(R.id.et2);
		    et3=findViewById(R.id.et3);
		    et4=findViewById(R.id.et4);
		    
		    cb1.setOnClickListener(this);
		    cb2.setOnClickListener(this);
		    cb3.setOnClickListener(this);
		    cb4.setOnClickListener(this);
		    
    }

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		   case R.id.cb1: ; break;
		   case R.id.cb2: ; break;
		   case R.id.cb3: ; break;
		   case R.id.cb4: ; break;
	       
		}
	}

}
