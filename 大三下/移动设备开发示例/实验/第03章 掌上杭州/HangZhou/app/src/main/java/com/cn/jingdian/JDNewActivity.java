package com.cn.jingdian;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.cn.hangzhou.R;
import com.cn.util.BitmapIOUtil;
import com.cn.util.Constant;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;

@SuppressWarnings("deprecation")
public class JDNewActivity extends Activity {
	int size_index;
	TextView rvIntro;
	public String nearlyname;
	public static  Bitmap[] imageIDs;
	public String nearlyhm;
	String szzy=Constant.snzy;
	public void onCreate(Bundle savedInstanceState) 
	{
		 super.onCreate(savedInstanceState);  
	     requestWindowFeature(Window.FEATURE_NO_TITLE);   //去掉标题栏
	     SDKInitializer.initialize(getApplicationContext()); 
	     setContentView(R.layout.jingdian_new);
	     //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	     //得到当前景点的id号
		 Intent intent=getIntent();
		// boolean flag=intent.getBooleanExtra("isAll", false); //获得标志，是否从所有景点列表跳转来
		 nearlyname=intent.getStringExtra("nearlyname");
		 nearlyhm=intent.getStringExtra("nearlyhm");
		 imageIDs=new Bitmap[5];
	      for(int i=0,j=1;i<5;i++,j++)               //确定有三张图片
	      {
	    	  imageIDs[i]=BitmapIOUtil.getSBitmap("jingdian/pic/"+nearlyname+j+".jpg");
	      }
	      
		 Gallery ga=(Gallery)findViewById(R.id.galleryshow);//获得画廊控件引用
	      //设置适配器
	      BaseAdapter ba=new BaseAdapter()
	        {
				@Override
				public int getCount() {
					return imageIDs.length;
				}

				@Override
				public Object getItem(int arg0) {			    
					return null;
				}

				@Override
				public long getItemId(int arg0) {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public View getView(int arg0, View arg1, ViewGroup arg2) {
					ImageView iv = new ImageView(JDNewActivity.this);
					iv.setImageBitmap(imageIDs[arg0]);
					iv.setScaleType(ImageView.ScaleType.FIT_XY);
					iv.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
					return iv;
				}        	
	        };
	        
	        //将适配器添加进控件
	        ga.setAdapter(ba);
	      //为Gallery控件添加监听事件，实现点击放大的效果
			ga.setOnItemClickListener(
					new OnItemClickListener(){
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							 //选中项目，跳转到下一界面
							Intent intent=new Intent();
						    intent.setClass(JDNewActivity.this,JDMaxActivity.class);
							intent.putExtra("num",arg2);
							startActivity(intent);
						}
					}
					);
			//设置中间的图片为选中
			ga.setSelection(ga.getCount()/2); 
	      //获得最上方显示景点名称的textview
		   TextView rvName=(TextView)findViewById(R.id.showName);
		   rvName.setText(nearlyhm);
		   String information=PubMethod.loadFromFile("jingdian/"+szzy+"/"+nearlyname+".txt"); 
		   rvIntro=(TextView)findViewById(R.id.showIntro);		
		   rvIntro.setTextSize(Constant.TEXT_SIZES[size_index]);//设置显示的字体大小
		   rvIntro.setText(information);
		   rvIntro.setTextColor(JDNewActivity.this.getResources().getColor(Constant.COLOR));
////		 //得到播放控制按钮
//			final Button playbt=(Button)findViewById(R.id.soundbt);
//			//根据播放器状态初始化喇叭按钮图标
//			if(Constant.isPlay==0||Constant.isPlay==1){//若播放状态为还未播放及正在播放0代表当前无景点介绍，1 代表正在播放，2 代表暂停
//				playbt.setBackgroundResource(R.drawable.soundopen);//设图标为开
//			}else if(Constant.isPlay==2 && nearlyname.equals(Constant.curScenicId)){//若之前为暂停状态，且显示的是同一景点
//				playbt.setBackgroundResource(R.drawable.soundclose);//设图标为关
//			}else if(Constant.isPlay==2 && nearlyname.equals(Constant.curSMP)){
//				playbt.setBackgroundResource(R.drawable.soundclose);//设图标为关
//			}else{
//				playbt.setBackgroundResource(R.drawable.soundopen);//设图标为开
//			}
//			//初始化进入页面时播放状态为正在播放
//			outer:	if(Constant.isPlay==0 || !nearlyname.equals(Constant.curSMP) || !nearlyname.equals(Constant.curScenicId) ){
//					Constant.isPlay=1;//设置标志为正在播放
//					if(flag && nearlyname.equals(Constant.curSMP)){//若是从所有景点页面转来，且和上次景点相同则不从新加载
//							break outer;
//					}
//					if(!flag){//若是从所有景点处转来，则不改变当前景点的记录值
//						Constant.curScenicId=nearlyname;//改变记录当前景点编号的常量
//						
//					}
//					Constant.curSMP=nearlyname;//改变正在播报的景点id
//					if(Constant.mp!=null){Constant.mp.release();}
//					//int soundId=getResources().getIdentifier(message[2], "raw", getPackageName());//获取相应声音名的id
//					Constant.mp=new MediaPlayer();
//					//AssetFileDescriptor fileDescriptor;
//					try {
//						Constant.mp.setDataSource("zshz/jingdian/audio_file/"+nearlyname+"1.wav");
//						/*fileDescriptor = this.getResources().getAssets().openFd("audio_file/"+message[2]+".wav");
//						Constant.mp.setDataSource(fileDescriptor.getFileDescriptor(),
//		                        fileDescriptor.getStartOffset(),
//		                        fileDescriptor.getLength());*/
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					Constant.mp.setOnCompletionListener(//歌曲播放结束事件的监听器
//							 new OnCompletionListener()
//								{
//									@Override
//									public void onCompletion(MediaPlayer arg0) 
//									{//歌曲播放结束停止播放并更新界面状态
//										arg0.stop();
//										Constant.isPlay=0;//播放完毕后改变标志位	
//										playbt.setBackgroundResource(R.drawable.soundclose);//改变声音图标
//									}			
//								}				 
//					 );
//					 try {	
//						 Constant.mp.prepare();
//						 Constant.mp.start();//开始播放
//					 } catch (Exception e1){					
//						e1.printStackTrace();
//					 }	
//				}	
//				playbt.setOnClickListener(
//						new OnClickListener(){
//							@Override
//							public void onClick(View v) {
//								if(Constant.isPlay==1){//若当前为播放状态
//									Constant.mp.pause();//设播放器为暂停
//									Constant.isPlay=2;//改变标志位为暂停
//									playbt.setBackgroundResource(R.drawable.soundclose);
//								}else if(Constant.isPlay==2){//若为暂停状态
//									Constant.mp.start();//开始播放
//									Constant.isPlay=1;//改变标志位为正在播放
//									playbt.setBackgroundResource(R.drawable.soundopen);
//								}else if(Constant.isPlay==0){
//									if(Constant.mp!=null){Constant.mp.release();}
//									//int soundId=getResources().getIdentifier(message[2], "raw", getPackageName());//获取相应声音名的id
//									Constant.mp=new MediaPlayer();
//									//AssetFileDescriptor fileDescriptor;
//									try {
//										Constant.mp.setDataSource("zshz/jingdian/audio_file/"+nearlyname+"1.wav");
//										/*fileDescriptor = ShowScenicActivity.this.getResources().getAssets().openFd("audio_file/"+message[2]+".wav");
//										Constant.mp.setDataSource(fileDescriptor.getFileDescriptor(),
//						                        fileDescriptor.getStartOffset(),
//						                        fileDescriptor.getLength());*/
//										Constant.mp.prepare();
//										Constant.mp.start();
//									} catch (IOException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//									Constant.isPlay=1;//改变标志位为正在播放
//									playbt.setBackgroundResource(R.drawable.soundopen);
//								}
//							}
//						}
//						);
		 //获得返回按钮的引用
			Button btback=(Button)findViewById(R.id.back);
			btback.setOnClickListener(
					new OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
						  finish();//关闭此Activity
						}
						
					}
					);
			//获得加大字号的按钮图标
			Button bt_size_plus=(Button)findViewById(R.id.size_plus_bt);
			bt_size_plus.setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							size_index=size_index+1;
							if(size_index>Constant.TEXT_SIZES.length-1){
								size_index=size_index-1;
								Toast.makeText(JDNewActivity.this,
										getResources().getString(R.string.text_max),Toast.LENGTH_SHORT).show();
							}
							rvIntro.setTextSize(Constant.TEXT_SIZES[size_index]);
						}
					}
					);
			//获得减小字号的按钮图标
			Button bt_size_minus=(Button)findViewById(R.id.size_minus_bt);
			bt_size_minus.setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							size_index=size_index-1;
							if(size_index<0){
								size_index=size_index+1;
								Toast.makeText(JDNewActivity.this,
										getResources().getString(R.string.text_min),Toast.LENGTH_SHORT).show();
							}
							rvIntro.setTextSize(Constant.TEXT_SIZES[size_index]);
						}
					}
					);
		   
	}
}
