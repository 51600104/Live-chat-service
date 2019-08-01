package org.o7planning.thymeleaf.model;

public class User {

		private String name ;
		private String iD;
		public String role;
		private String date;
		private String image;

		
		public User() {
			 
	    }
	 
	    public User(String name , String iD, String role) {
	        this.name = name;
	        this.iD = iD;
	        this.role = role;
	    }
		
		@Override
		public String toString() {
			return " [name=" + this.name + ", iD=" + this.iD + "], "+ "Role="+this.role;
		}

		public String getiD() {
			return iD;
		}

		public void setiD(String iD) {
			this.iD = iD;
		}

		public String getname() {
			return name;
		}

		public void setname(String name) {
			this.name = name;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		

		
		

	}

	
	

