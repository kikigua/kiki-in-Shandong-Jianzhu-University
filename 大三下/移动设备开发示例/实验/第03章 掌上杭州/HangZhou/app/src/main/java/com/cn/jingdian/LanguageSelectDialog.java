package com.cn.jingdian;

import java.util.Locale;

import android.app.ActivityManagerNative;
import android.app.Dialog;
import android.app.IActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.hangzhou.R;
import com.cn.util.Constant;

public class LanguageSelectDialog extends Dialog{
	public static final String[] LANGUAGE={"简体中文","English"};//可选语言种类
	Context context;
	int index;//当前记录的选中值
	public LanguageSelectDialog(Context context) {
		super(context);
		this.context=context;
		String lan=Locale.getDefault().getLanguage();//获取当前系统使用的语言
	    String country=Locale.getDefault().getCountry();//获得地区
	    System.out.println("Language-----"+lan+",   countty="+country);
	    //判断语言和地区种类，确定当前选中项
	    if("zh".equals(lan)&&"CN".equals(country)){//判断语言
	    	index=0;//中文简体
	    }else{
	    	index=1;//英文
	    }
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setTitle(R.string.LanTitle);//设置标题
		setContentView(R.layout.jiandian_moredialog);//转到语言选择布局
		 //用自定义的字体方法
 //       FontManager.changeFonts(FontManager.getContentView(this),this);
		//为listview创建适配器
		BaseAdapter ba=new BaseAdapter(){

			@Override
			public int getCount() {
				return LANGUAGE.length;
			}

			@Override
			public Object getItem(int arg0) {
				return LANGUAGE[arg0];
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
				View view=(View)factory.inflate(R.layout.lanitem, null);
				//从自定义页面中拿到控件引用并赋值
				TextView tv=(TextView)view.findViewById(R.id.languagetv);
				tv.setText(LANGUAGE[position]);
				if(index==position){
					ImageView iv=(ImageView)view.findViewById(R.id.selectediv);
					iv.setImageResource(R.drawable.seletedlan);
				}
		        return view;
			}
			
		};
		ListView lv=(ListView)findViewById(R.id.showLan);//获得显示语言种类的列表
		lv.setAdapter(ba);//设置适配器
		lv.setOnItemClickListener(
				new OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int item, long arg3) {
						Toast.makeText(context, LANGUAGE[item], Toast.LENGTH_SHORT).show();
				        if(item==0){//为简体中文
				        	Constant.snzy="zhongn";
				        	updateLanguage(Locale.SIMPLIFIED_CHINESE);
				        	
				        }else if(item==1){//英语
				        	Constant.snzy="yingn";
				        	updateLanguage(Locale.ENGLISH);				        	
				        }
				        dismiss();//关闭
					}
					
				}
				);
	}
	//改变系统语言设置的方法
		private void updateLanguage(Locale locale) {
			IActivityManager iActMag = ActivityManagerNative.getDefault();
			try {
				Configuration config = iActMag.getConfiguration();
				config.locale = locale;

				iActMag.updateConfiguration(config);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	
}
