package edu.aueb.cs.uw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class InstructionsActivity extends Activity
{
	private Button backButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
        this.backButton=(Button)this.findViewById(R.id.back_instructions);
        this.backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
    }
}
