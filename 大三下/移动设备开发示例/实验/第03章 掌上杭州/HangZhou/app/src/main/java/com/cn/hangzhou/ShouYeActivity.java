package com.cn.hangzhou;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import com.baidu.navisdk.BNaviEngineManager.NaviEngineInitListener;
import com.baidu.navisdk.BaiduNaviManager;
import com.cn.gouwu.GouWuActivity;
import com.cn.jingdian.JDMainAvtivity;
import com.cn.meishi.MeiShiActivity;
import com.cn.util.BitmapIOUtil;
import com.cn.util.Constant;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;
import com.cn.yiliao.YiLiaoActivity;
import com.cn.yule.YuLeActivity;
import com.cn.zhusu.ZhuSuActivity;

public class ShouYeActivity extends Activity implements OnClickListener
{
	
	public static Activity shouyeAc;
		ImageButton[] tv; 
		public static SlidingSwitcherView sya;
		int count;//数量
		String[] imgSubPath;//图片路径
		String[] name;//图片名字
		Bitmap[] imgBp;        //图片数组
		String hname;
		//PubMethod pub=new PubMethod(this);
	  
	@Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);   //去掉标题栏
	        setContentView(R.layout.shouye);
	       
	        shouyeAc=this;   //为了使下一个Activity拿到此Activity的引用
	      //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	        //给美食按钮添加监听
//	         ImageButton meishi_ib=(ImageButton)this.findViewById(R.id.main_meishi);
	       //获得当前景点按钮的引用
	         LinearLayout meishi_ib=(LinearLayout)findViewById(R.id.l_meishi);
	         meishi_ib.setOnClickListener(
						new View.OnClickListener() 
						{	
							@Override
							public void onClick(View v) 
							{
								Intent intent=new Intent(ShouYeActivity.this,MeiShiActivity.class);
								startActivity(intent);
							}
						}
						);
	         //给购物按钮添加监听
	        // ImageButton gouwu_ib=(ImageButton)this.findViewById(R.id.main_gouwu);
	         LinearLayout gouwu_ib=(LinearLayout)findViewById(R.id.l_gouwu);
	         gouwu_ib.setOnClickListener(
	        		 new View.OnClickListener() 
	        		 {
						@Override
						public void onClick(View v) 
						{
							Intent intent=new Intent(ShouYeActivity.this,GouWuActivity.class);
							startActivity(intent);
						}
					 }
	        		 );
	         
	         //给医疗按钮添加监听
//	         ImageButton yiliao_ib=(ImageButton)this.findViewById(R.id.yiliao);
	         LinearLayout yiliao_ib=(LinearLayout)findViewById(R.id.l_yiliao);
	         yiliao_ib.setOnClickListener(
	        		 new View.OnClickListener() 
	        		 {
						@Override
						public void onClick(View v) 
						{
							Intent intent=new Intent(ShouYeActivity.this,YiLiaoActivity.class);
							startActivity(intent);
						}
					 }
	        		 );
	         
	         //给娱乐按钮添加监听
	        // ImageButton yule_ib=(ImageButton)this.findViewById(R.id.yule);
	         LinearLayout yule_ib=(LinearLayout)findViewById(R.id.l_yule);
	         yule_ib.setOnClickListener(
	        		 new View.OnClickListener() 
	        		 {
						@Override
						public void onClick(View v) 
						{
							Intent intent=new Intent(ShouYeActivity.this,YuLeActivity.class);
							startActivity(intent);
						}
					 }
	        		 );
	         
	         //给住宿按钮添加监听
	        // ImageButton zhusu_ib=(ImageButton)this.findViewById(R.id.zhusu);
	         LinearLayout zhusu_ib=(LinearLayout)findViewById(R.id.l_zhusu);
	         zhusu_ib.setOnClickListener(
	        		 new View.OnClickListener() 
	        		 {
						@Override
						public void onClick(View v) 
						{
							Intent intent=new Intent(ShouYeActivity.this,ZhuSuActivity.class);
							startActivity(intent);
						}
					 }
	        		 );
	       //给住宿按钮添加监听
	       //  ImageButton jingdian_ib=(ImageButton)this.findViewById(R.id.lvyou);
	         LinearLayout jingdian_ib=(LinearLayout)findViewById(R.id.l_lvyou);
	         jingdian_ib.setOnClickListener(
	        		 new View.OnClickListener() 
	        		 {
						@Override
						public void onClick(View v) 
						{
							Intent intent=new Intent(ShouYeActivity.this,JDMainAvtivity.class);
							startActivity(intent);
						}
					 }
	        		 );
	         tv= new ImageButton[4];
	         tv[0]=(ImageButton) findViewById(R.id.b01);
	         tv[1]=(ImageButton) findViewById(R.id.b02);
	         tv[2]=(ImageButton) findViewById(R.id.b03);
	         tv[3]=(ImageButton) findViewById(R.id.b04);
	 
	       Constant.List=PubMethod.loadFromFile("donghua/fjname.txt");
	  	   Constant.ListArray=Constant.List.split("\\|");
	  	   System.out.println( Constant.ListArray);
	  		//获取数组长度
	  		final int count=Constant.ListArray.length/2;//11
	  		imgSubPath=new String[count];//11长度
			name=new String[count];//长度11
			imgBp=new Bitmap[count];//长度11	
			System.out.println(count);
			for(int i=0;i<count;i++)
			{
				name[i]=Constant.ListArray[2*i];//图片名称
				imgSubPath[i]="donghua/img/"+Constant.ListArray[i*2+1];  //图片路径
		    	imgBp[i]=BitmapIOUtil.getSBitmap(imgSubPath[i]);//根据路径获取图片
			}	
            
			for(int i=0;i<4;i++)
			{
				
				//tv[i].setImageBitmap(imgBp[i]); 
			    @SuppressWarnings("deprecation")
				BitmapDrawable bd= new BitmapDrawable(imgBp[i]);    
			    tv[i].setBackground(bd);                           //贴图
				tv[i].setOnClickListener(this);                    //添加监听
			} 
		     
		        BaiduNaviManager.getInstance().initEngine(this, getSdcardDir(),
		                mNaviEngineInitListener, new LBSAuthManagerListener() {
		                    @Override
		                    public void onAuthResult(int status, String msg) {
		                        if (0 == status) {
		                            System.out.println("=================校验成功！！！");
		                        } else {
		                            System.out.println("=================校验失败！！！");
		                        }
//		                        Toast.makeText(ShouYeActivity.this, str,
//		                                Toast.LENGTH_LONG).show();
		                    }
		                });
		        
	    }
	    protected void dialog() { 
	        AlertDialog.Builder builder = new Builder(ShouYeActivity.this); 
	        builder.setMessage("您确定要退出吗?"); 
//	        builder.setTitle(" 提示"); 
	        builder.setPositiveButton("确认", 
	                new android.content.DialogInterface.OnClickListener() { 
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) { 
	                        dialog.dismiss(); 
	                        ShouYeActivity.this.finish(); 
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
	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) { 
	        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
	        { 
	            dialog(); 
	            return false; 
	        } 
	        return false; 
	    }
	@Override
	public void onClick(View v)
	{
		if(Math.abs(SlidingSwitcherView.xDown-SlidingSwitcherView.xUp)<8)
		{
		if(v.getId()==R.id.b01)
		{
			Intent intent=new Intent();			
			Bundle b=new Bundle();
			b.putString("img", imgSubPath[0]);
			b.putString("textEx", (String)name[0]);
			intent.setClass(ShouYeActivity.this,JieActivity.class);
			intent.putExtras(b);
			startActivity(intent);										
		}
		if(v.getId()==R.id.b02)
		{
			Intent intent=new Intent();			
			Bundle b=new Bundle();
			b.putString("img", imgSubPath[1]);
			b.putString("textEx",(String)name[1]);
			intent.setClass(ShouYeActivity.this,JieActivity.class);
			intent.putExtras(b);
			startActivity(intent);										
		}
		if(v.getId()==R.id.b03)
		{
			Intent intent=new Intent();			
			Bundle b=new Bundle();
			b.putString("img", imgSubPath[2]);
			b.putString("textEx", (String)name[2]);
			intent.setClass(ShouYeActivity.this,JieActivity.class);
			intent.putExtras(b);
			startActivity(intent);										
		}
		if(v.getId()==R.id.b04)
		{
			Intent intent=new Intent();			
			Bundle b=new Bundle();
			b.putString("img", imgSubPath[3]);
			b.putString("textEx", (String)name[3]);
			intent.setClass(ShouYeActivity.this,JieActivity.class);
			intent.putExtras(b);
			startActivity(intent);										
		}
	}
  }
	
	private String getSdcardDir() {
		if (Environment.getExternalStorageState().equalsIgnoreCase(
				Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().toString();
		}
		return null;
	}
	
	private NaviEngineInitListener mNaviEngineInitListener = new NaviEngineInitListener() {
		public void engineInitSuccess() {
		}

		public void engineInitStart() {
		}

		public void engineInitFail() {
		}
	};
}
