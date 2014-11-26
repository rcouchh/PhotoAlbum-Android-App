package com.example.photoalbum;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class Main_menu extends ActionBarActivity {
ListView lv;
Button delete,rename,create,open;
Context context= this;
//control main;
//AlbumList myAlbs;
 storage save;
 static String select="";
static  ArrayList<String> albNames;
static ArrayList<album> albs;
static ArrayAdapter<String> adapter;
//static album alb;
	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		save= new storage(this);
		
	
	
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		     lv= (ListView) findViewById(R.id.album_list);
		     lv.setClickable(true);
		     lv.setOnItemClickListener(new OnItemClickListener() {
		    	 public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
		    	    {
		    	      select = (lv.getItemAtPosition(position).toString());
		    	      open(view);
		    	    }});
		     String n = select;
	     //  delete=(Button) findViewById(R.id.delete);
	        // Defined Array values to show in ListView
	        //make into album list
		   
	
	albs= (ArrayList<album>) save.loadObject();
	if(albs!= null){
	   albNames = save.getAlbNames(albs);
	         adapter = new ArrayAdapter<String>(this,
	          android.R.layout.simple_list_item_1, android.R.id.text1, albNames);
	        // Assign adapter to ListView
	        lv.setAdapter(adapter); 
	        }
	}
	        // ListView Item Click Listener
	      //  lv.setOnItemClickListener(new OnItemClickListener() {

	       /*       @SuppressWarnings("unused")
				public void onItemClick(AdapterView<?> parent, ListView view,
	                 int position, long id) {
	                
	               // ListView Clicked item index
	               int itemPosition = position;
	               
	               // ListView Clicked item value
	               album selected  = (album) lv.getItemAtPosition(position);
	                  
	                // Show Alert 
	                Toast.makeText(getApplicationContext(),
	                  "Position :"+itemPosition+"  ListItem : "  , Toast.LENGTH_LONG)
	                  .show();
	             
	              }

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					
				}

	         }); 
	}*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);		
		return true;
	}

	public void create(View view){
		AlertDialog.Builder alert= new AlertDialog.Builder(this);
		alert.setTitle("Create Album");
		alert.setMessage("Enter Album Name");
		final EditText alb = new EditText(this);
		alert.setView(alb);
		alert.setPositiveButton("create", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int button){
				String value= alb.getText().toString();
				control c= new control((ArrayList<album>) save.loadObject());
				ArrayList<album>a =	c.createAlbum(value);
				if(a != null){
					save.saveObject(a);
					 albNames = save.getAlbNames(a);
			         adapter = new ArrayAdapter<String>(context,
			          android.R.layout.simple_list_item_1, android.R.id.text1, albNames);
			        // Assign adapter to ListView
			        lv.setAdapter(adapter); 
				}

			}
		}); 
		alert.setNegativeButton("cancel", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int button){
				//cancel
			}
		});
		alert.show();
	}
	
	public void open(View view){
		//alb = alb;
		
		Intent k= new Intent(this, OpenedAlbum.class);
		startActivity(k);
		
	}
	
	public void delete(View view){
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Delete Album");
			alert.setMessage("Enter Album Name");
			final EditText alb = new EditText(this);
			alert.setView(alb);
			
			alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					String value = alb.getText().toString();
					control c = new control((ArrayList<album>) save.loadObject());
					ArrayList<album> a = c.deleteAlbum(value);
					if(a!=null){
						save.saveObject(a);
						//delete song from list
						 albNames = save.getAlbNames(a);
				         adapter = new ArrayAdapter<String>(context,
				          android.R.layout.simple_list_item_1, android.R.id.text1, albNames);
				        // Assign adapter to ListView
				        lv.setAdapter(adapter); 
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
	
	
	public void rename(View view){
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Rename Album");
		
		final LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		final EditText oldAlb = new EditText(this);
		oldAlb.setHint("Enter Old Album Name");
		final EditText newAlb = new EditText(this);
		newAlb.setHint("Enter New Album Name");
		layout.addView(oldAlb);
		layout.addView(newAlb);
		alert.setView(layout);
		alert.setPositiveButton("Rename", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String oldName = oldAlb.getText().toString();
				String newName = newAlb.getText().toString();
				control c = new control((ArrayList<album>) save.loadObject());
				ArrayList<album> a=c.renameAlbum(oldName, newName);
				if(a!=null){
				save.saveObject(a);
				//delete song from list
				 albNames = save.getAlbNames(a);
		         adapter = new ArrayAdapter<String>(context,
		          android.R.layout.simple_list_item_1, android.R.id.text1, albNames);
		        // Assign adapter to ListView
		        lv.setAdapter(adapter); 
				//rename album in list
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
	
	
	public void search(View view){
		
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Search for Tag by Photo");
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
				Tag t= new Tag(type,val);
				control c= new control((ArrayList<album>) save.loadObject());
				ArrayList<photo>p = c.getPhotosByTag(t);
				if(p!=null){
				returnVal(p);
				
				}
				if(p== null){
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
	public void returnVal(ArrayList<photo>p){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Photos for tag search");
		
		
		// Set an EditText view to get user input 
		final ListView lv = new ListView(this);
		String[] names= getPhotoNames(p);
		int[] drawables= getPhotos(p);
		imgAdapter adapter= new imgAdapter(this,names,drawables);
		lv.setAdapter(adapter);
		alert.setView(lv);
		
		alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Canceled.
			}
		});
	
		alert.show();
		
		
	}	public String[] getPhotoNames(ArrayList<photo> a){
		if(a== null){
			return null;
		}
		String[] names= new String[a.size()];
		for(int i=0; i< a.size();i++){
			names[i]=a.get(i).caption;
		}
		return names;
	}
	public int[] getPhotos (ArrayList<photo> a){//returns the drawable ints for an album
		if(a== null){
			return null;
		}
		int[] drawables= new int[a.size()];
		for(int i=0; i< a.size();i++){
		drawables[i]=getResources().getIdentifier(a.get(i).name, "drawable", "com.example.photoalbum");
		}
		return drawables;
		
		
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


	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_menu,
					container, false);
			return rootView;
		}
	}

}
