package app.yakun.number;

import app.yakun.number.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class StartActivity extends Activity implements OnClickListener{
	
	private Button start;
	private Button ranking;
	private Button exit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start_layout);

		start = (Button) findViewById(R.id.start);
		ranking = (Button) findViewById(R.id.ranking);
		exit = (Button) findViewById(R.id.exit);
		start.setOnClickListener(this);
		ranking.setOnClickListener(this);
		exit.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		case R.id.start:
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("Please enter your name");
			final EditText input = new EditText(this);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			input.setLayoutParams(lp);
			input.setSingleLine();
			dialog.setView(input);
			dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					MainActivity.initActivity(StartActivity.this, input.getText().toString().trim());
					
				}
			});
			dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					dialog.cancel();
					
				}
			});
			dialog.show();
			break;
		case R.id.ranking:
			Intent intent = new Intent(this, RankActivity.class);
			startActivity(intent);
			break;
		case R.id.exit:
			finish();
			break;
		default:
			break;
		}
		
	}
	
}
