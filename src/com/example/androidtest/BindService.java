package com.example.androidtest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;



public class BindService extends Service{
	private int count=0;
	private boolean quit=false;
	
	final String TAG = "my binder";
	
	private MyBinder  myBinder = new MyBinder();
	
	public class MyBinder extends Binder{
		public int getCount(){
			return count;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG,"Service is Binded");
		return myBinder;
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
		Log.d(TAG,"Service is Create");
		new Thread(){
			@Override
			public void run(){
				while(!quit){
					try {
						sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					count++;
				}
			}
		}.start();
	}
	
	@Override
	public boolean onUnbind(Intent intent){
		Log.d(TAG,"Service is onUnbind");
		return true;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		this.quit = true;
		Log.d(TAG,"Service is onDestroy");
	}
}
