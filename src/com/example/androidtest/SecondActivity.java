package com.example.androidtest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity{

	int i;
	int j;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_second);
		
		Button previous = (Button)findViewById(R.id.second_button1);
		Button close = (Button)findViewById(R.id.second_button2);
		//TextView textView = (TextView)findViewById(R.id.secondtextView);
		//ComponentName componentName = getIntent().getComponent();
		//textView.setText("包名:"+componentName.getPackageName()+"\n类名:"+componentName.getClassName());
		
		
		previous.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SecondActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
		
		
		close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SecondActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
/*	
	private TextView textView =null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_second);
		
		Button previous = (Button)findViewById(R.id.second_button1);
		Button close = (Button)findViewById(R.id.second_button2);
		Button add = (Button)findViewById(R.id.second_button3);
		textView = (TextView)findViewById(R.id.secondtextView);
		
		Intent intent = getIntent();
		Person person = (Person)intent.getSerializableExtra("person");
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("姓名:").append(person.getName()).append("\n");
		stringBuffer.append("年龄:").append(person.getAge()).append("\n");
		textView.setText(stringBuffer.toString());
		
		previous.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SecondActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
		
		
		close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SecondActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textView.setText(textView.getText()+"1");
			}
		});
	}
*/	
}
