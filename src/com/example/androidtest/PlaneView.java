package com.example.androidtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class PlaneView extends View{
	
	private float currentX;
	private float currentY;
	private Bitmap planeBitmap;
	
	public PlaneView(Context context){
		super(context);
		planeBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
		setFocusable(true);
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		
		Paint paint = new Paint();
		
		canvas.drawBitmap(planeBitmap,currentX,currentY,paint);
	}
	
	public void setCurrentX(float currentX) {
		this.currentX = currentX;
	}
	
	public void setCurrentY(float currentY) {
		this.currentY = currentY;
	}
	
	public float getCurrentX(){
		return currentX;
	}
	
	public float getCurrentY(){
		return currentY;
	}	
}
