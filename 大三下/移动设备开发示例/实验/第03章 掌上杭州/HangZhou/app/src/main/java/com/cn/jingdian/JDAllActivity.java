package com.cn.jingdian;

import com.cn.hangzhou.R;
import com.cn.util.BitmapIOUtil;
import com.cn.util.Constant;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class JDAllActivity extends Activity {
	public static String[] name;//景点名字
	public static String[] tpname;//景点的图片的名字
	public static int count;//数组长度
	public static double[] jdata;
	public static double[] vdata;
	String szzy=Constant.snzy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jingdian_all);//转到景点介绍界面
		 //用自定义的字体方法
        FontManager.changeFonts(FontManager.getContentView(this),this);
				//获得返回按钮的引用
		Button btback=(Button)findViewById(R.id.allback);
		btback.setOnClickListener(
				new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
					  finish();//关闭此Activity
					}					
				});
		intitAll();
	}
	public void intitAll()
	{
		 Constant.List=PubMethod.loadFromFile("jingdian/"+szzy+"/"+"hname.txt");
		 Constant.ListArray=Constant.List.split("\\|");
			//获取数组长度
		 final int count=Constant.ListArray.length/4;//11
		 name=new String[count];//长度11
	     tpname=new String[count];//长度11
	     jdata=new double[count];
	     vdata=new double[count];
	   	  
		 for(int i=0;i<count;i++)
	     {
	    	name[i]=Constant.ListArray[4*i];//名字(汉字)
	    	vdata[i]=Double.valueOf(Constant.ListArray[4*i+1]);//景点的纬度
	    	jdata[i]=Double.valueOf(Constant.ListArray[4*i+2]);//景点的经度
	    	tpname[i]=Constant.ListArray[4*i+3];//图片的名字
	    	System.out.println("这些是景点的名字："+name[i]);
	    	System.out.println("这些是景点图片的名字："+tpname[i]);
	    	System.out.println("这些是纬度："+jdata[i]);
	    	System.out.println("这些是经度："+vdata[i]);
	    	
	    	
	     }
		 BaseAdapter adapter = new BaseAdapter(){

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return count;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return arg0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				//拿到一个LayoutInflater
				LayoutInflater factory=LayoutInflater.from(JDAllActivity.this);
				//将自定义的griditem.xml实例化,转换为View
				View view=(View)factory.inflate(R.layout.listitem, null);
				//从自定义页面中拿到控件引用并赋值
				ImageView iv=(ImageView)view.findViewById(R.id.piciv);
					
				iv.setImageBitmap(BitmapIOUtil.getSBitmap("jingdian/pic/"+tpname[position]+1+".jpg"));//设置图片资源
				iv.setScaleType(ImageView.ScaleType.FIT_XY);
				TextView showTitle=(TextView)view.findViewById(R.id.showTitle);
				showTitle.setText(name[position]);//设置景点名称
//				showTitle.setTextColor(JDAllActivity.this.getResources().getColor(Constant.COLOR));
 			    TextView showDistance=(TextView)view.findViewById(R.id.showDistance);//显示距离文本控件
// 			    showDistance.setTextColor(JDAllActivity.this.getResources().getColor(Constant.COLOR));
				if(Constant.myLocation!=null){
					double dis=Constant.jWD2M(vdata[position],
							jdata[position],//参数第一个是纬度，第二个是经度经度，而得到的数组第一个是经度，第二是纬度
					Constant.myLocation.getLatitude(), Constant.myLocation.getLongitude());//计算与之前位置的距离
					if(dis<Constant.DISTANCE_SCENIC){
						showDistance.setText(getResources().getString(R.string.curs));
					}else{
						showDistance.setText(dis+getResources().getString(R.string.unit));//getResources().getString(R.string.showdis)
					}
				}else{
					showDistance.setText(getResources().getString(R.string.GPSFailed));
				}
				return view;
			}
			 
		 };
		 GridView showS=(GridView)findViewById(R.id.lvshow);
		 showS.setAdapter(adapter);
		 showS.setOnItemClickListener(
					new OnItemClickListener(){
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							Intent intent=new Intent(JDAllActivity.this,JDNewActivity.class);
							intent.putExtra("nearlyname", tpname[arg2]);//添加所进入景点名字的附加信息
				     		intent.putExtra("nearlyhm", name[arg2]);//添加所进入景点汉语名字的附加信息
				    		intent.putExtra("isAll",true);//添加一个标志位，用于判断是否从所有景点列表界面转到显示信息介绍页面的
				    		startActivity(intent);//开启Activity
						}		
					}
					);
    } 
}
