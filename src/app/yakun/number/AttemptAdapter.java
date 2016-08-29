package app.yakun.number;

import java.util.List;

import app.yakun.number.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AttemptAdapter extends ArrayAdapter<Attempt> {
	
	private int resourceId;

	public AttemptAdapter(Context context, int resource, List<Attempt> objects) {
		
		super(context, resource, objects);
		resourceId = resource;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Attempt attempt = getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView == null){
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.id = (TextView) view.findViewById(R.id.times);
			viewHolder.number = (TextView) view.findViewById(R.id.record);
			viewHolder.hint = (TextView) view.findViewById(R.id.hint);
			view.setTag(viewHolder);
		}else{
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.id.setText(attempt.getId());
		viewHolder.number.setText(attempt.getNumber());
		viewHolder.hint.setText(attempt.getHint());
		return view;
	}
	
	class ViewHolder{
		TextView id;
		TextView number;
		TextView hint;
	}


}
