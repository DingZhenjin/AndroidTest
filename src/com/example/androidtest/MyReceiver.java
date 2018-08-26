package com.example.androidtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "���յ���Intent��ActionΪ��"+intent.getAction()+
				"\n��Ϣ����: "+intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
		Bundle bundle = new Bundle();
		bundle.putString("mymsg1", "BroadcastReceiver.MyReceiver���͵ĵ�һ����Ϣ");
		setResultExtras(bundle);
		abortBroadcast();
	}

	int j;
}
