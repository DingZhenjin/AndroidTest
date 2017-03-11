package com.example.androidtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.PrivateCredentialPermission;

import org.w3c.dom.Comment;

import com.seuic.android.PosdService;


import com.seuic.extra.lightpay.LightPayFuncs;
import com.seuic.extra.lightpay.LightPayListener;

import com.seuic.util.HexUtil;


import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.params.RggbChannelVector;
import android.media.ExifInterface;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.Vibrator;
import android.R.array;
import android.R.bool;
import android.R.integer;
import android.R.string;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ActionBar.Tab;
import android.app.DownloadManager.Request;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.app.Fragment;
import android.app.Notification;
import android.app.Notification.Action;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.app.TabActivity;
import android.app.TimePickerDialog;
import android.speech.tts.TextToSpeech;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.GridLayout;
import android.telephony.TelephonyManager;
import android.text.Editable.Factory;
import android.text.TextUtils;
import android.text.method.SingleLineTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterViewFlipper;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends Activity{
	final String TAG = "--CrazyIt--";

	String[] fileStrings = null;
	AssetManager asserts = null;
	ImageView imageView = null;
	Button bnButton = null;
	int imagecount = 0;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_bitmap);
		
		imageView = (ImageView)findViewById(R.id.bitmap_imageView);
		try {
			asserts = getAssets();
			fileStrings = asserts.list("");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		bnButton = (Button)findViewById(R.id.bitmap_bn);
		bnButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(imagecount >= fileStrings.length){
					imagecount = 0;
				}
				while(!fileStrings[imagecount].endsWith(".png") && 
					  !fileStrings[imagecount].endsWith(".jpg") &&
					  !fileStrings[imagecount].endsWith(".JPG") &&
					  !fileStrings[imagecount].endsWith(".gif")){
					
					imagecount++;
					if(imagecount >= fileStrings.length){
						imagecount = 0;
					}
				}
				
				InputStream inputStream = null;
				try {
					inputStream = asserts.open(fileStrings[imagecount++]);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				BitmapDrawable bitmapDrawable = (BitmapDrawable)imageView.getDrawable();
				if(bitmapDrawable !=null && !bitmapDrawable.getBitmap().isRecycled()){
					//bitmapDrawable.getBitmap().recycle();
				}
				imageView.setImageBitmap(BitmapFactory.decodeStream(inputStream));
			}
		});
		
	}	
	
/*	
	EditText urleditText = null;
	Button bnButton = null;
	WebView showWebView = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_webview);
		
		urleditText = (EditText)findViewById(R.id.webview_edit);
		showWebView =(WebView)findViewById(R.id.webview_show);
		bnButton = (Button)findViewById(R.id.webview_bn);
		bnButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = urleditText.getText().toString();
				showWebView.loadUrl(url);
			}
		});
	}	
*/	
	
/*	
	ImageView imageView = null;
	Bitmap bitmap = null;
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			if(msg.what == 0x123){
				if(bitmap != null && imageView !=null){
					imageView.setImageBitmap(bitmap);
				}
			}
		}
	};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_url);
		
		imageView = (ImageView)findViewById(R.id.url);
		
		new Thread(){
			@Override
			public void run(){
				try {
					URL url = new URL("http://www.crazyit.org/"
							+"attachments/month_1008/20100812_7763e970f"
							+"822325bfb019ELQVym8tW3A.png");
					InputStream isInputStream = url.openStream();
					bitmap = BitmapFactory.decodeStream(isInputStream);
					handler.sendEmptyMessage(0x123);
					isInputStream.close();
					
					isInputStream = url.openStream();
					OutputStream os = openFileOutput("crazyit.png", MODE_PRIVATE);
					byte[] buffer = new byte[1024];
					int hasread = 0;
					while((hasread = isInputStream.read(buffer))>0){
						os.write(buffer, 0, hasread);
					}
					isInputStream.close();
					os.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}.start();
	}

*/	
		
		
/*	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_recviver);
		
		Button send = (Button)findViewById(R.id.receiver_send);
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("com.example.androidtest.MyReceiver");
				intent.putExtra("msg", "一个简单的信息交互");
				sendOrderedBroadcast(intent, null);
			}
		});
	}
	
*/	
	
/*	
	
	Vibrator vibrator;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_vibrator);
		
		vibrator = (Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
		
		Button startButton = (Button)findViewById(R.id.vibrator_start);
		startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vibrator.vibrate(1000);
			}
		});
		
		Button startrepeatButton = (Button)findViewById(R.id.vibrator_start_repeat);
		startrepeatButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vibrator.vibrate(new long[]{100,400,800,1200,1600}, 6);
			}
		});
		
		Button stopButton = (Button)findViewById(R.id.vibrator_stop);
		stopButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vibrator.cancel();
			}
		});
	}	
*/	
/*	
	ListView listView = null;
	String[] statusNames;
	ArrayList<String> statusvalueArrayList = new ArrayList<String>();
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_maintest);
		
		TelephonyManager tmanagerManager =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		statusNames = getResources().getStringArray(R.array.statusName);
		String[] simstateStrings = getResources().getStringArray(R.array.simstate);
		String[] phoneType = getResources().getStringArray(R.array.phonetype);
		
		String string = tmanagerManager.getDeviceId();
		
		statusvalueArrayList.add(string);
		statusvalueArrayList.add(tmanagerManager.getDeviceSoftwareVersion() !=null?
				tmanagerManager.getDeviceSoftwareVersion():"未知");
		
		statusvalueArrayList.add(tmanagerManager.getNetworkOperator());
		statusvalueArrayList.add(tmanagerManager.getNetworkOperatorName());
		statusvalueArrayList.add(phoneType[tmanagerManager.getPhoneType()]);
		statusvalueArrayList.add(tmanagerManager.getCellLocation()!=null?
				tmanagerManager.getCellLocation().toString():"未知");
		statusvalueArrayList.add(tmanagerManager.getSimCountryIso());
		statusvalueArrayList.add(tmanagerManager.getSimSerialNumber());
		statusvalueArrayList.add(simstateStrings[tmanagerManager.getSimState()]);
	
		listView = (ListView)findViewById(R.id.listmain);
		ArrayList<Map<String, String>> statusArrayList = new ArrayList<Map<String,String>>();
		
		for(int i =0;i<statusvalueArrayList.size();i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", statusNames[i]);
			map.put("value", statusvalueArrayList.get(i));
			statusArrayList.add(map);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(this,statusArrayList,R.layout.linearlayout_line,
				new String[]{"name","value"},
				new int[]{R.id.line_title,R.id.line_content});
		listView.setAdapter(adapter);
	}

*/	
	
/*	
	BindService.MyBinder binder = null;
	Button startServiceButton = null;
	Button stopServiceButton = null;
	Button getServiceInfoButton = null;
	
	private ServiceConnection connection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "启动Service DisConnect", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			binder =(BindService.MyBinder)service;
			Toast.makeText(MainActivity.this, "启动Service Connect", Toast.LENGTH_SHORT).show();
		}
	};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_service);
		
		final Intent intent = new Intent(this,BindService.class);	
		
		startServiceButton = (Button)findViewById(R.id.service_start);
		startServiceButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bindService(intent, connection, BIND_AUTO_CREATE);
				Toast.makeText(MainActivity.this, "绑定Service Connect", Toast.LENGTH_SHORT).show();
			}
		});
		
		stopServiceButton = (Button)findViewById(R.id.service_stop);
		stopServiceButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unbindService(connection);
				Toast.makeText(MainActivity.this, "解除Service Connect", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		getServiceInfoButton = (Button)findViewById(R.id.service_info);
		getServiceInfoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Toast.makeText(MainActivity.this, "Service Connect info"+binder.getCount(), Toast.LENGTH_SHORT).show();
			}
		});
	}
	
*/	
/* startService 只是启动一个Server，此应用和调用的Service没有联系 */	
/*
	Button startServiceButton = null;
	Button stopServiceButton = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_service);
		
		startServiceButton = (Button)findViewById(R.id.service_start);
		stopServiceButton = (Button)findViewById(R.id.service_stop);
		
		final Intent intent = new Intent();
		intent.setPackage("com.example.androidtest2");
		intent.setAction("com.example.androidtest2.FirstService");
		
		startServiceButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startService(intent);
			}
		});
		
		stopServiceButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopService(intent);
			}
		});
	}	
*/	
	
/*	
	ContentResolver contentResolver;
	Uri uri = Uri.parse("content://com.example.androidtest2.SecondProvider/");
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_provider);
		
		contentResolver = getContentResolver();
		
		Button query = (Button)findViewById(R.id.provide_query);
		query.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Cursor cursor = contentResolver.query(uri, null, "query_where", null, null);
				Toast.makeText(MainActivity.this, "远程ContentProvider返回的Cursor为： "+cursor, Toast.LENGTH_SHORT).show();
			}
		});
		
		Button insert = (Button)findViewById(R.id.provide_insert);
		insert.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentValues contentValues = new ContentValues();
				contentValues.put("studentname", "dingzhnjin");
				Uri newUri =  contentResolver.insert(uri, contentValues);
			
				Toast.makeText(MainActivity.this, "远程ContentProvider返回的newUri为： "+newUri, Toast.LENGTH_SHORT).show();
			}
		});
		
		Button update = (Button)findViewById(R.id.provide_update);
		update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentValues contentValues = new ContentValues();
				contentValues.put("studentname", "wqdwqd");
				int count = contentResolver.update(uri, contentValues, "update_where", null);
				Toast.makeText(MainActivity.this, "远程ContentProvider返回的newUri为： "+count, Toast.LENGTH_SHORT).show();
			}
		});
		
		Button detele = (Button)findViewById(R.id.provide_delete);
		detele.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int count = contentResolver.delete(uri, "update_where", null);
				Toast.makeText(MainActivity.this, "远程ContentProvider返回的newUri为： "+count, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
*/	
	
/*	
	final static String FILE_NAME = "crazyit.bin";
	
	TextToSpeech tts;
	EditText edittext;
	Button speakButton;
	Button recordButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout);
		
		tts = new TextToSpeech(this,new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int status) {
				// TODO Auto-generated method stub
				if(status == TextToSpeech.SUCCESS){
					int result = tts.setLanguage(Locale.US);
					if(result != TextToSpeech.LANG_COUNTRY_AVAILABLE &&
						result != TextToSpeech.LANG_AVAILABLE){
						Toast.makeText(MainActivity.this, "暂不支持", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		edittext = (EditText)findViewById(R.id.tts_edittext);
		speakButton =(Button)findViewById(R.id.tts_speak);
		recordButton =(Button)findViewById(R.id.tts_record);
		
		speakButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tts.speak(edittext.getText().toString(), TextToSpeech.QUEUE_ADD, null,"speech");
			}
		});
		
		recordButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tts.synthesizeToFile(edittext.getText().toString(), null, new File("/mnt/sdcard/sound.wav"), "record");
			}
		});
		
	}
*/
/*	
	GestureDetector detector;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_gesture);
		detector = new GestureDetector(this,this);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent me){
		return detector.onTouchEvent(me);
		
	}
	

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "onDown is happen", Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Toast.makeText(MainActivity.this, "onFling is happen", Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "onLongPress is happen", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "onScroll is happen", Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "onShowPress is happen", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "onSingleTapUp is happen", Toast.LENGTH_SHORT).show();
		return false;
	}
	
	

	*/
	
/*	
	private SQLiteDatabase dbDatabase = null;
	private Button bn = null;
	private ListView listView = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_database);
		Log.d(TAG, "onCreate 运行...");
	
		dbDatabase = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/my.db3", null);
		
		listView = (ListView)findViewById(R.id.database_listview);
		bn = (Button)findViewById(R.id.database_bn);
		
		bn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String titleString = ((EditText)findViewById(R.id.database_title)).getText().toString();
				String contentString = ((EditText)findViewById(R.id.database_content)).getText().toString();
				try {
					insertData(dbDatabase,titleString,contentString);
					Cursor cursor = dbDatabase.rawQuery("select * from news_inf", null);
					inflateList(cursor);
				} catch (Exception e) {
					// TODO: handle exception
					dbDatabase.execSQL("create table news_inf(_id integer"
							+" primary key autoincrement,"
							+" news_title varchar(50),"
							+" news_content varchar(255))");
					insertData(dbDatabase, titleString, contentString);
					
					Cursor cursor = dbDatabase.rawQuery("select *from news_inf", null);
					inflateList(cursor);
				}
			}
		});	
	}
	
	private void insertData(SQLiteDatabase db,String title,String content){
		dbDatabase.execSQL("insert into news_inf values(null,?,?)", new String[]{title,content});
	}
	
	private void inflateList(Cursor cursor){
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.linearlayout_line, cursor,
				new String[]{"news_title","news_content"}, new int[]{R.id.line_title,R.id.line_content}, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView.setAdapter(adapter);
		
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		if(dbDatabase != null && dbDatabase.isOpen()){
			dbDatabase.close();
		}
	}
	
*/	
	
/*	
	private TextView readtxTextView;
	private EditText writetxTextView;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_file);
		Log.d(TAG, "onCreate 运行...");

		Button readButton = (Button)findViewById(R.id.file_readbn);
		Button writeButton = (Button)findViewById(R.id.file_writebn);
		readtxTextView = (TextView)findViewById(R.id.file_readtx);
		writetxTextView = (EditText)findViewById(R.id.file_writetx);
		
		readButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				readtxTextView.setText(read());
			}
		});
		
		writeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				write(writetxTextView.getText().toString());
				writetxTextView.setText("");
			}
		});
		
		
	}
	
	
	
	private String read(){
		try {
			FileInputStream fis = openFileInput(FILE_NAME);
			byte[] buffer = new byte[1024];
			int readnum = 0;
			StringBuilder sbBuilder  = new StringBuilder("");
			
			while((readnum = fis.read(buffer)) > 0){
				sbBuilder.append(new String(buffer,0,readnum));
			}
			fis.close();
			
			return sbBuilder.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private void write(String content){
		try {
			FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
			PrintStream ps = new PrintStream(fos);
			ps.println(content);
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
*/	
	
/*	
	SharedPreferences preferences = null;
	SharedPreferences.Editor editor = null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linerarlayout_sharepreferences);
		Log.d(TAG, "onCreate 运行...");
		
		preferences = getSharedPreferences("fileedit", MODE_PRIVATE);
		editor = preferences.edit();
		
		Button readButton = (Button)findViewById(R.id.share_bn1);
		Button writeButton = (Button)findViewById(R.id.share_bn2);
		Button clearButton = (Button)findViewById(R.id.share_bn3);
		
		TextView tx = (TextView)findViewById(R.id.share_tx);
		
		int startcount = preferences.getInt("startcount", 0);
		editor.putInt("startcount", ++startcount);
		editor.commit();
		tx.setText("启动次数: "+startcount);
		readButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String timeString =  preferences.getString("time", null);
				int randnum = preferences.getInt("randrom", 0);
				
				String resultString = timeString==null?"数据未写入数据":"写入时间: "+timeString+"\n随机数: "+randnum;
				
				Toast.makeText(MainActivity.this, resultString, Toast.LENGTH_LONG).show();
				
			}
		});
		
		writeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年 MM月 dd日 "+"hh:mm:ss");
				editor.putString("time", simpleDateFormat.format(new Date()));
				editor.putInt("randrom", (int)(Math.random()*100));
				editor.commit();
			}
		});
		
		clearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				editor.clear();
				editor.commit();
				
				Toast.makeText(MainActivity.this, "删除完成", Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}
	
*/	
/*
	final String TAG = "--CrazyIt--";
	public final static String CRAZYIT_ACTION ="com.example.androidtest.action.CRAZYIT_ACTION";  
	public final static String CRAZYIT_CATEGORY = "com.example.androidtest.action.CRAZYIT_CATEGORY";
	
	TextView tx = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_main);
		Log.d(TAG, "onCreate 运行...");
		
		Button startActivityButton = (Button)findViewById(R.id.main_startnewactivity);
		Button finishButton = (Button)findViewById(R.id.main_finish);
		Button bn1 = (Button)findViewById(R.id.main_button1);
		Button bn2 = (Button)findViewById(R.id.main_button2);
	    tx = (TextView)findViewById(R.id.main_textView);
		
		startActivityButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				//intent.setAction(Intent.ACTION_MAIN);
				//intent.addCategory(Intent.CATEGORY_HOME);
				String uriString = "http://www.baidu.com";
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(uriString));
				startActivity(intent);
			}
		});
		
		finishButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setType("xyz/abc");
				intent.setData(Uri.parse("lee://www.fack.ory:8888/test"));
				tx.setText(intent.toString());
			}
		});
		
		bn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setData(Uri.parse("lee://www.fack.ory:8888/test"));
				intent.setType("xyz/abc");
				tx.setText(intent.toString());
			}
		});
		
		bn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setDataAndType(Uri.parse("lee://www.fack.ory:8888/test"),"xyz/abc");
				tx.setText(intent.toString());
			}
		});
	}
	
*/		
/*	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_main);
		Log.d(TAG, "onCreate 运行...");
		
		Button startActivityButton = (Button)findViewById(R.id.main_startnewactivity);
		Button finishButton = (Button)findViewById(R.id.main_finish);
		
		startActivityButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,SecondActivity.class);
				startActivity(intent);
			}
		});
		
		finishButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivity.this.finish();
			}
		});
		
	}
	
	@Override
	public void onStart(){
		super.onStart();
		
		Log.d(TAG, "onStart 运行...");
	}
	
	@Override
	public void onRestart(){
		super.onRestart();
		
		Log.d(TAG, "onRestart 运行...");
	}

	@Override
	public void onResume(){
		super.onResume();
		
		Log.d(TAG, "onResume 运行...");
	}
	
	@Override
	public void onPause(){
		super.onPause();
		
		Log.d(TAG, "onPause 运行...");
	}
	
	@Override
	public void onStop(){
		super.onStop();
		Log.d(TAG, "onStop 运行...");
	}

	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d(TAG, "onDestroy 运行...");
	}
*/	
/*	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablelayout_editview);
		
		Button button = (Button)findViewById(R.id.logbutton1);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText nameEditText = (EditText)findViewById(R.id.editvieweditText1);
				EditText ageEditText = (EditText)findViewById(R.id.editvieweditText3);
				
				Person person = new Person(nameEditText.getText().toString(), ageEditText.getText().toString());
				
				Bundle bundle = new Bundle();
				bundle.putSerializable("person", (Serializable)person);
				
				Intent intent = new Intent(MainActivity.this,SecondActivity.class);
				//intent.putExtras(bundle);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}
	
*/
	
	
/*	
 * 
 *  //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!光子支付测试代码
 
	public static PosdService posdService = null;
    public static LightPayFuncs lightPayFuncs = null;
	public TextView editText =null;
	public String string  = new String();
    
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			if(msg.what == 0x123){
				editText.setText(string);
			}
		}
	};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_config);
		Button button = (Button)findViewById(R.id.bn1);
		Button buttoncancel = (Button)findViewById(R.id.bn2);
		 editText = (TextView)findViewById(R.id.tx);
		 
		

		bindPosdService();
				
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					editText.setText("");
					lightPayFuncs.start(new LightPayCallBack(), 60*1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		
		buttoncancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					editText.setText("");
					lightPayFuncs.cancel();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}	
	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
		String screenString = newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE?"横向屏幕":"竖向屏幕";
		Toast.makeText(this, "系统的屏幕方向发送改变"+"\n修改后的屏幕方向为:"+screenString,Toast.LENGTH_LONG).show();
	}




	
	private void bindPosdService() {
		Intent intent = new Intent();
		intent.setAction( "com.seuic.android.PosdService" );
		intent.setPackage("com.seuic.android");
		intent.putExtra( "CheckFirmwareUpdate", true );
		Log.d("Posd","posdConnection001");
		bindService( intent, posdConnection , BIND_AUTO_CREATE );
	}
	
	
    private ServiceConnection posdConnection = new ServiceConnection() {
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				Log.d("Posd","posdConnection");
				posdService = PosdService.Stub.asInterface( service );
				if ( null != posdService ) {
					try {
					
						lightPayFuncs = LightPayFuncs.Stub.asInterface(posdService.getExtraFuncs("LightPay"));
						
						//mHandler.sendEmptyMessage( MSG_START_CHECK_UPDATE );
					} catch (RemoteException e) {
						
					}			
				} else {
					
				}
			}
		
			@Override
			public void onServiceDisconnected(ComponentName name) {
				Log.d("Posd","posdConnection002");
				posdService = null;
		
			    lightPayFuncs = null;
			}
		
		};
				
	
	private class LightPayCallBack extends LightPayListener.Stub{

		@Override
		public int OnSuccess(byte[] lightdata, int lightdatalen,
				byte[] hardwareID) throws RemoteException {
			// TODO Auto-generated method stub
			StringBuffer strbuf = new StringBuffer();
			strbuf.append( "lightdatalen: " + lightdatalen
					+ "\nlightdata:" + HexUtil.byteToHexString( lightdata,lightdatalen,true) 
					+ "\nhardwareID: " + HexUtil.byteToHexString( hardwareID ));
			string = strbuf.toString();
			handler.sendEmptyMessage(0x123);
			return 0;
		}

		@Override
		public int OnFail(int returncode) throws RemoteException {
			// TODO Auto-generated method stub
			StringBuffer strbuf = new StringBuffer();
			strbuf.append("失败").append(returncode);
			string = strbuf.toString();
			handler.sendEmptyMessage(0x123);
			return 0;
		}
	
	}
*/
	
	/*	
	private int speed = 10;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		

		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		final PlaneView planeView = new PlaneView(this);
		setContentView(planeView);
		//planeView.setBackgroundResource(Color.BLACK);
		
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		
		display.getMetrics(metrics);
		
		planeView.setCurrentX(metrics.widthPixels/2);
		planeView.setCurrentY(metrics.heightPixels-80);
		
		planeView.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				float x,y;
				x = planeView.getCurrentX();
				y = planeView.getCurrentY();
				
				switch(event.getKeyCode()){
				case KeyEvent.KEYCODE_4:
					planeView.setCurrentX(x-speed);
					break;
				case KeyEvent.KEYCODE_6:
					planeView.setCurrentX(x+speed);
					break;
				case KeyEvent.KEYCODE_8:
					planeView.setCurrentY(y+speed);
					break;
				case KeyEvent.KEYCODE_2:
					planeView.setCurrentY(y-speed);
					break;	
				default:
					break;
				}
				planeView.invalidate();
				return true;
			}
		});
		
		
		planeView.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				planeView.setCurrentX(event.getX()-80);
				planeView.setCurrentY(event.getY()+80);
				planeView.invalidate();
				return true;
			}
		});
	}
*/	

/*	
	private static final String SELECTED_ITEM = null;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_fragment);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText("第一页").setTabListener((TabListener) this));
		actionBar.addTab(actionBar.newTab().setText("第二页").setTabListener((TabListener) this));
		actionBar.addTab(actionBar.newTab().setText("第三页").setTabListener((TabListener) this));
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState){
		if(savedInstanceState.containsKey(SELECTED_ITEM)){
			getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(SELECTED_ITEM));
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState){
		outState.putInt(SELECTED_ITEM, getActionBar().getSelectedNavigationIndex());
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		Fragment fragment = new Fragment();
		
		Bundle args = new Bundle();
		args.putInt(, value)
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
*/
/*	
	
	final int MENU1 = 0x111;
	final int MENU2 = 0x112;
	final int MENU3 = 0x113;
	
	TextView textView = null;
	PopupMenu popupMenu  = null;
	Button button = null;

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_menu);
		button = (Button)findViewById(R.id.popupmenubotton);
		textView = (TextView)findViewById(R.id.menuview);
		/*
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textView.setText("按键按下");
			}
		});
		
		button.setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
			//	onPopupButtonClick(v);
				return true;
			}
		});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.actionview, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
*/
/*	
	public void displayActionBar(View v){
		
	}
	
	public void hideActionBar(View v){
		
	}
*/	
/*	
	public void onPopupButtonClick(View button){
		popupMenu = new PopupMenu(this,button);
		getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
		popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				switch(item.getItemId()){
				case R.id.find:
					textView.setText("查找文字");
					break;
				case R.id.add:
					textView.setText("添加文字");
					break;
				case R.id.edit:
					textView.setText("请输入文字");
					break;
				case R.id.hide:
					popupMenu.dismiss();
					textView.setText("");
				default:
					break;
				}
				return false;
			}
		});
		popupMenu.show();
	}
*/	
/*	
	@Override
	public void onCreateContextMenu(ContextMenu menu,View source,ContextMenu.ContextMenuInfo memContextMenuInfo){
		menu.add(0, MENU1, 0, "红色");
		menu.add(0, MENU2, 0, "绿色");
		menu.add(0, MENU3, 0, "蓝色");
		
		menu.setGroupCheckable(0, true, true);
		menu.setHeaderIcon(R.drawable.b23_small);
		menu.setHeaderTitle("选择背景色");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem menuItem){
	
		switch(menuItem.getItemId()){
		case MENU1:
			menuItem.setCheckable(false);
			textView.setBackgroundColor(Color.RED);
			break;
		case MENU2:
			menuItem.setCheckable(false);
			textView.setBackgroundColor(Color.GREEN);
			break;
		case MENU3:
			menuItem.setCheckable(true);
			textView.setBackgroundColor(Color.BLUE);			
			break;
		default:
			break;
		}
		
		return true;
	}
*/	
	
/*	
	//定义"字体大小"菜单项的标识
	final int FONT_10 = 0x111;
	final int FONT_12 = 0x112;
	final int FONT_14 = 0x113;
	final int FONT_16 = 0x114;
	final int FONT_18 = 0x115;
	
	//定义普通菜单 ID
	final int PLAIN_ITEM = 0x11b;
	
	//定义 字体颜色 菜单表示
	final int FONT_RED = 0x116;
	final int FONT_BLUE = 0x117;
	final int FONT_GREEN = 0x118;
	
	private  TextView textView = null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_menu);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		SubMenu fontmenu = menu.addSubMenu("字体大小");
		fontmenu.setIcon(R.drawable.b23_small);
		fontmenu.setHeaderIcon(R.drawable.b23_small);
		fontmenu.setHeaderTitle("选择字体大小");
		fontmenu.add(0, FONT_10, 0, "10号字体");
		fontmenu.add(0, FONT_12, 0, "12号字体");
		fontmenu.add(0, FONT_14, 0, "14号字体");
		fontmenu.add(0, FONT_16, 0, "16号字体");
		fontmenu.add(0, FONT_18, 0, "18号字体");
		
		menu.add(0, PLAIN_ITEM, 0, "字体形状");
		
		SubMenu colormenu = menu.addSubMenu("字体颜色");
		colormenu.setIcon(R.drawable.b23_small);
		colormenu.setHeaderIcon(R.drawable.b23_small);
		colormenu.setHeaderTitle("选择字体颜色");
		colormenu.add(0, FONT_RED, 0, "红色");
		colormenu.add(0, FONT_BLUE, 0, "蓝色");
		//MenuItem menuItem = colormenu.add(0, FONT_GREEN, 0, "绿色");
		MenuItem menuItem = colormenu.add("绿色");
		menuItem.setIntent(new Intent(this, OtherActivity.class));
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem){
		textView = (TextView)findViewById(R.id.menuview);
		switch(menuItem.getItemId()){
		case FONT_10:
			textView.setTextSize(10*2);
			break;
		case FONT_12:
			textView.setTextSize(12*2);
			break;
		case FONT_14:
			textView.setTextSize(14*2);
			break;
		case FONT_16:
			textView.setTextSize(16*2);
			break;
		case FONT_18:
			textView.setTextSize(18*2);
			break;
		case FONT_RED:
			textView.setTextColor(Color.RED);
			break;
		case FONT_BLUE:
			textView.setTextColor(Color.BLUE);
			break;
		case FONT_GREEN:
			textView.setTextColor(Color.GREEN);
			break;
		case PLAIN_ITEM:
			//Toast.makeText(MainActivity.this, "暂不支持此功能", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		
		return true;
	}
*/	
/*	
	private final static int MAXPROCESS = 100;
	private  int[] date = new int[100];
	private  int processstatus  = 0;
	private  int hasdate=0;
	
	private static ProgressDialog progressDialog1 = null;
	
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			if(msg.what == 0x123){
				progressDialog1.setProgress(processstatus);
			}
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_processdialog);
	}
	
	public void showSpinner(View source){
		ProgressDialog.show(MainActivity.this, "任务1执行中", "任务执行中...请等待", true,true);
	}
	
	public void showIndeterminate(View source){
		ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
		progressDialog.setTitle("任务2执行中");
		progressDialog.setMessage("任务执行中...请稍候");
		progressDialog.setCancelable(true);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setIndeterminate(true);
		progressDialog.show();
	}
	
	public void showProcess(View source){
		processstatus = 0;
		hasdate = 0;
		progressDialog1 = new ProgressDialog(MainActivity.this);
		progressDialog1.setMax(MAXPROCESS);
		progressDialog1.setTitle("任务3执行中");
		progressDialog1.setMessage("任务执行中...");
		progressDialog1.setCancelable(false);
		progressDialog1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog1.setIndeterminate(false);
		progressDialog1.show();
		
		new Thread(){
			@Override
			public void run(){
				while(processstatus < MAXPROCESS){
					processstatus = MAXPROCESS*doWork()/date.length;
					handler.sendEmptyMessage(0x123);
				}
					
				if(processstatus >= MAXPROCESS){
					progressDialog1.dismiss();
				}
			}
		}.start();
	}
	
	public int doWork(){
		date[hasdate++] = 1;
		try {
			Thread.sleep(100);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return hasdate;
	}
*/	
/*	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_datepickerdialog);
		
		Button dateButton = (Button)findViewById(R.id.datebn);
		Button timeButton = (Button)findViewById(R.id.timebn);
		
		dateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar calendar = Calendar.getInstance();
				DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, 
					new DatePickerDialog.OnDateSetListener() {
						
						@Override
						public void onDateSet(DatePicker view, int year, int monthOfYear,
								int dayOfMonth) {
							// TODO Auto-generated method stub
							TextView textView = (TextView)findViewById(R.id.textView1) ;
							textView.setText("选择的时间:"+year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日");
						}
					},
						calendar.get(Calendar.YEAR), 
						calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH));
				
				datePickerDialog.show();
				datePickerDialog.setCancelable(false);
			}
		});
		
		timeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar calendar = Calendar.getInstance();
				TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
						new TimePickerDialog.OnTimeSetListener() {
							
							@Override
							public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
								// TODO Auto-generated method stub
								TextView textView = (TextView)findViewById(R.id.textView1) ;
								textView.setText("选择的时间:"+hourOfDay+"时"+minute+"分");
							}
						}, calendar.get(Calendar.HOUR), Calendar.MINUTE, false);
				timePickerDialog.show();
				timePickerDialog.setCancelable(false);
				
			}
		});
	}
*/	
/*	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_popupview);
	
		View  root = this.getLayoutInflater().inflate(R.layout.linearlayout_popupimage, null);
		final PopupWindow popupWindow = new PopupWindow(root,400,600);
		
		Button button = (Button)findViewById(R.id.popupbutton1);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupWindow.showAsDropDown(v);
				popupWindow.showAtLocation(findViewById(R.id.popupbutton1), Gravity.CENTER, 20, 20);
			}
		});
		
		root.findViewById(R.id.popupbutton1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
			}
		});
	}
*/	
/*	
	private static boolean dialogctr = false;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_alertdialog);
		dialogctr = false;
	}
	
	//-------------------------------------------------显示内容
	private void showText(int type,String str){
		  TextView textView = (TextView)findViewById(R.id.dialogView1);
		  StringBuffer stringBuffer = new StringBuffer();
		  
		  switch(type){
		  case 1:
			  stringBuffer.append("简单对话框-");
			  break;
		  case 2:
			  stringBuffer.append("简单列表项对话框-");
			  break;
		  case 3:
			  stringBuffer.append("单选列表项对话框-");
			  break;
		  case 4:
			  stringBuffer.append("多选列表项对话框-");
			  break;
		  case 5:
			  stringBuffer.append("自定义列表项-");
			  break;
		  case 6:
			  stringBuffer.append("自定义View-");
			  break;
		  case 7:
			  break;
		  case 8:
			  stringBuffer.append(textView.getText());
			  break;
		  default:
			  break;
		  }
		  
		  stringBuffer.append(str);
		  textView.setText(stringBuffer.toString());
	}
	
	//------------------------------------------------按钮设置
	private void setPositiveButton(AlertDialog.Builder builder){
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				showText(1,"【确定键】");
				dialogctr = false;
			}
		});
	}
	
	private void setNegativeButton(AlertDialog.Builder builder){
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				showText(1,"【取消键】");
				dialogctr = false;
			}
		});
	}
	
	private void setNeutralButton(AlertDialog.Builder builder){
		builder.setNegativeButton("中立", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				showText(1,"【中立键】");
				dialogctr = false;
			}
		});
	}
	//---------------------------------------------------简单对话框代码
	public void simpleDialog(View source){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("简单对话框标题");
		builder.setIcon(R.drawable.b23_small);
		builder.setMessage("简单对话框内容第一行\n内容第二行");
		setPositiveButton(builder);
		setNegativeButton(builder);
		builder.create();
		builder.show();
		
	}
	
	public void dialogbn1(View source){
		simpleDialog(source);
	}
	
	//-------------------------------------------------简单列表项对话框
	final private String[] items = new String[]{
			"苏州市民卡",
			"苏州独墅湖卡",
			"苏州文体卡",
			"交通联合卡片",
	};
	final private boolean[] choice = new boolean[]{
			false,false,false,false,
	};
	
	public void simpleListDialog(View source){		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("简单列表对话框标题");
		builder.setIcon(R.drawable.b23_small);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				showText(2,"选择对象:"+items[which]);
			}
		});
		//setPositiveButton(builder);
		setNegativeButton(builder);
		//setNeutralButton(builder);
		builder.create();
		builder.show().setCancelable(false);
		
	}
	
	public void dialogbn2(View source){
		simpleListDialog(source);
	}
	
	//-------------------------------------------------单项选择对话框
	public void singleChioceDialog(View source){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("单项列表对话框").setIcon(R.drawable.b23_small)
			   .setSingleChoiceItems(items, 100, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					showText(7,"选择对象:"+items[which]);
				}
			});
		setNegativeButton(builder);
		setPositiveButton(builder);
		builder.create().show();
	}
	
	public void dialogbn3(View source){
		singleChioceDialog(source);
	}
	
	//-------------------------------------------------多项选择对话框	
	public void multChioceDialog(View source){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("多项列表对话框").setIcon(R.drawable.b23_small)
		       .setMultiChoiceItems(items, choice, new DialogInterface.OnMultiChoiceClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					// TODO Auto-generated method stub
					showText(8,"选择对象:"+which + "是否选择:"+isChecked);
				}
			});
		setNegativeButton(builder);
		setPositiveButton(builder);
		builder.create().show();
	}
	
	public void dialogbn4(View source){
		multChioceDialog(source);
	}
	
	//------------------------------------------------自定义列表项对话框
	public void customListDialog(View source){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("自定义列表项对话框").setIcon(R.drawable.b23_small)
		       .setAdapter(new ArrayAdapter<String>(this, R.layout.item_array, items), null);
		setNegativeButton(builder);
		setPositiveButton(builder);
		builder.create().show();
	}
	
	public void dialogbn5(View source){
		customListDialog(source);
	}
	
	//---------------------------------------------自定义View
	public void customView(View source){
		TableLayout tableLayout = (TableLayout)getLayoutInflater().inflate(R.layout.tablelayout_log, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("自定义View框图").setIcon(R.drawable.b23_small)
		       .setView(tableLayout);
		setNegativeButton(builder);
		setPositiveButton(builder);
		builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				dialogctr = false;
			}
		});
		builder.create();
		builder.show().setCanceledOnTouchOutside(false);
	}
	
	public void dialogbn6(View source){
		if(!dialogctr)
		{	
			dialogctr = true;
			customView(source);
		}	
	}
*/	
/*	
	final int NOTIFICATION_ID = 0x123;
	NotificationManager notificationManager = null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_notification);
		
		notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	}
	
	public void send(View source){
		Intent intent = new Intent();
		
		PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
		
		Notification notification = new Notification.Builder(this)
			.setAutoCancel(true)
			.setTicker("QQ消息-小玉发来消息")
			.setSmallIcon(R.drawable.image1)
			.setContentTitle("通知内容标题")
			.setContentText("这是Notification的实际内容--还钱 还钱AAAAAAAAAA")
			.setDefaults(Notification.DEFAULT_ALL)
			.setWhen(System.currentTimeMillis())
			.setContentIntent(pendingIntent)
			.build();
		
		notificationManager.notify(NOTIFICATION_ID, notification);
		
	}
	
	public void cancel(View source){
		notificationManager.cancel(NOTIFICATION_ID);
	}
*/	
/*	
	/* MainActivity 需要继承  TabActivity *//*
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.tabhost);
		
		TabHost tabHost  = getTabHost();
		
		TabSpec  tabSpec1 = tabHost.newTabSpec("tab01").setIndicator("已接电话").setContent(R.id.tab01);
		tabHost.addTab(tabSpec1);
		
		TabSpec  tabSpec2 = tabHost.newTabSpec("tab02").setIndicator("呼出电话").setContent(R.id.tab02);
		tabHost.addTab(tabSpec2);
		
		TabSpec  tabSpec3 = tabHost.newTabSpec("tab03").setIndicator("未接电话").setContent(R.id.tab03);
		tabHost.addTab(tabSpec3);
	}
*/	
/*	
	ListView listView = null;
	SearchView searchView = null;
	String[] arrString = new String[]{
			"aaaaaaaa",
			"aaaawddddqd",
			"bbbbbbb",
			"中国人的慰问",
			"32222222222",
	};
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_searchview);
		
		listView = (ListView)findViewById(R.id.searchlistView1);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrString);
		listView.setAdapter(adapter);
		listView.setTextFilterEnabled(true);
		
		searchView = (SearchView)findViewById(R.id.searchView);
		searchView.setIconifiedByDefault(true);
		searchView.setSubmitButtonEnabled(true);
		searchView.setQueryHint("查找");
		
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "查找内容"+query, Toast.LENGTH_SHORT).show();
				return true;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				if(TextUtils.isEmpty(newText)){
					listView.clearTextFilter();
				}else{
					listView.setFilterText(newText);
				}
				return true;				
			}
		});
		
	}
*/	
/*	
	NumberPicker numberPicker = null;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_numberpicker);
		
		numberPicker = (NumberPicker)findViewById(R.id.numberPicker);
		
		numberPicker.setMinValue(20);
		numberPicker.setMaxValue(60);
		numberPicker.setValue(35);
		
		numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "你选择的号码:"+newVal+"之前的号码:"+oldVal, Toast.LENGTH_SHORT).show();
			}
		});
	}
*	
/*	
	private DatePicker datePicker = null;
	private TimePicker timePicker = null;
	private TextView textView = null;
	 int year;
	 int month;
	 int monthday;
	 int hour;
	 int minute;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_picker);
		
		datePicker = (DatePicker)findViewById(R.id.datePicker);
		timePicker = (TimePicker)findViewById(R.id.timePicker);
		
		Calendar calendar = Calendar.getInstance();
		year     = calendar.get(Calendar.YEAR);
		month    = calendar.get(Calendar.MONTH);
		monthday = calendar.get(Calendar.DAY_OF_MONTH);
		hour     = calendar.get(Calendar.HOUR);
		minute   = calendar.get(Calendar.MINUTE);
		
		datePicker.init(year, month, monthday, new DatePicker.OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				MainActivity.this.year   = year;
				MainActivity.this.month  = monthOfYear;
				MainActivity.this.monthday = dayOfMonth;
				showSelectDate(year,month,monthday,hour,minute);
			}
		});
		
		timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				hour = hourOfDay;
				minute = minute;
				showSelectDate(year,month,monthday,hour,minute);
			}
		});
	}
	
	@SuppressLint("ResourceAsColor")
	private void showSelectDate(int year,int month,int monthday,int hour,int minute){
		
		textView   = (TextView)findViewById(R.id.pickshow);
		textView.setText( year+"年"+(month+1)+"月"+monthday+ "号  "+hour+":"+minute);
		textView.setTextColor(R.color.color1);
	}

*/


/*	
	private CalendarView calendarView = null;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_calendarview);
		calendarView  = (CalendarView)findViewById(R.id.calendar);
		Calendar calendar = Calendar.getInstance();
		int year   = calendar.get(Calendar.YEAR);
		int month  = calendar.get(Calendar.MONTH);
		int monthday = calendar.get(Calendar.MONDAY);
		
		calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, year+"年"+(month+1)+"月"+dayOfMonth+ "号", Toast.LENGTH_SHORT).show();
			}
		});
	}	
*/	
/*	
	private Button simplebutton = null;
	private Button compixbutton = null;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.linearlayout_toast);
		
		simplebutton = (Button)findViewById(R.id.toastbutton1);
		compixbutton = (Button)findViewById(R.id.toastbutton2);

		
		simplebutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(MainActivity.this, "这是一个简单的提示信息", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
				toast.show();
			}
		});
	
		compixbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast toast1 = new Toast(MainActivity.this);
				
				LinearLayout linearlayout = new LinearLayout(MainActivity.this);
				linearlayout.setOrientation(LinearLayout.HORIZONTAL);
				
				ImageView imageview = new ImageView(MainActivity.this);
				imageview.setImageResource(R.drawable.red);
				
				TextView textview = new TextView(MainActivity.this);
				textview.setText("只是一个构造的提示信息");
				textview.setTextColor(Color.RED);
				textview.setTextSize(24);
				
				linearlayout.addView(imageview);
				linearlayout.addView(textview);
				
				toast1.setView(linearlayout);
				toast1.setGravity(Gravity.CENTER, 0, 0);
				toast1.setDuration(Toast.LENGTH_LONG);
				
				toast1.show();
			}
		});
	
	}
*/	
/*	
	private ImageView imageview = null;
	private TextView textview =null;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.relativelayout_seekbar);
		
		imageview = (ImageView)findViewById(R.id.seek_image1);
		textview = (TextView)findViewById(R.id.seekView1);
		
		SeekBar seekbar = (SeekBar)findViewById(R.id.seekBar1);
		RatingBar ratingbar = (RatingBar)findViewById(R.id.ratingBar1);
		seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				imageview.setImageAlpha(progress);
				textview.setText(String.valueOf(progress));
			}
		});
		
		ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				imageview.setImageAlpha((int)(rating*255/5));
				textview.setText(String.valueOf(rating));
			}
		});
	}
*/	
/*	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);		
		setContentView(R.layout.linearlayout_titlebar);
		
		Button button1  = (Button)findViewById(R.id.titlebar_bn1);
		Button button2  = (Button)findViewById(R.id.titlebar_bn2);
		
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setProgressBarVisibility(true);
				setProgressBarIndeterminateVisibility(true);
				setProgress(4500);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setProgressBarVisibility(false);
				setProgressBarIndeterminateVisibility(false);
			}
		});
	}
*/	
/*	
	private int[] data = new int[100];
	private int hasdata  =0;
	private int status;
	private ProgressBar bar1,bar2;
	
	Handler handler = new Handler(){
		
		@Override
		public void handleMessage(Message msg){
			if(msg.what == 0x111){
				bar1.setProgress(status);
			}else if(msg.what == 0x112){
				bar1.setProgress(0);
			}
				
		}
	};
	
	public int doWork(){
		data[hasdata++] = (int) (Math.random()*100);
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return hasdata;
	}
	
	public int finshWork(){
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_processbar);
	
		bar1 = (ProgressBar)findViewById(R.id.progressBar4);
		bar1.setMax(100);
		new Thread(){
				public void run(){
					while(status<100){
						status = doWork();
						handler.sendEmptyMessage(0x111);
					}
					finshWork();
					handler.sendEmptyMessage(0x112);
				}
		}.start();
		
		

	}
*/	
/*	
	private int[] images = new int[]{
			R.drawable.diaoxiang,
			R.drawable.flower,
			R.drawable.huanghun,
			R.drawable.lovegire,
			R.drawable.meihua,
			R.drawable.monter,
			R.drawable.stone,
			R.drawable.stonetext,
			R.drawable.tiankong,
			R.drawable.xiyang,
	};
	
	private AdapterViewFlipper flipper = null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relativelayout_adapterviewflipper);
		flipper = (AdapterViewFlipper) findViewById(R.id.flipper);
		BaseAdapter baseAdapter = new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ImageView imageview = new ImageView(MainActivity.this);
			
				InputStream isInputStream = getResources().openRawResource(images[position]);
				Bitmap bitmap = BitmapFactory.decodeStream(isInputStream);
				
				
				/******************************************************************************************
				******************************************************************************************
				******************************************************************************************
				********************************     未解决的问题           *************************************
				******************************************************************************************
				******************************************************************************************
				******************************************************************************************//*
				ExifInterface exifInterface =null;
				try {
					exifInterface = new ExifInterface(String.valueOf(images[position]));
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
					exifInterface = null;
					return null;
				}
				int degree =0;
				if(exifInterface != null){
					int ori = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
				
					switch(ori){
						case ExifInterface.ORIENTATION_ROTATE_90:
							degree = 90;
							break;
						case ExifInterface.ORIENTATION_ROTATE_180:
							degree = 180;
							break;
						case ExifInterface.ORIENTATION_ROTATE_270:
							degree = 270;
							break;
						default:
							degree  =0;
							break;							
					}
				}
				
				if(degree != 0){
					Matrix matrix = new Matrix();
					matrix.postRotate(degree);
					bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
				}
				imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageview.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
				return imageview;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return images[position];
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return images.length;
			}
		};
		
		flipper.setAdapter(baseAdapter);
	}	
	
	public void prevOnClick(View source){
		flipper.showPrevious();
		flipper.stopFlipping();
	}
	
	public void autoOnClick(View source){
		flipper.startFlipping();
	}
	
	public void nextOnClick(View source){
		flipper.showNext();
		flipper.stopFlipping();
	}
*/	
/*	
 * 
	List<String> list = null;
	
	TextView textView = null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_spinner);
		
		textView = (TextView)findViewById(R.id.show);
		list = new ArrayList<String>();
		list.add("三国演艺");
		list.add("红楼梦");
		list.add("水浒传");
		list.add("西游记");
		list.add("西游记");
		list.add("12345");
		list.add("2132132132131");
		list.add("fewfwfwqwfwfq");
		list.add("6764747457574575");
		
		Spinner spinner = (Spinner)findViewById(R.id.auto_spinner2);
		Spinner spinner2 = (Spinner)findViewById(R.id.static_spinner1);
		
		spinner.setPrompt("选择你喜欢的书籍");
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
		
		spinner.setAdapter(arrayAdapter);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				textView.setText("您喜欢的书籍: "+arg0.getItemAtPosition(arg2).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				textView.setText("");
			}
			
		});
		
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				//Toast.makeText(MainActivity.this, (int) arg0.getItemIdAtPosition(arg2), Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
*/	
	
/*	
	final private String[] armtype  = new String[]{
			"神族兵种",
			"虫族兵种",
			"人族兵种",
			"精灵兵种",
	};
	final private String[][] arms = new String[][]{
			{"狂战士","龙骑士","黑暗圣堂","电兵","天兵",},
			{"小狗","刺蛇","飞龙","地狱神龟","黑皮",},
			{"机枪兵","护士MM","幽灵","自爆飞机",},
			{"弓箭手","带刀护卫第三方i萨佛i阿凡我佛佛哦哦iwe",},
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_expandable);
		
		ExpandableListAdapter expandableListActivity = new BaseExpandableListAdapter() {
			
			private TextView getGroupTextView() {
				AbsListView.LayoutParams lpLayoutParams = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,64);
				
				TextView textView = new TextView(MainActivity.this);
				textView.setLayoutParams(lpLayoutParams);
				textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
				textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(30);
				return  textView;
			}
			
			private TextView getChildTextView() {
				AbsListView.LayoutParams lpLayoutParams = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,64);
				
				TextView textView = new TextView(MainActivity.this);
				textView.setLayoutParams(lpLayoutParams);
				textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
				textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(20);
				textView.setTransformationMethod(SingleLineTransformationMethod.getInstance());
				textView.setTextColor(getResources().getColor(R.color.color1));
				return  textView;
			}
			
			@Override
			public boolean isChildSelectable(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				LinearLayout linearLayout = new LinearLayout(MainActivity.this);
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				
				TextView textView = getGroupTextView();
				textView.setText(getGroup(groupPosition).toString());
				linearLayout.addView(textView);
				return linearLayout;
			}
			
			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return groupPosition;
			}
			
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return armtype.length;
			}
			
			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return armtype[groupPosition];
			}
			
			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return arms[groupPosition].length;
			}
			
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				TextView textView = getChildTextView();
				textView.setText(getChild(groupPosition, childPosition).toString());
				return textView;
			}
			
			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				
				return childPosition;
			}
			
			@Override
			public Object getChild(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return arms[arg0][arg1];
			}
		};
		
		ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.expandableListView1);
		expandableListView.setAdapter(expandableListActivity);
	}
*/	
/*	
	AutoCompleteTextView autoCompleteTextView=null;
	MultiAutoCompleteTextView multiAutoCompleteTextView = null;
	
	final private String[] books = new String[]{
			"",
			"疯狂JAVA讲义",
			"疯狂的人民日报",
			"疯狂的s阿萨法撒旦法",
			"疯狂的收费服务",
			"中国的的无穷大的",
			"中国的微积分我就服务范围批发价为肌肤"
	};
			
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_autocompletetextview);
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, books);
		
		autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.auto);
		autoCompleteTextView.setAdapter(arrayAdapter);
		
		multiAutoCompleteTextView = (MultiAutoCompleteTextView)findViewById(R.id.mauto);
		multiAutoCompleteTextView.setAdapter(arrayAdapter);
		
		multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		
	
	}
*/	
/*	
	final private String[] namesStrings = new String[]{
		"多拉多","李清照","马文才","虎子","周星星","二狗子","冬天里的一把火 火 火 火 火 火 火 火 火 火","悟空"	,"心如",
	};
	
	final private String[] deceStrings = new String[]{
			"一个二次元世界的人",
			"一个擅长文学的女子",
			"一个闷骚的男人",
			"喝了二锅头的骚年",
			"喜剧之王",
			"骨头的1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"寂寞沙洲冷",
			"如来神掌",
			"最美的人",
	};
	
	final private int[] imageidID = new int[]{
			R.drawable.image1,
			R.drawable.image2,
			R.drawable.image3,
			R.drawable.image4,
			R.drawable.image5,
			R.drawable.image6,
			R.drawable.image7,
			R.drawable.image8,
			R.drawable.image9,
		
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_simpleadapter);
		
		List<Map<String, Object>> listitems = new ArrayList<Map<String,Object>>();
			for(int i=0;i< namesStrings.length;i++){
				Map<String, Object> items = new HashMap<String, Object>();
				items.put("headimage",  imageidID[i]);
				items.put("personname", namesStrings[i]);
				items.put("dece",       deceStrings[i]);
				
				listitems.add(items);
			}

		SimpleAdapter simpleAdapter = new SimpleAdapter(this, 
				listitems, 
				R.layout.simple_item, 
				new String[]{"headimage",   "personname",   "dece"}, 
				new int[]{    R.id.header,  R.id.name,      R.id.desc});
		
		ListView listView = (ListView)findViewById(R.id.listid);
		listView.setAdapter(simpleAdapter);
	}
*/
/*	
	final String[] arrayStrings = new String[]{
			"周星驰",
			"刘德华",
			"陆毅",
			"张毅",
			"姜文",
			"卢雪松",
	};
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_listview);
		
		ListView listview = (ListView)findViewById(R.id.list1);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_array, arrayStrings);
		listview.setAdapter(arrayAdapter);
	}
*/	
/*	
	private int alpha=255;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linerarlayout_imageview);
		
		final ImageView image1  = (ImageView)findViewById(R.id.image1);
		final ImageView image2  = (ImageView)findViewById(R.id.image2);
		final Button puls       = (Button)findViewById(R.id.puls);
		final Button minus      = (Button)findViewById(R.id.minus);
		
		View.OnClickListener shortlistener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v==puls){
					alpha +=10;
				}
				else {
					alpha -=10;
				}
				
				if(alpha >=255)
				{
					alpha=255;
				}
				else if(alpha<=0)
				{
					alpha =0;
				}
			
				image1.setImageAlpha(alpha);
			}
		};
		
		View.OnTouchListener longClickListener = new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(v==puls){
					alpha++;
				}
				else {
					alpha--;
				}
				
				if(alpha >=255)
				{
					alpha=255;
				}
				else if(alpha<=0)
				{
					alpha =0;
				}
			
				image1.setImageAlpha(alpha);
				return false;
			}
		};

		puls.setOnClickListener(shortlistener);
		minus.setOnClickListener(shortlistener);
		
		puls.setOnTouchListener(longClickListener);
		minus.setOnTouchListener(longClickListener);
		
		image1.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				BitmapDrawable bitmapDrawable = (BitmapDrawable)image1.getDrawable();
				Bitmap bitmap = bitmapDrawable.getBitmap();
				double scale = 1.0*bitmap.getHeight()/image1.getHeight();
				int x = (int)(event.getX()*scale);
				int y = (int)(event.getY()*scale);
				if(x+100 >bitmap.getWidth())
				{
					x = bitmap.getWidth()-100;
				}
				if(y+100 >bitmap.getHeight())
				{
					y = bitmap.getHeight()-100;
				}	
				
				image2.setImageBitmap(Bitmap.createBitmap(bitmap, x, y, 100, 100));
				image2.setImageAlpha(alpha);
				return false;
			}
		});
	}
*/	
/*	
	ToggleButton toggleButton=null;
	Switch switcher  = null;
	TextView textView = null;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_togglebutton);
		
		toggleButton = (ToggleButton)findViewById(R.id.toggle);
		switcher = (Switch)findViewById(R.id.switcher);
		textView = (TextView)findViewById(R.id.show);
		final LinearLayout linearLayout =(LinearLayout)findViewById(R.id.testlinearlayout);
		
		OnCheckedChangeListener listener = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					linearLayout.setOrientation(LinearLayout.HORIZONTAL);
					toggleButton.setChecked(true);
					switcher.setChecked(true);
				}else{
					linearLayout.setOrientation(LinearLayout.VERTICAL);
					toggleButton.setChecked(false);
					switcher.setChecked(false);
				}
				
				String tip = (buttonView.getId() == R.id.toggle?"操作者:Toggle":"操作者:Switcher");
				textView.setText(tip);
			}
		};
		
		toggleButton.setOnCheckedChangeListener(listener);
		switcher.setOnCheckedChangeListener(listener);
		
	}
*/	
/* 	
	static RadioGroup radioGroup=null;
	int radiocheckedId;
	TextView textView = null;
	static String red,bule,purple;
	CheckBox redCheckBox = null;
	CheckBox buleCheckBox = null;
	CheckBox purpleCheckBox = null;
	
	public String showInfo(int checkedId,String red,String bule,String purple){
		StringBuffer stringBuffer = new StringBuffer();
		
		stringBuffer.append((checkedId == R.id.male)?"您的性别是男":"您的性别是女");
		
		if(red != null || bule != null || purple != null)
			stringBuffer.append("  你喜欢的颜色:");
		
		if(red != null){
			stringBuffer.append(red).append(" ");
		}
		if(bule != null){
			stringBuffer.append(bule).append(" ");
		}
		if(purple != null){
			stringBuffer.append(purple).append(" ");
		}
		
		return stringBuffer.toString();
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablelayout_editview);
		
		radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		textView = (TextView)findViewById(R.id.textView8);
		redCheckBox = (CheckBox)findViewById(R.id.red);
		buleCheckBox = (CheckBox)findViewById(R.id.bule);
		purpleCheckBox = (CheckBox)findViewById(R.id.purple);
		
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				radiocheckedId=checkedId;
				textView.setText(showInfo(radiocheckedId, red, bule, purple));
			}
		});
		
		redCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					red = "红色";
					
				}else {
					red=null;
				}
				textView.setText(showInfo(radiocheckedId, red, bule, purple));
			}
		});
		
		buleCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					bule = "蓝色";
					
				}else {
					bule=null;
				}
				textView.setText(showInfo(radiocheckedId, red, bule, purple));
			}
		});
		
		purpleCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					purple = "紫色";
					
				}else {
					purple=null;
				}
				textView.setText(showInfo(radiocheckedId, red, bule, purple));
			}
		});
	}
*/
	
/* 网格布局 2016.12.22
	GridLayout gridLayout;
	
	String[] chars = new String[]{
			"7","8","9","÷",
			"4","5","6","×",
			"1","2","3","-",
			"-","0","=","+",
	};
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridlayout);
		gridLayout = (GridLayout)findViewById(R.id.root);
		for(int i=0;i<chars.length;i++){
			Button bn = new Button(this);
			bn.setText(chars[i]);
			bn.setTextSize(40);
			bn.setPadding(5, 35, 5, 35);
			
			GridLayout.Spec rowSpec = GridLayout.spec(i/4+2);
			GridLayout.Spec columnSpec = GridLayout.spec(i%4);
			GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec,columnSpec);
			params.setGravity(Gravity.FILL);
			gridLayout.addView(bn, params);
		}
	}
*/	
	
/*	 //  为帧布局代码 2016.12.21
	
	private int currentcolor = 0;
	
	private final int[] colors = new int[]{
			R.color.color1,
			R.color.color2,
			R.color.color3,
			R.color.color4,
			R.color.color5,
			R.color.color6,
			R.color.color7,
			R.color.color8,			
	};
	
	private final int[] name = new int[]{
			R.id.View1,
			R.id.View2,
			R.id.View3,
			R.id.View4,
			R.id.View5,
			R.id.View6,
			R.id.View7,
			R.id.View8,			
	};
	
	TextView[] textView = new TextView[name.length];
	
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			if(msg.what == 0x123){
				for(int i=0;i<name.length;i++){
					textView[i].setBackgroundResource(colors[(i+currentcolor)%name.length]);
				}
				currentcolor++;
			}
			
			super.handleMessage(msg);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.framelayout_main);
		for(int i = 0;i<name.length;i++){
			textView[i] = (TextView)findViewById(name[i]);
		}
		//---------------定义一个周期线程
        new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msgMessage = new Message();
				msgMessage.what = 0x123;
				handler.sendMessage(msgMessage);
			}
		}, 0, 300);
	}
*/

/*
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
*/
}
