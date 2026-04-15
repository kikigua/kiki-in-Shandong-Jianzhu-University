package com.cn.shezhi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cn.hangzhou.R;
import com.cn.util.Constant;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;

public class DetailFLActivity extends Activity implements OnClickListener{
	String shizhi_LM;      //确定当前类别
	TextView f_title;
	String ss;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //去掉标题栏
        setContentView(R.layout.shezhi_detail);
      //用自定义的字体方法
        FontManager.changeFonts(FontManager.getContentView(this),this);
      //接受分类的名称
        shizhi_LM=this.getIntent().getStringExtra("LeiMing");
        System.out.println(shizhi_LM);
        //设置标题
        f_title=(TextView)this.findViewById(R.id.shezhi_title);
        if(shizhi_LM.equals("ZiTi"))
        {
        	
        	f_title.setText("设置字体");
        	
        	intSwitch();
        }
        if(shizhi_LM.equals("BanZhu"))
        {
        	f_title.setText("使用帮助");
        	intSwitch();
        }
//        if(shizhi_LM.equals("GuanYu"))
//        {
//        	f_title.setText("关于软件");
//        	intSwitch();
//        }
        ImageButton ib=(ImageButton)this.findViewById(R.id.shezhi_back);
        ib.setOnClickListener(this);
	}
	public void intSwitch()
	{
		TextView tv=(TextView)this.findViewById(R.id.shezhi_js);
		if(shizhi_LM.equals("BanZhu"))
		{
			//shizhi_LM.equals("BanZhu")
		    ss=PubMethod.loadFromFileZ("shezhi/"+shizhi_LM+".txt");
		    tv.setText(ss);
		    tv.setTextSize(Constant.TEXT_SIZE);		
		}
		else
		{			
			tv.setTextSize(Constant.TEXT_SIZE);
			Intent intent=new Intent(DetailFLActivity.this,SheZhiZiTiActivity.class);		  
			startActivity(intent);			  
			finish();
			
		}
	}
	@Override
	public void onClick(View v)
	{
		if(v.getId()==R.id.shezhi_back)
		{			  
			  finish();
		}
		
	}

}
