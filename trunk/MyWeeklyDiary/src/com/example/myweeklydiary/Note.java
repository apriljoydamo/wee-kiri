package com.example.myweeklydiary;

public class Note {
	
	private int ID;
	private String note;
	private String title;
	private String category;
	private int priority;
	private String date;
	private String day;
	private String isAccomplished;
	
	public Note(){}
	
	public Note(int ID, String note, String title, String category, int priority, 
				String date, String day, String isAccomplished){
		this.ID = ID;
		this.note = note;
		this.title = title;
		this.category = category;
		this.priority = priority;
		this.date = date;
		this.day = day;
		this.isAccomplished = isAccomplished;
	}
	
	public Note(String note, String title){
		this.note = note;
		this.title = title;
	}
	
	public Note(String category){
		this.category = category;
	}
	
	public Note(int priority){
		this.priority = priority;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String isAccomplished() {
		return isAccomplished;
	}
	public void setAccomplished(String isAccomplished) {
		this.isAccomplished = isAccomplished;
	}

	
}
