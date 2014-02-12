package co.uk.freethinkin.yamba;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdaterService extends Service{
	static final String TAG = "UpdaterService";
	
	static final int DELAY = 60000; // a minute
	private boolean runFlag = false; //
	private Updater updater;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		this.updater = new Updater();
		Log.d(TAG, "onCreated");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroyed");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		this.runFlag = true;
		this.updater.start();
		Log.d(TAG, "onStarted");
		
		return START_STICKY;
	}
	
	/**
	* Thread that performs the actual update from the online service
	*/
	private class Updater extends Thread { 
		
		public Updater() {
			super("UpdaterService-Updater"); 
		}
		
		@Override
		public void run() { 
			UpdaterService updaterService = UpdaterService.this; 
		
			while (updaterService.runFlag) { 
				Log.d(TAG, "Updater running");
				try {
					// Some work goes here...
					Log.d(TAG, "Updater ran");
					Thread.sleep(DELAY); //
				} catch (InterruptedException e) { //
					updaterService.runFlag = false;
				}
			}
		}
	} // Updater

}
