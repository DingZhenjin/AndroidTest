package com.example.androidtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle bundle = getResultExtras(true);
		String msg = bundle.getString("mymsg1");
		Toast.makeText(context, "���յ���Bundle����"+msg, Toast.LENGTH_LONG).show();
	}

}
