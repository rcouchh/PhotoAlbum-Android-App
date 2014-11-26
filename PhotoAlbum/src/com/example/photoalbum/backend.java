package com.example.photoalbum;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * @author Ryan Couch
 * 
 * 
 */



public class backend<T> {
	
	@SuppressWarnings("unchecked")
	public ArrayList<album> deserialize(){
		/*
		 * method used to generically retrieve objects in data folder
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		try{
		FileInputStream fileIn = new FileInputStream("data/albums.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		ArrayList<album> list = (ArrayList<album>)in.readObject();
		in.close();
		//System.out.println("Data loaded from \\cs213\\data\\users.ser");
	//	System.out.println("User: ");
		return list;
		
		
		}catch(IOException i){
			i.printStackTrace();
			return null;
			
		}catch(ClassNotFoundException c){
			System.out.println("Set of Photos/Users/Albums not found!");
			c.printStackTrace();
			return null;
			
		}
		
		
	}
	
	
	public void serialize(ArrayList<album> name){
		/*
		 * method used to generically store objects in data folder
		 * @throws IOException
		 */
		
		
		try{
		ArrayList<album> temp = name;
		FileOutputStream fileOut = new FileOutputStream("data/album.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(temp);
		out.close();
		fileOut.close();		
		
		}catch(IOException i){
			i.printStackTrace();
			return;
		
		}
		
	}
	
	
	public album readAlbum(String name){
		/*
		 * Method used to read a user from data, given userID
		 *Plan on making generic so can be used for albums and photos also

		 * 
		 */
		//load arraylist stored
		ArrayList<album> list = deserialize();
		
		//check if user is in list
		int x = list.size();
		
		for(int i=0; i<x; x++){
		album temp = (album)list.get(i);
		
		//if match found
		if(temp.name.compareToIgnoreCase(name)==0){
			System.out.println("User found!");
			//print full name
			
			//print albums they are tagged in
			
			return temp;
				
		}
		
	}	//end for


		return null;	
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void writeUser(album person){
		/*
		 * Method to write a particular user to storage
		 * Plan on making generic so can be used for albums and photos also
		 */
		ArrayList<album> list = deserialize();
		//int x = list.size();
		list.add(person);
		serialize(list);
		
	}
	
	
	public void deletealbum(String name){
		/*
		 * Method to delete a user from the stored user list
		 * Plan on making generic
		 */
		//get list
		ArrayList<album> list = deserialize();

		//traverse to see if user in list
		int x = list.size();
		boolean found = false;

		for(int i=0; i<list.size(); i++){
		album temp = (album)list.get(i);

		//if match found, remove
		if(temp.name.compareToIgnoreCase(name)==0){
			System.out.println("User found!");
			System.out.println("User deleted!");
			list.remove(i);
			found=true;
			break;
			}
		}//end for
		
		if(found==false){
			System.out.println("User not found!");
			return;
		}
		
		
		//serialize new list with user deleted
		serialize(list);
		
		return;
	}
	
	
	
	public ArrayList<album> getList(String type){
		/*
		 * Generically gets object list stored in data file
		 */
		ArrayList<album> list = (ArrayList<album>)deserialize();
		
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	

}
