package co.uk.freethinkin.yamba;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class TimelineActivity extends Activity {
	DbHelper dbHelper;
	SQLiteDatabase db;
	Cursor cursor;
	ListView listTimeline; 
	TimelineAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeline);
		
		// Find your views
		listTimeline = (ListView) findViewById(R.id.listTimeline);
		
		// Connect to database
		dbHelper = new DbHelper(this); 
		db = dbHelper.getReadableDatabase(); 
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		// Close the database
		db.close(); 
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		// Get the data from the database
		cursor = db.query(DbHelper.TABLE, null, null, null, null, null,DbHelper.C_CREATED_AT + " DESC"); 
		startManagingCursor(cursor); 
		
		// Iterate over all the data and print it out
		// Set up the adapter
		adapter = new TimelineAdapter(this, cursor);
		listTimeline.setAdapter(adapter);
	}
}
	
