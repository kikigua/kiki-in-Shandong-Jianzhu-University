package com.cn.jingdian;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cn.hangzhou.R;
import com.cn.weibo.WBMainActivity;

public class MoreDialog  extends Dialog{
	JDMainAvtivity context;
	ListView lv;
	public MoreDialog(Context context)
	{
		super(context);
		this.context=(JDMainAvtivity)context;
	}
	public static final int[] MORE={R.string.share,//分享微博0
		                          R.string.findrange,//查找周边1
		                     //     R.string.setting,//设置模式2
		                          R.string.userbackBt,//建议反馈3
		                          R.string.selectLan,//设置语言4
		                          R.string.about};//更多选择中的选项5
	public static final int[] MOREImage={R.drawable.share,
		                                 R.drawable.search,
		                          //       R.drawable.settings,
		                                 R.drawable.email,
		                                 R.drawable.language,
		                                 R.drawable.about};//更多选择中的选项的图片
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(context.getResources().getString(R.string.more));
		setContentView(R.layout.jiandian_moredialog);
		 
		//为listview创建适配器
				BaseAdapter ba=new BaseAdapter(){

					@Override
					public int getCount() {
						return MORE.length;
					}

					@Override
					public Object getItem(int arg0) {
						return MORE[arg0];
					}

					@Override
					public long getItemId(int arg0) {
						return arg0;
					}

					@Override
					public View getView(int position, View convertView, ViewGroup parent) {
						View view=convertView;
						if(null == view){
							//拿到一个LayoutInflater
							LayoutInflater factory=LayoutInflater.from(context);
							//将自定义的listitem.xml实例化,转换为View
							view=(View)factory.inflate(R.layout.moreitem, null);
						}
						//从自定义页面中拿到控件引用并赋值
						TextView tv=(TextView)view.findViewById(R.id.moretv);
						tv.setText(context.getResources().getString(MORE[position]));
						ImageView iv=(ImageView)view.findViewById(R.id.moreiv);
						iv.setImageResource(MOREImage[position]);
						
				        return view;
					}
					
				};
				lv=(ListView)findViewById(R.id.showLan);//获得显示语言种类的列表
				lv.setAdapter(ba);//设置适配器
				lv.setOnItemClickListener(
						new OnItemClickListener(){
							@Override
							public void onItemClick(AdapterView<?> arg0, View arg1,
									int arg2, long arg3) {
								switch(arg2){
								case 0://为分享
									recycledBitmap();//回收图片资源
//									if(NetCheckUtil.checkNet(context)){//若有网络连接，则跳转页面
										Intent intent=new Intent(context,WBMainActivity.class);
										context.startActivity(intent);
//									}else{//提示连接网络
//										Toast.makeText(context,context.getResources().getString(R.string.netmessage)
//												, Toast.LENGTH_SHORT).show();
//									}	
									break;
								case 1://查找周边兴趣点
									Intent intent0=new Intent(context,JDSearchActivity.class);
									context.startActivity(intent0);
									break;
//								case 2://设置模式
//									SelectMode sm=new SelectMode(context);
//									sm.show();
//									break;
								
								
								case 2://反馈
									Intent intent1=new Intent(context,JDJYActivity.class);
									context.startActivity(intent1);
									break;
								case 3://为选择语言
									LanguageSelectDialog lsd=new LanguageSelectDialog(context);
									lsd.show();
									break;
//								case 5://更改资源包
//									AlertDialog.Builder builder=new AlertDialog.Builder(context); 
//						        	builder.setMessage(context.getString(R.string.changezip));
//						        	builder.setPositiveButton(context.getString(R.string.zip_ok), new DialogInterface.OnClickListener() {
//										@Override
//										public void onClick(DialogInterface dialog, int which) {
//											Intent intent=new Intent(context,FillListActivity.class);
//											context.startActivity(intent);
//										}
//									});
//						        	builder.create().show();
//						        	break;
								case 4://关于
									final Dialog about_us_Dialog=new Dialog(context);
									about_us_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
									View aoubtView=context.getLayoutInflater().inflate(R.layout.about_us,null);//得到关于我们的布局文件
									ImageView closeIV=(ImageView) aoubtView.findViewById(R.id.aoubtCanclePic);//关闭图片的引用
									closeIV.setOnClickListener(
											new View.OnClickListener() {
												@Override
												public void onClick(View v) {
													about_us_Dialog.dismiss();
												}
											}
											);
									about_us_Dialog.setContentView(aoubtView);//设置显示内容
									about_us_Dialog.show();//对话框
									break;
								}
						        dismiss();//关闭
							}
							
						}
						);
			}
			
			//回收图片的方法
			public void recycledBitmap(){
				//拿到一个LayoutInflater
				LayoutInflater factory=LayoutInflater.from(context);
				//将自定义的listitem.xml实例化,转换为View
				View view=(View)factory.inflate(R.layout.moreitem, null);
				ImageView iv=(ImageView)view.findViewById(R.id.moreiv);
				BitmapDrawable image=(BitmapDrawable) iv.getDrawable();//得到显示的图片资源
				iv.setImageDrawable(null);//设置为空
				if(null != image && !image.getBitmap().isRecycled()){//不为空且未回收则回收
					image.getBitmap().recycle();//回收资源
				}
			}

			
		}