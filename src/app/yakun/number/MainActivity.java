package app.yakun.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.yakun.number.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private String username;
	
	private String oNumber;
	
	private EditText gNumber;
	
	private ListView records;
	
	private Integer count = 1;
	
	private Button check;
	
	private Button guide;
	
	private AttemptAdapter adapter;
	
	private List<Attempt> attempts = new ArrayList<Attempt>();
	
	private ArrayList<Rank> ranks;
	
	public static void initActivity(Context context, String result){
		
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("name", result);
		context.startActivity(intent);
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_layout);
		
		records = (ListView) findViewById(R.id.list_view);
		adapter = new AttemptAdapter(this, R.layout.list_item, attempts);
		records.setAdapter(adapter);
		
		username = getIntent().getStringExtra("name");
		if(username.equals("")){
			username = "Anonymous";
		}
		
		oNumber = generateNum();
		gNumber = (EditText) findViewById(R.id.number);
		check = (Button) findViewById(R.id.confirm);
		guide = (Button) findViewById(R.id.tip);
		
		check.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String guess = gNumber.getText().toString();
				if(guess.length() != 4){
					Toast.makeText(MainActivity.this, "4 digits please...", Toast.LENGTH_SHORT).show();
					return;
				}
				String hint = getHint(guess);
				
				if(hint.equals("4A0B")){		
					saveData();
					youWin();
				}else{
					attempts.add(new Attempt((count++).toString(), guess, hint));
					adapter.notifyDataSetChanged();
					records.setSelection(attempts.size());
					gNumber.setText("");
				}
				
			}
		});
		
		guide.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder tip = new AlertDialog.Builder(MainActivity.this);
				tip.setTitle("How to Play");
				tip.setMessage("Try to guess a 4-digit number. After each guess, you will get the hint about the number of matches. If you guess a correct digit in the correct position, it is called a bull, while if they match a digit but in the wrong position, it is called a cow. Have Fun!");
				tip.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});

				tip.show();
				
			}
		});
		
		
	}
	
	private String generateNum(){
		
		List<Integer> numbers = new ArrayList<Integer>();
	    for(int i = 0; i < 10; i++){
	        numbers.add(i);
	    }

	    Collections.shuffle(numbers);

	    String result = "";
	    for(int i = 0; i < 4; i++){
	        result += numbers.get(i).toString();
	    }
	    
	    return result;
		
	}
	
	private String getHint(String guess){
		
		char[] a = oNumber.toCharArray();
		char[] g = guess.toCharArray();
		int m = 0;
		int n = 0;
		int[] count = new int[10];
		
		for(int i=0; i<a.length; i++){
			if(a[i] == g[i]) m++;
			else{
				if(count[a[i] - '0']++ < 0) n++;
				if(count[g[i] - '0']-- > 0) n++;
			}
		}
		
		return m+"A"+n+"B";
	}
	
	private void youWin(){
		
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Congratulation!");
		dialog.setMessage("You took "+count+" attempts");
		dialog.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				finish();
				MainActivity.initActivity(MainActivity.this, username);
				
			}
		});
		dialog.setNegativeButton("Ranking", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				finish();
				startActivity(new Intent(MainActivity.this, RankActivity.class));
				
			}
		});
		dialog.show();
		
	}
	
	private void saveData(){
		
		SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
		Editor editor = pref.edit();
		ranks = (ArrayList<Rank>)ObjectSerializer.deserialize(pref.getString("rank", ObjectSerializer.serialize(new ArrayList<Rank>())));
		Rank newRank = new Rank(username, count.toString());
		if(!ranks.contains(newRank)){
			ranks.add(newRank);
			Collections.sort(ranks, new Comparator<Rank>() {

				@Override
				public int compare(Rank lhs, Rank rhs) {
					
					return Integer.parseInt(lhs.getSteps()) - Integer.parseInt(rhs.getSteps());
					
				}
			});
			editor.putString("rank", ObjectSerializer.serialize(ranks));
			editor.commit();
		}	
		
	}

}
