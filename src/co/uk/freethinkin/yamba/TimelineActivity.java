package co.uk.freethinkin.yamba;

import java.io.IOException;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.ParcelFileDescriptor.OnCloseListener;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

//OnQueryTextListener, OnCloseListener,LoaderManager.LoaderCallbacks<Cursor>


public class TimelineActivity extends BaseActivity implements  LoaderManager.LoaderCallbacks<Cursor> {
	Cursor cursor;
	ListView listTimeline;
	SimpleCursorAdapter adapter;
	static final String[] FROM = { DbHelper.C_CREATED_AT, DbHelper.C_USER,DbHelper.C_TEXT };
	static final int[] TO = { R.id.textCreatedAt, R.id.textUser, R.id.textText };
	
	//private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;
	//private static final int LOADER_ID = 1;
	
	// View binder constant to inject business logic for timestamp to relative
	// time conversion
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeline);
		
		// Check if preferences have been set
		if (yamba.getPrefs().getString("username", null) == null) { 
			startActivity(new Intent(this, PrefsActivity.class));
			Toast.makeText(this, R.string.msgSetupPrefs, Toast.LENGTH_LONG).show();
		}
		
	
//		// Get the data
//		cursor = yamba.getStatusData().getStatusUpdates();
//		listTimeline = (ListView) findViewById(R.id.listTimeline);
//		adapter = new SimpleCursorAdapter(this, R.layout.row,cursor, FROM, TO,0);
//		
//		 
//		//Set up Loader with callbacks
//		mCallbacks = this;
//		 LoaderManager lm = getLoaderManager();
//		 lm.initLoader(LOADER_ID, null, mCallbacks);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		// Close the database
		yamba.getStatusData().close();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		
		// Setup List
		//this.setupList();
	}
	// Responsible for fetching data and setting up the list and the adapter
	private void setupList() { //
		// Get the data
		cursor = yamba.getStatusData().getStatusUpdates();
	
		// Setup Adapter
		adapter = new SimpleCursorAdapter(this, R.layout.row,cursor, FROM, TO,0);
		//adapter.setViewBinder(VIEW_BINDER); //
		listTimeline.setAdapter(adapter);
	}
	

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return null;
    }

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
	
//static final ViewBinder VIEW_BINDER = new ViewBinder(){
		
//		@Override 
//		public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
//		
//			if (view.getId() != R.id.textCreatedAt)
//				return false;
//				
//			// Update the created at text to relative time
//			long timestamp = cursor.getLong(columnIndex);
//			CharSequence relTime = DateUtils.getRelativeTimeSpanString(view.getContext(), timestamp);
//			((TextView) view).setText(relTime);
//		
//			return true;
//		}
//
//	};

//@Override
//public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void onLoaderReset(Loader<Cursor> arg0) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void onClose(IOException e) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public boolean onQueryTextChange(String newText) {
//	// TODO Auto-generated method stub
//	return false;
//}
//
//@Override
//public boolean onQueryTextSubmit(String query) {
//	// TODO Auto-generated method stub
//	return false;
//}	

}
        


	
