package com.example.photoalbum;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class CreateAlbum extends Activity {
	album alb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_album);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_album, menu);
		return true;
	}
	public void ret(View view){
		//returns window after creating album
		int contain= 0;//0 if doesnt contain 1 if contains
		Intent k= new Intent(this, Main_menu.class);
		EditText name= (EditText)findViewById(R.id.albname);
		String n= name.getText().toString();
		storage s = new storage(this);
		@SuppressWarnings("unchecked")
		ArrayList<album> a= (ArrayList<album>) s.loadObject();
		control c= new control(a);
		a=c.createAlbum(n);
		if(a==null){
			startActivity(k);
		}
		//a.add(new album(n,null));
		s.saveObject(a);
		startActivity(k);
		
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
			View rootView = inflater.inflate(R.layout.fragment_create_album,
					container, false);
			return rootView;
		}
	}

}
