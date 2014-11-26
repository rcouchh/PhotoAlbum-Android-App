package com.example.photoalbum;

import java.io.Serializable;
import java.util.ArrayList;


public class album implements Serializable{
	public String name;
	public ArrayList<photo> photos;
	//public ImageIcon file;
public album(String name, ArrayList<photo> photos){
	this.name= name;
	this.photos= photos;
	//this.file = new ImageIcon("file.jpg");
}
public boolean equals(album cur, album check){
	if (cur.equals(check)){
		return true;
	}
	
	return false;
}
public int addPhoto(photo add){
	if(this.photos== null){
		this.photos.add(add);
		return 0;
	}
	for(int i=0; i< this.photos.size();i++){
		if(this.photos.get(i).name.compareToIgnoreCase(add.name)==0){
			System.out.println("already contains photo!");
			return 1;// returns 1 if photo already contained
		}
	}
	this.photos.add(add);
	return 0;
}
public int deletePhoto(String del){
	for(int i= 0; i< this.photos.size();i++){
		if(this.photos.get(i).name.compareToIgnoreCase(del)==0){
			this.photos.remove(i);
			return 1;
		}
	}
	
	return 0;
}
public ArrayList<photo> getPhotos(){
	ArrayList<photo> pics = new ArrayList<photo>();
if(this.photos!=null)	{
	for(int i = 0; i<this.photos.size(); i++){
	pics.add(this.photos.get(i));
	}
	}
	return pics;
}
public void listPhotos(album a){
	int s= a.photos.size();
	for(int i= 0; i < s; i++){
		System.out.println(a.photos.get(i));
	}

}


}
