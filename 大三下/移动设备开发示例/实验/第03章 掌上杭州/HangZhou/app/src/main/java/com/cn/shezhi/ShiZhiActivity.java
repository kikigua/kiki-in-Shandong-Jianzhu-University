package com.cn.shezhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.cn.hangzhou.R;
import com.cn.util.FontManager;

public class ShiZhiActivity extends Activity{
	public static Activity shouyeAc;
	public String[] leiMing=new String[]{"设置字体","使用帮助","关于软件"};
	public List<? extends Map<String,?>> generateDataList()
  			{
  		            ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
  		            for(int i=0;i<3;i++)
  		            {
  		            	HashMap<String,Object> hmp=new HashMap<String,Object>();
  		            	hmp.put("col1",leiMing[i]);  //存菜名
  		            	list.add(hmp);
  		            }
  		            return list;
  			}
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);   //去掉标题栏
	        setContentView(R.layout.shezhi);
	      //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	        shouyeAc=this;   //为了使下一个Activity拿到此Activity的引用
	        TextView yy=(TextView)this.findViewById(R.id.shizhe_title);
	        yy.setText("设置");
	        //设置分类的菜名
		     GridView gv=(GridView)this.findViewById(R.id.shezhi_gv);
		   
		   //设置适配器
		     SimpleAdapter sca=new SimpleAdapter
		                (
		                  this,
		                  generateDataList(), //数据List
		                  R.layout.shezhi_row, //行对应layout id
		                  new String[]{"col1"},  //列名列表
		                  new int[]{R.id.shizhi_lm}//列对应控件id列表
		                );
		     gv.setAdapter(sca);
		     //为列表添加监听
		     gv.setOnItemClickListener(
		        		new OnItemClickListener()
		        		{
		        			@Override
		        			public void onItemClick(AdapterView<?> arg0,View arg1,int arg2,long arg3)
		        			{
		        				if(arg2==0)
		        				{
		        					Intent intent=new Intent(ShiZhiActivity.this,DetailFLActivity.class);
		        				    intent.putExtra("LeiMing","ZiTi");
		    						startActivity(intent);
		        				}
		        				if(arg2==1)
		        				{
		        					Intent intent=new Intent(ShiZhiActivity.this,DetailFLActivity.class);
		    						intent.putExtra("LeiMing","BanZhu");
		    						startActivity(intent);
		        				}
		        				if(arg2==2)
		        				{
		        					guanyu();
		        				}
		        			}
		        		}
		        		);
	 }
	  protected void dialog() { 
	        AlertDialog.Builder builder = new Builder(ShiZhiActivity.this); 
	        builder.setMessage("您确定要退出吗?"); 
//	        builder.setTitle(" 提示"); 
	        builder.setPositiveButton("确认", 
	                new android.content.DialogInterface.OnClickListener() { 
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) { 
	                        dialog.dismiss(); 
	                        ShiZhiActivity.this.finish(); 
	                    } 
	                }); 
	        builder.setNegativeButton("取消", 
	                new android.content.DialogInterface.OnClickListener() { 
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) { 
	                        dialog.dismiss(); 
	                    } 
	                }); 
	        builder.create().show(); 
	    }
	 public void guanyu()
	 {
		 final Dialog about_us_Dialog=new Dialog(ShiZhiActivity.this);
			about_us_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			View aoubtView=ShiZhiActivity.this.getLayoutInflater().inflate(R.layout.about_sz,null);//得到关于我们的布局文件
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
	 }
	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) { 
	        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
	        { 
	            dialog(); 
	            return false; 
	        } 
	        return false; 
	    }
}
