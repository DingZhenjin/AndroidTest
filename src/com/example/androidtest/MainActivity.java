package com.example.androidtest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.params.RggbChannelVector;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.R.array;
import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.app.DownloadManager.Request;
import android.app.ExpandableListActivity;
import android.support.v7.widget.GridLayout;
import android.text.method.SingleLineTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterViewFlipper;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
