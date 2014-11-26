package com.example.photoalbum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class storage {
	File save;
	public storage(Context c){
		save = new File(c.getFilesDir(), "stuff.dat");
	}
	public void saveObject(Object alb){
		try{
			FileOutputStream f = new FileOutputStream(save);
			ObjectOutputStream oos= new ObjectOutputStream(f);
			oos.writeObject(alb);
			oos.close();
			
		}
		catch(Exception e){
			
		}
	}

	public Object loadObject(){
		try{
			FileInputStream f = new FileInputStream(save);
			ObjectInputStream ois= new ObjectInputStream(f);
			Object o= ois.readObject();
			ois.close();
			return o;
		}
		catch(Exception e){
			
		}
		return null;
	}
	public album getAlbum(String alb){
		ArrayList<album> albums= (ArrayList<album>) loadObject();
		for (int i=0; i<albums.size();i++){
			if (albums.get(i).name.compareToIgnoreCase(alb)==0){
				return albums.get(i);
			}
		}
		
		return null;
	}
	public String[] getPhotoNames(album a){
		if(a.photos== null){
			return null;
		}
		String[] names= new String[a.photos.size()];
		for(int i=0; i< a.photos.size();i++){
			names[i]=a.photos.get(i).caption;
		}
		return names;
	}
	public photo getPhoto(album curr,String capt){
		album pics= curr;
		if (pics.photos== null){return null;}
		for(int i=0;i<pics.photos.size();i++){
			if(pics.photos.get(i).caption.compareToIgnoreCase(capt)==0){
				return pics.photos.get(i);
			}
		}
		return null;
	}
	public ArrayList<String> getAlbNames(ArrayList<album> albs){
		ArrayList<album> names= albs;
		ArrayList<String> n= new ArrayList<String>();
		for (int i= 0;i<names.size();i++){
			n.add(names.get(i).name);
		}
		return n;
	}
	public void addAlbum(album a){
		ArrayList<album> albums= (ArrayList<album>) loadObject();
		for (int i=0; i<albums.size();i++){
			if (albums.get(i).name.compareToIgnoreCase(a.name)==0){
				 albums.set(i, a);
			}
		}
		saveObject(albums);
	}
}

