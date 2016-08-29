package app.yakun.number;

import java.util.ArrayList;
import java.util.List;

import app.yakun.number.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

public class RankActivity extends Activity {

	private ListView rankList;
	
	private RankAdapter adapter;
	
	private List<Rank> ranks = new ArrayList<Rank>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.rank_layout);
		
		initList();
		
		rankList = (ListView) findViewById(R.id.rank_list);
		adapter = new RankAdapter(this, R.layout.rank_item, ranks);
		rankList.setAdapter(adapter);
		
	}
	
	private void initList(){
		
		SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
		String strList = pref.getString("rank", "");
		if(strList.equals("")){
			Toast.makeText(this, "No ranking...", Toast.LENGTH_SHORT).show();
		}else{
			ranks = (ArrayList<Rank>) ObjectSerializer.deserialize(strList);
		}
		
	}
	
}
