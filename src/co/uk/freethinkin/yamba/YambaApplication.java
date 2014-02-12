package co.uk.freethinkin.yamba;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

public class YambaApplication extends Application implements OnSharedPreferenceChangeListener{
	
	private static final String TAG = "YambaApplication";
	public Twitter twitter;
	private SharedPreferences prefs;
	
	@Override
	public void onCreate() { 
		super.onCreate();

		//Get shared preference object to access prefs. 
		prefs = PreferenceManager.getDefaultSharedPreferences(this); 
		prefs.registerOnSharedPreferenceChangeListener(this);
		
		Log.i(TAG, "onCreated");
	}
	
	@Override
	public void onTerminate() { 
		super.onTerminate();
		Log.i(TAG, "onTerminated");
	}
	
	//NOTE:synchronized cus multi threads may call this
	public synchronized Twitter getTwitter() { //
		if (this.twitter == null) {
			
			String username = this.prefs.getString("username", "");
			String password = this.prefs.getString("password", "");
			String apiRoot = prefs.getString("apiRoot","http://yamba.marakana.com/api");
			
			if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)
				&& !TextUtils.isEmpty(apiRoot)) {
				this.twitter = new Twitter(username, password);
				this.twitter.setAPIRootUrl(apiRoot);
			}
		}
		
		return this.twitter;
	}
		
	public synchronized void onSharedPreferenceChanged(
		SharedPreferences sharedPreferences, String key) { 
		this.twitter = null;
	}

}
