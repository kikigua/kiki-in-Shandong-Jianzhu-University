package com.cn.jingdian;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.cn.hangzhou.R;
import com.cn.meishi.DIYGallery;
import com.cn.util.FontManager;

@SuppressWarnings("deprecation")
public class JDMaxActivity extends Activity
{
	int num;
	public void onCreate(Bundle savedInstanceState) {  
	      super.onCreate(savedInstanceState);  
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      setContentView(R.layout.meishi_max);
	    //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	      //接受编号
	      int num=this.getIntent().getIntExtra("num",-1); 
	      System.out.println("========="+num+"========");
	      //System.out.println("++++++++++++++++"+DetailsActivity.imageIDs[1]+"++++++++++++");
	      
	      
	      

	      
	      //设置图片
	      DIYGallery gl=(DIYGallery)this.findViewById(R.id.GalleryMax);
	      //设置适配器
	      BaseAdapter ba=new BaseAdapter()
	        {
				@Override
				public int getCount() {
					return 5;
				}

				@Override
				public Object getItem(int arg0) {			    
					return arg0;
				}

				@Override
				public long getItemId(int arg0) {
					// TODO Auto-generated method stub
					return arg0;
				}

				@Override
				public View getView(int arg0, View arg1, ViewGroup arg2) {
					ImageView iv = new ImageView(JDMaxActivity.this);
					iv.setImageBitmap(JDNewActivity.imageIDs[arg0]);
					iv.setScaleType(ImageView.ScaleType.FIT_XY);
					iv.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT,600));
					
					return iv;
				}        	
	        };
	        
	        //将适配器添加进控件
	        gl.setAdapter(ba);
	        gl.setSelection(num);       //选中第二张图片在中间，编号从0开始
	}
}







