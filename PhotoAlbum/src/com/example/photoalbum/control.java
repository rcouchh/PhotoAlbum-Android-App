package com.example.photoalbum;


import java.io.FileNotFoundException;

import java.util.ArrayList;



public class control {
/*
 * @author dan torres and ryan couch
 */
	
	//public backend<album> backend = new backend<album>();
	public ArrayList<album> albums;
	
	public ArrayList<photo> photos = new ArrayList<photo>();
	
	//backin = backend.getUsers();
	public control(ArrayList<album> albs){
		this.albums = albs;
	}

	
	public ArrayList<album> createAlbum(String name){
		if(albums== null){
			albums= new ArrayList<album>();
			album alb = new album(name, null);
			albums.add(alb);	
			return albums;
		}
		for (int i=0; i < albums.size();i++){
			if(albums.get(i).name.compareToIgnoreCase(name)==0){
				System.out.println("Album exists ");
				return null;
			}
		}
			
				ArrayList<photo> photos = new ArrayList<photo>();
				album alb = new album(name, null);
				albums.add(alb);	
				return albums;
				//System.out.println("Created album for user "+login.id + ":");
				//System.out.println(name);
			
	
		
	}

	
	public ArrayList<album> renameAlbum(String oldName, String newName){
		if(albums!=null){
			//	if(login.containsAlbum(login, name)==1){
			for(int i=0; i <albums.size(); i++){
				if(albums.get(i).name.compareToIgnoreCase(oldName)==0){
					albums.get(i).name=newName;
					return albums;
				}
			}
		}
		return null;
	}
	
	
	public ArrayList<album> deleteAlbum(String name){
	
		
		if(albums!=null){
		//	if(login.containsAlbum(login, name)==1){
		for(int i=0; i <albums.size(); i++){
			if(albums.get(i).name.compareToIgnoreCase(name)==0)	{	
			albums.remove(i);
			return albums;
			}
		}
		//	}
		}
		return null;
	
	}
	
	public ArrayList<album>  listAlbums(){
		
		return albums;
	
	}
	
	//list photos in album
	public 	ArrayList<photo> listPhotos(String name){
	
		//String[] list=new String[10];
		ArrayList<photo> alb = new ArrayList<photo>();
		for(int i=0; i<albums.size(); i++){
		if (albums.get(i).name.compareToIgnoreCase(name)==0){
			alb= albums.get(i).photos;
		//	list=new String[alb.size()];	
			continue;
		}
		}
		if(alb.size()==0 || alb==null){
			System.out.println("No photos in album "+name);
			return null;
		}
		System.out.println("Photos for album "+name+":");
	
		for(int j= 0; j < alb.size();j++){
			String ph = alb.get(j).name;
			
		//	list[j]= ph;
			
			//Calendar date= alb.get(j).time;
			//System.out.println(ph + " - " + alb.get(j).getFormatted());
		}
		return alb;
	
	
	}
	
	public int addPhoto(String fileName, album albName){
		/*
		 * adds a photo to the album
		 * returns 0 if went ok
		 * returns 1 if photo with same name already exists
		 * returns 2 if photo doesnt exist
		 */
		photo add;
	//	try {
			 add= new photo(fileName);
			// add.time.setTimeInMillis(0);
	//	} catch (Exception e) {
	//		System.out.println("File"+ fileName+ "does not exist");
	//		return;
	//	}
		
		
		//ArrayList<photo> alb =null;
		int i=0;
		boolean found=false;
		if(albName.photos ==null){
			albName.photos= new ArrayList<photo>();
			albName.photos.add(add);
			return 0;
		}
		for(int x=0; x<albName.photos.size(); x++){
			if(albName.photos.get(x).caption.compareToIgnoreCase(add.caption)==0){
				found=true;
			}
		}
		
		if(found==true){
		//System.out.println("Photo"+ fileName + "already exists in album"+ albName); 
		return 1;
		}
		else{
		albName.photos.add(add);
		//System.out.println("Added photo "+ fileName+":");
		//System.out.println(" - Album: "+ albName); 
		return 0;
		}
	}
	
//////BY RYAN
	public ArrayList<album> movePhoto(String fileName, String oldAlbum, String newAlbum){
	
		photo phot= null;
		album oldA = new album(null, null);
		album newA = new album(null, null);
		//finds old album
		for(int i = 0; i<albums.size(); i++){
			if(albums.get(i).name.compareToIgnoreCase(oldAlbum)==0){
				oldA = albums.get(i);
				break;
			}
		}
		boolean found = false;
		//finds photo in album, deletes
		if(oldA!=null){
			System.out.println(oldA.photos.size());
			for(int i = 0; i<oldA.photos.size(); i++){
				if(oldA.photos.get(i).name.compareToIgnoreCase(fileName)==0){
					phot = oldA.photos.get(i);
					oldA.photos.remove(i);
					found=true;
					break;
				}
			}
		}
		
		//finds new album
		for(int i=0; i<albums.size(); i++){
			if(albums.get(i).name.compareToIgnoreCase(newAlbum)==0){
				newA = albums.get(i);
				break;
			}
		}
		if(found==false){
			return null;
			//System.out.println("Photo "+fileName +" does not exist in " +oldAlbum);
		}
		if(found==true){
			if(newA.photos == null){
				newA.photos= new ArrayList<photo>();
				newA.photos.add(phot);
				return albums;
			}
			for(int i=0;i<newA.photos.size();i++){
				if(newA.photos.get(i).caption.compareToIgnoreCase(phot.caption)==0){
					return null;
				}
			}
		newA.photos.add(phot);
		return albums;
		
		//System.out.println("Moved photo: "+fileName +":");
		//System.out.println(fileName + " - From album " + oldAlbum +" to album "+newAlbum);
	
		}
		return null;
	}

	
	public int removePhoto(String fileName, album albumName){
        /*
         * removes a photo from the album
         */
   
              
                int i= 0;
                    
        i=albumName.deletePhoto(fileName);
        if (i == 0){
               // System.out.println("Photo" +fileName+ " is not in album"+ albumName);
                return 1;
        }
               // System.out.println("Removed photo: ");
               // System.out.println(fileName+" - From album "+ albumName);
                return 0;
                
}
	
	public album addTag(album a,photo fileName, String tagType, String tagValue){
	/*
	 * adds a tag to a photo
	 */
	Tag f= new Tag(tagType, tagValue);
	for(int i=0;i<a.photos.size();i++){
		if(a.photos.get(i).caption.compareToIgnoreCase(fileName.caption)==0){
			if(a.photos.get(i).tags== null){
				a.photos.get(i).tags= new ArrayList<Tag>();
				a.photos.get(i).tags.add(f);
				return a;
			}
			for(int j=0;j<a.photos.get(i).tags.size();j++){
				if(a.photos.get(i).tags.get(j).type.compareTo(f.type)==0 && a.photos.get(i).tags.get(j).value.compareTo(f.value)==0){
					return null;//already contains
				}
			}
			a.photos.get(i).tags.add(f);
			return a;
		}
	}
	return null;
	
	}
	public album deleteTag(album a,photo fileName, String tagType, String tagValue){
		/*
		 * adds a tag to a photo
		 */
		Tag f= new Tag(tagType, tagValue);
		for(int i=0;i<a.photos.size();i++){
			if(a.photos.get(i).caption.compareToIgnoreCase(fileName.caption)==0){
				if(a.photos.get(i).tags== null){
					return null;
				}
				for(int j=0;j<a.photos.get(i).tags.size();j++){
					if(a.photos.get(i).tags.get(j).type.compareTo(f.type)==0 && a.photos.get(i).tags.get(j).value.compareTo(f.value)==0){
						a.photos.get(i).tags.remove(j);
						return a;
					}
				}
				
				return null;
			}
		}
		return null;
		
		}
	
	
	public ArrayList<String> listPhotoInfo(photo fileName) {
		/*
		 * lists the information of the given photo
		 */
		ArrayList<String> tags= new ArrayList<String>();
		photo pic= fileName;
		if(pic.tags!=null){
		for(int i=0;i<pic.tags.size();i++){
			Tag p = pic.tags.get(i);
			if(p.type.compareToIgnoreCase("person")==0){
				tags.add("person:"+ p.value);
			}
			if(p.type.compareToIgnoreCase("location")==0){
				tags.add("location:"+ p.value);
			}
		}
		return tags;
		}
			System.out.println("Photo "+fileName +" does not exist");
			return null;
	}
	

	
	public ArrayList<photo> getPhotosByTag(Tag n){


		/*

		 * searches for a photo given the tag

		 */
		ArrayList<photo> selected= new ArrayList<photo>();
	//	System.out.println("Photos for user"+ login.id+" with tags"+n.type+n.value+":");
		for(int i=0;i<albums.size();i++){
			if(albums.get(i).photos!= null){
			ArrayList<photo> pics= albums.get(i).photos;
			for(int j=0; j< pics.size();j++){
				//String albn= albums.get(i).name;
				photo p=pics.get(j);
				if(p.tags!= null){
				for(int c= 0;c< p.tags.size(); c++){
					Tag t= p.tags.get(c);
					if(n.type.compareToIgnoreCase("")!=0){
						if(n.type.compareToIgnoreCase(t.type)==0 && t.value.contains(n.value) ==true ){
							selected.add(p);
							
						}
					}
					
				}
				}
			}
			}
		}
		return selected;
	}
	
	public void logout(){
		/*
		 * logs out the user
		 */
		//backend.serialize(albums);
		return;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
