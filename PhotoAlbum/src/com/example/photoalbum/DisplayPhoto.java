package com.example.photoalbum;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

public class DisplayPhoto extends Activity {
	ImageView pic;
	Drawable d;
	TextView tv;
	ListView lv;
	Context context = this;
	int current=0;
	public static int[] imgs_ids;
	public static String[] imgNames;
	public static album albumOpen;
	public static storage save;
	 static ArrayList<String> tags;
	
	static ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_photo);
		save= new storage(this);
		pic=(ImageView)findViewById(R.id.imageView1); 
		tv=(TextView)findViewById(R.id.photoName);
		lv=(ListView)findViewById(R.id.tags);
		albumOpen=OpenedAlbum.albumOpen;
		imgNames= save.getPhotoNames(albumOpen);
		imgs_ids=getPhotos(albumOpen);
		setFields();
		
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
	}
	public int[] getPhotos (album a){//returns the drawable ints for an album
		if(a.photos== null){
			return null;
		}
		int[] drawables= new int[a.photos.size()];
		for(int i=0; i< a.photos.size();i++){
		drawables[i]=getResources().getIdentifier(a.photos.get(i).name, "drawable", "com.example.photoalbum");
		}
		return drawables;
		
		
	}
	public void next(View view){
		if(current ==albumOpen.photos.size()-1){//at end of list
			current=0;
			setFields();
			return;
		}
		current=current+1;
		setFields();
		return;
	}
	public void prev(View view){
		if(current == 0){//at end of list
			current=albumOpen.photos.size()-1;
			setFields();
			return;
		}
		current=current-1;
		setFields();
		return;
	}
	public void setFields(){
		if(imgs_ids!= null){
			control c= new control((ArrayList<album>) save.loadObject());
			 d= getResources().getDrawable(imgs_ids[current]);
			pic.setImageDrawable(d);
			tv.setText(imgNames[current]);
			photo p= save.getPhoto(albumOpen, imgNames[current]);
			tags=c.listPhotoInfo(p);
			if(tags!=null){
				adapter = new ArrayAdapter<String>(this,
				          android.R.layout.simple_list_item_1, android.R.id.text1, tags);
				        // Assign adapter to ListView
				        lv.setAdapter(adapter); 
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_photo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void addTag(View view){	
	
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Add Tag to Photo");
		alert.setMessage("Enter Tag");
		
		// Set an EditText view to get user input 
		final EditText tagType = new EditText(this);
		tagType.setHint("Enter Tag Type (Must be 'person' or 'location')");

		final EditText tagValue = new EditText(this);
		tagValue.setHint("Enter Tag Value");
		
		final LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		

		layout.addView(tagType);
		layout.addView(tagValue);
		
		alert.setView(layout);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String type= tagType.getText().toString();
				if(type.compareToIgnoreCase("person")==0 || type.compareToIgnoreCase("location")==0){
				String val = tagValue.getText().toString();
				control c= new control((ArrayList<album>) save.loadObject());
				photo p= save.getPhoto(albumOpen, imgNames[current]);
				album a=c.addTag(albumOpen, p, type, val);
				if(a!=null){
				save.addAlbum(a);
				setFields();
				}
				if(a== null){
					//send error
				}
				}	
				//add tag to photo
			}
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Canceled.
			}
		});
		alert.show();
	}
	
	
	public void deleteTag(View view){	

		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("delete Tag from Photo");
		alert.setMessage("Enter Tag");
		
		// Set an EditText view to get user input 
		final EditText tagType = new EditText(this);
		tagType.setHint("Enter Tag Type (Must be 'person' or 'location')");

		final EditText tagValue = new EditText(this);
		tagValue.setHint("Enter Tag Value");
		
		final LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		

		layout.addView(tagType);
		layout.addView(tagValue);
		
		alert.setView(layout);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String type= tagType.getText().toString();
				if(type.compareToIgnoreCase("person")==0 || type.compareToIgnoreCase("location")==0){
				String val = tagValue.getText().toString();
				control c= new control((ArrayList<album>) save.loadObject());
				photo p= save.getPhoto(albumOpen, imgNames[current]);
				album a=c.deleteTag(albumOpen, p, type, val);
				if(a!=null){
				save.addAlbum(a);
				setFields();
				}
				if(a== null){
					//send error
				}
				}	
				//add tag to photo
			}
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Canceled.
			}
		});
		alert.show();
	}



	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_display_photo,
					container, false);
			return rootView;
		}
	}

}
