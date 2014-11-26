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
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

public class OpenedAlbum extends Activity {
	ListView lv;
	Context context = this;
	
	public static int[] imgs;
	public static String[] imgNames;
	public static album albumOpen;
	public static storage save;
	static imgAdapter Adapter;
	// Drawable drawable = getResources().getDrawable(getResources()
        //     .getIdentifier("one", "drawable", getPackageName()));
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opened_album);
		//context = this;
		lv = (ListView)findViewById(R.id.listView1);
		String choice= Main_menu.select;
		 save= new storage(this);
		albumOpen= save.getAlbum(choice);
		imgNames= save.getPhotoNames(albumOpen);
		imgs= getPhotos(albumOpen);
		if(imgNames== null || imgs== null || imgNames.length==0){
			imgNames= new String[1];
			imgNames[0]=("no Photos to display!");
			imgs= new int[1];
			imgs[0]= getResources().getIdentifier("ic_launcher", "drawable", "com.example.photoalbum");
			lv.setClickable(false);
		}
		if(imgNames[0].compareToIgnoreCase("no Photos to display!")!=0){
			lv.setClickable(true);
		}
		Adapter= new imgAdapter(context, imgNames,imgs);
		lv.setAdapter(Adapter);
		
		//int res= getResources().getIdentifier("one", "drawable", "com.example.photoalbum");
		
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opened_album, menu);
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

	
	public void display(View view){
		if(albumOpen.photos== null || albumOpen.photos.size()==0){
			ErrorPhoto(1);
		}
		if(albumOpen.photos!= null && albumOpen.photos.size()!=0){
			Intent k= new Intent(this,DisplayPhoto.class);
			 startActivity(k);
		}
		 
		
		//d= getResources().getDrawable(id)
	}
	public void ErrorPhoto(int cause){//0if not real photo 1 of otherwise
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("ERROR!");
		if(cause==0){
		alert.setMessage("Photo Does not exist! make sure file is in res/drawable");}
		else if(cause==1){
			alert.setMessage("Error! no photos to display!");
		}
		// Set an EditText view to get user input 
		final TextView input = new TextView(this);
		alert.setView(input);
		alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  
		  }
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
		
		
		//Intent k= new Intent(this, alert);
		//startActivity(k);
		//pass photo that gets opened to openedAlbum
	}
	
	
	
	public void add(View view){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Add Photo");
		alert.setMessage("Enter Photo Name(no need for format type)");
		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("addPhoto", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  String value = input.getText().toString();
		  control c= new control((ArrayList<album>) save.loadObject());
		
		 int exists= getResources().getIdentifier(value, "drawable", "com.example.photoalbum");
		 if(exists==0){
			ErrorPhoto(0);
			
		 }
		 if(exists!=0) {
			 int check= c.addPhoto(value, albumOpen); 
			 //check =0 if fine, 1 if already contained, 
			 if(check==0){
			 save.addAlbum(albumOpen);
			 imgNames= save.getPhotoNames(albumOpen);
			 imgs= getPhotos(albumOpen);
			 Adapter =new imgAdapter(context, imgNames,imgs);
				lv.setAdapter(Adapter);
			//Intent k= new Intent(context, OpenedAlbum.class);
			// startActivity(k);
		 }
			 if(check==1){
				 
			 }
		 }
		  
		  
		  }
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
		
		
		//Intent k= new Intent(this, alert);
		//startActivity(k);
		//pass photo that gets opened to openedAlbum
	}
	
	public void delete(View view){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Delete Photo");
		alert.setMessage("Enter Photo Name(no need for format type)");
		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  String value = input.getText().toString();
		  control c= new control((ArrayList<album>) save.loadObject());
		
		 int exists= getResources().getIdentifier(value, "drawable", "com.example.photoalbum");
		 if(exists==0){
			ErrorPhoto(0);
			
		 }
		 if(exists!=0) {
			 int check= c.removePhoto(value, albumOpen); 
			 //check =0 if fine, 1 if already contained, 
			 if(check==0){
			if(albumOpen!=null){
			 save.addAlbum(albumOpen);
			 imgNames= save.getPhotoNames(albumOpen);
			 imgs= getPhotos(albumOpen);
			 Adapter =new imgAdapter(context, imgNames,imgs);
				lv.setAdapter(Adapter);
			}
			//Intent k= new Intent(context, OpenedAlbum.class);
			// startActivity(k);
		 }
			 if(check==1){
				 
			 }
		 }
		  
		  
		  }
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
		
	}

	public void move(View view){
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("move photo");
		
		final LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		final EditText photoName = new EditText(this);
		photoName.setHint("enter photo name");
		final EditText oldAlb = new EditText(this);
		oldAlb.setHint("Enter Old Album Name");
		final EditText newAlb = new EditText(this);
		newAlb.setHint("Enter New Album Name");
		layout.addView(photoName);
		layout.addView(oldAlb);
		layout.addView(newAlb);
		alert.setView(layout);
		alert.setPositiveButton("move", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String pName= photoName.getText().toString();
				String oldA = oldAlb.getText().toString();
				String newA = newAlb.getText().toString();
				if(save.getAlbum(oldA)!=null && save.getAlbum(newA)!=null  ){
					
				control c = new control((ArrayList<album>) save.loadObject());
				
				ArrayList<album> a=c.movePhoto(pName, oldA, newA);
				if(a!=null){
				save.saveObject(a);
				//delete song from list
				album b = save.getAlbum(oldA);
				imgNames= save.getPhotoNames(b);
				 imgs= getPhotos(b);
				 Adapter =new imgAdapter(context, imgNames,imgs);
					lv.setAdapter(Adapter);
				//rename album in list
				}
			}
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
			View rootView = inflater.inflate(R.layout.fragment_opened_album,
					container, false);
			return rootView;
		}
	}

}


