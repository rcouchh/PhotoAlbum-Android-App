package com.example.photoalbum;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import android.app.Activity;
import android.support.v7.appcompat.R.drawable;




/*
 * @author ryan couch and dan torres
 * photo class
 */


public class photo  implements Serializable {
	public final String name;
	public String caption;

	public ArrayList<Tag> tags;
	//public int drawable;
	
	//ImageIcon pic;
	String formatted;

public photo(String name){
	
	this.name = name.toLowerCase();//Stays the same once created this is the actual name
	this.caption= name;//name the user sees and can change
	
	this.tags= new ArrayList<Tag>();
	//this.drawable = getDrawable();/// holds images integer location drawable

	
}//end photo declar
//end tag declar


public void addTag (Tag n){
	
	if (this.tags.contains(n)){
		return;
	}
	this.tags.add(n);
	
}



/*public int getDrawable(){
	int drawable = getResources().getIdentifier(name, "drawable", "com.example.photoalbum");
	
	
	int i = drawable;
	int j= i;
	return drawable;
}*/
public void deleteTag(Tag n){
	if (!this.tags.contains(n)){
		return;
	}
	this.tags.add(n);
	
}
public void listTags(photo P){
	for(int i= 0; i < P.tags.size(); i++){
		if(P.tags.get(i) != null){
			System.out.println(P.tags.get(i));}
	}
}
public void changeName(String capt){//use to be change caption but now name 
	caption= capt;
	
}
public void orderTags(){
	ArrayList<Tag> t= this.tags;
	ArrayList<Tag>p= new ArrayList<Tag>();
	ArrayList<Tag>l = new ArrayList<Tag>();
	ArrayList<Tag>o = new ArrayList<Tag>();
	for(int i= 0; i< t.size();i++){
	Tag g= t.get(i);
	if(g.type.compareToIgnoreCase("Location")==0){
		l.add(g);
		
	}
	if(g.type.compareToIgnoreCase("People")==0){
		p.add(g);
		
	}
	
	else if (g.type.compareToIgnoreCase("People")!=0 && g.type.compareToIgnoreCase("Location")!=0){
		o.add(g);
		
	}
	}
	int j=0;
	while(j< l.size()){
		Tag g= l.get(j);
		System.out.println(g.type+":"+ g.value);
		j++;
	}
	j=0;
	while(j< p.size()){
		Tag g= p.get(j);
		System.out.println(g.type+":"+ g.value);
		j++;
	}
	j=0;
	while(j< o.size()){
		Tag g= o.get(j);
		System.out.println(g.type+":"+ g.value);
		j++;
	}
	return;
}




}