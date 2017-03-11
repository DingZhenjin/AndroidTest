package com.example.androidtest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class FirstProvider extends	ContentProvider{

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		System.out.println("========== onCreate ===========");
		System.out.println("update 参数:"+selectionArgs);	
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		System.out.println("========== onCreate ===========");
		System.out.println("update 参数:"+values);		
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		System.out.println("========== onCreate ===========");
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		System.out.println("========== onCreate ===========");
		System.out.println("where 参数:"+selection);
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		System.out.println("========== onCreate ===========");
		System.out.println("update 参数:"+selection);
		return 0;
	}

}
