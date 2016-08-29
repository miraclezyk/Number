package app.yakun.number;

import java.util.List;

import app.yakun.number.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RankAdapter extends ArrayAdapter<Rank> {
	
	private int resourceId;

	public RankAdapter(Context context, int resource, List<Rank> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Rank rank = getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView == null){
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) view.findViewById(R.id.name);
			viewHolder.steps = (TextView) view.findViewById(R.id.steps);
			view.setTag(viewHolder);
		}else{
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.name.setText(rank.getName());
		viewHolder.steps.setText(rank.getSteps());
		return view;
	}
	
	class ViewHolder{
		TextView name;
		TextView steps;
	}


	
}
