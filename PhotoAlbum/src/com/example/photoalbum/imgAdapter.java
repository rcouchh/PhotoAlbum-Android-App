package com.example.photoalbum;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class imgAdapter extends BaseAdapter{
	private static LayoutInflater inflater=null;
	String[] result;
	Context context;
	int[] imageID;
	ImageView img;
	TextView txt;
	
	static class ViewHolder{
		TextView imgName;
		ImageView img;
	}
	
	public imgAdapter(Context activity, String[] imgList, int[] imgs){
		result = imgList;
		context = activity;
		imageID = imgs;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View rowView = convertView;
		ViewHolder holder = new ViewHolder();
		if(rowView== null){
		rowView = inflater.inflate(R.layout.photo_list, null);
		holder.imgName = (TextView) rowView.findViewById(R.id.textView1);
		holder.img = (ImageView)rowView.findViewById(R.id.imageView1);
		holder.imgName.setText(result[position]);
		holder.img.setImageResource(imageID[position]);
		
		rowView.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
			
				
			}
		});
		}
		else{
			holder.imgName = (TextView) rowView.findViewById(R.id.textView1);
			holder.img = (ImageView)rowView.findViewById(R.id.imageView1);
			holder.imgName.setText(result[position]);
			holder.img.setImageResource(imageID[position]);
			
		}
		//if(convertView==null){
			return rowView;
			
			//}
		/*if(convertView!=null){
			holder = (ViewHolder)convertView.getTag();
			}
		
		return null;
	}*/
	}
}
