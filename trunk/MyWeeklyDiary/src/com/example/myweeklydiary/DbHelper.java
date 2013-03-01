package com.example.myweeklydiary;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{
	
	// Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "MyWeeklyDiaryDB";
 
    // Table names
    private static final String TABLE_NOTES = "Notes";
    
    private static final String KEY_ID = "id";
    private static final String KEY_NOTE = "note";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_DATE = "date";
    private static final String KEY_DAY = "day";
    private static final String KEY_ISACCOMPLISHED = "isaccomplished";
    
    
	
	public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," 
                + KEY_NOTE + " TEXT,"
                + KEY_TITLE + " TEXT,"
                + KEY_CATEGORY + " TEXT,"
                + KEY_PRIORITY + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_DAY + " TEXT,"
                + KEY_ISACCOMPLISHED + " TEXT" + ")";
        
		db.execSQL(CREATE_USERS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
 
        // Create tables again
        onCreate(db);
	}
	
    public void addNote(Note note) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_NOTE, note.getNote());
        values.put(KEY_TITLE, note.getTitle());
        values.put(KEY_CATEGORY, note.getCategory());
        values.put(KEY_PRIORITY, note.getPriority());
        values.put(KEY_DATE, note.getDate());
        values.put(KEY_DAY, note.getDay());
        values.put(KEY_ISACCOMPLISHED, note.isAccomplished());
     
        db.insert(TABLE_NOTES, null, values);
        db.close(); 
    }
    
    public List<Note> getAllNotes(String day) {
    	List<Note> notesList = new ArrayList<Note>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTES;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        db.rawQuery("SELECT * FROM " +TABLE_NOTES+" WHERE "+KEY_DAY+ "=" +day+", ORDER BY "+KEY_PRIORITY+" ASC;", null);
        
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setID(Integer.parseInt(cursor.getString(0)));
                note.setNote(cursor.getString(1));
                note.setTitle(cursor.getString(2));
                note.setCategory(cursor.getString(3));
                note.setPriority(Integer.parseInt(cursor.getString(4)));
                note.setDate(cursor.getString(5));
                note.setDay(cursor.getString(6));
                note.setAccomplished(cursor.getString(7));
                
                notesList.add(note);
            } while (cursor.moveToNext());
        }
        return notesList;
    }
    
    public Note getNote(int id) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	 
        Cursor cursor = db.query(TABLE_NOTES, new String[] {
        		KEY_ID,
                KEY_NOTE, 
                KEY_TITLE,
                KEY_CATEGORY,
                KEY_PRIORITY,
                KEY_DATE,
                KEY_DAY,
                KEY_ISACCOMPLISHED }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Note note = new Note(
        		Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), 
                cursor.getString(2), 
                cursor.getString(3),
                Integer.parseInt(cursor.getString(4)),
                cursor.getString(5), 
                cursor.getString(6), 
                cursor.getString(7));
        db.close();
        return note;
    }
    
    public int updateNote(Note note, int KEY_ID) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
        ContentValues values = new ContentValues();
        values.put(KEY_NOTE, note.getNote());
        values.put(KEY_TITLE, note.getTitle());
        String where = "id" + "=" + KEY_ID;
        
        return db.update(TABLE_NOTES, values, where, null);
    }
    
    public int updateCategory(Note note, int KEY_ID) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, note.getCategory());
        String where = "id" + "=" + KEY_ID;
        
        return db.update(TABLE_NOTES, values, where, null);
    }
    
    public int updatePriority(Note note, int KEY_ID) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
        ContentValues values = new ContentValues();
        values.put(KEY_PRIORITY, note.getPriority());
        String where = "id" + "=" + KEY_ID;
        
        return db.update(TABLE_NOTES, values, where, null);
    }
    
    public void deleteUser(int id) {
    	SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, KEY_ID + " = " + id, null);
        //db.rawQuery("DELETE FROM " +TABLE_USERS+" WHERE "+KEY_ID+" = "+ id +";", null);
        db.close();
    } 
    
    
}
