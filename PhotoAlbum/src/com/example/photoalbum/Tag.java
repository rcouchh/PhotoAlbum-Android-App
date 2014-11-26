package com.example.photoalbum;

import java.io.Serializable;

public class Tag implements Serializable {
	
		public String type;
		public String value;
		public Tag(String type, String value){
			this.type = type;
			this.value= value;

		}
		
	

}


