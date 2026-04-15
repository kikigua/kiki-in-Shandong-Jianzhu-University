package com.cn.weibo;

import com.cn.hangzhou.R;
import com.cn.util.Constant;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SelectPicDialog extends Dialog{
	private WBShareActivity context;
	private int[] selectItems={R.string.from_photo_album,R.string.from_camera};
	private int[] selectItemsIcom={R.drawable.fromalbum,R.drawable.fromcamera};
	public SelectPicDialog(Context context) {
		super(context);
		this.context=(WBShareActivity)context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(context.getResources().getString(R.string.select_method));
		//requestWindowFeature(Window.FEATURE_NO_TITLE); //去掉对话框的标题头部
		setContentView(R.layout.jiandian_moredialog);//转到语言选择布局
		//为listview创建适配器
		BaseAdapter ba=new BaseAdapter(){

			@Override
			public int getCount() {
				return selectItems.length;
			}

			@Override
			public Object getItem(int arg0) {
				return selectItems[arg0];
			}

			@Override
			public long getItemId(int arg0) {
				return arg0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				//拿到一个LayoutInflater
				LayoutInflater factory=LayoutInflater.from(context);
				//将自定义的listitem.xml实例化,转换为View
				View view=(View)factory.inflate(R.layout.moreitem, null);
				//从自定义页面中拿到控件引用并赋值
				TextView tv=(TextView)view.findViewById(R.id.moretv);
				tv.setText(context.getResources().getString(selectItems[position]));
				ImageView iv=(ImageView)view.findViewById(R.id.moreiv);
				iv.setImageResource(selectItemsIcom[position]);
		        return view;
			}
			
		};
		ListView lv=(ListView)findViewById(R.id.showLan);//获得显示语言种类的列表
		lv.setAdapter(ba);//设置适配器
		lv.setOnItemClickListener(
				new OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						switch(arg2){
						case 0://从相册中获取
							Intent intentAlbum = new Intent(Intent.ACTION_PICK, //调用系统图片库
									android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
							context.startActivityForResult(intentAlbum, Constant.FROMALBUM);					
							break;
						case 1://掉相机拍照
							Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
			                context.startActivityForResult(intentCamera,Constant.FROMCAMERA );
							break;
						}
				        dismiss();//关闭
					}
					
				}
				);
	}

	
}
