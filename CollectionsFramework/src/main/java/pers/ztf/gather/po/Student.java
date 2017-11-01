package main.java.pers.ztf.gather.po;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
		private String id;
	    private String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Student(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		@Override
		public String toString() {
			return "Student [id=" + id + ", name=" + name + "]";
		}
		
		
	    
	    
	    
	    
//	    public Student(String i, String n, ArrayList<Score> scores) {
//			id=new SimpleStringProperty(i);
//			name=new SimpleStringProperty(n);
//			this.scoreArrayList = scores;
//		}
//		public Student(String i, String n) {
//			id=new SimpleStringProperty(i);
//			name=new SimpleStringProperty(n);
//		}
//	    public String getId() {
//	        return id.get();
//	    }
//
//	    public void setId(String i) {
//	    	id.set(i);
//	    }
//
//	    public String getName() {
//	        return name.get();
//	    }
//
//	    public void setName(String na) {
//	        name.set(na);
//	    }
//
//	    public ArrayList<Score> getScoreArrayList() {
//	        return scoreArrayList;
//	    }
//
//	    public void setScoreArrayList(ArrayList<Score> scoreArrayList) {
//	        this.scoreArrayList = scoreArrayList;
//	    }
}
