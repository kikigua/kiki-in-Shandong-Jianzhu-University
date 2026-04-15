package com.cn.hangzhou;

import com.cn.util.BitmapIOUtil;
import com.cn.util.Constant;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;
import com.cn.hangzhou.R;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressWarnings("deprecation")
public class JieActivity extends Activity implements OnClickListener{
	    PubMethod pub=new PubMethod(this);
		static Bitmap[] imageIDs;
	     @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main_js);
	        ImageButton ib=(ImageButton)this.findViewById(R.id.ImageButtonback02);
	        ib.setOnClickListener(this);
	        TextView tv=(TextView)this.findViewById(R.id.jiezi);
	        TextView dname=(TextView)this.findViewById(R.id.diming);
	      //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	        //初始化界
			//获得当前的Intent 
			Intent intent = this.getIntent();
			//获得全部数据 
			Bundle bundle = intent.getExtras();
			String imgPath=bundle.getString("img");	
			String str=this.getIntent().getStringExtra("textEx");
			dname.setText(str);
			//System.out.println(str);
	    	String yxPath=imgPath.substring(12,imgPath.length()-4);
	    	imageIDs=new Bitmap[3];
		    for(int i=0,j=1;i<3;i++,j++)               
		    {
		       imageIDs[i]=BitmapIOUtil.getSBitmap("donghua/jieshao/"+yxPath+"/"+j+".jpg");
		       System.out.println( imageIDs[i]);
		    }			
		    String sText=PubMethod.loadFromFile("donghua/jieshao/"+yxPath+".txt");
		    System.out.println(sText);
			tv.setText(sText);
			tv.setTextSize(Constant.TEXT_SIZE);
			tv.setTextColor(JieActivity.this.getResources().getColor(Constant.COLOR));
	   	
	      Gallery gl=(Gallery)this.findViewById(R.id.jietu);
	      //设置适配器
	      BaseAdapter ba=new BaseAdapter()
	        {
				@Override
				public int getCount() {
					return 3;
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
					ImageView iv = new ImageView(JieActivity.this);
					iv.setImageBitmap(imageIDs[arg0]);
					iv.setScaleType(ImageView.ScaleType.FIT_XY);
					iv.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
					return iv;
				}        	
	        };
	        
	        //将适配器添加进控件
	        gl.setAdapter(ba);
	        gl.setSelection(1);       //选中第二张图片在中间，编号从0开始
	        gl.setOnItemClickListener
	        (
	        		new OnItemClickListener()
	        		{
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) 
						{
							Gallery gl=(Gallery)findViewById(R.id.jietu);
							gl.setSelection(arg2);
//							Toast.makeText(JieActivity.this, "点击了第"+arg2+"张图片", Toast.LENGTH_LONG).show();
							
							 //选中项目，跳转到下一界面
							Intent intent=new Intent();
						    intent.setClass(JieActivity.this,DaActivity.class);
							intent.putExtra("num",arg2);
							startActivity(intent);
						}        			
	        		}
	        );
	 	}
	@Override
	public void onClick(View v) 
	{
		if(v.getId()==R.id.ImageButtonback02)
		{
			 Intent intent=new Intent(JieActivity.this,MainActivityGroup.class);
			 startActivity(intent);		    				  
			 ShouYeActivity.shouyeAc.finish();    				  
			 JieActivity.this.finish();    
		}
	}
}
